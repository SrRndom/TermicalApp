package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.util.Collections;
import java.util.Iterator;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaaq implements zaay {

    @NotOnlyInitialized
    private final zaax zaa;

    public zaaq(zaax zaaxVar) {
        this.zaa = zaaxVar;
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(int i) {
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final boolean zab() {
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zaa() {
        Iterator<Api.Client> it = this.zaa.zaa.values().iterator();
        while (it.hasNext()) {
            it.next().disconnect();
        }
        this.zaa.zad.zac = Collections.emptySet();
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T zaa(T t) {
        this.zaa.zad.zaa.add(t);
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zab(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // com.google.android.gms.common.api.internal.zaay
    public final void zac() {
        this.zaa.zah();
    }
}
