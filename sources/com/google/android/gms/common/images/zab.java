package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zaj;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public abstract class zab {
    final zaa zaa;
    protected int zab;
    private int zac = 0;
    private boolean zad = false;
    private boolean zae = true;
    private boolean zaf = false;
    private boolean zag = true;

    public zab(Uri uri, int i) {
        this.zab = 0;
        this.zaa = new zaa(uri);
        this.zab = i;
    }

    protected abstract void zaa(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zaa(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        zaa(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zaa(Context context, zaj zajVar) {
        if (this.zag) {
            zaa(null, false, true, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zaa(Context context, zaj zajVar, boolean z) {
        int i = this.zab;
        zaa(i != 0 ? context.getResources().getDrawable(i) : null, z, false, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zaa(boolean z, boolean z2) {
        return (!this.zae || z2 || z) ? false : true;
    }
}
