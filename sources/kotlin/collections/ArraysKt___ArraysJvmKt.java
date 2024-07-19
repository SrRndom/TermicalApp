package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: _ArraysJvm.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0018\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u0004\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0006\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\b\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\n\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001*\u00020\f\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\r0\u0001*\u00020\u000e\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001*\u00020\u0010\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001*\u00020\u0012\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00130\u0001*\u00020\u0014\u001aU\u0010\u0015\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010\u001c\u001a9\u0010\u0015\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010\u001d\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\n2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a0\u0010\u001e\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\f¢\u0006\u0002\u0010 \u001a \u0010!\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\"\u001a \u0010#\u001a\u00020$\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010%\u001a0\u0010&\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\f¢\u0006\u0002\u0010 \u001a\u0015\u0010&\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0087\f\u001a\u0015\u0010&\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0087\f\u001a\u0015\u0010&\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\u0087\f\u001a\u0015\u0010&\u001a\u00020\u0005*\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\u0087\f\u001a\u0015\u0010&\u001a\u00020\u0005*\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000eH\u0087\f\u001a\u0015\u0010&\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\u0087\f\u001a\u0015\u0010&\u001a\u00020\u0005*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0087\f\u001a\u0015\u0010&\u001a\u00020\u0005*\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\u0087\f\u001a \u0010'\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\"\u001a\r\u0010'\u001a\u00020\u000f*\u00020\u0006H\u0087\b\u001a\r\u0010'\u001a\u00020\u000f*\u00020\bH\u0087\b\u001a\r\u0010'\u001a\u00020\u000f*\u00020\nH\u0087\b\u001a\r\u0010'\u001a\u00020\u000f*\u00020\fH\u0087\b\u001a\r\u0010'\u001a\u00020\u000f*\u00020\u000eH\u0087\b\u001a\r\u0010'\u001a\u00020\u000f*\u00020\u0010H\u0087\b\u001a\r\u0010'\u001a\u00020\u000f*\u00020\u0012H\u0087\b\u001a\r\u0010'\u001a\u00020\u000f*\u00020\u0014H\u0087\b\u001a \u0010(\u001a\u00020$\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010%\u001a\r\u0010(\u001a\u00020$*\u00020\u0006H\u0087\b\u001a\r\u0010(\u001a\u00020$*\u00020\bH\u0087\b\u001a\r\u0010(\u001a\u00020$*\u00020\nH\u0087\b\u001a\r\u0010(\u001a\u00020$*\u00020\fH\u0087\b\u001a\r\u0010(\u001a\u00020$*\u00020\u000eH\u0087\b\u001a\r\u0010(\u001a\u00020$*\u00020\u0010H\u0087\b\u001a\r\u0010(\u001a\u00020$*\u00020\u0012H\u0087\b\u001a\r\u0010(\u001a\u00020$*\u00020\u0014H\u0087\b\u001a$\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010*\u001a.\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010+\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\u0010,\u001a\r\u0010)\u001a\u00020\u0006*\u00020\u0006H\u0087\b\u001a\u0015\u0010)\u001a\u00020\u0006*\u00020\u00062\u0006\u0010+\u001a\u00020\u000fH\u0087\b\u001a\r\u0010)\u001a\u00020\b*\u00020\bH\u0087\b\u001a\u0015\u0010)\u001a\u00020\b*\u00020\b2\u0006\u0010+\u001a\u00020\u000fH\u0087\b\u001a\r\u0010)\u001a\u00020\n*\u00020\nH\u0087\b\u001a\u0015\u0010)\u001a\u00020\n*\u00020\n2\u0006\u0010+\u001a\u00020\u000fH\u0087\b\u001a\r\u0010)\u001a\u00020\f*\u00020\fH\u0087\b\u001a\u0015\u0010)\u001a\u00020\f*\u00020\f2\u0006\u0010+\u001a\u00020\u000fH\u0087\b\u001a\r\u0010)\u001a\u00020\u000e*\u00020\u000eH\u0087\b\u001a\u0015\u0010)\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010+\u001a\u00020\u000fH\u0087\b\u001a\r\u0010)\u001a\u00020\u0010*\u00020\u0010H\u0087\b\u001a\u0015\u0010)\u001a\u00020\u0010*\u00020\u00102\u0006\u0010+\u001a\u00020\u000fH\u0087\b\u001a\r\u0010)\u001a\u00020\u0012*\u00020\u0012H\u0087\b\u001a\u0015\u0010)\u001a\u00020\u0012*\u00020\u00122\u0006\u0010+\u001a\u00020\u000fH\u0087\b\u001a\r\u0010)\u001a\u00020\u0014*\u00020\u0014H\u0087\b\u001a\u0015\u0010)\u001a\u00020\u0014*\u00020\u00142\u0006\u0010+\u001a\u00020\u000fH\u0087\b\u001a4\u0010-\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\u0010.\u001a\u001d\u0010-\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010-\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010-\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010-\u001a\u00020\f*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010-\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010-\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010-\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010-\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b\u001a7\u0010/\u001a\u000200\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u00101\u001a&\u0010/\u001a\u000200*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010/\u001a\u000200*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010/\u001a\u000200*\u00020\n2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010/\u001a\u000200*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010/\u001a\u000200*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010/\u001a\u000200*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010/\u001a\u000200*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010/\u001a\u000200*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a-\u00102\u001a\b\u0012\u0004\u0012\u0002H30\u0001\"\u0004\b\u0000\u00103*\u0006\u0012\u0002\b\u00030\u00032\f\u00104\u001a\b\u0012\u0004\u0012\u0002H305¢\u0006\u0002\u00106\u001aA\u00107\u001a\u0002H8\"\u0010\b\u0000\u00108*\n\u0012\u0006\b\u0000\u0012\u0002H309\"\u0004\b\u0001\u00103*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010:\u001a\u0002H82\f\u00104\u001a\b\u0012\u0004\u0012\u0002H305¢\u0006\u0002\u0010;\u001a,\u0010<\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010=\u001a4\u0010<\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0010>\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0086\u0002¢\u0006\u0002\u0010?\u001a2\u0010<\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u00020@H\u0086\u0002¢\u0006\u0002\u0010A\u001a\u0015\u0010<\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0005H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\u0006*\u00020\u00062\u0006\u0010>\u001a\u00020\u0006H\u0086\u0002\u001a\u001b\u0010<\u001a\u00020\u0006*\u00020\u00062\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00050@H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0007H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\b*\u00020\b2\u0006\u0010>\u001a\u00020\bH\u0086\u0002\u001a\u001b\u0010<\u001a\u00020\b*\u00020\b2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00070@H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\n*\u00020\n2\u0006\u0010\u0016\u001a\u00020\tH\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\n*\u00020\n2\u0006\u0010>\u001a\u00020\nH\u0086\u0002\u001a\u001b\u0010<\u001a\u00020\n*\u00020\n2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\t0@H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\f*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000bH\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\f*\u00020\f2\u0006\u0010>\u001a\u00020\fH\u0086\u0002\u001a\u001b\u0010<\u001a\u00020\f*\u00020\f2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000b0@H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\rH\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010>\u001a\u00020\u000eH\u0086\u0002\u001a\u001b\u0010<\u001a\u00020\u000e*\u00020\u000e2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\r0@H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000fH\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\u0010*\u00020\u00102\u0006\u0010>\u001a\u00020\u0010H\u0086\u0002\u001a\u001b\u0010<\u001a\u00020\u0010*\u00020\u00102\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000f0@H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0011H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\u0012*\u00020\u00122\u0006\u0010>\u001a\u00020\u0012H\u0086\u0002\u001a\u001b\u0010<\u001a\u00020\u0012*\u00020\u00122\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00110@H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0013H\u0086\u0002\u001a\u0015\u0010<\u001a\u00020\u0014*\u00020\u00142\u0006\u0010>\u001a\u00020\u0014H\u0086\u0002\u001a\u001b\u0010<\u001a\u00020\u0014*\u00020\u00142\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00130@H\u0086\u0002\u001a,\u0010B\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010=\u001a\u001d\u0010C\u001a\u000200\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010D\u001a*\u0010C\u001a\u000200\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020E*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010F\u001a1\u0010C\u001a\u000200\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010G\u001a\n\u0010C\u001a\u000200*\u00020\b\u001a\u001e\u0010C\u001a\u000200*\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010C\u001a\u000200*\u00020\n\u001a\u001e\u0010C\u001a\u000200*\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010C\u001a\u000200*\u00020\f\u001a\u001e\u0010C\u001a\u000200*\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010C\u001a\u000200*\u00020\u000e\u001a\u001e\u0010C\u001a\u000200*\u00020\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010C\u001a\u000200*\u00020\u0010\u001a\u001e\u0010C\u001a\u000200*\u00020\u00102\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010C\u001a\u000200*\u00020\u0012\u001a\u001e\u0010C\u001a\u000200*\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010C\u001a\u000200*\u00020\u0014\u001a\u001e\u0010C\u001a\u000200*\u00020\u00142\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a9\u0010H\u001a\u000200\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0019¢\u0006\u0002\u0010I\u001aM\u0010H\u001a\u000200\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010J\u001a-\u0010K\u001a\b\u0012\u0004\u0012\u0002H\u00020L\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020E*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010M\u001a?\u0010K\u001a\b\u0012\u0004\u0012\u0002H\u00020L\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0019¢\u0006\u0002\u0010N\u001a\u0010\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00050L*\u00020\u0006\u001a\u0010\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00070L*\u00020\b\u001a\u0010\u0010K\u001a\b\u0012\u0004\u0012\u00020\t0L*\u00020\n\u001a\u0010\u0010K\u001a\b\u0012\u0004\u0012\u00020\u000b0L*\u00020\f\u001a\u0010\u0010K\u001a\b\u0012\u0004\u0012\u00020\r0L*\u00020\u000e\u001a\u0010\u0010K\u001a\b\u0012\u0004\u0012\u00020\u000f0L*\u00020\u0010\u001a\u0010\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00110L*\u00020\u0012\u001a\u0010\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00130L*\u00020\u0014\u001a\u0015\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00050\u0003*\u00020\u0006¢\u0006\u0002\u0010P\u001a\u0015\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003*\u00020\b¢\u0006\u0002\u0010Q\u001a\u0015\u0010O\u001a\b\u0012\u0004\u0012\u00020\t0\u0003*\u00020\n¢\u0006\u0002\u0010R\u001a\u0015\u0010O\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003*\u00020\f¢\u0006\u0002\u0010S\u001a\u0015\u0010O\u001a\b\u0012\u0004\u0012\u00020\r0\u0003*\u00020\u000e¢\u0006\u0002\u0010T\u001a\u0015\u0010O\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0003*\u00020\u0010¢\u0006\u0002\u0010U\u001a\u0015\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003*\u00020\u0012¢\u0006\u0002\u0010V\u001a\u0015\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00130\u0003*\u00020\u0014¢\u0006\u0002\u0010W¨\u0006X"}, d2 = {"asList", "", "T", "", "([Ljava/lang/Object;)Ljava/util/List;", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "binarySearch", "element", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "fromIndex", "toIndex", "([Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;II)I", "([Ljava/lang/Object;Ljava/lang/Object;II)I", "contentDeepEquals", "other", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepHashCode", "([Ljava/lang/Object;)I", "contentDeepToString", "", "([Ljava/lang/Object;)Ljava/lang/String;", "contentEquals", "contentHashCode", "contentToString", "copyOf", "([Ljava/lang/Object;)[Ljava/lang/Object;", "newSize", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "copyOfRange", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "fill", "", "([Ljava/lang/Object;Ljava/lang/Object;II)V", "filterIsInstance", "R", "klass", "Ljava/lang/Class;", "([Ljava/lang/Object;Ljava/lang/Class;)Ljava/util/List;", "filterIsInstanceTo", "C", "", "destination", "([Ljava/lang/Object;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "plus", "([Ljava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;", "elements", "([Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;", "", "([Ljava/lang/Object;Ljava/util/Collection;)[Ljava/lang/Object;", "plusElement", "sort", "([Ljava/lang/Object;)V", "", "([Ljava/lang/Comparable;)V", "([Ljava/lang/Object;II)V", "sortWith", "([Ljava/lang/Object;Ljava/util/Comparator;)V", "([Ljava/lang/Object;Ljava/util/Comparator;II)V", "toSortedSet", "Ljava/util/SortedSet;", "([Ljava/lang/Comparable;)Ljava/util/SortedSet;", "([Ljava/lang/Object;Ljava/util/Comparator;)Ljava/util/SortedSet;", "toTypedArray", "([Z)[Ljava/lang/Boolean;", "([B)[Ljava/lang/Byte;", "([C)[Ljava/lang/Character;", "([D)[Ljava/lang/Double;", "([F)[Ljava/lang/Float;", "([I)[Ljava/lang/Integer;", "([J)[Ljava/lang/Long;", "([S)[Ljava/lang/Short;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 10}, xi = 1, xs = "kotlin/collections/ArraysKt")
/* loaded from: classes.dex */
public class ArraysKt___ArraysJvmKt extends ArraysKt__ArraysKt {
    public static final <R> List<R> filterIsInstance(Object[] receiver, Class<R> klass) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(klass, "klass");
        return (List) ArraysKt.filterIsInstanceTo(receiver, new ArrayList(), klass);
    }

    public static final <C extends Collection<? super R>, R> C filterIsInstanceTo(Object[] receiver, C destination, Class<R> klass) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(klass, "klass");
        for (Object obj : receiver) {
            if (klass.isInstance(obj)) {
                destination.add(obj);
            }
        }
        return destination;
    }

    public static final <T> List<T> asList(T[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        List<T> asList = ArraysUtilJVM.asList(receiver);
        Intrinsics.checkExpressionValueIsNotNull(asList, "ArraysUtilJVM.asList(this)");
        return asList;
    }

    public static final List<Byte> asList(byte[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ArraysKt___ArraysJvmKt$asList$1(receiver);
    }

    public static final List<Short> asList(short[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ArraysKt___ArraysJvmKt$asList$2(receiver);
    }

    public static final List<Integer> asList(int[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ArraysKt___ArraysJvmKt$asList$3(receiver);
    }

    public static final List<Long> asList(long[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ArraysKt___ArraysJvmKt$asList$4(receiver);
    }

    public static final List<Float> asList(float[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ArraysKt___ArraysJvmKt$asList$5(receiver);
    }

    public static final List<Double> asList(double[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ArraysKt___ArraysJvmKt$asList$6(receiver);
    }

    public static final List<Boolean> asList(boolean[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ArraysKt___ArraysJvmKt$asList$7(receiver);
    }

    public static final List<Character> asList(char[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ArraysKt___ArraysJvmKt$asList$8(receiver);
    }

    public static /* bridge */ /* synthetic */ int binarySearch$default(Object[] objArr, Object obj, Comparator comparator, int i, int i2, int i3, Object obj2) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = objArr.length;
        }
        return ArraysKt.binarySearch(objArr, obj, comparator, i, i2);
    }

    public static final <T> int binarySearch(T[] receiver, T t, Comparator<? super T> comparator, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        return Arrays.binarySearch(receiver, i, i2, t, comparator);
    }

    public static /* bridge */ /* synthetic */ int binarySearch$default(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        return ArraysKt.binarySearch(objArr, obj, i, i2);
    }

    public static final <T> int binarySearch(T[] receiver, T t, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Arrays.binarySearch(receiver, i, i2, t);
    }

    public static /* bridge */ /* synthetic */ int binarySearch$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return ArraysKt.binarySearch(bArr, b, i, i2);
    }

    public static final int binarySearch(byte[] receiver, byte b, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Arrays.binarySearch(receiver, i, i2, b);
    }

    public static /* bridge */ /* synthetic */ int binarySearch$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        return ArraysKt.binarySearch(sArr, s, i, i2);
    }

    public static final int binarySearch(short[] receiver, short s, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Arrays.binarySearch(receiver, i, i2, s);
    }

    public static /* bridge */ /* synthetic */ int binarySearch$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        return ArraysKt.binarySearch(iArr, i, i2, i3);
    }

    public static final int binarySearch(int[] receiver, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Arrays.binarySearch(receiver, i2, i3, i);
    }

    public static /* bridge */ /* synthetic */ int binarySearch$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        return ArraysKt.binarySearch(jArr, j, i, i2);
    }

    public static final int binarySearch(long[] receiver, long j, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Arrays.binarySearch(receiver, i, i2, j);
    }

    public static /* bridge */ /* synthetic */ int binarySearch$default(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        return ArraysKt.binarySearch(fArr, f, i, i2);
    }

    public static final int binarySearch(float[] receiver, float f, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Arrays.binarySearch(receiver, i, i2, f);
    }

    public static /* bridge */ /* synthetic */ int binarySearch$default(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        return ArraysKt.binarySearch(dArr, d, i, i2);
    }

    public static final int binarySearch(double[] receiver, double d, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Arrays.binarySearch(receiver, i, i2, d);
    }

    public static /* bridge */ /* synthetic */ int binarySearch$default(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return ArraysKt.binarySearch(cArr, c, i, i2);
    }

    public static final int binarySearch(char[] receiver, char c, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Arrays.binarySearch(receiver, i, i2, c);
    }

    private static final <T> boolean contentDeepEquals(T[] tArr, T[] tArr2) {
        return Arrays.deepEquals(tArr, tArr2);
    }

    private static final <T> int contentDeepHashCode(T[] tArr) {
        return Arrays.deepHashCode(tArr);
    }

    private static final <T> String contentDeepToString(T[] tArr) {
        String deepToString = Arrays.deepToString(tArr);
        Intrinsics.checkExpressionValueIsNotNull(deepToString, "java.util.Arrays.deepToString(this)");
        return deepToString;
    }

    private static final <T> boolean contentEquals(T[] tArr, T[] tArr2) {
        return Arrays.equals(tArr, tArr2);
    }

    private static final boolean contentEquals(byte[] bArr, byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }

    private static final boolean contentEquals(short[] sArr, short[] sArr2) {
        return Arrays.equals(sArr, sArr2);
    }

    private static final boolean contentEquals(int[] iArr, int[] iArr2) {
        return Arrays.equals(iArr, iArr2);
    }

    private static final boolean contentEquals(long[] jArr, long[] jArr2) {
        return Arrays.equals(jArr, jArr2);
    }

    private static final boolean contentEquals(float[] fArr, float[] fArr2) {
        return Arrays.equals(fArr, fArr2);
    }

    private static final boolean contentEquals(double[] dArr, double[] dArr2) {
        return Arrays.equals(dArr, dArr2);
    }

    private static final boolean contentEquals(boolean[] zArr, boolean[] zArr2) {
        return Arrays.equals(zArr, zArr2);
    }

    private static final boolean contentEquals(char[] cArr, char[] cArr2) {
        return Arrays.equals(cArr, cArr2);
    }

    private static final <T> int contentHashCode(T[] tArr) {
        return Arrays.hashCode(tArr);
    }

    private static final int contentHashCode(byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    private static final int contentHashCode(short[] sArr) {
        return Arrays.hashCode(sArr);
    }

    private static final int contentHashCode(int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    private static final int contentHashCode(long[] jArr) {
        return Arrays.hashCode(jArr);
    }

    private static final int contentHashCode(float[] fArr) {
        return Arrays.hashCode(fArr);
    }

    private static final int contentHashCode(double[] dArr) {
        return Arrays.hashCode(dArr);
    }

    private static final int contentHashCode(boolean[] zArr) {
        return Arrays.hashCode(zArr);
    }

    private static final int contentHashCode(char[] cArr) {
        return Arrays.hashCode(cArr);
    }

    private static final <T> String contentToString(T[] tArr) {
        String arrays = Arrays.toString(tArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    private static final String contentToString(byte[] bArr) {
        String arrays = Arrays.toString(bArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    private static final String contentToString(short[] sArr) {
        String arrays = Arrays.toString(sArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    private static final String contentToString(int[] iArr) {
        String arrays = Arrays.toString(iArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    private static final String contentToString(long[] jArr) {
        String arrays = Arrays.toString(jArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    private static final String contentToString(float[] fArr) {
        String arrays = Arrays.toString(fArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    private static final String contentToString(double[] dArr) {
        String arrays = Arrays.toString(dArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    private static final String contentToString(boolean[] zArr) {
        String arrays = Arrays.toString(zArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    private static final String contentToString(char[] cArr) {
        String arrays = Arrays.toString(cArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    private static final <T> T[] copyOf(T[] tArr) {
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, tArr.length);
        Intrinsics.checkExpressionValueIsNotNull(tArr2, "java.util.Arrays.copyOf(this, size)");
        return tArr2;
    }

    private static final byte[] copyOf(byte[] bArr) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    private static final short[] copyOf(short[] sArr) {
        short[] copyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    private static final int[] copyOf(int[] iArr) {
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    private static final long[] copyOf(long[] jArr) {
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    private static final float[] copyOf(float[] fArr) {
        float[] copyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    private static final double[] copyOf(double[] dArr) {
        double[] copyOf = Arrays.copyOf(dArr, dArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    private static final boolean[] copyOf(boolean[] zArr) {
        boolean[] copyOf = Arrays.copyOf(zArr, zArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    private static final char[] copyOf(char[] cArr) {
        char[] copyOf = Arrays.copyOf(cArr, cArr.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    private static final byte[] copyOf(byte[] bArr, int i) {
        byte[] copyOf = Arrays.copyOf(bArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    private static final short[] copyOf(short[] sArr, int i) {
        short[] copyOf = Arrays.copyOf(sArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    private static final int[] copyOf(int[] iArr, int i) {
        int[] copyOf = Arrays.copyOf(iArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    private static final long[] copyOf(long[] jArr, int i) {
        long[] copyOf = Arrays.copyOf(jArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    private static final float[] copyOf(float[] fArr, int i) {
        float[] copyOf = Arrays.copyOf(fArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    private static final double[] copyOf(double[] dArr, int i) {
        double[] copyOf = Arrays.copyOf(dArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    private static final boolean[] copyOf(boolean[] zArr, int i) {
        boolean[] copyOf = Arrays.copyOf(zArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    private static final char[] copyOf(char[] cArr, int i) {
        char[] copyOf = Arrays.copyOf(cArr, i);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    private static final <T> T[] copyOf(T[] tArr, int i) {
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, i);
        Intrinsics.checkExpressionValueIsNotNull(tArr2, "java.util.Arrays.copyOf(this, newSize)");
        return tArr2;
    }

    private static final <T> T[] copyOfRange(T[] tArr, int i, int i2) {
        T[] tArr2 = (T[]) Arrays.copyOfRange(tArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(tArr2, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return tArr2;
    }

    private static final byte[] copyOfRange(byte[] bArr, int i, int i2) {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    private static final short[] copyOfRange(short[] sArr, int i, int i2) {
        short[] copyOfRange = Arrays.copyOfRange(sArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    private static final int[] copyOfRange(int[] iArr, int i, int i2) {
        int[] copyOfRange = Arrays.copyOfRange(iArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    private static final long[] copyOfRange(long[] jArr, int i, int i2) {
        long[] copyOfRange = Arrays.copyOfRange(jArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    private static final float[] copyOfRange(float[] fArr, int i, int i2) {
        float[] copyOfRange = Arrays.copyOfRange(fArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    private static final double[] copyOfRange(double[] dArr, int i, int i2) {
        double[] copyOfRange = Arrays.copyOfRange(dArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    private static final boolean[] copyOfRange(boolean[] zArr, int i, int i2) {
        boolean[] copyOfRange = Arrays.copyOfRange(zArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    private static final char[] copyOfRange(char[] cArr, int i, int i2) {
        char[] copyOfRange = Arrays.copyOfRange(cArr, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    public static /* bridge */ /* synthetic */ void fill$default(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        ArraysKt.fill(objArr, obj, i, i2);
    }

    public static final <T> void fill(T[] receiver, T t, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.fill(receiver, i, i2, t);
    }

    public static /* bridge */ /* synthetic */ void fill$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        ArraysKt.fill(bArr, b, i, i2);
    }

    public static final void fill(byte[] receiver, byte b, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.fill(receiver, i, i2, b);
    }

    public static /* bridge */ /* synthetic */ void fill$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        ArraysKt.fill(sArr, s, i, i2);
    }

    public static final void fill(short[] receiver, short s, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.fill(receiver, i, i2, s);
    }

    public static /* bridge */ /* synthetic */ void fill$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        ArraysKt.fill(iArr, i, i2, i3);
    }

    public static final void fill(int[] receiver, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.fill(receiver, i2, i3, i);
    }

    public static /* bridge */ /* synthetic */ void fill$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        ArraysKt.fill(jArr, j, i, i2);
    }

    public static final void fill(long[] receiver, long j, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.fill(receiver, i, i2, j);
    }

    public static /* bridge */ /* synthetic */ void fill$default(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        ArraysKt.fill(fArr, f, i, i2);
    }

    public static final void fill(float[] receiver, float f, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.fill(receiver, i, i2, f);
    }

    public static /* bridge */ /* synthetic */ void fill$default(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        ArraysKt.fill(dArr, d, i, i2);
    }

    public static final void fill(double[] receiver, double d, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.fill(receiver, i, i2, d);
    }

    public static /* bridge */ /* synthetic */ void fill$default(boolean[] zArr, boolean z, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = zArr.length;
        }
        ArraysKt.fill(zArr, z, i, i2);
    }

    public static final void fill(boolean[] receiver, boolean z, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.fill(receiver, i, i2, z);
    }

    public static /* bridge */ /* synthetic */ void fill$default(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        ArraysKt.fill(cArr, c, i, i2);
    }

    public static final void fill(char[] receiver, char c, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.fill(receiver, i, i2, c);
    }

    public static final <T> T[] plus(T[] receiver, T t) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length;
        T[] result = (T[]) Arrays.copyOf(receiver, length + 1);
        result[length] = t;
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final byte[] plus(byte[] receiver, byte b) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length;
        byte[] result = Arrays.copyOf(receiver, length + 1);
        result[length] = b;
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final short[] plus(short[] receiver, short s) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length;
        short[] result = Arrays.copyOf(receiver, length + 1);
        result[length] = s;
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final int[] plus(int[] receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length;
        int[] result = Arrays.copyOf(receiver, length + 1);
        result[length] = i;
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final long[] plus(long[] receiver, long j) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length;
        long[] result = Arrays.copyOf(receiver, length + 1);
        result[length] = j;
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final float[] plus(float[] receiver, float f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length;
        float[] result = Arrays.copyOf(receiver, length + 1);
        result[length] = f;
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final double[] plus(double[] receiver, double d) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length;
        double[] result = Arrays.copyOf(receiver, length + 1);
        result[length] = d;
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final boolean[] plus(boolean[] receiver, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length;
        boolean[] result = Arrays.copyOf(receiver, length + 1);
        result[length] = z;
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final char[] plus(char[] receiver, char c) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int length = receiver.length;
        char[] result = Arrays.copyOf(receiver, length + 1);
        result[length] = c;
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final <T> T[] plus(T[] receiver, Collection<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        T[] result = (T[]) Arrays.copyOf(receiver, elements.size() + length);
        Iterator<? extends T> it = elements.iterator();
        while (it.hasNext()) {
            result[length] = it.next();
            length++;
        }
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final byte[] plus(byte[] receiver, Collection<Byte> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        byte[] result = Arrays.copyOf(receiver, elements.size() + length);
        Iterator<Byte> it = elements.iterator();
        while (it.hasNext()) {
            result[length] = it.next().byteValue();
            length++;
        }
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final short[] plus(short[] receiver, Collection<Short> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        short[] result = Arrays.copyOf(receiver, elements.size() + length);
        Iterator<Short> it = elements.iterator();
        while (it.hasNext()) {
            result[length] = it.next().shortValue();
            length++;
        }
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final int[] plus(int[] receiver, Collection<Integer> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        int[] result = Arrays.copyOf(receiver, elements.size() + length);
        Iterator<Integer> it = elements.iterator();
        while (it.hasNext()) {
            result[length] = it.next().intValue();
            length++;
        }
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final long[] plus(long[] receiver, Collection<Long> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        long[] result = Arrays.copyOf(receiver, elements.size() + length);
        Iterator<Long> it = elements.iterator();
        while (it.hasNext()) {
            result[length] = it.next().longValue();
            length++;
        }
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final float[] plus(float[] receiver, Collection<Float> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        float[] result = Arrays.copyOf(receiver, elements.size() + length);
        Iterator<Float> it = elements.iterator();
        while (it.hasNext()) {
            result[length] = it.next().floatValue();
            length++;
        }
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final double[] plus(double[] receiver, Collection<Double> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        double[] result = Arrays.copyOf(receiver, elements.size() + length);
        Iterator<Double> it = elements.iterator();
        while (it.hasNext()) {
            result[length] = it.next().doubleValue();
            length++;
        }
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final boolean[] plus(boolean[] receiver, Collection<Boolean> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        boolean[] result = Arrays.copyOf(receiver, elements.size() + length);
        Iterator<Boolean> it = elements.iterator();
        while (it.hasNext()) {
            result[length] = it.next().booleanValue();
            length++;
        }
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final char[] plus(char[] receiver, Collection<Character> elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        char[] result = Arrays.copyOf(receiver, elements.size() + length);
        Iterator<Character> it = elements.iterator();
        while (it.hasNext()) {
            result[length] = it.next().charValue();
            length++;
        }
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final <T> T[] plus(T[] receiver, T[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        int length2 = elements.length;
        T[] result = (T[]) Arrays.copyOf(receiver, length + length2);
        System.arraycopy(elements, 0, result, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final byte[] plus(byte[] receiver, byte[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        int length2 = elements.length;
        byte[] result = Arrays.copyOf(receiver, length + length2);
        System.arraycopy(elements, 0, result, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final short[] plus(short[] receiver, short[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        int length2 = elements.length;
        short[] result = Arrays.copyOf(receiver, length + length2);
        System.arraycopy(elements, 0, result, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final int[] plus(int[] receiver, int[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        int length2 = elements.length;
        int[] result = Arrays.copyOf(receiver, length + length2);
        System.arraycopy(elements, 0, result, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final long[] plus(long[] receiver, long[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        int length2 = elements.length;
        long[] result = Arrays.copyOf(receiver, length + length2);
        System.arraycopy(elements, 0, result, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final float[] plus(float[] receiver, float[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        int length2 = elements.length;
        float[] result = Arrays.copyOf(receiver, length + length2);
        System.arraycopy(elements, 0, result, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final double[] plus(double[] receiver, double[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        int length2 = elements.length;
        double[] result = Arrays.copyOf(receiver, length + length2);
        System.arraycopy(elements, 0, result, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final boolean[] plus(boolean[] receiver, boolean[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        int length2 = elements.length;
        boolean[] result = Arrays.copyOf(receiver, length + length2);
        System.arraycopy(elements, 0, result, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    public static final char[] plus(char[] receiver, char[] elements) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        int length = receiver.length;
        int length2 = elements.length;
        char[] result = Arrays.copyOf(receiver, length + length2);
        System.arraycopy(elements, 0, result, length, length2);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        return result;
    }

    private static final <T> T[] plusElement(T[] tArr, T t) {
        return (T[]) ArraysKt.plus(tArr, t);
    }

    public static final void sort(int[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length > 1) {
            Arrays.sort(receiver);
        }
    }

    public static final void sort(long[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length > 1) {
            Arrays.sort(receiver);
        }
    }

    public static final void sort(byte[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length > 1) {
            Arrays.sort(receiver);
        }
    }

    public static final void sort(short[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length > 1) {
            Arrays.sort(receiver);
        }
    }

    public static final void sort(double[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length > 1) {
            Arrays.sort(receiver);
        }
    }

    public static final void sort(float[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length > 1) {
            Arrays.sort(receiver);
        }
    }

    public static final void sort(char[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length > 1) {
            Arrays.sort(receiver);
        }
    }

    private static final <T extends Comparable<? super T>> void sort(T[] tArr) {
        if (tArr == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        }
        ArraysKt.sort((Object[]) tArr);
    }

    public static final <T> void sort(T[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length > 1) {
            Arrays.sort(receiver);
        }
    }

    public static /* bridge */ /* synthetic */ void sort$default(Object[] objArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = objArr.length;
        }
        ArraysKt.sort(objArr, i, i2);
    }

    public static final <T> void sort(T[] receiver, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.sort(receiver, i, i2);
    }

    public static /* bridge */ /* synthetic */ void sort$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        ArraysKt.sort(bArr, i, i2);
    }

    public static final void sort(byte[] receiver, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.sort(receiver, i, i2);
    }

    public static /* bridge */ /* synthetic */ void sort$default(short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = sArr.length;
        }
        ArraysKt.sort(sArr, i, i2);
    }

    public static final void sort(short[] receiver, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.sort(receiver, i, i2);
    }

    public static /* bridge */ /* synthetic */ void sort$default(int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = iArr.length;
        }
        ArraysKt.sort(iArr, i, i2);
    }

    public static final void sort(int[] receiver, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.sort(receiver, i, i2);
    }

    public static /* bridge */ /* synthetic */ void sort$default(long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = jArr.length;
        }
        ArraysKt.sort(jArr, i, i2);
    }

    public static final void sort(long[] receiver, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.sort(receiver, i, i2);
    }

    public static /* bridge */ /* synthetic */ void sort$default(float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = fArr.length;
        }
        ArraysKt.sort(fArr, i, i2);
    }

    public static final void sort(float[] receiver, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.sort(receiver, i, i2);
    }

    public static /* bridge */ /* synthetic */ void sort$default(double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = dArr.length;
        }
        ArraysKt.sort(dArr, i, i2);
    }

    public static final void sort(double[] receiver, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.sort(receiver, i, i2);
    }

    public static /* bridge */ /* synthetic */ void sort$default(char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = cArr.length;
        }
        ArraysKt.sort(cArr, i, i2);
    }

    public static final void sort(char[] receiver, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Arrays.sort(receiver, i, i2);
    }

    public static final <T> void sortWith(T[] receiver, Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        if (receiver.length > 1) {
            Arrays.sort(receiver, comparator);
        }
    }

    public static /* bridge */ /* synthetic */ void sortWith$default(Object[] objArr, Comparator comparator, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        ArraysKt.sortWith(objArr, comparator, i, i2);
    }

    public static final <T> void sortWith(T[] receiver, Comparator<? super T> comparator, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        Arrays.sort(receiver, i, i2, comparator);
    }

    public static final Byte[] toTypedArray(byte[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Byte[] bArr = new Byte[receiver.length];
        int length = receiver.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = Byte.valueOf(receiver[i]);
        }
        return bArr;
    }

    public static final Short[] toTypedArray(short[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Short[] shArr = new Short[receiver.length];
        int length = receiver.length;
        for (int i = 0; i < length; i++) {
            shArr[i] = Short.valueOf(receiver[i]);
        }
        return shArr;
    }

    public static final Integer[] toTypedArray(int[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Integer[] numArr = new Integer[receiver.length];
        int length = receiver.length;
        for (int i = 0; i < length; i++) {
            numArr[i] = Integer.valueOf(receiver[i]);
        }
        return numArr;
    }

    public static final Long[] toTypedArray(long[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Long[] lArr = new Long[receiver.length];
        int length = receiver.length;
        for (int i = 0; i < length; i++) {
            lArr[i] = Long.valueOf(receiver[i]);
        }
        return lArr;
    }

    public static final Float[] toTypedArray(float[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Float[] fArr = new Float[receiver.length];
        int length = receiver.length;
        for (int i = 0; i < length; i++) {
            fArr[i] = Float.valueOf(receiver[i]);
        }
        return fArr;
    }

    public static final Double[] toTypedArray(double[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Double[] dArr = new Double[receiver.length];
        int length = receiver.length;
        for (int i = 0; i < length; i++) {
            dArr[i] = Double.valueOf(receiver[i]);
        }
        return dArr;
    }

    public static final Boolean[] toTypedArray(boolean[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Boolean[] boolArr = new Boolean[receiver.length];
        int length = receiver.length;
        for (int i = 0; i < length; i++) {
            boolArr[i] = Boolean.valueOf(receiver[i]);
        }
        return boolArr;
    }

    public static final Character[] toTypedArray(char[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Character[] chArr = new Character[receiver.length];
        int length = receiver.length;
        for (int i = 0; i < length; i++) {
            chArr[i] = Character.valueOf(receiver[i]);
        }
        return chArr;
    }

    public static final <T extends Comparable<? super T>> SortedSet<T> toSortedSet(T[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (SortedSet) ArraysKt.toCollection(receiver, new TreeSet());
    }

    public static final SortedSet<Byte> toSortedSet(byte[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (SortedSet) ArraysKt.toCollection(receiver, new TreeSet());
    }

    public static final SortedSet<Short> toSortedSet(short[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (SortedSet) ArraysKt.toCollection(receiver, new TreeSet());
    }

    public static final SortedSet<Integer> toSortedSet(int[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (SortedSet) ArraysKt.toCollection(receiver, new TreeSet());
    }

    public static final SortedSet<Long> toSortedSet(long[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (SortedSet) ArraysKt.toCollection(receiver, new TreeSet());
    }

    public static final SortedSet<Float> toSortedSet(float[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (SortedSet) ArraysKt.toCollection(receiver, new TreeSet());
    }

    public static final SortedSet<Double> toSortedSet(double[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (SortedSet) ArraysKt.toCollection(receiver, new TreeSet());
    }

    public static final SortedSet<Boolean> toSortedSet(boolean[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (SortedSet) ArraysKt.toCollection(receiver, new TreeSet());
    }

    public static final SortedSet<Character> toSortedSet(char[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (SortedSet) ArraysKt.toCollection(receiver, new TreeSet());
    }

    public static final <T> SortedSet<T> toSortedSet(T[] receiver, Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        return (SortedSet) ArraysKt.toCollection(receiver, new TreeSet(comparator));
    }
}
