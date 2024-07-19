package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.collections.SlidingWindowKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: _Strings.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000Ô\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u001f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a!\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0010\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b*\u00020\u0002\u001a\u0010\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n*\u00020\u0002\u001aE\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\u0086\b\u001a3\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\u00050\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b\u001aM\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b\u001aN\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0018\b\u0001\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u00020\u00050\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b¢\u0006\u0002\u0010\u0018\u001ah\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b¢\u0006\u0002\u0010\u0019\u001a`\u0010\u001a\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\u0086\b¢\u0006\u0002\u0010\u0018\u001a\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0007\u001a4\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H 0\u001c\"\u0004\b\u0000\u0010 *\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H 0\u0004H\u0007\u001a\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001d0\n*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0007\u001a4\u0010!\u001a\b\u0012\u0004\u0012\u0002H 0\n\"\u0004\b\u0000\u0010 *\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H 0\u0004H\u0007\u001a\r\u0010\"\u001a\u00020\u001f*\u00020\u0002H\u0087\b\u001a!\u0010\"\u001a\u00020\u001f*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010$\u001a\u00020\u001f\u001a\u0012\u0010#\u001a\u00020\u001d*\u00020\u001d2\u0006\u0010$\u001a\u00020\u001f\u001a\u0012\u0010%\u001a\u00020\u0002*\u00020\u00022\u0006\u0010$\u001a\u00020\u001f\u001a\u0012\u0010%\u001a\u00020\u001d*\u00020\u001d2\u0006\u0010$\u001a\u00020\u001f\u001a!\u0010&\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010&\u001a\u00020\u001d*\u00020\u001d2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010'\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010'\u001a\u00020\u001d*\u00020\u001d2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0015\u0010(\u001a\u00020\u0005*\u00020\u00022\u0006\u0010)\u001a\u00020\u001fH\u0087\b\u001a)\u0010*\u001a\u00020\u0005*\u00020\u00022\u0006\u0010)\u001a\u00020\u001f2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\u0004H\u0087\b\u001a\u001c\u0010,\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010)\u001a\u00020\u001fH\u0087\b¢\u0006\u0002\u0010-\u001a!\u0010.\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010.\u001a\u00020\u001d*\u00020\u001d2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a6\u0010/\u001a\u00020\u0002*\u00020\u00022'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000100H\u0086\b\u001a6\u0010/\u001a\u00020\u001d*\u00020\u001d2'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000100H\u0086\b\u001aQ\u00103\u001a\u0002H4\"\f\b\u0000\u00104*\u000605j\u0002`6*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H42'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000100H\u0086\b¢\u0006\u0002\u00107\u001a!\u00108\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u00108\u001a\u00020\u001d*\u00020\u001d2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a<\u00109\u001a\u0002H4\"\f\b\u0000\u00104*\u000605j\u0002`6*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H42\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u0010:\u001a<\u0010;\u001a\u0002H4\"\f\b\u0000\u00104*\u000605j\u0002`6*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H42\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u0010:\u001a(\u0010<\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0087\b¢\u0006\u0002\u0010=\u001a(\u0010>\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0087\b¢\u0006\u0002\u0010=\u001a\n\u0010?\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010?\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0011\u0010@\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010A\u001a(\u0010@\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u0010=\u001a3\u0010B\u001a\b\u0012\u0004\u0012\u0002H 0\u001c\"\u0004\b\u0000\u0010 *\u00020\u00022\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0\b0\u0004H\u0086\b\u001aL\u0010C\u001a\u0002H4\"\u0004\b\u0000\u0010 \"\u0010\b\u0001\u00104*\n\u0012\u0006\b\u0000\u0012\u0002H 0D*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H42\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0\b0\u0004H\u0086\b¢\u0006\u0002\u0010E\u001aI\u0010F\u001a\u0002H \"\u0004\b\u0000\u0010 *\u00020\u00022\u0006\u0010G\u001a\u0002H 2'\u0010H\u001a#\u0012\u0013\u0012\u0011H ¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(I\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H 00H\u0086\b¢\u0006\u0002\u0010J\u001a^\u0010K\u001a\u0002H \"\u0004\b\u0000\u0010 *\u00020\u00022\u0006\u0010G\u001a\u0002H 2<\u0010H\u001a8\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0013\u0012\u0011H ¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(I\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H 0LH\u0086\b¢\u0006\u0002\u0010M\u001aI\u0010N\u001a\u0002H \"\u0004\b\u0000\u0010 *\u00020\u00022\u0006\u0010G\u001a\u0002H 2'\u0010H\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H ¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(I\u0012\u0004\u0012\u0002H 00H\u0086\b¢\u0006\u0002\u0010J\u001a^\u0010O\u001a\u0002H \"\u0004\b\u0000\u0010 *\u00020\u00022\u0006\u0010G\u001a\u0002H 2<\u0010H\u001a8\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H ¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(I\u0012\u0004\u0012\u0002H 0LH\u0086\b¢\u0006\u0002\u0010M\u001a!\u0010P\u001a\u00020Q*\u00020\u00022\u0012\u0010R\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020Q0\u0004H\u0086\b\u001a6\u0010S\u001a\u00020Q*\u00020\u00022'\u0010R\u001a#\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020Q00H\u0086\b\u001a)\u0010T\u001a\u00020\u0005*\u00020\u00022\u0006\u0010)\u001a\u00020\u001f2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\u0004H\u0087\b\u001a\u0019\u0010U\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010)\u001a\u00020\u001f¢\u0006\u0002\u0010-\u001a9\u0010V\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001c0\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b\u001aS\u0010V\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u001c0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b\u001aR\u0010W\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u001c\b\u0001\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050X0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b¢\u0006\u0002\u0010\u0018\u001al\u0010W\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u001c\b\u0002\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0X0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b¢\u0006\u0002\u0010\u0019\u001a5\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0Z\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0014\b\u0004\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0087\b\u001a!\u0010[\u001a\u00020\u001f*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010\\\u001a\u00020\u001f*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\n\u0010]\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010]\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0011\u0010^\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010A\u001a(\u0010^\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u0010=\u001a-\u0010_\u001a\b\u0012\u0004\u0012\u0002H 0\u001c\"\u0004\b\u0000\u0010 *\u00020\u00022\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H 0\u0004H\u0086\b\u001aB\u0010`\u001a\b\u0012\u0004\u0012\u0002H 0\u001c\"\u0004\b\u0000\u0010 *\u00020\u00022'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H 00H\u0086\b\u001aH\u0010a\u001a\b\u0012\u0004\u0012\u0002H 0\u001c\"\b\b\u0000\u0010 *\u00020b*\u00020\u00022)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H 00H\u0086\b\u001aa\u0010c\u001a\u0002H4\"\b\b\u0000\u0010 *\u00020b\"\u0010\b\u0001\u00104*\n\u0012\u0006\b\u0000\u0012\u0002H 0D*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H42)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H 00H\u0086\b¢\u0006\u0002\u0010d\u001a[\u0010e\u001a\u0002H4\"\u0004\b\u0000\u0010 \"\u0010\b\u0001\u00104*\n\u0012\u0006\b\u0000\u0012\u0002H 0D*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H42'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H 00H\u0086\b¢\u0006\u0002\u0010d\u001a3\u0010f\u001a\b\u0012\u0004\u0012\u0002H 0\u001c\"\b\b\u0000\u0010 *\u00020b*\u00020\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H 0\u0004H\u0086\b\u001aL\u0010g\u001a\u0002H4\"\b\b\u0000\u0010 *\u00020b\"\u0010\b\u0001\u00104*\n\u0012\u0006\b\u0000\u0012\u0002H 0D*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H42\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H 0\u0004H\u0086\b¢\u0006\u0002\u0010E\u001aF\u0010h\u001a\u0002H4\"\u0004\b\u0000\u0010 \"\u0010\b\u0001\u00104*\n\u0012\u0006\b\u0000\u0012\u0002H 0D*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H42\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H 0\u0004H\u0086\b¢\u0006\u0002\u0010E\u001a\u0011\u0010i\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010A\u001a8\u0010j\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010 *\b\u0012\u0004\u0012\u0002H 0k*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H 0\u0004H\u0086\b¢\u0006\u0002\u0010=\u001a-\u0010m\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010n\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050oj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`p¢\u0006\u0002\u0010q\u001a\u0011\u0010r\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010A\u001a8\u0010s\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010 *\b\u0012\u0004\u0012\u0002H 0k*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H 0\u0004H\u0086\b¢\u0006\u0002\u0010=\u001a-\u0010t\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010n\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050oj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`p¢\u0006\u0002\u0010q\u001a\n\u0010u\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010u\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a0\u0010v\u001a\u0002Hw\"\b\b\u0000\u0010w*\u00020\u0002*\u0002Hw2\u0012\u0010R\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020Q0\u0004H\u0087\b¢\u0006\u0002\u0010x\u001a-\u0010y\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0010*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a-\u0010y\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d0\u0010*\u00020\u001d2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a6\u0010z\u001a\u00020\u0005*\u00020\u00022'\u0010H\u001a#\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(I\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000500H\u0086\b\u001aK\u0010{\u001a\u00020\u0005*\u00020\u00022<\u0010H\u001a8\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(I\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050LH\u0086\b\u001a6\u0010|\u001a\u00020\u0005*\u00020\u00022'\u0010H\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(I\u0012\u0004\u0012\u00020\u000500H\u0086\b\u001aK\u0010}\u001a\u00020\u0005*\u00020\u00022<\u0010H\u001a8\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(I\u0012\u0004\u0012\u00020\u00050LH\u0086\b\u001a\n\u0010~\u001a\u00020\u0002*\u00020\u0002\u001a\r\u0010~\u001a\u00020\u001d*\u00020\u001dH\u0087\b\u001a\n\u0010\u007f\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010\u007f\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0012\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010A\u001a)\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u0010=\u001a\u001a\u0010\u0081\u0001\u001a\u00020\u0002*\u00020\u00022\r\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\u001f0\b\u001a\u0015\u0010\u0081\u0001\u001a\u00020\u0002*\u00020\u00022\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001\u001a\u001d\u0010\u0081\u0001\u001a\u00020\u001d*\u00020\u001d2\r\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\u001f0\bH\u0087\b\u001a\u0015\u0010\u0081\u0001\u001a\u00020\u001d*\u00020\u001d2\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001\u001a\"\u0010\u0084\u0001\u001a\u00020\u001f*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001f0\u0004H\u0086\b\u001a$\u0010\u0085\u0001\u001a\u00030\u0086\u0001*\u00020\u00022\u0013\u0010l\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u0086\u00010\u0004H\u0086\b\u001a\u0013\u0010\u0087\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010$\u001a\u00020\u001f\u001a\u0013\u0010\u0087\u0001\u001a\u00020\u001d*\u00020\u001d2\u0006\u0010$\u001a\u00020\u001f\u001a\u0013\u0010\u0088\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010$\u001a\u00020\u001f\u001a\u0013\u0010\u0088\u0001\u001a\u00020\u001d*\u00020\u001d2\u0006\u0010$\u001a\u00020\u001f\u001a\"\u0010\u0089\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\"\u0010\u0089\u0001\u001a\u00020\u001d*\u00020\u001d2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\"\u0010\u008a\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\"\u0010\u008a\u0001\u001a\u00020\u001d*\u00020\u001d2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a+\u0010\u008b\u0001\u001a\u0002H4\"\u0010\b\u0000\u00104*\n\u0012\u0006\b\u0000\u0012\u00020\u00050D*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H4¢\u0006\u0003\u0010\u008c\u0001\u001a\u001d\u0010\u008d\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00050\u008e\u0001j\t\u0012\u0004\u0012\u00020\u0005`\u008f\u0001*\u00020\u0002\u001a\u0011\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u001c*\u00020\u0002\u001a\u0011\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050X*\u00020\u0002\u001a\u0012\u0010\u0092\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050\u0093\u0001*\u00020\u0002\u001a1\u0010\u0094\u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\t\b\u0002\u0010\u0095\u0001\u001a\u00020\u001f2\t\b\u0002\u0010\u0096\u0001\u001a\u00020\u0001H\u0007\u001aK\u0010\u0094\u0001\u001a\b\u0012\u0004\u0012\u0002H 0\u001c\"\u0004\b\u0000\u0010 *\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\t\b\u0002\u0010\u0095\u0001\u001a\u00020\u001f2\t\b\u0002\u0010\u0096\u0001\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H 0\u0004H\u0007\u001a1\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0\n*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\t\b\u0002\u0010\u0095\u0001\u001a\u00020\u001f2\t\b\u0002\u0010\u0096\u0001\u001a\u00020\u0001H\u0007\u001aK\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u0002H 0\n\"\u0004\b\u0000\u0010 *\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\t\b\u0002\u0010\u0095\u0001\u001a\u00020\u001f2\t\b\u0002\u0010\u0096\u0001\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H 0\u0004H\u0007\u001a\u0018\u0010\u0098\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u00050\u0099\u00010\b*\u00020\u0002\u001a)\u0010\u009a\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100\u001c*\u00020\u00022\u0007\u0010\u009b\u0001\u001a\u00020\u0002H\u0086\u0004\u001a]\u0010\u009a\u0001\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u001c\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0007\u0010\u009b\u0001\u001a\u00020\u000228\u0010\u000f\u001a4\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b1\u0012\t\b2\u0012\u0005\b\b(\u009c\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b1\u0012\t\b2\u0012\u0005\b\b(\u009d\u0001\u0012\u0004\u0012\u0002H\u000e00H\u0086\b\u001a\u001f\u0010\u009e\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100\u001c*\u00020\u0002H\u0007\u001aT\u0010\u009e\u0001\u001a\b\u0012\u0004\u0012\u0002H 0\u001c\"\u0004\b\u0000\u0010 *\u00020\u000228\u0010\u000f\u001a4\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b1\u0012\t\b2\u0012\u0005\b\b(\u009c\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b1\u0012\t\b2\u0012\u0005\b\b(\u009d\u0001\u0012\u0004\u0012\u0002H 00H\u0087\b¨\u0006\u009f\u0001"}, d2 = {"all", "", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "associate", "", "K", "V", "transform", "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "associateByTo", "M", "", "destination", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "associateTo", "chunked", "", "", "size", "", "R", "chunkedSequence", "count", "drop", "n", "dropLast", "dropLastWhile", "dropWhile", "elementAt", "index", "elementAtOrElse", "defaultValue", "elementAtOrNull", "(Ljava/lang/CharSequence;I)Ljava/lang/Character;", "filter", "filterIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "filterIndexedTo", "C", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function2;)Ljava/lang/Appendable;", "filterNot", "filterNotTo", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Appendable;", "filterTo", "find", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "findLast", "first", "firstOrNull", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "flatMap", "flatMapTo", "", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "fold", "initial", "operation", "acc", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "foldIndexed", "Lkotlin/Function3;", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "foldRight", "foldRightIndexed", "forEach", "", "action", "forEachIndexed", "getOrElse", "getOrNull", "groupBy", "groupByTo", "", "groupingBy", "Lkotlin/collections/Grouping;", "indexOfFirst", "indexOfLast", "last", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "", "mapIndexedNotNullTo", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;)Ljava/util/Collection;", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", "max", "maxBy", "", "selector", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", "min", "minBy", "minWith", "none", "onEach", "S", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/CharSequence;", "partition", "reduce", "reduceIndexed", "reduceRight", "reduceRightIndexed", "reversed", "single", "singleOrNull", "slice", "indices", "Lkotlin/ranges/IntRange;", "sumBy", "sumByDouble", "", "take", "takeLast", "takeLastWhile", "takeWhile", "toCollection", "(Ljava/lang/CharSequence;Ljava/util/Collection;)Ljava/util/Collection;", "toHashSet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "toList", "toMutableList", "toSet", "", "windowed", "step", "partialWindows", "windowedSequence", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "zipWithNext", "kotlin-stdlib"}, k = 5, mv = {1, 1, 10}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
class StringsKt___StringsKt extends StringsKt___StringsJvmKt {
    private static final char elementAt(CharSequence charSequence, int i) {
        return charSequence.charAt(i);
    }

    private static final char elementAtOrElse(CharSequence charSequence, int i, Function1<? super Integer, Character> function1) {
        return (i < 0 || i > StringsKt.getLastIndex(charSequence)) ? function1.invoke(Integer.valueOf(i)).charValue() : charSequence.charAt(i);
    }

    private static final Character elementAtOrNull(CharSequence charSequence, int i) {
        return StringsKt.getOrNull(charSequence, i);
    }

    public static final char first(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        return receiver.charAt(0);
    }

    public static final char first(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            if (predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                return charAt;
            }
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    public static final Character firstOrNull(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() == 0) {
            return null;
        }
        return Character.valueOf(receiver.charAt(0));
    }

    public static final Character firstOrNull(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            if (predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                return Character.valueOf(charAt);
            }
        }
        return null;
    }

    private static final char getOrElse(CharSequence charSequence, int i, Function1<? super Integer, Character> function1) {
        return (i < 0 || i > StringsKt.getLastIndex(charSequence)) ? function1.invoke(Integer.valueOf(i)).charValue() : charSequence.charAt(i);
    }

    public static final Character getOrNull(CharSequence receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (i < 0 || i > StringsKt.getLastIndex(receiver)) {
            return null;
        }
        return Character.valueOf(receiver.charAt(i));
    }

    public static final int indexOfFirst(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length();
        for (int i = 0; i < length; i++) {
            if (predicate.invoke(Character.valueOf(receiver.charAt(i))).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int length = receiver.length() - 1; length >= 0; length--) {
            if (predicate.invoke(Character.valueOf(receiver.charAt(length))).booleanValue()) {
                return length;
            }
        }
        return -1;
    }

    public static final char last(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        return receiver.charAt(StringsKt.getLastIndex(receiver));
    }

    public static final char last(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length();
        do {
            length--;
            if (length >= 0) {
                charAt = receiver.charAt(length);
            } else {
                throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
            }
        } while (!predicate.invoke(Character.valueOf(charAt)).booleanValue());
        return charAt;
    }

    public static final Character lastOrNull(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() == 0) {
            return null;
        }
        return Character.valueOf(receiver.charAt(receiver.length() - 1));
    }

    public static final Character lastOrNull(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length();
        do {
            length--;
            if (length < 0) {
                return null;
            }
            charAt = receiver.charAt(length);
        } while (!predicate.invoke(Character.valueOf(charAt)).booleanValue());
        return Character.valueOf(charAt);
    }

    public static final char single(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length();
        if (length == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        if (length == 1) {
            return receiver.charAt(0);
        }
        throw new IllegalArgumentException("Char sequence has more than one element.");
    }

    public static final char single(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        Character ch = null;
        boolean z = false;
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            if (predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Char sequence contains more than one matching element.");
                }
                ch = Character.valueOf(charAt);
                z = true;
            }
        }
        if (!z) {
            throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
        }
        if (ch != null) {
            return ch.charValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Char");
    }

    public static final Character singleOrNull(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() == 1) {
            return Character.valueOf(receiver.charAt(0));
        }
        return null;
    }

    public static final Character singleOrNull(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        Character ch = null;
        boolean z = false;
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            if (predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                if (z) {
                    return null;
                }
                ch = Character.valueOf(charAt);
                z = true;
            }
        }
        if (z) {
            return ch;
        }
        return null;
    }

    public static final CharSequence drop(CharSequence receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
        }
        return receiver.subSequence(RangesKt.coerceAtMost(i, receiver.length()), receiver.length());
    }

    public static final String drop(String receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
        }
        String substring = receiver.substring(RangesKt.coerceAtMost(i, receiver.length()));
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return substring;
    }

    public static final CharSequence dropLast(CharSequence receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
        }
        return StringsKt.take(receiver, RangesKt.coerceAtLeast(receiver.length() - i, 0));
    }

    public static final String dropLast(String receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
        }
        return StringsKt.take(receiver, RangesKt.coerceAtLeast(receiver.length() - i, 0));
    }

    public static final CharSequence dropLastWhile(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int lastIndex = StringsKt.getLastIndex(receiver); lastIndex >= 0; lastIndex--) {
            if (!predicate.invoke(Character.valueOf(receiver.charAt(lastIndex))).booleanValue()) {
                return receiver.subSequence(0, lastIndex + 1);
            }
        }
        return "";
    }

    public static final String dropLastWhile(String receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int lastIndex = StringsKt.getLastIndex(receiver); lastIndex >= 0; lastIndex--) {
            if (!predicate.invoke(Character.valueOf(receiver.charAt(lastIndex))).booleanValue()) {
                String substring = receiver.substring(0, lastIndex + 1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return substring;
            }
        }
        return "";
    }

    public static final CharSequence dropWhile(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length();
        for (int i = 0; i < length; i++) {
            if (!predicate.invoke(Character.valueOf(receiver.charAt(i))).booleanValue()) {
                return receiver.subSequence(i, receiver.length());
            }
        }
        return "";
    }

    public static final String dropWhile(String receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length();
        for (int i = 0; i < length; i++) {
            if (!predicate.invoke(Character.valueOf(receiver.charAt(i))).booleanValue()) {
                String substring = receiver.substring(i);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                return substring;
            }
        }
        return "";
    }

    public static final CharSequence filter(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        int length = receiver.length();
        for (int i = 0; i < length; i++) {
            char charAt = receiver.charAt(i);
            if (predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        return sb;
    }

    public static final String filter(String receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        String str = receiver;
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterTo(StringBuilder(), predicate).toString()");
        return sb2;
    }

    public static final CharSequence filterIndexed(CharSequence receiver, Function2<? super Integer, ? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < receiver.length()) {
            char charAt = receiver.charAt(i);
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
            i++;
            i2 = i3;
        }
        return sb;
    }

    public static final String filterIndexed(String receiver, Function2<? super Integer, ? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        String str = receiver;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
            i++;
            i2 = i3;
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterIndexedTo(StringBu…(), predicate).toString()");
        return sb2;
    }

    public static final CharSequence filterNot(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            if (!predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        return sb;
    }

    public static final String filterNot(String receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        String str = receiver;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterNotTo(StringBuilder(), predicate).toString()");
        return sb2;
    }

    public static final <C extends Appendable> C filterNotTo(CharSequence receiver, C destination, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            if (!predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                destination.append(charAt);
            }
        }
        return destination;
    }

    public static final <C extends Appendable> C filterTo(CharSequence receiver, C destination, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length();
        for (int i = 0; i < length; i++) {
            char charAt = receiver.charAt(i);
            if (predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                destination.append(charAt);
            }
        }
        return destination;
    }

    public static final CharSequence slice(CharSequence receiver, IntRange indices) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(indices, "indices");
        return indices.isEmpty() ? "" : StringsKt.subSequence(receiver, indices);
    }

    public static final String slice(String receiver, IntRange indices) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(indices, "indices");
        return indices.isEmpty() ? "" : StringsKt.substring(receiver, indices);
    }

    public static final CharSequence slice(CharSequence receiver, Iterable<Integer> indices) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(indices, "indices");
        int collectionSizeOrDefault = CollectionsKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(collectionSizeOrDefault);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            sb.append(receiver.charAt(it.next().intValue()));
        }
        return sb;
    }

    private static final String slice(String str, Iterable<Integer> iterable) {
        if (str != null) {
            return StringsKt.slice((CharSequence) str, iterable).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    public static final CharSequence take(CharSequence receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
        }
        return receiver.subSequence(0, RangesKt.coerceAtMost(i, receiver.length()));
    }

    public static final String take(String receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
        }
        String substring = receiver.substring(0, RangesKt.coerceAtMost(i, receiver.length()));
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    public static final CharSequence takeLast(CharSequence receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
        }
        int length = receiver.length();
        return receiver.subSequence(length - RangesKt.coerceAtMost(i, length), length);
    }

    public static final String takeLast(String receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
        }
        int length = receiver.length();
        String substring = receiver.substring(length - RangesKt.coerceAtMost(i, length));
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return substring;
    }

    public static final CharSequence takeLastWhile(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int lastIndex = StringsKt.getLastIndex(receiver); lastIndex >= 0; lastIndex--) {
            if (!predicate.invoke(Character.valueOf(receiver.charAt(lastIndex))).booleanValue()) {
                return receiver.subSequence(lastIndex + 1, receiver.length());
            }
        }
        return receiver.subSequence(0, receiver.length());
    }

    public static final String takeLastWhile(String receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int lastIndex = StringsKt.getLastIndex(receiver); lastIndex >= 0; lastIndex--) {
            if (!predicate.invoke(Character.valueOf(receiver.charAt(lastIndex))).booleanValue()) {
                String substring = receiver.substring(lastIndex + 1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                return substring;
            }
        }
        return receiver;
    }

    public static final CharSequence takeWhile(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length();
        for (int i = 0; i < length; i++) {
            if (!predicate.invoke(Character.valueOf(receiver.charAt(i))).booleanValue()) {
                return receiver.subSequence(0, i);
            }
        }
        return receiver.subSequence(0, receiver.length());
    }

    public static final String takeWhile(String receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length();
        for (int i = 0; i < length; i++) {
            if (!predicate.invoke(Character.valueOf(receiver.charAt(i))).booleanValue()) {
                String substring = receiver.substring(0, i);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return substring;
            }
        }
        return receiver;
    }

    public static final CharSequence reversed(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        StringBuilder reverse = new StringBuilder(receiver).reverse();
        Intrinsics.checkExpressionValueIsNotNull(reverse, "StringBuilder(this).reverse()");
        return reverse;
    }

    private static final String reversed(String str) {
        if (str != null) {
            return StringsKt.reversed((CharSequence) str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    public static final <K, V> Map<K, V> associate(CharSequence receiver, Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(receiver.length()), 16));
        for (int i = 0; i < receiver.length(); i++) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Character.valueOf(receiver.charAt(i)));
            linkedHashMap.put(invoke.getFirst(), invoke.getSecond());
        }
        return linkedHashMap;
    }

    public static final <K> Map<K, Character> associateBy(CharSequence receiver, Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(receiver.length()), 16));
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            linkedHashMap.put(keySelector.invoke(Character.valueOf(charAt)), Character.valueOf(charAt));
        }
        return linkedHashMap;
    }

    public static final <K, V> Map<K, V> associateBy(CharSequence receiver, Function1<? super Character, ? extends K> keySelector, Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(receiver.length()), 16));
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            linkedHashMap.put(keySelector.invoke(Character.valueOf(charAt)), valueTransform.invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    public static final <K, M extends Map<? super K, ? super Character>> M associateByTo(CharSequence receiver, M destination, Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            destination.put(keySelector.invoke(Character.valueOf(charAt)), Character.valueOf(charAt));
        }
        return destination;
    }

    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(CharSequence receiver, M destination, Function1<? super Character, ? extends K> keySelector, Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            destination.put(keySelector.invoke(Character.valueOf(charAt)), valueTransform.invoke(Character.valueOf(charAt)));
        }
        return destination;
    }

    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(CharSequence receiver, M destination, Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        for (int i = 0; i < receiver.length(); i++) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Character.valueOf(receiver.charAt(i)));
            destination.put(invoke.getFirst(), invoke.getSecond());
        }
        return destination;
    }

    public static final <C extends Collection<? super Character>> C toCollection(CharSequence receiver, C destination) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        for (int i = 0; i < receiver.length(); i++) {
            destination.add(Character.valueOf(receiver.charAt(i)));
        }
        return destination;
    }

    public static final HashSet<Character> toHashSet(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (HashSet) StringsKt.toCollection(receiver, new HashSet(MapsKt.mapCapacity(receiver.length())));
    }

    public static final List<Character> toList(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length();
        if (length == 0) {
            return CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt.listOf(Character.valueOf(receiver.charAt(0)));
        }
        return StringsKt.toMutableList(receiver);
    }

    public static final List<Character> toMutableList(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (List) StringsKt.toCollection(receiver, new ArrayList(receiver.length()));
    }

    public static final Set<Character> toSet(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length();
        if (length == 0) {
            return SetsKt.emptySet();
        }
        if (length == 1) {
            return SetsKt.setOf(Character.valueOf(receiver.charAt(0)));
        }
        return (Set) StringsKt.toCollection(receiver, new LinkedHashSet(MapsKt.mapCapacity(receiver.length())));
    }

    public static final <R> List<R> flatMap(CharSequence receiver, Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < receiver.length(); i++) {
            CollectionsKt.addAll(arrayList, transform.invoke(Character.valueOf(receiver.charAt(i))));
        }
        return arrayList;
    }

    public static final <R, C extends Collection<? super R>> C flatMapTo(CharSequence receiver, C destination, Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        for (int i = 0; i < receiver.length(); i++) {
            CollectionsKt.addAll(destination, transform.invoke(Character.valueOf(receiver.charAt(i))));
        }
        return destination;
    }

    public static final <K> Map<K, List<Character>> groupBy(CharSequence receiver, Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            K invoke = keySelector.invoke(Character.valueOf(charAt));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(Character.valueOf(charAt));
        }
        return linkedHashMap;
    }

    public static final <K, V> Map<K, List<V>> groupBy(CharSequence receiver, Function1<? super Character, ? extends K> keySelector, Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            K invoke = keySelector.invoke(Character.valueOf(charAt));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    public static final <K, M extends Map<? super K, List<Character>>> M groupByTo(CharSequence receiver, M destination, Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            K invoke = keySelector.invoke(Character.valueOf(charAt));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(Character.valueOf(charAt));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(CharSequence receiver, M destination, Function1<? super Character, ? extends K> keySelector, Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            K invoke = keySelector.invoke(Character.valueOf(charAt));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(Character.valueOf(charAt)));
        }
        return destination;
    }

    public static final <K> Grouping<Character, K> groupingBy(final CharSequence receiver, final Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        return new Grouping<Character, K>() { // from class: kotlin.text.StringsKt___StringsKt$groupingBy$1
            @Override // kotlin.collections.Grouping
            public /* bridge */ /* synthetic */ Object keyOf(Character ch) {
                return keyOf(ch.charValue());
            }

            @Override // kotlin.collections.Grouping
            public Iterator<Character> sourceIterator() {
                return StringsKt.iterator(receiver);
            }

            public K keyOf(char element) {
                return (K) keySelector.invoke(Character.valueOf(element));
            }
        };
    }

    public static final <R> List<R> map(CharSequence receiver, Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        ArrayList arrayList = new ArrayList(receiver.length());
        for (int i = 0; i < receiver.length(); i++) {
            arrayList.add(transform.invoke(Character.valueOf(receiver.charAt(i))));
        }
        return arrayList;
    }

    public static final <R> List<R> mapIndexed(CharSequence receiver, Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        ArrayList arrayList = new ArrayList(receiver.length());
        int i = 0;
        for (int i2 = 0; i2 < receiver.length(); i2++) {
            char charAt = receiver.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            arrayList.add(transform.invoke(valueOf, Character.valueOf(charAt)));
        }
        return arrayList;
    }

    public static final <R> List<R> mapIndexedNotNull(CharSequence receiver, Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < receiver.length()) {
            int i3 = i2 + 1;
            R invoke = transform.invoke(Integer.valueOf(i2), Character.valueOf(receiver.charAt(i)));
            if (invoke != null) {
                arrayList.add(invoke);
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    public static final <R, C extends Collection<? super R>> C mapIndexedTo(CharSequence receiver, C destination, Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int i = 0;
        for (int i2 = 0; i2 < receiver.length(); i2++) {
            char charAt = receiver.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            destination.add(transform.invoke(valueOf, Character.valueOf(charAt)));
        }
        return destination;
    }

    public static final <R> List<R> mapNotNull(CharSequence receiver, Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < receiver.length(); i++) {
            R invoke = transform.invoke(Character.valueOf(receiver.charAt(i)));
            if (invoke != null) {
                arrayList.add(invoke);
            }
        }
        return arrayList;
    }

    public static final <R, C extends Collection<? super R>> C mapTo(CharSequence receiver, C destination, Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        for (int i = 0; i < receiver.length(); i++) {
            destination.add(transform.invoke(Character.valueOf(receiver.charAt(i))));
        }
        return destination;
    }

    public static final Iterable<IndexedValue<Character>> withIndex(final CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new IndexingIterable(new Function0<CharIterator>() { // from class: kotlin.text.StringsKt___StringsKt$withIndex$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CharIterator invoke() {
                return StringsKt.iterator(receiver);
            }
        });
    }

    public static final boolean all(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < receiver.length(); i++) {
            if (!predicate.invoke(Character.valueOf(receiver.charAt(i))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean any(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return !(receiver.length() == 0);
    }

    public static final boolean any(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < receiver.length(); i++) {
            if (predicate.invoke(Character.valueOf(receiver.charAt(i))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    private static final int count(CharSequence charSequence) {
        return charSequence.length();
    }

    public static final int count(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int i = 0;
        for (int i2 = 0; i2 < receiver.length(); i2++) {
            if (predicate.invoke(Character.valueOf(receiver.charAt(i2))).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public static final <R> R fold(CharSequence receiver, R r, Function2<? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        for (int i = 0; i < receiver.length(); i++) {
            r = operation.invoke(r, Character.valueOf(receiver.charAt(i)));
        }
        return r;
    }

    public static final <R> R foldIndexed(CharSequence receiver, R r, Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int i = 0;
        for (int i2 = 0; i2 < receiver.length(); i2++) {
            char charAt = receiver.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            r = operation.invoke(valueOf, r, Character.valueOf(charAt));
        }
        return r;
    }

    public static final <R> R foldRight(CharSequence receiver, R r, Function2<? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        for (int lastIndex = StringsKt.getLastIndex(receiver); lastIndex >= 0; lastIndex--) {
            r = operation.invoke(Character.valueOf(receiver.charAt(lastIndex)), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(CharSequence receiver, R r, Function3<? super Integer, ? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        for (int lastIndex = StringsKt.getLastIndex(receiver); lastIndex >= 0; lastIndex--) {
            r = operation.invoke(Integer.valueOf(lastIndex), Character.valueOf(receiver.charAt(lastIndex)), r);
        }
        return r;
    }

    public static final void forEach(CharSequence receiver, Function1<? super Character, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        for (int i = 0; i < receiver.length(); i++) {
            action.invoke(Character.valueOf(receiver.charAt(i)));
        }
    }

    public static final void forEachIndexed(CharSequence receiver, Function2<? super Integer, ? super Character, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        int i = 0;
        for (int i2 = 0; i2 < receiver.length(); i2++) {
            char charAt = receiver.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            action.invoke(valueOf, Character.valueOf(charAt));
        }
    }

    public static final Character max(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char charAt = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = receiver.charAt(i);
                if (charAt < charAt2) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final <R extends Comparable<? super R>> Character maxBy(CharSequence receiver, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char charAt = receiver.charAt(0);
        R invoke = selector.invoke(Character.valueOf(charAt));
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = receiver.charAt(i);
                R invoke2 = selector.invoke(Character.valueOf(charAt2));
                if (invoke.compareTo(invoke2) < 0) {
                    charAt = charAt2;
                    invoke = invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final Character maxWith(CharSequence receiver, Comparator<? super Character> comparator) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char charAt = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = receiver.charAt(i);
                if (comparator.compare(Character.valueOf(charAt), Character.valueOf(charAt2)) < 0) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final Character min(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char charAt = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = receiver.charAt(i);
                if (charAt > charAt2) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final <R extends Comparable<? super R>> Character minBy(CharSequence receiver, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char charAt = receiver.charAt(0);
        R invoke = selector.invoke(Character.valueOf(charAt));
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = receiver.charAt(i);
                R invoke2 = selector.invoke(Character.valueOf(charAt2));
                if (invoke.compareTo(invoke2) > 0) {
                    charAt = charAt2;
                    invoke = invoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final Character minWith(CharSequence receiver, Comparator<? super Character> comparator) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char charAt = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = receiver.charAt(i);
                if (comparator.compare(Character.valueOf(charAt), Character.valueOf(charAt2)) > 0) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final boolean none(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.length() == 0;
    }

    public static final boolean none(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        for (int i = 0; i < receiver.length(); i++) {
            if (predicate.invoke(Character.valueOf(receiver.charAt(i))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <S extends CharSequence> S onEach(S receiver, Function1<? super Character, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        for (int i = 0; i < receiver.length(); i++) {
            action.invoke(Character.valueOf(receiver.charAt(i)));
        }
        return receiver;
    }

    public static final char reduce(CharSequence receiver, Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int i = 1;
        if (receiver.length() == 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char charAt = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                charAt = operation.invoke(Character.valueOf(charAt), Character.valueOf(receiver.charAt(i))).charValue();
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return charAt;
    }

    public static final char reduceIndexed(CharSequence receiver, Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int i = 1;
        if (receiver.length() == 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char charAt = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                charAt = operation.invoke(Integer.valueOf(i), Character.valueOf(charAt), Character.valueOf(receiver.charAt(i))).charValue();
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return charAt;
    }

    public static final char reduceRight(CharSequence receiver, Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (lastIndex < 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char charAt = receiver.charAt(lastIndex);
        for (int i = lastIndex - 1; i >= 0; i--) {
            charAt = operation.invoke(Character.valueOf(receiver.charAt(i)), Character.valueOf(charAt)).charValue();
        }
        return charAt;
    }

    public static final char reduceRightIndexed(CharSequence receiver, Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (lastIndex < 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char charAt = receiver.charAt(lastIndex);
        for (int i = lastIndex - 1; i >= 0; i--) {
            charAt = operation.invoke(Integer.valueOf(i), Character.valueOf(receiver.charAt(i)), Character.valueOf(charAt)).charValue();
        }
        return charAt;
    }

    public static final int sumBy(CharSequence receiver, Function1<? super Character, Integer> selector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        int i = 0;
        for (int i2 = 0; i2 < receiver.length(); i2++) {
            i += selector.invoke(Character.valueOf(receiver.charAt(i2))).intValue();
        }
        return i;
    }

    public static final double sumByDouble(CharSequence receiver, Function1<? super Character, Double> selector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        double d = 0.0d;
        for (int i = 0; i < receiver.length(); i++) {
            d += selector.invoke(Character.valueOf(receiver.charAt(i))).doubleValue();
        }
        return d;
    }

    public static final List<String> chunked(CharSequence receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.windowed(receiver, i, i, true);
    }

    public static final <R> List<R> chunked(CharSequence receiver, int i, Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        return StringsKt.windowed(receiver, i, i, true, transform);
    }

    public static final Sequence<String> chunkedSequence(CharSequence receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.chunkedSequence(receiver, i, new Function1<CharSequence, String>() { // from class: kotlin.text.StringsKt___StringsKt$chunkedSequence$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(CharSequence it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.toString();
            }
        });
    }

    public static final <R> Sequence<R> chunkedSequence(CharSequence receiver, int i, Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        return StringsKt.windowedSequence(receiver, i, i, true, transform);
    }

    public static final Pair<CharSequence, CharSequence> partition(CharSequence receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < receiver.length(); i++) {
            char charAt = receiver.charAt(i);
            if (predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            } else {
                sb2.append(charAt);
            }
        }
        return new Pair<>(sb, sb2);
    }

    public static final Pair<String, String> partition(String receiver, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int length = receiver.length();
        for (int i = 0; i < length; i++) {
            char charAt = receiver.charAt(i);
            if (predicate.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            } else {
                sb2.append(charAt);
            }
        }
        return new Pair<>(sb.toString(), sb2.toString());
    }

    public static /* bridge */ /* synthetic */ List windowed$default(CharSequence charSequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return StringsKt.windowed(charSequence, i, i2, z);
    }

    public static final List<String> windowed(CharSequence receiver, int i, int i2, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.windowed(receiver, i, i2, z, new Function1<CharSequence, String>() { // from class: kotlin.text.StringsKt___StringsKt$windowed$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(CharSequence it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.toString();
            }
        });
    }

    public static /* bridge */ /* synthetic */ List windowed$default(CharSequence charSequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return StringsKt.windowed(charSequence, i, i2, z, function1);
    }

    public static final <R> List<R> windowed(CharSequence receiver, int i, int i2, boolean z, Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        int length = receiver.length();
        ArrayList arrayList = new ArrayList(((length + i2) - 1) / i2);
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + i;
            if (i4 > length) {
                if (!z) {
                    break;
                }
                i4 = length;
            }
            arrayList.add(transform.invoke(receiver.subSequence(i3, i4)));
            i3 += i2;
        }
        return arrayList;
    }

    public static /* bridge */ /* synthetic */ Sequence windowedSequence$default(CharSequence charSequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return StringsKt.windowedSequence(charSequence, i, i2, z);
    }

    public static final Sequence<String> windowedSequence(CharSequence receiver, int i, int i2, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.windowedSequence(receiver, i, i2, z, new Function1<CharSequence, String>() { // from class: kotlin.text.StringsKt___StringsKt$windowedSequence$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(CharSequence it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.toString();
            }
        });
    }

    public static /* bridge */ /* synthetic */ Sequence windowedSequence$default(CharSequence charSequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return StringsKt.windowedSequence(charSequence, i, i2, z, function1);
    }

    public static final <R> Sequence<R> windowedSequence(final CharSequence receiver, final int i, int i2, boolean z, final Function1<? super CharSequence, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        return SequencesKt.map(CollectionsKt.asSequence(RangesKt.step(z ? StringsKt.getIndices(receiver) : RangesKt.until(0, (receiver.length() - i) + 1), i2)), new Function1<Integer, R>() { // from class: kotlin.text.StringsKt___StringsKt$windowedSequence$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final R invoke(int i3) {
                Function1 function1 = transform;
                CharSequence charSequence = receiver;
                return (R) function1.invoke(charSequence.subSequence(i3, RangesKt.coerceAtMost(i + i3, charSequence.length())));
            }
        });
    }

    public static final <V> List<V> zip(CharSequence receiver, CharSequence other, Function2<? super Character, ? super Character, ? extends V> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int min = Math.min(receiver.length(), other.length());
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(transform.invoke(Character.valueOf(receiver.charAt(i)), Character.valueOf(other.charAt(i))));
        }
        return arrayList;
    }

    public static final <R> List<R> zipWithNext(CharSequence receiver, Function2<? super Character, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int length = receiver.length() - 1;
        if (length < 1) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            Character valueOf = Character.valueOf(receiver.charAt(i));
            i++;
            arrayList.add(transform.invoke(valueOf, Character.valueOf(receiver.charAt(i))));
        }
        return arrayList;
    }

    public static final Iterable<Character> asIterable(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver instanceof String) {
            if (receiver.length() == 0) {
                return CollectionsKt.emptyList();
            }
        }
        return new StringsKt___StringsKt$asIterable$$inlined$Iterable$1(receiver);
    }

    public static final Sequence<Character> asSequence(final CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver instanceof String) {
            if (receiver.length() == 0) {
                return SequencesKt.emptySequence();
            }
        }
        return new Sequence<Character>() { // from class: kotlin.text.StringsKt___StringsKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator<Character> iterator() {
                return StringsKt.iterator(receiver);
            }
        };
    }

    private static final Character find(CharSequence charSequence, Function1<? super Character, Boolean> function1) {
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                return Character.valueOf(charAt);
            }
        }
        return null;
    }

    private static final Character findLast(CharSequence charSequence, Function1<? super Character, Boolean> function1) {
        char charAt;
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return null;
            }
            charAt = charSequence.charAt(length);
        } while (!function1.invoke(Character.valueOf(charAt)).booleanValue());
        return Character.valueOf(charAt);
    }

    public static final <C extends Appendable> C filterIndexedTo(CharSequence receiver, C destination, Function2<? super Integer, ? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int i = 0;
        int i2 = 0;
        while (i < receiver.length()) {
            char charAt = receiver.charAt(i);
            int i3 = i2 + 1;
            if (predicate.invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                destination.append(charAt);
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    public static final <R, C extends Collection<? super R>> C mapIndexedNotNullTo(CharSequence receiver, C destination, Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int i = 0;
        int i2 = 0;
        while (i < receiver.length()) {
            int i3 = i2 + 1;
            R invoke = transform.invoke(Integer.valueOf(i2), Character.valueOf(receiver.charAt(i)));
            if (invoke != null) {
                destination.add(invoke);
            }
            i++;
            i2 = i3;
        }
        return destination;
    }

    public static final <R, C extends Collection<? super R>> C mapNotNullTo(CharSequence receiver, C destination, Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        for (int i = 0; i < receiver.length(); i++) {
            R invoke = transform.invoke(Character.valueOf(receiver.charAt(i)));
            if (invoke != null) {
                destination.add(invoke);
            }
        }
        return destination;
    }

    public static final List<Pair<Character, Character>> zip(CharSequence receiver, CharSequence other) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int min = Math.min(receiver.length(), other.length());
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(TuplesKt.to(Character.valueOf(receiver.charAt(i)), Character.valueOf(other.charAt(i))));
        }
        return arrayList;
    }

    public static final List<Pair<Character, Character>> zipWithNext(CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length() - 1;
        if (length < 1) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            char charAt = receiver.charAt(i);
            i++;
            arrayList.add(TuplesKt.to(Character.valueOf(charAt), Character.valueOf(receiver.charAt(i))));
        }
        return arrayList;
    }
}
