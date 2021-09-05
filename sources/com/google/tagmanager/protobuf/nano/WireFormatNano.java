package com.google.tagmanager.protobuf.nano;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class WireFormatNano {
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final Boolean[] EMPTY_BOOLEAN_REF_ARRAY = new Boolean[0];
    public static final byte[] EMPTY_BYTES = new byte[0];
    public static final byte[][] EMPTY_BYTES_ARRAY = new byte[0][];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final Double[] EMPTY_DOUBLE_REF_ARRAY = new Double[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final Float[] EMPTY_FLOAT_REF_ARRAY = new Float[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INT_REF_ARRAY = new Integer[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final Long[] EMPTY_LONG_REF_ARRAY = new Long[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    static final int MESSAGE_SET_ITEM = 1;
    static final int MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
    static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
    static final int MESSAGE_SET_MESSAGE = 3;
    static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);
    static final int MESSAGE_SET_TYPE_ID = 2;
    static final int MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
    static final int TAG_TYPE_BITS = 3;
    static final int TAG_TYPE_MASK = 7;
    static final int WIRETYPE_END_GROUP = 4;
    static final int WIRETYPE_FIXED32 = 5;
    static final int WIRETYPE_FIXED64 = 1;
    static final int WIRETYPE_LENGTH_DELIMITED = 2;
    static final int WIRETYPE_START_GROUP = 3;
    static final int WIRETYPE_VARINT = 0;

    private WireFormatNano() {
    }

    static int getTagWireType(int tag) {
        return tag & 7;
    }

    public static int getTagFieldNumber(int tag) {
        return tag >>> 3;
    }

    static int makeTag(int fieldNumber, int wireType) {
        return (fieldNumber << 3) | wireType;
    }

    public static boolean parseUnknownField(CodedInputByteBufferNano input, int tag) throws IOException {
        return input.skipField(tag);
    }

    public static boolean storeUnknownField(List<UnknownFieldData> data, CodedInputByteBufferNano input, int tag) throws IOException {
        int startPos = input.getPosition();
        boolean skip = input.skipField(tag);
        data.add(new UnknownFieldData(tag, input.getData(startPos, input.getPosition() - startPos)));
        return skip;
    }

    public static final int getRepeatedFieldArrayLength(CodedInputByteBufferNano input, int tag) throws IOException {
        int arrayLength = 1;
        int startPos = input.getPosition();
        input.skipField(tag);
        while (input.getBytesUntilLimit() > 0 && input.readTag() == tag) {
            input.skipField(tag);
            arrayLength++;
        }
        input.rewindToPosition(startPos);
        return arrayLength;
    }

    public static <T> T getExtension(Extension<T> extension, List<UnknownFieldData> unknownFields) {
        if (unknownFields == null) {
            return null;
        }
        List<UnknownFieldData> dataForField = new ArrayList<>();
        for (UnknownFieldData data : unknownFields) {
            if (getTagFieldNumber(data.tag) == extension.fieldNumber) {
                dataForField.add(data);
            }
        }
        if (dataForField.isEmpty()) {
            return null;
        }
        if (!extension.isRepeatedField) {
            return readData(extension.fieldType, dataForField.get(dataForField.size() - 1).bytes);
        }
        List<Object> result = new ArrayList<>(dataForField.size());
        for (UnknownFieldData data2 : dataForField) {
            result.add(readData(extension.fieldType, data2.bytes));
        }
        return extension.listType.cast(result);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a8, code lost:
        throw new java.lang.IllegalArgumentException("Error creating instance of class " + r6, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a9, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b1, code lost:
        throw new java.lang.IllegalArgumentException("Error reading extension field", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b2, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00cb, code lost:
        throw new java.lang.IllegalArgumentException("Error creating instance of class " + r6, r1);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:5:0x000d, B:27:0x0080] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T readData(java.lang.Class<T> r6, byte[] r7) {
        /*
            int r3 = r7.length
            if (r3 != 0) goto L_0x0005
            r3 = 0
        L_0x0004:
            return r3
        L_0x0005:
            com.google.tagmanager.protobuf.nano.CodedInputByteBufferNano r0 = com.google.tagmanager.protobuf.nano.CodedInputByteBufferNano.newInstance(r7)
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            if (r6 != r3) goto L_0x0016
            java.lang.String r3 = r0.readString()     // Catch:{ IOException -> 0x00a9 }
            java.lang.Object r3 = r6.cast(r3)     // Catch:{ IOException -> 0x00a9 }
            goto L_0x0004
        L_0x0016:
            java.lang.Class<java.lang.Integer> r3 = java.lang.Integer.class
            if (r6 != r3) goto L_0x0027
            int r3 = r0.readInt32()     // Catch:{ IOException -> 0x00a9 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00a9 }
            java.lang.Object r3 = r6.cast(r3)     // Catch:{ IOException -> 0x00a9 }
            goto L_0x0004
        L_0x0027:
            java.lang.Class<java.lang.Long> r3 = java.lang.Long.class
            if (r6 != r3) goto L_0x0038
            long r4 = r0.readInt64()     // Catch:{ IOException -> 0x00a9 }
            java.lang.Long r3 = java.lang.Long.valueOf(r4)     // Catch:{ IOException -> 0x00a9 }
            java.lang.Object r3 = r6.cast(r3)     // Catch:{ IOException -> 0x00a9 }
            goto L_0x0004
        L_0x0038:
            java.lang.Class<java.lang.Boolean> r3 = java.lang.Boolean.class
            if (r6 != r3) goto L_0x0049
            boolean r3 = r0.readBool()     // Catch:{ IOException -> 0x00a9 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ IOException -> 0x00a9 }
            java.lang.Object r3 = r6.cast(r3)     // Catch:{ IOException -> 0x00a9 }
            goto L_0x0004
        L_0x0049:
            java.lang.Class<java.lang.Float> r3 = java.lang.Float.class
            if (r6 != r3) goto L_0x005a
            float r3 = r0.readFloat()     // Catch:{ IOException -> 0x00a9 }
            java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{ IOException -> 0x00a9 }
            java.lang.Object r3 = r6.cast(r3)     // Catch:{ IOException -> 0x00a9 }
            goto L_0x0004
        L_0x005a:
            java.lang.Class<java.lang.Double> r3 = java.lang.Double.class
            if (r6 != r3) goto L_0x006b
            double r4 = r0.readDouble()     // Catch:{ IOException -> 0x00a9 }
            java.lang.Double r3 = java.lang.Double.valueOf(r4)     // Catch:{ IOException -> 0x00a9 }
            java.lang.Object r3 = r6.cast(r3)     // Catch:{ IOException -> 0x00a9 }
            goto L_0x0004
        L_0x006b:
            java.lang.Class<byte[]> r3 = byte[].class
            if (r6 != r3) goto L_0x0078
            byte[] r3 = r0.readBytes()     // Catch:{ IOException -> 0x00a9 }
            java.lang.Object r3 = r6.cast(r3)     // Catch:{ IOException -> 0x00a9 }
            goto L_0x0004
        L_0x0078:
            java.lang.Class<com.google.tagmanager.protobuf.nano.MessageNano> r3 = com.google.tagmanager.protobuf.nano.MessageNano.class
            boolean r3 = r3.isAssignableFrom(r6)     // Catch:{ IOException -> 0x00a9 }
            if (r3 == 0) goto L_0x00cc
            java.lang.Object r2 = r6.newInstance()     // Catch:{ IllegalAccessException -> 0x008f, InstantiationException -> 0x00b2 }
            com.google.tagmanager.protobuf.nano.MessageNano r2 = (com.google.tagmanager.protobuf.nano.MessageNano) r2     // Catch:{ IllegalAccessException -> 0x008f, InstantiationException -> 0x00b2 }
            r0.readMessage(r2)     // Catch:{ IllegalAccessException -> 0x008f, InstantiationException -> 0x00b2 }
            java.lang.Object r3 = r6.cast(r2)     // Catch:{ IllegalAccessException -> 0x008f, InstantiationException -> 0x00b2 }
            goto L_0x0004
        L_0x008f:
            r1 = move-exception
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x00a9 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a9 }
            r4.<init>()     // Catch:{ IOException -> 0x00a9 }
            java.lang.String r5 = "Error creating instance of class "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IOException -> 0x00a9 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ IOException -> 0x00a9 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00a9 }
            r3.<init>(r4, r1)     // Catch:{ IOException -> 0x00a9 }
            throw r3     // Catch:{ IOException -> 0x00a9 }
        L_0x00a9:
            r1 = move-exception
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Error reading extension field"
            r3.<init>(r4, r1)
            throw r3
        L_0x00b2:
            r1 = move-exception
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x00a9 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a9 }
            r4.<init>()     // Catch:{ IOException -> 0x00a9 }
            java.lang.String r5 = "Error creating instance of class "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IOException -> 0x00a9 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ IOException -> 0x00a9 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00a9 }
            r3.<init>(r4, r1)     // Catch:{ IOException -> 0x00a9 }
            throw r3     // Catch:{ IOException -> 0x00a9 }
        L_0x00cc:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x00a9 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a9 }
            r4.<init>()     // Catch:{ IOException -> 0x00a9 }
            java.lang.String r5 = "Unhandled extension field type: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IOException -> 0x00a9 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ IOException -> 0x00a9 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00a9 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x00a9 }
            throw r3     // Catch:{ IOException -> 0x00a9 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.tagmanager.protobuf.nano.WireFormatNano.readData(java.lang.Class, byte[]):java.lang.Object");
    }

    public static <T> void setExtension(Extension<T> extension, T value, List<UnknownFieldData> unknownFields) {
        Iterator<UnknownFieldData> i = unknownFields.iterator();
        while (i.hasNext()) {
            if (extension.fieldNumber == getTagFieldNumber(i.next().tag)) {
                i.remove();
            }
        }
        if (value != null) {
            if (value instanceof List) {
                for (Object item : (List) value) {
                    unknownFields.add(write(extension.fieldNumber, item));
                }
                return;
            }
            unknownFields.add(write(extension.fieldNumber, value));
        }
    }

    private static UnknownFieldData write(int fieldNumber, Object object) {
        byte[] data;
        int tag;
        Class<?> clazz = object.getClass();
        if (clazz == String.class) {
            try {
                String str = (String) object;
                data = new byte[CodedOutputByteBufferNano.computeStringSizeNoTag(str)];
                CodedOutputByteBufferNano.newInstance(data).writeStringNoTag(str);
                tag = makeTag(fieldNumber, 2);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        } else if (clazz == Integer.class) {
            Integer integer = (Integer) object;
            data = new byte[CodedOutputByteBufferNano.computeInt32SizeNoTag(integer.intValue())];
            CodedOutputByteBufferNano.newInstance(data).writeInt32NoTag(integer.intValue());
            tag = makeTag(fieldNumber, 0);
        } else if (clazz == Long.class) {
            Long longValue = (Long) object;
            data = new byte[CodedOutputByteBufferNano.computeInt64SizeNoTag(longValue.longValue())];
            CodedOutputByteBufferNano.newInstance(data).writeInt64NoTag(longValue.longValue());
            tag = makeTag(fieldNumber, 0);
        } else if (clazz == Boolean.class) {
            Boolean boolValue = (Boolean) object;
            data = new byte[CodedOutputByteBufferNano.computeBoolSizeNoTag(boolValue.booleanValue())];
            CodedOutputByteBufferNano.newInstance(data).writeBoolNoTag(boolValue.booleanValue());
            tag = makeTag(fieldNumber, 0);
        } else if (clazz == Float.class) {
            Float floatValue = (Float) object;
            data = new byte[CodedOutputByteBufferNano.computeFloatSizeNoTag(floatValue.floatValue())];
            CodedOutputByteBufferNano.newInstance(data).writeFloatNoTag(floatValue.floatValue());
            tag = makeTag(fieldNumber, 5);
        } else if (clazz == Double.class) {
            Double doubleValue = (Double) object;
            data = new byte[CodedOutputByteBufferNano.computeDoubleSizeNoTag(doubleValue.doubleValue())];
            CodedOutputByteBufferNano.newInstance(data).writeDoubleNoTag(doubleValue.doubleValue());
            tag = makeTag(fieldNumber, 1);
        } else if (clazz == byte[].class) {
            byte[] byteArrayValue = (byte[]) object;
            data = new byte[CodedOutputByteBufferNano.computeByteArraySizeNoTag(byteArrayValue)];
            CodedOutputByteBufferNano.newInstance(data).writeByteArrayNoTag(byteArrayValue);
            tag = makeTag(fieldNumber, 2);
        } else if (MessageNano.class.isAssignableFrom(clazz)) {
            MessageNano messageValue = (MessageNano) object;
            int messageSize = messageValue.getSerializedSize();
            data = new byte[(messageSize + CodedOutputByteBufferNano.computeRawVarint32Size(messageSize))];
            CodedOutputByteBufferNano buffer = CodedOutputByteBufferNano.newInstance(data);
            buffer.writeRawVarint32(messageSize);
            buffer.writeRawBytes(MessageNano.toByteArray(messageValue));
            tag = makeTag(fieldNumber, 2);
        } else {
            throw new IllegalArgumentException("Unhandled extension field type: " + clazz);
        }
        return new UnknownFieldData(tag, data);
    }

    public static int computeWireSize(List<UnknownFieldData> unknownFields) {
        if (unknownFields == null) {
            return 0;
        }
        int size = 0;
        for (UnknownFieldData unknownField : unknownFields) {
            size = size + CodedOutputByteBufferNano.computeRawVarint32Size(unknownField.tag) + unknownField.bytes.length;
        }
        return size;
    }

    public static void writeUnknownFields(List<UnknownFieldData> unknownFields, CodedOutputByteBufferNano outBuffer) throws IOException {
        if (unknownFields != null) {
            for (UnknownFieldData data : unknownFields) {
                outBuffer.writeTag(getTagFieldNumber(data.tag), getTagWireType(data.tag));
                outBuffer.writeRawBytes(data.bytes);
            }
        }
    }
}
