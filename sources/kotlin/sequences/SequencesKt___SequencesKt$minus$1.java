package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: _Sequences.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0096\u0002¨\u0006\u0005"}, d2 = {"kotlin/sequences/SequencesKt___SequencesKt$minus$1", "Lkotlin/sequences/Sequence;", "(Lkotlin/sequences/Sequence;Ljava/lang/Object;)V", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes.dex */
public final class SequencesKt___SequencesKt$minus$1<T> implements Sequence<T> {
    final /* synthetic */ Object $element;
    final /* synthetic */ Sequence receiver$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SequencesKt___SequencesKt$minus$1(Sequence<? extends T> sequence, Object obj) {
        this.receiver$0 = sequence;
        this.$element = obj;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = false;
        return SequencesKt.filter(this.receiver$0, new Function1<T, Boolean>() { // from class: kotlin.sequences.SequencesKt___SequencesKt$minus$1$iterator$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke2((SequencesKt___SequencesKt$minus$1$iterator$1<T>) obj));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(T t) {
                if (booleanRef.element || !Intrinsics.areEqual(t, SequencesKt___SequencesKt$minus$1.this.$element)) {
                    return true;
                }
                booleanRef.element = true;
                return false;
            }
        }).iterator();
    }
}
