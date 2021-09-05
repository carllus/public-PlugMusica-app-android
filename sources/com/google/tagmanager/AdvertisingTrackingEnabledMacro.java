package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

class AdvertisingTrackingEnabledMacro extends FunctionCallImplementation {

    /* renamed from: ID */
    private static final String f901ID = FunctionType.ADVERTISING_TRACKING_ENABLED.toString();

    public static String getFunctionId() {
        return f901ID;
    }

    public AdvertisingTrackingEnabledMacro() {
        super(f901ID, new String[0]);
    }

    public boolean isCacheable() {
        return true;
    }

    public TypeSystem.Value evaluate(Map<String, TypeSystem.Value> map) {
        return Types.objectToValue(true);
    }
}
