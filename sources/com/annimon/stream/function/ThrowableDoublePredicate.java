package com.annimon.stream.function;

import java.lang.Throwable;

/* loaded from: classes.dex */
public interface ThrowableDoublePredicate<E extends Throwable> {
    boolean test(double d) throws Throwable;
}
