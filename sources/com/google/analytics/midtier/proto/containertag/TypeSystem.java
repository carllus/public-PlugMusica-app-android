package com.google.analytics.midtier.proto.containertag;

import com.google.tagmanager.protobuf.nano.CodedInputByteBufferNano;
import com.google.tagmanager.protobuf.nano.CodedOutputByteBufferNano;
import com.google.tagmanager.protobuf.nano.ExtendableMessageNano;
import com.google.tagmanager.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.tagmanager.protobuf.nano.MessageNano;
import com.google.tagmanager.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public interface TypeSystem {

    public static final class Value extends ExtendableMessageNano {
        public static final Value[] EMPTY_ARRAY = new Value[0];
        public boolean boolean_ = false;
        public boolean containsReferences = false;
        public int[] escaping = WireFormatNano.EMPTY_INT_ARRAY;
        public String functionId = "";
        public long integer = 0;
        public Value[] listItem = EMPTY_ARRAY;
        public String macroReference = "";
        public Value[] mapKey = EMPTY_ARRAY;
        public Value[] mapValue = EMPTY_ARRAY;
        public String string = "";
        public String tagReference = "";
        public Value[] templateToken = EMPTY_ARRAY;
        public int type = 1;

        public interface Escaping {
            public static final int CONVERT_JS_VALUE_TO_EXPRESSION = 16;
            public static final int ESCAPE_CSS_STRING = 10;
            public static final int ESCAPE_HTML = 1;
            public static final int ESCAPE_HTML_ATTRIBUTE = 3;
            public static final int ESCAPE_HTML_ATTRIBUTE_NOSPACE = 4;
            public static final int ESCAPE_HTML_RCDATA = 2;
            public static final int ESCAPE_JS_REGEX = 9;
            public static final int ESCAPE_JS_STRING = 7;
            public static final int ESCAPE_JS_VALUE = 8;
            public static final int ESCAPE_URI = 12;
            public static final int FILTER_CSS_VALUE = 11;
            public static final int FILTER_HTML_ATTRIBUTES = 6;
            public static final int FILTER_HTML_ELEMENT_NAME = 5;
            public static final int FILTER_NORMALIZE_URI = 14;
            public static final int NORMALIZE_URI = 13;
            public static final int NO_AUTOESCAPE = 15;
            public static final int TEXT = 17;
        }

        public interface Type {
            public static final int BOOLEAN = 8;
            public static final int FUNCTION_ID = 5;
            public static final int INTEGER = 6;
            public static final int LIST = 2;
            public static final int MACRO_REFERENCE = 4;
            public static final int MAP = 3;
            public static final int STRING = 1;
            public static final int TAG_REFERENCE = 9;
            public static final int TEMPLATE = 7;
        }

        public final Value clear() {
            this.type = 1;
            this.string = "";
            this.listItem = EMPTY_ARRAY;
            this.mapKey = EMPTY_ARRAY;
            this.mapValue = EMPTY_ARRAY;
            this.macroReference = "";
            this.functionId = "";
            this.integer = 0;
            this.boolean_ = false;
            this.templateToken = EMPTY_ARRAY;
            this.tagReference = "";
            this.escaping = WireFormatNano.EMPTY_INT_ARRAY;
            this.containsReferences = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Value)) {
                return false;
            }
            Value other = (Value) o;
            if (this.type == other.type && (this.string != null ? this.string.equals(other.string) : other.string == null) && Arrays.equals(this.listItem, other.listItem) && Arrays.equals(this.mapKey, other.mapKey) && Arrays.equals(this.mapValue, other.mapValue) && (this.macroReference != null ? this.macroReference.equals(other.macroReference) : other.macroReference == null) && (this.functionId != null ? this.functionId.equals(other.functionId) : other.functionId == null) && this.integer == other.integer && this.boolean_ == other.boolean_ && Arrays.equals(this.templateToken, other.templateToken) && (this.tagReference != null ? this.tagReference.equals(other.tagReference) : other.tagReference == null) && Arrays.equals(this.escaping, other.escaping) && this.containsReferences == other.containsReferences) {
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
            int i3 = 0;
            int result = ((this.type + 527) * 31) + (this.string == null ? 0 : this.string.hashCode());
            if (this.listItem == null) {
                result *= 31;
            } else {
                for (int i4 = 0; i4 < this.listItem.length; i4++) {
                    result = (result * 31) + (this.listItem[i4] == null ? 0 : this.listItem[i4].hashCode());
                }
            }
            if (this.mapKey == null) {
                result *= 31;
            } else {
                for (int i5 = 0; i5 < this.mapKey.length; i5++) {
                    result = (result * 31) + (this.mapKey[i5] == null ? 0 : this.mapKey[i5].hashCode());
                }
            }
            if (this.mapValue == null) {
                result *= 31;
            } else {
                for (int i6 = 0; i6 < this.mapValue.length; i6++) {
                    result = (result * 31) + (this.mapValue[i6] == null ? 0 : this.mapValue[i6].hashCode());
                }
            }
            int hashCode = ((((((result * 31) + (this.macroReference == null ? 0 : this.macroReference.hashCode())) * 31) + (this.functionId == null ? 0 : this.functionId.hashCode())) * 31) + ((int) (this.integer ^ (this.integer >>> 32)))) * 31;
            if (this.boolean_) {
                i = 1;
            } else {
                i = 2;
            }
            int result2 = hashCode + i;
            if (this.templateToken == null) {
                result2 *= 31;
            } else {
                for (int i7 = 0; i7 < this.templateToken.length; i7++) {
                    result2 = (result2 * 31) + (this.templateToken[i7] == null ? 0 : this.templateToken[i7].hashCode());
                }
            }
            int result3 = (result2 * 31) + (this.tagReference == null ? 0 : this.tagReference.hashCode());
            if (this.escaping == null) {
                result3 *= 31;
            } else {
                for (int i8 : this.escaping) {
                    result3 = (result3 * 31) + i8;
                }
            }
            int i9 = result3 * 31;
            if (!this.containsReferences) {
                i2 = 2;
            }
            int i10 = (i9 + i2) * 31;
            if (this.unknownFieldData != null) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i10 + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            output.writeInt32(1, this.type);
            if (!this.string.equals("")) {
                output.writeString(2, this.string);
            }
            if (this.listItem != null) {
                for (Value element : this.listItem) {
                    output.writeMessage(3, element);
                }
            }
            if (this.mapKey != null) {
                for (Value element2 : this.mapKey) {
                    output.writeMessage(4, element2);
                }
            }
            if (this.mapValue != null) {
                for (Value element3 : this.mapValue) {
                    output.writeMessage(5, element3);
                }
            }
            if (!this.macroReference.equals("")) {
                output.writeString(6, this.macroReference);
            }
            if (!this.functionId.equals("")) {
                output.writeString(7, this.functionId);
            }
            if (this.integer != 0) {
                output.writeInt64(8, this.integer);
            }
            if (this.containsReferences) {
                output.writeBool(9, this.containsReferences);
            }
            if (this.escaping != null && this.escaping.length > 0) {
                for (int element4 : this.escaping) {
                    output.writeInt32(10, element4);
                }
            }
            if (this.templateToken != null) {
                for (Value element5 : this.templateToken) {
                    output.writeMessage(11, element5);
                }
            }
            if (this.boolean_) {
                output.writeBool(12, this.boolean_);
            }
            if (!this.tagReference.equals("")) {
                output.writeString(13, this.tagReference);
            }
            WireFormatNano.writeUnknownFields(this.unknownFieldData, output);
        }

        public int getSerializedSize() {
            int size = 0 + CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            if (!this.string.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.string);
            }
            if (this.listItem != null) {
                for (Value element : this.listItem) {
                    size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                }
            }
            if (this.mapKey != null) {
                for (Value element2 : this.mapKey) {
                    size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                }
            }
            if (this.mapValue != null) {
                for (Value element3 : this.mapValue) {
                    size += CodedOutputByteBufferNano.computeMessageSize(5, element3);
                }
            }
            if (!this.macroReference.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.macroReference);
            }
            if (!this.functionId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.functionId);
            }
            if (this.integer != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(8, this.integer);
            }
            if (this.containsReferences) {
                size += CodedOutputByteBufferNano.computeBoolSize(9, this.containsReferences);
            }
            if (this.escaping != null && this.escaping.length > 0) {
                int dataSize = 0;
                for (int element4 : this.escaping) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element4);
                }
                size = size + dataSize + (this.escaping.length * 1);
            }
            if (this.templateToken != null) {
                for (Value element5 : this.templateToken) {
                    size += CodedOutputByteBufferNano.computeMessageSize(11, element5);
                }
            }
            if (this.boolean_) {
                size += CodedOutputByteBufferNano.computeBoolSize(12, this.boolean_);
            }
            if (!this.tagReference.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.tagReference);
            }
            int size2 = size + WireFormatNano.computeWireSize(this.unknownFieldData);
            this.cachedSize = size2;
            return size2;
        }

        public Value mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int i;
            int i2;
            int i3;
            int i4;
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        int temp = input.readInt32();
                        if (temp != 1 && temp != 2 && temp != 3 && temp != 4 && temp != 5 && temp != 6 && temp != 7 && temp != 8 && temp != 9) {
                            this.type = 1;
                            break;
                        } else {
                            this.type = temp;
                            continue;
                        }
                        break;
                    case 18:
                        this.string = input.readString();
                        continue;
                    case 26:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.listItem == null) {
                            i4 = 0;
                        } else {
                            i4 = this.listItem.length;
                        }
                        Value[] newArray = new Value[(i4 + arrayLength)];
                        if (this.listItem != null) {
                            System.arraycopy(this.listItem, 0, newArray, 0, i4);
                        }
                        this.listItem = newArray;
                        while (i4 < this.listItem.length - 1) {
                            this.listItem[i4] = new Value();
                            input.readMessage(this.listItem[i4]);
                            input.readTag();
                            i4++;
                        }
                        this.listItem[i4] = new Value();
                        input.readMessage(this.listItem[i4]);
                        continue;
                    case 34:
                        int arrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.mapKey == null) {
                            i3 = 0;
                        } else {
                            i3 = this.mapKey.length;
                        }
                        Value[] newArray2 = new Value[(i3 + arrayLength2)];
                        if (this.mapKey != null) {
                            System.arraycopy(this.mapKey, 0, newArray2, 0, i3);
                        }
                        this.mapKey = newArray2;
                        while (i3 < this.mapKey.length - 1) {
                            this.mapKey[i3] = new Value();
                            input.readMessage(this.mapKey[i3]);
                            input.readTag();
                            i3++;
                        }
                        this.mapKey[i3] = new Value();
                        input.readMessage(this.mapKey[i3]);
                        continue;
                    case 42:
                        int arrayLength3 = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.mapValue == null) {
                            i2 = 0;
                        } else {
                            i2 = this.mapValue.length;
                        }
                        Value[] newArray3 = new Value[(i2 + arrayLength3)];
                        if (this.mapValue != null) {
                            System.arraycopy(this.mapValue, 0, newArray3, 0, i2);
                        }
                        this.mapValue = newArray3;
                        while (i2 < this.mapValue.length - 1) {
                            this.mapValue[i2] = new Value();
                            input.readMessage(this.mapValue[i2]);
                            input.readTag();
                            i2++;
                        }
                        this.mapValue[i2] = new Value();
                        input.readMessage(this.mapValue[i2]);
                        continue;
                    case 50:
                        this.macroReference = input.readString();
                        continue;
                    case 58:
                        this.functionId = input.readString();
                        continue;
                    case 64:
                        this.integer = input.readInt64();
                        continue;
                    case 72:
                        this.containsReferences = input.readBool();
                        continue;
                    case 80:
                        int arrayLength4 = WireFormatNano.getRepeatedFieldArrayLength(input, 80);
                        int i5 = this.escaping.length;
                        int[] newArray4 = new int[(i5 + arrayLength4)];
                        System.arraycopy(this.escaping, 0, newArray4, 0, i5);
                        this.escaping = newArray4;
                        while (i5 < this.escaping.length - 1) {
                            this.escaping[i5] = input.readInt32();
                            input.readTag();
                            i5++;
                        }
                        this.escaping[i5] = input.readInt32();
                        continue;
                    case 90:
                        int arrayLength5 = WireFormatNano.getRepeatedFieldArrayLength(input, 90);
                        if (this.templateToken == null) {
                            i = 0;
                        } else {
                            i = this.templateToken.length;
                        }
                        Value[] newArray5 = new Value[(i + arrayLength5)];
                        if (this.templateToken != null) {
                            System.arraycopy(this.templateToken, 0, newArray5, 0, i);
                        }
                        this.templateToken = newArray5;
                        while (i < this.templateToken.length - 1) {
                            this.templateToken[i] = new Value();
                            input.readMessage(this.templateToken[i]);
                            input.readTag();
                            i++;
                        }
                        this.templateToken[i] = new Value();
                        input.readMessage(this.templateToken[i]);
                        continue;
                    case 96:
                        this.boolean_ = input.readBool();
                        continue;
                    case 106:
                        this.tagReference = input.readString();
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

        public static Value parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Value) MessageNano.mergeFrom(new Value(), data);
        }

        public static Value parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Value().mergeFrom(input);
        }
    }
}
