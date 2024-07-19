package com.annimon.stream;

import com.annimon.stream.function.BiConsumer;
import com.annimon.stream.function.BiFunction;
import com.annimon.stream.function.BinaryOperator;
import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.IndexedBiFunction;
import com.annimon.stream.function.IndexedConsumer;
import com.annimon.stream.function.IndexedFunction;
import com.annimon.stream.function.IndexedPredicate;
import com.annimon.stream.function.IntFunction;
import com.annimon.stream.function.Predicate;
import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.function.ToLongFunction;
import com.annimon.stream.function.UnaryOperator;
import com.annimon.stream.internal.Compose;
import com.annimon.stream.internal.Operators;
import com.annimon.stream.internal.Params;
import com.annimon.stream.iterator.IndexedIterator;
import com.annimon.stream.iterator.LazyIterator;
import com.annimon.stream.operator.ObjArray;
import com.annimon.stream.operator.ObjChunkBy;
import com.annimon.stream.operator.ObjConcat;
import com.annimon.stream.operator.ObjDistinct;
import com.annimon.stream.operator.ObjDistinctBy;
import com.annimon.stream.operator.ObjDropWhile;
import com.annimon.stream.operator.ObjDropWhileIndexed;
import com.annimon.stream.operator.ObjFilter;
import com.annimon.stream.operator.ObjFilterIndexed;
import com.annimon.stream.operator.ObjFlatMap;
import com.annimon.stream.operator.ObjFlatMapToDouble;
import com.annimon.stream.operator.ObjFlatMapToInt;
import com.annimon.stream.operator.ObjFlatMapToLong;
import com.annimon.stream.operator.ObjGenerate;
import com.annimon.stream.operator.ObjIterate;
import com.annimon.stream.operator.ObjLimit;
import com.annimon.stream.operator.ObjMap;
import com.annimon.stream.operator.ObjMapIndexed;
import com.annimon.stream.operator.ObjMapToDouble;
import com.annimon.stream.operator.ObjMapToInt;
import com.annimon.stream.operator.ObjMapToLong;
import com.annimon.stream.operator.ObjMerge;
import com.annimon.stream.operator.ObjPeek;
import com.annimon.stream.operator.ObjScan;
import com.annimon.stream.operator.ObjScanIdentity;
import com.annimon.stream.operator.ObjSkip;
import com.annimon.stream.operator.ObjSlidingWindow;
import com.annimon.stream.operator.ObjSorted;
import com.annimon.stream.operator.ObjTakeUntil;
import com.annimon.stream.operator.ObjTakeUntilIndexed;
import com.annimon.stream.operator.ObjTakeWhile;
import com.annimon.stream.operator.ObjTakeWhileIndexed;
import com.annimon.stream.operator.ObjZip;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class Stream<T> implements Closeable {
    private static final int MATCH_ALL = 1;
    private static final int MATCH_ANY = 0;
    private static final int MATCH_NONE = 2;
    private final Iterator<? extends T> iterator;
    private final Params params;

    public static <T> Stream<T> empty() {
        return of(Collections.emptyList());
    }

    public static <K, V> Stream<Map.Entry<K, V>> of(Map<K, V> map) {
        Objects.requireNonNull(map);
        return new Stream<>(map.entrySet());
    }

    public static <T> Stream<T> of(Iterator<? extends T> it) {
        Objects.requireNonNull(it);
        return new Stream<>(it);
    }

    public static <T> Stream<T> of(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable);
        return new Stream<>(iterable);
    }

    public static <T> Stream<T> of(T... tArr) {
        Objects.requireNonNull(tArr);
        if (tArr.length == 0) {
            return empty();
        }
        return new Stream<>(new ObjArray(tArr));
    }

    public static <T> Stream<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public static <T> Stream<T> ofNullable(T[] tArr) {
        return tArr == null ? empty() : of(tArr);
    }

    public static <K, V> Stream<Map.Entry<K, V>> ofNullable(Map<K, V> map) {
        return map == null ? empty() : of(map);
    }

    public static <T> Stream<T> ofNullable(Iterator<? extends T> it) {
        return it == null ? empty() : of(it);
    }

    public static <T> Stream<T> ofNullable(Iterable<? extends T> iterable) {
        return iterable == null ? empty() : of(iterable);
    }

    public static Stream<Integer> range(int i, int i2) {
        return IntStream.range(i, i2).boxed();
    }

    public static Stream<Long> range(long j, long j2) {
        return LongStream.range(j, j2).boxed();
    }

    public static Stream<Integer> rangeClosed(int i, int i2) {
        return IntStream.rangeClosed(i, i2).boxed();
    }

    public static Stream<Long> rangeClosed(long j, long j2) {
        return LongStream.rangeClosed(j, j2).boxed();
    }

    public static <T> Stream<T> generate(Supplier<T> supplier) {
        Objects.requireNonNull(supplier);
        return new Stream<>(new ObjGenerate(supplier));
    }

    public static <T> Stream<T> iterate(T t, UnaryOperator<T> unaryOperator) {
        Objects.requireNonNull(unaryOperator);
        return new Stream<>(new ObjIterate(t, unaryOperator));
    }

    public static <T> Stream<T> iterate(T t, Predicate<? super T> predicate, UnaryOperator<T> unaryOperator) {
        Objects.requireNonNull(predicate);
        return iterate(t, unaryOperator).takeWhile(predicate);
    }

    public static <T> Stream<T> concat(Stream<? extends T> stream, Stream<? extends T> stream2) {
        Objects.requireNonNull(stream);
        Objects.requireNonNull(stream2);
        return new Stream(new ObjConcat(((Stream) stream).iterator, ((Stream) stream2).iterator)).onClose(Compose.closeables(stream, stream2));
    }

    public static <T> Stream<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2) {
        Objects.requireNonNull(it);
        Objects.requireNonNull(it2);
        return new Stream<>(new ObjConcat(it, it2));
    }

    public static <F, S, R> Stream<R> zip(Stream<? extends F> stream, Stream<? extends S> stream2, BiFunction<? super F, ? super S, ? extends R> biFunction) {
        Objects.requireNonNull(stream);
        Objects.requireNonNull(stream2);
        return zip(((Stream) stream).iterator, ((Stream) stream2).iterator, biFunction);
    }

    public static <F, S, R> Stream<R> zip(Iterator<? extends F> it, Iterator<? extends S> it2, BiFunction<? super F, ? super S, ? extends R> biFunction) {
        Objects.requireNonNull(it);
        Objects.requireNonNull(it2);
        return new Stream<>(new ObjZip(it, it2, biFunction));
    }

    public static <T> Stream<T> merge(Stream<? extends T> stream, Stream<? extends T> stream2, BiFunction<? super T, ? super T, ObjMerge.MergeResult> biFunction) {
        Objects.requireNonNull(stream);
        Objects.requireNonNull(stream2);
        return merge(((Stream) stream).iterator, ((Stream) stream2).iterator, biFunction);
    }

    public static <T> Stream<T> merge(Iterator<? extends T> it, Iterator<? extends T> it2, BiFunction<? super T, ? super T, ObjMerge.MergeResult> biFunction) {
        Objects.requireNonNull(it);
        Objects.requireNonNull(it2);
        return new Stream<>(new ObjMerge(it, it2, biFunction));
    }

    private Stream(Iterator<? extends T> it) {
        this((Params) null, it);
    }

    private Stream(Iterable<? extends T> iterable) {
        this((Params) null, new LazyIterator(iterable));
    }

    private Stream(Params params, Iterable<? extends T> iterable) {
        this(params, new LazyIterator(iterable));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Stream(Params params, Iterator<? extends T> it) {
        this.params = params;
        this.iterator = it;
    }

    public Iterator<? extends T> iterator() {
        return this.iterator;
    }

    public <R> R custom(Function<Stream<T>, R> function) {
        Objects.requireNonNull(function);
        return function.apply(this);
    }

    public Stream<T> filter(Predicate<? super T> predicate) {
        return new Stream<>(this.params, new ObjFilter(this.iterator, predicate));
    }

    public Stream<T> filterIndexed(IndexedPredicate<? super T> indexedPredicate) {
        return filterIndexed(0, 1, indexedPredicate);
    }

    public Stream<T> filterIndexed(int i, int i2, IndexedPredicate<? super T> indexedPredicate) {
        return new Stream<>(this.params, new ObjFilterIndexed(new IndexedIterator(i, i2, this.iterator), indexedPredicate));
    }

    public Stream<T> filterNot(Predicate<? super T> predicate) {
        return filter(Predicate.Util.negate(predicate));
    }

    public <TT> Stream<TT> select(final Class<TT> cls) {
        return filter(new Predicate<T>() { // from class: com.annimon.stream.Stream.1
            @Override // com.annimon.stream.function.Predicate
            public boolean test(T t) {
                return cls.isInstance(t);
            }
        });
    }

    public Stream<T> withoutNulls() {
        return filter(Predicate.Util.notNull());
    }

    public Stream<T> nullsOnly() {
        return filterNot(Predicate.Util.notNull());
    }

    public Stream<T> equalsOnly(final T t) {
        return filter(new Predicate<T>() { // from class: com.annimon.stream.Stream.2
            @Override // com.annimon.stream.function.Predicate
            public boolean test(T t2) {
                return Objects.equals(t2, t);
            }
        });
    }

    public <R> Stream<R> map(Function<? super T, ? extends R> function) {
        return new Stream<>(this.params, new ObjMap(this.iterator, function));
    }

    public <R> Stream<R> mapIndexed(IndexedFunction<? super T, ? extends R> indexedFunction) {
        return mapIndexed(0, 1, indexedFunction);
    }

    public <R> Stream<R> mapIndexed(int i, int i2, IndexedFunction<? super T, ? extends R> indexedFunction) {
        return new Stream<>(this.params, new ObjMapIndexed(new IndexedIterator(i, i2, this.iterator), indexedFunction));
    }

    public IntStream mapToInt(ToIntFunction<? super T> toIntFunction) {
        return new IntStream(this.params, new ObjMapToInt(this.iterator, toIntFunction));
    }

    public LongStream mapToLong(ToLongFunction<? super T> toLongFunction) {
        return new LongStream(this.params, new ObjMapToLong(this.iterator, toLongFunction));
    }

    public DoubleStream mapToDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        return new DoubleStream(this.params, new ObjMapToDouble(this.iterator, toDoubleFunction));
    }

    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> function) {
        return new Stream<>(this.params, new ObjFlatMap(this.iterator, function));
    }

    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> function) {
        return new IntStream(this.params, new ObjFlatMapToInt(this.iterator, function));
    }

    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> function) {
        return new LongStream(this.params, new ObjFlatMapToLong(this.iterator, function));
    }

    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> function) {
        return new DoubleStream(this.params, new ObjFlatMapToDouble(this.iterator, function));
    }

    public Stream<IntPair<T>> indexed() {
        return indexed(0, 1);
    }

    public Stream<IntPair<T>> indexed(int i, int i2) {
        return (Stream<IntPair<T>>) mapIndexed(i, i2, new IndexedFunction<T, IntPair<T>>() { // from class: com.annimon.stream.Stream.3
            @Override // com.annimon.stream.function.IndexedFunction
            public /* bridge */ /* synthetic */ Object apply(int i3, Object obj) {
                return apply(i3, (int) obj);
            }

            @Override // com.annimon.stream.function.IndexedFunction
            public IntPair<T> apply(int i3, T t) {
                return new IntPair<>(i3, t);
            }
        });
    }

    public Stream<T> distinct() {
        return new Stream<>(this.params, new ObjDistinct(this.iterator));
    }

    public <K> Stream<T> distinctBy(Function<? super T, ? extends K> function) {
        return new Stream<>(this.params, new ObjDistinctBy(this.iterator, function));
    }

    public Stream<T> sorted() {
        return sorted(new Comparator<T>() { // from class: com.annimon.stream.Stream.4
            @Override // java.util.Comparator
            public int compare(T t, T t2) {
                return ((Comparable) t).compareTo((Comparable) t2);
            }
        });
    }

    public Stream<T> sorted(Comparator<? super T> comparator) {
        return new Stream<>(this.params, new ObjSorted(this.iterator, comparator));
    }

    public <R extends Comparable<? super R>> Stream<T> sortBy(Function<? super T, ? extends R> function) {
        return sorted(ComparatorCompat.comparing(function));
    }

    public <K> Stream<Map.Entry<K, List<T>>> groupBy(Function<? super T, ? extends K> function) {
        return new Stream<>(this.params, ((Map) collect(Collectors.groupingBy(function))).entrySet());
    }

    public <K> Stream<List<T>> chunkBy(Function<? super T, ? extends K> function) {
        return new Stream<>(this.params, new ObjChunkBy(this.iterator, function));
    }

    public Stream<T> sample(int i) {
        if (i > 0) {
            return i == 1 ? this : (Stream<T>) slidingWindow(1, i).map(new Function<List<T>, T>() { // from class: com.annimon.stream.Stream.5
                @Override // com.annimon.stream.function.Function
                public T apply(List<T> list) {
                    return list.get(0);
                }
            });
        }
        throw new IllegalArgumentException("stepWidth cannot be zero or negative");
    }

    public Stream<List<T>> slidingWindow(int i) {
        return slidingWindow(i, 1);
    }

    public Stream<List<T>> slidingWindow(int i, int i2) {
        if (i <= 0) {
            throw new IllegalArgumentException("windowSize cannot be zero or negative");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("stepWidth cannot be zero or negative");
        }
        return new Stream<>(this.params, new ObjSlidingWindow(this.iterator, i, i2));
    }

    public Stream<T> peek(Consumer<? super T> consumer) {
        return new Stream<>(this.params, new ObjPeek(this.iterator, consumer));
    }

    public Stream<T> scan(BiFunction<T, T, T> biFunction) {
        Objects.requireNonNull(biFunction);
        return new Stream<>(this.params, new ObjScan(this.iterator, biFunction));
    }

    public <R> Stream<R> scan(R r, BiFunction<? super R, ? super T, ? extends R> biFunction) {
        Objects.requireNonNull(biFunction);
        return new Stream<>(this.params, new ObjScanIdentity(this.iterator, r, biFunction));
    }

    public Stream<T> takeWhile(Predicate<? super T> predicate) {
        return new Stream<>(this.params, new ObjTakeWhile(this.iterator, predicate));
    }

    public Stream<T> takeWhileIndexed(IndexedPredicate<? super T> indexedPredicate) {
        return takeWhileIndexed(0, 1, indexedPredicate);
    }

    public Stream<T> takeWhileIndexed(int i, int i2, IndexedPredicate<? super T> indexedPredicate) {
        return new Stream<>(this.params, new ObjTakeWhileIndexed(new IndexedIterator(i, i2, this.iterator), indexedPredicate));
    }

    public Stream<T> takeUntil(Predicate<? super T> predicate) {
        return new Stream<>(this.params, new ObjTakeUntil(this.iterator, predicate));
    }

    public Stream<T> takeUntilIndexed(IndexedPredicate<? super T> indexedPredicate) {
        return takeUntilIndexed(0, 1, indexedPredicate);
    }

    public Stream<T> takeUntilIndexed(int i, int i2, IndexedPredicate<? super T> indexedPredicate) {
        return new Stream<>(this.params, new ObjTakeUntilIndexed(new IndexedIterator(i, i2, this.iterator), indexedPredicate));
    }

    public Stream<T> dropWhile(Predicate<? super T> predicate) {
        return new Stream<>(this.params, new ObjDropWhile(this.iterator, predicate));
    }

    public Stream<T> dropWhileIndexed(IndexedPredicate<? super T> indexedPredicate) {
        return dropWhileIndexed(0, 1, indexedPredicate);
    }

    public Stream<T> dropWhileIndexed(int i, int i2, IndexedPredicate<? super T> indexedPredicate) {
        return new Stream<>(this.params, new ObjDropWhileIndexed(new IndexedIterator(i, i2, this.iterator), indexedPredicate));
    }

    public Stream<T> limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("maxSize cannot be negative");
        }
        if (j == 0) {
            return empty();
        }
        return new Stream<>(this.params, new ObjLimit(this.iterator, j));
    }

    public Stream<T> skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : new Stream<>(this.params, new ObjSkip(this.iterator, j));
        }
        throw new IllegalArgumentException("n cannot be negative");
    }

    public void forEach(Consumer<? super T> consumer) {
        while (this.iterator.hasNext()) {
            consumer.accept(this.iterator.next());
        }
    }

    public void forEachIndexed(IndexedConsumer<? super T> indexedConsumer) {
        forEachIndexed(0, 1, indexedConsumer);
    }

    public void forEachIndexed(int i, int i2, IndexedConsumer<? super T> indexedConsumer) {
        while (this.iterator.hasNext()) {
            indexedConsumer.accept(i, this.iterator.next());
            i += i2;
        }
    }

    public <R> R reduce(R r, BiFunction<? super R, ? super T, ? extends R> biFunction) {
        while (this.iterator.hasNext()) {
            r = biFunction.apply(r, this.iterator.next());
        }
        return r;
    }

    public <R> R reduceIndexed(R r, IndexedBiFunction<? super R, ? super T, ? extends R> indexedBiFunction) {
        return (R) reduceIndexed(0, 1, r, indexedBiFunction);
    }

    public <R> R reduceIndexed(int i, int i2, R r, IndexedBiFunction<? super R, ? super T, ? extends R> indexedBiFunction) {
        while (this.iterator.hasNext()) {
            r = indexedBiFunction.apply(i, r, this.iterator.next());
            i += i2;
        }
        return r;
    }

    public Optional<T> reduce(BiFunction<T, T, T> biFunction) {
        boolean z = false;
        T t = null;
        while (this.iterator.hasNext()) {
            T next = this.iterator.next();
            if (z) {
                t = biFunction.apply(t, next);
            } else {
                z = true;
                t = next;
            }
        }
        return z ? Optional.of(t) : Optional.empty();
    }

    public Object[] toArray() {
        return toArray(new IntFunction<Object[]>() { // from class: com.annimon.stream.Stream.6
            @Override // com.annimon.stream.function.IntFunction
            public Object[] apply(int i) {
                return new Object[i];
            }
        });
    }

    public <R> R[] toArray(IntFunction<R[]> intFunction) {
        return (R[]) Operators.toArray(this.iterator, intFunction);
    }

    public List<T> toList() {
        ArrayList arrayList = new ArrayList();
        while (this.iterator.hasNext()) {
            arrayList.add(this.iterator.next());
        }
        return arrayList;
    }

    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> biConsumer) {
        R r = supplier.get();
        while (this.iterator.hasNext()) {
            biConsumer.accept(r, this.iterator.next());
        }
        return r;
    }

    public <R, A> R collect(Collector<? super T, A, R> collector) {
        A a = collector.supplier().get();
        while (this.iterator.hasNext()) {
            collector.accumulator().accept(a, this.iterator.next());
        }
        if (collector.finisher() != null) {
            return collector.finisher().apply(a);
        }
        return (R) Collectors.castIdentity().apply(a);
    }

    public Optional<T> min(Comparator<? super T> comparator) {
        return reduce(BinaryOperator.Util.minBy(comparator));
    }

    public Optional<T> max(Comparator<? super T> comparator) {
        return reduce(BinaryOperator.Util.maxBy(comparator));
    }

    public long count() {
        long j = 0;
        while (this.iterator.hasNext()) {
            this.iterator.next();
            j++;
        }
        return j;
    }

    public boolean anyMatch(Predicate<? super T> predicate) {
        return match(predicate, 0);
    }

    public boolean allMatch(Predicate<? super T> predicate) {
        return match(predicate, 1);
    }

    public boolean noneMatch(Predicate<? super T> predicate) {
        return match(predicate, 2);
    }

    public Optional<IntPair<T>> findIndexed(IndexedPredicate<? super T> indexedPredicate) {
        return findIndexed(0, 1, indexedPredicate);
    }

    public Optional<IntPair<T>> findIndexed(int i, int i2, IndexedPredicate<? super T> indexedPredicate) {
        while (this.iterator.hasNext()) {
            T next = this.iterator.next();
            if (indexedPredicate.test(i, next)) {
                return Optional.of(new IntPair(i, next));
            }
            i += i2;
        }
        return Optional.empty();
    }

    public Optional<T> findFirst() {
        if (this.iterator.hasNext()) {
            return Optional.of(this.iterator.next());
        }
        return Optional.empty();
    }

    public Optional<T> findLast() {
        return reduce(new BinaryOperator<T>() { // from class: com.annimon.stream.Stream.7
            @Override // com.annimon.stream.function.BiFunction
            public T apply(T t, T t2) {
                return t2;
            }
        });
    }

    public T single() {
        if (this.iterator.hasNext()) {
            T next = this.iterator.next();
            if (this.iterator.hasNext()) {
                throw new IllegalStateException("Stream contains more than one element");
            }
            return next;
        }
        throw new NoSuchElementException("Stream contains no element");
    }

    public Optional<T> findSingle() {
        if (this.iterator.hasNext()) {
            T next = this.iterator.next();
            if (this.iterator.hasNext()) {
                throw new IllegalStateException("Stream contains more than one element");
            }
            return Optional.of(next);
        }
        return Optional.empty();
    }

    public Stream<T> onClose(Runnable runnable) {
        Objects.requireNonNull(runnable);
        Params params = this.params;
        if (params == null) {
            params = new Params();
            params.closeHandler = runnable;
        } else {
            params.closeHandler = Compose.runnables(params.closeHandler, runnable);
        }
        return new Stream<>(params, this.iterator);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Params params = this.params;
        if (params == null || params.closeHandler == null) {
            return;
        }
        this.params.closeHandler.run();
        this.params.closeHandler = null;
    }

    private boolean match(Predicate<? super T> predicate, int i) {
        boolean z = i == 0;
        boolean z2 = i == 1;
        while (this.iterator.hasNext()) {
            boolean test = predicate.test(this.iterator.next());
            if (test ^ z2) {
                return z && test;
            }
        }
        return !z;
    }
}
