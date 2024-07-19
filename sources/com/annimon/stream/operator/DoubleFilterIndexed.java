package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedDoublePredicate;
import com.annimon.stream.iterator.PrimitiveIndexedIterator;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class DoubleFilterIndexed extends PrimitiveIterator.OfDouble {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final PrimitiveIndexedIterator.OfDouble iterator;
    private double next;
    private final IndexedDoublePredicate predicate;

    public DoubleFilterIndexed(PrimitiveIndexedIterator.OfDouble ofDouble, IndexedDoublePredicate indexedDoublePredicate) {
        this.iterator = ofDouble;
        this.predicate = indexedDoublePredicate;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (!this.hasNextEvaluated) {
            nextIteration();
            this.hasNextEvaluated = true;
        }
        return this.hasNext;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
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
            int index = this.iterator.getIndex();
            double doubleValue = this.iterator.next().doubleValue();
            this.next = doubleValue;
            if (this.predicate.test(index, doubleValue)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
