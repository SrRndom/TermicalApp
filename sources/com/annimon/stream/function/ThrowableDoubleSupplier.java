package com.annimon.stream.function;

import java.lang.Throwable;

/* loaded from: classes.dex */
public interface ThrowableDoubleSupplier<E extends Throwable> {
    double getAsDouble() throws Throwable;
}
