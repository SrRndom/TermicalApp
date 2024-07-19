package com.annimon.stream.function;

/* loaded from: classes.dex */
public interface DoubleUnaryOperator {
    double applyAsDouble(double d);

    /* loaded from: classes.dex */
    public static class Util {
        private Util() {
        }

        public static DoubleUnaryOperator identity() {
            return new DoubleUnaryOperator() { // from class: com.annimon.stream.function.DoubleUnaryOperator.Util.1
                @Override // com.annimon.stream.function.DoubleUnaryOperator
                public double applyAsDouble(double d) {
                    return d;
                }
            };
        }
    }
}
