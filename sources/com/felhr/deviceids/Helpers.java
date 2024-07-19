package com.felhr.deviceids;

import java.util.Arrays;

/* loaded from: classes.dex */
class Helpers {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static long createDevice(int i, int i2) {
        return (i2 & 4294967295L) | (i << 32);
    }

    Helpers() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long[] createTable(long... jArr) {
        Arrays.sort(jArr);
        return jArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean exists(long[] jArr, int i, int i2) {
        return Arrays.binarySearch(jArr, createDevice(i, i2)) >= 0;
    }
}
