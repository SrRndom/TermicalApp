package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.base.zak;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zad extends zab {
    private WeakReference<ImageView> zac;

    public zad(ImageView imageView, Uri uri) {
        super(uri, 0);
        Asserts.checkNotNull(imageView);
        this.zac = new WeakReference<>(imageView);
    }

    public final int hashCode() {
        return 0;
    }

    public zad(ImageView imageView, int i) {
        super(Uri.EMPTY, i);
        Asserts.checkNotNull(imageView);
        this.zac = new WeakReference<>(imageView);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zad)) {
            return false;
        }
        ImageView imageView = this.zac.get();
        ImageView imageView2 = ((zad) obj).zac.get();
        return (imageView2 == null || imageView == null || !Objects.equal(imageView2, imageView)) ? false : true;
    }

    @Override // com.google.android.gms.common.images.zab
    protected final void zaa(Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageView imageView = this.zac.get();
        if (imageView != null) {
            boolean z4 = (z2 || z3) ? false : true;
            if (z4 && (imageView instanceof zak)) {
                int zaa = zak.zaa();
                if (this.zab != 0 && zaa == this.zab) {
                    return;
                }
            }
            boolean zaa2 = zaa(z, z2);
            if (zaa2) {
                Drawable drawable2 = imageView.getDrawable();
                if (drawable2 == null) {
                    drawable2 = null;
                } else if (drawable2 instanceof com.google.android.gms.internal.base.zae) {
                    drawable2 = ((com.google.android.gms.internal.base.zae) drawable2).zaa();
                }
                drawable = new com.google.android.gms.internal.base.zae(drawable2, drawable);
            }
            imageView.setImageDrawable(drawable);
            if (imageView instanceof zak) {
                zak.zaa(z3 ? this.zaa.zaa : Uri.EMPTY);
                zak.zaa(z4 ? this.zab : 0);
            }
            if (drawable == null || !zaa2) {
                return;
            }
            ((com.google.android.gms.internal.base.zae) drawable).zaa(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        }
    }
}
