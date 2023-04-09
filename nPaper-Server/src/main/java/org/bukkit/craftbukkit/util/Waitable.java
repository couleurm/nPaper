package org.bukkit.craftbukkit.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public abstract class Waitable<T> implements Runnable {
    private T value;
    private Throwable t;
    private CountDownLatch latch = new CountDownLatch(1);

    public final void run() {
        try {
            value = evaluate();
        } catch (Throwable t) {
            this.t = t;
        } finally {
            latch.countDown();
        }
    }

    protected abstract T evaluate();

    public T get() throws InterruptedException, ExecutionException {
        latch.await();
        if (t != null) {
            throw new ExecutionException(t);
        }
        return value;
    }
}
