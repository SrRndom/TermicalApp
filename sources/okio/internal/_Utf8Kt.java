package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;

/* compiled from: -Utf8.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0001¨\u0006\u0004"}, d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "jvm"}, k = 2, mv = {1, 1, 11})
/* loaded from: classes.dex */
public final class _Utf8Kt {
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0105, code lost:
    
        if (((r16[r5] & 192) == 128) == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x008d, code lost:
    
        if (((r16[r5] & 192) == 128) == false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String commonToUtf8String(byte[] r16) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal._Utf8Kt.commonToUtf8String(byte[]):java.lang.String");
    }

    public static final byte[] commonAsUtf8ToByteArray(String receiver) {
        int i;
        int i2;
        char charAt;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        byte[] bArr = new byte[receiver.length() * 4];
        int length = receiver.length();
        int i3 = 0;
        while (i3 < length) {
            char charAt2 = receiver.charAt(i3);
            if (charAt2 >= 128) {
                int length2 = receiver.length();
                int i4 = i3;
                while (i3 < length2) {
                    char charAt3 = receiver.charAt(i3);
                    if (charAt3 < 128) {
                        int i5 = i4 + 1;
                        bArr[i4] = (byte) charAt3;
                        i3++;
                        while (i3 < length2 && receiver.charAt(i3) < 128) {
                            bArr[i5] = (byte) receiver.charAt(i3);
                            i3++;
                            i5++;
                        }
                        i4 = i5;
                    } else {
                        if (charAt3 < 2048) {
                            int i6 = i4 + 1;
                            bArr[i4] = (byte) ((charAt3 >> 6) | 192);
                            byte b = (byte) ((charAt3 & '?') | 128);
                            i = i6 + 1;
                            bArr[i6] = b;
                        } else if (55296 > charAt3 || 57343 < charAt3) {
                            int i7 = i4 + 1;
                            bArr[i4] = (byte) ((charAt3 >> '\f') | 224);
                            int i8 = i7 + 1;
                            bArr[i7] = (byte) (((charAt3 >> 6) & 63) | 128);
                            byte b2 = (byte) ((charAt3 & '?') | 128);
                            i = i8 + 1;
                            bArr[i8] = b2;
                        } else if (charAt3 > 56319 || length2 <= (i2 = i3 + 1) || 56320 > (charAt = receiver.charAt(i2)) || 57343 < charAt) {
                            i = i4 + 1;
                            bArr[i4] = Utf8.REPLACEMENT_BYTE;
                        } else {
                            int charAt4 = ((charAt3 << '\n') + receiver.charAt(i2)) - 56613888;
                            int i9 = i4 + 1;
                            bArr[i4] = (byte) ((charAt4 >> 18) | 240);
                            int i10 = i9 + 1;
                            bArr[i9] = (byte) (((charAt4 >> 12) & 63) | 128);
                            int i11 = i10 + 1;
                            bArr[i10] = (byte) (((charAt4 >> 6) & 63) | 128);
                            byte b3 = (byte) ((charAt4 & 63) | 128);
                            i = i11 + 1;
                            bArr[i11] = b3;
                            i3 += 2;
                            i4 = i;
                        }
                        i3++;
                        i4 = i;
                    }
                }
                byte[] copyOf = Arrays.copyOf(bArr, i4);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                return copyOf;
            }
            bArr[i3] = (byte) charAt2;
            i3++;
        }
        byte[] copyOf2 = Arrays.copyOf(bArr, receiver.length());
        Intrinsics.checkExpressionValueIsNotNull(copyOf2, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf2;
    }
}
