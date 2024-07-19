package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* loaded from: classes.dex */
public class DoubleSkip extends PrimitiveIterator.OfDouble {
    private final PrimitiveIterator.OfDouble iterator;
    private final long n;
    private long skipped = 0;

    public DoubleSkip(PrimitiveIterator.OfDouble ofDouble, long j) {
        this.iterator = ofDouble;
        this.n = j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (this.iterator.hasNext() && this.skipped != this.n) {
            this.iterator.nextDouble();
            this.skipped++;
        }
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        return this.iterator.nextDouble();
    }
}
