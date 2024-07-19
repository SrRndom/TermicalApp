package com.annimon.stream.function;

import java.lang.Throwable;

/* loaded from: classes.dex */
public interface ThrowableSupplier<T, E extends Throwable> {
    T get() throws Throwable;
}
