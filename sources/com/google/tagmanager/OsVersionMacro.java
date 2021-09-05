package com.google.tagmanager;

import android.os.Build;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

class OsVersionMacro extends FunctionCallImplementation {

    /* renamed from: ID */
    private static final String f929ID = FunctionType.OS_VERSION.toString();

    public static String getFunctionId() {
        return f929ID;
    }

    public OsVersionMacro() {
        super(f929ID, new String[0]);
    }

    public boolean isCacheable() {
        return true;
    }

    public TypeSystem.Value evaluate(Map<String, TypeSystem.Value> map) {
        return Types.objectToValue(Build.VERSION.RELEASE);
    }
}
