package com.felhr.usbserial;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;
import com.felhr.usbserial.SerialPortBuilder;
import com.felhr.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: classes.dex */
public class SerialPortBuilder {
    private static final String ACTION_USB_PERMISSION = "com.felhr.usbserial.USB_PERMISSION";
    private static final int MODE_OPEN = 1;
    private static final int MODE_START = 0;
    private static SerialPortBuilder SerialPortBuilder;
    private int baudRate;
    private PendingUsbPermission currentPendingPermission;
    private int dataBits;
    private List<UsbDeviceStatus> devices;
    private int flowControl;
    private int parity;
    private final SerialPortCallback serialPortCallback;
    private int stopBits;
    private UsbManager usbManager;
    private List<UsbSerialDevice> serialDevices = new ArrayList();
    private final ArrayBlockingQueue<PendingUsbPermission> queuedPermissions = new ArrayBlockingQueue<>(100);
    private volatile boolean processingPermission = false;
    private int mode = 0;
    private boolean broadcastRegistered = false;
    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() { // from class: com.felhr.usbserial.SerialPortBuilder.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SerialPortBuilder.ACTION_USB_PERMISSION)) {
                if (!intent.getExtras().getBoolean("permission")) {
                    if (SerialPortBuilder.this.queuedPermissions.size() > 0) {
                        SerialPortBuilder.this.launchPermission();
                        return;
                    }
                    SerialPortBuilder.this.processingPermission = false;
                    if (SerialPortBuilder.this.mode == 0) {
                        SerialPortBuilder.this.serialPortCallback.onSerialPortsDetected(SerialPortBuilder.this.serialDevices);
                        return;
                    } else {
                        SerialPortBuilder serialPortBuilder = SerialPortBuilder.this;
                        new InitSerialPortThread(serialPortBuilder.serialDevices).start();
                        return;
                    }
                }
                SerialPortBuilder serialPortBuilder2 = SerialPortBuilder.this;
                serialPortBuilder2.createAllPorts(serialPortBuilder2.currentPendingPermission.usbDeviceStatus);
                if (SerialPortBuilder.this.queuedPermissions.size() > 0) {
                    SerialPortBuilder.this.launchPermission();
                    return;
                }
                SerialPortBuilder.this.processingPermission = false;
                if (SerialPortBuilder.this.mode == 0) {
                    SerialPortBuilder.this.serialPortCallback.onSerialPortsDetected(SerialPortBuilder.this.serialDevices);
                } else {
                    SerialPortBuilder serialPortBuilder3 = SerialPortBuilder.this;
                    new InitSerialPortThread(serialPortBuilder3.serialDevices).start();
                }
            }
        }
    };

    private SerialPortBuilder(SerialPortCallback serialPortCallback) {
        this.serialPortCallback = serialPortCallback;
    }

    public static SerialPortBuilder createSerialPortBuilder(SerialPortCallback serialPortCallback) {
        SerialPortBuilder serialPortBuilder = SerialPortBuilder;
        if (serialPortBuilder != null) {
            return serialPortBuilder;
        }
        SerialPortBuilder serialPortBuilder2 = new SerialPortBuilder(serialPortCallback);
        SerialPortBuilder = serialPortBuilder2;
        return serialPortBuilder2;
    }

    public List<UsbDevice> getPossibleSerialPorts(Context context) {
        UsbManager usbManager = (UsbManager) context.getSystemService("usb");
        this.usbManager = usbManager;
        return Stream.of(usbManager.getDeviceList().values()).filter(new Predicate() { // from class: com.felhr.usbserial.SerialPortBuilder$$ExternalSyntheticLambda6
            @Override // com.annimon.stream.function.Predicate
            public final boolean test(Object obj) {
                return UsbSerialDevice.isSupported((UsbDevice) obj);
            }
        }).toList();
    }

    public boolean getSerialPorts(Context context) {
        initReceiver(context);
        List<UsbDeviceStatus> list = this.devices;
        if (list == null || list.size() == 0) {
            List<UsbDeviceStatus> list2 = Stream.of(getPossibleSerialPorts(context)).map(new Function() { // from class: com.felhr.usbserial.SerialPortBuilder$$ExternalSyntheticLambda0
                @Override // com.annimon.stream.function.Function
                public final Object apply(Object obj) {
                    return SerialPortBuilder.this.m6lambda$getSerialPorts$0$comfelhrusbserialSerialPortBuilder((UsbDevice) obj);
                }
            }).toList();
            this.devices = list2;
            if (list2.size() == 0) {
                return false;
            }
            Iterator<UsbDeviceStatus> it = this.devices.iterator();
            while (it.hasNext()) {
                this.queuedPermissions.add(createUsbPermission(context, it.next()));
            }
            if (this.processingPermission) {
                return true;
            }
            launchPermission();
            return true;
        }
        List list3 = Stream.of(getPossibleSerialPorts(context)).map(new Function() { // from class: com.felhr.usbserial.SerialPortBuilder$$ExternalSyntheticLambda1
            @Override // com.annimon.stream.function.Function
            public final Object apply(Object obj) {
                return SerialPortBuilder.this.m7lambda$getSerialPorts$1$comfelhrusbserialSerialPortBuilder((UsbDevice) obj);
            }
        }).filter(new Predicate() { // from class: com.felhr.usbserial.SerialPortBuilder$$ExternalSyntheticLambda4
            @Override // com.annimon.stream.function.Predicate
            public final boolean test(Object obj) {
                return SerialPortBuilder.this.m8lambda$getSerialPorts$2$comfelhrusbserialSerialPortBuilder((SerialPortBuilder.UsbDeviceStatus) obj);
            }
        }).toList();
        if (list3.size() == 0) {
            return false;
        }
        Iterator it2 = list3.iterator();
        while (it2.hasNext()) {
            this.queuedPermissions.add(createUsbPermission(context, (UsbDeviceStatus) it2.next()));
        }
        this.devices.addAll(list3);
        if (this.processingPermission) {
            return true;
        }
        launchPermission();
        return true;
    }

    /* renamed from: lambda$getSerialPorts$0$com-felhr-usbserial-SerialPortBuilder, reason: not valid java name */
    public /* synthetic */ UsbDeviceStatus m6lambda$getSerialPorts$0$comfelhrusbserialSerialPortBuilder(UsbDevice usbDevice) {
        return new UsbDeviceStatus(usbDevice);
    }

    /* renamed from: lambda$getSerialPorts$1$com-felhr-usbserial-SerialPortBuilder, reason: not valid java name */
    public /* synthetic */ UsbDeviceStatus m7lambda$getSerialPorts$1$comfelhrusbserialSerialPortBuilder(UsbDevice usbDevice) {
        return new UsbDeviceStatus(usbDevice);
    }

    /* renamed from: lambda$getSerialPorts$2$com-felhr-usbserial-SerialPortBuilder, reason: not valid java name */
    public /* synthetic */ boolean m8lambda$getSerialPorts$2$comfelhrusbserialSerialPortBuilder(UsbDeviceStatus usbDeviceStatus) {
        return !this.devices.contains(usbDeviceStatus);
    }

    public boolean openSerialPorts(Context context, int i, int i2, int i3, int i4, int i5) {
        this.baudRate = i;
        this.dataBits = i2;
        this.stopBits = i3;
        this.parity = i4;
        this.flowControl = i5;
        this.mode = 1;
        return getSerialPorts(context);
    }

    public boolean disconnectDevice(final UsbSerialDevice usbSerialDevice) {
        usbSerialDevice.syncClose();
        this.serialDevices = Utils.removeIf(this.serialDevices, new Predicate() { // from class: com.felhr.usbserial.SerialPortBuilder$$ExternalSyntheticLambda5
            @Override // com.annimon.stream.function.Predicate
            public final boolean test(Object obj) {
                return SerialPortBuilder.lambda$disconnectDevice$3(UsbSerialDevice.this, (UsbSerialDevice) obj);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$disconnectDevice$3(UsbSerialDevice usbSerialDevice, UsbSerialDevice usbSerialDevice2) {
        return usbSerialDevice.getDeviceId() == usbSerialDevice2.getDeviceId();
    }

    public boolean disconnectDevice(final UsbDevice usbDevice) {
        List list = Stream.of(this.serialDevices).filter(new Predicate() { // from class: com.felhr.usbserial.SerialPortBuilder$$ExternalSyntheticLambda2
            @Override // com.annimon.stream.function.Predicate
            public final boolean test(Object obj) {
                return SerialPortBuilder.lambda$disconnectDevice$4(usbDevice, (UsbSerialDevice) obj);
            }
        }).toList();
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            ((UsbSerialDevice) it.next()).syncClose();
            this.serialDevices = Utils.removeIf(this.serialDevices, new Predicate() { // from class: com.felhr.usbserial.SerialPortBuilder$$ExternalSyntheticLambda3
                @Override // com.annimon.stream.function.Predicate
                public final boolean test(Object obj) {
                    return SerialPortBuilder.lambda$disconnectDevice$5(usbDevice, (UsbSerialDevice) obj);
                }
            });
            i++;
        }
        return i == list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$disconnectDevice$4(UsbDevice usbDevice, UsbSerialDevice usbSerialDevice) {
        return usbDevice.getDeviceId() == usbSerialDevice.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$disconnectDevice$5(UsbDevice usbDevice, UsbSerialDevice usbSerialDevice) {
        return usbDevice.getDeviceId() == usbSerialDevice.getDeviceId();
    }

    public void unregisterListeners(Context context) {
        if (this.broadcastRegistered) {
            context.unregisterReceiver(this.usbReceiver);
            this.broadcastRegistered = false;
        }
    }

    private PendingUsbPermission createUsbPermission(Context context, UsbDeviceStatus usbDeviceStatus) {
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
        PendingUsbPermission pendingUsbPermission = new PendingUsbPermission();
        pendingUsbPermission.pendingIntent = broadcast;
        pendingUsbPermission.usbDeviceStatus = usbDeviceStatus;
        return pendingUsbPermission;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchPermission() {
        try {
            this.processingPermission = true;
            PendingUsbPermission take = this.queuedPermissions.take();
            this.currentPendingPermission = take;
            this.usbManager.requestPermission(take.usbDeviceStatus.usbDevice, this.currentPendingPermission.pendingIntent);
        } catch (InterruptedException e) {
            e.printStackTrace();
            this.processingPermission = false;
        }
    }

    private void initReceiver(Context context) {
        if (this.broadcastRegistered) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_USB_PERMISSION);
        context.registerReceiver(this.usbReceiver, intentFilter);
        this.broadcastRegistered = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAllPorts(UsbDeviceStatus usbDeviceStatus) {
        int interfaceCount = usbDeviceStatus.usbDevice.getInterfaceCount();
        for (int i = 0; i <= interfaceCount - 1; i++) {
            if (usbDeviceStatus.usbDeviceConnection == null) {
                usbDeviceStatus.usbDeviceConnection = this.usbManager.openDevice(usbDeviceStatus.usbDevice);
            }
            this.serialDevices.add(UsbSerialDevice.createUsbSerialDevice(usbDeviceStatus.usbDevice, usbDeviceStatus.usbDeviceConnection, i));
        }
    }

    /* loaded from: classes.dex */
    private class InitSerialPortThread extends Thread {
        private final List<UsbSerialDevice> usbSerialDevices;

        public InitSerialPortThread(List<UsbSerialDevice> list) {
            this.usbSerialDevices = list;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i = 1;
            for (UsbSerialDevice usbSerialDevice : this.usbSerialDevices) {
                if (!usbSerialDevice.isOpen && usbSerialDevice.syncOpen()) {
                    usbSerialDevice.setBaudRate(SerialPortBuilder.this.baudRate);
                    usbSerialDevice.setDataBits(SerialPortBuilder.this.dataBits);
                    usbSerialDevice.setStopBits(SerialPortBuilder.this.stopBits);
                    usbSerialDevice.setParity(SerialPortBuilder.this.parity);
                    usbSerialDevice.setFlowControl(SerialPortBuilder.this.flowControl);
                    usbSerialDevice.setPortName("COM " + String.valueOf(i));
                    i++;
                }
            }
            SerialPortBuilder.this.serialPortCallback.onSerialPortsDetected(SerialPortBuilder.this.serialDevices);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class UsbDeviceStatus {
        public boolean open;
        public UsbDevice usbDevice;
        public UsbDeviceConnection usbDeviceConnection;

        public UsbDeviceStatus(UsbDevice usbDevice) {
            this.usbDevice = usbDevice;
        }

        public boolean equals(Object obj) {
            return ((UsbDeviceStatus) obj).usbDevice.getDeviceId() == this.usbDevice.getDeviceId();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PendingUsbPermission {
        public PendingIntent pendingIntent;
        public UsbDeviceStatus usbDeviceStatus;

        private PendingUsbPermission() {
        }
    }
}
