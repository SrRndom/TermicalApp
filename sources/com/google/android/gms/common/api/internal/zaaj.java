package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaaj extends zaan {
    private final ArrayList<Api.Client> zaa;
    private final /* synthetic */ zaad zab;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaaj(zaad zaadVar, ArrayList<Api.Client> arrayList) {
        super(zaadVar, null);
        this.zab = zaadVar;
        this.zaa = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.zaan
    public final void zaa() {
        zaax zaaxVar;
        Set<Scope> zai;
        IAccountAccessor iAccountAccessor;
        zaax zaaxVar2;
        zaaxVar = this.zab.zaa;
        zaap zaapVar = zaaxVar.zad;
        zai = this.zab.zai();
        zaapVar.zac = zai;
        ArrayList<Api.Client> arrayList = this.zaa;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Api.Client client = arrayList.get(i);
            i++;
            iAccountAccessor = this.zab.zao;
            zaaxVar2 = this.zab.zaa;
            client.getRemoteService(iAccountAccessor, zaaxVar2.zad.zac);
        }
    }
}
