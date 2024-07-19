package com.annimon.stream.function;

import java.lang.Throwable;

/* loaded from: classes.dex */
public interface ThrowableFunction<I, R, E extends Throwable> {
    R apply(I i) throws Throwable;
}
