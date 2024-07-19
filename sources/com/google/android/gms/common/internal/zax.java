package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zax implements Parcelable.Creator<zau> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zau[] newArray(int i) {
        return new zau[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zau createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        Scope[] scopeArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 2) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 4) {
                scopeArr = (Scope[]) SafeParcelReader.createTypedArray(parcel, readHeader, Scope.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zau(i, i2, i3, scopeArr);
    }
}
