package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaaz extends com.google.android.gms.internal.base.zap {
    private final /* synthetic */ zaax zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaaz(zaax zaaxVar, Looper looper) {
        super(looper);
        this.zaa = zaaxVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            ((zaba) message.obj).zaa(this.zaa);
            return;
        }
        if (i == 2) {
            throw ((RuntimeException) message.obj);
        }
        int i2 = message.what;
        StringBuilder sb = new StringBuilder(31);
        sb.append("Unknown message id: ");
        sb.append(i2);
        Log.w("GACStateManager", sb.toString());
    }
}
