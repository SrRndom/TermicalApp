package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.FieldConverter<String, Integer> {
    public static final Parcelable.Creator<StringToIntConverter> CREATOR = new zac();
    private final int zaa;
    private final HashMap<String, Integer> zab;
    private final SparseArray<String> zac;
    private final ArrayList<zaa> zad;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StringToIntConverter(int i, ArrayList<zaa> arrayList) {
        this.zaa = i;
        this.zab = new HashMap<>();
        this.zac = new SparseArray<>();
        this.zad = null;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            zaa zaaVar = arrayList.get(i2);
            i2++;
            zaa zaaVar2 = zaaVar;
            add(zaaVar2.zaa, zaaVar2.zab);
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final int zaa() {
        return 7;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final int zab() {
        return 0;
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
    /* loaded from: classes.dex */
    public static final class zaa extends AbstractSafeParcelable {
        public static final Parcelable.Creator<zaa> CREATOR = new zad();
        final String zaa;
        final int zab;
        private final int zac;

        /* JADX INFO: Access modifiers changed from: package-private */
        public zaa(int i, String str, int i2) {
            this.zac = i;
            this.zaa = str;
            this.zab = i2;
        }

        zaa(String str, int i) {
            this.zac = 1;
            this.zaa = str;
            this.zab = i;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.zac);
            SafeParcelWriter.writeString(parcel, 2, this.zaa, false);
            SafeParcelWriter.writeInt(parcel, 3, this.zab);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public StringToIntConverter() {
        this.zaa = 1;
        this.zab = new HashMap<>();
        this.zac = new SparseArray<>();
        this.zad = null;
    }

    public final StringToIntConverter add(String str, int i) {
        this.zab.put(str, Integer.valueOf(i));
        this.zac.put(i, str);
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zab.keySet()) {
            arrayList.add(new zaa(str, this.zab.get(str).intValue()));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final /* synthetic */ String zaa(Integer num) {
        String str = this.zac.get(num.intValue());
        return (str == null && this.zab.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final /* synthetic */ Integer zab(String str) {
        Integer num = this.zab.get(str);
        return num == null ? this.zab.get("gms_unknown") : num;
    }
}
