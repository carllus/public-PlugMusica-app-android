package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug;
import com.google.analytics.midtier.proto.containertag.TypeSystem;

class DebugResolvedPropertyBuilder implements ResolvedPropertyBuilder {
    private Debug.ResolvedProperty resolvedProperty;

    public DebugResolvedPropertyBuilder(Debug.ResolvedProperty resolvedProperty2) {
        this.resolvedProperty = resolvedProperty2;
    }

    public ValueBuilder createPropertyValueBuilder(TypeSystem.Value propertyValue) {
        TypeSystem.Value val = DebugValueBuilder.copyImmutableValue(propertyValue);
        this.resolvedProperty.value = val;
        return new DebugValueBuilder(val);
    }
}
