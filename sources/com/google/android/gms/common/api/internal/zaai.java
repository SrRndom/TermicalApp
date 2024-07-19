package com.google.android.gms.common.api.internal;

import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaai extends zaan {
    final /* synthetic */ zaad zaa;
    private final Map<Api.Client, zaaf> zab;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaai(zaad zaadVar, Map<Api.Client, zaaf> map) {
        super(zaadVar, null);
        this.zaa = zaadVar;
        this.zab = map;
    }

    @Override // com.google.android.gms.common.api.internal.zaan
    public final void zaa() {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Context context;
        boolean z;
        Context context2;
        zaax zaaxVar;
        com.google.android.gms.signin.zad zadVar;
        com.google.android.gms.signin.zad zadVar2;
        zaax zaaxVar2;
        Context context3;
        boolean z2;
        googleApiAvailabilityLight = this.zaa.zad;
        com.google.android.gms.common.internal.zaj zajVar = new com.google.android.gms.common.internal.zaj(googleApiAvailabilityLight);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client client : this.zab.keySet()) {
            if (client.requiresGooglePlayServices()) {
                z2 = this.zab.get(client).zac;
                if (!z2) {
                    arrayList.add(client);
                }
            }
            arrayList2.add(client);
        }
        int i = -1;
        int i2 = 0;
        if (!arrayList.isEmpty()) {
            int size = arrayList.size();
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                context = this.zaa.zac;
                i = zajVar.zaa(context, (Api.Client) obj);
                if (i != 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList2.size();
            while (i2 < size2) {
                Object obj2 = arrayList2.get(i2);
                i2++;
                context3 = this.zaa.zac;
                i = zajVar.zaa(context3, (Api.Client) obj2);
                if (i == 0) {
                    break;
                }
            }
        }
        if (i != 0) {
            ConnectionResult connectionResult = new ConnectionResult(i, null);
            zaaxVar2 = this.zaa.zaa;
            zaaxVar2.zaa(new zaah(this, this.zaa, connectionResult));
            return;
        }
        z = this.zaa.zam;
        if (z) {
            zadVar = this.zaa.zak;
            if (zadVar != null) {
                zadVar2 = this.zaa.zak;
                zadVar2.zab();
            }
        }
        for (Api.Client client2 : this.zab.keySet()) {
            zaaf zaafVar = this.zab.get(client2);
            if (client2.requiresGooglePlayServices()) {
                context2 = this.zaa.zac;
                if (zajVar.zaa(context2, client2) != 0) {
                    zaaxVar = this.zaa.zaa;
                    zaaxVar.zaa(new zaak(this, this.zaa, zaafVar));
                }
            }
            client2.connect(zaafVar);
        }
    }
}
