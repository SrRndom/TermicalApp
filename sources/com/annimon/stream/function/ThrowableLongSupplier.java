package com.annimon.stream.function;

import java.lang.Throwable;

/* loaded from: classes.dex */
public interface ThrowableLongSupplier<E extends Throwable> {
    long getAsLong() throws Throwable;
}
