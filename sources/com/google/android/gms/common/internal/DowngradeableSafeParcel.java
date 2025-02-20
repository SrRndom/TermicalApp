package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
/* loaded from: classes.dex */
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zza = new Object();
    private static ClassLoader zzb;
    private static Integer zzc;
    private boolean zzd = false;

    protected abstract boolean prepareForClientVersion(int i);

    private static ClassLoader zza() {
        synchronized (zza) {
        }
        return null;
    }

    protected static Integer getUnparcelClientVersion() {
        synchronized (zza) {
        }
        return null;
    }

    protected boolean shouldDowngrade() {
        return this.zzd;
    }

    public void setShouldDowngrade(boolean z) {
        this.zzd = z;
    }

    protected static boolean canUnparcelSafely(String str) {
        zza();
        return true;
    }
}
