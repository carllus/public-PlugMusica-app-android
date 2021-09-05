package com.squareup.picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

final class MarkableInputStream extends InputStream {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private long defaultMark;

    /* renamed from: in */
    private final InputStream f940in;
    private long limit;
    private long offset;
    private long reset;

    public MarkableInputStream(InputStream in) {
        this(in, 4096);
    }

    public MarkableInputStream(InputStream in, int size) {
        this.defaultMark = -1;
        this.f940in = !in.markSupported() ? new BufferedInputStream(in, size) : in;
    }

    public void mark(int readLimit) {
        this.defaultMark = savePosition(readLimit);
    }

    public long savePosition(int readLimit) {
        long offsetLimit = this.offset + ((long) readLimit);
        if (this.limit < offsetLimit) {
            setLimit(offsetLimit);
        }
        return this.offset;
    }

    private void setLimit(long limit2) {
        try {
            if (this.reset >= this.offset || this.offset > this.limit) {
                this.reset = this.offset;
                this.f940in.mark((int) (limit2 - this.offset));
            } else {
                this.f940in.reset();
                this.f940in.mark((int) (limit2 - this.reset));
                skip(this.reset, this.offset);
            }
            this.limit = limit2;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to mark: " + e);
        }
    }

    public void reset() throws IOException {
        reset(this.defaultMark);
    }

    public void reset(long token) throws IOException {
        if (this.offset > this.limit || token < this.reset) {
            throw new IOException("Cannot reset");
        }
        this.f940in.reset();
        skip(this.reset, token);
        this.offset = token;
    }

    private void skip(long current, long target) throws IOException {
        while (current < target) {
            long skipped = this.f940in.skip(target - current);
            if (skipped == 0) {
                if (read() != -1) {
                    skipped = 1;
                } else {
                    return;
                }
            }
            current += skipped;
        }
    }

    public int read() throws IOException {
        int result = this.f940in.read();
        if (result != -1) {
            this.offset++;
        }
        return result;
    }

    public int read(byte[] buffer) throws IOException {
        int count = this.f940in.read(buffer);
        if (count != -1) {
            this.offset += (long) count;
        }
        return count;
    }

    public int read(byte[] buffer, int offset2, int length) throws IOException {
        int count = this.f940in.read(buffer, offset2, length);
        if (count != -1) {
            this.offset += (long) count;
        }
        return count;
    }

    public long skip(long byteCount) throws IOException {
        long skipped = this.f940in.skip(byteCount);
        this.offset += skipped;
        return skipped;
    }

    public int available() throws IOException {
        return this.f940in.available();
    }

    public void close() throws IOException {
        this.f940in.close();
    }

    public boolean markSupported() {
        return this.f940in.markSupported();
    }
}
