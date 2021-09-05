package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

class GreaterEqualsPredicate extends NumberPredicate {

    /* renamed from: ID */
    private static final String f919ID = FunctionType.GREATER_EQUALS.toString();

    public static String getFunctionId() {
        return f919ID;
    }

    public GreaterEqualsPredicate() {
        super(f919ID);
    }

    /* access modifiers changed from: protected */
    public boolean evaluateNumber(TypedNumber arg0, TypedNumber arg1, Map<String, TypeSystem.Value> map) {
        return arg0.compareTo(arg1) >= 0;
    }
}
