package kotlin.text;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.sequences.SequencesKt;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: StringsJVM.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000x\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u0011\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0011\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\u0019\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0087\b\u001a!\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0087\b\u001a)\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0087\b\u001a\u0011\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\b\u001a!\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0087\b\u001a!\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0087\b\u001a\n\u0010\u0016\u001a\u00020\u0002*\u00020\u0002\u001a\u0015\u0010\u0017\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010\u0019\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0010H\u0087\b\u001a\u001d\u0010\u001a\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010H\u0087\b\u001a\u001c\u0010\u001d\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a\u0015\u0010!\u001a\u00020 *\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0087\b\u001a\u0015\u0010!\u001a\u00020 *\u00020\u00022\u0006\u0010\"\u001a\u00020#H\u0087\b\u001a\n\u0010$\u001a\u00020\u0002*\u00020\u0002\u001a\u001c\u0010%\u001a\u00020 *\u00020\u00022\u0006\u0010&\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a \u0010'\u001a\u00020 *\u0004\u0018\u00010\u00022\b\u0010\u001e\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a2\u0010(\u001a\u00020\u0002*\u00020\u00022\u0006\u0010)\u001a\u00020*2\u0016\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010-0,\"\u0004\u0018\u00010-H\u0087\b¢\u0006\u0002\u0010.\u001a*\u0010(\u001a\u00020\u0002*\u00020\u00022\u0016\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010-0,\"\u0004\u0018\u00010-H\u0087\b¢\u0006\u0002\u0010/\u001a:\u0010(\u001a\u00020\u0002*\u00020\u00032\u0006\u0010)\u001a\u00020*2\u0006\u0010(\u001a\u00020\u00022\u0016\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010-0,\"\u0004\u0018\u00010-H\u0087\b¢\u0006\u0002\u00100\u001a2\u0010(\u001a\u00020\u0002*\u00020\u00032\u0006\u0010(\u001a\u00020\u00022\u0016\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010-0,\"\u0004\u0018\u00010-H\u0087\b¢\u0006\u0002\u00101\u001a\r\u00102\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\n\u00103\u001a\u00020 *\u00020#\u001a\u001d\u00104\u001a\u00020\u0010*\u00020\u00022\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0010H\u0081\b\u001a\u001d\u00104\u001a\u00020\u0010*\u00020\u00022\u0006\u00108\u001a\u00020\u00022\u0006\u00107\u001a\u00020\u0010H\u0081\b\u001a\u001d\u00109\u001a\u00020\u0010*\u00020\u00022\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0010H\u0081\b\u001a\u001d\u00109\u001a\u00020\u0010*\u00020\u00022\u0006\u00108\u001a\u00020\u00022\u0006\u00107\u001a\u00020\u0010H\u0081\b\u001a\u001d\u0010:\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u0010H\u0087\b\u001a4\u0010<\u001a\u00020 *\u00020#2\u0006\u0010=\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020#2\u0006\u0010>\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020 \u001a4\u0010<\u001a\u00020 *\u00020\u00022\u0006\u0010=\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020 \u001a\u0012\u0010?\u001a\u00020\u0002*\u00020#2\u0006\u0010@\u001a\u00020\u0010\u001a$\u0010A\u001a\u00020\u0002*\u00020\u00022\u0006\u0010B\u001a\u0002062\u0006\u0010C\u001a\u0002062\b\b\u0002\u0010\u001f\u001a\u00020 \u001a$\u0010A\u001a\u00020\u0002*\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\u0006\u0010E\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a$\u0010F\u001a\u00020\u0002*\u00020\u00022\u0006\u0010B\u001a\u0002062\u0006\u0010C\u001a\u0002062\b\b\u0002\u0010\u001f\u001a\u00020 \u001a$\u0010F\u001a\u00020\u0002*\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\u0006\u0010E\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a\"\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00020H*\u00020#2\u0006\u0010I\u001a\u00020J2\b\b\u0002\u0010K\u001a\u00020\u0010\u001a\u001c\u0010L\u001a\u00020 *\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a$\u0010L\u001a\u00020 *\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\u0006\u0010N\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020 \u001a\u0015\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010N\u001a\u00020\u0010H\u0087\b\u001a\u001d\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010N\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010H\u0087\b\u001a\u0017\u0010P\u001a\u00020\f*\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\b\u001a\r\u0010Q\u001a\u00020\u0013*\u00020\u0002H\u0087\b\u001a3\u0010Q\u001a\u00020\u0013*\u00020\u00022\u0006\u0010R\u001a\u00020\u00132\b\b\u0002\u0010S\u001a\u00020\u00102\b\b\u0002\u0010N\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u0010H\u0087\b\u001a\r\u0010T\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010T\u001a\u00020\u0002*\u00020\u00022\u0006\u0010)\u001a\u00020*H\u0087\b\u001a\u0017\u0010U\u001a\u00020J*\u00020\u00022\b\b\u0002\u0010V\u001a\u00020\u0010H\u0087\b\u001a\r\u0010W\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010W\u001a\u00020\u0002*\u00020\u00022\u0006\u0010)\u001a\u00020*H\u0087\b\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006X"}, d2 = {"CASE_INSENSITIVE_ORDER", "Ljava/util/Comparator;", "", "Lkotlin/String$Companion;", "getCASE_INSENSITIVE_ORDER", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/util/Comparator;", "String", "stringBuffer", "Ljava/lang/StringBuffer;", "stringBuilder", "Ljava/lang/StringBuilder;", "bytes", "", "charset", "Ljava/nio/charset/Charset;", "offset", "", "length", "chars", "", "codePoints", "", "capitalize", "codePointAt", "index", "codePointBefore", "codePointCount", "beginIndex", "endIndex", "compareTo", "other", "ignoreCase", "", "contentEquals", "charSequence", "", "decapitalize", "endsWith", "suffix", "equals", "format", "locale", "Ljava/util/Locale;", "args", "", "", "(Ljava/lang/String;Ljava/util/Locale;[Ljava/lang/Object;)Ljava/lang/String;", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "intern", "isBlank", "nativeIndexOf", "ch", "", "fromIndex", "str", "nativeLastIndexOf", "offsetByCodePoints", "codePointOffset", "regionMatches", "thisOffset", "otherOffset", "repeat", "n", "replace", "oldChar", "newChar", "oldValue", "newValue", "replaceFirst", "split", "", "regex", "Ljava/util/regex/Pattern;", "limit", "startsWith", "prefix", "startIndex", "substring", "toByteArray", "toCharArray", "destination", "destinationOffset", "toLowerCase", "toPattern", "flags", "toUpperCase", "kotlin-stdlib"}, k = 5, mv = {1, 1, 10}, xi = 1, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
public class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsKt {
    private static final int nativeIndexOf(String str, char c, int i) {
        if (str != null) {
            return str.indexOf(c, i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private static final int nativeIndexOf(String str, String str2, int i) {
        if (str != null) {
            return str.indexOf(str2, i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private static final int nativeLastIndexOf(String str, char c, int i) {
        if (str != null) {
            return str.lastIndexOf(c, i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private static final int nativeLastIndexOf(String str, String str2, int i) {
        if (str != null) {
            return str.lastIndexOf(str2, i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public static final boolean equals(String str, String str2, boolean z) {
        if (str == null) {
            return str2 == null;
        }
        if (!z) {
            return str.equals(str2);
        }
        return str.equalsIgnoreCase(str2);
    }

    public static final String replace(String receiver, char c, char c2, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!z) {
            String replace = receiver.replace(c, c2);
            Intrinsics.checkExpressionValueIsNotNull(replace, "(this as java.lang.Strin…replace(oldChar, newChar)");
            return replace;
        }
        return SequencesKt.joinToString$default(StringsKt.splitToSequence$default(receiver, new char[]{c}, z, 0, 4, (Object) null), String.valueOf(c2), null, null, 0, null, null, 62, null);
    }

    public static final String replace(String receiver, String oldValue, String newValue, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(oldValue, "oldValue");
        Intrinsics.checkParameterIsNotNull(newValue, "newValue");
        return SequencesKt.joinToString$default(StringsKt.splitToSequence$default(receiver, new String[]{oldValue}, z, 0, 4, (Object) null), newValue, null, null, 0, null, null, 62, null);
    }

    public static final String replaceFirst(String receiver, char c, char c2, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        String str = receiver;
        int indexOf$default = StringsKt.indexOf$default(str, c, 0, z, 2, (Object) null);
        return indexOf$default < 0 ? receiver : StringsKt.replaceRange((CharSequence) str, indexOf$default, indexOf$default + 1, (CharSequence) String.valueOf(c2)).toString();
    }

    public static final String replaceFirst(String receiver, String oldValue, String newValue, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(oldValue, "oldValue");
        Intrinsics.checkParameterIsNotNull(newValue, "newValue");
        String str = receiver;
        int indexOf$default = StringsKt.indexOf$default(str, oldValue, 0, z, 2, (Object) null);
        return indexOf$default < 0 ? receiver : StringsKt.replaceRange((CharSequence) str, indexOf$default, oldValue.length() + indexOf$default, (CharSequence) newValue).toString();
    }

    private static final String toUpperCase(String str) {
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = str.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        return upperCase;
    }

    private static final String toLowerCase(String str) {
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = str.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        return lowerCase;
    }

    private static final char[] toCharArray(String str) {
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        char[] charArray = str.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
        return charArray;
    }

    public static /* bridge */ /* synthetic */ String replace$default(String str, char c, char c2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return StringsKt.replace(str, c, c2, z);
    }

    static /* bridge */ /* synthetic */ char[] toCharArray$default(String str, char[] cArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = str.length();
        }
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        str.getChars(i2, i3, cArr, i);
        return cArr;
    }

    private static final char[] toCharArray(String str, char[] cArr, int i, int i2, int i3) {
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        str.getChars(i2, i3, cArr, i);
        return cArr;
    }

    public static /* bridge */ /* synthetic */ String replace$default(String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return StringsKt.replace(str, str2, str3, z);
    }

    public static /* bridge */ /* synthetic */ String replaceFirst$default(String str, char c, char c2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return StringsKt.replaceFirst(str, c, c2, z);
    }

    private static final String format(String str, Object... objArr) {
        String format = String.format(str, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return format;
    }

    public static /* bridge */ /* synthetic */ String replaceFirst$default(String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return StringsKt.replaceFirst(str, str2, str3, z);
    }

    private static final String format(StringCompanionObject stringCompanionObject, String str, Object... objArr) {
        String format = String.format(str, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        return format;
    }

    public static /* bridge */ /* synthetic */ boolean equals$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.equals(str, str2, z);
    }

    private static final String format(String str, Locale locale, Object... objArr) {
        String format = String.format(locale, str, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, this, *args)");
        return format;
    }

    private static final String format(StringCompanionObject stringCompanionObject, Locale locale, String str, Object... objArr) {
        String format = String.format(locale, str, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        return format;
    }

    public static /* bridge */ /* synthetic */ List split$default(CharSequence charSequence, Pattern pattern, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return StringsKt.split(charSequence, pattern, i);
    }

    public static final List<String> split(CharSequence receiver, Pattern regex, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(regex, "regex");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + i + ClassUtils.PACKAGE_SEPARATOR_CHAR).toString());
        }
        if (i == 0) {
            i = -1;
        }
        String[] split = regex.split(receiver, i);
        Intrinsics.checkExpressionValueIsNotNull(split, "regex.split(this, if (limit == 0) -1 else limit)");
        return ArraysKt.asList(split);
    }

    private static final String substring(String str, int i) {
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = str.substring(i);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return substring;
    }

    private static final String substring(String str, int i, int i2) {
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = str.substring(i, i2);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    public static /* bridge */ /* synthetic */ boolean startsWith$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.startsWith(str, str2, z);
    }

    public static final boolean startsWith(String receiver, String prefix, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!z) {
            return receiver.startsWith(prefix);
        }
        return StringsKt.regionMatches(receiver, 0, prefix, 0, prefix.length(), z);
    }

    public static /* bridge */ /* synthetic */ boolean startsWith$default(String str, String str2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.startsWith(str, str2, i, z);
    }

    public static final boolean startsWith(String receiver, String prefix, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!z) {
            return receiver.startsWith(prefix, i);
        }
        return StringsKt.regionMatches(receiver, i, prefix, 0, prefix.length(), z);
    }

    public static /* bridge */ /* synthetic */ boolean endsWith$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.endsWith(str, str2, z);
    }

    public static final boolean endsWith(String receiver, String suffix, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if (!z) {
            return receiver.endsWith(suffix);
        }
        return StringsKt.regionMatches(receiver, receiver.length() - suffix.length(), suffix, 0, suffix.length(), true);
    }

    private static final String String(byte[] bArr, int i, int i2, Charset charset) {
        return new String(bArr, i, i2, charset);
    }

    private static final String String(byte[] bArr, Charset charset) {
        return new String(bArr, charset);
    }

    private static final String String(byte[] bArr, int i, int i2) {
        return new String(bArr, i, i2, Charsets.UTF_8);
    }

    private static final String String(byte[] bArr) {
        return new String(bArr, Charsets.UTF_8);
    }

    private static final String String(char[] cArr) {
        return new String(cArr);
    }

    private static final String String(char[] cArr, int i, int i2) {
        return new String(cArr, i, i2);
    }

    private static final String String(int[] iArr, int i, int i2) {
        return new String(iArr, i, i2);
    }

    private static final String String(StringBuffer stringBuffer) {
        return new String(stringBuffer);
    }

    private static final String String(StringBuilder sb) {
        return new String(sb);
    }

    private static final int codePointAt(String str, int i) {
        if (str != null) {
            return str.codePointAt(i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private static final int codePointBefore(String str, int i) {
        if (str != null) {
            return str.codePointBefore(i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private static final int codePointCount(String str, int i, int i2) {
        if (str != null) {
            return str.codePointCount(i, i2);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public static /* bridge */ /* synthetic */ int compareTo$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.compareTo(str, str2, z);
    }

    public static final int compareTo(String receiver, String other, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (z) {
            return receiver.compareToIgnoreCase(other);
        }
        return receiver.compareTo(other);
    }

    private static final boolean contentEquals(String str, CharSequence charSequence) {
        if (str != null) {
            return str.contentEquals(charSequence);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private static final boolean contentEquals(String str, StringBuffer stringBuffer) {
        if (str != null) {
            return str.contentEquals(stringBuffer);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private static final String intern(String str) {
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String intern = str.intern();
        Intrinsics.checkExpressionValueIsNotNull(intern, "(this as java.lang.String).intern()");
        return intern;
    }

    public static final boolean isBlank(CharSequence receiver) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() != 0) {
            Iterable indices = StringsKt.getIndices(receiver);
            if (!(indices instanceof Collection) || !((Collection) indices).isEmpty()) {
                Iterator it = indices.iterator();
                while (it.hasNext()) {
                    if (!CharsKt.isWhitespace(receiver.charAt(((IntIterator) it).nextInt()))) {
                        z = false;
                        break;
                    }
                }
            }
            z = true;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private static final int offsetByCodePoints(String str, int i, int i2) {
        if (str != null) {
            return str.offsetByCodePoints(i, i2);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public static final boolean regionMatches(CharSequence receiver, int i, CharSequence other, int i2, int i3, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        if ((receiver instanceof String) && (other instanceof String)) {
            return StringsKt.regionMatches((String) receiver, i, (String) other, i2, i3, z);
        }
        return StringsKt.regionMatchesImpl(receiver, i, other, i2, i3, z);
    }

    public static final boolean regionMatches(String receiver, int i, String other, int i2, int i3, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (!z) {
            return receiver.regionMatches(i, other, i2, i3);
        }
        return receiver.regionMatches(z, i, other, i2, i3);
    }

    private static final String toLowerCase(String str, Locale locale) {
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }

    private static final String toUpperCase(String str, Locale locale) {
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = str.toUpperCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        return upperCase;
    }

    private static final byte[] toByteArray(String str, Charset charset) {
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    static /* bridge */ /* synthetic */ byte[] toByteArray$default(String str, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    private static final Pattern toPattern(String str, int i) {
        Pattern compile = Pattern.compile(str, i);
        Intrinsics.checkExpressionValueIsNotNull(compile, "java.util.regex.Pattern.compile(this, flags)");
        return compile;
    }

    public static final String capitalize(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(receiver.length() > 0) || !Character.isLowerCase(receiver.charAt(0))) {
            return receiver;
        }
        StringBuilder sb = new StringBuilder();
        String substring = receiver.substring(0, 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (substring == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = substring.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        sb.append(upperCase);
        String substring2 = receiver.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        sb.append(substring2);
        return sb.toString();
    }

    public static final String decapitalize(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(receiver.length() > 0) || !Character.isUpperCase(receiver.charAt(0))) {
            return receiver;
        }
        StringBuilder sb = new StringBuilder();
        String substring = receiver.substring(0, 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (substring == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = substring.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        sb.append(lowerCase);
        String substring2 = receiver.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        sb.append(substring2);
        return sb.toString();
    }

    public static final String repeat(CharSequence receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int i2 = 1;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + i + ClassUtils.PACKAGE_SEPARATOR_CHAR).toString());
        }
        if (i == 0) {
            return "";
        }
        if (i == 1) {
            return receiver.toString();
        }
        int length = receiver.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            char charAt = receiver.charAt(0);
            char[] cArr = new char[i];
            for (int i3 = 0; i3 < i; i3++) {
                cArr[i3] = charAt;
            }
            return new String(cArr);
        }
        StringBuilder sb = new StringBuilder(receiver.length() * i);
        if (1 <= i) {
            while (true) {
                sb.append(receiver);
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    public static final Comparator<String> getCASE_INSENSITIVE_ORDER(StringCompanionObject receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
        Intrinsics.checkExpressionValueIsNotNull(comparator, "java.lang.String.CASE_INSENSITIVE_ORDER");
        return comparator;
    }

    static /* bridge */ /* synthetic */ Pattern toPattern$default(String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Pattern compile = Pattern.compile(str, i);
        Intrinsics.checkExpressionValueIsNotNull(compile, "java.util.regex.Pattern.compile(this, flags)");
        return compile;
    }
}
