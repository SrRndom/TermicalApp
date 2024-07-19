package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zal implements Runnable {
    final /* synthetic */ zak zaa;
    private final zam zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zal(zak zakVar, zam zamVar) {
        this.zaa = zakVar;
        this.zab = zamVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zaa.zaa) {
            ConnectionResult zab = this.zab.zab();
            if (zab.hasResolution()) {
                this.zaa.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(this.zaa.getActivity(), (PendingIntent) Preconditions.checkNotNull(zab.getResolution()), this.zab.zaa(), false), 1);
                return;
            }
            if (this.zaa.zac.getErrorResolutionIntent(this.zaa.getActivity(), zab.getErrorCode(), null) != null) {
                this.zaa.zac.zaa(this.zaa.getActivity(), this.zaa.mLifecycleFragment, zab.getErrorCode(), 2, this.zaa);
            } else if (zab.getErrorCode() == 18) {
                this.zaa.zac.zaa(this.zaa.getActivity().getApplicationContext(), new zan(this, GoogleApiAvailability.zaa(this.zaa.getActivity(), this.zaa)));
            } else {
                this.zaa.zaa(zab, this.zab.zaa());
            }
        }
    }
}
