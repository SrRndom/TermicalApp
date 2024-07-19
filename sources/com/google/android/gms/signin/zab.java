package com.google.android.gms.signin;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.SignInClientImpl;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zab extends Api.AbstractClientBuilder<SignInClientImpl, zae> {
    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ SignInClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, zae zaeVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new SignInClientImpl(context, looper, false, clientSettings, zae.zaa(), connectionCallbacks, onConnectionFailedListener);
    }
}
