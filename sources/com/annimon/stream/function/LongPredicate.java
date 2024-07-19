package com.annimon.stream.function;

/* loaded from: classes.dex */
public interface LongPredicate {
    boolean test(long j);

    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static LongPredicate and(final LongPredicate longPredicate, final LongPredicate longPredicate2) {
            return new LongPredicate() { // from class: com.annimon.stream.function.LongPredicate.Util.1
                @Override // com.annimon.stream.function.LongPredicate
                public boolean test(long j) {
                    return LongPredicate.this.test(j) && longPredicate2.test(j);
                }
            };
        }

        public static LongPredicate or(final LongPredicate longPredicate, final LongPredicate longPredicate2) {
            return new LongPredicate() { // from class: com.annimon.stream.function.LongPredicate.Util.2
                @Override // com.annimon.stream.function.LongPredicate
                public boolean test(long j) {
                    return LongPredicate.this.test(j) || longPredicate2.test(j);
                }
            };
        }

        public static LongPredicate xor(final LongPredicate longPredicate, final LongPredicate longPredicate2) {
            return new LongPredicate() { // from class: com.annimon.stream.function.LongPredicate.Util.3
                @Override // com.annimon.stream.function.LongPredicate
                public boolean test(long j) {
                    return longPredicate2.test(j) ^ LongPredicate.this.test(j);
                }
            };
        }

        public static LongPredicate negate(final LongPredicate longPredicate) {
            return new LongPredicate() { // from class: com.annimon.stream.function.LongPredicate.Util.4
                @Override // com.annimon.stream.function.LongPredicate
                public boolean test(long j) {
                    return !LongPredicate.this.test(j);
                }
            };
        }

        public static LongPredicate safe(ThrowableLongPredicate<Throwable> throwableLongPredicate) {
            return safe(throwableLongPredicate, false);
        }

        public static LongPredicate safe(final ThrowableLongPredicate<Throwable> throwableLongPredicate, final boolean z) {
            return new LongPredicate() { // from class: com.annimon.stream.function.LongPredicate.Util.5
                @Override // com.annimon.stream.function.LongPredicate
                public boolean test(long j) {
                    try {
                        return ThrowableLongPredicate.this.test(j);
                    } catch (Throwable unused) {
                        return z;
                    }
                }
            };
        }
    }
}
