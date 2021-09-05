package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug;
import com.google.analytics.midtier.proto.containertag.TypeSystem;

class DebugResolvedFunctionCallBuilder implements ResolvedFunctionCallBuilder {
    private Debug.ResolvedFunctionCall resolvedFunctionCall;

    public DebugResolvedFunctionCallBuilder(Debug.ResolvedFunctionCall functionCall) {
        this.resolvedFunctionCall = functionCall;
    }

    public ResolvedPropertyBuilder createResolvedPropertyBuilder(String key) {
        Debug.ResolvedProperty newProperty = new Debug.ResolvedProperty();
        newProperty.key = key;
        this.resolvedFunctionCall.properties = ArrayUtils.appendToArray(this.resolvedFunctionCall.properties, newProperty);
        return new DebugResolvedPropertyBuilder(newProperty);
    }

    public void setFunctionResult(TypeSystem.Value functionResult) {
        this.resolvedFunctionCall.result = DebugValueBuilder.copyImmutableValue(functionResult);
    }
}
