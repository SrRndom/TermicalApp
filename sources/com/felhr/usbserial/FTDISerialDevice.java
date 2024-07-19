package com.felhr.usbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.os.Build;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import com.felhr.usbserial.UsbSerialInterface;
import com.felhr.utils.SafeUsbRequest;
import java.util.Arrays;

/* loaded from: classes.dex */
public class FTDISerialDevice extends UsbSerialDevice {
    private static final String CLASS_ID = "FTDISerialDevice";
    public static final int FTDI_BAUDRATE_115200 = 26;
    public static final int FTDI_BAUDRATE_1200 = 2500;
    public static final int FTDI_BAUDRATE_19200 = 32924;
    public static final int FTDI_BAUDRATE_230400 = 13;
    public static final int FTDI_BAUDRATE_2400 = 1250;
    public static final int FTDI_BAUDRATE_300 = 10000;
    public static final int FTDI_BAUDRATE_38400 = 49230;
    public static final int FTDI_BAUDRATE_460800 = 16390;
    public static final int FTDI_BAUDRATE_4800 = 625;
    public static final int FTDI_BAUDRATE_57600 = 52;
    public static final int FTDI_BAUDRATE_600 = 5000;
    public static final int FTDI_BAUDRATE_921600 = 32771;
    public static final int FTDI_BAUDRATE_9600 = 16696;
    private static final int FTDI_REQTYPE_HOST2DEVICE = 64;
    private static final int FTDI_SET_DATA_DEFAULT = 8;
    private static final int FTDI_SET_FLOW_CTRL_DEFAULT = 0;
    private static final int FTDI_SET_MODEM_CTRL_DEFAULT1 = 257;
    private static final int FTDI_SET_MODEM_CTRL_DEFAULT2 = 514;
    private static final int FTDI_SET_MODEM_CTRL_DEFAULT3 = 256;
    private static final int FTDI_SET_MODEM_CTRL_DEFAULT4 = 512;
    private static final int FTDI_SIO_MODEM_CTRL = 1;
    private static final int FTDI_SIO_RESET = 0;
    private static final int FTDI_SIO_SET_BAUD_RATE = 3;
    private static final int FTDI_SIO_SET_BREAK_OFF = 0;
    private static final int FTDI_SIO_SET_BREAK_ON = 16384;
    private static final int FTDI_SIO_SET_DATA = 4;
    private static final int FTDI_SIO_SET_DTR_HIGH = 257;
    private static final int FTDI_SIO_SET_DTR_LOW = 256;
    private static final int FTDI_SIO_SET_DTR_MASK = 1;
    private static final int FTDI_SIO_SET_FLOW_CTRL = 2;
    private static final int FTDI_SIO_SET_RTS_HIGH = 514;
    private static final int FTDI_SIO_SET_RTS_LOW = 512;
    private static final int FTDI_SIO_SET_RTS_MASK = 2;
    private UsbSerialInterface.UsbBreakCallback breakCallback;
    private UsbSerialInterface.UsbCTSCallback ctsCallback;
    private boolean ctsState;
    private int currentSioSetData;
    private UsbSerialInterface.UsbDSRCallback dsrCallback;
    private boolean dsrState;
    private boolean dtrDsrEnabled;
    private boolean firstTime;
    private UsbSerialInterface.UsbFrameCallback frameCallback;
    public FTDIUtilities ftdiUtilities;
    private UsbEndpoint inEndpoint;
    private final UsbInterface mInterface;
    private UsbEndpoint outEndpoint;
    private UsbSerialInterface.UsbOverrunCallback overrunCallback;
    private UsbSerialInterface.UsbParityCallback parityCallback;
    private boolean rtsCtsEnabled;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final byte[] skip = new byte[2];

    public FTDISerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        this(usbDevice, usbDeviceConnection, -1);
    }

    public FTDISerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        super(usbDevice, usbDeviceConnection);
        this.currentSioSetData = 0;
        this.ftdiUtilities = new FTDIUtilities();
        this.rtsCtsEnabled = false;
        this.dtrDsrEnabled = false;
        this.ctsState = true;
        this.dsrState = true;
        this.firstTime = true;
        this.mInterface = usbDevice.getInterface(i < 0 ? 0 : i);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean open() {
        if (openFTDI()) {
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
        setControlCommand(1, 256, 0);
        setControlCommand(1, 512, 0);
        this.currentSioSetData = 0;
        killWorkingThread();
        killWriteThread();
        this.connection.releaseInterface(this.mInterface);
        this.isOpen = false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean syncOpen() {
        if (openFTDI()) {
            setSyncParams(this.inEndpoint, this.outEndpoint);
            this.asyncMode = false;
            this.inputStream = new SerialInputStream(this);
            this.outputStream = new SerialOutputStream(this);
            this.isOpen = true;
            return true;
        }
        this.isOpen = false;
        return false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void syncClose() {
        setControlCommand(1, 256, 0);
        setControlCommand(1, 512, 0);
        this.currentSioSetData = 0;
        this.connection.releaseInterface(this.mInterface);
        this.isOpen = false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setBaudRate(int i) {
        short[] encodedBaudRate = encodedBaudRate(i);
        if (encodedBaudRate != null) {
            setEncodedBaudRate(encodedBaudRate);
        } else {
            setOldBaudRate(i);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setDataBits(int i) {
        if (i == 5) {
            int i2 = this.currentSioSetData | 1;
            this.currentSioSetData = i2;
            int i3 = i2 & (-3);
            this.currentSioSetData = i3;
            int i4 = i3 | 4;
            this.currentSioSetData = i4;
            int i5 = i4 & (-9);
            this.currentSioSetData = i5;
            setControlCommand(4, i5, 0);
            return;
        }
        if (i == 6) {
            int i6 = this.currentSioSetData & (-2);
            this.currentSioSetData = i6;
            int i7 = i6 | 2;
            this.currentSioSetData = i7;
            int i8 = i7 | 4;
            this.currentSioSetData = i8;
            int i9 = i8 & (-9);
            this.currentSioSetData = i9;
            setControlCommand(4, i9, 0);
            return;
        }
        if (i == 7) {
            int i10 = this.currentSioSetData | 1;
            this.currentSioSetData = i10;
            int i11 = i10 | 2;
            this.currentSioSetData = i11;
            int i12 = i11 | 4;
            this.currentSioSetData = i12;
            int i13 = i12 & (-9);
            this.currentSioSetData = i13;
            setControlCommand(4, i13, 0);
            return;
        }
        if (i == 8) {
            int i14 = this.currentSioSetData & (-2);
            this.currentSioSetData = i14;
            int i15 = i14 & (-3);
            this.currentSioSetData = i15;
            int i16 = i15 & (-5);
            this.currentSioSetData = i16;
            int i17 = i16 | 8;
            this.currentSioSetData = i17;
            setControlCommand(4, i17, 0);
            return;
        }
        int i18 = this.currentSioSetData & (-2);
        this.currentSioSetData = i18;
        int i19 = i18 & (-3);
        this.currentSioSetData = i19;
        int i20 = i19 & (-5);
        this.currentSioSetData = i20;
        int i21 = i20 | 8;
        this.currentSioSetData = i21;
        setControlCommand(4, i21, 0);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setStopBits(int i) {
        if (i == 1) {
            int i2 = this.currentSioSetData & (-2049);
            this.currentSioSetData = i2;
            int i3 = i2 & (-4097);
            this.currentSioSetData = i3;
            int i4 = i3 & (-8193);
            this.currentSioSetData = i4;
            setControlCommand(4, i4, 0);
            return;
        }
        if (i == 2) {
            int i5 = this.currentSioSetData & (-2049);
            this.currentSioSetData = i5;
            int i6 = i5 | 4096;
            this.currentSioSetData = i6;
            int i7 = i6 & (-8193);
            this.currentSioSetData = i7;
            setControlCommand(4, i7, 0);
            return;
        }
        if (i == 3) {
            int i8 = this.currentSioSetData | 2048;
            this.currentSioSetData = i8;
            int i9 = i8 & (-4097);
            this.currentSioSetData = i9;
            int i10 = i9 & (-8193);
            this.currentSioSetData = i10;
            setControlCommand(4, i10, 0);
            return;
        }
        int i11 = this.currentSioSetData & (-2049);
        this.currentSioSetData = i11;
        int i12 = i11 & (-4097);
        this.currentSioSetData = i12;
        int i13 = i12 & (-8193);
        this.currentSioSetData = i13;
        setControlCommand(4, i13, 0);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setParity(int i) {
        if (i == 0) {
            int i2 = this.currentSioSetData & (-257);
            this.currentSioSetData = i2;
            int i3 = i2 & (-513);
            this.currentSioSetData = i3;
            int i4 = i3 & (-1025);
            this.currentSioSetData = i4;
            setControlCommand(4, i4, 0);
            return;
        }
        if (i == 1) {
            int i5 = this.currentSioSetData | 256;
            this.currentSioSetData = i5;
            int i6 = i5 & (-513);
            this.currentSioSetData = i6;
            int i7 = i6 & (-1025);
            this.currentSioSetData = i7;
            setControlCommand(4, i7, 0);
            return;
        }
        if (i == 2) {
            int i8 = this.currentSioSetData & (-257);
            this.currentSioSetData = i8;
            int i9 = i8 | 512;
            this.currentSioSetData = i9;
            int i10 = i9 & (-1025);
            this.currentSioSetData = i10;
            setControlCommand(4, i10, 0);
            return;
        }
        if (i == 3) {
            int i11 = this.currentSioSetData | 256;
            this.currentSioSetData = i11;
            int i12 = i11 | 512;
            this.currentSioSetData = i12;
            int i13 = i12 & (-1025);
            this.currentSioSetData = i13;
            setControlCommand(4, i13, 0);
            return;
        }
        if (i == 4) {
            int i14 = this.currentSioSetData & (-257);
            this.currentSioSetData = i14;
            int i15 = i14 & (-513);
            this.currentSioSetData = i15;
            int i16 = i15 | 1024;
            this.currentSioSetData = i16;
            setControlCommand(4, i16, 0);
            return;
        }
        int i17 = this.currentSioSetData & (-257);
        this.currentSioSetData = i17;
        int i18 = i17 & (-513);
        this.currentSioSetData = i18;
        int i19 = i18 & (-1025);
        this.currentSioSetData = i19;
        setControlCommand(4, i19, 0);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setFlowControl(int i) {
        if (i == 0) {
            setControlCommand(2, 0, 0);
            this.rtsCtsEnabled = false;
            this.dtrDsrEnabled = false;
        } else if (i == 1) {
            this.rtsCtsEnabled = true;
            this.dtrDsrEnabled = false;
            setControlCommand(2, 0, 1);
        } else if (i == 2) {
            this.dtrDsrEnabled = true;
            this.rtsCtsEnabled = false;
            setControlCommand(2, 0, 2);
        } else if (i == 3) {
            setControlCommand(2, 4881, 4);
        } else {
            setControlCommand(2, 0, 0);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setBreak(boolean z) {
        if (z) {
            this.currentSioSetData |= 16384;
        } else {
            this.currentSioSetData &= -16385;
        }
        setControlCommand(4, this.currentSioSetData, 0);
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setRTS(boolean z) {
        if (z) {
            setControlCommand(1, 514, 0);
        } else {
            setControlCommand(1, 512, 0);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setDTR(boolean z) {
        if (z) {
            setControlCommand(1, 257, 0);
        } else {
            setControlCommand(1, 256, 0);
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
    }

    private boolean openFTDI() {
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
            this.firstTime = true;
            if (setControlCommand(0, 0, 0) < 0 || setControlCommand(4, 8, 0) < 0) {
                return false;
            }
            this.currentSioSetData = 8;
            if (setControlCommand(1, 257, 0) < 0 || setControlCommand(1, 514, 0) < 0 || setControlCommand(2, 0, 0) < 0 || setControlCommand(3, FTDI_BAUDRATE_9600, 0) < 0) {
                return false;
            }
            this.rtsCtsEnabled = false;
            this.dtrDsrEnabled = false;
            return true;
        }
        Log.i(CLASS_ID, "Interface could not be claimed");
        return false;
    }

    private int setControlCommand(int i, int i2, int i3) {
        int controlTransfer = this.connection.controlTransfer(64, i, i2, this.mInterface.getId() + 1 + i3, null, 0, 0);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return controlTransfer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] adaptArray(byte[] bArr) {
        int length = bArr.length;
        int i = 64;
        if (length <= 64) {
            if (length == 2) {
                return EMPTY_BYTE_ARRAY;
            }
            return Arrays.copyOfRange(bArr, 2, length);
        }
        int i2 = 1;
        while (i < length) {
            i2++;
            i = i2 * 64;
        }
        byte[] bArr2 = new byte[length - (i2 * 2)];
        copyData(bArr, bArr2);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void copyData(byte[] bArr, byte[] bArr2) {
        int i = 0;
        int i2 = 2;
        while (i2 - 2 <= bArr.length - 64) {
            System.arraycopy(bArr, i2, bArr2, i, 62);
            i2 += 64;
            i += 62;
        }
        int length = (bArr.length - i2) + 2;
        if (length > 0) {
            System.arraycopy(bArr, i2, bArr2, i, length - 2);
        }
    }

    /* loaded from: classes.dex */
    public class FTDIUtilities {
        public FTDIUtilities() {
        }

        public byte[] adaptArray(byte[] bArr) {
            int length = bArr.length;
            int i = 64;
            if (length > 64) {
                int i2 = 1;
                while (i < length) {
                    i2++;
                    i = i2 * 64;
                }
                byte[] bArr2 = new byte[length - (i2 * 2)];
                FTDISerialDevice.copyData(bArr, bArr2);
                return bArr2;
            }
            return Arrays.copyOfRange(bArr, 2, length);
        }

        public void checkModemStatus(byte[] bArr) {
            if (bArr.length == 0) {
                return;
            }
            boolean z = (bArr[0] & 16) == 16;
            boolean z2 = (bArr[0] & 32) == 32;
            if (FTDISerialDevice.this.firstTime) {
                FTDISerialDevice.this.ctsState = z;
                FTDISerialDevice.this.dsrState = z2;
                if (FTDISerialDevice.this.rtsCtsEnabled && FTDISerialDevice.this.ctsCallback != null) {
                    FTDISerialDevice.this.ctsCallback.onCTSChanged(FTDISerialDevice.this.ctsState);
                }
                if (FTDISerialDevice.this.dtrDsrEnabled && FTDISerialDevice.this.dsrCallback != null) {
                    FTDISerialDevice.this.dsrCallback.onDSRChanged(FTDISerialDevice.this.dsrState);
                }
                FTDISerialDevice.this.firstTime = false;
                return;
            }
            if (FTDISerialDevice.this.rtsCtsEnabled && z != FTDISerialDevice.this.ctsState && FTDISerialDevice.this.ctsCallback != null) {
                FTDISerialDevice.this.ctsState = !r0.ctsState;
                FTDISerialDevice.this.ctsCallback.onCTSChanged(FTDISerialDevice.this.ctsState);
            }
            if (FTDISerialDevice.this.dtrDsrEnabled && z2 != FTDISerialDevice.this.dsrState && FTDISerialDevice.this.dsrCallback != null) {
                FTDISerialDevice.this.dsrState = !r0.dsrState;
                FTDISerialDevice.this.dsrCallback.onDSRChanged(FTDISerialDevice.this.dsrState);
            }
            if (FTDISerialDevice.this.parityCallback != null && (bArr[1] & 4) == 4) {
                FTDISerialDevice.this.parityCallback.onParityError();
            }
            if (FTDISerialDevice.this.frameCallback != null && (bArr[1] & 8) == 8) {
                FTDISerialDevice.this.frameCallback.onFramingError();
            }
            if (FTDISerialDevice.this.overrunCallback != null && (bArr[1] & 2) == 2) {
                FTDISerialDevice.this.overrunCallback.onOverrunError();
            }
            if (FTDISerialDevice.this.breakCallback == null || (bArr[1] & 16) != 16) {
                return;
            }
            FTDISerialDevice.this.breakCallback.onBreakInterrupt();
        }
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public int syncRead(byte[] bArr, int i) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis() + i;
        if (this.asyncMode) {
            return -1;
        }
        if (bArr == null) {
            return 0;
        }
        int length = bArr.length / 62;
        if (bArr.length % 62 != 0) {
            length++;
        }
        int length2 = bArr.length + (length * 2);
        byte[] bArr2 = new byte[length2];
        int i3 = 0;
        do {
            if (i > 0) {
                i2 = (int) (currentTimeMillis - System.currentTimeMillis());
                if (i2 <= 0) {
                    break;
                }
            } else {
                i2 = 0;
            }
            int bulkTransfer = this.connection.bulkTransfer(this.inEndpoint, bArr2, length2, i2);
            if (bulkTransfer > 2) {
                System.arraycopy(this.ftdiUtilities.adaptArray(bArr2), 0, bArr, 0, bArr.length);
                int i4 = bulkTransfer / 64;
                if (bulkTransfer % 64 != 0) {
                    i4++;
                }
                i3 = bulkTransfer - (i4 * 2);
            }
        } while (i3 <= 0);
        return i3;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public int syncRead(byte[] bArr, int i, int i2, int i3) {
        int i4;
        long currentTimeMillis = System.currentTimeMillis() + i3;
        if (this.asyncMode) {
            return -1;
        }
        if (bArr == null) {
            return 0;
        }
        int i5 = i2 / 62;
        if (i2 % 62 != 0) {
            i5++;
        }
        int i6 = (i5 * 2) + i2;
        byte[] bArr2 = new byte[i6];
        int i7 = 0;
        do {
            if (i3 > 0) {
                i4 = (int) (currentTimeMillis - System.currentTimeMillis());
                if (i4 <= 0) {
                    break;
                }
            } else {
                i4 = 0;
            }
            int bulkTransfer = this.connection.bulkTransfer(this.inEndpoint, bArr2, i6, i4);
            if (bulkTransfer > 2) {
                System.arraycopy(this.ftdiUtilities.adaptArray(bArr2), 0, bArr, i, i2);
                int i8 = bulkTransfer / 64;
                if (bulkTransfer % 64 != 0) {
                    i8++;
                }
                i7 = bulkTransfer - (i8 * 2);
            }
        } while (i7 <= 0);
        return i7;
    }

    private int readSyncJelly(byte[] bArr, int i, long j) {
        int i2;
        int i3 = 0;
        do {
            if (i <= 0) {
                i2 = 0;
            } else {
                int currentTimeMillis = (int) (j - System.currentTimeMillis());
                if (currentTimeMillis <= 0) {
                    break;
                }
                i2 = currentTimeMillis;
            }
            UsbDeviceConnection usbDeviceConnection = this.connection;
            UsbEndpoint usbEndpoint = this.inEndpoint;
            byte[] bArr2 = skip;
            if (usbDeviceConnection.bulkTransfer(usbEndpoint, bArr2, bArr2.length, i2) > 2) {
                i3 += this.connection.bulkTransfer(this.inEndpoint, bArr, i3, 62, i2);
            }
        } while (i3 <= 0);
        return i3;
    }

    private short getBcdDevice() {
        if (Build.VERSION.SDK_INT < 13) {
            return (short) -1;
        }
        byte[] rawDescriptors = this.connection.getRawDescriptors();
        return (short) ((rawDescriptors[13] << 8) + rawDescriptors[12]);
    }

    private byte getISerialNumber() {
        if (Build.VERSION.SDK_INT >= 13) {
            return this.connection.getRawDescriptors()[16];
        }
        return (byte) -1;
    }

    private boolean isBaudTolerated(long j, long j2) {
        long j3 = j2 * 100;
        return j >= j3 / 103 && j <= j3 / 97;
    }

    private short[] encodedBaudRate(int i) {
        int i2;
        int i3;
        int i4;
        short[] sArr = new short[2];
        byte[] bArr = {0, 3, 2, 4, 1, 5, 6, 7};
        byte[] bArr2 = {0, 1, 0, 1, 0, -1, 2, 1, 0, -1, -2, -3, 4, 3, 2, 1};
        short bcdDevice = getBcdDevice();
        if (bcdDevice == -1) {
            return null;
        }
        boolean z = bcdDevice == 512 && getISerialNumber() == 0;
        boolean z2 = bcdDevice == 1280 || bcdDevice == 1792 || bcdDevice == 2048 || bcdDevice == 2304 || bcdDevice == 4096;
        boolean z3 = bcdDevice == 1792 || bcdDevice == 2048 || bcdDevice == 2304;
        if (i < 1200 || !z3) {
            i2 = 3000000;
            i3 = 0;
        } else {
            i2 = 12000000;
            i3 = 131072;
        }
        if (i < (i2 >> 14) || i > i2) {
            return null;
        }
        int i5 = (i2 << 4) / i;
        int i6 = i5 & 15;
        if (i6 == 1) {
            i4 = i5 & (-8);
        } else {
            i4 = z ? bArr2[i6] + i5 : i5 + 1;
        }
        int i7 = i4 >> 1;
        if (!isBaudTolerated((i2 << 3) / i7, i)) {
            return null;
        }
        int i8 = i7 & 7;
        int i9 = i7 >> 3;
        if (i9 == 1) {
            if (i8 == 0) {
                i9 = 0;
            } else {
                i8 = 0;
            }
        }
        int i10 = (bArr[i8] << 14) | i3 | i9;
        sArr[0] = (short) i10;
        sArr[1] = (short) (z2 ? ((i10 >> 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (this.mInterface.getId() + 1) : i10 >> 16);
        return sArr;
    }

    private void setEncodedBaudRate(short[] sArr) {
        this.connection.controlTransfer(64, 3, sArr[0], sArr[1], null, 0, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0081, code lost:
    
        if (r5 <= 921600) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0084, code lost:
    
        if (r5 > 921600) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void setOldBaudRate(int r5) {
        /*
            r4 = this;
            r0 = 32771(0x8003, float:4.5922E-41)
            r1 = 16696(0x4138, float:2.3396E-41)
            r2 = 300(0x12c, float:4.2E-43)
            if (r5 < 0) goto Lf
            if (r5 > r2) goto Lf
            r0 = 10000(0x2710, float:1.4013E-41)
            goto L86
        Lf:
            r3 = 600(0x258, float:8.41E-43)
            if (r5 <= r2) goto L19
            if (r5 > r3) goto L19
            r0 = 5000(0x1388, float:7.006E-42)
            goto L86
        L19:
            r2 = 1200(0x4b0, float:1.682E-42)
            if (r5 <= r3) goto L23
            if (r5 > r2) goto L23
            r0 = 2500(0x9c4, float:3.503E-42)
            goto L86
        L23:
            r3 = 2400(0x960, float:3.363E-42)
            if (r5 <= r2) goto L2d
            if (r5 > r3) goto L2d
            r0 = 1250(0x4e2, float:1.752E-42)
            goto L86
        L2d:
            r2 = 4800(0x12c0, float:6.726E-42)
            if (r5 <= r3) goto L36
            if (r5 > r2) goto L36
            r0 = 625(0x271, float:8.76E-43)
            goto L86
        L36:
            r3 = 9600(0x2580, float:1.3452E-41)
            if (r5 <= r2) goto L3f
            if (r5 > r3) goto L3f
        L3c:
            r0 = 16696(0x4138, float:2.3396E-41)
            goto L86
        L3f:
            r2 = 19200(0x4b00, float:2.6905E-41)
            if (r5 <= r3) goto L49
            if (r5 > r2) goto L49
            r0 = 32924(0x809c, float:4.6136E-41)
            goto L86
        L49:
            if (r5 <= r2) goto L54
            r3 = 38400(0x9600, float:5.381E-41)
            if (r5 > r3) goto L54
            r0 = 49230(0xc04e, float:6.8986E-41)
            goto L86
        L54:
            r3 = 57600(0xe100, float:8.0715E-41)
            if (r5 <= r2) goto L5e
            if (r5 > r3) goto L5e
            r0 = 52
            goto L86
        L5e:
            r2 = 115200(0x1c200, float:1.6143E-40)
            if (r5 <= r3) goto L68
            if (r5 > r2) goto L68
            r0 = 26
            goto L86
        L68:
            r3 = 230400(0x38400, float:3.22859E-40)
            if (r5 <= r2) goto L72
            if (r5 > r3) goto L72
            r0 = 13
            goto L86
        L72:
            r2 = 460800(0x70800, float:6.45718E-40)
            if (r5 <= r3) goto L7c
            if (r5 > r2) goto L7c
            r0 = 16390(0x4006, float:2.2967E-41)
            goto L86
        L7c:
            r3 = 921600(0xe1000, float:1.291437E-39)
            if (r5 <= r2) goto L84
            if (r5 > r3) goto L84
            goto L86
        L84:
            if (r5 <= r3) goto L3c
        L86:
            r5 = 3
            r1 = 0
            r4.setControlCommand(r5, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.felhr.usbserial.FTDISerialDevice.setOldBaudRate(int):void");
    }
}
