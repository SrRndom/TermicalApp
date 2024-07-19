package com.annimon.stream;

import com.annimon.stream.function.DoubleSupplier;
import com.annimon.stream.function.IntSupplier;
import com.annimon.stream.function.LongSupplier;
import java.util.Random;

/* loaded from: classes.dex */
public final class RandomCompat {
    private final Random random;

    public RandomCompat() {
        this.random = new Random();
    }

    public RandomCompat(long j) {
        this.random = new Random(j);
    }

    public RandomCompat(Random random) {
        this.random = random;
    }

    public Random getRandom() {
        return this.random;
    }

    public IntStream ints(long j) {
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        if (j == 0) {
            return IntStream.empty();
        }
        return ints().limit(j);
    }

    public LongStream longs(long j) {
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        if (j == 0) {
            return LongStream.empty();
        }
        return longs().limit(j);
    }

    public DoubleStream doubles(long j) {
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        if (j == 0) {
            return DoubleStream.empty();
        }
        return doubles().limit(j);
    }

    public IntStream ints() {
        return IntStream.generate(new IntSupplier() { // from class: com.annimon.stream.RandomCompat.1
            @Override // com.annimon.stream.function.IntSupplier
            public int getAsInt() {
                return RandomCompat.this.random.nextInt();
            }
        });
    }

    public LongStream longs() {
        return LongStream.generate(new LongSupplier() { // from class: com.annimon.stream.RandomCompat.2
            @Override // com.annimon.stream.function.LongSupplier
            public long getAsLong() {
                return RandomCompat.this.random.nextLong();
            }
        });
    }

    public DoubleStream doubles() {
        return DoubleStream.generate(new DoubleSupplier() { // from class: com.annimon.stream.RandomCompat.3
            @Override // com.annimon.stream.function.DoubleSupplier
            public double getAsDouble() {
                return RandomCompat.this.random.nextDouble();
            }
        });
    }

    public IntStream ints(long j, int i, int i2) {
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        if (j == 0) {
            return IntStream.empty();
        }
        return ints(i, i2).limit(j);
    }

    public LongStream longs(long j, long j2, long j3) {
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        if (j == 0) {
            return LongStream.empty();
        }
        return longs(j2, j3).limit(j);
    }

    public DoubleStream doubles(long j, double d, double d2) {
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        if (j == 0) {
            return DoubleStream.empty();
        }
        return doubles(d, d2).limit(j);
    }

    public IntStream ints(int i, int i2) {
        if (i >= i2) {
            throw new IllegalArgumentException();
        }
        return IntStream.generate(new IntSupplier(i2, i) { // from class: com.annimon.stream.RandomCompat.4
            private final int bound;
            final /* synthetic */ int val$randomNumberBound;
            final /* synthetic */ int val$randomNumberOrigin;

            {
                this.val$randomNumberBound = i2;
                this.val$randomNumberOrigin = i;
                this.bound = i2 - i;
            }

            @Override // com.annimon.stream.function.IntSupplier
            public int getAsInt() {
                if (this.bound >= 0) {
                    return this.val$randomNumberOrigin + RandomCompat.this.random.nextInt(this.bound);
                }
                while (true) {
                    int nextInt = RandomCompat.this.random.nextInt();
                    if (this.val$randomNumberOrigin < nextInt && nextInt < this.val$randomNumberBound) {
                        return nextInt;
                    }
                }
            }
        });
    }

    public LongStream longs(long j, long j2) {
        if (j >= j2) {
            throw new IllegalArgumentException();
        }
        return LongStream.generate(new LongSupplier(j2, j) { // from class: com.annimon.stream.RandomCompat.5
            private final long bound;
            private final long boundMinus1;
            final /* synthetic */ long val$randomNumberBound;
            final /* synthetic */ long val$randomNumberOrigin;

            {
                this.val$randomNumberBound = j2;
                this.val$randomNumberOrigin = j;
                long j3 = j2 - j;
                this.bound = j3;
                this.boundMinus1 = j3 - 1;
            }

            @Override // com.annimon.stream.function.LongSupplier
            public long getAsLong() {
                long j3;
                long j4;
                long nextLong = RandomCompat.this.random.nextLong();
                long j5 = this.bound;
                long j6 = this.boundMinus1;
                if ((j5 & j6) == 0) {
                    j3 = nextLong & j6;
                    j4 = this.val$randomNumberOrigin;
                } else if (j5 > 0) {
                    while (true) {
                        long j7 = nextLong >>> 1;
                        long j8 = this.boundMinus1 + j7;
                        j3 = j7 % this.bound;
                        if (j8 - j3 >= 0) {
                            break;
                        }
                        nextLong = RandomCompat.this.random.nextLong();
                    }
                    j4 = this.val$randomNumberOrigin;
                } else {
                    while (true) {
                        if (this.val$randomNumberOrigin < nextLong && nextLong < this.val$randomNumberBound) {
                            return nextLong;
                        }
                        nextLong = RandomCompat.this.random.nextLong();
                    }
                }
                return j3 + j4;
            }
        });
    }

    public DoubleStream doubles(double d, double d2) {
        if (d >= d2) {
            throw new IllegalArgumentException();
        }
        return DoubleStream.generate(new DoubleSupplier(d2, d) { // from class: com.annimon.stream.RandomCompat.6
            private final double bound;
            final /* synthetic */ double val$randomNumberBound;
            final /* synthetic */ double val$randomNumberOrigin;

            {
                this.val$randomNumberBound = d2;
                this.val$randomNumberOrigin = d;
                this.bound = d2 - d;
            }

            @Override // com.annimon.stream.function.DoubleSupplier
            public double getAsDouble() {
                double nextDouble = (RandomCompat.this.random.nextDouble() * this.bound) + this.val$randomNumberOrigin;
                double d3 = this.val$randomNumberBound;
                return nextDouble >= d3 ? Double.longBitsToDouble(Double.doubleToLongBits(d3) - 1) : nextDouble;
            }
        });
    }
}
