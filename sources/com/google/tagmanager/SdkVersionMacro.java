package com.google.tagmanager;

import android.os.Build;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

class SdkVersionMacro extends FunctionCallImplementation {

    /* renamed from: ID */
    private static final String f936ID = FunctionType.SDK_VERSION.toString();

    public static String getFunctionId() {
        return f936ID;
    }

    public SdkVersionMacro() {
        super(f936ID, new String[0]);
    }

    public boolean isCacheable() {
        return true;
    }

    public TypeSystem.Value evaluate(Map<String, TypeSystem.Value> map) {
        return Types.objectToValue(Integer.valueOf(Build.VERSION.SDK_INT));
    }
}
