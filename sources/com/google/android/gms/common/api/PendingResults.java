package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class PendingResults {
    public static PendingResult<Status> immediatePendingResult(Status status) {
        Preconditions.checkNotNull(status, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.setResult(status);
        return statusPendingResult;
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
    /* loaded from: classes.dex */
    private static final class zac<R extends Result> extends BasePendingResult<R> {
        public zac(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public final R createFailedResult(Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
    /* loaded from: classes.dex */
    private static final class zaa<R extends Result> extends BasePendingResult<R> {
        private final R zab;

        public zaa(GoogleApiClient googleApiClient, R r) {
            super(googleApiClient);
            this.zab = r;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public final R createFailedResult(Status status) {
            return this.zab;
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
    /* loaded from: classes.dex */
    private static final class zab<R extends Result> extends BasePendingResult<R> {
        private final R zab;

        public zab(R r) {
            super(Looper.getMainLooper());
            this.zab = r;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public final R createFailedResult(Status status) {
            if (status.getStatusCode() != this.zab.getStatus().getStatusCode()) {
                throw new UnsupportedOperationException("Creating failed results is not supported");
            }
            return this.zab;
        }
    }

    public static PendingResult<Status> immediatePendingResult(Status status, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(status, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(googleApiClient);
        statusPendingResult.setResult(status);
        return statusPendingResult;
    }

    public static <R extends Result> PendingResult<R> immediateFailedResult(R r, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(r, "Result must not be null");
        Preconditions.checkArgument(!r.getStatus().isSuccess(), "Status code must not be SUCCESS");
        zaa zaaVar = new zaa(googleApiClient, r);
        zaaVar.setResult(r);
        return zaaVar;
    }

    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r) {
        Preconditions.checkNotNull(r, "Result must not be null");
        zac zacVar = new zac(null);
        zacVar.setResult(r);
        return new OptionalPendingResultImpl(zacVar);
    }

    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(r, "Result must not be null");
        zac zacVar = new zac(googleApiClient);
        zacVar.setResult(r);
        return new OptionalPendingResultImpl(zacVar);
    }

    public static PendingResult<Status> canceledPendingResult() {
        StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.cancel();
        return statusPendingResult;
    }

    public static <R extends Result> PendingResult<R> canceledPendingResult(R r) {
        Preconditions.checkNotNull(r, "Result must not be null");
        Preconditions.checkArgument(r.getStatus().getStatusCode() == 16, "Status code must be CommonStatusCodes.CANCELED");
        zab zabVar = new zab(r);
        zabVar.cancel();
        return zabVar;
    }

    private PendingResults() {
    }
}
