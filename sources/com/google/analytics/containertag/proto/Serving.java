package com.google.analytics.containertag.proto;

import android.support.p000v4.media.TransportMediator;
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

public interface Serving {

    public interface ResourceState {
        public static final int LIVE = 2;
        public static final int PREVIEW = 1;
    }

    public interface ResourceType {
        public static final int CLEAR_CACHE = 6;
        public static final int GET_COOKIE = 5;
        public static final int JS_RESOURCE = 1;
        public static final int NS_RESOURCE = 2;
        public static final int PIXEL_COLLECTION = 3;
        public static final int RAW_PROTO = 7;
        public static final int SET_COOKIE = 4;
    }

    public static final class Container extends ExtendableMessageNano {
        public static final Container[] EMPTY_ARRAY = new Container[0];
        public String containerId = "";
        public Resource jsResource = null;
        public int state = 1;
        public String version = "";

        public final Container clear() {
            this.jsResource = null;
            this.containerId = "";
            this.state = 1;
            this.version = "";
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Container)) {
                return false;
            }
            Container other = (Container) o;
            if (this.jsResource != null ? this.jsResource.equals(other.jsResource) : other.jsResource == null) {
                if (this.containerId != null ? this.containerId.equals(other.containerId) : other.containerId == null) {
                    if (this.state == other.state && (this.version != null ? this.version.equals(other.version) : other.version == null)) {
                        if (this.unknownFieldData == null) {
                            if (other.unknownFieldData == null) {
                                return true;
                            }
                        } else if (this.unknownFieldData.equals(other.unknownFieldData)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((((this.jsResource == null ? 0 : this.jsResource.hashCode()) + 527) * 31) + (this.containerId == null ? 0 : this.containerId.hashCode())) * 31) + this.state) * 31) + (this.version == null ? 0 : this.version.hashCode())) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.jsResource != null) {
                output.writeMessage(1, this.jsResource);
            }
            output.writeString(3, this.containerId);
            output.writeInt32(4, this.state);
            if (!this.version.equals("")) {
                output.writeString(5, this.version);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.jsResource != null) {
                size = 0 + CodedOutputByteBufferNano.computeMessageSize(1, this.jsResource);
            }
            int size2 = size + CodedOutputByteBufferNano.computeStringSize(3, this.containerId) + CodedOutputByteBufferNano.computeInt32Size(4, this.state);
            if (!this.version.equals("")) {
                size2 += CodedOutputByteBufferNano.computeStringSize(5, this.version);
            }
            int size3 = size2 + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size3;
            return size3;
        }

        public Container mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        this.jsResource = new Resource();
                        input.readMessage(this.jsResource);
                        continue;
                    case 26:
                        this.containerId = input.readString();
                        continue;
                    case 32:
                        int temp = input.readInt32();
                        if (temp != 1 && temp != 2) {
                            this.state = 1;
                            break;
                        } else {
                            this.state = temp;
                            continue;
                        }
                    case 42:
                        this.version = input.readString();
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

        public static Container parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Container) MessageNano.mergeFrom(new Container(), data);
        }

        public static Container parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Container().mergeFrom(input);
        }
    }

    public static final class ServingValue extends ExtendableMessageNano {
        public static final ServingValue[] EMPTY_ARRAY = new ServingValue[0];
        public static final Extension<ServingValue> ext = Extension.create(101, new Extension.TypeLiteral<ServingValue>() {
        });
        public int[] listItem = WireFormatNano.EMPTY_INT_ARRAY;
        public int macroNameReference = 0;
        public int macroReference = 0;
        public int[] mapKey = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] mapValue = WireFormatNano.EMPTY_INT_ARRAY;
        public int tagReference = 0;
        public int[] templateToken = WireFormatNano.EMPTY_INT_ARRAY;

        public final ServingValue clear() {
            this.listItem = WireFormatNano.EMPTY_INT_ARRAY;
            this.mapKey = WireFormatNano.EMPTY_INT_ARRAY;
            this.mapValue = WireFormatNano.EMPTY_INT_ARRAY;
            this.macroReference = 0;
            this.templateToken = WireFormatNano.EMPTY_INT_ARRAY;
            this.macroNameReference = 0;
            this.tagReference = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ServingValue)) {
                return false;
            }
            ServingValue other = (ServingValue) o;
            if (Arrays.equals(this.listItem, other.listItem) && Arrays.equals(this.mapKey, other.mapKey) && Arrays.equals(this.mapValue, other.mapValue) && this.macroReference == other.macroReference && Arrays.equals(this.templateToken, other.templateToken) && this.macroNameReference == other.macroNameReference && this.tagReference == other.tagReference) {
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
            int result = 17;
            if (this.listItem == null) {
                result = 17 * 31;
            } else {
                for (int i : this.listItem) {
                    result = (result * 31) + i;
                }
            }
            if (this.mapKey == null) {
                result *= 31;
            } else {
                for (int i2 : this.mapKey) {
                    result = (result * 31) + i2;
                }
            }
            if (this.mapValue == null) {
                result *= 31;
            } else {
                for (int i3 : this.mapValue) {
                    result = (result * 31) + i3;
                }
            }
            int result2 = (result * 31) + this.macroReference;
            if (this.templateToken == null) {
                result2 *= 31;
            } else {
                for (int i4 : this.templateToken) {
                    result2 = (result2 * 31) + i4;
                }
            }
            return (((((result2 * 31) + this.macroNameReference) * 31) + this.tagReference) * 31) + (this.unknownFieldData == null ? 0 : this.unknownFieldData.hashCode());
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.listItem != null) {
                for (int element : this.listItem) {
                    output.writeInt32(1, element);
                }
            }
            if (this.mapKey != null) {
                for (int element2 : this.mapKey) {
                    output.writeInt32(2, element2);
                }
            }
            if (this.mapValue != null) {
                for (int element3 : this.mapValue) {
                    output.writeInt32(3, element3);
                }
            }
            if (this.macroReference != 0) {
                output.writeInt32(4, this.macroReference);
            }
            if (this.templateToken != null) {
                for (int element4 : this.templateToken) {
                    output.writeInt32(5, element4);
                }
            }
            if (this.macroNameReference != 0) {
                output.writeInt32(6, this.macroNameReference);
            }
            if (this.tagReference != 0) {
                output.writeInt32(7, this.tagReference);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.listItem != null && this.listItem.length > 0) {
                int dataSize = 0;
                for (int element : this.listItem) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = 0 + dataSize + (this.listItem.length * 1);
            }
            if (this.mapKey != null && this.mapKey.length > 0) {
                int dataSize2 = 0;
                for (int element2 : this.mapKey) {
                    dataSize2 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = size + dataSize2 + (this.mapKey.length * 1);
            }
            if (this.mapValue != null && this.mapValue.length > 0) {
                int dataSize3 = 0;
                for (int element3 : this.mapValue) {
                    dataSize3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element3);
                }
                size = size + dataSize3 + (this.mapValue.length * 1);
            }
            if (this.macroReference != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.macroReference);
            }
            if (this.templateToken != null && this.templateToken.length > 0) {
                int dataSize4 = 0;
                for (int element4 : this.templateToken) {
                    dataSize4 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element4);
                }
                size = size + dataSize4 + (this.templateToken.length * 1);
            }
            if (this.macroNameReference != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.macroNameReference);
            }
            if (this.tagReference != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.tagReference);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public ServingValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
                        int i = this.listItem.length;
                        int[] newArray = new int[(i + arrayLength)];
                        System.arraycopy(this.listItem, 0, newArray, 0, i);
                        this.listItem = newArray;
                        while (i < this.listItem.length - 1) {
                            this.listItem[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        this.listItem[i] = input.readInt32();
                        continue;
                    case 16:
                        int arrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(input, 16);
                        int i2 = this.mapKey.length;
                        int[] newArray2 = new int[(i2 + arrayLength2)];
                        System.arraycopy(this.mapKey, 0, newArray2, 0, i2);
                        this.mapKey = newArray2;
                        while (i2 < this.mapKey.length - 1) {
                            this.mapKey[i2] = input.readInt32();
                            input.readTag();
                            i2++;
                        }
                        this.mapKey[i2] = input.readInt32();
                        continue;
                    case 24:
                        int arrayLength3 = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        int i3 = this.mapValue.length;
                        int[] newArray3 = new int[(i3 + arrayLength3)];
                        System.arraycopy(this.mapValue, 0, newArray3, 0, i3);
                        this.mapValue = newArray3;
                        while (i3 < this.mapValue.length - 1) {
                            this.mapValue[i3] = input.readInt32();
                            input.readTag();
                            i3++;
                        }
                        this.mapValue[i3] = input.readInt32();
                        continue;
                    case 32:
                        this.macroReference = input.readInt32();
                        continue;
                    case 40:
                        int arrayLength4 = WireFormatNano.getRepeatedFieldArrayLength(input, 40);
                        int i4 = this.templateToken.length;
                        int[] newArray4 = new int[(i4 + arrayLength4)];
                        System.arraycopy(this.templateToken, 0, newArray4, 0, i4);
                        this.templateToken = newArray4;
                        while (i4 < this.templateToken.length - 1) {
                            this.templateToken[i4] = input.readInt32();
                            input.readTag();
                            i4++;
                        }
                        this.templateToken[i4] = input.readInt32();
                        continue;
                    case 48:
                        this.macroNameReference = input.readInt32();
                        continue;
                    case 56:
                        this.tagReference = input.readInt32();
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

        public static ServingValue parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ServingValue) MessageNano.mergeFrom(new ServingValue(), data);
        }

        public static ServingValue parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ServingValue().mergeFrom(input);
        }
    }

    public static final class Property extends ExtendableMessageNano {
        public static final Property[] EMPTY_ARRAY = new Property[0];
        public int key = 0;
        public int value = 0;

        public final Property clear() {
            this.key = 0;
            this.value = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Property)) {
                return false;
            }
            Property other = (Property) o;
            if (this.key == other.key && this.value == other.value) {
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
            return ((((this.key + 527) * 31) + this.value) * 31) + (this.unknownFieldData == null ? 0 : this.unknownFieldData.hashCode());
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            output.writeInt32(1, this.key);
            output.writeInt32(2, this.value);
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0 + CodedOutputByteBufferNano.computeInt32Size(1, this.key) + CodedOutputByteBufferNano.computeInt32Size(2, this.value) + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size;
            return size;
        }

        public Property mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        this.key = input.readInt32();
                        continue;
                    case 16:
                        this.value = input.readInt32();
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

        public static Property parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Property) MessageNano.mergeFrom(new Property(), data);
        }

        public static Property parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Property().mergeFrom(input);
        }
    }

    public static final class FunctionCall extends ExtendableMessageNano {
        public static final FunctionCall[] EMPTY_ARRAY = new FunctionCall[0];
        public int function = 0;
        public boolean liveOnly = false;
        public int name = 0;
        public int[] property = WireFormatNano.EMPTY_INT_ARRAY;
        public boolean serverSide = false;

        public final FunctionCall clear() {
            this.property = WireFormatNano.EMPTY_INT_ARRAY;
            this.function = 0;
            this.name = 0;
            this.liveOnly = false;
            this.serverSide = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FunctionCall)) {
                return false;
            }
            FunctionCall other = (FunctionCall) o;
            if (Arrays.equals(this.property, other.property) && this.function == other.function && this.name == other.name && this.liveOnly == other.liveOnly && this.serverSide == other.serverSide) {
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
            int i;
            int i2 = 1;
            int result = 17;
            if (this.property == null) {
                result = 17 * 31;
            } else {
                for (int i3 : this.property) {
                    result = (result * 31) + i3;
                }
            }
            int i4 = ((((result * 31) + this.function) * 31) + this.name) * 31;
            if (this.liveOnly) {
                i = 1;
            } else {
                i = 2;
            }
            int i5 = (i4 + i) * 31;
            if (!this.serverSide) {
                i2 = 2;
            }
            return ((i5 + i2) * 31) + (this.unknownFieldData == null ? 0 : this.unknownFieldData.hashCode());
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.serverSide) {
                output.writeBool(1, this.serverSide);
            }
            output.writeInt32(2, this.function);
            if (this.property != null) {
                for (int element : this.property) {
                    output.writeInt32(3, element);
                }
            }
            if (this.name != 0) {
                output.writeInt32(4, this.name);
            }
            if (this.liveOnly) {
                output.writeBool(6, this.liveOnly);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.serverSide) {
                size = 0 + CodedOutputByteBufferNano.computeBoolSize(1, this.serverSide);
            }
            int size2 = size + CodedOutputByteBufferNano.computeInt32Size(2, this.function);
            if (this.property != null && this.property.length > 0) {
                int dataSize = 0;
                for (int element : this.property) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size2 = size2 + dataSize + (this.property.length * 1);
            }
            if (this.name != 0) {
                size2 += CodedOutputByteBufferNano.computeInt32Size(4, this.name);
            }
            if (this.liveOnly) {
                size2 += CodedOutputByteBufferNano.computeBoolSize(6, this.liveOnly);
            }
            int size3 = size2 + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size3;
            return size3;
        }

        public FunctionCall mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        this.serverSide = input.readBool();
                        continue;
                    case 16:
                        this.function = input.readInt32();
                        continue;
                    case 24:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        int i = this.property.length;
                        int[] newArray = new int[(i + arrayLength)];
                        System.arraycopy(this.property, 0, newArray, 0, i);
                        this.property = newArray;
                        while (i < this.property.length - 1) {
                            this.property[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        this.property[i] = input.readInt32();
                        continue;
                    case 32:
                        this.name = input.readInt32();
                        continue;
                    case 48:
                        this.liveOnly = input.readBool();
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

        public static FunctionCall parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FunctionCall) MessageNano.mergeFrom(new FunctionCall(), data);
        }

        public static FunctionCall parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FunctionCall().mergeFrom(input);
        }
    }

    public static final class Rule extends ExtendableMessageNano {
        public static final Rule[] EMPTY_ARRAY = new Rule[0];
        public int[] addMacro = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] addMacroRuleName = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] addTag = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] addTagRuleName = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] negativePredicate = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] positivePredicate = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] removeMacro = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] removeMacroRuleName = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] removeTag = WireFormatNano.EMPTY_INT_ARRAY;
        public int[] removeTagRuleName = WireFormatNano.EMPTY_INT_ARRAY;

        public final Rule clear() {
            this.positivePredicate = WireFormatNano.EMPTY_INT_ARRAY;
            this.negativePredicate = WireFormatNano.EMPTY_INT_ARRAY;
            this.addTag = WireFormatNano.EMPTY_INT_ARRAY;
            this.removeTag = WireFormatNano.EMPTY_INT_ARRAY;
            this.addTagRuleName = WireFormatNano.EMPTY_INT_ARRAY;
            this.removeTagRuleName = WireFormatNano.EMPTY_INT_ARRAY;
            this.addMacro = WireFormatNano.EMPTY_INT_ARRAY;
            this.removeMacro = WireFormatNano.EMPTY_INT_ARRAY;
            this.addMacroRuleName = WireFormatNano.EMPTY_INT_ARRAY;
            this.removeMacroRuleName = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Rule)) {
                return false;
            }
            Rule other = (Rule) o;
            if (Arrays.equals(this.positivePredicate, other.positivePredicate) && Arrays.equals(this.negativePredicate, other.negativePredicate) && Arrays.equals(this.addTag, other.addTag) && Arrays.equals(this.removeTag, other.removeTag) && Arrays.equals(this.addTagRuleName, other.addTagRuleName) && Arrays.equals(this.removeTagRuleName, other.removeTagRuleName) && Arrays.equals(this.addMacro, other.addMacro) && Arrays.equals(this.removeMacro, other.removeMacro) && Arrays.equals(this.addMacroRuleName, other.addMacroRuleName) && Arrays.equals(this.removeMacroRuleName, other.removeMacroRuleName)) {
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
            int result = 17;
            if (this.positivePredicate == null) {
                result = 17 * 31;
            } else {
                for (int i : this.positivePredicate) {
                    result = (result * 31) + i;
                }
            }
            if (this.negativePredicate == null) {
                result *= 31;
            } else {
                for (int i2 : this.negativePredicate) {
                    result = (result * 31) + i2;
                }
            }
            if (this.addTag == null) {
                result *= 31;
            } else {
                for (int i3 : this.addTag) {
                    result = (result * 31) + i3;
                }
            }
            if (this.removeTag == null) {
                result *= 31;
            } else {
                for (int i4 : this.removeTag) {
                    result = (result * 31) + i4;
                }
            }
            if (this.addTagRuleName == null) {
                result *= 31;
            } else {
                for (int i5 : this.addTagRuleName) {
                    result = (result * 31) + i5;
                }
            }
            if (this.removeTagRuleName == null) {
                result *= 31;
            } else {
                for (int i6 : this.removeTagRuleName) {
                    result = (result * 31) + i6;
                }
            }
            if (this.addMacro == null) {
                result *= 31;
            } else {
                for (int i7 : this.addMacro) {
                    result = (result * 31) + i7;
                }
            }
            if (this.removeMacro == null) {
                result *= 31;
            } else {
                for (int i8 : this.removeMacro) {
                    result = (result * 31) + i8;
                }
            }
            if (this.addMacroRuleName == null) {
                result *= 31;
            } else {
                for (int i9 : this.addMacroRuleName) {
                    result = (result * 31) + i9;
                }
            }
            if (this.removeMacroRuleName == null) {
                result *= 31;
            } else {
                for (int i10 : this.removeMacroRuleName) {
                    result = (result * 31) + i10;
                }
            }
            return (result * 31) + (this.unknownFieldData == null ? 0 : this.unknownFieldData.hashCode());
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.positivePredicate != null) {
                for (int element : this.positivePredicate) {
                    output.writeInt32(1, element);
                }
            }
            if (this.negativePredicate != null) {
                for (int element2 : this.negativePredicate) {
                    output.writeInt32(2, element2);
                }
            }
            if (this.addTag != null) {
                for (int element3 : this.addTag) {
                    output.writeInt32(3, element3);
                }
            }
            if (this.removeTag != null) {
                for (int element4 : this.removeTag) {
                    output.writeInt32(4, element4);
                }
            }
            if (this.addTagRuleName != null) {
                for (int element5 : this.addTagRuleName) {
                    output.writeInt32(5, element5);
                }
            }
            if (this.removeTagRuleName != null) {
                for (int element6 : this.removeTagRuleName) {
                    output.writeInt32(6, element6);
                }
            }
            if (this.addMacro != null) {
                for (int element7 : this.addMacro) {
                    output.writeInt32(7, element7);
                }
            }
            if (this.removeMacro != null) {
                for (int element8 : this.removeMacro) {
                    output.writeInt32(8, element8);
                }
            }
            if (this.addMacroRuleName != null) {
                for (int element9 : this.addMacroRuleName) {
                    output.writeInt32(9, element9);
                }
            }
            if (this.removeMacroRuleName != null) {
                for (int element10 : this.removeMacroRuleName) {
                    output.writeInt32(10, element10);
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.positivePredicate != null && this.positivePredicate.length > 0) {
                int dataSize = 0;
                for (int element : this.positivePredicate) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = 0 + dataSize + (this.positivePredicate.length * 1);
            }
            if (this.negativePredicate != null && this.negativePredicate.length > 0) {
                int dataSize2 = 0;
                for (int element2 : this.negativePredicate) {
                    dataSize2 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = size + dataSize2 + (this.negativePredicate.length * 1);
            }
            if (this.addTag != null && this.addTag.length > 0) {
                int dataSize3 = 0;
                for (int element3 : this.addTag) {
                    dataSize3 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element3);
                }
                size = size + dataSize3 + (this.addTag.length * 1);
            }
            if (this.removeTag != null && this.removeTag.length > 0) {
                int dataSize4 = 0;
                for (int element4 : this.removeTag) {
                    dataSize4 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element4);
                }
                size = size + dataSize4 + (this.removeTag.length * 1);
            }
            if (this.addTagRuleName != null && this.addTagRuleName.length > 0) {
                int dataSize5 = 0;
                for (int element5 : this.addTagRuleName) {
                    dataSize5 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element5);
                }
                size = size + dataSize5 + (this.addTagRuleName.length * 1);
            }
            if (this.removeTagRuleName != null && this.removeTagRuleName.length > 0) {
                int dataSize6 = 0;
                for (int element6 : this.removeTagRuleName) {
                    dataSize6 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element6);
                }
                size = size + dataSize6 + (this.removeTagRuleName.length * 1);
            }
            if (this.addMacro != null && this.addMacro.length > 0) {
                int dataSize7 = 0;
                for (int element7 : this.addMacro) {
                    dataSize7 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element7);
                }
                size = size + dataSize7 + (this.addMacro.length * 1);
            }
            if (this.removeMacro != null && this.removeMacro.length > 0) {
                int dataSize8 = 0;
                for (int element8 : this.removeMacro) {
                    dataSize8 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element8);
                }
                size = size + dataSize8 + (this.removeMacro.length * 1);
            }
            if (this.addMacroRuleName != null && this.addMacroRuleName.length > 0) {
                int dataSize9 = 0;
                for (int element9 : this.addMacroRuleName) {
                    dataSize9 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element9);
                }
                size = size + dataSize9 + (this.addMacroRuleName.length * 1);
            }
            if (this.removeMacroRuleName != null && this.removeMacroRuleName.length > 0) {
                int dataSize10 = 0;
                for (int element10 : this.removeMacroRuleName) {
                    dataSize10 += CodedOutputByteBufferNano.computeInt32SizeNoTag(element10);
                }
                size = size + dataSize10 + (this.removeMacroRuleName.length * 1);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public Rule mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
                        int i = this.positivePredicate.length;
                        int[] newArray = new int[(i + arrayLength)];
                        System.arraycopy(this.positivePredicate, 0, newArray, 0, i);
                        this.positivePredicate = newArray;
                        while (i < this.positivePredicate.length - 1) {
                            this.positivePredicate[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        this.positivePredicate[i] = input.readInt32();
                        continue;
                    case 16:
                        int arrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(input, 16);
                        int i2 = this.negativePredicate.length;
                        int[] newArray2 = new int[(i2 + arrayLength2)];
                        System.arraycopy(this.negativePredicate, 0, newArray2, 0, i2);
                        this.negativePredicate = newArray2;
                        while (i2 < this.negativePredicate.length - 1) {
                            this.negativePredicate[i2] = input.readInt32();
                            input.readTag();
                            i2++;
                        }
                        this.negativePredicate[i2] = input.readInt32();
                        continue;
                    case 24:
                        int arrayLength3 = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        int i3 = this.addTag.length;
                        int[] newArray3 = new int[(i3 + arrayLength3)];
                        System.arraycopy(this.addTag, 0, newArray3, 0, i3);
                        this.addTag = newArray3;
                        while (i3 < this.addTag.length - 1) {
                            this.addTag[i3] = input.readInt32();
                            input.readTag();
                            i3++;
                        }
                        this.addTag[i3] = input.readInt32();
                        continue;
                    case 32:
                        int arrayLength4 = WireFormatNano.getRepeatedFieldArrayLength(input, 32);
                        int i4 = this.removeTag.length;
                        int[] newArray4 = new int[(i4 + arrayLength4)];
                        System.arraycopy(this.removeTag, 0, newArray4, 0, i4);
                        this.removeTag = newArray4;
                        while (i4 < this.removeTag.length - 1) {
                            this.removeTag[i4] = input.readInt32();
                            input.readTag();
                            i4++;
                        }
                        this.removeTag[i4] = input.readInt32();
                        continue;
                    case 40:
                        int arrayLength5 = WireFormatNano.getRepeatedFieldArrayLength(input, 40);
                        int i5 = this.addTagRuleName.length;
                        int[] newArray5 = new int[(i5 + arrayLength5)];
                        System.arraycopy(this.addTagRuleName, 0, newArray5, 0, i5);
                        this.addTagRuleName = newArray5;
                        while (i5 < this.addTagRuleName.length - 1) {
                            this.addTagRuleName[i5] = input.readInt32();
                            input.readTag();
                            i5++;
                        }
                        this.addTagRuleName[i5] = input.readInt32();
                        continue;
                    case 48:
                        int arrayLength6 = WireFormatNano.getRepeatedFieldArrayLength(input, 48);
                        int i6 = this.removeTagRuleName.length;
                        int[] newArray6 = new int[(i6 + arrayLength6)];
                        System.arraycopy(this.removeTagRuleName, 0, newArray6, 0, i6);
                        this.removeTagRuleName = newArray6;
                        while (i6 < this.removeTagRuleName.length - 1) {
                            this.removeTagRuleName[i6] = input.readInt32();
                            input.readTag();
                            i6++;
                        }
                        this.removeTagRuleName[i6] = input.readInt32();
                        continue;
                    case 56:
                        int arrayLength7 = WireFormatNano.getRepeatedFieldArrayLength(input, 56);
                        int i7 = this.addMacro.length;
                        int[] newArray7 = new int[(i7 + arrayLength7)];
                        System.arraycopy(this.addMacro, 0, newArray7, 0, i7);
                        this.addMacro = newArray7;
                        while (i7 < this.addMacro.length - 1) {
                            this.addMacro[i7] = input.readInt32();
                            input.readTag();
                            i7++;
                        }
                        this.addMacro[i7] = input.readInt32();
                        continue;
                    case 64:
                        int arrayLength8 = WireFormatNano.getRepeatedFieldArrayLength(input, 64);
                        int i8 = this.removeMacro.length;
                        int[] newArray8 = new int[(i8 + arrayLength8)];
                        System.arraycopy(this.removeMacro, 0, newArray8, 0, i8);
                        this.removeMacro = newArray8;
                        while (i8 < this.removeMacro.length - 1) {
                            this.removeMacro[i8] = input.readInt32();
                            input.readTag();
                            i8++;
                        }
                        this.removeMacro[i8] = input.readInt32();
                        continue;
                    case 72:
                        int arrayLength9 = WireFormatNano.getRepeatedFieldArrayLength(input, 72);
                        int i9 = this.addMacroRuleName.length;
                        int[] newArray9 = new int[(i9 + arrayLength9)];
                        System.arraycopy(this.addMacroRuleName, 0, newArray9, 0, i9);
                        this.addMacroRuleName = newArray9;
                        while (i9 < this.addMacroRuleName.length - 1) {
                            this.addMacroRuleName[i9] = input.readInt32();
                            input.readTag();
                            i9++;
                        }
                        this.addMacroRuleName[i9] = input.readInt32();
                        continue;
                    case 80:
                        int arrayLength10 = WireFormatNano.getRepeatedFieldArrayLength(input, 80);
                        int i10 = this.removeMacroRuleName.length;
                        int[] newArray10 = new int[(i10 + arrayLength10)];
                        System.arraycopy(this.removeMacroRuleName, 0, newArray10, 0, i10);
                        this.removeMacroRuleName = newArray10;
                        while (i10 < this.removeMacroRuleName.length - 1) {
                            this.removeMacroRuleName[i10] = input.readInt32();
                            input.readTag();
                            i10++;
                        }
                        this.removeMacroRuleName[i10] = input.readInt32();
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

        public static Rule parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Rule) MessageNano.mergeFrom(new Rule(), data);
        }

        public static Rule parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Rule().mergeFrom(input);
        }
    }

    public static final class CacheOption extends ExtendableMessageNano {
        public static final CacheOption[] EMPTY_ARRAY = new CacheOption[0];
        public int expirationSeconds = 0;
        public int gcacheExpirationSeconds = 0;
        public int level = 1;

        public interface CacheLevel {
            public static final int NO_CACHE = 1;
            public static final int PRIVATE = 2;
            public static final int PUBLIC = 3;
        }

        public final CacheOption clear() {
            this.level = 1;
            this.expirationSeconds = 0;
            this.gcacheExpirationSeconds = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CacheOption)) {
                return false;
            }
            CacheOption other = (CacheOption) o;
            if (this.level == other.level && this.expirationSeconds == other.expirationSeconds && this.gcacheExpirationSeconds == other.gcacheExpirationSeconds) {
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
            return ((((((this.level + 527) * 31) + this.expirationSeconds) * 31) + this.gcacheExpirationSeconds) * 31) + (this.unknownFieldData == null ? 0 : this.unknownFieldData.hashCode());
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.level != 1) {
                output.writeInt32(1, this.level);
            }
            if (this.expirationSeconds != 0) {
                output.writeInt32(2, this.expirationSeconds);
            }
            if (this.gcacheExpirationSeconds != 0) {
                output.writeInt32(3, this.gcacheExpirationSeconds);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.level != 1) {
                size = 0 + CodedOutputByteBufferNano.computeInt32Size(1, this.level);
            }
            if (this.expirationSeconds != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.expirationSeconds);
            }
            if (this.gcacheExpirationSeconds != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.gcacheExpirationSeconds);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public CacheOption mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        int temp = input.readInt32();
                        if (temp != 1 && temp != 2 && temp != 3) {
                            this.level = 1;
                            break;
                        } else {
                            this.level = temp;
                            continue;
                        }
                        break;
                    case 16:
                        this.expirationSeconds = input.readInt32();
                        continue;
                    case 24:
                        this.gcacheExpirationSeconds = input.readInt32();
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

        public static CacheOption parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CacheOption) MessageNano.mergeFrom(new CacheOption(), data);
        }

        public static CacheOption parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CacheOption().mergeFrom(input);
        }
    }

    public static final class Resource extends ExtendableMessageNano {
        public static final Resource[] EMPTY_ARRAY = new Resource[0];
        private static final String TEMPLATE_VERSION_SET_DEFAULT = "0";
        public String[] key = WireFormatNano.EMPTY_STRING_ARRAY;
        public CacheOption liveJsCacheOption = null;
        public FunctionCall[] macro = FunctionCall.EMPTY_ARRAY;
        public String malwareScanAuthCode = "";
        public boolean oBSOLETEEnableAutoEventTracking = false;
        public FunctionCall[] predicate = FunctionCall.EMPTY_ARRAY;
        public String previewAuthCode = "";
        public Property[] property = Property.EMPTY_ARRAY;
        public float reportingSampleRate = 0.0f;
        public int resourceFormatVersion = 0;
        public Rule[] rule = Rule.EMPTY_ARRAY;
        public String[] supplemental = WireFormatNano.EMPTY_STRING_ARRAY;
        public FunctionCall[] tag = FunctionCall.EMPTY_ARRAY;
        public String templateVersionSet = TEMPLATE_VERSION_SET_DEFAULT;
        public String[] usageContext = WireFormatNano.EMPTY_STRING_ARRAY;
        public TypeSystem.Value[] value = TypeSystem.Value.EMPTY_ARRAY;
        public String version = "";

        public final Resource clear() {
            this.supplemental = WireFormatNano.EMPTY_STRING_ARRAY;
            this.key = WireFormatNano.EMPTY_STRING_ARRAY;
            this.value = TypeSystem.Value.EMPTY_ARRAY;
            this.property = Property.EMPTY_ARRAY;
            this.macro = FunctionCall.EMPTY_ARRAY;
            this.tag = FunctionCall.EMPTY_ARRAY;
            this.predicate = FunctionCall.EMPTY_ARRAY;
            this.rule = Rule.EMPTY_ARRAY;
            this.previewAuthCode = "";
            this.malwareScanAuthCode = "";
            this.templateVersionSet = TEMPLATE_VERSION_SET_DEFAULT;
            this.version = "";
            this.liveJsCacheOption = null;
            this.reportingSampleRate = 0.0f;
            this.oBSOLETEEnableAutoEventTracking = false;
            this.usageContext = WireFormatNano.EMPTY_STRING_ARRAY;
            this.resourceFormatVersion = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Resource)) {
                return false;
            }
            Resource other = (Resource) o;
            if (Arrays.equals(this.supplemental, other.supplemental) && Arrays.equals(this.key, other.key) && Arrays.equals(this.value, other.value) && Arrays.equals(this.property, other.property) && Arrays.equals(this.macro, other.macro) && Arrays.equals(this.tag, other.tag) && Arrays.equals(this.predicate, other.predicate) && Arrays.equals(this.rule, other.rule) && (this.previewAuthCode != null ? this.previewAuthCode.equals(other.previewAuthCode) : other.previewAuthCode == null) && (this.malwareScanAuthCode != null ? this.malwareScanAuthCode.equals(other.malwareScanAuthCode) : other.malwareScanAuthCode == null) && (this.templateVersionSet != null ? this.templateVersionSet.equals(other.templateVersionSet) : other.templateVersionSet == null) && (this.version != null ? this.version.equals(other.version) : other.version == null) && (this.liveJsCacheOption != null ? this.liveJsCacheOption.equals(other.liveJsCacheOption) : other.liveJsCacheOption == null) && this.reportingSampleRate == other.reportingSampleRate && this.oBSOLETEEnableAutoEventTracking == other.oBSOLETEEnableAutoEventTracking && Arrays.equals(this.usageContext, other.usageContext) && this.resourceFormatVersion == other.resourceFormatVersion) {
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
            if (this.supplemental == null) {
                result = 17 * 31;
            } else {
                for (int i2 = 0; i2 < this.supplemental.length; i2++) {
                    result = (result * 31) + (this.supplemental[i2] == null ? 0 : this.supplemental[i2].hashCode());
                }
            }
            if (this.key == null) {
                result *= 31;
            } else {
                for (int i3 = 0; i3 < this.key.length; i3++) {
                    result = (result * 31) + (this.key[i3] == null ? 0 : this.key[i3].hashCode());
                }
            }
            if (this.value == null) {
                result *= 31;
            } else {
                for (int i4 = 0; i4 < this.value.length; i4++) {
                    result = (result * 31) + (this.value[i4] == null ? 0 : this.value[i4].hashCode());
                }
            }
            if (this.property == null) {
                result *= 31;
            } else {
                for (int i5 = 0; i5 < this.property.length; i5++) {
                    result = (result * 31) + (this.property[i5] == null ? 0 : this.property[i5].hashCode());
                }
            }
            if (this.macro == null) {
                result *= 31;
            } else {
                for (int i6 = 0; i6 < this.macro.length; i6++) {
                    result = (result * 31) + (this.macro[i6] == null ? 0 : this.macro[i6].hashCode());
                }
            }
            if (this.tag == null) {
                result *= 31;
            } else {
                for (int i7 = 0; i7 < this.tag.length; i7++) {
                    result = (result * 31) + (this.tag[i7] == null ? 0 : this.tag[i7].hashCode());
                }
            }
            if (this.predicate == null) {
                result *= 31;
            } else {
                for (int i8 = 0; i8 < this.predicate.length; i8++) {
                    result = (result * 31) + (this.predicate[i8] == null ? 0 : this.predicate[i8].hashCode());
                }
            }
            if (this.rule == null) {
                result *= 31;
            } else {
                for (int i9 = 0; i9 < this.rule.length; i9++) {
                    result = (result * 31) + (this.rule[i9] == null ? 0 : this.rule[i9].hashCode());
                }
            }
            int result2 = (((((((((((((result * 31) + (this.previewAuthCode == null ? 0 : this.previewAuthCode.hashCode())) * 31) + (this.malwareScanAuthCode == null ? 0 : this.malwareScanAuthCode.hashCode())) * 31) + (this.templateVersionSet == null ? 0 : this.templateVersionSet.hashCode())) * 31) + (this.version == null ? 0 : this.version.hashCode())) * 31) + (this.liveJsCacheOption == null ? 0 : this.liveJsCacheOption.hashCode())) * 31) + Float.floatToIntBits(this.reportingSampleRate)) * 31) + (this.oBSOLETEEnableAutoEventTracking ? 1 : 2);
            if (this.usageContext == null) {
                result2 *= 31;
            } else {
                for (int i10 = 0; i10 < this.usageContext.length; i10++) {
                    result2 = (result2 * 31) + (this.usageContext[i10] == null ? 0 : this.usageContext[i10].hashCode());
                }
            }
            int i11 = ((result2 * 31) + this.resourceFormatVersion) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return i11 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.key != null) {
                for (String element : this.key) {
                    output.writeString(1, element);
                }
            }
            if (this.value != null) {
                for (TypeSystem.Value element2 : this.value) {
                    output.writeMessage(2, element2);
                }
            }
            if (this.property != null) {
                for (Property element3 : this.property) {
                    output.writeMessage(3, element3);
                }
            }
            if (this.macro != null) {
                for (FunctionCall element4 : this.macro) {
                    output.writeMessage(4, element4);
                }
            }
            if (this.tag != null) {
                for (FunctionCall element5 : this.tag) {
                    output.writeMessage(5, element5);
                }
            }
            if (this.predicate != null) {
                for (FunctionCall element6 : this.predicate) {
                    output.writeMessage(6, element6);
                }
            }
            if (this.rule != null) {
                for (Rule element7 : this.rule) {
                    output.writeMessage(7, element7);
                }
            }
            if (!this.previewAuthCode.equals("")) {
                output.writeString(9, this.previewAuthCode);
            }
            if (!this.malwareScanAuthCode.equals("")) {
                output.writeString(10, this.malwareScanAuthCode);
            }
            if (!this.templateVersionSet.equals(TEMPLATE_VERSION_SET_DEFAULT)) {
                output.writeString(12, this.templateVersionSet);
            }
            if (!this.version.equals("")) {
                output.writeString(13, this.version);
            }
            if (this.liveJsCacheOption != null) {
                output.writeMessage(14, this.liveJsCacheOption);
            }
            if (this.reportingSampleRate != 0.0f) {
                output.writeFloat(15, this.reportingSampleRate);
            }
            if (this.usageContext != null) {
                for (String element8 : this.usageContext) {
                    output.writeString(16, element8);
                }
            }
            if (this.resourceFormatVersion != 0) {
                output.writeInt32(17, this.resourceFormatVersion);
            }
            if (this.oBSOLETEEnableAutoEventTracking) {
                output.writeBool(18, this.oBSOLETEEnableAutoEventTracking);
            }
            if (this.supplemental != null) {
                for (String element9 : this.supplemental) {
                    output.writeString(19, element9);
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.key != null && this.key.length > 0) {
                int dataSize = 0;
                for (String element : this.key) {
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
                size = 0 + dataSize + (this.key.length * 1);
            }
            if (this.value != null) {
                for (TypeSystem.Value element2 : this.value) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, element2);
                }
            }
            if (this.property != null) {
                for (Property element3 : this.property) {
                    size += CodedOutputByteBufferNano.computeMessageSize(3, element3);
                }
            }
            if (this.macro != null) {
                for (FunctionCall element4 : this.macro) {
                    size += CodedOutputByteBufferNano.computeMessageSize(4, element4);
                }
            }
            if (this.tag != null) {
                for (FunctionCall element5 : this.tag) {
                    size += CodedOutputByteBufferNano.computeMessageSize(5, element5);
                }
            }
            if (this.predicate != null) {
                for (FunctionCall element6 : this.predicate) {
                    size += CodedOutputByteBufferNano.computeMessageSize(6, element6);
                }
            }
            if (this.rule != null) {
                for (Rule element7 : this.rule) {
                    size += CodedOutputByteBufferNano.computeMessageSize(7, element7);
                }
            }
            if (!this.previewAuthCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.previewAuthCode);
            }
            if (!this.malwareScanAuthCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.malwareScanAuthCode);
            }
            if (!this.templateVersionSet.equals(TEMPLATE_VERSION_SET_DEFAULT)) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.templateVersionSet);
            }
            if (!this.version.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.version);
            }
            if (this.liveJsCacheOption != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(14, this.liveJsCacheOption);
            }
            if (this.reportingSampleRate != 0.0f) {
                size += CodedOutputByteBufferNano.computeFloatSize(15, this.reportingSampleRate);
            }
            if (this.usageContext != null && this.usageContext.length > 0) {
                int dataSize2 = 0;
                for (String element8 : this.usageContext) {
                    dataSize2 += CodedOutputByteBufferNano.computeStringSizeNoTag(element8);
                }
                size = size + dataSize2 + (this.usageContext.length * 2);
            }
            if (this.resourceFormatVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(17, this.resourceFormatVersion);
            }
            if (this.oBSOLETEEnableAutoEventTracking) {
                size += CodedOutputByteBufferNano.computeBoolSize(18, this.oBSOLETEEnableAutoEventTracking);
            }
            if (this.supplemental != null && this.supplemental.length > 0) {
                int dataSize3 = 0;
                for (String element9 : this.supplemental) {
                    dataSize3 += CodedOutputByteBufferNano.computeStringSizeNoTag(element9);
                }
                size = size + dataSize3 + (this.supplemental.length * 2);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public Resource mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            while (true) {
                int tag2 = input.readTag();
                switch (tag2) {
                    case 0:
                        break;
                    case 10:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        int i7 = this.key.length;
                        String[] newArray = new String[(i7 + arrayLength)];
                        System.arraycopy(this.key, 0, newArray, 0, i7);
                        this.key = newArray;
                        while (i7 < this.key.length - 1) {
                            this.key[i7] = input.readString();
                            input.readTag();
                            i7++;
                        }
                        this.key[i7] = input.readString();
                        continue;
                    case 18:
                        int arrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.value == null) {
                            i6 = 0;
                        } else {
                            i6 = this.value.length;
                        }
                        TypeSystem.Value[] newArray2 = new TypeSystem.Value[(i6 + arrayLength2)];
                        if (this.value != null) {
                            System.arraycopy(this.value, 0, newArray2, 0, i6);
                        }
                        this.value = newArray2;
                        while (i6 < this.value.length - 1) {
                            this.value[i6] = new TypeSystem.Value();
                            input.readMessage(this.value[i6]);
                            input.readTag();
                            i6++;
                        }
                        this.value[i6] = new TypeSystem.Value();
                        input.readMessage(this.value[i6]);
                        continue;
                    case 26:
                        int arrayLength3 = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.property == null) {
                            i5 = 0;
                        } else {
                            i5 = this.property.length;
                        }
                        Property[] newArray3 = new Property[(i5 + arrayLength3)];
                        if (this.property != null) {
                            System.arraycopy(this.property, 0, newArray3, 0, i5);
                        }
                        this.property = newArray3;
                        while (i5 < this.property.length - 1) {
                            this.property[i5] = new Property();
                            input.readMessage(this.property[i5]);
                            input.readTag();
                            i5++;
                        }
                        this.property[i5] = new Property();
                        input.readMessage(this.property[i5]);
                        continue;
                    case 34:
                        int arrayLength4 = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.macro == null) {
                            i4 = 0;
                        } else {
                            i4 = this.macro.length;
                        }
                        FunctionCall[] newArray4 = new FunctionCall[(i4 + arrayLength4)];
                        if (this.macro != null) {
                            System.arraycopy(this.macro, 0, newArray4, 0, i4);
                        }
                        this.macro = newArray4;
                        while (i4 < this.macro.length - 1) {
                            this.macro[i4] = new FunctionCall();
                            input.readMessage(this.macro[i4]);
                            input.readTag();
                            i4++;
                        }
                        this.macro[i4] = new FunctionCall();
                        input.readMessage(this.macro[i4]);
                        continue;
                    case 42:
                        int arrayLength5 = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.tag == null) {
                            i3 = 0;
                        } else {
                            i3 = this.tag.length;
                        }
                        FunctionCall[] newArray5 = new FunctionCall[(i3 + arrayLength5)];
                        if (this.tag != null) {
                            System.arraycopy(this.tag, 0, newArray5, 0, i3);
                        }
                        this.tag = newArray5;
                        while (i3 < this.tag.length - 1) {
                            this.tag[i3] = new FunctionCall();
                            input.readMessage(this.tag[i3]);
                            input.readTag();
                            i3++;
                        }
                        this.tag[i3] = new FunctionCall();
                        input.readMessage(this.tag[i3]);
                        continue;
                    case 50:
                        int arrayLength6 = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.predicate == null) {
                            i2 = 0;
                        } else {
                            i2 = this.predicate.length;
                        }
                        FunctionCall[] newArray6 = new FunctionCall[(i2 + arrayLength6)];
                        if (this.predicate != null) {
                            System.arraycopy(this.predicate, 0, newArray6, 0, i2);
                        }
                        this.predicate = newArray6;
                        while (i2 < this.predicate.length - 1) {
                            this.predicate[i2] = new FunctionCall();
                            input.readMessage(this.predicate[i2]);
                            input.readTag();
                            i2++;
                        }
                        this.predicate[i2] = new FunctionCall();
                        input.readMessage(this.predicate[i2]);
                        continue;
                    case 58:
                        int arrayLength7 = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.rule == null) {
                            i = 0;
                        } else {
                            i = this.rule.length;
                        }
                        Rule[] newArray7 = new Rule[(i + arrayLength7)];
                        if (this.rule != null) {
                            System.arraycopy(this.rule, 0, newArray7, 0, i);
                        }
                        this.rule = newArray7;
                        while (i < this.rule.length - 1) {
                            this.rule[i] = new Rule();
                            input.readMessage(this.rule[i]);
                            input.readTag();
                            i++;
                        }
                        this.rule[i] = new Rule();
                        input.readMessage(this.rule[i]);
                        continue;
                    case 74:
                        this.previewAuthCode = input.readString();
                        continue;
                    case 82:
                        this.malwareScanAuthCode = input.readString();
                        continue;
                    case 98:
                        this.templateVersionSet = input.readString();
                        continue;
                    case 106:
                        this.version = input.readString();
                        continue;
                    case 114:
                        this.liveJsCacheOption = new CacheOption();
                        input.readMessage(this.liveJsCacheOption);
                        continue;
                    case 125:
                        this.reportingSampleRate = input.readFloat();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD:
                        int arrayLength8 = WireFormatNano.getRepeatedFieldArrayLength(input, TransportMediator.KEYCODE_MEDIA_RECORD);
                        int i8 = this.usageContext.length;
                        String[] newArray8 = new String[(i8 + arrayLength8)];
                        System.arraycopy(this.usageContext, 0, newArray8, 0, i8);
                        this.usageContext = newArray8;
                        while (i8 < this.usageContext.length - 1) {
                            this.usageContext[i8] = input.readString();
                            input.readTag();
                            i8++;
                        }
                        this.usageContext[i8] = input.readString();
                        continue;
                    case 136:
                        this.resourceFormatVersion = input.readInt32();
                        continue;
                    case 144:
                        this.oBSOLETEEnableAutoEventTracking = input.readBool();
                        continue;
                    case 154:
                        int arrayLength9 = WireFormatNano.getRepeatedFieldArrayLength(input, 154);
                        int i9 = this.supplemental.length;
                        String[] newArray9 = new String[(i9 + arrayLength9)];
                        System.arraycopy(this.supplemental, 0, newArray9, 0, i9);
                        this.supplemental = newArray9;
                        while (i9 < this.supplemental.length - 1) {
                            this.supplemental[i9] = input.readString();
                            input.readTag();
                            i9++;
                        }
                        this.supplemental[i9] = input.readString();
                        continue;
                    default:
                        if (this.unknownFieldData == null) {
                            this.unknownFieldData = new ArrayList();
                        }
                        if (!WireFormatNano.storeUnknownField(this.unknownFieldData, input, tag2)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public static Resource parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Resource) MessageNano.mergeFrom(new Resource(), data);
        }

        public static Resource parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Resource().mergeFrom(input);
        }
    }

    public static final class GaExperimentRandom extends ExtendableMessageNano {
        public static final GaExperimentRandom[] EMPTY_ARRAY = new GaExperimentRandom[0];
        public String key = "";
        public long lifetimeInMilliseconds = 0;
        public long maxRandom = 2147483647L;
        public long minRandom = 0;
        public boolean retainOriginalValue = false;

        public final GaExperimentRandom clear() {
            this.key = "";
            this.minRandom = 0;
            this.maxRandom = 2147483647L;
            this.retainOriginalValue = false;
            this.lifetimeInMilliseconds = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GaExperimentRandom)) {
                return false;
            }
            GaExperimentRandom other = (GaExperimentRandom) o;
            if (this.key != null ? this.key.equals(other.key) : other.key == null) {
                if (this.minRandom == other.minRandom && this.maxRandom == other.maxRandom && this.retainOriginalValue == other.retainOriginalValue && this.lifetimeInMilliseconds == other.lifetimeInMilliseconds) {
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
            int hashCode = ((((((((((this.key == null ? 0 : this.key.hashCode()) + 527) * 31) + ((int) (this.minRandom ^ (this.minRandom >>> 32)))) * 31) + ((int) (this.maxRandom ^ (this.maxRandom >>> 32)))) * 31) + (this.retainOriginalValue ? 1 : 2)) * 31) + ((int) (this.lifetimeInMilliseconds ^ (this.lifetimeInMilliseconds >>> 32)))) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.key.equals("")) {
                output.writeString(1, this.key);
            }
            if (this.minRandom != 0) {
                output.writeInt64(2, this.minRandom);
            }
            if (this.maxRandom != 2147483647L) {
                output.writeInt64(3, this.maxRandom);
            }
            if (this.retainOriginalValue) {
                output.writeBool(4, this.retainOriginalValue);
            }
            if (this.lifetimeInMilliseconds != 0) {
                output.writeInt64(5, this.lifetimeInMilliseconds);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (!this.key.equals("")) {
                size = 0 + CodedOutputByteBufferNano.computeStringSize(1, this.key);
            }
            if (this.minRandom != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.minRandom);
            }
            if (this.maxRandom != 2147483647L) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.maxRandom);
            }
            if (this.retainOriginalValue) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.retainOriginalValue);
            }
            if (this.lifetimeInMilliseconds != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(5, this.lifetimeInMilliseconds);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public GaExperimentRandom mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        this.key = input.readString();
                        continue;
                    case 16:
                        this.minRandom = input.readInt64();
                        continue;
                    case 24:
                        this.maxRandom = input.readInt64();
                        continue;
                    case 32:
                        this.retainOriginalValue = input.readBool();
                        continue;
                    case 40:
                        this.lifetimeInMilliseconds = input.readInt64();
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

        public static GaExperimentRandom parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GaExperimentRandom) MessageNano.mergeFrom(new GaExperimentRandom(), data);
        }

        public static GaExperimentRandom parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GaExperimentRandom().mergeFrom(input);
        }
    }

    public static final class GaExperimentSupplemental extends ExtendableMessageNano {
        public static final GaExperimentSupplemental[] EMPTY_ARRAY = new GaExperimentSupplemental[0];
        public GaExperimentRandom[] experimentRandom = GaExperimentRandom.EMPTY_ARRAY;
        public TypeSystem.Value[] valueToClear = TypeSystem.Value.EMPTY_ARRAY;
        public TypeSystem.Value[] valueToPush = TypeSystem.Value.EMPTY_ARRAY;

        public final GaExperimentSupplemental clear() {
            this.valueToPush = TypeSystem.Value.EMPTY_ARRAY;
            this.valueToClear = TypeSystem.Value.EMPTY_ARRAY;
            this.experimentRandom = GaExperimentRandom.EMPTY_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GaExperimentSupplemental)) {
                return false;
            }
            GaExperimentSupplemental other = (GaExperimentSupplemental) o;
            if (Arrays.equals(this.valueToPush, other.valueToPush) && Arrays.equals(this.valueToClear, other.valueToClear) && Arrays.equals(this.experimentRandom, other.experimentRandom)) {
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
            if (this.valueToPush == null) {
                result = 17 * 31;
            } else {
                for (int i2 = 0; i2 < this.valueToPush.length; i2++) {
                    result = (result * 31) + (this.valueToPush[i2] == null ? 0 : this.valueToPush[i2].hashCode());
                }
            }
            if (this.valueToClear == null) {
                result *= 31;
            } else {
                for (int i3 = 0; i3 < this.valueToClear.length; i3++) {
                    result = (result * 31) + (this.valueToClear[i3] == null ? 0 : this.valueToClear[i3].hashCode());
                }
            }
            if (this.experimentRandom == null) {
                result *= 31;
            } else {
                for (int i4 = 0; i4 < this.experimentRandom.length; i4++) {
                    result = (result * 31) + (this.experimentRandom[i4] == null ? 0 : this.experimentRandom[i4].hashCode());
                }
            }
            int i5 = result * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return i5 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.valueToPush != null) {
                for (TypeSystem.Value element : this.valueToPush) {
                    output.writeMessage(1, element);
                }
            }
            if (this.valueToClear != null) {
                for (TypeSystem.Value element2 : this.valueToClear) {
                    output.writeMessage(2, element2);
                }
            }
            if (this.experimentRandom != null) {
                for (GaExperimentRandom element3 : this.experimentRandom) {
                    output.writeMessage(3, element3);
                }
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.valueToPush != null) {
                for (TypeSystem.Value element : this.valueToPush) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
            if (this.valueToClear != null) {
                for (TypeSystem.Value element2 : this.valueToClear) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, element2);
                }
            }
            if (this.experimentRandom != null) {
                for (GaExperimentRandom element3 : this.experimentRandom) {
                    size += CodedOutputByteBufferNano.computeMessageSize(3, element3);
                }
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public GaExperimentSupplemental mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int i;
            int i2;
            int i3;
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.valueToPush == null) {
                            i3 = 0;
                        } else {
                            i3 = this.valueToPush.length;
                        }
                        TypeSystem.Value[] newArray = new TypeSystem.Value[(i3 + arrayLength)];
                        if (this.valueToPush != null) {
                            System.arraycopy(this.valueToPush, 0, newArray, 0, i3);
                        }
                        this.valueToPush = newArray;
                        while (i3 < this.valueToPush.length - 1) {
                            this.valueToPush[i3] = new TypeSystem.Value();
                            input.readMessage(this.valueToPush[i3]);
                            input.readTag();
                            i3++;
                        }
                        this.valueToPush[i3] = new TypeSystem.Value();
                        input.readMessage(this.valueToPush[i3]);
                        continue;
                    case 18:
                        int arrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.valueToClear == null) {
                            i2 = 0;
                        } else {
                            i2 = this.valueToClear.length;
                        }
                        TypeSystem.Value[] newArray2 = new TypeSystem.Value[(i2 + arrayLength2)];
                        if (this.valueToClear != null) {
                            System.arraycopy(this.valueToClear, 0, newArray2, 0, i2);
                        }
                        this.valueToClear = newArray2;
                        while (i2 < this.valueToClear.length - 1) {
                            this.valueToClear[i2] = new TypeSystem.Value();
                            input.readMessage(this.valueToClear[i2]);
                            input.readTag();
                            i2++;
                        }
                        this.valueToClear[i2] = new TypeSystem.Value();
                        input.readMessage(this.valueToClear[i2]);
                        continue;
                    case 26:
                        int arrayLength3 = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.experimentRandom == null) {
                            i = 0;
                        } else {
                            i = this.experimentRandom.length;
                        }
                        GaExperimentRandom[] newArray3 = new GaExperimentRandom[(i + arrayLength3)];
                        if (this.experimentRandom != null) {
                            System.arraycopy(this.experimentRandom, 0, newArray3, 0, i);
                        }
                        this.experimentRandom = newArray3;
                        while (i < this.experimentRandom.length - 1) {
                            this.experimentRandom[i] = new GaExperimentRandom();
                            input.readMessage(this.experimentRandom[i]);
                            input.readTag();
                            i++;
                        }
                        this.experimentRandom[i] = new GaExperimentRandom();
                        input.readMessage(this.experimentRandom[i]);
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

        public static GaExperimentSupplemental parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GaExperimentSupplemental) MessageNano.mergeFrom(new GaExperimentSupplemental(), data);
        }

        public static GaExperimentSupplemental parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GaExperimentSupplemental().mergeFrom(input);
        }
    }

    public static final class Supplemental extends ExtendableMessageNano {
        public static final Supplemental[] EMPTY_ARRAY = new Supplemental[0];
        public GaExperimentSupplemental experimentSupplemental = null;
        public String name = "";
        public TypeSystem.Value value = null;

        public final Supplemental clear() {
            this.name = "";
            this.value = null;
            this.experimentSupplemental = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Supplemental)) {
                return false;
            }
            Supplemental other = (Supplemental) o;
            if (this.name != null ? this.name.equals(other.name) : other.name == null) {
                if (this.value != null ? this.value.equals(other.value) : other.value == null) {
                    if (this.experimentSupplemental != null ? this.experimentSupplemental.equals(other.experimentSupplemental) : other.experimentSupplemental == null) {
                        if (this.unknownFieldData == null) {
                            if (other.unknownFieldData == null) {
                                return true;
                            }
                        } else if (this.unknownFieldData.equals(other.unknownFieldData)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((this.name == null ? 0 : this.name.hashCode()) + 527) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31) + (this.experimentSupplemental == null ? 0 : this.experimentSupplemental.hashCode())) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals("")) {
                output.writeString(1, this.name);
            }
            if (this.value != null) {
                output.writeMessage(2, this.value);
            }
            if (this.experimentSupplemental != null) {
                output.writeMessage(3, this.experimentSupplemental);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (!this.name.equals("")) {
                size = 0 + CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.value != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.value);
            }
            if (this.experimentSupplemental != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.experimentSupplemental);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public Supplemental mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        this.name = input.readString();
                        continue;
                    case 18:
                        this.value = new TypeSystem.Value();
                        input.readMessage(this.value);
                        continue;
                    case 26:
                        this.experimentSupplemental = new GaExperimentSupplemental();
                        input.readMessage(this.experimentSupplemental);
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

        public static Supplemental parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Supplemental) MessageNano.mergeFrom(new Supplemental(), data);
        }

        public static Supplemental parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Supplemental().mergeFrom(input);
        }
    }

    public static final class SupplementedResource extends ExtendableMessageNano {
        public static final SupplementedResource[] EMPTY_ARRAY = new SupplementedResource[0];
        public String fingerprint = "";
        public Resource resource = null;
        public Supplemental[] supplemental = Supplemental.EMPTY_ARRAY;

        public final SupplementedResource clear() {
            this.supplemental = Supplemental.EMPTY_ARRAY;
            this.resource = null;
            this.fingerprint = "";
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SupplementedResource)) {
                return false;
            }
            SupplementedResource other = (SupplementedResource) o;
            if (Arrays.equals(this.supplemental, other.supplemental) && (this.resource != null ? this.resource.equals(other.resource) : other.resource == null) && (this.fingerprint != null ? this.fingerprint.equals(other.fingerprint) : other.fingerprint == null)) {
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
            if (this.supplemental == null) {
                result = 17 * 31;
            } else {
                for (int i2 = 0; i2 < this.supplemental.length; i2++) {
                    result = (result * 31) + (this.supplemental[i2] == null ? 0 : this.supplemental[i2].hashCode());
                }
            }
            int hashCode = ((((result * 31) + (this.resource == null ? 0 : this.resource.hashCode())) * 31) + (this.fingerprint == null ? 0 : this.fingerprint.hashCode())) * 31;
            if (this.unknownFieldData != null) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.supplemental != null) {
                for (Supplemental element : this.supplemental) {
                    output.writeMessage(1, element);
                }
            }
            if (this.resource != null) {
                output.writeMessage(2, this.resource);
            }
            if (!this.fingerprint.equals("")) {
                output.writeString(3, this.fingerprint);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0;
            if (this.supplemental != null) {
                for (Supplemental element : this.supplemental) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
            if (this.resource != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.resource);
            }
            if (!this.fingerprint.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.fingerprint);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public SupplementedResource mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int i;
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.supplemental == null) {
                            i = 0;
                        } else {
                            i = this.supplemental.length;
                        }
                        Supplemental[] newArray = new Supplemental[(i + arrayLength)];
                        if (this.supplemental != null) {
                            System.arraycopy(this.supplemental, 0, newArray, 0, i);
                        }
                        this.supplemental = newArray;
                        while (i < this.supplemental.length - 1) {
                            this.supplemental[i] = new Supplemental();
                            input.readMessage(this.supplemental[i]);
                            input.readTag();
                            i++;
                        }
                        this.supplemental[i] = new Supplemental();
                        input.readMessage(this.supplemental[i]);
                        continue;
                    case 18:
                        this.resource = new Resource();
                        input.readMessage(this.resource);
                        continue;
                    case 26:
                        this.fingerprint = input.readString();
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

        public static SupplementedResource parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SupplementedResource) MessageNano.mergeFrom(new SupplementedResource(), data);
        }

        public static SupplementedResource parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SupplementedResource().mergeFrom(input);
        }
    }
}
