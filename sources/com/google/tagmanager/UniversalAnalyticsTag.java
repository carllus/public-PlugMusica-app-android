package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.HitTypes;
import com.google.analytics.tracking.android.Tracker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class UniversalAnalyticsTag extends TrackingTag {
    private static final String ACCOUNT = Key.ACCOUNT.toString();
    private static final String ANALYTICS_FIELDS = Key.ANALYTICS_FIELDS.toString();
    private static final String ANALYTICS_PASS_THROUGH = Key.ANALYTICS_PASS_THROUGH.toString();
    private static final String DEFAULT_TRACKING_ID = "_GTM_DEFAULT_TRACKER_";

    /* renamed from: ID */
    private static final String f939ID = FunctionType.UNIVERSAL_ANALYTICS.toString();
    private static final String TRACK_TRANSACTION = Key.TRACK_TRANSACTION.toString();
    private static final String TRANSACTION_DATALAYER_MAP = Key.TRANSACTION_DATALAYER_MAP.toString();
    private static final String TRANSACTION_ITEM_DATALAYER_MAP = Key.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static Map<String, String> defaultItemMap;
    private static Map<String, String> defaultTransactionMap;
    private final DataLayer mDataLayer;
    private final TrackerProvider mTrackerProvider;
    private final Set<String> mTurnOffAnonymizeIpValues;

    public static String getFunctionId() {
        return f939ID;
    }

    public UniversalAnalyticsTag(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new TrackerProvider(context));
    }

    @VisibleForTesting
    UniversalAnalyticsTag(Context context, DataLayer dataLayer, TrackerProvider trackerProvider) {
        super(f939ID, new String[0]);
        this.mDataLayer = dataLayer;
        this.mTrackerProvider = trackerProvider;
        this.mTurnOffAnonymizeIpValues = new HashSet();
        this.mTurnOffAnonymizeIpValues.add("");
        this.mTurnOffAnonymizeIpValues.add("0");
        this.mTurnOffAnonymizeIpValues.add("false");
    }

    private boolean checkBooleanProperty(Map<String, TypeSystem.Value> tag, String key) {
        TypeSystem.Value value = tag.get(key);
        if (value == null) {
            return false;
        }
        return Types.valueToBoolean(value).booleanValue();
    }

    public void evaluateTrackingTag(Map<String, TypeSystem.Value> tag) {
        Tracker tracker = this.mTrackerProvider.getTracker(DEFAULT_TRACKING_ID);
        if (checkBooleanProperty(tag, ANALYTICS_PASS_THROUGH)) {
            tracker.send(convertToGaFields(tag.get(ANALYTICS_FIELDS)));
        } else if (checkBooleanProperty(tag, TRACK_TRANSACTION)) {
            sendTransaction(tracker, tag);
        } else {
            Log.m181w("Ignoring unknown tag.");
        }
        this.mTrackerProvider.close(tracker);
    }

    private String getDataLayerString(String field) {
        Object data = this.mDataLayer.get(field);
        if (data == null) {
            return null;
        }
        return data.toString();
    }

    private List<Map<String, String>> getTransactionItems() {
        Object data = this.mDataLayer.get("transactionProducts");
        if (data == null) {
            return null;
        }
        if (!(data instanceof List)) {
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        }
        for (Object obj : (List) data) {
            if (!(obj instanceof Map)) {
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
            }
        }
        return (List) data;
    }

    private void addParam(Map<String, String> itemParams, String gaKey, String value) {
        if (value != null) {
            itemParams.put(gaKey, value);
        }
    }

    private void sendTransaction(Tracker tracker, Map<String, TypeSystem.Value> tag) {
        String transactionId = getDataLayerString("transactionId");
        if (transactionId == null) {
            Log.m175e("Cannot find transactionId in data layer.");
            return;
        }
        List<Map<String, String>> sendQueue = new LinkedList<>();
        try {
            Map<String, String> params = convertToGaFields(tag.get(ANALYTICS_FIELDS));
            params.put(Fields.HIT_TYPE, HitTypes.TRANSACTION);
            for (Map.Entry<String, String> entry : getTransactionFields(tag).entrySet()) {
                addParam(params, entry.getValue(), getDataLayerString(entry.getKey()));
            }
            sendQueue.add(params);
            List<Map<String, String>> items = getTransactionItems();
            if (items != null) {
                for (Map<String, String> item : items) {
                    if (item.get("name") == null) {
                        Log.m175e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map<String, String> itemParams = convertToGaFields(tag.get(ANALYTICS_FIELDS));
                    itemParams.put(Fields.HIT_TYPE, HitTypes.ITEM);
                    itemParams.put(Fields.TRANSACTION_ID, transactionId);
                    for (Map.Entry<String, String> entry2 : getTransactionItemFields(tag).entrySet()) {
                        addParam(itemParams, entry2.getValue(), item.get(entry2.getKey()));
                    }
                    sendQueue.add(itemParams);
                }
            }
            for (Map<String, String> gaParam : sendQueue) {
                tracker.send(gaParam);
            }
        } catch (IllegalArgumentException e) {
            Log.m176e("Unable to send transaction", e);
        }
    }

    private Map<String, String> valueToMap(TypeSystem.Value mapValue) {
        Object o = Types.valueToObject(mapValue);
        if (!(o instanceof Map)) {
            return null;
        }
        Map<String, String> stringMap = new LinkedHashMap<>();
        for (Map.Entry entry : ((Map) o).entrySet()) {
            stringMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return stringMap;
    }

    private Map<String, String> convertToGaFields(TypeSystem.Value analyticsFields) {
        if (analyticsFields == null) {
            return new HashMap();
        }
        Map<String, String> params = valueToMap(analyticsFields);
        if (params == null) {
            return new HashMap<>();
        }
        String aip = params.get(Fields.ANONYMIZE_IP);
        if (aip == null || !this.mTurnOffAnonymizeIpValues.contains(aip.toLowerCase())) {
            return params;
        }
        params.remove(Fields.ANONYMIZE_IP);
        return params;
    }

    private Map<String, String> getTransactionFields(Map<String, TypeSystem.Value> tag) {
        TypeSystem.Value map = tag.get(TRANSACTION_DATALAYER_MAP);
        if (map != null) {
            return valueToMap(map);
        }
        if (defaultTransactionMap == null) {
            HashMap<String, String> defaultMap = new HashMap<>();
            defaultMap.put("transactionId", Fields.TRANSACTION_ID);
            defaultMap.put("transactionAffiliation", Fields.TRANSACTION_AFFILIATION);
            defaultMap.put("transactionTax", Fields.TRANSACTION_TAX);
            defaultMap.put("transactionShipping", Fields.TRANSACTION_SHIPPING);
            defaultMap.put("transactionTotal", Fields.TRANSACTION_REVENUE);
            defaultMap.put("transactionCurrency", Fields.CURRENCY_CODE);
            defaultTransactionMap = defaultMap;
        }
        return defaultTransactionMap;
    }

    private Map<String, String> getTransactionItemFields(Map<String, TypeSystem.Value> tag) {
        TypeSystem.Value map = tag.get(TRANSACTION_ITEM_DATALAYER_MAP);
        if (map != null) {
            return valueToMap(map);
        }
        if (defaultItemMap == null) {
            HashMap<String, String> defaultMap = new HashMap<>();
            defaultMap.put("name", Fields.ITEM_NAME);
            defaultMap.put("sku", Fields.ITEM_SKU);
            defaultMap.put("category", Fields.ITEM_CATEGORY);
            defaultMap.put("price", Fields.ITEM_PRICE);
            defaultMap.put("quantity", Fields.ITEM_QUANTITY);
            defaultMap.put("currency", Fields.CURRENCY_CODE);
            defaultItemMap = defaultMap;
        }
        return defaultItemMap;
    }
}
