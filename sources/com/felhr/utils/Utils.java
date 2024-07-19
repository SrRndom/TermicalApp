package com.felhr.utils;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Predicate;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class Utils {
    public static <T> List<T> removeIf(Collection<T> collection, Predicate<? super T> predicate) {
        return (List) Stream.of(collection.iterator()).filterNot(predicate).collect(Collectors.toList());
    }
}
