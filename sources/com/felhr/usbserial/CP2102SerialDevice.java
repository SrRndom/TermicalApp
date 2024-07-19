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
public class CP2102SerialDevice extends UsbSerialDevice {
    private static final String CLASS_ID = "CP2102SerialDevice";
    private static final int CP210X_SET_BREAK = 5;
    private static final int CP210x_BREAK_OFF = 0;
    private static final int CP210x_BREAK_ON = 1;
    private static final int CP210x_GET_COMM_STATUS = 16;
    private static final int CP210x_GET_LINE_CTL = 4;
    private static final int CP210x_GET_MDMSTS = 8;
    private static final int CP210x_IFC_ENABLE = 0;
    private static final int CP210x_LINE_CTL_DEFAULT = 2048;
    private static final int CP210x_MHS_ALL = 17;
    private static final int CP210x_MHS_DEFAULT = 0;
    private static final int CP210x_MHS_DTR = 1;
    private static final int CP210x_MHS_DTR_OFF = 256;
    private static final int CP210x_MHS_DTR_ON = 257;
    private static final int CP210x_MHS_RTS = 16;
    private static final int CP210x_MHS_RTS_OFF = 512;
    private static final int CP210x_MHS_RTS_ON = 514;
    private static final int CP210x_PURGE = 18;
    private static final int CP210x_PURGE_ALL = 15;
    private static final int CP210x_REQTYPE_DEVICE2HOST = 193;
    private static final int CP210x_REQTYPE_HOST2DEVICE = 65;
    private static final int CP210x_SET_BAUDDIV = 1;
    private static final int CP210x_SET_BAUDRATE = 30;
    private static final int CP210x_SET_CHARS = 25;
    private static final int CP210x_SET_FLOW = 19;
    private static final int CP210x_SET_LINE_CTL = 3;
    private static final int CP210x_SET_MHS = 7;
    private static final int CP210x_SET_XOFF = 10;
    private static final int CP210x_SET_XON = 9;
    private static final int CP210x_UART_DISABLE = 0;
    private static final int CP210x_UART_ENABLE = 1;
    private static final int CP210x_XOFF = 0;
    private static final int CP210x_XON = 0;
    private static final int DEFAULT_BAUDRATE = 9600;
    private UsbSerialInterface.UsbBreakCallback breakCallback;
    private UsbSerialInterface.UsbCTSCallback ctsCallback;
    private boolean ctsState;
    private UsbSerialInterface.UsbDSRCallback dsrCallback;
    private boolean dsrState;
    private boolean dtrDsrEnabled;
    private FlowControlThread flowControlThread;
    private UsbSerialInterface.UsbFrameCallback frameCallback;
    private UsbEndpoint inEndpoint;
    private final UsbInterface mInterface;
    private UsbEndpoint outEndpoint;
    private UsbSerialInterface.UsbOverrunCallback overrunCallback;
    private UsbSerialInterface.UsbParityCallback parityCallback;
    private boolean rtsCtsEnabled;

    public CP2102SerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        this(usbDevice, usbDeviceConnection, -1);
    }

    public CP2102SerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        super(usbDevice, usbDeviceConnection);
        this.rtsCtsEnabled = false;
        this.dtrDsrEnabled = false;
        this.ctsState = true;
        this.dsrState = true;
        this.mInterface = usbDevice.getInterface(i < 0 ? 0 : i);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean open() {
        if (openCP2102()) {
            SafeUsbRequest safeUsbRequest = new SafeUsbRequest();
            safeUsbRequest.initialize(this.connection, this.inEndpoint);
            restartWorkingThread();
            restartWriteThread();
            createFlowControlThread();
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
        setControlCommand(18, 15, null);
        setControlCommand(0, 0, null);
        killWorkingThread();
        killWriteThread();
        stopFlowControlThread();
        this.connection.releaseInterface(this.mInterface);
        this.isOpen = false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean syncOpen() {
        if (openCP2102()) {
            createFlowControlThread();
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
        setControlCommand(18, 15, null);
        setControlCommand(0, 0, null);
        stopFlowControlThread();
        this.connection.releaseInterface(this.mInterface);
        this.isOpen = false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setBaudRate(int i) {
        setControlCommand(30, 0, new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setDataBits(int i) {
        int i2;
        short ctl = (short) (getCTL() & (-3841));
        if (i == 5) {
            i2 = ctl | 1280;
        } else if (i == 6) {
            i2 = ctl | 1536;
        } else if (i == 7) {
            i2 = ctl | 1792;
        } else if (i != 8) {
            return;
        } else {
            i2 = ctl | 2048;
        }
        setControlCommand(3, (short) i2, null);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setStopBits(int i) {
        int i2;
        short ctl = (short) (getCTL() & (-4));
        if (i == 1) {
            i2 = ctl | 0;
        } else if (i == 2) {
            i2 = ctl | 2;
        } else if (i != 3) {
            return;
        } else {
            i2 = ctl | 1;
        }
        setControlCommand(3, (short) i2, null);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setParity(int i) {
        int i2;
        short ctl = (short) (getCTL() & (-241));
        if (i == 0) {
            i2 = ctl | 0;
        } else if (i == 1) {
            i2 = ctl | 16;
        } else if (i == 2) {
            i2 = ctl | 32;
        } else if (i == 3) {
            i2 = ctl | 48;
        } else if (i != 4) {
            return;
        } else {
            i2 = ctl | 64;
        }
        setControlCommand(3, (short) i2, null);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setFlowControl(int i) {
        if (i == 0) {
            byte[] bArr = {1, 0, 0, 0, 64, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 32, 0, 0};
            this.rtsCtsEnabled = false;
            this.dtrDsrEnabled = false;
            setControlCommand(19, 0, bArr);
            return;
        }
        if (i == 1) {
            byte[] bArr2 = {9, 0, 0, 0, 64, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 32, 0, 0};
            this.rtsCtsEnabled = true;
            this.dtrDsrEnabled = false;
            setControlCommand(19, 0, bArr2);
            setControlCommand(7, CP210x_MHS_RTS_ON, null);
            this.ctsState = (getCommStatus()[4] & 1) == 0;
            startFlowControlThread();
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            byte[] bArr3 = {1, 0, 0, 0, 67, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 32, 0, 0};
            setControlCommand(25, 0, new byte[]{0, 0, 0, 0, 17, 19});
            setControlCommand(19, 0, bArr3);
            return;
        }
        byte[] bArr4 = {17, 0, 0, 0, 64, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 32, 0, 0};
        this.dtrDsrEnabled = true;
        this.rtsCtsEnabled = false;
        setControlCommand(19, 0, bArr4);
        setControlCommand(7, 257, null);
        this.dsrState = (getCommStatus()[4] & 2) == 0;
        startFlowControlThread();
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setBreak(boolean z) {
        if (z) {
            setControlCommand(5, 1, null);
        } else {
            setControlCommand(5, 0, null);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setRTS(boolean z) {
        if (z) {
            setControlCommand(7, CP210x_MHS_RTS_ON, null);
        } else {
            setControlCommand(7, 512, null);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setDTR(boolean z) {
        if (z) {
            setControlCommand(7, 257, null);
        } else {
            setControlCommand(7, 256, null);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getCTS(UsbSerialInterface.UsbCTSCallback usbCTSCallback) {
        this.ctsCallback = usbCTSCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getDSR(UsbSerialInterface.UsbDSRCallback usbDSRCallback) {
        this.dsrCallback = usbDSRCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getBreak(UsbSerialInterface.UsbBreakCallback usbBreakCallback) {
        this.breakCallback = usbBreakCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getFrame(UsbSerialInterface.UsbFrameCallback usbFrameCallback) {
        this.frameCallback = usbFrameCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getOverrun(UsbSerialInterface.UsbOverrunCallback usbOverrunCallback) {
        this.overrunCallback = usbOverrunCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getParity(UsbSerialInterface.UsbParityCallback usbParityCallback) {
        this.parityCallback = usbParityCallback;
        startFlowControlThread();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class FlowControlThread extends AbstractWorkerThread {
        private final long time;

        private FlowControlThread() {
            this.time = 40L;
        }

        @Override // com.felhr.usbserial.AbstractWorkerThread
        public void doRun() {
            if (this.firstTime) {
                if (CP2102SerialDevice.this.rtsCtsEnabled && CP2102SerialDevice.this.ctsCallback != null) {
                    CP2102SerialDevice.this.ctsCallback.onCTSChanged(CP2102SerialDevice.this.ctsState);
                }
                if (CP2102SerialDevice.this.dtrDsrEnabled && CP2102SerialDevice.this.dsrCallback != null) {
                    CP2102SerialDevice.this.dsrCallback.onDSRChanged(CP2102SerialDevice.this.dsrState);
                }
                this.firstTime = false;
                return;
            }
            byte[] pollLines = pollLines();
            byte[] commStatus = CP2102SerialDevice.this.getCommStatus();
            if (CP2102SerialDevice.this.rtsCtsEnabled) {
                if (CP2102SerialDevice.this.ctsState != ((pollLines[0] & 16) == 16)) {
                    CP2102SerialDevice.this.ctsState = !r3.ctsState;
                    if (CP2102SerialDevice.this.ctsCallback != null) {
                        CP2102SerialDevice.this.ctsCallback.onCTSChanged(CP2102SerialDevice.this.ctsState);
                    }
                }
            }
            if (CP2102SerialDevice.this.dtrDsrEnabled) {
                if (CP2102SerialDevice.this.dsrState != ((pollLines[0] & 32) == 32)) {
                    CP2102SerialDevice.this.dsrState = !r0.dsrState;
                    if (CP2102SerialDevice.this.dsrCallback != null) {
                        CP2102SerialDevice.this.dsrCallback.onDSRChanged(CP2102SerialDevice.this.dsrState);
                    }
                }
            }
            if (CP2102SerialDevice.this.parityCallback != null && (commStatus[0] & 16) == 16) {
                CP2102SerialDevice.this.parityCallback.onParityError();
            }
            if (CP2102SerialDevice.this.frameCallback != null && (commStatus[0] & 2) == 2) {
                CP2102SerialDevice.this.frameCallback.onFramingError();
            }
            if (CP2102SerialDevice.this.breakCallback != null && (commStatus[0] & 1) == 1) {
                CP2102SerialDevice.this.breakCallback.onBreakInterrupt();
            }
            if (CP2102SerialDevice.this.overrunCallback != null) {
                if ((commStatus[0] & 4) == 4 || (commStatus[0] & 8) == 8) {
                    CP2102SerialDevice.this.overrunCallback.onOverrunError();
                }
            }
        }

        private byte[] pollLines() {
            synchronized (this) {
                try {
                    wait(40L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return CP2102SerialDevice.this.getModemState();
        }
    }

    private boolean openCP2102() {
        if (this.connection.claimInterface(this.mInterface, true)) {
            Log.i(CLASS_ID, "Interface succesfully claimed");
            int endpointCount = this.mInterface.getEndpointCount();
            for (int i = 0; i <= endpointCount - 1; i++) {
                UsbEndpoint endpoint = this.mInterface.getEndpoint(i);
                if (endpoint.getType() == 2 && endpoint.getDirection() == 128) {
                    this.inEndpoint = endpoint;
                } else {
                    this.outEndpoint = endpoint;
                }
            }
            if (setControlCommand(0, 1, null) < 0) {
                return false;
            }
            setBaudRate(DEFAULT_BAUDRATE);
            if (setControlCommand(3, 2048, null) < 0) {
                return false;
            }
            setFlowControl(0);
            return setControlCommand(7, 0, null) >= 0;
        }
        Log.i(CLASS_ID, "Interface could not be claimed");
        return false;
    }

    private void createFlowControlThread() {
        this.flowControlThread = new FlowControlThread();
    }

    private void startFlowControlThread() {
        if (this.flowControlThread.isAlive()) {
            return;
        }
        this.flowControlThread.start();
    }

    private void stopFlowControlThread() {
        FlowControlThread flowControlThread = this.flowControlThread;
        if (flowControlThread != null) {
            flowControlThread.stopThread();
            this.flowControlThread = null;
        }
    }

    private int setControlCommand(int i, int i2, byte[] bArr) {
        int controlTransfer = this.connection.controlTransfer(65, i, i2, this.mInterface.getId(), bArr, bArr != null ? bArr.length : 0, 0);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return controlTransfer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getModemState() {
        byte[] bArr = new byte[1];
        this.connection.controlTransfer(CP210x_REQTYPE_DEVICE2HOST, 8, 0, this.mInterface.getId(), bArr, 1, 0);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getCommStatus() {
        byte[] bArr = new byte[19];
        int controlTransfer = this.connection.controlTransfer(CP210x_REQTYPE_DEVICE2HOST, 16, 0, this.mInterface.getId(), bArr, 19, 0);
        Log.i(CLASS_ID, "Control Transfer Response (Comm status): " + String.valueOf(controlTransfer));
        return bArr;
    }

    private short getCTL() {
        byte[] bArr = new byte[2];
        int controlTransfer = this.connection.controlTransfer(CP210x_REQTYPE_DEVICE2HOST, 4, 0, this.mInterface.getId(), bArr, 2, 0);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return (short) ((bArr[0] & 255) | (bArr[1] << 8));
    }
}
