package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.SequenceBuilder;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: SlidingWindow.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "Lkotlin/coroutines/experimental/SequenceBuilder;", "", "invoke", "(Lkotlin/coroutines/experimental/SequenceBuilder;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 10})
/* loaded from: classes.dex */
public final class SlidingWindowKt$windowedIterator$1<T> extends CoroutineImpl implements Function2<SequenceBuilder<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    private SequenceBuilder p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator it, boolean z, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.$step = i;
        this.$size = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
    }

    @Override // kotlin.coroutines.experimental.jvm.internal.CoroutineImpl
    public /* bridge */ /* synthetic */ Continuation create(Object obj, Continuation continuation) {
        return create((SequenceBuilder) obj, (Continuation<? super Unit>) continuation);
    }

    public final Continuation<Unit> create(SequenceBuilder<? super List<? extends T>> receiver, Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$step, this.$size, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.p$ = receiver;
        return slidingWindowKt$windowedIterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceBuilder<? super List<? extends T>> receiver, Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        return ((SlidingWindowKt$windowedIterator$1) create((SequenceBuilder) receiver, continuation)).doResume(Unit.INSTANCE, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00e8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00ba  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0162 -> B:19:0x0165). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x012c -> B:38:0x012f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x00b1 -> B:60:0x00b2). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.experimental.jvm.internal.CoroutineImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object doResume(java.lang.Object r13, java.lang.Throwable r14) {
        /*
            Method dump skipped, instructions count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.doResume(java.lang.Object, java.lang.Throwable):java.lang.Object");
    }
}
