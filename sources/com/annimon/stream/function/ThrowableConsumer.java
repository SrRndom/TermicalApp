package com.annimon.stream.function;

import java.lang.Throwable;

/* loaded from: classes.dex */
public interface ThrowableConsumer<T, E extends Throwable> {
    void accept(T t) throws Throwable;
}
