package com.felhr.deviceids;

import androidx.core.view.PointerIconCompat;

/* loaded from: classes.dex */
public class PL2303Ids {
    private static final long[] pl2303Devices = Helpers.createTable(Helpers.createDevice(1189, 16423), Helpers.createDevice(1659, 8963), Helpers.createDevice(1659, 1211), Helpers.createDevice(1659, 4660), Helpers.createDevice(1659, 43680), Helpers.createDevice(1659, 43682), Helpers.createDevice(1659, 1553), Helpers.createDevice(1659, 1554), Helpers.createDevice(1659, 1545), Helpers.createDevice(1659, 13082), Helpers.createDevice(1659, 775), Helpers.createDevice(1659, 1123), Helpers.createDevice(1367, 8200), Helpers.createDevice(1351, 8200), Helpers.createDevice(1211, 2563), Helpers.createDevice(1211, 2574), Helpers.createDevice(1390, 20483), Helpers.createDevice(1390, 20484), Helpers.createDevice(3770, 4224), Helpers.createDevice(3770, 8320), Helpers.createDevice(3575, 1568), Helpers.createDevice(1412, 45056), Helpers.createDevice(9336, 8200), Helpers.createDevice(5203, 16422), Helpers.createDevice(1841, 1320), Helpers.createDevice(24969, 8296), Helpers.createDevice(4599, 735), Helpers.createDevice(1256, 32769), Helpers.createDevice(4597, 1), Helpers.createDevice(4597, 3), Helpers.createDevice(4597, 4), Helpers.createDevice(4597, 5), Helpers.createDevice(1861, 1), Helpers.createDevice(1931, 4660), Helpers.createDevice(4277, 44144), Helpers.createDevice(1947, 39), Helpers.createDevice(1043, 8449), Helpers.createDevice(3669, 4363), Helpers.createDevice(1841, 8195), Helpers.createDevice(1293, 599), Helpers.createDevice(1423, 38688), Helpers.createDevice(4598, 8193), Helpers.createDevice(1962, 42), Helpers.createDevice(1453, 4026), Helpers.createDevice(21362, 8963), Helpers.createDevice(PointerIconCompat.TYPE_TEXT, 2873), Helpers.createDevice(PointerIconCompat.TYPE_TEXT, 12601), Helpers.createDevice(PointerIconCompat.TYPE_TEXT, 12857), Helpers.createDevice(PointerIconCompat.TYPE_TEXT, 13604), Helpers.createDevice(1208, 1313), Helpers.createDevice(1208, 1314), Helpers.createDevice(1356, 1079), Helpers.createDevice(4525, 1), Helpers.createDevice(2915, 25904), Helpers.createDevice(2956, 8963), Helpers.createDevice(4362, 4432), Helpers.createDevice(1367, 8200));

    private PL2303Ids() {
    }

    public static boolean isDeviceSupported(int i, int i2) {
        return Helpers.exists(pl2303Devices, i, i2);
    }
}
