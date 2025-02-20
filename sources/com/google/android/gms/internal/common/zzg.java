package com.google.android.gms.internal.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
/* loaded from: classes.dex */
final class zzg implements zzf {
    private zzg() {
    }

    @Override // com.google.android.gms.internal.common.zzf
    public final ScheduledExecutorService zza(int i, int i2) {
        return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1));
    }
}
