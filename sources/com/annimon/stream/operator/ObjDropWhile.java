package com.annimon.stream.operator;

import com.annimon.stream.function.Predicate;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ObjDropWhile<T> extends LsaExtIterator<T> {
    private final Iterator<? extends T> iterator;
    private final Predicate<? super T> predicate;

    public ObjDropWhile(Iterator<? extends T> it, Predicate<? super T> predicate) {
        this.iterator = it;
        this.predicate = predicate;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
    
        if (r2.hasNext == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002b, code lost:
    
        if (r2.iterator.hasNext() == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002d, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
    
        r2.hasNext = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0034, code lost:
    
        if (r2.hasNext != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0036, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0037, code lost:
    
        r2.next = r2.iterator.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003f, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x002f, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0002, code lost:
    
        if (r2.isInit == false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0004, code lost:
    
        r0 = r2.iterator.hasNext();
        r2.hasNext = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r0 == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
    
        r2.next = r2.iterator.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001e, code lost:
    
        if (r2.predicate.test(r2.next) != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
    
        return;
     */
    @Override // com.annimon.stream.iterator.LsaExtIterator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void nextIteration() {
        /*
            r2 = this;
            boolean r0 = r2.isInit
            if (r0 != 0) goto L21
        L4:
            java.util.Iterator<? extends T> r0 = r2.iterator
            boolean r0 = r0.hasNext()
            r2.hasNext = r0
            if (r0 == 0) goto L21
            java.util.Iterator<? extends T> r0 = r2.iterator
            java.lang.Object r0 = r0.next()
            r2.next = r0
            com.annimon.stream.function.Predicate<? super T> r0 = r2.predicate
            T r1 = r2.next
            boolean r0 = r0.test(r1)
            if (r0 != 0) goto L4
            return
        L21:
            boolean r0 = r2.hasNext
            if (r0 == 0) goto L2f
            java.util.Iterator<? extends T> r0 = r2.iterator
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L2f
            r0 = 1
            goto L30
        L2f:
            r0 = 0
        L30:
            r2.hasNext = r0
            boolean r0 = r2.hasNext
            if (r0 != 0) goto L37
            return
        L37:
            java.util.Iterator<? extends T> r0 = r2.iterator
            java.lang.Object r0 = r0.next()
            r2.next = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.operator.ObjDropWhile.nextIteration():void");
    }
}
