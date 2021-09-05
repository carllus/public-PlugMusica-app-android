package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

class EqualsPredicate extends StringPredicate {

    /* renamed from: ID */
    private static final String f917ID = FunctionType.EQUALS.toString();

    public static String getFunctionId() {
        return f917ID;
    }

    public EqualsPredicate() {
        super(f917ID);
    }

    /* access modifiers changed from: protected */
    public boolean evaluateString(String arg0, String arg1, Map<String, TypeSystem.Value> map) {
        return arg0.equals(arg1);
    }
}
