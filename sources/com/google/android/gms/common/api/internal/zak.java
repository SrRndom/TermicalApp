package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public abstract class zak extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean zaa;
    protected final AtomicReference<zam> zab;
    protected final GoogleApiAvailability zac;
    private final Handler zad;

    /* JADX INFO: Access modifiers changed from: protected */
    public zak(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }

    protected abstract void zaa();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void zaa(ConnectionResult connectionResult, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public zak(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zab = new AtomicReference<>(null);
        this.zad = new com.google.android.gms.internal.base.zap(Looper.getMainLooper());
        this.zac = googleApiAvailability;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        zaa(new ConnectionResult(13, null), zaa(this.zab.get()));
        zab();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zab.set(bundle.getBoolean("resolving_error", false) ? new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zam zamVar = this.zab.get();
        if (zamVar != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zamVar.zaa());
            bundle.putInt("failed_status", zamVar.zab().getErrorCode());
            bundle.putParcelable("failed_resolution", zamVar.zab().getResolution());
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.zaa = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onActivityResult(int i, int i2, Intent intent) {
        zam zamVar = this.zab.get();
        if (i != 1) {
            if (i == 2) {
                int isGooglePlayServicesAvailable = this.zac.isGooglePlayServicesAvailable(getActivity());
                r1 = isGooglePlayServicesAvailable == 0;
                if (zamVar == null) {
                    return;
                }
                if (zamVar.zab().getErrorCode() == 18 && isGooglePlayServicesAvailable == 18) {
                    return;
                }
            }
            r1 = false;
        } else if (i2 != -1) {
            if (i2 == 0) {
                if (zamVar == null) {
                    return;
                }
                zam zamVar2 = new zam(new ConnectionResult(intent != null ? intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13) : 13, null, zamVar.zab().toString()), zaa(zamVar));
                this.zab.set(zamVar2);
                zamVar = zamVar2;
            }
            r1 = false;
        }
        if (r1) {
            zab();
        } else if (zamVar != null) {
            zaa(zamVar.zab(), zamVar.zaa());
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.zaa = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zab() {
        this.zab.set(null);
        zaa();
    }

    public final void zab(ConnectionResult connectionResult, int i) {
        zam zamVar = new zam(connectionResult, i);
        if (this.zab.compareAndSet(null, zamVar)) {
            this.zad.post(new zal(this, zamVar));
        }
    }

    private static int zaa(zam zamVar) {
        if (zamVar == null) {
            return -1;
        }
        return zamVar.zaa();
    }
}
