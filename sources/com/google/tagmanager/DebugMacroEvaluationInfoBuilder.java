package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug;

class DebugMacroEvaluationInfoBuilder implements MacroEvaluationInfoBuilder {
    private Debug.MacroEvaluationInfo macroEvaluationInfo;

    public DebugMacroEvaluationInfoBuilder(Debug.MacroEvaluationInfo macroEvaluationInfo2) {
        this.macroEvaluationInfo = macroEvaluationInfo2;
    }

    public ResolvedFunctionCallBuilder createResult() {
        this.macroEvaluationInfo.result = new Debug.ResolvedFunctionCall();
        return new DebugResolvedFunctionCallBuilder(this.macroEvaluationInfo.result);
    }

    public RuleEvaluationStepInfoBuilder createRulesEvaluation() {
        this.macroEvaluationInfo.rulesEvaluation = new Debug.RuleEvaluationStepInfo();
        return new DebugRuleEvaluationStepInfoBuilder(this.macroEvaluationInfo.rulesEvaluation);
    }
}
