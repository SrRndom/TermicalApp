package com.annimon.stream.operator;

import com.annimon.stream.function.Predicate;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class ObjFilter<T> implements Iterator<T> {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final Iterator<? extends T> iterator;
    private T next;
    private final Predicate<? super T> predicate;

    public ObjFilter(Iterator<? extends T> it, Predicate<? super T> predicate) {
        this.iterator = it;
        this.predicate = predicate;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (!this.hasNextEvaluated) {
            nextIteration();
            this.hasNextEvaluated = true;
        }
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public T next() {
        if (!this.hasNextEvaluated) {
            this.hasNext = hasNext();
        }
        if (!this.hasNext) {
            throw new NoSuchElementException();
        }
        this.hasNextEvaluated = false;
        return this.next;
    }

    private void nextIteration() {
        while (this.iterator.hasNext()) {
            T next = this.iterator.next();
            this.next = next;
            if (this.predicate.test(next)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }
}
