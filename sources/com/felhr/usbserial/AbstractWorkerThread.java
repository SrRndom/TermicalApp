package com.felhr.usbserial;

/* loaded from: classes.dex */
abstract class AbstractWorkerThread extends Thread {
    boolean firstTime = true;
    private volatile boolean keep = true;
    private volatile Thread workingThread;

    abstract void doRun();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopThread() {
        this.keep = false;
        if (this.workingThread != null) {
            this.workingThread.interrupt();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        if (this.keep) {
            this.workingThread = Thread.currentThread();
            while (this.keep && !this.workingThread.isInterrupted()) {
                doRun();
            }
        }
    }
}
