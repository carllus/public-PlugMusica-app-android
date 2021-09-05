package com.google.tagmanager;

import com.google.analytics.containertag.proto.Debug;
import com.google.analytics.midtier.proto.containertag.TypeSystem;
import com.google.tagmanager.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.tagmanager.protobuf.nano.MessageNano;

class DebugValueBuilder implements ValueBuilder {
    private TypeSystem.Value value;

    public DebugValueBuilder(TypeSystem.Value value2) {
        this.value = value2;
    }

    private void validateType(int expected, int actual, String message) {
        if (expected != actual) {
            throw new TypeMismatchException(message, actual);
        }
    }

    public static TypeSystem.Value copyImmutableValue(TypeSystem.Value value2) {
        TypeSystem.Value result = new TypeSystem.Value();
        try {
            MessageNano.mergeFrom(result, MessageNano.toByteArray(value2));
        } catch (InvalidProtocolBufferNanoException e) {
            Log.m175e("Failed to copy runtime value into debug value");
        }
        return result;
    }

    public ValueBuilder getListItem(int index) {
        validateType(2, this.value.type, "add new list item");
        return new DebugValueBuilder(this.value.listItem[index]);
    }

    public ValueBuilder getMapKey(int index) {
        validateType(3, this.value.type, "add new map key");
        return new DebugValueBuilder(this.value.mapKey[index]);
    }

    public ValueBuilder getMapValue(int index) {
        validateType(3, this.value.type, "add new map value");
        return new DebugValueBuilder(this.value.mapValue[index]);
    }

    public ValueBuilder getTemplateToken(int index) {
        validateType(7, this.value.type, "add template token");
        return new DebugValueBuilder(this.value.templateToken[index]);
    }

    public MacroEvaluationInfoBuilder createValueMacroEvaluationInfoExtension() {
        validateType(4, this.value.type, "set macro evaluation extension");
        Debug.MacroEvaluationInfo info = new Debug.MacroEvaluationInfo();
        this.value.setExtension(Debug.MacroEvaluationInfo.macro, info);
        return new DebugMacroEvaluationInfoBuilder(info);
    }

    private static class TypeMismatchException extends IllegalStateException {
        public TypeMismatchException(String operation, int t) {
            super("Attempted operation: " + operation + " on object of type: " + t);
        }
    }
}
