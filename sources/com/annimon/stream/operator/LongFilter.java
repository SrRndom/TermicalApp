package com.annimon.stream.operator;

import com.annimon.stream.function.LongPredicate;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class LongFilter extends PrimitiveIterator.OfLong {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final PrimitiveIterator.OfLong iterator;
    private long next;
    private final LongPredicate predicate;

    public LongFilter(PrimitiveIterator.OfLong ofLong, LongPredicate longPredicate) {
        this.iterator = ofLong;
        this.predicate = longPredicate;
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
            long nextLong = this.iterator.nextLong();
            this.next = nextLong;
            if (this.predicate.test(nextLong)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
