package com.felhr.deviceids;

/* loaded from: classes.dex */
public class XdcVcpIds {
    private static final long[] xdcvcpDevices = Helpers.createTable(Helpers.createDevice(9805, 562), Helpers.createDevice(9805, 288), Helpers.createDevice(1155, 22336));

    public static boolean isDeviceSupported(int i, int i2) {
        return Helpers.exists(xdcvcpDevices, i, i2);
    }
}
