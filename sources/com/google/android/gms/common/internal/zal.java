package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zal extends com.google.android.gms.internal.base.zab implements zam {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }

    @Override // com.google.android.gms.common.internal.zam
    public final IObjectWrapper zaa(IObjectWrapper iObjectWrapper, zau zauVar) throws RemoteException {
        Parcel zaa = zaa();
        com.google.android.gms.internal.base.zad.zaa(zaa, iObjectWrapper);
        com.google.android.gms.internal.base.zad.zaa(zaa, zauVar);
        Parcel zaa2 = zaa(2, zaa);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zaa2.readStrongBinder());
        zaa2.recycle();
        return asInterface;
    }
}
