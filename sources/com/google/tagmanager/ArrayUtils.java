package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug;

class ArrayUtils {
    private ArrayUtils() {
    }

    public static Debug.EventInfo[] appendToArray(Debug.EventInfo[] from, Debug.EventInfo itemToAppend) {
        Debug.EventInfo[] result = new Debug.EventInfo[(from.length + 1)];
        System.arraycopy(from, 0, result, 0, from.length);
        result[from.length] = itemToAppend;
        return result;
    }

    public static Debug.ResolvedFunctionCall[] appendToArray(Debug.ResolvedFunctionCall[] from, Debug.ResolvedFunctionCall itemToAppend) {
        Debug.ResolvedFunctionCall[] result = new Debug.ResolvedFunctionCall[(from.length + 1)];
        System.arraycopy(from, 0, result, 0, from.length);
        result[from.length] = itemToAppend;
        return result;
    }

    public static Debug.ResolvedProperty[] appendToArray(Debug.ResolvedProperty[] from, Debug.ResolvedProperty itemToAppend) {
        Debug.ResolvedProperty[] result = new Debug.ResolvedProperty[(from.length + 1)];
        System.arraycopy(from, 0, result, 0, from.length);
        result[from.length] = itemToAppend;
        return result;
    }

    public static Debug.ResolvedRule[] appendToArray(Debug.ResolvedRule[] from, Debug.ResolvedRule itemToAppend) {
        Debug.ResolvedRule[] result = new Debug.ResolvedRule[(from.length + 1)];
        System.arraycopy(from, 0, result, 0, from.length);
        result[from.length] = itemToAppend;
        return result;
    }
}
