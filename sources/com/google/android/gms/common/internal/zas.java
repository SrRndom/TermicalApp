package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zas extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zas> CREATOR = new zav();
    private final int zaa;
    private IBinder zab;
    private ConnectionResult zac;
    private boolean zad;
    private boolean zae;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zas(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.zaa = i;
        this.zab = iBinder;
        this.zac = connectionResult;
        this.zad = z;
        this.zae = z2;
    }

    public final IAccountAccessor zaa() {
        IBinder iBinder = this.zab;
        if (iBinder == null) {
            return null;
        }
        return IAccountAccessor.Stub.asInterface(iBinder);
    }

    public final ConnectionResult zab() {
        return this.zac;
    }

    public final boolean zac() {
        return this.zad;
    }

    public final boolean zad() {
        return this.zae;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zab, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zac, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zad);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zae);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zas)) {
            return false;
        }
        zas zasVar = (zas) obj;
        return this.zac.equals(zasVar.zac) && Objects.equal(zaa(), zasVar.zaa());
    }
}
