package com.hhm.basic.Thread;

/**
 * 终止线程。不要直接使用stop方法，在高并发环境下会极其容易引起数据不一致。
 * 下面来模拟引起数据不一致的例子。
 * 本例中，希望user的id和name是一致的，但是由于随意stop线程，很容易造成数据不一致
 */
public class ThreadStopUnSafe {
    private static User user = new User();

    public static void main(String[] args) throws InterruptedException {
        new ReadObjThread().start();
        while (true){
            Thread t = new ChangeObjThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
        /**
         id=1559360650,name=1559360649
         id=1559360652,name=1559360651
         */
    }


    /**
     * 写线程，把user的id和name设为同一个值。
     */
    public static class ChangeObjThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (user){
                    int v = (int)(System.currentTimeMillis()/1000);
                    user.setId(v);
                    try {
                        Thread.sleep(100); //休眠100毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(v));
                }
                Thread.yield();//让出锁
            }
        }
    }

    /**
     * 读线程，当user的id不等于name时，打印出值
    */
    public static class ReadObjThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (user){
                    if(user.getName() != null && (user.getId() != Integer.parseInt(user.getName()))){
                        System.out.println(user.toString());
                    }
                }
            }
        }
    }

    public static class User{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "id="+id+",name="+name;
        }
    }
}
