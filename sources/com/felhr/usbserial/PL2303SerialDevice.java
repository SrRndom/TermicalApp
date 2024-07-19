package com.felhr.usbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.util.Log;
import com.felhr.usbserial.UsbSerialInterface;
import com.felhr.utils.SafeUsbRequest;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class PL2303SerialDevice extends UsbSerialDevice {
    private static final String CLASS_ID = "PL2303SerialDevice";
    private static final int PL2303_REQTYPE_DEVICE2HOST_VENDOR = 192;
    private static final int PL2303_REQTYPE_HOST2DEVICE = 33;
    private static final int PL2303_REQTYPE_HOST2DEVICE_VENDOR = 64;
    private static final int PL2303_SET_CONTROL_REQUEST = 34;
    private static final int PL2303_SET_LINE_CODING = 32;
    private static final int PL2303_VENDOR_WRITE_REQUEST = 1;
    private final byte[] defaultSetLine;
    private UsbEndpoint inEndpoint;
    private final UsbInterface mInterface;
    private UsbEndpoint outEndpoint;

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getBreak(UsbSerialInterface.UsbBreakCallback usbBreakCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getCTS(UsbSerialInterface.UsbCTSCallback usbCTSCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getDSR(UsbSerialInterface.UsbDSRCallback usbDSRCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getFrame(UsbSerialInterface.UsbFrameCallback usbFrameCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getOverrun(UsbSerialInterface.UsbOverrunCallback usbOverrunCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getParity(UsbSerialInterface.UsbParityCallback usbParityCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setBreak(boolean z) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setDTR(boolean z) {
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setFlowControl(int i) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setRTS(boolean z) {
    }

    public PL2303SerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        this(usbDevice, usbDeviceConnection, -1);
    }

    public PL2303SerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        super(usbDevice, usbDeviceConnection);
        this.defaultSetLine = new byte[]{ByteCompanionObject.MIN_VALUE, 37, 0, 0, 0, 0, 8};
        if (i > 1) {
            throw new IllegalArgumentException("Multi-interface PL2303 devices not supported!");
        }
        this.mInterface = usbDevice.getInterface(i < 0 ? 0 : i);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean open() {
        if (openPL2303()) {
            SafeUsbRequest safeUsbRequest = new SafeUsbRequest();
            safeUsbRequest.initialize(this.connection, this.inEndpoint);
            restartWorkingThread();
            restartWriteThread();
            setThreadsParams(safeUsbRequest, this.outEndpoint);
            this.asyncMode = true;
            this.isOpen = true;
            return true;
        }
        this.isOpen = false;
        return false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void close() {
        killWorkingThread();
        killWriteThread();
        this.connection.releaseInterface(this.mInterface);
        this.isOpen = false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean syncOpen() {
        if (openPL2303()) {
            setSyncParams(this.inEndpoint, this.outEndpoint);
            this.asyncMode = false;
            this.isOpen = true;
            this.inputStream = new SerialInputStream(this);
            this.outputStream = new SerialOutputStream(this);
            return true;
        }
        this.isOpen = false;
        return false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void syncClose() {
        this.connection.releaseInterface(this.mInterface);
        this.isOpen = false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setBaudRate(int i) {
        byte[] bArr = {(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
        byte b = bArr[0];
        byte[] bArr2 = this.defaultSetLine;
        if (b == bArr2[0] && bArr[1] == bArr2[1] && bArr[2] == bArr2[2] && bArr[3] == bArr2[3]) {
            return;
        }
        bArr2[0] = bArr[0];
        bArr2[1] = bArr[1];
        bArr2[2] = bArr[2];
        bArr2[3] = bArr[3];
        setControlCommand(33, 32, 0, 0, bArr2);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setDataBits(int i) {
        if (i == 5) {
            byte[] bArr = this.defaultSetLine;
            if (bArr[6] != 5) {
                bArr[6] = 5;
                setControlCommand(33, 32, 0, 0, bArr);
                return;
            }
            return;
        }
        if (i == 6) {
            byte[] bArr2 = this.defaultSetLine;
            if (bArr2[6] != 6) {
                bArr2[6] = 6;
                setControlCommand(33, 32, 0, 0, bArr2);
                return;
            }
            return;
        }
        if (i == 7) {
            byte[] bArr3 = this.defaultSetLine;
            if (bArr3[6] != 7) {
                bArr3[6] = 7;
                setControlCommand(33, 32, 0, 0, bArr3);
                return;
            }
            return;
        }
        if (i != 8) {
            return;
        }
        byte[] bArr4 = this.defaultSetLine;
        if (bArr4[6] != 8) {
            bArr4[6] = 8;
            setControlCommand(33, 32, 0, 0, bArr4);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setStopBits(int i) {
        if (i == 1) {
            byte[] bArr = this.defaultSetLine;
            if (bArr[4] != 0) {
                bArr[4] = 0;
                setControlCommand(33, 32, 0, 0, bArr);
                return;
            }
            return;
        }
        if (i == 2) {
            byte[] bArr2 = this.defaultSetLine;
            if (bArr2[4] != 2) {
                bArr2[4] = 2;
                setControlCommand(33, 32, 0, 0, bArr2);
                return;
            }
            return;
        }
        if (i != 3) {
            return;
        }
        byte[] bArr3 = this.defaultSetLine;
        if (bArr3[4] != 1) {
            bArr3[4] = 1;
            setControlCommand(33, 32, 0, 0, bArr3);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setParity(int i) {
        if (i == 0) {
            byte[] bArr = this.defaultSetLine;
            if (bArr[5] != 0) {
                bArr[5] = 0;
                setControlCommand(33, 32, 0, 0, bArr);
                return;
            }
            return;
        }
        if (i == 1) {
            byte[] bArr2 = this.defaultSetLine;
            if (bArr2[5] != 1) {
                bArr2[5] = 1;
                setControlCommand(33, 32, 0, 0, bArr2);
                return;
            }
            return;
        }
        if (i == 2) {
            byte[] bArr3 = this.defaultSetLine;
            if (bArr3[5] != 2) {
                bArr3[5] = 2;
                setControlCommand(33, 32, 0, 0, bArr3);
                return;
            }
            return;
        }
        if (i == 3) {
            byte[] bArr4 = this.defaultSetLine;
            if (bArr4[5] != 3) {
                bArr4[5] = 3;
                setControlCommand(33, 32, 0, 0, bArr4);
                return;
            }
            return;
        }
        if (i != 4) {
            return;
        }
        byte[] bArr5 = this.defaultSetLine;
        if (bArr5[5] != 4) {
            bArr5[5] = 4;
            setControlCommand(33, 32, 0, 0, bArr5);
        }
    }

    private boolean openPL2303() {
        if (this.connection.claimInterface(this.mInterface, true)) {
            Log.i(CLASS_ID, "Interface succesfully claimed");
            int endpointCount = this.mInterface.getEndpointCount();
            for (int i = 0; i <= endpointCount - 1; i++) {
                UsbEndpoint endpoint = this.mInterface.getEndpoint(i);
                if (endpoint.getType() == 2 && endpoint.getDirection() == 128) {
                    this.inEndpoint = endpoint;
                } else if (endpoint.getType() == 2 && endpoint.getDirection() == 0) {
                    this.outEndpoint = endpoint;
                }
            }
            byte[] bArr = new byte[1];
            return setControlCommand(PL2303_REQTYPE_DEVICE2HOST_VENDOR, 1, 33924, 0, bArr) >= 0 && setControlCommand(64, 1, 1028, 0, null) >= 0 && setControlCommand(PL2303_REQTYPE_DEVICE2HOST_VENDOR, 1, 33924, 0, bArr) >= 0 && setControlCommand(PL2303_REQTYPE_DEVICE2HOST_VENDOR, 1, 33667, 0, bArr) >= 0 && setControlCommand(PL2303_REQTYPE_DEVICE2HOST_VENDOR, 1, 33924, 0, bArr) >= 0 && setControlCommand(64, 1, 1028, 1, null) >= 0 && setControlCommand(PL2303_REQTYPE_DEVICE2HOST_VENDOR, 1, 33924, 0, bArr) >= 0 && setControlCommand(PL2303_REQTYPE_DEVICE2HOST_VENDOR, 1, 33667, 0, bArr) >= 0 && setControlCommand(64, 1, 0, 1, null) >= 0 && setControlCommand(64, 1, 1, 0, null) >= 0 && setControlCommand(64, 1, 2, 68, null) >= 0 && setControlCommand(33, 34, 3, 0, null) >= 0 && setControlCommand(33, 32, 0, 0, this.defaultSetLine) >= 0 && setControlCommand(64, 1, 1285, 4881, null) >= 0;
        }
        Log.i(CLASS_ID, "Interface could not be claimed");
        return false;
    }

    private int setControlCommand(int i, int i2, int i3, int i4, byte[] bArr) {
        int controlTransfer = this.connection.controlTransfer(i, i2, i3, i4, bArr, bArr != null ? bArr.length : 0, 0);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return controlTransfer;
    }
}
