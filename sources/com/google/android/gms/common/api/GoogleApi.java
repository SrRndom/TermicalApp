package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zabk;
import com.google.android.gms.common.api.internal.zacb;
import com.google.android.gms.common.api.internal.zax;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public class GoogleApi<O extends Api.ApiOptions> implements HasApiKey<O> {
    private final Context zaa;
    private final String zab;
    private final Api<O> zac;
    private final O zad;
    private final ApiKey<O> zae;
    private final Looper zaf;
    private final int zag;

    @NotOnlyInitialized
    private final GoogleApiClient zah;
    private final StatusExceptionMapper zai;
    private final GoogleApiManager zaj;

    /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
    /* loaded from: classes.dex */
    public static class Settings {
        public static final Settings DEFAULT_SETTINGS = new Builder().build();
        public final StatusExceptionMapper zaa;
        public final Looper zab;

        /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
        /* loaded from: classes.dex */
        public static class Builder {
            private StatusExceptionMapper zaa;
            private Looper zab;

            public Builder setMapper(StatusExceptionMapper statusExceptionMapper) {
                Preconditions.checkNotNull(statusExceptionMapper, "StatusExceptionMapper must not be null.");
                this.zaa = statusExceptionMapper;
                return this;
            }

            public Builder setLooper(Looper looper) {
                Preconditions.checkNotNull(looper, "Looper must not be null.");
                this.zab = looper;
                return this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public Settings build() {
                if (this.zaa == null) {
                    this.zaa = new ApiExceptionMapper();
                }
                if (this.zab == null) {
                    this.zab = Looper.getMainLooper();
                }
                return new Settings(this.zaa, this.zab);
            }
        }

        private Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper) {
            this.zaa = statusExceptionMapper;
            this.zab = looper;
        }
    }

    @Deprecated
    public GoogleApi(Context context, Api<O> api, O o, Looper looper, StatusExceptionMapper statusExceptionMapper) {
        this(context, api, o, new Settings.Builder().setLooper(looper).setMapper(statusExceptionMapper).build());
    }

    public GoogleApi(Activity activity, Api<O> api, O o, Settings settings) {
        Preconditions.checkNotNull(activity, "Null activity is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = activity.getApplicationContext();
        this.zaa = applicationContext;
        this.zab = zaa(activity);
        this.zac = api;
        this.zad = o;
        this.zaf = settings.zab;
        ApiKey<O> sharedApiKey = ApiKey.getSharedApiKey(api, o);
        this.zae = sharedApiKey;
        this.zah = new zabk(this);
        GoogleApiManager zaa = GoogleApiManager.zaa(applicationContext);
        this.zaj = zaa;
        this.zag = zaa.zab();
        this.zai = settings.zaa;
        if (!(activity instanceof GoogleApiActivity) && Looper.myLooper() == Looper.getMainLooper()) {
            zax.zaa(activity, zaa, sharedApiKey);
        }
        zaa.zaa((GoogleApi<?>) this);
    }

    public GoogleApi(Context context, Api<O> api, O o, Settings settings) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = context.getApplicationContext();
        this.zaa = applicationContext;
        this.zab = zaa(context);
        this.zac = api;
        this.zad = o;
        this.zaf = settings.zab;
        this.zae = ApiKey.getSharedApiKey(api, o);
        this.zah = new zabk(this);
        GoogleApiManager zaa = GoogleApiManager.zaa(applicationContext);
        this.zaj = zaa;
        this.zag = zaa.zab();
        this.zai = settings.zaa;
        zaa.zaa((GoogleApi<?>) this);
    }

    @Deprecated
    public GoogleApi(Activity activity, Api<O> api, O o, StatusExceptionMapper statusExceptionMapper) {
        this(activity, (Api) api, (Api.ApiOptions) o, new Settings.Builder().setMapper(statusExceptionMapper).setLooper(activity.getMainLooper()).build());
    }

    @Deprecated
    public GoogleApi(Context context, Api<O> api, O o, StatusExceptionMapper statusExceptionMapper) {
        this(context, api, o, new Settings.Builder().setMapper(statusExceptionMapper).build());
    }

    private final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zaa(int i, T t) {
        t.zab();
        this.zaj.zaa(this, i, t);
        return t;
    }

    private final <TResult, A extends Api.AnyClient> Task<TResult> zaa(int i, TaskApiCall<A, TResult> taskApiCall) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zaj.zaa(this, i, taskApiCall, taskCompletionSource, this.zai);
        return taskCompletionSource.getTask();
    }

    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doRead(T t) {
        return (T) zaa(0, (int) t);
    }

    public <TResult, A extends Api.AnyClient> Task<TResult> doRead(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(0, taskApiCall);
    }

    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(T t) {
        return (T) zaa(1, (int) t);
    }

    public <TResult, A extends Api.AnyClient> Task<TResult> doWrite(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(1, taskApiCall);
    }

    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(T t) {
        return (T) zaa(2, (int) t);
    }

    public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(2, taskApiCall);
    }

    @Deprecated
    public <A extends Api.AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(T t, U u) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(u);
        Preconditions.checkNotNull(t.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(u.getListenerKey(), "Listener has already been released.");
        Preconditions.checkArgument(Objects.equal(t.getListenerKey(), u.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zaj.zaa(this, t, u, zad.zaa);
    }

    public <A extends Api.AnyClient> Task<Void> doRegisterEventListener(RegistrationMethods<A, ?> registrationMethods) {
        Preconditions.checkNotNull(registrationMethods);
        Preconditions.checkNotNull(registrationMethods.zaa.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(registrationMethods.zab.getListenerKey(), "Listener has already been released.");
        return this.zaj.zaa(this, registrationMethods.zaa, registrationMethods.zab, registrationMethods.zac);
    }

    public Task<Boolean> doUnregisterEventListener(ListenerHolder.ListenerKey<?> listenerKey) {
        Preconditions.checkNotNull(listenerKey, "Listener key cannot be null.");
        return this.zaj.zaa(this, listenerKey);
    }

    public <L> ListenerHolder<L> registerListener(L l, String str) {
        return ListenerHolders.createListenerHolder(l, this.zaf, str);
    }

    protected Task<Boolean> disconnectService() {
        return this.zaj.zab((GoogleApi<?>) this);
    }

    public final Api.Client zaa(Looper looper, GoogleApiManager.zaa<O> zaaVar) {
        return ((Api.AbstractClientBuilder) Preconditions.checkNotNull(this.zac.zab())).buildClient(this.zaa, looper, createClientSettingsBuilder().build(), (ClientSettings) this.zad, (GoogleApiClient.ConnectionCallbacks) zaaVar, (GoogleApiClient.OnConnectionFailedListener) zaaVar);
    }

    public O getApiOptions() {
        return this.zad;
    }

    @Override // com.google.android.gms.common.api.HasApiKey
    public ApiKey<O> getApiKey() {
        return this.zae;
    }

    public final int zaa() {
        return this.zag;
    }

    public GoogleApiClient asGoogleApiClient() {
        return this.zah;
    }

    public Looper getLooper() {
        return this.zaf;
    }

    public Context getApplicationContext() {
        return this.zaa;
    }

    @Deprecated
    protected String getContextFeatureId() {
        return this.zab;
    }

    protected String getContextAttributionTag() {
        return this.zab;
    }

    protected ClientSettings.Builder createClientSettingsBuilder() {
        Account account;
        Set<Scope> emptySet;
        GoogleSignInAccount googleSignInAccount;
        GoogleSignInAccount googleSignInAccount2;
        ClientSettings.Builder builder = new ClientSettings.Builder();
        O o = this.zad;
        if ((o instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) && (googleSignInAccount2 = ((Api.ApiOptions.HasGoogleSignInAccountOptions) o).getGoogleSignInAccount()) != null) {
            account = googleSignInAccount2.getAccount();
        } else {
            O o2 = this.zad;
            account = o2 instanceof Api.ApiOptions.HasAccountOptions ? ((Api.ApiOptions.HasAccountOptions) o2).getAccount() : null;
        }
        ClientSettings.Builder zaa = builder.zaa(account);
        O o3 = this.zad;
        if ((o3 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) && (googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) o3).getGoogleSignInAccount()) != null) {
            emptySet = googleSignInAccount.getRequestedScopes();
        } else {
            emptySet = Collections.emptySet();
        }
        return zaa.zaa(emptySet).zaa(this.zaa.getClass().getName()).setRealClientPackageName(this.zaa.getPackageName());
    }

    private static String zaa(Object obj) {
        if (!PlatformVersion.isAtLeastR()) {
            return null;
        }
        try {
            return (String) Context.class.getMethod("getAttributionTag", new Class[0]).invoke(obj, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    public final zacb zaa(Context context, Handler handler) {
        return new zacb(context, handler, createClientSettingsBuilder().build());
    }
}
