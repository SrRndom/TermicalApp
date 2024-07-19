package com.annimon.stream.operator;

import com.annimon.stream.function.IntUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* loaded from: classes.dex */
public class IntMap extends PrimitiveIterator.OfInt {
    private final PrimitiveIterator.OfInt iterator;
    private final IntUnaryOperator mapper;

    public IntMap(PrimitiveIterator.OfInt ofInt, IntUnaryOperator intUnaryOperator) {
        this.iterator = ofInt;
        this.mapper = intUnaryOperator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        return this.mapper.applyAsInt(this.iterator.nextInt());
    }
}
