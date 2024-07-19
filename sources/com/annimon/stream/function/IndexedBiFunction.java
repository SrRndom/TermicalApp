package com.annimon.stream.function;

import com.annimon.stream.Objects;

/* loaded from: classes.dex */
public interface IndexedBiFunction<T, U, R> {
    R apply(int i, T t, U u);

    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static <T, U, R> IndexedBiFunction<T, U, R> wrap(final BiFunction<? super T, ? super U, ? extends R> biFunction) {
            Objects.requireNonNull(biFunction);
            return new IndexedBiFunction<T, U, R>() { // from class: com.annimon.stream.function.IndexedBiFunction.Util.1
                @Override // com.annimon.stream.function.IndexedBiFunction
                public R apply(int i, T t, U u) {
                    return (R) BiFunction.this.apply(t, u);
                }
            };
        }
    }
}
