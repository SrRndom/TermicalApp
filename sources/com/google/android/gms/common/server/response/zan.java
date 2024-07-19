package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zan extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zan> CREATOR = new zai();
    final String zaa;
    final FastJsonResponse.Field<?, ?> zab;
    private final int zac;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zan(int i, String str, FastJsonResponse.Field<?, ?> field) {
        this.zac = i;
        this.zaa = str;
        this.zab = field;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zan(String str, FastJsonResponse.Field<?, ?> field) {
        this.zac = 1;
        this.zaa = str;
        this.zab = field;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zac);
        SafeParcelWriter.writeString(parcel, 2, this.zaa, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zab, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
