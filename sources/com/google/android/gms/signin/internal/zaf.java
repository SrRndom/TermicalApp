package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public abstract class zaf extends com.google.android.gms.internal.base.zaa implements zac {
    public zaf() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    @Override // com.google.android.gms.internal.base.zaa
    protected final boolean zaa(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 3:
                zaa((ConnectionResult) com.google.android.gms.internal.base.zad.zaa(parcel, ConnectionResult.CREATOR), (zab) com.google.android.gms.internal.base.zad.zaa(parcel, zab.CREATOR));
                break;
            case 4:
                zaa((Status) com.google.android.gms.internal.base.zad.zaa(parcel, Status.CREATOR));
                break;
            case 5:
            default:
                return false;
            case 6:
                zab((Status) com.google.android.gms.internal.base.zad.zaa(parcel, Status.CREATOR));
                break;
            case 7:
                zaa((Status) com.google.android.gms.internal.base.zad.zaa(parcel, Status.CREATOR), (GoogleSignInAccount) com.google.android.gms.internal.base.zad.zaa(parcel, GoogleSignInAccount.CREATOR));
                break;
            case 8:
                zaa((zam) com.google.android.gms.internal.base.zad.zaa(parcel, zam.CREATOR));
                break;
            case 9:
                zaa((zag) com.google.android.gms.internal.base.zad.zaa(parcel, zag.CREATOR));
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
