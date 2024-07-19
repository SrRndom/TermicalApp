package com.felhr.usbserial;

import java.util.List;

/* loaded from: classes.dex */
public interface SerialPortCallback {
    void onSerialPortsDetected(List<UsbSerialDevice> list);
}
