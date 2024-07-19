package com.tione;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.felhr.usbserial.CDCSerialDevice;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class UsbService extends Service {
    public static final String ACTION_CDC_DRIVER_NOT_WORKING = "com.felhr.connectivityservices.ACTION_CDC_DRIVER_NOT_WORKING";
    public static final String ACTION_NO_USB = "com.felhr.usbservice.NO_USB";
    public static final String ACTION_USB_ATTACHED = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
    public static final String ACTION_USB_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED";
    public static final String ACTION_USB_DEVICE_NOT_WORKING = "com.felhr.connectivityservices.ACTION_USB_DEVICE_NOT_WORKING";
    public static final String ACTION_USB_DISCONNECTED = "com.felhr.usbservice.USB_DISCONNECTED";
    public static final String ACTION_USB_NOT_SUPPORTED = "com.felhr.usbservice.USB_NOT_SUPPORTED";
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    public static final String ACTION_USB_PERMISSION_GRANTED = "com.felhr.usbservice.USB_PERMISSION_GRANTED";
    public static final String ACTION_USB_PERMISSION_NOT_GRANTED = "com.felhr.usbservice.USB_PERMISSION_NOT_GRANTED";
    public static final String ACTION_USB_READY = "com.felhr.connectivityservices.USB_READY";
    private static final int BAUD_RATE = 921600;
    public static final int CTS_CHANGE = 1;
    public static final int DSR_CHANGE = 2;
    public static final int MESSAGE_FROM_SERIAL_PORT = 0;
    public static boolean SERVICE_CONNECTED = false;
    public static final String TAG = "UsbService";
    private UsbDeviceConnection connection;
    private Context context;
    private UsbDevice device;
    private Handler mHandler;
    private UsbSerialDevice serialPort;
    private boolean serialPortConnected;
    private UsbManager usbManager;
    private IBinder binder = new UsbBinder();
    private UsbSerialInterface.UsbReadCallback mCallback = new UsbSerialInterface.UsbReadCallback() { // from class: com.tione.UsbService.1
        @Override // com.felhr.usbserial.UsbSerialInterface.UsbReadCallback
        public void onReceivedData(byte[] bArr) {
            if (UsbService.this.mHandler != null) {
                UsbService.this.mHandler.obtainMessage(0, bArr).sendToTarget();
            }
        }
    };
    private UsbSerialInterface.UsbCTSCallback ctsCallback = new UsbSerialInterface.UsbCTSCallback() { // from class: com.tione.UsbService.2
        @Override // com.felhr.usbserial.UsbSerialInterface.UsbCTSCallback
        public void onCTSChanged(boolean z) {
            if (UsbService.this.mHandler != null) {
                UsbService.this.mHandler.obtainMessage(1).sendToTarget();
            }
        }
    };
    private UsbSerialInterface.UsbDSRCallback dsrCallback = new UsbSerialInterface.UsbDSRCallback() { // from class: com.tione.UsbService.3
        @Override // com.felhr.usbserial.UsbSerialInterface.UsbDSRCallback
        public void onDSRChanged(boolean z) {
            if (UsbService.this.mHandler != null) {
                UsbService.this.mHandler.obtainMessage(2).sendToTarget();
            }
        }
    };
    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() { // from class: com.tione.UsbService.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(UsbService.ACTION_USB_PERMISSION)) {
                if (intent.getExtras().getBoolean("permission")) {
                    context.sendBroadcast(new Intent(UsbService.ACTION_USB_PERMISSION_GRANTED));
                    UsbService usbService = UsbService.this;
                    usbService.connection = usbService.usbManager.openDevice(UsbService.this.device);
                    new ConnectionThread().start();
                    return;
                }
                context.sendBroadcast(new Intent(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED));
                return;
            }
            if (intent.getAction().equals(UsbService.ACTION_USB_ATTACHED)) {
                if (UsbService.this.serialPortConnected) {
                    return;
                }
                UsbService.this.findSerialPortDevice();
            } else if (intent.getAction().equals(UsbService.ACTION_USB_DETACHED)) {
                context.sendBroadcast(new Intent(UsbService.ACTION_USB_DISCONNECTED));
                if (UsbService.this.serialPortConnected) {
                    UsbService.this.serialPort.close();
                }
                UsbService.this.serialPortConnected = false;
            }
        }
    };

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.context = this;
        this.serialPortConnected = false;
        SERVICE_CONNECTED = true;
        setFilter();
        this.usbManager = (UsbManager) getSystemService("usb");
        findSerialPortDevice();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.serialPort.close();
        unregisterReceiver(this.usbReceiver);
        SERVICE_CONNECTED = false;
    }

    public void write(byte[] bArr) {
        UsbSerialDevice usbSerialDevice = this.serialPort;
        if (usbSerialDevice != null) {
            usbSerialDevice.write(bArr);
        }
    }

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void findSerialPortDevice() {
        HashMap<String, UsbDevice> deviceList = this.usbManager.getDeviceList();
        if (!deviceList.isEmpty()) {
            Iterator<Map.Entry<String, UsbDevice>> it = deviceList.entrySet().iterator();
            while (it.hasNext()) {
                UsbDevice value = it.next().getValue();
                this.device = value;
                Log.d(TAG, String.format("USBDevice.HashMap (vid:pid) (%X:%X)-%b class:%X:%X name:%s", Integer.valueOf(value.getVendorId()), Integer.valueOf(this.device.getProductId()), Boolean.valueOf(UsbSerialDevice.isSupported(this.device)), Integer.valueOf(this.device.getDeviceClass()), Integer.valueOf(this.device.getDeviceSubclass()), this.device.getDeviceName()));
            }
            Iterator<Map.Entry<String, UsbDevice>> it2 = deviceList.entrySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                UsbDevice value2 = it2.next().getValue();
                this.device = value2;
                value2.getVendorId();
                this.device.getProductId();
                if (UsbSerialDevice.isSupported(this.device)) {
                    requestUserPermission();
                    break;
                } else {
                    this.connection = null;
                    this.device = null;
                }
            }
            if (this.device == null) {
                sendBroadcast(new Intent(ACTION_NO_USB));
                return;
            }
            return;
        }
        Log.d(TAG, "findSerialPortDevice() usbManager returned empty device list.");
        sendBroadcast(new Intent(ACTION_NO_USB));
    }

    private void setFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_USB_PERMISSION);
        intentFilter.addAction(ACTION_USB_DETACHED);
        intentFilter.addAction(ACTION_USB_ATTACHED);
        registerReceiver(this.usbReceiver, intentFilter);
    }

    private void requestUserPermission() {
        Log.d(TAG, String.format("requestUserPermission(%X:%X)", Integer.valueOf(this.device.getVendorId()), Integer.valueOf(this.device.getProductId())));
        this.usbManager.requestPermission(this.device, PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 33554432));
    }

    /* loaded from: classes.dex */
    public class UsbBinder extends Binder {
        public UsbBinder() {
        }

        public UsbService getService() {
            return UsbService.this;
        }
    }

    /* loaded from: classes.dex */
    private class ConnectionThread extends Thread {
        private ConnectionThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            UsbService usbService = UsbService.this;
            usbService.serialPort = UsbSerialDevice.createUsbSerialDevice(usbService.device, UsbService.this.connection);
            if (UsbService.this.serialPort != null) {
                if (UsbService.this.serialPort.open()) {
                    UsbService.this.serialPortConnected = true;
                    UsbService.this.serialPort.setBaudRate(UsbService.BAUD_RATE);
                    UsbService.this.serialPort.setDataBits(8);
                    UsbService.this.serialPort.setStopBits(1);
                    UsbService.this.serialPort.setParity(0);
                    UsbService.this.serialPort.setFlowControl(0);
                    UsbService.this.serialPort.read(UsbService.this.mCallback);
                    UsbService.this.serialPort.getCTS(UsbService.this.ctsCallback);
                    UsbService.this.serialPort.getDSR(UsbService.this.dsrCallback);
                    UsbService.this.context.sendBroadcast(new Intent(UsbService.ACTION_USB_READY));
                    return;
                }
                if (UsbService.this.serialPort instanceof CDCSerialDevice) {
                    UsbService.this.context.sendBroadcast(new Intent(UsbService.ACTION_CDC_DRIVER_NOT_WORKING));
                    return;
                } else {
                    UsbService.this.context.sendBroadcast(new Intent(UsbService.ACTION_USB_DEVICE_NOT_WORKING));
                    return;
                }
            }
            UsbService.this.context.sendBroadcast(new Intent(UsbService.ACTION_USB_NOT_SUPPORTED));
        }
    }
}
