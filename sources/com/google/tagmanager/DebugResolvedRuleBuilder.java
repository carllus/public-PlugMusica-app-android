package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import com.google.tagmanager.ResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DebugResolvedRuleBuilder implements ResolvedRuleBuilder {
    ResolvedFunctionCallTranslatorList addMacrosHolder = new DebugResolvedFunctionCallListTranslator(1);
    ResolvedFunctionCallTranslatorList addTagsHolder = new DebugResolvedFunctionCallListTranslator(3);
    ResolvedFunctionCallTranslatorList removeMacrosHolder = new DebugResolvedFunctionCallListTranslator(2);
    ResolvedFunctionCallTranslatorList removeTagsHolder = new DebugResolvedFunctionCallListTranslator(4);
    Debug.ResolvedRule resolvedRule;

    public DebugResolvedRuleBuilder(Debug.ResolvedRule rule) {
        this.resolvedRule = rule;
    }

    public ResolvedFunctionCallBuilder createNegativePredicate() {
        Debug.ResolvedFunctionCall predicate = new Debug.ResolvedFunctionCall();
        this.resolvedRule.negativePredicates = ArrayUtils.appendToArray(this.resolvedRule.negativePredicates, predicate);
        return new DebugResolvedFunctionCallBuilder(predicate);
    }

    public ResolvedFunctionCallBuilder createPositivePredicate() {
        Debug.ResolvedFunctionCall predicate = new Debug.ResolvedFunctionCall();
        this.resolvedRule.positivePredicates = ArrayUtils.appendToArray(this.resolvedRule.positivePredicates, predicate);
        return new DebugResolvedFunctionCallBuilder(predicate);
    }

    public ResolvedFunctionCallTranslatorList getAddedMacroFunctions() {
        return this.addMacrosHolder;
    }

    public ResolvedFunctionCallTranslatorList getRemovedMacroFunctions() {
        return this.removeMacrosHolder;
    }

    public ResolvedFunctionCallTranslatorList getAddedTagFunctions() {
        return this.addTagsHolder;
    }

    public ResolvedFunctionCallTranslatorList getRemovedTagFunctions() {
        return this.removeTagsHolder;
    }

    public void setValue(TypeSystem.Value result) {
        this.resolvedRule.result = DebugValueBuilder.copyImmutableValue(result);
    }

    public static Debug.ResolvedFunctionCall translateExpandedFunctionCall(ResourceUtil.ExpandedFunctionCall f) {
        Debug.ResolvedFunctionCall result = new Debug.ResolvedFunctionCall();
        for (Map.Entry<String, TypeSystem.Value> originalParam : f.getProperties().entrySet()) {
            Debug.ResolvedProperty prop = new Debug.ResolvedProperty();
            prop.key = originalParam.getKey();
            prop.value = DebugValueBuilder.copyImmutableValue(originalParam.getValue());
            result.properties = ArrayUtils.appendToArray(result.properties, prop);
        }
        return result;
    }

    class DebugResolvedFunctionCallListTranslator implements ResolvedFunctionCallTranslatorList {
        private final int type;

        class Type {
            static final int ADD_MACROS = 1;
            static final int ADD_TAGS = 3;
            static final int REMOVE_MACROS = 2;
            static final int REMOVE_TAGS = 4;

            Type() {
            }
        }

        DebugResolvedFunctionCallListTranslator(int type2) {
            this.type = type2;
        }

        public void translateAndAddAll(List<ResourceUtil.ExpandedFunctionCall> functions, List<String> ruleNames) {
            List<Debug.ResolvedFunctionCall> translatedList = new ArrayList<>(functions.size());
            for (int i = 0; i < functions.size(); i++) {
                translatedList.add(DebugResolvedRuleBuilder.translateExpandedFunctionCall(functions.get(i)));
                if (i < ruleNames.size()) {
                    translatedList.get(i).associatedRuleName = ruleNames.get(i);
                } else {
                    translatedList.get(i).associatedRuleName = "Unknown";
                }
            }
            Debug.ResolvedFunctionCall[] translated = (Debug.ResolvedFunctionCall[]) translatedList.toArray(new Debug.ResolvedFunctionCall[0]);
            switch (this.type) {
                case 1:
                    DebugResolvedRuleBuilder.this.resolvedRule.addMacros = translated;
                    return;
                case 2:
                    DebugResolvedRuleBuilder.this.resolvedRule.removeMacros = translated;
                    return;
                case 3:
                    DebugResolvedRuleBuilder.this.resolvedRule.addTags = translated;
                    return;
                case 4:
                    DebugResolvedRuleBuilder.this.resolvedRule.removeTags = translated;
                    return;
                default:
                    Log.m175e("unknown type in translateAndAddAll: " + this.type);
                    return;
            }
        }
    }
}
