package com.annimon.stream.operator;

import com.annimon.stream.function.IntPredicate;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class IntFilter extends PrimitiveIterator.OfInt {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final PrimitiveIterator.OfInt iterator;
    private int next;
    private final IntPredicate predicate;

    public IntFilter(PrimitiveIterator.OfInt ofInt, IntPredicate intPredicate) {
        this.iterator = ofInt;
        this.predicate = intPredicate;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (!this.hasNextEvaluated) {
            nextIteration();
            this.hasNextEvaluated = true;
        }
        return this.hasNext;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
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
            int nextInt = this.iterator.nextInt();
            this.next = nextInt;
            if (this.predicate.test(nextInt)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }
}
