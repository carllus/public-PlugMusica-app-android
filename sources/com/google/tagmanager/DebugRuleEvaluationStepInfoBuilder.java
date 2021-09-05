package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug;
import com.google.tagmanager.ResourceUtil;
import java.util.Set;

class DebugRuleEvaluationStepInfoBuilder implements RuleEvaluationStepInfoBuilder {
    private Debug.RuleEvaluationStepInfo ruleEvaluationStepInfo;

    public DebugRuleEvaluationStepInfoBuilder(Debug.RuleEvaluationStepInfo ruleEvaluationStepInfo2) {
        this.ruleEvaluationStepInfo = ruleEvaluationStepInfo2;
    }

    public void setEnabledFunctions(Set<ResourceUtil.ExpandedFunctionCall> enabledFunctions) {
        for (ResourceUtil.ExpandedFunctionCall enabledFunction : enabledFunctions) {
            this.ruleEvaluationStepInfo.enabledFunctions = ArrayUtils.appendToArray(this.ruleEvaluationStepInfo.enabledFunctions, DebugResolvedRuleBuilder.translateExpandedFunctionCall(enabledFunction));
        }
    }

    public ResolvedRuleBuilder createResolvedRuleBuilder() {
        Debug.ResolvedRule rule = new Debug.ResolvedRule();
        this.ruleEvaluationStepInfo.rules = ArrayUtils.appendToArray(this.ruleEvaluationStepInfo.rules, rule);
        return new DebugResolvedRuleBuilder(rule);
    }
}
