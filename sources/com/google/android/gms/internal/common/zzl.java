package com.google.android.gms.internal.common;

import android.content.Context;
import android.os.Build;

/* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
/* loaded from: classes.dex */
public final class zzl {
    private static volatile boolean zza = !zza();
    private static boolean zzb = false;

    public static boolean zza() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static Context zza(Context context) {
        return context.isDeviceProtectedStorage() ? context : context.createDeviceProtectedStorageContext();
    }
}
