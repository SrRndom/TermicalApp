package com.felhr.deviceids;

/* loaded from: classes.dex */
public class CP2130Ids {
    private static final long[] cp2130Devices = Helpers.createTable(Helpers.createDevice(4292, 34720));

    public static boolean isDeviceSupported(int i, int i2) {
        return Helpers.exists(cp2130Devices, i, i2);
    }
}
