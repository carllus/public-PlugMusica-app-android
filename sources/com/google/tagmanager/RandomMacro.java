package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.util.Map;

class RandomMacro extends FunctionCallImplementation {

    /* renamed from: ID */
    private static final String f931ID = FunctionType.RANDOM.toString();
    private static final String MAX = Key.MAX.toString();
    private static final String MIN = Key.MIN.toString();

    public static String getFunctionId() {
        return f931ID;
    }

    public RandomMacro() {
        super(f931ID, new String[0]);
    }

    public boolean isCacheable() {
        return false;
    }

    public TypeSystem.Value evaluate(Map<String, TypeSystem.Value> parameters) {
        double min = 0.0d;
        double max = 2.147483647E9d;
        TypeSystem.Value minParameter = parameters.get(MIN);
        TypeSystem.Value maxParameter = parameters.get(MAX);
        if (!(minParameter == null || minParameter == Types.getDefaultValue() || maxParameter == null || maxParameter == Types.getDefaultValue())) {
            TypedNumber minValue = Types.valueToNumber(minParameter);
            TypedNumber maxValue = Types.valueToNumber(maxParameter);
            if (!(minValue == Types.getDefaultNumber() || maxValue == Types.getDefaultNumber())) {
                double minDouble = minValue.doubleValue();
                double maxDouble = maxValue.doubleValue();
                if (minDouble <= maxDouble) {
                    min = minDouble;
                    max = maxDouble;
                }
            }
        }
        return Types.objectToValue(Long.valueOf(Math.round((Math.random() * (max - min)) + min)));
    }
}
