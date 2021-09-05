package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug;

class DebugDataLayerEventEvaluationInfoBuilder implements DataLayerEventEvaluationInfoBuilder {
    private Debug.DataLayerEventEvaluationInfo dataLayerEvent;

    public DebugDataLayerEventEvaluationInfoBuilder(Debug.DataLayerEventEvaluationInfo dataLayerEvent2) {
        this.dataLayerEvent = dataLayerEvent2;
    }

    public ResolvedFunctionCallBuilder createAndAddResult() {
        Debug.ResolvedFunctionCall result = new Debug.ResolvedFunctionCall();
        this.dataLayerEvent.results = ArrayUtils.appendToArray(this.dataLayerEvent.results, result);
        return new DebugResolvedFunctionCallBuilder(result);
    }

    public RuleEvaluationStepInfoBuilder createRulesEvaluation() {
        this.dataLayerEvent.rulesEvaluation = new Debug.RuleEvaluationStepInfo();
        return new DebugRuleEvaluationStepInfoBuilder(this.dataLayerEvent.rulesEvaluation);
    }
}
