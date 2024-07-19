package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public class zax extends zak {
    private final ArraySet<ApiKey<?>> zad;
    private final GoogleApiManager zae;

    public static void zaa(Activity activity, GoogleApiManager googleApiManager, ApiKey<?> apiKey) {
        LifecycleFragment fragment = getFragment(activity);
        zax zaxVar = (zax) fragment.getCallbackOrNull("ConnectionlessLifecycleHelper", zax.class);
        if (zaxVar == null) {
            zaxVar = new zax(fragment, googleApiManager);
        }
        Preconditions.checkNotNull(apiKey, "ApiKey cannot be null");
        zaxVar.zad.add(apiKey);
        googleApiManager.zaa(zaxVar);
    }

    private zax(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager) {
        this(lifecycleFragment, googleApiManager, GoogleApiAvailability.getInstance());
    }

    private zax(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment, googleApiAvailability);
        this.zad = new ArraySet<>();
        this.zae = googleApiManager;
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    @Override // com.google.android.gms.common.api.internal.zak, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        zad();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onResume() {
        super.onResume();
        zad();
    }

    @Override // com.google.android.gms.common.api.internal.zak, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.zae.zab(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.zak
    public final void zaa(ConnectionResult connectionResult, int i) {
        this.zae.zab(connectionResult, i);
    }

    @Override // com.google.android.gms.common.api.internal.zak
    protected final void zaa() {
        this.zae.zac();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ArraySet<ApiKey<?>> zac() {
        return this.zad;
    }

    private final void zad() {
        if (this.zad.isEmpty()) {
            return;
        }
        this.zae.zaa(this);
    }
}
