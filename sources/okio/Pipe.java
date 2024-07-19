package okio;

import java.io.IOException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Pipe.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\b\u001aJ\r\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\b\u001bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u000b\u001a\u00020\f8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u00020\u00158\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013¨\u0006\u001c"}, d2 = {"Lokio/Pipe;", "", "maxBufferSize", "", "(J)V", "buffer", "Lokio/Buffer;", "getBuffer$jvm", "()Lokio/Buffer;", "getMaxBufferSize$jvm", "()J", "sink", "Lokio/Sink;", "()Lokio/Sink;", "sinkClosed", "", "getSinkClosed$jvm", "()Z", "setSinkClosed$jvm", "(Z)V", "source", "Lokio/Source;", "()Lokio/Source;", "sourceClosed", "getSourceClosed$jvm", "setSourceClosed$jvm", "-deprecated_sink", "-deprecated_source", "jvm"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes.dex */
public final class Pipe {
    private final Buffer buffer = new Buffer();
    private final long maxBufferSize;
    private final Sink sink;
    private boolean sinkClosed;
    private final Source source;
    private boolean sourceClosed;

    public Pipe(long j) {
        this.maxBufferSize = j;
        if (!(j >= 1)) {
            throw new IllegalArgumentException(("maxBufferSize < 1: " + j).toString());
        }
        this.sink = new Sink() { // from class: okio.Pipe$sink$1
            private final Timeout timeout = new Timeout();

            @Override // okio.Sink
            public void write(Buffer source, long byteCount) {
                Intrinsics.checkParameterIsNotNull(source, "source");
                synchronized (Pipe.this.getBuffer()) {
                    if (!(!Pipe.this.getSinkClosed())) {
                        throw new IllegalStateException("closed".toString());
                    }
                    while (byteCount > 0) {
                        if (Pipe.this.getSourceClosed()) {
                            throw new IOException("source is closed");
                        }
                        long maxBufferSize = Pipe.this.getMaxBufferSize() - Pipe.this.getBuffer().size();
                        if (maxBufferSize == 0) {
                            this.timeout.waitUntilNotified(Pipe.this.getBuffer());
                        } else {
                            long min = Math.min(maxBufferSize, byteCount);
                            Pipe.this.getBuffer().write(source, min);
                            byteCount -= min;
                            Buffer buffer = Pipe.this.getBuffer();
                            if (buffer == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                            }
                            buffer.notifyAll();
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() {
                synchronized (Pipe.this.getBuffer()) {
                    if (!(!Pipe.this.getSinkClosed())) {
                        throw new IllegalStateException("closed".toString());
                    }
                    if (Pipe.this.getSourceClosed() && Pipe.this.getBuffer().size() > 0) {
                        throw new IOException("source is closed");
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }

            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                synchronized (Pipe.this.getBuffer()) {
                    if (Pipe.this.getSinkClosed()) {
                        return;
                    }
                    if (Pipe.this.getSourceClosed() && Pipe.this.getBuffer().size() > 0) {
                        throw new IOException("source is closed");
                    }
                    Pipe.this.setSinkClosed$jvm(true);
                    Buffer buffer = Pipe.this.getBuffer();
                    if (buffer == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                    }
                    buffer.notifyAll();
                    Unit unit = Unit.INSTANCE;
                }
            }

            @Override // okio.Sink
            /* renamed from: timeout, reason: from getter */
            public Timeout getTimeout() {
                return this.timeout;
            }
        };
        this.source = new Source() { // from class: okio.Pipe$source$1
            private final Timeout timeout = new Timeout();

            @Override // okio.Source
            public long read(Buffer sink, long byteCount) {
                Intrinsics.checkParameterIsNotNull(sink, "sink");
                synchronized (Pipe.this.getBuffer()) {
                    if (!(!Pipe.this.getSourceClosed())) {
                        throw new IllegalStateException("closed".toString());
                    }
                    while (Pipe.this.getBuffer().size() == 0) {
                        if (Pipe.this.getSinkClosed()) {
                            return -1L;
                        }
                        this.timeout.waitUntilNotified(Pipe.this.getBuffer());
                    }
                    long read = Pipe.this.getBuffer().read(sink, byteCount);
                    Buffer buffer = Pipe.this.getBuffer();
                    if (buffer == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                    }
                    buffer.notifyAll();
                    return read;
                }
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                synchronized (Pipe.this.getBuffer()) {
                    Pipe.this.setSourceClosed$jvm(true);
                    Buffer buffer = Pipe.this.getBuffer();
                    if (buffer == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                    }
                    buffer.notifyAll();
                    Unit unit = Unit.INSTANCE;
                }
            }

            @Override // okio.Source
            /* renamed from: timeout, reason: from getter */
            public Timeout getTimeout() {
                return this.timeout;
            }
        };
    }

    /* renamed from: getMaxBufferSize$jvm, reason: from getter */
    public final long getMaxBufferSize() {
        return this.maxBufferSize;
    }

    /* renamed from: getBuffer$jvm, reason: from getter */
    public final Buffer getBuffer() {
        return this.buffer;
    }

    /* renamed from: getSinkClosed$jvm, reason: from getter */
    public final boolean getSinkClosed() {
        return this.sinkClosed;
    }

    public final void setSinkClosed$jvm(boolean z) {
        this.sinkClosed = z;
    }

    /* renamed from: getSourceClosed$jvm, reason: from getter */
    public final boolean getSourceClosed() {
        return this.sourceClosed;
    }

    public final void setSourceClosed$jvm(boolean z) {
        this.sourceClosed = z;
    }

    public final Sink sink() {
        return this.sink;
    }

    public final Source source() {
        return this.source;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sink", imports = {}))
    /* renamed from: -deprecated_sink, reason: not valid java name and from getter */
    public final Sink getSink() {
        return this.sink;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "source", imports = {}))
    /* renamed from: -deprecated_source, reason: not valid java name and from getter */
    public final Source getSource() {
        return this.source;
    }
}
