package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zap implements PendingResult.StatusListener {
    private final /* synthetic */ PendingResult zaa;
    private final /* synthetic */ TaskCompletionSource zab;
    private final /* synthetic */ PendingResultUtil.ResultConverter zac;
    private final /* synthetic */ PendingResultUtil.zaa zad;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zap(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, PendingResultUtil.ResultConverter resultConverter, PendingResultUtil.zaa zaaVar) {
        this.zaa = pendingResult;
        this.zab = taskCompletionSource;
        this.zac = resultConverter;
        this.zad = zaaVar;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        if (status.isSuccess()) {
            this.zab.setResult(this.zac.convert(this.zaa.await(0L, TimeUnit.MILLISECONDS)));
        } else {
            this.zab.setException(this.zad.zaa(status));
        }
    }
}
