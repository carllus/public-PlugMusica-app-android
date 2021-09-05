package com.google.tagmanager.protobuf.nano;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class Extension<T> {
    public final int fieldNumber;
    public Class<T> fieldType;
    public boolean isRepeatedField;
    public Class<T> listType;

    private Extension(int fieldNumber2, TypeLiteral<T> type) {
        this.fieldNumber = fieldNumber2;
        this.isRepeatedField = type.isList();
        this.fieldType = type.getTargetClass();
        this.listType = this.isRepeatedField ? type.getListType() : null;
    }

    public static <T> Extension<T> create(int fieldNumber2, TypeLiteral<T> type) {
        return new Extension<>(fieldNumber2, type);
    }

    public static <T> Extension<List<T>> createRepeated(int fieldNumber2, TypeLiteral<List<T>> type) {
        return new Extension<>(fieldNumber2, type);
    }

    public static abstract class TypeLiteral<T> {
        private final Type type;

        protected TypeLiteral() {
            Type superclass = getClass().getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter");
            }
            this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
        }

        /* access modifiers changed from: private */
        public boolean isList() {
            return this.type instanceof ParameterizedType;
        }

        /* access modifiers changed from: private */
        public Class<T> getListType() {
            return (Class) ((ParameterizedType) this.type).getRawType();
        }

        /* access modifiers changed from: private */
        public Class<T> getTargetClass() {
            if (isList()) {
                return (Class) ((ParameterizedType) this.type).getActualTypeArguments()[0];
            }
            return (Class) this.type;
        }
    }
}
