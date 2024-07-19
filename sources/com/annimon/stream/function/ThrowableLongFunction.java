package com.annimon.stream.function;

import java.lang.Throwable;

/* loaded from: classes.dex */
public interface ThrowableLongFunction<R, E extends Throwable> {
    R apply(long j) throws Throwable;
}
