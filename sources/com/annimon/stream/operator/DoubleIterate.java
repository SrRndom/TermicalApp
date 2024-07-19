package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* loaded from: classes.dex */
public class DoubleIterate extends PrimitiveIterator.OfDouble {
    private double current;
    private final DoubleUnaryOperator op;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public DoubleIterate(double d, DoubleUnaryOperator doubleUnaryOperator) {
        this.op = doubleUnaryOperator;
        this.current = d;
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
    public double nextDouble() {
        double d = this.current;
        this.current = this.op.applyAsDouble(d);
        return d;
    }
}
