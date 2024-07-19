package kotlin;

@Metadata(bv = {1, 0, 2}, d1 = {"kotlin/LazyKt__LazyJVMKt", "kotlin/LazyKt__LazyKt"}, k = 4, mv = {1, 1, 10}, xi = 1)
/* loaded from: classes.dex */
public final class LazyKt extends LazyKt__LazyKt {

    @Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 10})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LazyThreadSafetyMode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;
            iArr[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;
            iArr[LazyThreadSafetyMode.NONE.ordinal()] = 3;
        }
    }

    private LazyKt() {
    }
}
