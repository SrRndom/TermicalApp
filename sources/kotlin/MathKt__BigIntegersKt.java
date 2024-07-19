package kotlin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BigIntegers.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\u0001H\u0087\b\u001a!\u0010\u0010\u001a\u00020\u0011*\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\rH\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\u0016H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f¨\u0006\u0019"}, d2 = {"and", "Ljava/math/BigInteger;", "other", "dec", "div", "inc", "inv", "minus", "or", "plus", "rem", "shl", "n", "", "shr", "times", "toBigDecimal", "Ljava/math/BigDecimal;", "scale", "mathContext", "Ljava/math/MathContext;", "toBigInteger", "", "unaryMinus", "xor", "kotlin-stdlib"}, k = 5, mv = {1, 1, 10}, xi = 1, xs = "kotlin/MathKt")
/* loaded from: classes.dex */
class MathKt__BigIntegersKt extends MathKt__BigDecimalsKt {
    private static final BigInteger plus(BigInteger receiver, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        BigInteger add = receiver.add(bigInteger);
        Intrinsics.checkExpressionValueIsNotNull(add, "this.add(other)");
        return add;
    }

    private static final BigInteger minus(BigInteger receiver, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        BigInteger subtract = receiver.subtract(bigInteger);
        Intrinsics.checkExpressionValueIsNotNull(subtract, "this.subtract(other)");
        return subtract;
    }

    private static final BigInteger times(BigInteger receiver, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        BigInteger multiply = receiver.multiply(bigInteger);
        Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
        return multiply;
    }

    private static final BigInteger div(BigInteger receiver, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        BigInteger divide = receiver.divide(bigInteger);
        Intrinsics.checkExpressionValueIsNotNull(divide, "this.divide(other)");
        return divide;
    }

    private static final BigInteger rem(BigInteger receiver, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        BigInteger remainder = receiver.remainder(bigInteger);
        Intrinsics.checkExpressionValueIsNotNull(remainder, "this.remainder(other)");
        return remainder;
    }

    private static final BigInteger unaryMinus(BigInteger receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        BigInteger negate = receiver.negate();
        Intrinsics.checkExpressionValueIsNotNull(negate, "this.negate()");
        return negate;
    }

    private static final BigInteger inc(BigInteger receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        BigInteger add = receiver.add(BigInteger.ONE);
        Intrinsics.checkExpressionValueIsNotNull(add, "this.add(BigInteger.ONE)");
        return add;
    }

    private static final BigInteger dec(BigInteger receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        BigInteger subtract = receiver.subtract(BigInteger.ONE);
        Intrinsics.checkExpressionValueIsNotNull(subtract, "this.subtract(BigInteger.ONE)");
        return subtract;
    }

    private static final BigInteger inv(BigInteger bigInteger) {
        BigInteger not = bigInteger.not();
        Intrinsics.checkExpressionValueIsNotNull(not, "this.not()");
        return not;
    }

    private static final BigInteger and(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger and = bigInteger.and(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(and, "this.and(other)");
        return and;
    }

    private static final BigInteger or(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger or = bigInteger.or(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(or, "this.or(other)");
        return or;
    }

    private static final BigInteger xor(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger xor = bigInteger.xor(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(xor, "this.xor(other)");
        return xor;
    }

    private static final BigInteger shl(BigInteger bigInteger, int i) {
        BigInteger shiftLeft = bigInteger.shiftLeft(i);
        Intrinsics.checkExpressionValueIsNotNull(shiftLeft, "this.shiftLeft(n)");
        return shiftLeft;
    }

    private static final BigInteger shr(BigInteger bigInteger, int i) {
        BigInteger shiftRight = bigInteger.shiftRight(i);
        Intrinsics.checkExpressionValueIsNotNull(shiftRight, "this.shiftRight(n)");
        return shiftRight;
    }

    private static final BigInteger toBigInteger(int i) {
        BigInteger valueOf = BigInteger.valueOf(i);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "BigInteger.valueOf(this.toLong())");
        return valueOf;
    }

    private static final BigInteger toBigInteger(long j) {
        BigInteger valueOf = BigInteger.valueOf(j);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "BigInteger.valueOf(this)");
        return valueOf;
    }

    private static final BigDecimal toBigDecimal(BigInteger bigInteger) {
        return new BigDecimal(bigInteger);
    }

    static /* bridge */ /* synthetic */ BigDecimal toBigDecimal$default(BigInteger bigInteger, int i, MathContext mathContext, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            mathContext = MathContext.UNLIMITED;
            Intrinsics.checkExpressionValueIsNotNull(mathContext, "MathContext.UNLIMITED");
        }
        return new BigDecimal(bigInteger, i, mathContext);
    }

    private static final BigDecimal toBigDecimal(BigInteger bigInteger, int i, MathContext mathContext) {
        return new BigDecimal(bigInteger, i, mathContext);
    }
}
