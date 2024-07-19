package com.annimon.stream.operator;

import com.annimon.stream.function.DoublePredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator;
import com.annimon.stream.iterator.PrimitiveIterator;

/* loaded from: classes.dex */
public class DoubleDropWhile extends PrimitiveExtIterator.OfDouble {
    private final PrimitiveIterator.OfDouble iterator;
    private final DoublePredicate predicate;

    public DoubleDropWhile(PrimitiveIterator.OfDouble ofDouble, DoublePredicate doublePredicate) {
        this.iterator = ofDouble;
        this.predicate = doublePredicate;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
    
        if (r3.hasNext == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
    
        if (r3.iterator.hasNext() == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0034, code lost:
    
        r3.hasNext = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
    
        if (r3.hasNext != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003b, code lost:
    
        r3.next = r3.iterator.next().doubleValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0033, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0002, code lost:
    
        if (r3.isInit == false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0004, code lost:
    
        r0 = r3.iterator.hasNext();
        r3.hasNext = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r0 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
    
        r3.next = r3.iterator.next().doubleValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0022, code lost:
    
        if (r3.predicate.test(r3.next) != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0024, code lost:
    
        return;
     */
    @Override // com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void nextIteration() {
        /*
            r3 = this;
            boolean r0 = r3.isInit
            if (r0 != 0) goto L25
        L4:
            com.annimon.stream.iterator.PrimitiveIterator$OfDouble r0 = r3.iterator
            boolean r0 = r0.hasNext()
            r3.hasNext = r0
            if (r0 == 0) goto L25
            com.annimon.stream.iterator.PrimitiveIterator$OfDouble r0 = r3.iterator
            java.lang.Double r0 = r0.next()
            double r0 = r0.doubleValue()
            r3.next = r0
            com.annimon.stream.function.DoublePredicate r0 = r3.predicate
            double r1 = r3.next
            boolean r0 = r0.test(r1)
            if (r0 != 0) goto L4
            return
        L25:
            boolean r0 = r3.hasNext
            if (r0 == 0) goto L33
            com.annimon.stream.iterator.PrimitiveIterator$OfDouble r0 = r3.iterator
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L33
            r0 = 1
            goto L34
        L33:
            r0 = 0
        L34:
            r3.hasNext = r0
            boolean r0 = r3.hasNext
            if (r0 != 0) goto L3b
            return
        L3b:
            com.annimon.stream.iterator.PrimitiveIterator$OfDouble r0 = r3.iterator
            java.lang.Double r0 = r0.next()
            double r0 = r0.doubleValue()
            r3.next = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.operator.DoubleDropWhile.nextIteration():void");
    }
}
