package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zacm extends com.google.android.gms.internal.base.zap {
    private final /* synthetic */ zack zaa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zacm(zack zackVar, Looper looper) {
        super(looper);
        this.zaa = zackVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        Object obj;
        zack zackVar;
        int i = message.what;
        if (i != 0) {
            if (i == 1) {
                RuntimeException runtimeException = (RuntimeException) message.obj;
                String valueOf = String.valueOf(runtimeException.getMessage());
                Log.e("TransformedResultImpl", valueOf.length() != 0 ? "Runtime exception on the transformation worker thread: ".concat(valueOf) : new String("Runtime exception on the transformation worker thread: "));
                throw runtimeException;
            }
            int i2 = message.what;
            StringBuilder sb = new StringBuilder(70);
            sb.append("TransformationResultHandler received unknown message type: ");
            sb.append(i2);
            Log.e("TransformedResultImpl", sb.toString());
            return;
        }
        PendingResult<?> pendingResult = (PendingResult) message.obj;
        obj = this.zaa.zae;
        synchronized (obj) {
            zackVar = this.zaa.zab;
            zack zackVar2 = (zack) Preconditions.checkNotNull(zackVar);
            if (pendingResult != null) {
                if (!(pendingResult instanceof zabz)) {
                    zackVar2.zaa(pendingResult);
                } else {
                    zackVar2.zaa(((zabz) pendingResult).zaa());
                }
            } else {
                zackVar2.zaa(new Status(13, "Transform returned null"));
            }
        }
    }
}
