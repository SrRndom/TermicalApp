package com.felhr.usbserial;

import java.io.OutputStream;

/* loaded from: classes.dex */
public class SerialOutputStream extends OutputStream {
    protected final UsbSerialInterface device;
    private int timeout = 0;

    public SerialOutputStream(UsbSerialInterface usbSerialInterface) {
        this.device = usbSerialInterface;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.device.syncWrite(new byte[]{(byte) i}, this.timeout);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.device.syncWrite(bArr, this.timeout);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Offset must be >= 0");
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Length must positive");
        }
        if (i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException("off + length greater than buffer length");
        }
        if (i == 0 && i2 == bArr.length) {
            write(bArr);
        } else {
            this.device.syncWrite(bArr, i, i2, this.timeout);
        }
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }
}
