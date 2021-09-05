package com.google.tagmanager;

import com.google.analytics.containertag.common.Key;
import com.google.analytics.containertag.proto.Serving;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ResourceUtil {
    private static final int BUFFER_SIZE = 1024;

    private ResourceUtil() {
    }

    public static class InvalidResourceException extends Exception {
        public InvalidResourceException(String s) {
            super(s);
        }
    }

    public static class ExpandedFunctionCallBuilder {
        private final Map<String, TypeSystem.Value> mPropertiesMap;
        private TypeSystem.Value mPushAfterEvaluate;

        private ExpandedFunctionCallBuilder() {
            this.mPropertiesMap = new HashMap();
        }

        public ExpandedFunctionCallBuilder addProperty(String key, TypeSystem.Value value) {
            this.mPropertiesMap.put(key, value);
            return this;
        }

        public ExpandedFunctionCallBuilder setPushAfterEvaluate(TypeSystem.Value value) {
            this.mPushAfterEvaluate = value;
            return this;
        }

        public ExpandedFunctionCall build() {
            return new ExpandedFunctionCall(this.mPropertiesMap, this.mPushAfterEvaluate);
        }
    }

    public static class ExpandedFunctionCall {
        private final Map<String, TypeSystem.Value> mPropertiesMap;
        private final TypeSystem.Value mPushAfterEvaluate;

        private ExpandedFunctionCall(Map<String, TypeSystem.Value> propertiesMap, TypeSystem.Value pushAfterEvaluate) {
            this.mPropertiesMap = propertiesMap;
            this.mPushAfterEvaluate = pushAfterEvaluate;
        }

        public static ExpandedFunctionCallBuilder newBuilder() {
            return new ExpandedFunctionCallBuilder();
        }

        public void updateCacheableProperty(String key, TypeSystem.Value v) {
            this.mPropertiesMap.put(key, v);
        }

        public Map<String, TypeSystem.Value> getProperties() {
            return Collections.unmodifiableMap(this.mPropertiesMap);
        }

        public TypeSystem.Value getPushAfterEvaluate() {
            return this.mPushAfterEvaluate;
        }

        public String toString() {
            return "Properties: " + getProperties() + " pushAfterEvaluate: " + this.mPushAfterEvaluate;
        }
    }

    public static class ExpandedRuleBuilder {
        private final List<String> mAddMacroRuleNames;
        private final List<ExpandedFunctionCall> mAddMacros;
        private final List<String> mAddTagRuleNames;
        private final List<ExpandedFunctionCall> mAddTags;
        private final List<ExpandedFunctionCall> mNegativePredicates;
        private final List<ExpandedFunctionCall> mPositivePredicates;
        private final List<String> mRemoveMacroRuleNames;
        private final List<ExpandedFunctionCall> mRemoveMacros;
        private final List<String> mRemoveTagRuleNames;
        private final List<ExpandedFunctionCall> mRemoveTags;

        private ExpandedRuleBuilder() {
            this.mPositivePredicates = new ArrayList();
            this.mNegativePredicates = new ArrayList();
            this.mAddTags = new ArrayList();
            this.mRemoveTags = new ArrayList();
            this.mAddMacros = new ArrayList();
            this.mRemoveMacros = new ArrayList();
            this.mAddMacroRuleNames = new ArrayList();
            this.mRemoveMacroRuleNames = new ArrayList();
            this.mAddTagRuleNames = new ArrayList();
            this.mRemoveTagRuleNames = new ArrayList();
        }

        public ExpandedRuleBuilder addPositivePredicate(ExpandedFunctionCall f) {
            this.mPositivePredicates.add(f);
            return this;
        }

        public ExpandedRuleBuilder addNegativePredicate(ExpandedFunctionCall f) {
            this.mNegativePredicates.add(f);
            return this;
        }

        public ExpandedRuleBuilder addAddTag(ExpandedFunctionCall f) {
            this.mAddTags.add(f);
            return this;
        }

        public ExpandedRuleBuilder addAddTagRuleName(String ruleName) {
            this.mAddTagRuleNames.add(ruleName);
            return this;
        }

        public ExpandedRuleBuilder addRemoveTag(ExpandedFunctionCall f) {
            this.mRemoveTags.add(f);
            return this;
        }

        public ExpandedRuleBuilder addRemoveTagRuleName(String ruleName) {
            this.mRemoveTagRuleNames.add(ruleName);
            return this;
        }

        public ExpandedRuleBuilder addAddMacro(ExpandedFunctionCall f) {
            this.mAddMacros.add(f);
            return this;
        }

        public ExpandedRuleBuilder addAddMacroRuleName(String ruleName) {
            this.mAddMacroRuleNames.add(ruleName);
            return this;
        }

        public ExpandedRuleBuilder addRemoveMacro(ExpandedFunctionCall f) {
            this.mRemoveMacros.add(f);
            return this;
        }

        public ExpandedRuleBuilder addRemoveMacroRuleName(String ruleName) {
            this.mRemoveMacroRuleNames.add(ruleName);
            return this;
        }

        public ExpandedRule build() {
            return new ExpandedRule(this.mPositivePredicates, this.mNegativePredicates, this.mAddTags, this.mRemoveTags, this.mAddMacros, this.mRemoveMacros, this.mAddMacroRuleNames, this.mRemoveMacroRuleNames, this.mAddTagRuleNames, this.mRemoveTagRuleNames);
        }
    }

    public static class ExpandedRule {
        private final List<String> mAddMacroRuleNames;
        private final List<ExpandedFunctionCall> mAddMacros;
        private final List<String> mAddTagRuleNames;
        private final List<ExpandedFunctionCall> mAddTags;
        private final List<ExpandedFunctionCall> mNegativePredicates;
        private final List<ExpandedFunctionCall> mPositivePredicates;
        private final List<String> mRemoveMacroRuleNames;
        private final List<ExpandedFunctionCall> mRemoveMacros;
        private final List<String> mRemoveTagRuleNames;
        private final List<ExpandedFunctionCall> mRemoveTags;

        private ExpandedRule(List<ExpandedFunctionCall> postivePredicates, List<ExpandedFunctionCall> negativePredicates, List<ExpandedFunctionCall> addTags, List<ExpandedFunctionCall> removeTags, List<ExpandedFunctionCall> addMacros, List<ExpandedFunctionCall> removeMacros, List<String> addMacroRuleNames, List<String> removeMacroRuleNames, List<String> addTagRuleNames, List<String> removeTagRuleNames) {
            this.mPositivePredicates = Collections.unmodifiableList(postivePredicates);
            this.mNegativePredicates = Collections.unmodifiableList(negativePredicates);
            this.mAddTags = Collections.unmodifiableList(addTags);
            this.mRemoveTags = Collections.unmodifiableList(removeTags);
            this.mAddMacros = Collections.unmodifiableList(addMacros);
            this.mRemoveMacros = Collections.unmodifiableList(removeMacros);
            this.mAddMacroRuleNames = Collections.unmodifiableList(addMacroRuleNames);
            this.mRemoveMacroRuleNames = Collections.unmodifiableList(removeMacroRuleNames);
            this.mAddTagRuleNames = Collections.unmodifiableList(addTagRuleNames);
            this.mRemoveTagRuleNames = Collections.unmodifiableList(removeTagRuleNames);
        }

        public static ExpandedRuleBuilder newBuilder() {
            return new ExpandedRuleBuilder();
        }

        public List<ExpandedFunctionCall> getPositivePredicates() {
            return this.mPositivePredicates;
        }

        public List<ExpandedFunctionCall> getNegativePredicates() {
            return this.mNegativePredicates;
        }

        public List<ExpandedFunctionCall> getAddTags() {
            return this.mAddTags;
        }

        public List<ExpandedFunctionCall> getRemoveTags() {
            return this.mRemoveTags;
        }

        public List<ExpandedFunctionCall> getAddMacros() {
            return this.mAddMacros;
        }

        public List<String> getAddMacroRuleNames() {
            return this.mAddMacroRuleNames;
        }

        public List<String> getRemoveMacroRuleNames() {
            return this.mRemoveMacroRuleNames;
        }

        public List<String> getAddTagRuleNames() {
            return this.mAddTagRuleNames;
        }

        public List<String> getRemoveTagRuleNames() {
            return this.mRemoveTagRuleNames;
        }

        public List<ExpandedFunctionCall> getRemoveMacros() {
            return this.mRemoveMacros;
        }

        public String toString() {
            return "Positive predicates: " + getPositivePredicates() + "  Negative predicates: " + getNegativePredicates() + "  Add tags: " + getAddTags() + "  Remove tags: " + getRemoveTags() + "  Add macros: " + getAddMacros() + "  Remove macros: " + getRemoveMacros();
        }
    }

    public static class ExpandedResourceBuilder {
        private final Map<String, List<ExpandedFunctionCall>> mMacros;
        private int mResourceFormatVersion;
        private final List<ExpandedRule> mRules;
        private String mVersion;

        private ExpandedResourceBuilder() {
            this.mRules = new ArrayList();
            this.mMacros = new HashMap();
            this.mVersion = "";
            this.mResourceFormatVersion = 0;
        }

        public ExpandedResourceBuilder addRule(ExpandedRule r) {
            this.mRules.add(r);
            return this;
        }

        public ExpandedResourceBuilder addMacro(ExpandedFunctionCall f) {
            String macroName = Types.valueToString(f.getProperties().get(Key.INSTANCE_NAME.toString()));
            List<ExpandedFunctionCall> macroList = this.mMacros.get(macroName);
            if (macroList == null) {
                macroList = new ArrayList<>();
                this.mMacros.put(macroName, macroList);
            }
            macroList.add(f);
            return this;
        }

        public ExpandedResourceBuilder setVersion(String version) {
            this.mVersion = version;
            return this;
        }

        public ExpandedResourceBuilder setResourceFormatVersion(int resourceFormatVersion) {
            this.mResourceFormatVersion = resourceFormatVersion;
            return this;
        }

        public ExpandedResource build() {
            return new ExpandedResource(this.mRules, this.mMacros, this.mVersion, this.mResourceFormatVersion);
        }
    }

    public static class ExpandedResource {
        private final Map<String, List<ExpandedFunctionCall>> mMacros;
        private final int mResourceFormatVersion;
        private final List<ExpandedRule> mRules;
        private final String mVersion;

        private ExpandedResource(List<ExpandedRule> rules, Map<String, List<ExpandedFunctionCall>> macros, String version, int resourceFormatVersion) {
            this.mRules = Collections.unmodifiableList(rules);
            this.mMacros = Collections.unmodifiableMap(macros);
            this.mVersion = version;
            this.mResourceFormatVersion = resourceFormatVersion;
        }

        public static ExpandedResourceBuilder newBuilder() {
            return new ExpandedResourceBuilder();
        }

        public List<ExpandedRule> getRules() {
            return this.mRules;
        }

        public String getVersion() {
            return this.mVersion;
        }

        public int getResourceFormatVersion() {
            return this.mResourceFormatVersion;
        }

        public List<ExpandedFunctionCall> getMacros(String name) {
            return this.mMacros.get(name);
        }

        public Map<String, List<ExpandedFunctionCall>> getAllMacros() {
            return this.mMacros;
        }

        public String toString() {
            return "Rules: " + getRules() + "  Macros: " + this.mMacros;
        }
    }

    public static ExpandedResource getExpandedResource(Serving.Resource resource) throws InvalidResourceException {
        TypeSystem.Value[] expandedValues = new TypeSystem.Value[resource.value.length];
        for (int i = 0; i < resource.value.length; i++) {
            expandValue(i, resource, expandedValues, new HashSet(0));
        }
        ExpandedResourceBuilder builder = ExpandedResource.newBuilder();
        List<ExpandedFunctionCall> tags = new ArrayList<>();
        for (int i2 = 0; i2 < resource.tag.length; i2++) {
            tags.add(expandFunctionCall(resource.tag[i2], resource, expandedValues, i2));
        }
        List<ExpandedFunctionCall> predicates = new ArrayList<>();
        for (int i3 = 0; i3 < resource.predicate.length; i3++) {
            predicates.add(expandFunctionCall(resource.predicate[i3], resource, expandedValues, i3));
        }
        List<ExpandedFunctionCall> macros = new ArrayList<>();
        for (int i4 = 0; i4 < resource.macro.length; i4++) {
            ExpandedFunctionCall thisMacro = expandFunctionCall(resource.macro[i4], resource, expandedValues, i4);
            builder.addMacro(thisMacro);
            macros.add(thisMacro);
        }
        for (Serving.Rule r : resource.rule) {
            builder.addRule(expandRule(r, tags, macros, predicates, resource));
        }
        builder.setVersion(resource.version);
        builder.setResourceFormatVersion(resource.resourceFormatVersion);
        return builder.build();
    }

    public static TypeSystem.Value newValueBasedOnValue(TypeSystem.Value v) {
        TypeSystem.Value result = new TypeSystem.Value();
        result.type = v.type;
        result.escaping = (int[]) v.escaping.clone();
        if (v.containsReferences) {
            result.containsReferences = v.containsReferences;
        }
        return result;
    }

    private static TypeSystem.Value expandValue(int i, Serving.Resource resource, TypeSystem.Value[] expandedValues, Set<Integer> pendingExpansions) throws InvalidResourceException {
        if (pendingExpansions.contains(Integer.valueOf(i))) {
            logAndThrow("Value cycle detected.  Current value reference: " + i + "." + "  Previous value references: " + pendingExpansions + ".");
        }
        TypeSystem.Value value = (TypeSystem.Value) getWithBoundsCheck((T[]) resource.value, i, "values");
        if (expandedValues[i] != null) {
            return expandedValues[i];
        }
        TypeSystem.Value toAdd = null;
        pendingExpansions.add(Integer.valueOf(i));
        switch (value.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                toAdd = value;
                break;
            case 2:
                Serving.ServingValue servingValue = getServingValue(value);
                toAdd = newValueBasedOnValue(value);
                toAdd.listItem = new TypeSystem.Value[servingValue.listItem.length];
                int[] arr$ = servingValue.listItem;
                int len$ = arr$.length;
                int i$ = 0;
                int index = 0;
                while (i$ < len$) {
                    toAdd.listItem[index] = expandValue(arr$[i$], resource, expandedValues, pendingExpansions);
                    i$++;
                    index++;
                }
                break;
            case 3:
                toAdd = newValueBasedOnValue(value);
                Serving.ServingValue servingValue2 = getServingValue(value);
                if (servingValue2.mapKey.length != servingValue2.mapValue.length) {
                    logAndThrow("Uneven map keys (" + servingValue2.mapKey.length + ") and map values (" + servingValue2.mapValue.length + ")");
                }
                toAdd.mapKey = new TypeSystem.Value[servingValue2.mapKey.length];
                toAdd.mapValue = new TypeSystem.Value[servingValue2.mapKey.length];
                int[] arr$2 = servingValue2.mapKey;
                int len$2 = arr$2.length;
                int i$2 = 0;
                int index2 = 0;
                while (i$2 < len$2) {
                    toAdd.mapKey[index2] = expandValue(arr$2[i$2], resource, expandedValues, pendingExpansions);
                    i$2++;
                    index2++;
                }
                int[] arr$3 = servingValue2.mapValue;
                int len$3 = arr$3.length;
                int i$3 = 0;
                int index3 = 0;
                while (i$3 < len$3) {
                    toAdd.mapValue[index3] = expandValue(arr$3[i$3], resource, expandedValues, pendingExpansions);
                    i$3++;
                    index3++;
                }
                break;
            case 4:
                toAdd = newValueBasedOnValue(value);
                toAdd.macroReference = Types.valueToString(expandValue(getServingValue(value).macroNameReference, resource, expandedValues, pendingExpansions));
                break;
            case 7:
                toAdd = newValueBasedOnValue(value);
                Serving.ServingValue servingValue3 = getServingValue(value);
                toAdd.templateToken = new TypeSystem.Value[servingValue3.templateToken.length];
                int[] arr$4 = servingValue3.templateToken;
                int len$4 = arr$4.length;
                int i$4 = 0;
                int index4 = 0;
                while (i$4 < len$4) {
                    toAdd.templateToken[index4] = expandValue(arr$4[i$4], resource, expandedValues, pendingExpansions);
                    i$4++;
                    index4++;
                }
                break;
        }
        if (toAdd == null) {
            logAndThrow("Invalid value: " + value);
        }
        expandedValues[i] = toAdd;
        pendingExpansions.remove(Integer.valueOf(i));
        return toAdd;
    }

    private static Serving.ServingValue getServingValue(TypeSystem.Value value) throws InvalidResourceException {
        if (((Serving.ServingValue) value.getExtension(Serving.ServingValue.ext)) == null) {
            logAndThrow("Expected a ServingValue and didn't get one. Value is: " + value);
        }
        return (Serving.ServingValue) value.getExtension(Serving.ServingValue.ext);
    }

    private static void logAndThrow(String error) throws InvalidResourceException {
        Log.m175e(error);
        throw new InvalidResourceException(error);
    }

    private static <T> T getWithBoundsCheck(T[] array, int idx, String listName) throws InvalidResourceException {
        if (idx < 0 || idx >= array.length) {
            logAndThrow("Index out of bounds detected: " + idx + " in " + listName);
        }
        return array[idx];
    }

    private static <T> T getWithBoundsCheck(List<T> list, int idx, String listName) throws InvalidResourceException {
        if (idx < 0 || idx >= list.size()) {
            logAndThrow("Index out of bounds detected: " + idx + " in " + listName);
        }
        return list.get(idx);
    }

    private static ExpandedFunctionCall expandFunctionCall(Serving.FunctionCall functionCall, Serving.Resource resource, TypeSystem.Value[] expandedValues, int idx) throws InvalidResourceException {
        ExpandedFunctionCallBuilder builder = ExpandedFunctionCall.newBuilder();
        for (int valueOf : functionCall.property) {
            Serving.Property p = (Serving.Property) getWithBoundsCheck((T[]) resource.property, Integer.valueOf(valueOf).intValue(), "properties");
            String key = (String) getWithBoundsCheck((T[]) resource.key, p.key, "keys");
            TypeSystem.Value value = (TypeSystem.Value) getWithBoundsCheck((T[]) expandedValues, p.value, "values");
            if (Key.PUSH_AFTER_EVALUATE.toString().equals(key)) {
                builder.setPushAfterEvaluate(value);
            } else {
                builder.addProperty(key, value);
            }
        }
        return builder.build();
    }

    private static ExpandedRule expandRule(Serving.Rule rule, List<ExpandedFunctionCall> tags, List<ExpandedFunctionCall> macros, List<ExpandedFunctionCall> predicates, Serving.Resource resource) {
        ExpandedRuleBuilder ruleBuilder = ExpandedRule.newBuilder();
        for (int valueOf : rule.positivePredicate) {
            ruleBuilder.addPositivePredicate(predicates.get(Integer.valueOf(valueOf).intValue()));
        }
        for (int valueOf2 : rule.negativePredicate) {
            ruleBuilder.addNegativePredicate(predicates.get(Integer.valueOf(valueOf2).intValue()));
        }
        for (int valueOf3 : rule.addTag) {
            ruleBuilder.addAddTag(tags.get(Integer.valueOf(valueOf3).intValue()));
        }
        for (int valueOf4 : rule.addTagRuleName) {
            ruleBuilder.addAddTagRuleName(resource.value[Integer.valueOf(valueOf4).intValue()].string);
        }
        for (int valueOf5 : rule.removeTag) {
            ruleBuilder.addRemoveTag(tags.get(Integer.valueOf(valueOf5).intValue()));
        }
        for (int valueOf6 : rule.removeTagRuleName) {
            ruleBuilder.addRemoveTagRuleName(resource.value[Integer.valueOf(valueOf6).intValue()].string);
        }
        for (int valueOf7 : rule.addMacro) {
            ruleBuilder.addAddMacro(macros.get(Integer.valueOf(valueOf7).intValue()));
        }
        for (int valueOf8 : rule.addMacroRuleName) {
            ruleBuilder.addAddMacroRuleName(resource.value[Integer.valueOf(valueOf8).intValue()].string);
        }
        for (int valueOf9 : rule.removeMacro) {
            ruleBuilder.addRemoveMacro(macros.get(Integer.valueOf(valueOf9).intValue()));
        }
        for (int valueOf10 : rule.removeMacroRuleName) {
            ruleBuilder.addRemoveMacroRuleName(resource.value[Integer.valueOf(valueOf10).intValue()].string);
        }
        return ruleBuilder.build();
    }

    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            int numBytes = in.read(buffer);
            if (numBytes != -1) {
                out.write(buffer, 0, numBytes);
            } else {
                return;
            }
        }
    }
}
