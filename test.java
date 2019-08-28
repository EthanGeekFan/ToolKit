package com.ethanyang.project_1;

// import sun.swing.PrintColorUIResource;

/**
 * test
 */
public class test {

    /*
     * public static void main(String[] args) { newThread R1 = new
     * newThread("Shit"); R1.start(); newThread R2 = new newThread("poop");
     * R2.start(); }
     */

}

class newThread implements Runnable {
    private Thread thread;
    private String name;

    public newThread(String name) {
        this.name = name;
        System.out.println("a new thread is built: " + name);
    }

    public void run() {
        while (true) {
            System.out.println("Hello" + name);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void start() {
        System.out.println(name + "thread is starting.");
        if (thread == null) {
            thread = new Thread(this, name);
        }
        thread.start();
        System.out.println(name + "thread is started.");
    }
}