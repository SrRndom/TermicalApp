package com.annimon.stream.operator;

import com.annimon.stream.iterator.LsaIterator;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ObjSkip<T> extends LsaIterator<T> {
    private final Iterator<? extends T> iterator;
    private final long n;
    private long skipped = 0;

    public ObjSkip(Iterator<? extends T> it, long j) {
        this.iterator = it;
        this.n = j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (this.skipped < this.n) {
            if (!this.iterator.hasNext()) {
                return false;
            }
            this.iterator.next();
            this.skipped++;
        }
        return this.iterator.hasNext();
    }

    @Override // com.annimon.stream.iterator.LsaIterator
    public T nextIteration() {
        return this.iterator.next();
    }
}
