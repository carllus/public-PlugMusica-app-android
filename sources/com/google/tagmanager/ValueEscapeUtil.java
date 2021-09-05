package com.google.tagmanager;

import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class ValueEscapeUtil {
    private ValueEscapeUtil() {
    }

    static ObjectAndStatic<TypeSystem.Value> applyEscapings(ObjectAndStatic<TypeSystem.Value> value, int... escapings) {
        ObjectAndStatic<TypeSystem.Value> escapedValue = value;
        for (int escaping : escapings) {
            escapedValue = applyEscaping(escapedValue, escaping);
        }
        return escapedValue;
    }

    static String urlEncode(String string) throws UnsupportedEncodingException {
        return URLEncoder.encode(string, "UTF-8").replaceAll("\\+", "%20");
    }

    private static ObjectAndStatic<TypeSystem.Value> applyEscaping(ObjectAndStatic<TypeSystem.Value> value, int escaping) {
        if (!isValidStringType(value.getObject())) {
            Log.m175e("Escaping can only be applied to strings.");
            return value;
        }
        switch (escaping) {
            case 12:
                return escapeUri(value);
            default:
                Log.m175e("Unsupported Value Escaping: " + escaping);
                return value;
        }
    }

    private static ObjectAndStatic<TypeSystem.Value> escapeUri(ObjectAndStatic<TypeSystem.Value> value) {
        try {
            return new ObjectAndStatic<>(Types.objectToValue(urlEncode(Types.valueToString(value.getObject()))), value.isStatic());
        } catch (UnsupportedEncodingException e) {
            Log.m176e("Escape URI: unsupported encoding", e);
            return value;
        }
    }

    private static boolean isValidStringType(TypeSystem.Value value) {
        return Types.valueToObject(value) instanceof String;
    }
}
