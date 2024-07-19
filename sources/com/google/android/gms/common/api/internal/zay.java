package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [TResult] */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zay<TResult> implements OnCompleteListener<TResult> {
    private final /* synthetic */ TaskCompletionSource zaa;
    private final /* synthetic */ zaw zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zay(zaw zawVar, TaskCompletionSource taskCompletionSource) {
        this.zab = zawVar;
        this.zaa = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<TResult> task) {
        Map map;
        map = this.zab.zab;
        map.remove(this.zaa);
    }
}
