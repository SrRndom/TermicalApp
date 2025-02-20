package kotlin.collections;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* compiled from: _Sets.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0004\u001a,\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002¢\u0006\u0002\u0010\u0007\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001a,\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0004¨\u0006\r"}, d2 = {"minus", "", "T", "element", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "elements", "", "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", "", "Lkotlin/sequences/Sequence;", "minusElement", "plus", "plusElement", "kotlin-stdlib"}, k = 5, mv = {1, 1, 10}, xi = 1, xs = "kotlin/collections/SetsKt")
/* loaded from: classes.dex */
class SetsKt___SetsKt extends SetsKt__SetsKt {
    public static final <T> Set<T> minus(Set<? extends T> receiver, T t) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(receiver.size()));
        boolean z = false;
        for (T t2 : receiver) {
            boolean z2 = true;
            if (!z && Intrinsics.areEqual(t2, t)) {
                z = true;
                z2 = false;
            }
            if (z2) {
                linkedHashSet.add(t2);
            }
        }
        return linkedHashSet;
    }

    public static final <T> Set<T> minus(Set<? extends T> receiver, T[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(receiver);
        CollectionsKt.removeAll(linkedHashSet, elements);
        return linkedHashSet;
    }

    public static final <T> Set<T> minus(Set<? extends T> receiver, Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        Set<? extends T> set = receiver;
        Collection<?> convertToSetForSetOperationWith = CollectionsKt.convertToSetForSetOperationWith(elements, set);
        if (convertToSetForSetOperationWith.isEmpty()) {
            return CollectionsKt.toSet(set);
        }
        if (convertToSetForSetOperationWith instanceof Set) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (T t : set) {
                if (!convertToSetForSetOperationWith.contains(t)) {
                    linkedHashSet.add(t);
                }
            }
            return linkedHashSet;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(receiver);
        linkedHashSet2.removeAll(convertToSetForSetOperationWith);
        return linkedHashSet2;
    }

    public static final <T> Set<T> minus(Set<? extends T> receiver, Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(receiver);
        CollectionsKt.removeAll(linkedHashSet, elements);
        return linkedHashSet;
    }

    private static final <T> Set<T> minusElement(Set<? extends T> set, T t) {
        return SetsKt.minus(set, t);
    }

    public static final <T> Set<T> plus(Set<? extends T> receiver, T t) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(receiver.size() + 1));
        linkedHashSet.addAll(receiver);
        linkedHashSet.add(t);
        return linkedHashSet;
    }

    public static final <T> Set<T> plus(Set<? extends T> receiver, T[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(receiver.size() + elements.length));
        linkedHashSet.addAll(receiver);
        CollectionsKt.addAll(linkedHashSet, elements);
        return linkedHashSet;
    }

    public static final <T> Set<T> plus(Set<? extends T> receiver, Iterable<? extends T> elements) {
        int size;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        Integer collectionSizeOrNull = CollectionsKt.collectionSizeOrNull(elements);
        if (collectionSizeOrNull != null) {
            size = receiver.size() + collectionSizeOrNull.intValue();
        } else {
            size = receiver.size() * 2;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(size));
        linkedHashSet.addAll(receiver);
        CollectionsKt.addAll(linkedHashSet, elements);
        return linkedHashSet;
    }

    public static final <T> Set<T> plus(Set<? extends T> receiver, Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(receiver.size() * 2));
        linkedHashSet.addAll(receiver);
        CollectionsKt.addAll(linkedHashSet, elements);
        return linkedHashSet;
    }

    private static final <T> Set<T> plusElement(Set<? extends T> set, T t) {
        return SetsKt.plus(set, t);
    }
}
