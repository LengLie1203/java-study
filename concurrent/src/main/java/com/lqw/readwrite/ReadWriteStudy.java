package com.lqw.readwrite;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteStudy {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        FileData fileData=new FileData("default file data");
        ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
        for (int i = 0; i < 3; i++) {

            executorService.execute(new ReadTask(fileData,readWriteLock));
        }
        executorService.execute(new WriteTask(fileData,readWriteLock));

        executorService.shutdown();
    }

    private static class ReadTask implements Runnable{

        private FileData fileData;

        private ReadWriteLock readWriteLock;

        public ReadTask(FileData fileData, ReadWriteLock readWriteLock) {
            this.fileData = fileData;
            this.readWriteLock = readWriteLock;
        }

        /**
         * 循环读取数据
         */
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                readWriteLock.readLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"  " +fileData.getData());
                }finally {
                    readWriteLock.readLock().unlock();;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static class WriteTask implements Runnable{

        private FileData fileData;

        private ReadWriteLock readWriteLock;

        public WriteTask(FileData fileData, ReadWriteLock readWriteLock) {
            this.fileData = fileData;
            this.readWriteLock = readWriteLock;
        }

        /**
         * 每隔1秒钟修改一次数据
         */
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                readWriteLock.writeLock().lock();
                System.out.println("****************edit file start**********************");

                try {
                    String fileDataStr="edit file data to "+i;
                    System.out.println(fileData.getData()+"=>"+fileDataStr);
                    fileData.setData(fileDataStr);
                    Thread.sleep(500);
                    System.out.println("****************edit file end************************");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readWriteLock.writeLock().unlock();;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



