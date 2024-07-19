package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;

/* loaded from: classes.dex */
public class IntSkip extends PrimitiveIterator.OfInt {
    private final PrimitiveIterator.OfInt iterator;
    private final long n;
    private long skipped = 0;

    public IntSkip(PrimitiveIterator.OfInt ofInt, long j) {
        this.iterator = ofInt;
        this.n = j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (this.iterator.hasNext() && this.skipped != this.n) {
            this.iterator.nextInt();
            this.skipped++;
        }
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        return this.iterator.nextInt();
    }
}
