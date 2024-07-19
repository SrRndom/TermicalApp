package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zam extends com.google.android.gms.internal.base.zab implements zak {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zam(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    @Override // com.google.android.gms.common.internal.service.zak
    public final void zaa(zai zaiVar) throws RemoteException {
        Parcel zaa = zaa();
        com.google.android.gms.internal.base.zad.zaa(zaa, zaiVar);
        zac(1, zaa);
    }
}
