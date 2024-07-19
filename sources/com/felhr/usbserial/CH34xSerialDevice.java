package com.felhr.usbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.util.Log;
import com.felhr.usbserial.UsbSerialInterface;
import com.felhr.utils.SafeUsbRequest;

/* loaded from: classes.dex */
public class CH34xSerialDevice extends UsbSerialDevice {
    private static final int CH341_NBREAK_BITS_REG1 = 1;
    private static final int CH341_NBREAK_BITS_REG2 = 64;
    private static final int CH341_REG_BREAK1 = 5;
    private static final int CH341_REG_BREAK2 = 24;
    private static final int CH341_REQ_READ_REG = 149;
    private static final int CH341_REQ_WRITE_REG = 154;
    private static final int CH34X_115200_1312 = 52355;
    private static final int CH34X_1200_0f2c = 59;
    private static final int CH34X_1200_1312 = 45697;
    private static final int CH34X_1228800_0f2c = 33;
    private static final int CH34X_1228800_1312 = 64259;
    private static final int CH34X_19200_0f2c_rest = 7;
    private static final int CH34X_19200_1312 = 55682;
    private static final int CH34X_2000000_0f2c = 2;
    private static final int CH34X_2000000_1312 = 64771;
    private static final int CH34X_230400_1312 = 59011;
    private static final int CH34X_2400_0f2c = 30;
    private static final int CH34X_2400_1312 = 55681;
    private static final int CH34X_300_0f2c = 235;
    private static final int CH34X_300_1312 = 55680;
    private static final int CH34X_38400_1312 = 25731;
    private static final int CH34X_460800_1312 = 62339;
    private static final int CH34X_4800_0f2c = 15;
    private static final int CH34X_4800_1312 = 25730;
    private static final int CH34X_57600_1312 = 39043;
    private static final int CH34X_600_0f2c = 118;
    private static final int CH34X_600_1312 = 25729;
    private static final int CH34X_921600_1312 = 62343;
    private static final int CH34X_9600_0f2c = 8;
    private static final int CH34X_9600_1312 = 45698;
    private static final int CH34X_FLOW_CONTROL_DSR_DTR = 514;
    private static final int CH34X_FLOW_CONTROL_NONE = 0;
    private static final int CH34X_FLOW_CONTROL_RTS_CTS = 257;
    private static final int CH34X_PARITY_EVEN = 219;
    private static final int CH34X_PARITY_MARK = 235;
    private static final int CH34X_PARITY_NONE = 195;
    private static final int CH34X_PARITY_ODD = 203;
    private static final int CH34X_PARITY_SPACE = 251;
    private static final String CLASS_ID = "CH34xSerialDevice";
    private static final int DEFAULT_BAUDRATE = 9600;
    private static final int REQTYPE_HOST_FROM_DEVICE = 192;
    private static final int REQTYPE_HOST_TO_DEVICE = 64;
    private UsbSerialInterface.UsbCTSCallback ctsCallback;
    private boolean ctsState;
    private UsbSerialInterface.UsbDSRCallback dsrCallback;
    private boolean dsrState;
    private boolean dtr;
    private boolean dtrDsrEnabled;
    private FlowControlThread flowControlThread;
    private UsbEndpoint inEndpoint;
    private UsbInterface mInterface;
    private UsbEndpoint outEndpoint;
    private boolean rts;
    private boolean rtsCtsEnabled;

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getBreak(UsbSerialInterface.UsbBreakCallback usbBreakCallback) {
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
    public void setDataBits(int i) {
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setStopBits(int i) {
    }

    public CH34xSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        super(usbDevice, usbDeviceConnection);
        this.dtr = false;
        this.rts = false;
        this.ctsState = false;
        this.dsrState = false;
    }

    public CH34xSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        super(usbDevice, usbDeviceConnection);
        this.dtr = false;
        this.rts = false;
        this.ctsState = false;
        this.dsrState = false;
        this.rtsCtsEnabled = false;
        this.dtrDsrEnabled = false;
        this.mInterface = usbDevice.getInterface(i < 0 ? 0 : i);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean open() {
        if (openCH34X()) {
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
        killWorkingThread();
        killWriteThread();
        stopFlowControlThread();
        this.connection.releaseInterface(this.mInterface);
        this.isOpen = false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean syncOpen() {
        if (openCH34X()) {
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
        stopFlowControlThread();
        this.connection.releaseInterface(this.mInterface);
        this.isOpen = false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setBaudRate(int i) {
        if (i <= 300) {
            if (setBaudRate(CH34X_300_1312, 235) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 300 && i <= 600) {
            if (setBaudRate(CH34X_600_1312, 118) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 600 && i <= 1200) {
            if (setBaudRate(CH34X_1200_1312, 59) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 1200 && i <= 2400) {
            if (setBaudRate(CH34X_2400_1312, 30) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 2400 && i <= 4800) {
            if (setBaudRate(CH34X_4800_1312, 15) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 4800 && i <= DEFAULT_BAUDRATE) {
            if (setBaudRate(CH34X_9600_1312, 8) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > DEFAULT_BAUDRATE && i <= 19200) {
            if (setBaudRate(CH34X_19200_1312, 7) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 19200 && i <= 38400) {
            if (setBaudRate(CH34X_38400_1312, 7) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 38400 && i <= 57600) {
            if (setBaudRate(CH34X_57600_1312, 7) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 57600 && i <= 115200) {
            if (setBaudRate(CH34X_115200_1312, 7) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 115200 && i <= 230400) {
            if (setBaudRate(CH34X_230400_1312, 7) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 230400 && i <= 460800) {
            if (setBaudRate(CH34X_460800_1312, 7) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
                return;
            }
            return;
        }
        if (i > 460800 && i <= 921600) {
            if (setBaudRate(CH34X_921600_1312, 7) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
            }
        } else if (i > 921600 && i <= 1228800) {
            if (setBaudRate(CH34X_1228800_1312, 33) == -1) {
                Log.i(CLASS_ID, "SetBaudRate failed!");
            }
        } else {
            if (i <= 1228800 || i > 2000000 || setBaudRate(CH34X_2000000_1312, 2) != -1) {
                return;
            }
            Log.i(CLASS_ID, "SetBaudRate failed!");
        }
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setParity(int i) {
        if (i == 0) {
            setCh340xParity(CH34X_PARITY_NONE);
            return;
        }
        if (i == 1) {
            setCh340xParity(CH34X_PARITY_ODD);
            return;
        }
        if (i == 2) {
            setCh340xParity(CH34X_PARITY_EVEN);
        } else if (i == 3) {
            setCh340xParity(235);
        } else {
            if (i != 4) {
                return;
            }
            setCh340xParity(CH34X_PARITY_SPACE);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setFlowControl(int i) {
        if (i == 0) {
            this.rtsCtsEnabled = false;
            this.dtrDsrEnabled = false;
            setCh340xFlow(0);
        } else {
            if (i == 1) {
                this.rtsCtsEnabled = true;
                this.dtrDsrEnabled = false;
                setCh340xFlow(257);
                this.ctsState = checkCTS();
                startFlowControlThread();
                return;
            }
            if (i != 2) {
                return;
            }
            this.rtsCtsEnabled = false;
            this.dtrDsrEnabled = true;
            setCh340xFlow(CH34X_FLOW_CONTROL_DSR_DTR);
            this.dsrState = checkDSR();
            startFlowControlThread();
        }
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setRTS(boolean z) {
        this.rts = z;
        writeHandshakeByte();
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setDTR(boolean z) {
        this.dtr = z;
        writeHandshakeByte();
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getCTS(UsbSerialInterface.UsbCTSCallback usbCTSCallback) {
        this.ctsCallback = usbCTSCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getDSR(UsbSerialInterface.UsbDSRCallback usbDSRCallback) {
        this.dsrCallback = usbDSRCallback;
    }

    private boolean openCH34X() {
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
            return init() == 0;
        }
        Log.i(CLASS_ID, "Interface could not be claimed");
        return false;
    }

    private int init() {
        if (setControlCommandOut(161, 49820, 45753, null) < 0) {
            Log.i(CLASS_ID, "init failed! #1");
            return -1;
        }
        if (setControlCommandOut(164, 223, 0, null) < 0) {
            Log.i(CLASS_ID, "init failed! #2");
            return -1;
        }
        if (setControlCommandOut(164, 159, 0, null) < 0) {
            Log.i(CLASS_ID, "init failed! #3");
            return -1;
        }
        if (checkState("init #4", CH341_REQ_READ_REG, 1798, new int[]{159, 238}) == -1) {
            return -1;
        }
        if (setControlCommandOut(CH341_REQ_WRITE_REG, 10023, 0, null) < 0) {
            Log.i(CLASS_ID, "init failed! #5");
            return -1;
        }
        if (setControlCommandOut(CH341_REQ_WRITE_REG, 4882, CH34X_9600_1312, null) < 0) {
            Log.i(CLASS_ID, "init failed! #6");
            return -1;
        }
        if (setControlCommandOut(CH341_REQ_WRITE_REG, 3884, 8, null) < 0) {
            Log.i(CLASS_ID, "init failed! #7");
            return -1;
        }
        if (setControlCommandOut(CH341_REQ_WRITE_REG, 9496, CH34X_PARITY_NONE, null) < 0) {
            Log.i(CLASS_ID, "init failed! #8");
            return -1;
        }
        if (checkState("init #9", CH341_REQ_READ_REG, 1798, new int[]{159, 238}) == -1) {
            return -1;
        }
        if (setControlCommandOut(CH341_REQ_WRITE_REG, 10023, 0, null) >= 0) {
            return 0;
        }
        Log.i(CLASS_ID, "init failed! #10");
        return -1;
    }

    private int setBaudRate(int i, int i2) {
        return (setControlCommandOut(CH341_REQ_WRITE_REG, 4882, i, null) >= 0 && setControlCommandOut(CH341_REQ_WRITE_REG, 3884, i2, null) >= 0 && checkState("set_baud_rate", CH341_REQ_READ_REG, 1798, new int[]{159, 238}) != -1 && setControlCommandOut(CH341_REQ_WRITE_REG, 10023, 0, null) >= 0) ? 0 : -1;
    }

    private int setCh340xParity(int i) {
        return (setControlCommandOut(CH341_REQ_WRITE_REG, 9496, i, null) >= 0 && checkState("set_parity", CH341_REQ_READ_REG, 1798, new int[]{159, 238}) != -1 && setControlCommandOut(CH341_REQ_WRITE_REG, 10023, 0, null) >= 0) ? 0 : -1;
    }

    private int setCh340xFlow(int i) {
        return (checkState("set_flow_control", CH341_REQ_READ_REG, 1798, new int[]{159, 238}) == -1 || setControlCommandOut(CH341_REQ_WRITE_REG, 10023, i, null) == -1) ? -1 : 0;
    }

    private int checkState(String str, int i, int i2, int[] iArr) {
        int controlCommandIn = setControlCommandIn(i, i2, 0, new byte[iArr.length]);
        if (controlCommandIn == iArr.length) {
            return 0;
        }
        Log.i(CLASS_ID, "Expected " + iArr.length + " bytes, but get " + controlCommandIn + " [" + str + "]");
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkCTS() {
        byte[] bArr = new byte[2];
        int controlCommandIn = setControlCommandIn(CH341_REQ_READ_REG, 1798, 0, bArr);
        if (controlCommandIn == 2) {
            return (bArr[0] & 1) == 0;
        }
        Log.i(CLASS_ID, "Expected 2 bytes, but get " + controlCommandIn);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkDSR() {
        byte[] bArr = new byte[2];
        int controlCommandIn = setControlCommandIn(CH341_REQ_READ_REG, 1798, 0, bArr);
        if (controlCommandIn == 2) {
            return (2 & bArr[0]) == 0;
        }
        Log.i(CLASS_ID, "Expected 2 bytes, but get " + controlCommandIn);
        return false;
    }

    private int writeHandshakeByte() {
        if (setControlCommandOut(164, ~((this.dtr ? 32 : 0) | (this.rts ? 64 : 0)), 0, null) >= 0) {
            return 0;
        }
        Log.i(CLASS_ID, "Failed to set handshake byte");
        return -1;
    }

    private int setControlCommandOut(int i, int i2, int i3, byte[] bArr) {
        int controlTransfer = this.connection.controlTransfer(64, i, i2, i3, bArr, bArr != null ? bArr.length : 0, 0);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return controlTransfer;
    }

    private int setControlCommandIn(int i, int i2, int i3, byte[] bArr) {
        int controlTransfer = this.connection.controlTransfer(REQTYPE_HOST_FROM_DEVICE, i, i2, i3, bArr, bArr != null ? bArr.length : 0, 0);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return controlTransfer;
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class FlowControlThread extends AbstractWorkerThread {
        private final long time;

        private FlowControlThread() {
            this.time = 100L;
        }

        @Override // com.felhr.usbserial.AbstractWorkerThread
        public void doRun() {
            if (!this.firstTime) {
                if (CH34xSerialDevice.this.rtsCtsEnabled) {
                    if (CH34xSerialDevice.this.ctsState != pollForCTS()) {
                        CH34xSerialDevice.this.ctsState = !r0.ctsState;
                        if (CH34xSerialDevice.this.ctsCallback != null) {
                            CH34xSerialDevice.this.ctsCallback.onCTSChanged(CH34xSerialDevice.this.ctsState);
                        }
                    }
                }
                if (CH34xSerialDevice.this.dtrDsrEnabled) {
                    if (CH34xSerialDevice.this.dsrState != pollForDSR()) {
                        CH34xSerialDevice.this.dsrState = !r0.dsrState;
                        if (CH34xSerialDevice.this.dsrCallback != null) {
                            CH34xSerialDevice.this.dsrCallback.onDSRChanged(CH34xSerialDevice.this.dsrState);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            if (CH34xSerialDevice.this.rtsCtsEnabled && CH34xSerialDevice.this.ctsCallback != null) {
                CH34xSerialDevice.this.ctsCallback.onCTSChanged(CH34xSerialDevice.this.ctsState);
            }
            if (CH34xSerialDevice.this.dtrDsrEnabled && CH34xSerialDevice.this.dsrCallback != null) {
                CH34xSerialDevice.this.dsrCallback.onDSRChanged(CH34xSerialDevice.this.dsrState);
            }
            this.firstTime = false;
        }

        public boolean pollForCTS() {
            synchronized (this) {
                try {
                    wait(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return CH34xSerialDevice.this.checkCTS();
        }

        public boolean pollForDSR() {
            synchronized (this) {
                try {
                    wait(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return CH34xSerialDevice.this.checkDSR();
        }
    }
}
