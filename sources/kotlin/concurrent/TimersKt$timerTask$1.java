package kotlin.concurrent;

import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: Timer.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "(Lkotlin/jvm/functions/Function1;)V", "run", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes.dex */
public final class TimersKt$timerTask$1 extends TimerTask {
    final /* synthetic */ Function1 $action;

    public TimersKt$timerTask$1(Function1 function1) {
        this.$action = function1;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.$action.invoke(this);
    }
}
