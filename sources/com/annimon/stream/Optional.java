package com.annimon.stream;

import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ToBooleanFunction;
import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.function.ToLongFunction;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<>();
    private final T value;

    public static <T> Optional<T> of(T t) {
        return new Optional<>(t);
    }

    public static <T> Optional<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public static <T> Optional<T> empty() {
        return (Optional<T>) EMPTY;
    }

    private Optional() {
        this.value = null;
    }

    private Optional(T t) {
        this.value = (T) Objects.requireNonNull(t);
    }

    public T get() {
        return orElseThrow();
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public boolean isEmpty() {
        return this.value == null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        T t = this.value;
        if (t != null) {
            consumer.accept(t);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> consumer, Runnable runnable) {
        T t = this.value;
        if (t != null) {
            consumer.accept(t);
        } else {
            runnable.run();
        }
    }

    public Optional<T> executeIfPresent(Consumer<? super T> consumer) {
        ifPresent(consumer);
        return this;
    }

    public Optional<T> executeIfAbsent(Runnable runnable) {
        if (this.value == null) {
            runnable.run();
        }
        return this;
    }

    public <R> R custom(Function<Optional<T>, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        if (isPresent() && !predicate.test(this.value)) {
            return empty();
        }
        return this;
    }

    public Optional<T> filterNot(Predicate<? super T> predicate) {
        return filter(Predicate.Util.negate(predicate));
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> function) {
        return !isPresent() ? empty() : ofNullable(function.apply(this.value));
    }

    public OptionalInt mapToInt(ToIntFunction<? super T> toIntFunction) {
        return !isPresent() ? OptionalInt.empty() : OptionalInt.of(toIntFunction.applyAsInt(this.value));
    }

    public OptionalLong mapToLong(ToLongFunction<? super T> toLongFunction) {
        return !isPresent() ? OptionalLong.empty() : OptionalLong.of(toLongFunction.applyAsLong(this.value));
    }

    public OptionalDouble mapToDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        return !isPresent() ? OptionalDouble.empty() : OptionalDouble.of(toDoubleFunction.applyAsDouble(this.value));
    }

    public OptionalBoolean mapToBoolean(ToBooleanFunction<? super T> toBooleanFunction) {
        return !isPresent() ? OptionalBoolean.empty() : OptionalBoolean.of(toBooleanFunction.applyAsBoolean(this.value));
    }

    public <U> Optional<U> flatMap(Function<? super T, Optional<U>> function) {
        return !isPresent() ? empty() : (Optional) Objects.requireNonNull(function.apply(this.value));
    }

    public Stream<T> stream() {
        return !isPresent() ? Stream.empty() : Stream.of(this.value);
    }

    public <R> Optional<R> select(Class<R> cls) {
        Objects.requireNonNull(cls);
        if (isPresent()) {
            return ofNullable(cls.isInstance(this.value) ? this.value : null);
        }
        return empty();
    }

    public Optional<T> or(Supplier<Optional<T>> supplier) {
        if (isPresent()) {
            return this;
        }
        Objects.requireNonNull(supplier);
        return (Optional) Objects.requireNonNull(supplier.get());
    }

    public T orElse(T t) {
        T t2 = this.value;
        return t2 != null ? t2 : t;
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        T t = this.value;
        return t != null ? t : supplier.get();
    }

    public T orElseThrow() {
        T t = this.value;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> supplier) throws Throwable {
        T t = this.value;
        if (t != null) {
            return t;
        }
        throw supplier.get();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Optional) {
            return Objects.equals(this.value, ((Optional) obj).value);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    public String toString() {
        T t = this.value;
        return t != null ? String.format("Optional[%s]", t) : "Optional.empty";
    }
}
