package com.company;

public class WorkerThread extends Thread {
    private final int threadID;
    private final String threadName;
    private static int threadCount;

    public WorkerThread(int threadID, String threadName) {
        this.threadID = threadID;
        this.threadName = threadName;
        threadCount++;
    }

    public int getThreadID() {
        return threadID;
    }

    public String getThreadName() {
        return threadName;
    }

    public static int getThreadCount() {
        return threadCount;
    }
}
