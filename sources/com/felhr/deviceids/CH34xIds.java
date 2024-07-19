package com.felhr.deviceids;

/* loaded from: classes.dex */
public class CH34xIds {
    private static final long[] ch34xDevices = Helpers.createTable(Helpers.createDevice(17224, 21795), Helpers.createDevice(6790, 29987), Helpers.createDevice(6790, 21795), Helpers.createDevice(6790, 1093));

    private CH34xIds() {
    }

    public static boolean isDeviceSupported(int i, int i2) {
        return Helpers.exists(ch34xDevices, i, i2);
    }
}
