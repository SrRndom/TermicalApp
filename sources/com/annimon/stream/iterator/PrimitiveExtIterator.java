package com.annimon.stream.iterator;

import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public final class PrimitiveExtIterator {
    private PrimitiveExtIterator() {
    }

    /* loaded from: classes.dex */
    public static abstract class OfInt extends PrimitiveIterator.OfInt {
        protected boolean hasNext;
        protected boolean isInit;
        protected int next;

        protected abstract void nextIteration();

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.isInit) {
                nextIteration();
                this.isInit = true;
            }
            return this.hasNext;
        }

        @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
        public int nextInt() {
            if (!this.isInit) {
                hasNext();
            }
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            int i = this.next;
            nextIteration();
            return i;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class OfLong extends PrimitiveIterator.OfLong {
        protected boolean hasNext;
        protected boolean isInit;
        protected long next;

        protected abstract void nextIteration();

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.isInit) {
                nextIteration();
                this.isInit = true;
            }
            return this.hasNext;
        }

        @Override // com.annimon.stream.iterator.PrimitiveIterator.OfLong
        public long nextLong() {
            if (!this.isInit) {
                hasNext();
            }
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            long j = this.next;
            nextIteration();
            return j;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class OfDouble extends PrimitiveIterator.OfDouble {
        protected boolean hasNext;
        protected boolean isInit;
        protected double next;

        protected abstract void nextIteration();

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.isInit) {
                nextIteration();
                this.isInit = true;
            }
            return this.hasNext;
        }

        @Override // com.annimon.stream.iterator.PrimitiveIterator.OfDouble
        public double nextDouble() {
            if (!this.isInit) {
                hasNext();
            }
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            double d = this.next;
            nextIteration();
            return d;
        }
    }
}
