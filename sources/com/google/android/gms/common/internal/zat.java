package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zat implements Parcelable.Creator<zar> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zar[] newArray(int i) {
        return new zar[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zar createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Account account = null;
        GoogleSignInAccount googleSignInAccount = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 2) {
                account = (Account) SafeParcelReader.createParcelable(parcel, readHeader, Account.CREATOR);
            } else if (fieldId == 3) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 4) {
                googleSignInAccount = (GoogleSignInAccount) SafeParcelReader.createParcelable(parcel, readHeader, GoogleSignInAccount.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zar(i, account, i2, googleSignInAccount);
    }
}
