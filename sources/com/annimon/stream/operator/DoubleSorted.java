package com.annimon.stream.operator;

import com.annimon.stream.internal.Operators;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Arrays;

/* loaded from: classes.dex */
public class DoubleSorted extends PrimitiveExtIterator.OfDouble {
    private double[] array;
    private int index = 0;
    private final PrimitiveIterator.OfDouble iterator;

    public DoubleSorted(PrimitiveIterator.OfDouble ofDouble) {
        this.iterator = ofDouble;
    }

    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble
    protected void nextIteration() {
        if (!this.isInit) {
            double[] doubleArray = Operators.toDoubleArray(this.iterator);
            this.array = doubleArray;
            Arrays.sort(doubleArray);
        }
        this.hasNext = this.index < this.array.length;
        if (this.hasNext) {
            double[] dArr = this.array;
            int i = this.index;
            this.index = i + 1;
            this.next = dArr[i];
        }
    }
}
