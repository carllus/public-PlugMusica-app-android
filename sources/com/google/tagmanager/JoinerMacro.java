package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class JoinerMacro extends FunctionCallImplementation {
    private static final String ARG0 = Key.ARG0.toString();
    private static final String DEFAULT_ITEM_SEPARATOR = "";
    private static final String DEFAULT_KEY_VALUE_SEPARATOR = "=";
    private static final String ESCAPE = Key.ESCAPE.toString();

    /* renamed from: ID */
    private static final String f924ID = FunctionType.JOINER.toString();
    private static final String ITEM_SEPARATOR = Key.ITEM_SEPARATOR.toString();
    private static final String KEY_VALUE_SEPARATOR = Key.KEY_VALUE_SEPARATOR.toString();

    private enum EscapeType {
        NONE,
        URL,
        BACKSLASH
    }

    public static String getFunctionId() {
        return f924ID;
    }

    public JoinerMacro() {
        super(f924ID, ARG0);
    }

    public boolean isCacheable() {
        return true;
    }

    public TypeSystem.Value evaluate(Map<String, TypeSystem.Value> parameters) {
        TypeSystem.Value input = parameters.get(ARG0);
        if (input == null) {
            return Types.getDefaultValue();
        }
        TypeSystem.Value itemSeparatorParameter = parameters.get(ITEM_SEPARATOR);
        String itemSeparator = itemSeparatorParameter != null ? Types.valueToString(itemSeparatorParameter) : "";
        TypeSystem.Value keyValueSeparatorParameter = parameters.get(KEY_VALUE_SEPARATOR);
        String keyValueSeparator = keyValueSeparatorParameter != null ? Types.valueToString(keyValueSeparatorParameter) : DEFAULT_KEY_VALUE_SEPARATOR;
        EscapeType escapeType = EscapeType.NONE;
        TypeSystem.Value escapeParameter = parameters.get(ESCAPE);
        Set<Character> charsToBackslashEscape = null;
        if (escapeParameter != null) {
            String escape = Types.valueToString(escapeParameter);
            if ("url".equals(escape)) {
                escapeType = EscapeType.URL;
            } else if ("backslash".equals(escape)) {
                escapeType = EscapeType.BACKSLASH;
                charsToBackslashEscape = new HashSet<>();
                addTo(charsToBackslashEscape, itemSeparator);
                addTo(charsToBackslashEscape, keyValueSeparator);
                charsToBackslashEscape.remove(92);
            } else {
                Log.m175e("Joiner: unsupported escape type: " + escape);
                return Types.getDefaultValue();
            }
        }
        StringBuilder sb = new StringBuilder();
        switch (input.type) {
            case 2:
                boolean firstTime = true;
                for (TypeSystem.Value itemVal : input.listItem) {
                    if (!firstTime) {
                        sb.append(itemSeparator);
                    }
                    firstTime = false;
                    append(sb, Types.valueToString(itemVal), escapeType, charsToBackslashEscape);
                }
                break;
            case 3:
                for (int i = 0; i < input.mapKey.length; i++) {
                    if (i > 0) {
                        sb.append(itemSeparator);
                    }
                    String key = Types.valueToString(input.mapKey[i]);
                    String value = Types.valueToString(input.mapValue[i]);
                    append(sb, key, escapeType, charsToBackslashEscape);
                    sb.append(keyValueSeparator);
                    append(sb, value, escapeType, charsToBackslashEscape);
                }
                break;
            default:
                append(sb, Types.valueToString(input), escapeType, charsToBackslashEscape);
                break;
        }
        return Types.objectToValue(sb.toString());
    }

    private void addTo(Set<Character> set, String string) {
        for (int i = 0; i < string.length(); i++) {
            set.add(Character.valueOf(string.charAt(i)));
        }
    }

    private void append(StringBuilder sb, String s, EscapeType escapeType, Set<Character> charsToBackslashEscape) {
        sb.append(escape(s, escapeType, charsToBackslashEscape));
    }

    private String escape(String s, EscapeType escapeType, Set<Character> charsToBackslashEscape) {
        switch (escapeType) {
            case URL:
                try {
                    return ValueEscapeUtil.urlEncode(s);
                } catch (UnsupportedEncodingException e) {
                    Log.m176e("Joiner: unsupported encoding", e);
                    return s;
                }
            case BACKSLASH:
                String s2 = s.replace("\\", "\\\\");
                for (Character c : charsToBackslashEscape) {
                    String charAsString = c.toString();
                    s2 = s2.replace(charAsString, "\\" + charAsString);
                }
                return s2;
            default:
                return s;
        }
    }
}
