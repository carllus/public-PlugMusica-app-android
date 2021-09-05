package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.List;
import java.util.Map;

class DataLayerWriteTag extends TrackingTag {
    private static final String CLEAR_PERSISTENT_DATA_LAYER_PREFIX = Key.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();

    /* renamed from: ID */
    private static final String f912ID = FunctionType.DATA_LAYER_WRITE.toString();
    private static final String VALUE = Key.VALUE.toString();
    private final DataLayer mDataLayer;

    public static String getFunctionId() {
        return f912ID;
    }

    public DataLayerWriteTag(DataLayer dataLayer) {
        super(f912ID, VALUE);
        this.mDataLayer = dataLayer;
    }

    public void evaluateTrackingTag(Map<String, TypeSystem.Value> tag) {
        pushToDataLayer(tag.get(VALUE));
        clearPersistent(tag.get(CLEAR_PERSISTENT_DATA_LAYER_PREFIX));
    }

    private void clearPersistent(TypeSystem.Value value) {
        String prefix;
        if (value != null && value != Types.getDefaultObject() && (prefix = Types.valueToString(value)) != Types.getDefaultString()) {
            this.mDataLayer.clearPersistentKeysWithPrefix(prefix);
        }
    }

    private void pushToDataLayer(TypeSystem.Value value) {
        if (value != null && value != Types.getDefaultObject()) {
            Object o = Types.valueToObject(value);
            if (o instanceof List) {
                for (Object obj : (List) o) {
                    if (obj instanceof Map) {
                        this.mDataLayer.push((Map) obj);
                    }
                }
            }
        }
    }
}
