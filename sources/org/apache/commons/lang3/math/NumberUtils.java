package org.apache.commons.lang3.math;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes.dex */
public class NumberUtils {
    public static final Long LONG_ZERO = 0L;
    public static final Long LONG_ONE = 1L;
    public static final Long LONG_MINUS_ONE = -1L;
    public static final Integer INTEGER_ZERO = 0;
    public static final Integer INTEGER_ONE = 1;
    public static final Integer INTEGER_MINUS_ONE = -1;
    public static final Short SHORT_ZERO = 0;
    public static final Short SHORT_ONE = 1;
    public static final Short SHORT_MINUS_ONE = -1;
    public static final Byte BYTE_ZERO = (byte) 0;
    public static final Byte BYTE_ONE = (byte) 1;
    public static final Byte BYTE_MINUS_ONE = (byte) -1;
    public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);

    public static int compare(byte b, byte b2) {
        return b - b2;
    }

    public static int compare(int i, int i2) {
        if (i == i2) {
            return 0;
        }
        return i < i2 ? -1 : 1;
    }

    public static int compare(long j, long j2) {
        if (j == j2) {
            return 0;
        }
        return j < j2 ? -1 : 1;
    }

    public static int compare(short s, short s2) {
        if (s == s2) {
            return 0;
        }
        return s < s2 ? -1 : 1;
    }

    public static byte max(byte b, byte b2, byte b3) {
        if (b2 > b) {
            b = b2;
        }
        return b3 > b ? b3 : b;
    }

    public static int max(int i, int i2, int i3) {
        if (i2 > i) {
            i = i2;
        }
        return i3 > i ? i3 : i;
    }

    public static long max(long j, long j2, long j3) {
        if (j2 > j) {
            j = j2;
        }
        return j3 > j ? j3 : j;
    }

    public static short max(short s, short s2, short s3) {
        if (s2 > s) {
            s = s2;
        }
        return s3 > s ? s3 : s;
    }

    public static byte min(byte b, byte b2, byte b3) {
        if (b2 < b) {
            b = b2;
        }
        return b3 < b ? b3 : b;
    }

    public static int min(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    public static long min(long j, long j2, long j3) {
        if (j2 < j) {
            j = j2;
        }
        return j3 < j ? j3 : j;
    }

    public static short min(short s, short s2, short s3) {
        if (s2 < s) {
            s = s2;
        }
        return s3 < s ? s3 : s;
    }

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static int toInt(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static long toLong(String str) {
        return toLong(str, 0L);
    }

    public static long toLong(String str, long j) {
        if (str == null) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public static float toFloat(String str) {
        return toFloat(str, 0.0f);
    }

    public static float toFloat(String str, float f) {
        if (str == null) {
            return f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    public static double toDouble(String str) {
        return toDouble(str, 0.0d);
    }

    public static double toDouble(String str, double d) {
        if (str == null) {
            return d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static byte toByte(String str) {
        return toByte(str, (byte) 0);
    }

    public static byte toByte(String str, byte b) {
        if (str == null) {
            return b;
        }
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException unused) {
            return b;
        }
    }

    public static short toShort(String str) {
        return toShort(str, (short) 0);
    }

    public static short toShort(String str, short s) {
        if (str == null) {
            return s;
        }
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException unused) {
            return s;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x0136, code lost:
    
        if (r1 == 'l') goto L82;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Number createNumber(java.lang.String r14) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.createNumber(java.lang.String):java.lang.Number");
    }

    private static String getMantissa(String str) {
        return getMantissa(str, str.length());
    }

    private static String getMantissa(String str, int i) {
        char charAt = str.charAt(0);
        return charAt == '-' || charAt == '+' ? str.substring(1, i) : str.substring(0, i);
    }

    private static boolean isAllZeros(String str) {
        if (str == null) {
            return true;
        }
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) != '0') {
                return false;
            }
        }
        return str.length() > 0;
    }

    public static Float createFloat(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    public static Double createDouble(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    public static Integer createInteger(String str) {
        if (str == null) {
            return null;
        }
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.decode(str);
    }

    public static BigInteger createBigInteger(String str) {
        int i;
        if (str == null) {
            return null;
        }
        boolean startsWith = str.startsWith("-");
        int i2 = 16;
        if (str.startsWith("0x", startsWith ? 1 : 0) || str.startsWith("0X", startsWith ? 1 : 0)) {
            i = (startsWith ? 1 : 0) + 2;
        } else if (str.startsWith("#", startsWith ? 1 : 0)) {
            i = (startsWith ? 1 : 0) + 1;
        } else {
            if (str.startsWith("0", startsWith ? 1 : 0)) {
                int length = str.length();
                int i3 = (startsWith ? 1 : 0) + 1;
                if (length > i3) {
                    i = i3;
                    i2 = 8;
                }
            }
            i = startsWith ? 1 : 0;
            i2 = 10;
        }
        BigInteger bigInteger = new BigInteger(str.substring(i), i2);
        return startsWith ? bigInteger.negate() : bigInteger;
    }

    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        if (str.trim().startsWith("--")) {
            throw new NumberFormatException(str + " is not a valid number.");
        }
        return new BigDecimal(str);
    }

    public static long min(long... jArr) {
        validateArray(jArr);
        long j = jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            if (jArr[i] < j) {
                j = jArr[i];
            }
        }
        return j;
    }

    public static int min(int... iArr) {
        validateArray(iArr);
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (iArr[i2] < i) {
                i = iArr[i2];
            }
        }
        return i;
    }

    public static short min(short... sArr) {
        validateArray(sArr);
        short s = sArr[0];
        for (int i = 1; i < sArr.length; i++) {
            if (sArr[i] < s) {
                s = sArr[i];
            }
        }
        return s;
    }

    public static byte min(byte... bArr) {
        validateArray(bArr);
        byte b = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            if (bArr[i] < b) {
                b = bArr[i];
            }
        }
        return b;
    }

    public static double min(double... dArr) {
        validateArray(dArr);
        double d = dArr[0];
        for (int i = 1; i < dArr.length; i++) {
            if (Double.isNaN(dArr[i])) {
                return Double.NaN;
            }
            if (dArr[i] < d) {
                d = dArr[i];
            }
        }
        return d;
    }

    public static float min(float... fArr) {
        validateArray(fArr);
        float f = fArr[0];
        for (int i = 1; i < fArr.length; i++) {
            if (Float.isNaN(fArr[i])) {
                return Float.NaN;
            }
            if (fArr[i] < f) {
                f = fArr[i];
            }
        }
        return f;
    }

    public static long max(long... jArr) {
        validateArray(jArr);
        long j = jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            if (jArr[i] > j) {
                j = jArr[i];
            }
        }
        return j;
    }

    public static int max(int... iArr) {
        validateArray(iArr);
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (iArr[i2] > i) {
                i = iArr[i2];
            }
        }
        return i;
    }

    public static short max(short... sArr) {
        validateArray(sArr);
        short s = sArr[0];
        for (int i = 1; i < sArr.length; i++) {
            if (sArr[i] > s) {
                s = sArr[i];
            }
        }
        return s;
    }

    public static byte max(byte... bArr) {
        validateArray(bArr);
        byte b = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            if (bArr[i] > b) {
                b = bArr[i];
            }
        }
        return b;
    }

    public static double max(double... dArr) {
        validateArray(dArr);
        double d = dArr[0];
        for (int i = 1; i < dArr.length; i++) {
            if (Double.isNaN(dArr[i])) {
                return Double.NaN;
            }
            if (dArr[i] > d) {
                d = dArr[i];
            }
        }
        return d;
    }

    public static float max(float... fArr) {
        validateArray(fArr);
        float f = fArr[0];
        for (int i = 1; i < fArr.length; i++) {
            if (Float.isNaN(fArr[i])) {
                return Float.NaN;
            }
            if (fArr[i] > f) {
                f = fArr[i];
            }
        }
        return f;
    }

    private static void validateArray(Object obj) {
        Validate.isTrue(obj != null, "The Array must not be null", new Object[0]);
        Validate.isTrue(Array.getLength(obj) != 0, "Array cannot be empty.", new Object[0]);
    }

    public static double min(double d, double d2, double d3) {
        return Math.min(Math.min(d, d2), d3);
    }

    public static float min(float f, float f2, float f3) {
        return Math.min(Math.min(f, f2), f3);
    }

    public static double max(double d, double d2, double d3) {
        return Math.max(Math.max(d, d2), d3);
    }

    public static float max(float f, float f2, float f3) {
        return Math.max(Math.max(f, f2), f3);
    }

    public static boolean isDigits(String str) {
        return StringUtils.isNumeric(str);
    }

    @Deprecated
    public static boolean isNumber(String str) {
        return isCreatable(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x00e4, code lost:
    
        if (r0[r3] != 'F') goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x00e6, code lost:
    
        return r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x00eb, code lost:
    
        if (r0[r3] == 'l') goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x00f1, code lost:
    
        if (r0[r3] != 'L') goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x00f4, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x00f5, code lost:
    
        if (r13 == false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x00f7, code lost:
    
        if (r14 != false) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x00f9, code lost:
    
        if (r15 != false) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x00fb, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x00fd, code lost:
    
        if (r8 != false) goto L177;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x00ff, code lost:
    
        if (r13 == false) goto L178;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0101, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0123, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x013f, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00a4, code lost:
    
        if (r3 >= r0.length) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00a8, code lost:
    
        if (r0[r3] < '0') goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00ac, code lost:
    
        if (r0[r3] > '9') goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00b0, code lost:
    
        if (org.apache.commons.lang3.SystemUtils.IS_JAVA_1_6 == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00b2, code lost:
    
        if (r7 == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00b4, code lost:
    
        if (r15 != false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00b6, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00b7, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00ba, code lost:
    
        if (r0[r3] == 'e') goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00be, code lost:
    
        if (r0[r3] != 'E') goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00c3, code lost:
    
        if (r0[r3] != '.') goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00c5, code lost:
    
        if (r15 != false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00c7, code lost:
    
        if (r14 == false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00ca, code lost:
    
        return r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00cb, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x00cc, code lost:
    
        if (r8 != false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x00d2, code lost:
    
        if (r0[r3] == 'd') goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x00d8, code lost:
    
        if (r0[r3] == 'D') goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00de, code lost:
    
        if (r0[r3] == 'f') goto L100;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isCreatable(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.isCreatable(java.lang.String):boolean");
    }

    public static boolean isParsable(String str) {
        if (StringUtils.isEmpty(str) || str.charAt(str.length() - 1) == '.') {
            return false;
        }
        if (str.charAt(0) == '-') {
            if (str.length() == 1) {
                return false;
            }
            return withDecimalsParsing(str, 1);
        }
        return withDecimalsParsing(str, 0);
    }

    private static boolean withDecimalsParsing(String str, int i) {
        int i2 = 0;
        while (i < str.length()) {
            boolean z = str.charAt(i) == '.';
            if (z) {
                i2++;
            }
            if (i2 > 1) {
                return false;
            }
            if (!z && !Character.isDigit(str.charAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }
}
