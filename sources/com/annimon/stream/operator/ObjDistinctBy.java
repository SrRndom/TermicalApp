package com.annimon.stream.operator;

import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class ObjDistinctBy<T, K> extends LsaExtIterator<T> {
    private final Function<? super T, ? extends K> classifier;
    private final Iterator<? extends T> iterator;
    private final Set<K> set = new HashSet();

    public ObjDistinctBy(Iterator<? extends T> it, Function<? super T, ? extends K> function) {
        this.iterator = it;
        this.classifier = function;
    }

    @Override // com.annimon.stream.iterator.LsaExtIterator
    protected void nextIteration() {
        do {
            boolean hasNext = this.iterator.hasNext();
            this.hasNext = hasNext;
            if (!hasNext) {
                return;
            }
            this.next = this.iterator.next();
        } while (!this.set.add(this.classifier.apply(this.next)));
    }
}
