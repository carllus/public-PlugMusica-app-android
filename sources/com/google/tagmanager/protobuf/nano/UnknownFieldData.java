package com.google.tagmanager.protobuf.nano;

import java.util.Arrays;

public final class UnknownFieldData {
    final byte[] bytes;
    final int tag;

    UnknownFieldData(int tag2, byte[] bytes2) {
        this.tag = tag2;
        this.bytes = bytes2;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UnknownFieldData)) {
            return false;
        }
        UnknownFieldData other = (UnknownFieldData) o;
        if (this.tag != other.tag || !Arrays.equals(this.bytes, other.bytes)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.tag + 527;
        for (byte b : this.bytes) {
            result = (result * 31) + b;
        }
        return result;
    }
}
