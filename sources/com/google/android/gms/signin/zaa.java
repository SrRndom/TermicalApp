package com.google.android.gms.signin;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.internal.SignInClientImpl;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaa {
    public static final Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> zaa;
    public static final Api<SignInOptions> zab;
    private static final Api.ClientKey<SignInClientImpl> zac;
    private static final Api.ClientKey<SignInClientImpl> zad;
    private static final Api.AbstractClientBuilder<SignInClientImpl, zae> zae;
    private static final Scope zaf;
    private static final Scope zag;
    private static final Api<zae> zah;

    static {
        Api.ClientKey<SignInClientImpl> clientKey = new Api.ClientKey<>();
        zac = clientKey;
        Api.ClientKey<SignInClientImpl> clientKey2 = new Api.ClientKey<>();
        zad = clientKey2;
        zac zacVar = new zac();
        zaa = zacVar;
        zab zabVar = new zab();
        zae = zabVar;
        zaf = new Scope(Scopes.PROFILE);
        zag = new Scope("email");
        zab = new Api<>("SignIn.API", zacVar, clientKey);
        zah = new Api<>("SignIn.INTERNAL_API", zabVar, clientKey2);
    }
}
