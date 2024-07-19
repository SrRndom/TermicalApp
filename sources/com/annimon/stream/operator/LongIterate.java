package com.annimon.stream.operator;

import com.annimon.stream.function.LongUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* loaded from: classes.dex */
public class LongIterate extends PrimitiveIterator.OfLong {
    private long current;
    private final LongUnaryOperator op;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public LongIterate(long j, LongUnaryOperator longUnaryOperator) {
        this.op = longUnaryOperator;
        this.current = j;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
    public long nextLong() {
        long j = this.current;
        this.current = this.op.applyAsLong(j);
        return j;
    }
}
