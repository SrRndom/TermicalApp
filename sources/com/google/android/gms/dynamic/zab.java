package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zab<T> implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zab(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zaa = deferredLifecycleHelper;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        LinkedList linkedList;
        LinkedList linkedList2;
        LifecycleDelegate lifecycleDelegate2;
        this.zaa.zaa = lifecycleDelegate;
        linkedList = this.zaa.zac;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            DeferredLifecycleHelper.zaa zaaVar = (DeferredLifecycleHelper.zaa) it.next();
            lifecycleDelegate2 = this.zaa.zaa;
            zaaVar.zaa(lifecycleDelegate2);
        }
        linkedList2 = this.zaa.zac;
        linkedList2.clear();
        DeferredLifecycleHelper.zaa(this.zaa, (Bundle) null);
    }
}
