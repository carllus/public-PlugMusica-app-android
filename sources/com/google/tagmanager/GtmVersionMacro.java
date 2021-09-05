package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

class GtmVersionMacro extends FunctionCallImplementation {

    /* renamed from: ID */
    private static final String f921ID = FunctionType.GTM_VERSION.toString();

    public static String getFunctionId() {
        return f921ID;
    }

    public GtmVersionMacro() {
        super(f921ID, new String[0]);
    }

    public boolean isCacheable() {
        return true;
    }

    public TypeSystem.Value evaluate(Map<String, TypeSystem.Value> map) {
        return Types.objectToValue("3.02");
    }
}
