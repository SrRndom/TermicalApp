package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaj {
    private final SparseIntArray zaa;
    private GoogleApiAvailabilityLight zab;

    public zaj() {
        this(GoogleApiAvailability.getInstance());
    }

    public zaj(GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.zaa = new SparseIntArray();
        Preconditions.checkNotNull(googleApiAvailabilityLight);
        this.zab = googleApiAvailabilityLight;
    }

    public final int zaa(Context context, Api.Client client) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(client);
        int i = 0;
        if (!client.requiresGooglePlayServices()) {
            return 0;
        }
        int minApkVersion = client.getMinApkVersion();
        int i2 = this.zaa.get(minApkVersion, -1);
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= this.zaa.size()) {
                i = i2;
                break;
            }
            int keyAt = this.zaa.keyAt(i3);
            if (keyAt > minApkVersion && this.zaa.get(keyAt) == 0) {
                break;
            }
            i3++;
        }
        if (i == -1) {
            i = this.zab.isGooglePlayServicesAvailable(context, minApkVersion);
        }
        this.zaa.put(minApkVersion, i);
        return i;
    }

    public final void zaa() {
        this.zaa.clear();
    }
}
