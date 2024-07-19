package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StringNumberConversions.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0003\u001a\u001b\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005*\u00020\u0002H\u0007¢\u0006\u0002\u0010\b\u001a\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\t\u001a\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u0002H\u0007¢\u0006\u0002\u0010\f\u001a\u001b\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\r\u001a\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0010\u001a\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"toByteOrNull", "", "", "(Ljava/lang/String;)Ljava/lang/Byte;", "radix", "", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLongOrNull", "", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShortOrNull", "", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 10}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
class StringsKt__StringNumberConversionsKt extends StringsKt__StringNumberConversionsJVMKt {
    public static final Byte toByteOrNull(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.toByteOrNull(receiver, 10);
    }

    public static final Byte toByteOrNull(String receiver, int i) {
        int intValue;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Integer intOrNull = StringsKt.toIntOrNull(receiver, i);
        if (intOrNull == null || (intValue = intOrNull.intValue()) < -128 || intValue > 127) {
            return null;
        }
        return Byte.valueOf((byte) intValue);
    }

    public static final Short toShortOrNull(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.toShortOrNull(receiver, 10);
    }

    public static final Short toShortOrNull(String receiver, int i) {
        int intValue;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Integer intOrNull = StringsKt.toIntOrNull(receiver, i);
        if (intOrNull == null || (intValue = intOrNull.intValue()) < -32768 || intValue > 32767) {
            return null;
        }
        return Short.valueOf((short) intValue);
    }

    public static final Integer toIntOrNull(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.toIntOrNull(receiver, 10);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037 A[LOOP:0: B:14:0x0037->B:21:0x004f, LOOP_START, PHI: r2 r3
      0x0037: PHI (r2v2 int) = (r2v0 int), (r2v4 int) binds: [B:13:0x0035, B:21:0x004f] A[DONT_GENERATE, DONT_INLINE]
      0x0037: PHI (r3v4 int) = (r3v3 int), (r3v5 int) binds: [B:13:0x0035, B:21:0x004f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Integer toIntOrNull(java.lang.String r9, int r10) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            kotlin.text.CharsKt.checkRadix(r10)
            int r0 = r9.length()
            r1 = 0
            if (r0 != 0) goto L10
            return r1
        L10:
            r2 = 0
            char r3 = r9.charAt(r2)
            r4 = 48
            r5 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            r6 = 1
            if (r3 >= r4) goto L30
            if (r0 != r6) goto L20
            return r1
        L20:
            r4 = 45
            if (r3 != r4) goto L29
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1
            r4 = 1
            goto L32
        L29:
            r4 = 43
            if (r3 != r4) goto L2f
            r3 = 1
            goto L31
        L2f:
            return r1
        L30:
            r3 = 0
        L31:
            r4 = 0
        L32:
            int r7 = r5 / r10
            int r0 = r0 - r6
            if (r3 > r0) goto L52
        L37:
            char r6 = r9.charAt(r3)
            int r6 = kotlin.text.CharsKt.digitOf(r6, r10)
            if (r6 >= 0) goto L42
            return r1
        L42:
            if (r2 >= r7) goto L45
            return r1
        L45:
            int r2 = r2 * r10
            int r8 = r5 + r6
            if (r2 >= r8) goto L4c
            return r1
        L4c:
            int r2 = r2 - r6
            if (r3 == r0) goto L52
            int r3 = r3 + 1
            goto L37
        L52:
            if (r4 == 0) goto L59
            java.lang.Integer r9 = java.lang.Integer.valueOf(r2)
            goto L5e
        L59:
            int r9 = -r2
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
        L5e:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringNumberConversionsKt.toIntOrNull(java.lang.String, int):java.lang.Integer");
    }

    public static final Long toLongOrNull(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.toLongOrNull(receiver, 10);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f A[LOOP:0: B:14:0x003f->B:22:0x0062, LOOP_START, PHI: r3 r4 r14
      0x003f: PHI (r3v1 java.lang.Long) = (r3v0 java.lang.Long), (r3v3 java.lang.Long) binds: [B:13:0x003d, B:22:0x0062] A[DONT_GENERATE, DONT_INLINE]
      0x003f: PHI (r4v3 int) = (r4v2 int), (r4v5 int) binds: [B:13:0x003d, B:22:0x0062] A[DONT_GENERATE, DONT_INLINE]
      0x003f: PHI (r14v2 long) = (r14v0 long), (r14v4 long) binds: [B:13:0x003d, B:22:0x0062] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Long toLongOrNull(java.lang.String r19, int r20) {
        /*
            r0 = r19
            r1 = r20
            java.lang.String r2 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r2)
            kotlin.text.CharsKt.checkRadix(r20)
            int r2 = r19.length()
            r3 = 0
            if (r2 != 0) goto L14
            return r3
        L14:
            r4 = 0
            char r5 = r0.charAt(r4)
            r6 = 48
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r9 = 1
            if (r5 >= r6) goto L36
            if (r2 != r9) goto L26
            return r3
        L26:
            r6 = 45
            if (r5 != r6) goto L2f
            r7 = -9223372036854775808
            r4 = 1
            r5 = 1
            goto L37
        L2f:
            r6 = 43
            if (r5 != r6) goto L35
            r4 = 1
            goto L36
        L35:
            return r3
        L36:
            r5 = 0
        L37:
            long r10 = (long) r1
            long r12 = r7 / r10
            r14 = 0
            int r2 = r2 - r9
            if (r4 > r2) goto L66
        L3f:
            char r6 = r0.charAt(r4)
            int r6 = kotlin.text.CharsKt.digitOf(r6, r1)
            if (r6 >= 0) goto L4a
            return r3
        L4a:
            int r9 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r9 >= 0) goto L4f
            return r3
        L4f:
            long r14 = r14 * r10
            r16 = r4
            long r3 = (long) r6
            long r17 = r7 + r3
            int r6 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1))
            if (r6 >= 0) goto L5c
            r6 = 0
            return r6
        L5c:
            r6 = 0
            long r14 = r14 - r3
            r4 = r16
            if (r4 == r2) goto L66
            int r4 = r4 + 1
            r3 = r6
            goto L3f
        L66:
            if (r5 == 0) goto L6d
            java.lang.Long r0 = java.lang.Long.valueOf(r14)
            goto L72
        L6d:
            long r0 = -r14
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
        L72:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringNumberConversionsKt.toLongOrNull(java.lang.String, int):java.lang.Long");
    }
}
