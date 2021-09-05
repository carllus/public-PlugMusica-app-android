package com.google.analytics.containertag.proto;

import com.google.analytics.midtier.proto.containertag.TypeSystem;
import com.google.tagmanager.protobuf.nano.CodedInputByteBufferNano;
import com.google.tagmanager.protobuf.nano.CodedOutputByteBufferNano;
import com.google.tagmanager.protobuf.nano.ExtendableMessageNano;
import com.google.tagmanager.protobuf.nano.Extension;
import com.google.tagmanager.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.tagmanager.protobuf.nano.MessageNano;
import com.google.tagmanager.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public interface Debug {

    public static final class DebugEvents extends ExtendableMessageNano {
        public static final DebugEvents[] EMPTY_ARRAY = new DebugEvents[0];
        public EventInfo[] event = EventInfo.EMPTY_ARRAY;

        public final DebugEvents clear() {
            this.event = EventInfo.EMPTY_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DebugEvents)) {
                return false;
            }
            DebugEvents other = (DebugEvents) o;
            if (Arrays.equals(this.event, other.event)) {
                if (this.unknownFieldData == null) {
                    if (other.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(other.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int result = 17;
            if (this.event == null) {
                result = 17 * 31;
            } else {
                for (int i2 = 0; i2 < this.event.length; i2++) {
                    result = (result * 31) + (this.event[i2] == null ? 0 : this.event[i2].hashCode());
                }
            }
            int i3 = result * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return i3 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.event != null) {
                for (EventInfo element : this.event) {
                    output.writeMessage(1, element);
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.event != null) {
                for (EventInfo element : this.event) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public DebugEvents mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int i;
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.event == null) {
                            i = 0;
                        } else {
                            i = this.event.length;
                        }
                        EventInfo[] newArray = new EventInfo[(i + arrayLength)];
                        if (this.event != null) {
                            System.arraycopy(this.event, 0, newArray, 0, i);
                        }
                        this.event = newArray;
                        while (i < this.event.length - 1) {
                            this.event[i] = new EventInfo();
                            input.readMessage(this.event[i]);
                            input.readTag();
                            i++;
                        }
                        this.event[i] = new EventInfo();
                        input.readMessage(this.event[i]);
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public static DebugEvents parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DebugEvents) MessageNano.mergeFrom(new DebugEvents(), data);
        }

        public static DebugEvents parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DebugEvents().mergeFrom(input);
        }
    }

    public static final class EventInfo extends ExtendableMessageNano {
        public static final EventInfo[] EMPTY_ARRAY = new EventInfo[0];
        public String containerId = "";
        public String containerVersion = "";
        public DataLayerEventEvaluationInfo dataLayerEventResult = null;
        public int eventType = 1;
        public String key = "";
        public MacroEvaluationInfo macroResult = null;

        public interface EventType {
            public static final int DATA_LAYER_EVENT = 1;
            public static final int MACRO_REFERENCE = 2;
        }

        public final EventInfo clear() {
            this.eventType = 1;
            this.containerVersion = "";
            this.containerId = "";
            this.key = "";
            this.macroResult = null;
            this.dataLayerEventResult = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof EventInfo)) {
                return false;
            }
            EventInfo other = (EventInfo) o;
            if (this.eventType == other.eventType && (this.containerVersion != null ? this.containerVersion.equals(other.containerVersion) : other.containerVersion == null) && (this.containerId != null ? this.containerId.equals(other.containerId) : other.containerId == null) && (this.key != null ? this.key.equals(other.key) : other.key == null) && (this.macroResult != null ? this.macroResult.equals(other.macroResult) : other.macroResult == null) && (this.dataLayerEventResult != null ? this.dataLayerEventResult.equals(other.dataLayerEventResult) : other.dataLayerEventResult == null)) {
                if (this.unknownFieldData == null) {
                    if (other.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(other.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (((((((((((this.eventType + 527) * 31) + (this.containerVersion == null ? 0 : this.containerVersion.hashCode())) * 31) + (this.containerId == null ? 0 : this.containerId.hashCode())) * 31) + (this.key == null ? 0 : this.key.hashCode())) * 31) + (this.macroResult == null ? 0 : this.macroResult.hashCode())) * 31) + (this.dataLayerEventResult == null ? 0 : this.dataLayerEventResult.hashCode())) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.eventType != 1) {
                output.writeInt32(1, this.eventType);
            }
            if (!this.containerVersion.equals("")) {
                output.writeString(2, this.containerVersion);
            }
            if (!this.containerId.equals("")) {
                output.writeString(3, this.containerId);
            }
            if (!this.key.equals("")) {
                output.writeString(4, this.key);
            }
            if (this.macroResult != null) {
                output.writeMessage(6, this.macroResult);
            }
            if (this.dataLayerEventResult != null) {
                output.writeMessage(7, this.dataLayerEventResult);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.eventType != 1) {
                size = 0 + CodedOutputByteBufferNano.computeInt32Size(1, this.eventType);
            }
            if (!this.containerVersion.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.containerVersion);
            }
            if (!this.containerId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.containerId);
            }
            if (!this.key.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.key);
            }
            if (this.macroResult != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.macroResult);
            }
            if (this.dataLayerEventResult != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.dataLayerEventResult);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public EventInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        int temp = input.readInt32();
                        if (temp != 1 && temp != 2) {
                            this.eventType = 1;
                            break;
                        } else {
                            this.eventType = temp;
                            continue;
                        }
                        break;
                    case 18:
                        this.containerVersion = input.readString();
                        continue;
                    case 26:
                        this.containerId = input.readString();
                        continue;
                    case 34:
                        this.key = input.readString();
                        continue;
                    case 50:
                        this.macroResult = new MacroEvaluationInfo();
                        input.readMessage(this.macroResult);
                        continue;
                    case 58:
                        this.dataLayerEventResult = new DataLayerEventEvaluationInfo();
                        input.readMessage(this.dataLayerEventResult);
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public static EventInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (EventInfo) MessageNano.mergeFrom(new EventInfo(), data);
        }

        public static EventInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new EventInfo().mergeFrom(input);
        }
    }

    public static final class ResolvedRule extends ExtendableMessageNano {
        public static final ResolvedRule[] EMPTY_ARRAY = new ResolvedRule[0];
        public ResolvedFunctionCall[] addMacros = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedFunctionCall[] addTags = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedFunctionCall[] negativePredicates = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedFunctionCall[] positivePredicates = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedFunctionCall[] removeMacros = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedFunctionCall[] removeTags = ResolvedFunctionCall.EMPTY_ARRAY;
        public TypeSystem.Value result = null;

        public final ResolvedRule clear() {
            this.positivePredicates = ResolvedFunctionCall.EMPTY_ARRAY;
            this.negativePredicates = ResolvedFunctionCall.EMPTY_ARRAY;
            this.addTags = ResolvedFunctionCall.EMPTY_ARRAY;
            this.removeTags = ResolvedFunctionCall.EMPTY_ARRAY;
            this.addMacros = ResolvedFunctionCall.EMPTY_ARRAY;
            this.removeMacros = ResolvedFunctionCall.EMPTY_ARRAY;
            this.result = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ResolvedRule)) {
                return false;
            }
            ResolvedRule other = (ResolvedRule) o;
            if (Arrays.equals(this.positivePredicates, other.positivePredicates) && Arrays.equals(this.negativePredicates, other.negativePredicates) && Arrays.equals(this.addTags, other.addTags) && Arrays.equals(this.removeTags, other.removeTags) && Arrays.equals(this.addMacros, other.addMacros) && Arrays.equals(this.removeMacros, other.removeMacros) && (this.result != null ? this.result.equals(other.result) : other.result == null)) {
                if (this.unknownFieldData == null) {
                    if (other.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(other.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int result2 = 17;
            if (this.positivePredicates == null) {
                result2 = 17 * 31;
            } else {
                for (int i2 = 0; i2 < this.positivePredicates.length; i2++) {
                    result2 = (result2 * 31) + (this.positivePredicates[i2] == null ? 0 : this.positivePredicates[i2].hashCode());
                }
            }
            if (this.negativePredicates == null) {
                result2 *= 31;
            } else {
                for (int i3 = 0; i3 < this.negativePredicates.length; i3++) {
                    result2 = (result2 * 31) + (this.negativePredicates[i3] == null ? 0 : this.negativePredicates[i3].hashCode());
                }
            }
            if (this.addTags == null) {
                result2 *= 31;
            } else {
                for (int i4 = 0; i4 < this.addTags.length; i4++) {
                    result2 = (result2 * 31) + (this.addTags[i4] == null ? 0 : this.addTags[i4].hashCode());
                }
            }
            if (this.removeTags == null) {
                result2 *= 31;
            } else {
                for (int i5 = 0; i5 < this.removeTags.length; i5++) {
                    result2 = (result2 * 31) + (this.removeTags[i5] == null ? 0 : this.removeTags[i5].hashCode());
                }
            }
            if (this.addMacros == null) {
                result2 *= 31;
            } else {
                for (int i6 = 0; i6 < this.addMacros.length; i6++) {
                    result2 = (result2 * 31) + (this.addMacros[i6] == null ? 0 : this.addMacros[i6].hashCode());
                }
            }
            if (this.removeMacros == null) {
                result2 *= 31;
            } else {
                for (int i7 = 0; i7 < this.removeMacros.length; i7++) {
                    result2 = (result2 * 31) + (this.removeMacros[i7] == null ? 0 : this.removeMacros[i7].hashCode());
                }
            }
            int hashCode = ((result2 * 31) + (this.result == null ? 0 : this.result.hashCode())) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.positivePredicates != null) {
                for (ResolvedFunctionCall element : this.positivePredicates) {
                    output.writeMessage(1, element);
                }
            }
            if (this.negativePredicates != null) {
                for (ResolvedFunctionCall element2 : this.negativePredicates) {
                    output.writeMessage(2, element2);
                }
            }
            if (this.addTags != null) {
                for (ResolvedFunctionCall element3 : this.addTags) {
                    output.writeMessage(3, element3);
                }
            }
            if (this.removeTags != null) {
                for (ResolvedFunctionCall element4 : this.removeTags) {
                    output.writeMessage(4, element4);
                }
            }
            if (this.addMacros != null) {
                for (ResolvedFunctionCall element5 : this.addMacros) {
                    output.writeMessage(5, element5);
                }
            }
            if (this.removeMacros != null) {
                for (ResolvedFunctionCall element6 : this.removeMacros) {
                    output.writeMessage(6, element6);
                }
            }
            if (this.result != null) {
                output.writeMessage(7, this.result);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.positivePredicates != null) {
                for (ResolvedFunctionCall element : this.positivePredicates) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
            if (this.negativePredicates != null) {
                for (ResolvedFunctionCall element2 : this.negativePredicates) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, element2);
                }
            }
            if (this.addTags != null) {
                for (ResolvedFunctionCall element3 : this.addTags) {
                    size += CodedOutputByteBufferNano.computeMessageSize(3, element3);
                }
            }
            if (this.removeTags != null) {
                for (ResolvedFunctionCall element4 : this.removeTags) {
                    size += CodedOutputByteBufferNano.computeMessageSize(4, element4);
                }
            }
            if (this.addMacros != null) {
                for (ResolvedFunctionCall element5 : this.addMacros) {
                    size += CodedOutputByteBufferNano.computeMessageSize(5, element5);
                }
            }
            if (this.removeMacros != null) {
                for (ResolvedFunctionCall element6 : this.removeMacros) {
                    size += CodedOutputByteBufferNano.computeMessageSize(6, element6);
                }
            }
            if (this.result != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.result);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public ResolvedRule mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.positivePredicates == null) {
                            i6 = 0;
                        } else {
                            i6 = this.positivePredicates.length;
                        }
                        ResolvedFunctionCall[] newArray = new ResolvedFunctionCall[(i6 + arrayLength)];
                        if (this.positivePredicates != null) {
                            System.arraycopy(this.positivePredicates, 0, newArray, 0, i6);
                        }
                        this.positivePredicates = newArray;
                        while (i6 < this.positivePredicates.length - 1) {
                            this.positivePredicates[i6] = new ResolvedFunctionCall();
                            input.readMessage(this.positivePredicates[i6]);
                            input.readTag();
                            i6++;
                        }
                        this.positivePredicates[i6] = new ResolvedFunctionCall();
                        input.readMessage(this.positivePredicates[i6]);
                        continue;
                    case 18:
                        int arrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.negativePredicates == null) {
                            i5 = 0;
                        } else {
                            i5 = this.negativePredicates.length;
                        }
                        ResolvedFunctionCall[] newArray2 = new ResolvedFunctionCall[(i5 + arrayLength2)];
                        if (this.negativePredicates != null) {
                            System.arraycopy(this.negativePredicates, 0, newArray2, 0, i5);
                        }
                        this.negativePredicates = newArray2;
                        while (i5 < this.negativePredicates.length - 1) {
                            this.negativePredicates[i5] = new ResolvedFunctionCall();
                            input.readMessage(this.negativePredicates[i5]);
                            input.readTag();
                            i5++;
                        }
                        this.negativePredicates[i5] = new ResolvedFunctionCall();
                        input.readMessage(this.negativePredicates[i5]);
                        continue;
                    case 26:
                        int arrayLength3 = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.addTags == null) {
                            i4 = 0;
                        } else {
                            i4 = this.addTags.length;
                        }
                        ResolvedFunctionCall[] newArray3 = new ResolvedFunctionCall[(i4 + arrayLength3)];
                        if (this.addTags != null) {
                            System.arraycopy(this.addTags, 0, newArray3, 0, i4);
                        }
                        this.addTags = newArray3;
                        while (i4 < this.addTags.length - 1) {
                            this.addTags[i4] = new ResolvedFunctionCall();
                            input.readMessage(this.addTags[i4]);
                            input.readTag();
                            i4++;
                        }
                        this.addTags[i4] = new ResolvedFunctionCall();
                        input.readMessage(this.addTags[i4]);
                        continue;
                    case 34:
                        int arrayLength4 = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.removeTags == null) {
                            i3 = 0;
                        } else {
                            i3 = this.removeTags.length;
                        }
                        ResolvedFunctionCall[] newArray4 = new ResolvedFunctionCall[(i3 + arrayLength4)];
                        if (this.removeTags != null) {
                            System.arraycopy(this.removeTags, 0, newArray4, 0, i3);
                        }
                        this.removeTags = newArray4;
                        while (i3 < this.removeTags.length - 1) {
                            this.removeTags[i3] = new ResolvedFunctionCall();
                            input.readMessage(this.removeTags[i3]);
                            input.readTag();
                            i3++;
                        }
                        this.removeTags[i3] = new ResolvedFunctionCall();
                        input.readMessage(this.removeTags[i3]);
                        continue;
                    case 42:
                        int arrayLength5 = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.addMacros == null) {
                            i2 = 0;
                        } else {
                            i2 = this.addMacros.length;
                        }
                        ResolvedFunctionCall[] newArray5 = new ResolvedFunctionCall[(i2 + arrayLength5)];
                        if (this.addMacros != null) {
                            System.arraycopy(this.addMacros, 0, newArray5, 0, i2);
                        }
                        this.addMacros = newArray5;
                        while (i2 < this.addMacros.length - 1) {
                            this.addMacros[i2] = new ResolvedFunctionCall();
                            input.readMessage(this.addMacros[i2]);
                            input.readTag();
                            i2++;
                        }
                        this.addMacros[i2] = new ResolvedFunctionCall();
                        input.readMessage(this.addMacros[i2]);
                        continue;
                    case 50:
                        int arrayLength6 = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.removeMacros == null) {
                            i = 0;
                        } else {
                            i = this.removeMacros.length;
                        }
                        ResolvedFunctionCall[] newArray6 = new ResolvedFunctionCall[(i + arrayLength6)];
                        if (this.removeMacros != null) {
                            System.arraycopy(this.removeMacros, 0, newArray6, 0, i);
                        }
                        this.removeMacros = newArray6;
                        while (i < this.removeMacros.length - 1) {
                            this.removeMacros[i] = new ResolvedFunctionCall();
                            input.readMessage(this.removeMacros[i]);
                            input.readTag();
                            i++;
                        }
                        this.removeMacros[i] = new ResolvedFunctionCall();
                        input.readMessage(this.removeMacros[i]);
                        continue;
                    case 58:
                        this.result = new TypeSystem.Value();
                        input.readMessage(this.result);
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public static ResolvedRule parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ResolvedRule) MessageNano.mergeFrom(new ResolvedRule(), data);
        }

        public static ResolvedRule parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ResolvedRule().mergeFrom(input);
        }
    }

    public static final class ResolvedFunctionCall extends ExtendableMessageNano {
        public static final ResolvedFunctionCall[] EMPTY_ARRAY = new ResolvedFunctionCall[0];
        public String associatedRuleName = "";
        public ResolvedProperty[] properties = ResolvedProperty.EMPTY_ARRAY;
        public TypeSystem.Value result = null;

        public final ResolvedFunctionCall clear() {
            this.properties = ResolvedProperty.EMPTY_ARRAY;
            this.result = null;
            this.associatedRuleName = "";
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ResolvedFunctionCall)) {
                return false;
            }
            ResolvedFunctionCall other = (ResolvedFunctionCall) o;
            if (Arrays.equals(this.properties, other.properties) && (this.result != null ? this.result.equals(other.result) : other.result == null) && (this.associatedRuleName != null ? this.associatedRuleName.equals(other.associatedRuleName) : other.associatedRuleName == null)) {
                if (this.unknownFieldData == null) {
                    if (other.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(other.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int result2 = 17;
            if (this.properties == null) {
                result2 = 17 * 31;
            } else {
                for (int i2 = 0; i2 < this.properties.length; i2++) {
                    result2 = (result2 * 31) + (this.properties[i2] == null ? 0 : this.properties[i2].hashCode());
                }
            }
            int hashCode = ((((result2 * 31) + (this.result == null ? 0 : this.result.hashCode())) * 31) + (this.associatedRuleName == null ? 0 : this.associatedRuleName.hashCode())) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.properties != null) {
                for (ResolvedProperty element : this.properties) {
                    output.writeMessage(1, element);
                }
            }
            if (this.result != null) {
                output.writeMessage(2, this.result);
            }
            if (!this.associatedRuleName.equals("")) {
                output.writeString(3, this.associatedRuleName);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.properties != null) {
                for (ResolvedProperty element : this.properties) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
            if (this.result != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.result);
            }
            if (!this.associatedRuleName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.associatedRuleName);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public ResolvedFunctionCall mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int i;
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.properties == null) {
                            i = 0;
                        } else {
                            i = this.properties.length;
                        }
                        ResolvedProperty[] newArray = new ResolvedProperty[(i + arrayLength)];
                        if (this.properties != null) {
                            System.arraycopy(this.properties, 0, newArray, 0, i);
                        }
                        this.properties = newArray;
                        while (i < this.properties.length - 1) {
                            this.properties[i] = new ResolvedProperty();
                            input.readMessage(this.properties[i]);
                            input.readTag();
                            i++;
                        }
                        this.properties[i] = new ResolvedProperty();
                        input.readMessage(this.properties[i]);
                        continue;
                    case 18:
                        this.result = new TypeSystem.Value();
                        input.readMessage(this.result);
                        continue;
                    case 26:
                        this.associatedRuleName = input.readString();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public static ResolvedFunctionCall parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ResolvedFunctionCall) MessageNano.mergeFrom(new ResolvedFunctionCall(), data);
        }

        public static ResolvedFunctionCall parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ResolvedFunctionCall().mergeFrom(input);
        }
    }

    public static final class RuleEvaluationStepInfo extends ExtendableMessageNano {
        public static final RuleEvaluationStepInfo[] EMPTY_ARRAY = new RuleEvaluationStepInfo[0];
        public ResolvedFunctionCall[] enabledFunctions = ResolvedFunctionCall.EMPTY_ARRAY;
        public ResolvedRule[] rules = ResolvedRule.EMPTY_ARRAY;

        public final RuleEvaluationStepInfo clear() {
            this.rules = ResolvedRule.EMPTY_ARRAY;
            this.enabledFunctions = ResolvedFunctionCall.EMPTY_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RuleEvaluationStepInfo)) {
                return false;
            }
            RuleEvaluationStepInfo other = (RuleEvaluationStepInfo) o;
            if (Arrays.equals(this.rules, other.rules) && Arrays.equals(this.enabledFunctions, other.enabledFunctions)) {
                if (this.unknownFieldData == null) {
                    if (other.unknownFieldData == null) {
                        return true;
                    }
                } else if (this.unknownFieldData.equals(other.unknownFieldData)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int result = 17;
            if (this.rules == null) {
                result = 17 * 31;
            } else {
                for (int i2 = 0; i2 < this.rules.length; i2++) {
                    result = (result * 31) + (this.rules[i2] == null ? 0 : this.rules[i2].hashCode());
                }
            }
            if (this.enabledFunctions == null) {
                result *= 31;
            } else {
                for (int i3 = 0; i3 < this.enabledFunctions.length; i3++) {
                    result = (result * 31) + (this.enabledFunctions[i3] == null ? 0 : this.enabledFunctions[i3].hashCode());
                }
            }
            int i4 = result * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return i4 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.rules != null) {
                for (ResolvedRule element : this.rules) {
                    output.writeMessage(1, element);
                }
            }
            if (this.enabledFunctions != null) {
                for (ResolvedFunctionCall element2 : this.enabledFunctions) {
                    output.writeMessage(2, element2);
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.rules != null) {
                for (ResolvedRule element : this.rules) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
            if (this.enabledFunctions != null) {
                for (ResolvedFunctionCall element2 : this.enabledFunctions) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, element2);
                }
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public RuleEvaluationStepInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int i;
            int i2;
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.rules == null) {
                            i2 = 0;
                        } else {
                            i2 = this.rules.length;
                        }
                        ResolvedRule[] newArray = new ResolvedRule[(i2 + arrayLength)];
                        if (this.rules != null) {
                            System.arraycopy(this.rules, 0, newArray, 0, i2);
                        }
                        this.rules = newArray;
                        while (i2 < this.rules.length - 1) {
                            this.rules[i2] = new ResolvedRule();
                            input.readMessage(this.rules[i2]);
                            input.readTag();
                            i2++;
                        }
                        this.rules[i2] = new ResolvedRule();
                        input.readMessage(this.rules[i2]);
                        continue;
                    case 18:
                        int arrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.enabledFunctions == null) {
                            i = 0;
                        } else {
                            i = this.enabledFunctions.length;
                        }
                        ResolvedFunctionCall[] newArray2 = new ResolvedFunctionCall[(i + arrayLength2)];
                        if (this.enabledFunctions != null) {
                            System.arraycopy(this.enabledFunctions, 0, newArray2, 0, i);
                        }
                        this.enabledFunctions = newArray2;
                        while (i < this.enabledFunctions.length - 1) {
                            this.enabledFunctions[i] = new ResolvedFunctionCall();
                            input.readMessage(this.enabledFunctions[i]);
                            input.readTag();
                            i++;
                        }
                        this.enabledFunctions[i] = new ResolvedFunctionCall();
                        input.readMessage(this.enabledFunctions[i]);
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public static RuleEvaluationStepInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RuleEvaluationStepInfo) MessageNano.mergeFrom(new RuleEvaluationStepInfo(), data);
        }

        public static RuleEvaluationStepInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RuleEvaluationStepInfo().mergeFrom(input);
        }
    }

    public static final class DataLayerEventEvaluationInfo extends ExtendableMessageNano {
        public static final DataLayerEventEvaluationInfo[] EMPTY_ARRAY = new DataLayerEventEvaluationInfo[0];
        public ResolvedFunctionCall[] results = ResolvedFunctionCall.EMPTY_ARRAY;
        public RuleEvaluationStepInfo rulesEvaluation = null;

        public final DataLayerEventEvaluationInfo clear() {
            this.rulesEvaluation = null;
            this.results = ResolvedFunctionCall.EMPTY_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DataLayerEventEvaluationInfo)) {
                return false;
            }
            DataLayerEventEvaluationInfo other = (DataLayerEventEvaluationInfo) o;
            if (this.rulesEvaluation != null ? this.rulesEvaluation.equals(other.rulesEvaluation) : other.rulesEvaluation == null) {
                if (Arrays.equals(this.results, other.results)) {
                    if (this.unknownFieldData == null) {
                        if (other.unknownFieldData == null) {
                            return true;
                        }
                    } else if (this.unknownFieldData.equals(other.unknownFieldData)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int result = (this.rulesEvaluation == null ? 0 : this.rulesEvaluation.hashCode()) + 527;
            if (this.results == null) {
                result *= 31;
            } else {
                for (int i2 = 0; i2 < this.results.length; i2++) {
                    result = (result * 31) + (this.results[i2] == null ? 0 : this.results[i2].hashCode());
                }
            }
            int i3 = result * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return i3 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.rulesEvaluation != null) {
                output.writeMessage(1, this.rulesEvaluation);
            }
            if (this.results != null) {
                for (ResolvedFunctionCall element : this.results) {
                    output.writeMessage(2, element);
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.rulesEvaluation != null) {
                size = 0 + CodedOutputByteBufferNano.computeMessageSize(1, this.rulesEvaluation);
            }
            if (this.results != null) {
                for (ResolvedFunctionCall element : this.results) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                }
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public DataLayerEventEvaluationInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int i;
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        this.rulesEvaluation = new RuleEvaluationStepInfo();
                        input.readMessage(this.rulesEvaluation);
                        continue;
                    case 18:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.results == null) {
                            i = 0;
                        } else {
                            i = this.results.length;
                        }
                        ResolvedFunctionCall[] newArray = new ResolvedFunctionCall[(i + arrayLength)];
                        if (this.results != null) {
                            System.arraycopy(this.results, 0, newArray, 0, i);
                        }
                        this.results = newArray;
                        while (i < this.results.length - 1) {
                            this.results[i] = new ResolvedFunctionCall();
                            input.readMessage(this.results[i]);
                            input.readTag();
                            i++;
                        }
                        this.results[i] = new ResolvedFunctionCall();
                        input.readMessage(this.results[i]);
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public static DataLayerEventEvaluationInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DataLayerEventEvaluationInfo) MessageNano.mergeFrom(new DataLayerEventEvaluationInfo(), data);
        }

        public static DataLayerEventEvaluationInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DataLayerEventEvaluationInfo().mergeFrom(input);
        }
    }

    public static final class MacroEvaluationInfo extends ExtendableMessageNano {
        public static final MacroEvaluationInfo[] EMPTY_ARRAY = new MacroEvaluationInfo[0];
        public static final Extension<MacroEvaluationInfo> macro = Extension.create(47497405, new Extension.TypeLiteral<MacroEvaluationInfo>() {
        });
        public ResolvedFunctionCall result = null;
        public RuleEvaluationStepInfo rulesEvaluation = null;

        public final MacroEvaluationInfo clear() {
            this.rulesEvaluation = null;
            this.result = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MacroEvaluationInfo)) {
                return false;
            }
            MacroEvaluationInfo other = (MacroEvaluationInfo) o;
            if (this.rulesEvaluation != null ? this.rulesEvaluation.equals(other.rulesEvaluation) : other.rulesEvaluation == null) {
                if (this.result != null ? this.result.equals(other.result) : other.result == null) {
                    if (this.unknownFieldData == null) {
                        if (other.unknownFieldData == null) {
                            return true;
                        }
                    } else if (this.unknownFieldData.equals(other.unknownFieldData)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.rulesEvaluation == null ? 0 : this.rulesEvaluation.hashCode()) + 527) * 31) + (this.result == null ? 0 : this.result.hashCode())) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.rulesEvaluation != null) {
                output.writeMessage(1, this.rulesEvaluation);
            }
            if (this.result != null) {
                output.writeMessage(3, this.result);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.rulesEvaluation != null) {
                size = 0 + CodedOutputByteBufferNano.computeMessageSize(1, this.rulesEvaluation);
            }
            if (this.result != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.result);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public MacroEvaluationInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        this.rulesEvaluation = new RuleEvaluationStepInfo();
                        input.readMessage(this.rulesEvaluation);
                        continue;
                    case 26:
                        this.result = new ResolvedFunctionCall();
                        input.readMessage(this.result);
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public static MacroEvaluationInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (MacroEvaluationInfo) MessageNano.mergeFrom(new MacroEvaluationInfo(), data);
        }

        public static MacroEvaluationInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new MacroEvaluationInfo().mergeFrom(input);
        }
    }

    public static final class ResolvedProperty extends ExtendableMessageNano {
        public static final ResolvedProperty[] EMPTY_ARRAY = new ResolvedProperty[0];
        public String key = "";
        public TypeSystem.Value value = null;

        public final ResolvedProperty clear() {
            this.key = "";
            this.value = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ResolvedProperty)) {
                return false;
            }
            ResolvedProperty other = (ResolvedProperty) o;
            if (this.key != null ? this.key.equals(other.key) : other.key == null) {
                if (this.value != null ? this.value.equals(other.value) : other.value == null) {
                    if (this.unknownFieldData == null) {
                        if (other.unknownFieldData == null) {
                            return true;
                        }
                    } else if (this.unknownFieldData.equals(other.unknownFieldData)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.key == null ? 0 : this.key.hashCode()) + 527) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.key.equals("")) {
                output.writeString(1, this.key);
            }
            if (this.value != null) {
                output.writeMessage(2, this.value);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (!this.key.equals("")) {
                size = 0 + CodedOutputByteBufferNano.computeStringSize(1, this.key);
            }
            if (this.value != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.value);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public ResolvedProperty mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        this.key = input.readString();
                        continue;
                    case 18:
                        this.value = new TypeSystem.Value();
                        input.readMessage(this.value);
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public static ResolvedProperty parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ResolvedProperty) MessageNano.mergeFrom(new ResolvedProperty(), data);
        }

        public static ResolvedProperty parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ResolvedProperty().mergeFrom(input);
        }
    }
}
