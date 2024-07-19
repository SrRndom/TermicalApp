package com.annimon.stream.operator;

import com.annimon.stream.function.Supplier;
import com.annimon.stream.iterator.LsaIterator;

/* loaded from: classes.dex */
public class ObjGenerate<T> extends LsaIterator<T> {
    private final Supplier<T> supplier;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    public ObjGenerate(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public T nextIteration() {
        return this.supplier.get();
    }
}
