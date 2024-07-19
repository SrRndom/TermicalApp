package com.felhr.usbserial;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import okio.Buffer;

/* loaded from: classes.dex */
public class SerialBuffer {
    static final int DEFAULT_READ_BUFFER_SIZE = 2048;
    static final int MAX_BULK_BUFFER = 2048;
    private ByteBuffer readBuffer;
    private byte[] readBufferCompatible;
    private boolean debugging = false;
    private final SynchronizedBuffer writeBuffer = new SynchronizedBuffer();

    public SerialBuffer(boolean z) {
        if (z) {
            this.readBuffer = ByteBuffer.allocate(2048);
        } else {
            this.readBufferCompatible = new byte[2048];
        }
    }

    public void debug(boolean z) {
        this.debugging = z;
    }

    public ByteBuffer getReadBuffer() {
        ByteBuffer byteBuffer;
        synchronized (this) {
            byteBuffer = this.readBuffer;
        }
        return byteBuffer;
    }

    public byte[] getDataReceived() {
        byte[] bArr;
        synchronized (this) {
            int position = this.readBuffer.position();
            bArr = new byte[position];
            this.readBuffer.position(0);
            this.readBuffer.get(bArr, 0, position);
            if (this.debugging) {
                UsbSerialDebugger.printReadLogGet(bArr, true);
            }
        }
        return bArr;
    }

    public void clearReadBuffer() {
        synchronized (this) {
            this.readBuffer.clear();
        }
    }

    public byte[] getWriteBuffer() {
        return this.writeBuffer.get();
    }

    public void putWriteBuffer(byte[] bArr) {
        this.writeBuffer.put(bArr);
    }

    public byte[] getBufferCompatible() {
        return this.readBufferCompatible;
    }

    public byte[] getDataReceivedCompatible(int i) {
        return Arrays.copyOfRange(this.readBufferCompatible, 0, i);
    }

    /* loaded from: classes.dex */
    private class SynchronizedBuffer {
        private final Buffer buffer = new Buffer();

        SynchronizedBuffer() {
        }

        synchronized void put(byte[] bArr) {
            if (bArr != null) {
                if (bArr.length != 0) {
                    if (SerialBuffer.this.debugging) {
                        UsbSerialDebugger.printLogPut(bArr, true);
                    }
                    this.buffer.write(bArr);
                    notify();
                }
            }
        }

        synchronized byte[] get() {
            byte[] readByteArray;
            if (this.buffer.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            if (this.buffer.size() <= 2048) {
                readByteArray = this.buffer.readByteArray();
            } else {
                try {
                    readByteArray = this.buffer.readByteArray(2048L);
                } catch (EOFException e2) {
                    e2.printStackTrace();
                    return new byte[0];
                }
            }
            if (SerialBuffer.this.debugging) {
                UsbSerialDebugger.printLogGet(readByteArray, true);
            }
            return readByteArray;
        }
    }
}
