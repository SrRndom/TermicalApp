package com.felhr.usbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.util.Log;
import com.felhr.usbserial.UsbSerialInterface;
import com.felhr.utils.SafeUsbRequest;

/* loaded from: classes.dex */
public class CDCSerialDevice extends UsbSerialDevice {
    private static final int CDC_CONTROL_LINE_OFF = 0;
    private static final int CDC_CONTROL_LINE_ON = 3;
    private static final byte[] CDC_DEFAULT_LINE_CODING = {0, -62, 1, 0, 0, 0, 8};
    private static final int CDC_GET_LINE_CODING = 33;
    private static final int CDC_REQTYPE_DEVICE2HOST = 161;
    private static final int CDC_REQTYPE_HOST2DEVICE = 33;
    private static final int CDC_SET_CONTROL_LINE_STATE = 34;
    private static final int CDC_SET_CONTROL_LINE_STATE_DTR = 1;
    private static final int CDC_SET_CONTROL_LINE_STATE_RTS = 2;
    private static final int CDC_SET_LINE_CODING = 32;
    private static final String CLASS_ID = "CDCSerialDevice";
    private int cdcControl;
    private int controlLineState;
    private UsbEndpoint inEndpoint;
    private int initialBaudRate;
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

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setFlowControl(int i) {
    }

    public CDCSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        this(usbDevice, usbDeviceConnection, -1);
    }

    public CDCSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        super(usbDevice, usbDeviceConnection);
        this.cdcControl = 0;
        this.initialBaudRate = 0;
        this.controlLineState = 3;
        this.cdcControl = findFirstControl(usbDevice);
        this.mInterface = usbDevice.getInterface(i < 0 ? findFirstCDC(usbDevice) : i);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice
    public void setInitialBaudRate(int i) {
        this.initialBaudRate = i;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice
    public int getInitialBaudRate() {
        return this.initialBaudRate;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean open() {
        if (openCDC()) {
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
        setControlCommand(34, 0, null);
        killWorkingThread();
        killWriteThread();
        this.connection.releaseInterface(this.mInterface);
        this.connection.close();
        this.isOpen = false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean syncOpen() {
        if (openCDC()) {
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
        setControlCommand(34, 0, null);
        this.connection.releaseInterface(this.mInterface);
        this.connection.close();
        this.isOpen = false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setBaudRate(int i) {
        byte[] lineCoding = getLineCoding();
        lineCoding[0] = (byte) (i & 255);
        lineCoding[1] = (byte) ((i >> 8) & 255);
        lineCoding[2] = (byte) ((i >> 16) & 255);
        lineCoding[3] = (byte) ((i >> 24) & 255);
        setControlCommand(32, 0, lineCoding);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setDataBits(int i) {
        byte[] lineCoding = getLineCoding();
        if (i == 5) {
            lineCoding[6] = 5;
        } else if (i == 6) {
            lineCoding[6] = 6;
        } else if (i == 7) {
            lineCoding[6] = 7;
        } else if (i != 8) {
            return;
        } else {
            lineCoding[6] = 8;
        }
        setControlCommand(32, 0, lineCoding);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setStopBits(int i) {
        byte[] lineCoding = getLineCoding();
        if (i == 1) {
            lineCoding[4] = 0;
        } else if (i == 2) {
            lineCoding[4] = 2;
        } else if (i != 3) {
            return;
        } else {
            lineCoding[4] = 1;
        }
        setControlCommand(32, 0, lineCoding);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setParity(int i) {
        byte[] lineCoding = getLineCoding();
        if (i == 0) {
            lineCoding[5] = 0;
        } else if (i == 1) {
            lineCoding[5] = 1;
        } else if (i == 2) {
            lineCoding[5] = 2;
        } else if (i == 3) {
            lineCoding[5] = 3;
        } else if (i != 4) {
            return;
        } else {
            lineCoding[5] = 4;
        }
        setControlCommand(32, 0, lineCoding);
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setRTS(boolean z) {
        if (z) {
            this.controlLineState |= 2;
        } else {
            this.controlLineState &= -3;
        }
        setControlCommand(34, this.controlLineState, null);
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setDTR(boolean z) {
        if (z) {
            this.controlLineState |= 1;
        } else {
            this.controlLineState &= -2;
        }
        setControlCommand(34, this.controlLineState, null);
    }

    private boolean openCDC() {
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
            if (this.outEndpoint == null || this.inEndpoint == null) {
                Log.i(CLASS_ID, "Interface does not have an IN or OUT interface");
                return false;
            }
            setControlCommand(32, 0, getInitialLineCoding());
            setControlCommand(34, 3, null);
            return true;
        }
        Log.i(CLASS_ID, "Interface could not be claimed");
        return false;
    }

    protected byte[] getInitialLineCoding() {
        int initialBaudRate = getInitialBaudRate();
        if (initialBaudRate > 0) {
            byte[] bArr = (byte[]) CDC_DEFAULT_LINE_CODING.clone();
            for (int i = 0; i < 4; i++) {
                bArr[i] = (byte) ((initialBaudRate >> (i * 8)) & 255);
            }
            return bArr;
        }
        return CDC_DEFAULT_LINE_CODING;
    }

    private int setControlCommand(int i, int i2, byte[] bArr) {
        int controlTransfer = this.connection.controlTransfer(33, i, i2, this.cdcControl, bArr, bArr != null ? bArr.length : 0, 0);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return controlTransfer;
    }

    private byte[] getLineCoding() {
        byte[] bArr = new byte[7];
        int controlTransfer = this.connection.controlTransfer(CDC_REQTYPE_DEVICE2HOST, 33, 0, this.cdcControl, bArr, 7, 0);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return bArr;
    }

    private static int findFirstCDC(UsbDevice usbDevice) {
        int interfaceCount = usbDevice.getInterfaceCount();
        for (int i = 0; i < interfaceCount; i++) {
            if (usbDevice.getInterface(i).getInterfaceClass() == 10) {
                return i;
            }
        }
        Log.i(CLASS_ID, "There is no CDC class interface");
        return -1;
    }

    private static int findFirstControl(UsbDevice usbDevice) {
        int interfaceCount = usbDevice.getInterfaceCount();
        for (int i = 0; i < interfaceCount; i++) {
            if (usbDevice.getInterface(i).getInterfaceClass() == 2) {
                Log.i(CLASS_ID, "Using CDC control interface " + String.valueOf(i));
                return i;
            }
        }
        Log.i(CLASS_ID, "There is no CDC control interface");
        return 0;
    }
}
