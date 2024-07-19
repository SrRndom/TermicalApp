package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaca {
    private static final ExecutorService zaa = com.google.android.gms.internal.base.zal.zaa().zaa(new NumberedThreadFactory("GAC_Transform"), com.google.android.gms.internal.base.zaq.zaa);

    public static ExecutorService zaa() {
        return zaa;
    }
}
