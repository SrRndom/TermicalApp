package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zas;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zal implements Parcelable.Creator<zam> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zam[] newArray(int i) {
        return new zam[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zam createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ConnectionResult connectionResult = null;
        zas zasVar = null;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 2) {
                connectionResult = (ConnectionResult) SafeParcelReader.createParcelable(parcel, readHeader, ConnectionResult.CREATOR);
            } else if (fieldId == 3) {
                zasVar = (zas) SafeParcelReader.createParcelable(parcel, readHeader, zas.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zam(i, connectionResult, zasVar);
    }
}
