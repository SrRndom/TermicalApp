package com.felhr.usbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbRequest;
import android.os.Build;
import com.felhr.deviceids.CH34xIds;
import com.felhr.deviceids.CP210xIds;
import com.felhr.deviceids.FTDISioIds;
import com.felhr.deviceids.PL2303Ids;
import com.felhr.usbserial.UsbSerialInterface;

/* loaded from: classes.dex */
public abstract class UsbSerialDevice implements UsbSerialInterface {
    public static final String CDC = "cdc";
    public static final String CH34x = "ch34x";
    protected static final String COM_PORT = "COM ";
    public static final String CP210x = "cp210x";
    public static final String FTDI = "ftdi";
    public static final String PL2303 = "pl2303";
    protected static final int USB_TIMEOUT = 0;
    static final boolean mr1Version;
    protected final UsbDeviceConnection connection;
    protected final UsbDevice device;
    private UsbEndpoint inEndpoint;
    protected SerialInputStream inputStream;
    protected boolean isOpen;
    private UsbEndpoint outEndpoint;
    protected SerialOutputStream outputStream;
    protected ReadThread readThread;
    protected WorkerThread workerThread;
    protected WriteThread writeThread;
    private String portName = "";
    protected boolean asyncMode = true;
    protected SerialBuffer serialBuffer = new SerialBuffer(mr1Version);

    @Override // com.felhr.usbserial.UsbSerialInterface
    public abstract void close();

    public int getInitialBaudRate() {
        return -1;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public abstract boolean open();

    @Override // com.felhr.usbserial.UsbSerialInterface
    public abstract void setBaudRate(int i);

    @Override // com.felhr.usbserial.UsbSerialInterface
    public abstract void setBreak(boolean z);

    @Override // com.felhr.usbserial.UsbSerialInterface
    public abstract void setDataBits(int i);

    @Override // com.felhr.usbserial.UsbSerialInterface
    public abstract void setFlowControl(int i);

    public void setInitialBaudRate(int i) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public abstract void setParity(int i);

    @Override // com.felhr.usbserial.UsbSerialInterface
    public abstract void setStopBits(int i);

    @Override // com.felhr.usbserial.UsbSerialInterface
    public abstract void syncClose();

    @Override // com.felhr.usbserial.UsbSerialInterface
    public abstract boolean syncOpen();

    static {
        mr1Version = Build.VERSION.SDK_INT > 17;
    }

    public UsbSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        this.device = usbDevice;
        this.connection = usbDeviceConnection;
    }

    public static UsbSerialDevice createUsbSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        return createUsbSerialDevice(usbDevice, usbDeviceConnection, -1);
    }

    public static UsbSerialDevice createUsbSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        int vendorId = usbDevice.getVendorId();
        int productId = usbDevice.getProductId();
        if (FTDISioIds.isDeviceSupported(usbDevice)) {
            return new FTDISerialDevice(usbDevice, usbDeviceConnection, i);
        }
        if (CP210xIds.isDeviceSupported(vendorId, productId)) {
            return new CP2102SerialDevice(usbDevice, usbDeviceConnection, i);
        }
        if (PL2303Ids.isDeviceSupported(vendorId, productId)) {
            return new PL2303SerialDevice(usbDevice, usbDeviceConnection, i);
        }
        if (CH34xIds.isDeviceSupported(vendorId, productId)) {
            return new CH34xSerialDevice(usbDevice, usbDeviceConnection, i);
        }
        if (isCdcDevice(usbDevice)) {
            return new CDCSerialDevice(usbDevice, usbDeviceConnection, i);
        }
        return null;
    }

    public static UsbSerialDevice createUsbSerialDevice(String str, UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        if (str.equals(FTDI)) {
            return new FTDISerialDevice(usbDevice, usbDeviceConnection, i);
        }
        if (str.equals(CP210x)) {
            return new CP2102SerialDevice(usbDevice, usbDeviceConnection, i);
        }
        if (str.equals(PL2303)) {
            return new PL2303SerialDevice(usbDevice, usbDeviceConnection, i);
        }
        if (str.equals(CH34x)) {
            return new CH34xSerialDevice(usbDevice, usbDeviceConnection, i);
        }
        if (str.equals(CDC)) {
            return new CDCSerialDevice(usbDevice, usbDeviceConnection, i);
        }
        throw new IllegalArgumentException("Invalid type argument. Must be:cdc, ch34x, cp210x, ftdi or pl2303");
    }

    public static boolean isSupported(UsbDevice usbDevice) {
        int vendorId = usbDevice.getVendorId();
        int productId = usbDevice.getProductId();
        return FTDISioIds.isDeviceSupported(usbDevice) || CP210xIds.isDeviceSupported(vendorId, productId) || PL2303Ids.isDeviceSupported(vendorId, productId) || CH34xIds.isDeviceSupported(vendorId, productId) || isCdcDevice(usbDevice);
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void write(byte[] bArr) {
        if (this.asyncMode) {
            this.serialBuffer.putWriteBuffer(bArr);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public int read(UsbSerialInterface.UsbReadCallback usbReadCallback) {
        if (!this.asyncMode) {
            return -1;
        }
        if (mr1Version) {
            WorkerThread workerThread = this.workerThread;
            if (workerThread == null) {
                return 0;
            }
            workerThread.setCallback(usbReadCallback);
            this.workerThread.getUsbRequest().queue(this.serialBuffer.getReadBuffer(), 2048);
            return 0;
        }
        this.readThread.setCallback(usbReadCallback);
        return 0;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public int syncWrite(byte[] bArr, int i) {
        if (this.asyncMode) {
            return -1;
        }
        if (bArr == null) {
            return 0;
        }
        return this.connection.bulkTransfer(this.outEndpoint, bArr, bArr.length, i);
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public int syncRead(byte[] bArr, int i) {
        if (this.asyncMode) {
            return -1;
        }
        if (bArr == null) {
            return 0;
        }
        return this.connection.bulkTransfer(this.inEndpoint, bArr, bArr.length, i);
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public int syncWrite(byte[] bArr, int i, int i2, int i3) {
        if (this.asyncMode) {
            return -1;
        }
        if (bArr == null) {
            return 0;
        }
        return this.connection.bulkTransfer(this.outEndpoint, bArr, i, i2, i3);
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public int syncRead(byte[] bArr, int i, int i2, int i3) {
        if (this.asyncMode) {
            return -1;
        }
        if (bArr == null) {
            return 0;
        }
        return this.connection.bulkTransfer(this.inEndpoint, bArr, i, i2, i3);
    }

    public SerialInputStream getInputStream() {
        if (this.asyncMode) {
            throw new IllegalStateException("InputStream only available in Sync mode. \nOpen the port with syncOpen()");
        }
        return this.inputStream;
    }

    public SerialOutputStream getOutputStream() {
        if (this.asyncMode) {
            throw new IllegalStateException("OutputStream only available in Sync mode. \nOpen the port with syncOpen()");
        }
        return this.outputStream;
    }

    public int getVid() {
        return this.device.getVendorId();
    }

    public int getPid() {
        return this.device.getProductId();
    }

    public int getDeviceId() {
        return this.device.getDeviceId();
    }

    public void debug(boolean z) {
        SerialBuffer serialBuffer = this.serialBuffer;
        if (serialBuffer != null) {
            serialBuffer.debug(z);
        }
    }

    public void setPortName(String str) {
        this.portName = str;
    }

    public String getPortName() {
        return this.portName;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFTDIDevice() {
        return this instanceof FTDISerialDevice;
    }

    public static boolean isCdcDevice(UsbDevice usbDevice) {
        int interfaceCount = usbDevice.getInterfaceCount();
        for (int i = 0; i <= interfaceCount - 1; i++) {
            if (usbDevice.getInterface(i).getInterfaceClass() == 10) {
                return true;
            }
        }
        return false;
    }

    /* loaded from: classes.dex */
    protected class WorkerThread extends AbstractWorkerThread {
        private UsbSerialInterface.UsbReadCallback callback;
        private UsbRequest requestIN;
        private final UsbSerialDevice usbSerialDevice;

        public WorkerThread(UsbSerialDevice usbSerialDevice) {
            this.usbSerialDevice = usbSerialDevice;
        }

        @Override // com.felhr.usbserial.AbstractWorkerThread
        public void doRun() {
            UsbRequest requestWait = UsbSerialDevice.this.connection.requestWait();
            if (requestWait != null && requestWait.getEndpoint().getType() == 2 && requestWait.getEndpoint().getDirection() == 128) {
                byte[] dataReceived = UsbSerialDevice.this.serialBuffer.getDataReceived();
                if (UsbSerialDevice.this.isFTDIDevice()) {
                    ((FTDISerialDevice) this.usbSerialDevice).ftdiUtilities.checkModemStatus(dataReceived);
                    UsbSerialDevice.this.serialBuffer.clearReadBuffer();
                    if (dataReceived.length > 2) {
                        onReceivedData(FTDISerialDevice.adaptArray(dataReceived));
                    }
                } else {
                    UsbSerialDevice.this.serialBuffer.clearReadBuffer();
                    onReceivedData(dataReceived);
                }
                this.requestIN.queue(UsbSerialDevice.this.serialBuffer.getReadBuffer(), 2048);
            }
        }

        public void setCallback(UsbSerialInterface.UsbReadCallback usbReadCallback) {
            this.callback = usbReadCallback;
        }

        public void setUsbRequest(UsbRequest usbRequest) {
            this.requestIN = usbRequest;
        }

        public UsbRequest getUsbRequest() {
            return this.requestIN;
        }

        private void onReceivedData(byte[] bArr) {
            UsbSerialInterface.UsbReadCallback usbReadCallback = this.callback;
            if (usbReadCallback != null) {
                usbReadCallback.onReceivedData(bArr);
            }
        }
    }

    /* loaded from: classes.dex */
    private class WriteThread extends AbstractWorkerThread {
        private UsbEndpoint outEndpoint;

        private WriteThread() {
        }

        @Override // com.felhr.usbserial.AbstractWorkerThread
        public void doRun() {
            byte[] writeBuffer = UsbSerialDevice.this.serialBuffer.getWriteBuffer();
            if (writeBuffer.length > 0) {
                UsbSerialDevice.this.connection.bulkTransfer(this.outEndpoint, writeBuffer, writeBuffer.length, 0);
            }
        }

        public void setUsbEndpoint(UsbEndpoint usbEndpoint) {
            this.outEndpoint = usbEndpoint;
        }
    }

    /* loaded from: classes.dex */
    protected class ReadThread extends AbstractWorkerThread {
        private UsbSerialInterface.UsbReadCallback callback;
        private UsbEndpoint inEndpoint;
        private final UsbSerialDevice usbSerialDevice;

        public ReadThread(UsbSerialDevice usbSerialDevice) {
            this.usbSerialDevice = usbSerialDevice;
        }

        public void setCallback(UsbSerialInterface.UsbReadCallback usbReadCallback) {
            this.callback = usbReadCallback;
        }

        @Override // com.felhr.usbserial.AbstractWorkerThread
        public void doRun() {
            int bulkTransfer = this.inEndpoint != null ? UsbSerialDevice.this.connection.bulkTransfer(this.inEndpoint, UsbSerialDevice.this.serialBuffer.getBufferCompatible(), 2048, 0) : 0;
            if (bulkTransfer > 0) {
                byte[] dataReceivedCompatible = UsbSerialDevice.this.serialBuffer.getDataReceivedCompatible(bulkTransfer);
                if (UsbSerialDevice.this.isFTDIDevice()) {
                    ((FTDISerialDevice) this.usbSerialDevice).ftdiUtilities.checkModemStatus(dataReceivedCompatible);
                    if (dataReceivedCompatible.length > 2) {
                        onReceivedData(FTDISerialDevice.adaptArray(dataReceivedCompatible));
                        return;
                    }
                    return;
                }
                onReceivedData(dataReceivedCompatible);
            }
        }

        public void setUsbEndpoint(UsbEndpoint usbEndpoint) {
            this.inEndpoint = usbEndpoint;
        }

        private void onReceivedData(byte[] bArr) {
            UsbSerialInterface.UsbReadCallback usbReadCallback = this.callback;
            if (usbReadCallback != null) {
                usbReadCallback.onReceivedData(bArr);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSyncParams(UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2) {
        this.inEndpoint = usbEndpoint;
        this.outEndpoint = usbEndpoint2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setThreadsParams(UsbRequest usbRequest, UsbEndpoint usbEndpoint) {
        this.writeThread.setUsbEndpoint(usbEndpoint);
        if (mr1Version) {
            this.workerThread.setUsbRequest(usbRequest);
        } else {
            this.readThread.setUsbEndpoint(usbRequest.getEndpoint());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void killWorkingThread() {
        ReadThread readThread;
        WorkerThread workerThread;
        boolean z = mr1Version;
        if (z && (workerThread = this.workerThread) != null) {
            workerThread.stopThread();
            this.workerThread = null;
        } else {
            if (z || (readThread = this.readThread) == null) {
                return;
            }
            readThread.stopThread();
            this.readThread = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void restartWorkingThread() {
        boolean z = mr1Version;
        if (z && this.workerThread == null) {
            WorkerThread workerThread = new WorkerThread(this);
            this.workerThread = workerThread;
            workerThread.start();
            do {
            } while (!this.workerThread.isAlive());
            return;
        }
        if (z || this.readThread != null) {
            return;
        }
        ReadThread readThread = new ReadThread(this);
        this.readThread = readThread;
        readThread.start();
        do {
        } while (!this.readThread.isAlive());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void killWriteThread() {
        WriteThread writeThread = this.writeThread;
        if (writeThread != null) {
            writeThread.stopThread();
            this.writeThread = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void restartWriteThread() {
        if (this.writeThread == null) {
            WriteThread writeThread = new WriteThread();
            this.writeThread = writeThread;
            writeThread.start();
            do {
            } while (!this.writeThread.isAlive());
        }
    }
}
