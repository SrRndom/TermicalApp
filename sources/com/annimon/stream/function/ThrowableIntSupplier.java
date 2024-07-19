package com.annimon.stream.function;

import java.lang.Throwable;

/* loaded from: classes.dex */
public interface ThrowableIntSupplier<E extends Throwable> {
    int getAsInt() throws Throwable;
}
