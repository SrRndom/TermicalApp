package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class ListenerHolder<L> {
    private final zaa zaa;
    private volatile L zab;
    private volatile ListenerKey<L> zac;

    /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
    /* loaded from: classes.dex */
    public interface Notifier<L> {
        void notifyListener(L l);

        void onNotifyListenerFailed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenerHolder(Looper looper, L l, String str) {
        this.zaa = new zaa(looper);
        this.zab = (L) Preconditions.checkNotNull(l, "Listener must not be null");
        this.zac = new ListenerKey<>(l, Preconditions.checkNotEmpty(str));
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
    /* loaded from: classes.dex */
    private final class zaa extends com.google.android.gms.internal.base.zap {
        public zaa(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Preconditions.checkArgument(message.what == 1);
            ListenerHolder.this.notifyListenerInternal((Notifier) message.obj);
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
    /* loaded from: classes.dex */
    public static final class ListenerKey<L> {
        private final L zaa;
        private final String zab;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ListenerKey(L l, String str) {
            this.zaa = l;
            this.zab = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey)) {
                return false;
            }
            ListenerKey listenerKey = (ListenerKey) obj;
            return this.zaa == listenerKey.zaa && this.zab.equals(listenerKey.zab);
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zaa) * 31) + this.zab.hashCode();
        }
    }

    public final void notifyListener(Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        this.zaa.sendMessage(this.zaa.obtainMessage(1, notifier));
    }

    public final boolean hasListener() {
        return this.zab != null;
    }

    public final void clear() {
        this.zab = null;
        this.zac = null;
    }

    public final ListenerKey<L> getListenerKey() {
        return this.zac;
    }

    final void notifyListenerInternal(Notifier<? super L> notifier) {
        L l = this.zab;
        if (l == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(l);
        } catch (RuntimeException e) {
            notifier.onNotifyListenerFailed();
            throw e;
        }
    }
}
