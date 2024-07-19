package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zar;
import com.google.android.gms.signin.SignInOptions;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public class SignInClientImpl extends GmsClient<zae> implements com.google.android.gms.signin.zad {
    private final boolean zaa;
    private final ClientSettings zab;
    private final Bundle zac;
    private final Integer zad;

    public SignInClientImpl(Context context, Looper looper, boolean z, ClientSettings clientSettings, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zaa = z;
        this.zab = clientSettings;
        this.zac = bundle;
        this.zad = clientSettings.zad();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    public SignInClientImpl(Context context, Looper looper, boolean z, ClientSettings clientSettings, SignInOptions signInOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, true, clientSettings, createBundleFromClientSettings(clientSettings), connectionCallbacks, onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public boolean requiresSignIn() {
        return this.zaa;
    }

    @Override // com.google.android.gms.signin.zad
    public final void zaa(IAccountAccessor iAccountAccessor, boolean z) {
        try {
            ((zae) getService()).zaa(iAccountAccessor, ((Integer) Preconditions.checkNotNull(this.zad)).intValue(), z);
        } catch (RemoteException unused) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    @Override // com.google.android.gms.signin.zad
    public final void zaa() {
        try {
            ((zae) getService()).zaa(((Integer) Preconditions.checkNotNull(this.zad)).intValue());
        } catch (RemoteException unused) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    @Override // com.google.android.gms.signin.zad
    public final void zaa(zac zacVar) {
        Preconditions.checkNotNull(zacVar, "Expecting a valid ISignInCallbacks");
        try {
            Account accountOrDefault = this.zab.getAccountOrDefault();
            ((zae) getService()).zaa(new zak(new zar(accountOrDefault, ((Integer) Preconditions.checkNotNull(this.zad)).intValue(), "<<default account>>".equals(accountOrDefault.name) ? Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount() : null)), zacVar);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zacVar.zaa(new zam(8));
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected Bundle getGetServiceRequestExtraArgs() {
        if (!getContext().getPackageName().equals(this.zab.getRealClientPackageName())) {
            this.zac.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zab.getRealClientPackageName());
        }
        return this.zac;
    }

    @Override // com.google.android.gms.signin.zad
    public final void zab() {
        connect(new BaseGmsClient.LegacyClientCallbackAdapter());
    }

    public static Bundle createBundleFromClientSettings(ClientSettings clientSettings) {
        SignInOptions zac = clientSettings.zac();
        Integer zad = clientSettings.zad();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", clientSettings.getAccount());
        if (zad != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", zad.intValue());
        }
        if (zac != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", null);
            bundle.putString("com.google.android.gms.signin.internal.logSessionId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        }
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if (queryLocalInterface instanceof zae) {
            return (zae) queryLocalInterface;
        }
        return new zah(iBinder);
    }
}
