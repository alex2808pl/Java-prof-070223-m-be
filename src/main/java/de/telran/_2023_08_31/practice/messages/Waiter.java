package de.telran._2023_08_31.practice.messages;

public class Waiter implements Runnable {
    private Message msg;

    public Waiter(Message m){
        this.msg = m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try{
                System.out.println(name + " ждем вызов метода notify: " + System.currentTimeMillis());
                msg.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(name + " был вызов метода notify: " + System.currentTimeMillis());
            // обработаем наше сообщение
            System.out.println(name + " : " + msg.getMsg());
        }
    }
}
