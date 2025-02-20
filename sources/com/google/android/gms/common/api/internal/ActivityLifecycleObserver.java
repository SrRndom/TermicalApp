package com.google.android.gms.common.api.internal;

import android.app.Activity;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public abstract class ActivityLifecycleObserver {
    public abstract ActivityLifecycleObserver onStopCallOnce(Runnable runnable);

    public static final ActivityLifecycleObserver of(Activity activity) {
        return new zaa(activity);
    }
}
