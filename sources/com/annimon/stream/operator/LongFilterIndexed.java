package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedLongPredicate;
import com.annimon.stream.iterator.PrimitiveIndexedIterator;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class LongFilterIndexed extends PrimitiveIterator.OfLong {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final PrimitiveIndexedIterator.OfLong iterator;
    private long next;
    private final IndexedLongPredicate predicate;

    public LongFilterIndexed(PrimitiveIndexedIterator.OfLong ofLong, IndexedLongPredicate indexedLongPredicate) {
        this.iterator = ofLong;
        this.predicate = indexedLongPredicate;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (!this.hasNextEvaluated) {
            nextIteration();
            this.hasNextEvaluated = true;
        }
        return this.hasNext;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
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
            long longValue = this.iterator.next().longValue();
            this.next = longValue;
            if (this.predicate.test(index, longValue)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
