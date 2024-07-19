package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SegmentedByteString.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\r\u0010\u001b\u001a\u00020\u0005H\u0010¢\u0006\u0002\b\u001cJ\b\u0010\u001d\u001a\u00020\u0005H\u0016J\b\u0010\u001e\u001a\u00020\u0015H\u0016J\u0010\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0001H\u0016J\u0010\u0010!\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0001H\u0016J\u0010\u0010\"\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0001H\u0016J\u0018\u0010#\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u0005H\u0016J\r\u0010%\u001a\u00020\rH\u0010¢\u0006\u0002\b&J\u0015\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0005H\u0010¢\u0006\u0002\b*J\u0018\u0010+\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u0005H\u0016J\b\u0010,\u001a\u00020\u0001H\u0016J(\u0010-\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010/\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J(\u0010-\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u00100\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\b\u00101\u001a\u00020\u0001H\u0016J\b\u00102\u001a\u00020\u0001H\u0016J\b\u00103\u001a\u00020\u0001H\u0016J\u0010\u00104\u001a\u00020\u00152\u0006\u00105\u001a\u000206H\u0016J\u0018\u00107\u001a\u00020\u00012\u0006\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0016J\b\u0010:\u001a\u00020\u0001H\u0016J\b\u0010;\u001a\u00020\u0001H\u0016J\b\u0010<\u001a\u00020\rH\u0016J\b\u0010=\u001a\u00020\u0001H\u0002J\b\u0010>\u001a\u00020\u0015H\u0016J\b\u0010?\u001a\u00020\u0015H\u0016J\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0016J\u0015\u0010@\u001a\u00020A2\u0006\u0010\u0002\u001a\u00020\u0003H\u0010¢\u0006\u0002\bDJ\b\u0010E\u001a\u00020FH\u0002R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006G"}, d2 = {"Lokio/SegmentedByteString;", "Lokio/ByteString;", "buffer", "Lokio/Buffer;", "byteCount", "", "(Lokio/Buffer;I)V", "directory", "", "getDirectory", "()[I", "segments", "", "", "getSegments", "()[[B", "[[B", "asByteBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "base64", "", "base64Url", "equals", "", "other", "", "getSize", "getSize$jvm", "hashCode", "hex", "hmacSha1", "key", "hmacSha256", "hmacSha512", "indexOf", "fromIndex", "internalArray", "internalArray$jvm", "internalGet", "", "pos", "internalGet$jvm", "lastIndexOf", "md5", "rangeEquals", "offset", "otherOffset", "segment", "sha1", "sha256", "sha512", "string", "charset", "Ljava/nio/charset/Charset;", "substring", "beginIndex", "endIndex", "toAsciiLowercase", "toAsciiUppercase", "toByteArray", "toByteString", "toString", "utf8", "write", "", "out", "Ljava/io/OutputStream;", "write$jvm", "writeReplace", "Ljava/lang/Object;", "jvm"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes.dex */
public final class SegmentedByteString extends ByteString {
    private final transient int[] directory;
    private final transient byte[][] segments;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SegmentedByteString(Buffer buffer, int i) {
        super(ByteString.EMPTY.getData());
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        Util.checkOffsetAndCount(buffer.size(), 0L, i);
        Segment segment = buffer.head;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            if (segment.limit == segment.pos) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += segment.limit - segment.pos;
            i4++;
            segment = segment.next;
        }
        byte[][] bArr = new byte[i4];
        this.directory = new int[i4 * 2];
        Segment segment2 = buffer.head;
        int i5 = 0;
        while (i2 < i) {
            if (segment2 == null) {
                Intrinsics.throwNpe();
            }
            bArr[i5] = segment2.data;
            i2 += segment2.limit - segment2.pos;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.directory;
            iArr[i5] = i2;
            iArr[bArr.length + i5] = segment2.pos;
            segment2.shared = true;
            i5++;
            segment2 = segment2.next;
        }
        this.segments = bArr;
    }

    public final byte[][] getSegments() {
        return this.segments;
    }

    public final int[] getDirectory() {
        return this.directory;
    }

    @Override // okio.ByteString
    public String utf8() {
        return toByteString().utf8();
    }

    @Override // okio.ByteString
    public String string(Charset charset) {
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return toByteString().string(charset);
    }

    @Override // okio.ByteString
    public String base64() {
        return toByteString().base64();
    }

    @Override // okio.ByteString
    public String hex() {
        return toByteString().hex();
    }

    @Override // okio.ByteString
    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    @Override // okio.ByteString
    public ByteString toAsciiUppercase() {
        return toByteString().toAsciiUppercase();
    }

    @Override // okio.ByteString
    public ByteString md5() {
        return toByteString().md5();
    }

    @Override // okio.ByteString
    public ByteString sha1() {
        return toByteString().sha1();
    }

    @Override // okio.ByteString
    public ByteString sha256() {
        return toByteString().sha256();
    }

    @Override // okio.ByteString
    public ByteString sha512() {
        return toByteString().sha512();
    }

    @Override // okio.ByteString
    public ByteString hmacSha1(ByteString key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return toByteString().hmacSha1(key);
    }

    @Override // okio.ByteString
    public ByteString hmacSha256(ByteString key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return toByteString().hmacSha256(key);
    }

    @Override // okio.ByteString
    public ByteString hmacSha512(ByteString key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return toByteString().hmacSha512(key);
    }

    @Override // okio.ByteString
    public String base64Url() {
        return toByteString().base64Url();
    }

    @Override // okio.ByteString
    public ByteString substring(int beginIndex, int endIndex) {
        return toByteString().substring(beginIndex, endIndex);
    }

    @Override // okio.ByteString
    public byte internalGet$jvm(int pos) {
        Util.checkOffsetAndCount(this.directory[this.segments.length - 1], pos, 1L);
        int segment = segment(pos);
        int i = segment == 0 ? 0 : this.directory[segment - 1];
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        return bArr[segment][(pos - i) + iArr[bArr.length + segment]];
    }

    private final int segment(int pos) {
        int binarySearch = Arrays.binarySearch(this.directory, 0, this.segments.length, pos + 1);
        return binarySearch >= 0 ? binarySearch : ~binarySearch;
    }

    @Override // okio.ByteString
    public int getSize$jvm() {
        return this.directory[this.segments.length - 1];
    }

    @Override // okio.ByteString
    public byte[] toByteArray() {
        int[] iArr = this.directory;
        byte[][] bArr = this.segments;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.directory;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            Platform.arraycopy(this.segments[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    @Override // okio.ByteString
    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    @Override // okio.ByteString
    public void write(OutputStream out) throws IOException {
        Intrinsics.checkParameterIsNotNull(out, "out");
        int length = this.segments.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.directory;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            out.write(this.segments[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    @Override // okio.ByteString
    public void write$jvm(Buffer buffer) {
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        int length = this.segments.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.directory;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            Segment segment = new Segment(this.segments[i], i3, (i3 + i4) - i2, true, false);
            if (buffer.head == null) {
                segment.prev = segment;
                segment.next = segment.prev;
                buffer.head = segment.next;
            } else {
                Segment segment2 = buffer.head;
                if (segment2 == null) {
                    Intrinsics.throwNpe();
                }
                Segment segment3 = segment2.prev;
                if (segment3 == null) {
                    Intrinsics.throwNpe();
                }
                segment3.push(segment);
            }
            i++;
            i2 = i4;
        }
        buffer.setSize$jvm(buffer.size() + i2);
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int offset, ByteString other, int otherOffset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (offset < 0 || offset > size() - byteCount) {
            return false;
        }
        int segment = segment(offset);
        while (byteCount > 0) {
            int i = segment == 0 ? 0 : this.directory[segment - 1];
            int min = Math.min(byteCount, ((this.directory[segment] - i) + i) - offset);
            int[] iArr = this.directory;
            byte[][] bArr = this.segments;
            if (!other.rangeEquals(otherOffset, bArr[segment], (offset - i) + iArr[bArr.length + segment], min)) {
                return false;
            }
            offset += min;
            otherOffset += min;
            byteCount -= min;
            segment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int offset, byte[] other, int otherOffset, int byteCount) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (offset < 0 || offset > size() - byteCount || otherOffset < 0 || otherOffset > other.length - byteCount) {
            return false;
        }
        int segment = segment(offset);
        while (byteCount > 0) {
            int i = segment == 0 ? 0 : this.directory[segment - 1];
            int min = Math.min(byteCount, ((this.directory[segment] - i) + i) - offset);
            int[] iArr = this.directory;
            byte[][] bArr = this.segments;
            if (!Util.arrayRangeEquals(bArr[segment], (offset - i) + iArr[bArr.length + segment], other, otherOffset, min)) {
                return false;
            }
            offset += min;
            otherOffset += min;
            byteCount -= min;
            segment++;
        }
        return true;
    }

    @Override // okio.ByteString
    public int indexOf(byte[] other, int fromIndex) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return toByteString().indexOf(other, fromIndex);
    }

    @Override // okio.ByteString
    public int lastIndexOf(byte[] other, int fromIndex) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return toByteString().lastIndexOf(other, fromIndex);
    }

    private final ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    @Override // okio.ByteString
    public byte[] internalArray$jvm() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ByteString) {
            ByteString byteString = (ByteString) other;
            if (byteString.size() == size() && rangeEquals(0, byteString, 0, size())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public int hashCode() {
        int hashCode$jvm = getHashCode();
        if (hashCode$jvm != 0) {
            return hashCode$jvm;
        }
        int length = this.segments.length;
        int i = 0;
        int i2 = 0;
        int i3 = 1;
        while (i < length) {
            byte[] bArr = this.segments[i];
            int[] iArr = this.directory;
            int i4 = iArr[length + i];
            int i5 = iArr[i];
            int i6 = (i5 - i2) + i4;
            while (i4 < i6) {
                i3 = (i3 * 31) + bArr[i4];
                i4++;
            }
            i++;
            i2 = i5;
        }
        setHashCode$jvm(i3);
        return i3;
    }

    @Override // okio.ByteString
    public String toString() {
        return toByteString().toString();
    }

    private final Object writeReplace() {
        ByteString byteString = toByteString();
        if (byteString != null) {
            return byteString;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
    }
}
