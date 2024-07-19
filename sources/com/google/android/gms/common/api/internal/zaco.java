package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zaco implements zacn {
    private final /* synthetic */ zacl zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaco(zacl zaclVar) {
        this.zaa = zaclVar;
    }

    @Override // com.google.android.gms.common.api.internal.zacn
    public final void zaa(BasePendingResult<?> basePendingResult) {
        this.zaa.zab.remove(basePendingResult);
    }
}
