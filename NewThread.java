package com.ethanyang.project_1;

import java.sql.Time;

/**
 * newThread
 */
public class NewThread implements Runnable {
    private Thread thread;
    private String threadName;

    public NewThread(String name) {
        this.threadName = name;
        System.out.println("Thread " + threadName + " is created.");
        start();
    }

    public void run() {
        System.out.println(this.threadName + " is running.");
        Menu.timeDisplay();
    }

    public void start() {
        System.out.println(this.threadName + " is starting.");
        if (this.thread == null) {
            thread = new Thread(this, this.threadName);

        }
        this.thread.start();
        System.out.println(this.threadName + " is started.");
    }

}