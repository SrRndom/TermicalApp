package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaa {
    public final Uri zaa;

    public zaa(Uri uri) {
        this.zaa = uri;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zaa);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zaa) {
            return Objects.equal(((zaa) obj).zaa, this.zaa);
        }
        return false;
    }
}
