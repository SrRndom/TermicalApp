package com.felhr.usbserial;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class SerialInputStream extends InputStream {
    private final byte[] buffer;
    private int bufferSize;
    protected final UsbSerialInterface device;
    private int maxBufferSize;
    private int pointer;
    private int timeout;

    public SerialInputStream(UsbSerialInterface usbSerialInterface) {
        this.timeout = 0;
        this.maxBufferSize = 16384;
        this.device = usbSerialInterface;
        this.buffer = new byte[16384];
        this.pointer = 0;
        this.bufferSize = -1;
    }

    public SerialInputStream(UsbSerialInterface usbSerialInterface, int i) {
        this.timeout = 0;
        this.maxBufferSize = 16384;
        this.device = usbSerialInterface;
        this.maxBufferSize = i;
        this.buffer = new byte[i];
        this.pointer = 0;
        this.bufferSize = -1;
    }

    @Override // java.io.InputStream
    public int read() {
        int checkFromBuffer = checkFromBuffer();
        if (checkFromBuffer >= 0) {
            return checkFromBuffer;
        }
        int syncRead = this.device.syncRead(this.buffer, this.timeout);
        if (syncRead < 0) {
            return -1;
        }
        this.bufferSize = syncRead;
        byte[] bArr = this.buffer;
        int i = this.pointer;
        this.pointer = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return this.device.syncRead(bArr, this.timeout);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Offset must be >= 0");
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Length must positive");
        }
        if (i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException("Length greater than b.length - off");
        }
        if (i == 0 && i2 == bArr.length) {
            return read(bArr);
        }
        return this.device.syncRead(bArr, i, i2, this.timeout);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        int i = this.bufferSize;
        if (i > 0) {
            return i - this.pointer;
        }
        return 0;
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    private int checkFromBuffer() {
        int i;
        int i2 = this.bufferSize;
        if (i2 > 0 && (i = this.pointer) < i2) {
            byte[] bArr = this.buffer;
            this.pointer = i + 1;
            return bArr[i] & 255;
        }
        this.pointer = 0;
        this.bufferSize = -1;
        return -1;
    }
}
