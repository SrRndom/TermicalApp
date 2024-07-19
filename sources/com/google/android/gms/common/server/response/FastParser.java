package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaf = {'u', 'l', 'l'};
    private static final char[] zag = {'r', 'u', 'e'};
    private static final char[] zah = {'r', 'u', 'e', Typography.quote};
    private static final char[] zai = {'a', 'l', 's', 'e'};
    private static final char[] zaj = {'a', 'l', 's', 'e', Typography.quote};
    private static final char[] zak = {'\n'};
    private static final zaa<Integer> zam = new com.google.android.gms.common.server.response.zaa();
    private static final zaa<Long> zan = new zac();
    private static final zaa<Float> zao = new zab();
    private static final zaa<Double> zap = new zae();
    private static final zaa<Boolean> zaq = new zad();
    private static final zaa<String> zar = new zag();
    private static final zaa<BigInteger> zas = new zaf();
    private static final zaa<BigDecimal> zat = new zah();
    private final char[] zaa = new char[1];
    private final char[] zab = new char[32];
    private final char[] zac = new char[1024];
    private final StringBuilder zad = new StringBuilder(32);
    private final StringBuilder zae = new StringBuilder(1024);
    private final Stack<Integer> zal = new Stack<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
    /* loaded from: classes.dex */
    public interface zaa<O> {
        O zaa(FastParser fastParser, BufferedReader bufferedReader) throws ParseException, IOException;
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
    /* loaded from: classes.dex */
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                this.zal.push(0);
                char zaj2 = zaj(bufferedReader);
                if (zaj2 == 0) {
                    throw new ParseException("No data to parse");
                }
                if (zaj2 == '[') {
                    this.zal.push(5);
                    Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                    if (fieldMappings.size() != 1) {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                    FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                    t.addConcreteTypeArrayInternal(value, value.zae, zaa(bufferedReader, value));
                } else if (zaj2 == '{') {
                    this.zal.push(1);
                    zaa(bufferedReader, t);
                } else {
                    StringBuilder sb = new StringBuilder(19);
                    sb.append("Unexpected token: ");
                    sb.append(zaj2);
                    throw new ParseException(sb.toString());
                }
                zaa(0);
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                    Log.w("FastParser", "Failed to close reader while parsing.");
                }
            } catch (IOException e) {
                throw new ParseException(e);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x0040. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x029b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x027d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean zaa(java.io.BufferedReader r17, com.google.android.gms.common.server.response.FastJsonResponse r18) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 706
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.zaa(java.io.BufferedReader, com.google.android.gms.common.server.response.FastJsonResponse):boolean");
    }

    private final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zal.push(2);
        char zaj2 = zaj(bufferedReader);
        if (zaj2 == '\"') {
            this.zal.push(3);
            String zab = zab(bufferedReader, this.zab, this.zad, null);
            zaa(3);
            if (zaj(bufferedReader) == ':') {
                return zab;
            }
            throw new ParseException("Expected key/value separator");
        }
        if (zaj2 == ']') {
            zaa(2);
            zaa(1);
            zaa(5);
            return null;
        }
        if (zaj2 == '}') {
            zaa(2);
            return null;
        }
        StringBuilder sb = new StringBuilder(19);
        sb.append("Unexpected token: ");
        sb.append(zaj2);
        throw new ParseException(sb.toString());
    }

    private final String zab(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        char zaj2 = zaj(bufferedReader);
        if (zaj2 == '\"') {
            if (bufferedReader.read(this.zaa) == -1) {
                throw new ParseException("Unexpected EOF while parsing string");
            }
            char c = this.zaa[0];
            boolean z = false;
            do {
                if (c != '\"' || z) {
                    z = c == '\\' ? !z : false;
                    if (bufferedReader.read(this.zaa) == -1) {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                    c = this.zaa[0];
                }
            } while (!Character.isISOControl(c));
            throw new ParseException("Unexpected control character while reading string");
        }
        if (zaj2 == ',') {
            throw new ParseException("Missing value");
        }
        int i = 1;
        if (zaj2 == '[') {
            this.zal.push(5);
            bufferedReader.mark(32);
            if (zaj(bufferedReader) == ']') {
                zaa(5);
            } else {
                bufferedReader.reset();
                boolean z2 = false;
                boolean z3 = false;
                while (i > 0) {
                    char zaj3 = zaj(bufferedReader);
                    if (zaj3 == 0) {
                        throw new ParseException("Unexpected EOF while parsing array");
                    }
                    if (Character.isISOControl(zaj3)) {
                        throw new ParseException("Unexpected control character while reading array");
                    }
                    if (zaj3 == '\"' && !z2) {
                        z3 = !z3;
                    }
                    if (zaj3 == '[' && !z3) {
                        i++;
                    }
                    if (zaj3 == ']' && !z3) {
                        i--;
                    }
                    z2 = (zaj3 == '\\' && z3) ? !z2 : false;
                }
                zaa(5);
            }
        } else if (zaj2 == '{') {
            this.zal.push(1);
            bufferedReader.mark(32);
            char zaj4 = zaj(bufferedReader);
            if (zaj4 == '}') {
                zaa(1);
            } else if (zaj4 == '\"') {
                bufferedReader.reset();
                zaa(bufferedReader);
                do {
                } while (zab(bufferedReader) != null);
                zaa(1);
            } else {
                StringBuilder sb = new StringBuilder(18);
                sb.append("Unexpected token ");
                sb.append(zaj4);
                throw new ParseException(sb.toString());
            }
        } else {
            bufferedReader.reset();
            zaa(bufferedReader, this.zac);
        }
        char zaj5 = zaj(bufferedReader);
        if (zaj5 == ',') {
            zaa(2);
            return zaa(bufferedReader);
        }
        if (zaj5 == '}') {
            zaa(2);
            return null;
        }
        StringBuilder sb2 = new StringBuilder(18);
        sb2.append("Unexpected token ");
        sb2.append(zaj5);
        throw new ParseException(sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zac(BufferedReader bufferedReader) throws ParseException, IOException {
        return zaa(bufferedReader, this.zab, this.zad, null);
    }

    private final <O> ArrayList<O> zaa(BufferedReader bufferedReader, zaa<O> zaaVar) throws ParseException, IOException {
        char zaj2 = zaj(bufferedReader);
        if (zaj2 == 'n') {
            zab(bufferedReader, zaf);
            return null;
        }
        if (zaj2 != '[') {
            throw new ParseException("Expected start of array");
        }
        this.zal.push(5);
        ArrayList<O> arrayList = new ArrayList<>();
        while (true) {
            bufferedReader.mark(1024);
            char zaj3 = zaj(bufferedReader);
            if (zaj3 == 0) {
                throw new ParseException("Unexpected EOF");
            }
            if (zaj3 != ',') {
                if (zaj3 == ']') {
                    zaa(5);
                    return arrayList;
                }
                bufferedReader.reset();
                arrayList.add(zaaVar.zaa(this, bufferedReader));
            }
        }
    }

    private final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char zaj2 = zaj(bufferedReader);
        if (zaj2 == '\"') {
            return zab(bufferedReader, cArr, sb, cArr2);
        }
        if (zaj2 == 'n') {
            zab(bufferedReader, zaf);
            return null;
        }
        throw new ParseException("Expected string");
    }

    private static String zab(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        boolean z;
        sb.setLength(0);
        bufferedReader.mark(cArr.length);
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                for (int i = 0; i < read; i++) {
                    char c = cArr[i];
                    if (Character.isISOControl(c)) {
                        if (cArr2 != null) {
                            for (char c2 : cArr2) {
                                if (c2 == c) {
                                    z = true;
                                    break;
                                }
                            }
                        }
                        z = false;
                        if (!z) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                    }
                    if (c == '\"' && !z2) {
                        sb.append(cArr, 0, i);
                        bufferedReader.reset();
                        bufferedReader.skip(i + 1);
                        if (z3) {
                            return JsonUtils.unescapeString(sb.toString());
                        }
                        return sb.toString();
                    }
                    if (c == '\\') {
                        z2 = !z2;
                        z3 = true;
                    } else {
                        z2 = false;
                    }
                }
                sb.append(cArr, 0, read);
                bufferedReader.mark(cArr.length);
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zad(BufferedReader bufferedReader) throws ParseException, IOException {
        int i;
        int i2;
        boolean z;
        int zaa2 = zaa(bufferedReader, this.zac);
        int i3 = 0;
        if (zaa2 == 0) {
            return 0;
        }
        char[] cArr = this.zac;
        if (zaa2 > 0) {
            if (cArr[0] == '-') {
                i = Integer.MIN_VALUE;
                i2 = 1;
                z = true;
            } else {
                i = -2147483647;
                i2 = 0;
                z = false;
            }
            if (i2 < zaa2) {
                int i4 = i2 + 1;
                int digit = Character.digit(cArr[i2], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                int i5 = -digit;
                i2 = i4;
                i3 = i5;
            }
            while (i2 < zaa2) {
                int i6 = i2 + 1;
                int digit2 = Character.digit(cArr[i2], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (i3 < -214748364) {
                    throw new ParseException("Number too large");
                }
                int i7 = i3 * 10;
                if (i7 < i + digit2) {
                    throw new ParseException("Number too large");
                }
                i3 = i7 - digit2;
                i2 = i6;
            }
            if (!z) {
                return -i3;
            }
            if (i2 > 1) {
                return i3;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zae(BufferedReader bufferedReader) throws ParseException, IOException {
        long j;
        boolean z;
        int zaa2 = zaa(bufferedReader, this.zac);
        long j2 = 0;
        if (zaa2 == 0) {
            return 0L;
        }
        char[] cArr = this.zac;
        if (zaa2 > 0) {
            int i = 0;
            if (cArr[0] == '-') {
                j = Long.MIN_VALUE;
                i = 1;
                z = true;
            } else {
                j = -9223372036854775807L;
                z = false;
            }
            if (i < zaa2) {
                int i2 = i + 1;
                int digit = Character.digit(cArr[i], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                i = i2;
                j2 = -digit;
            }
            while (i < zaa2) {
                int i3 = i + 1;
                int digit2 = Character.digit(cArr[i], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (j2 < -922337203685477580L) {
                    throw new ParseException("Number too large");
                }
                long j3 = j2 * 10;
                long j4 = digit2;
                if (j3 < j + j4) {
                    throw new ParseException("Number too large");
                }
                j2 = j3 - j4;
                i = i3;
            }
            if (!z) {
                return -j2;
            }
            if (i > 1) {
                return j2;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zac);
        if (zaa2 == 0) {
            return null;
        }
        return new BigInteger(new String(this.zac, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        while (true) {
            char zaj2 = zaj(bufferedReader);
            if (zaj2 != '\"') {
                if (zaj2 == 'f') {
                    zab(bufferedReader, z ? zaj : zai);
                    return false;
                }
                if (zaj2 == 'n') {
                    zab(bufferedReader, zaf);
                    return false;
                }
                if (zaj2 == 't') {
                    zab(bufferedReader, z ? zah : zag);
                    return true;
                }
                StringBuilder sb = new StringBuilder(19);
                sb.append("Unexpected token: ");
                sb.append(zaj2);
                throw new ParseException(sb.toString());
            }
            if (z) {
                throw new ParseException("No boolean value found in string");
            }
            z = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zag(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zac);
        if (zaa2 == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zac, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zah(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zac);
        if (zaa2 == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zac, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zai(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zac);
        if (zaa2 == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zac, 0, zaa2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T extends FastJsonResponse> ArrayList<T> zaa(BufferedReader bufferedReader, FastJsonResponse.Field<?, ?> field) throws ParseException, IOException {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        char zaj2 = zaj(bufferedReader);
        if (zaj2 == ']') {
            zaa(5);
            return arrayList;
        }
        if (zaj2 == 'n') {
            zab(bufferedReader, zaf);
            zaa(5);
            return null;
        }
        if (zaj2 == '{') {
            this.zal.push(1);
            while (true) {
                try {
                    FastJsonResponse zac = field.zac();
                    if (!zaa(bufferedReader, zac)) {
                        return arrayList;
                    }
                    arrayList.add(zac);
                    char zaj3 = zaj(bufferedReader);
                    if (zaj3 != ',') {
                        if (zaj3 == ']') {
                            zaa(5);
                            return arrayList;
                        }
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(zaj3);
                        throw new ParseException(sb.toString());
                    }
                    if (zaj(bufferedReader) != '{') {
                        throw new ParseException("Expected start of next object in array");
                    }
                    this.zal.push(1);
                } catch (IllegalAccessException e) {
                    throw new ParseException("Error instantiating inner object", e);
                } catch (InstantiationException e2) {
                    throw new ParseException("Error instantiating inner object", e2);
                }
            }
        } else {
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append("Unexpected token: ");
            sb2.append(zaj2);
            throw new ParseException(sb2.toString());
        }
    }

    private final char zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zaa) == -1) {
            return (char) 0;
        }
        while (Character.isWhitespace(this.zaa[0])) {
            if (bufferedReader.read(this.zaa) == -1) {
                return (char) 0;
            }
        }
        return this.zaa[0];
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        char zaj2 = zaj(bufferedReader);
        if (zaj2 == 0) {
            throw new ParseException("Unexpected EOF");
        }
        if (zaj2 == ',') {
            throw new ParseException("Missing value");
        }
        if (zaj2 == 'n') {
            zab(bufferedReader, zaf);
            return 0;
        }
        bufferedReader.mark(1024);
        if (zaj2 == '\"') {
            i = 0;
            boolean z = false;
            while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                char c = cArr[i];
                if (Character.isISOControl(c)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                if (c == '\"' && !z) {
                    bufferedReader.reset();
                    bufferedReader.skip(i + 1);
                    return i;
                }
                z = c == '\\' ? !z : false;
                i++;
            }
        } else {
            cArr[0] = zaj2;
            i = 1;
            while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                if (cArr[i] == '}' || cArr[i] == ',' || Character.isWhitespace(cArr[i]) || cArr[i] == ']') {
                    bufferedReader.reset();
                    bufferedReader.skip(i - 1);
                    cArr[i] = 0;
                    return i;
                }
                i++;
            }
        }
        if (i == cArr.length) {
            throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (i < cArr.length) {
            int read = bufferedReader.read(this.zab, 0, cArr.length - i);
            if (read == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i2 = 0; i2 < read; i2++) {
                if (cArr[i2 + i] != this.zab[i2]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += read;
        }
    }

    private final void zaa(int i) throws ParseException {
        if (this.zal.isEmpty()) {
            StringBuilder sb = new StringBuilder(46);
            sb.append("Expected state ");
            sb.append(i);
            sb.append(" but had empty stack");
            throw new ParseException(sb.toString());
        }
        int intValue = this.zal.pop().intValue();
        if (intValue == i) {
            return;
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append("Expected state ");
        sb2.append(i);
        sb2.append(" but had ");
        sb2.append(intValue);
        throw new ParseException(sb2.toString());
    }
}
