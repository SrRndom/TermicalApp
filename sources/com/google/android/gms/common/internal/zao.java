package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* JADX INFO: Add missing generic type declarations: [R, T] */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zao<R, T> implements PendingResultUtil.ResultConverter<R, T> {
    private final /* synthetic */ Response zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zao(Response response) {
        this.zaa = response;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Object convert(Result result) {
        this.zaa.setResult(result);
        return this.zaa;
    }
}
