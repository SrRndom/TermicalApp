package com.annimon.stream.operator;

import com.annimon.stream.function.IntUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* loaded from: classes.dex */
public class IntIterate extends PrimitiveIterator.OfInt {
    private int current;
    private final IntUnaryOperator op;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public IntIterate(int i, IntUnaryOperator intUnaryOperator) {
        this.op = intUnaryOperator;
        this.current = i;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        int i = this.current;
        this.current = this.op.applyAsInt(i);
        return i;
    }
}
