package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

class LessThanPredicate extends NumberPredicate {

    /* renamed from: ID */
    private static final String f927ID = FunctionType.LESS_THAN.toString();

    public static String getFunctionId() {
        return f927ID;
    }

    public LessThanPredicate() {
        super(f927ID);
    }

    /* access modifiers changed from: protected */
    public boolean evaluateNumber(TypedNumber arg0, TypedNumber arg1, Map<String, TypeSystem.Value> map) {
        return arg0.compareTo(arg1) < 0;
    }
}
