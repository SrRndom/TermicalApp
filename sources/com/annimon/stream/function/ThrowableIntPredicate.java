package com.annimon.stream.function;

import java.lang.Throwable;

/* loaded from: classes.dex */
public interface ThrowableIntPredicate<E extends Throwable> {
    boolean test(int i) throws Throwable;
}
