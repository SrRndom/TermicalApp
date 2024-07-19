package com.annimon.stream.operator;

import com.annimon.stream.function.DoublePredicate;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class DoubleFilter extends PrimitiveIterator.OfDouble {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final PrimitiveIterator.OfDouble iterator;
    private double next;
    private final DoublePredicate predicate;

    public DoubleFilter(PrimitiveIterator.OfDouble ofDouble, DoublePredicate doublePredicate) {
        this.iterator = ofDouble;
        this.predicate = doublePredicate;
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
            double nextDouble = this.iterator.nextDouble();
            this.next = nextDouble;
            if (this.predicate.test(nextDouble)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
