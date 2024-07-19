package com.annimon.stream.function;

/* loaded from: classes.dex */
public interface BooleanPredicate {
    boolean test(boolean z);

    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static BooleanPredicate identity() {
            return new BooleanPredicate() { // from class: com.annimon.stream.function.BooleanPredicate.Util.1
                @Override // com.annimon.stream.function.BooleanPredicate
                public boolean test(boolean z) {
                    return z;
                }
            };
        }

        public static BooleanPredicate and(final BooleanPredicate booleanPredicate, final BooleanPredicate booleanPredicate2) {
            return new BooleanPredicate() { // from class: com.annimon.stream.function.BooleanPredicate.Util.2
                @Override // com.annimon.stream.function.BooleanPredicate
                public boolean test(boolean z) {
                    return BooleanPredicate.this.test(z) && booleanPredicate2.test(z);
                }
            };
        }

        public static BooleanPredicate or(final BooleanPredicate booleanPredicate, final BooleanPredicate booleanPredicate2) {
            return new BooleanPredicate() { // from class: com.annimon.stream.function.BooleanPredicate.Util.3
                @Override // com.annimon.stream.function.BooleanPredicate
                public boolean test(boolean z) {
                    return BooleanPredicate.this.test(z) || booleanPredicate2.test(z);
                }
            };
        }

        public static BooleanPredicate xor(final BooleanPredicate booleanPredicate, final BooleanPredicate booleanPredicate2) {
            return new BooleanPredicate() { // from class: com.annimon.stream.function.BooleanPredicate.Util.4
                @Override // com.annimon.stream.function.BooleanPredicate
                public boolean test(boolean z) {
                    return booleanPredicate2.test(z) ^ BooleanPredicate.this.test(z);
                }
            };
        }

        public static BooleanPredicate negate(final BooleanPredicate booleanPredicate) {
            return new BooleanPredicate() { // from class: com.annimon.stream.function.BooleanPredicate.Util.5
                @Override // com.annimon.stream.function.BooleanPredicate
                public boolean test(boolean z) {
                    return !BooleanPredicate.this.test(z);
                }
            };
        }
    }
}
