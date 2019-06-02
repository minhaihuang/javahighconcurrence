package com.hhm.basic.Thread;

/**
 * 正确的终止线程的方法。
 * 加一个标识符，根据该表标识符的状态来确定是否终止线程
 */
public class ThreadStopSafe {
    private static ThreadStopUnSafe.User user = new ThreadStopUnSafe.User();

    public static void main(String[] args) throws InterruptedException {
        new ThreadStopUnSafe.ReadObjThread().start();
        while (true){
            ChangeObjThread changeObjThread = new ChangeObjThread();
            changeObjThread.start();
            Thread.sleep(150);
            changeObjThread.stopMe();
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
        volatile boolean isStop = false; //是否终止线程

        public void stopMe(){
            isStop = true;
        }
        @Override
        public void run() {
            while (true){
                if(isStop){ //若线程已经终止，就不让其再去写入数据。会跳出该循环，由于锁还没有被释放，所以其他线程还没有机会去写数据
                    //一直等到该线程执行完成之后，才会有机会获取锁，才会有机会去写数据
                    System.out.println("thread was been stop");
                    break;
                }
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
