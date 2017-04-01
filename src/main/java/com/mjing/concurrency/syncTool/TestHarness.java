package com.mjing.concurrency.syncTool;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 * @author jing.ming
 *
 */
public class TestHarness {

	public long timeTasks(int nThreads,final Runnable task){
		final CountDownLatch startGate = new CountDownLatch(1) ;
		final CountDownLatch endGate = new CountDownLatch(nThreads) ;
		
		for(int i=0;i<nThreads;i++){
			Thread t = new Thread(){
				public void run(){
					try {
						startGate.await();
						try{
							task.run();
						}finally{
							endGate.countDown();
						}
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		long start  = System.nanoTime() ;
		startGate.countDown();
		try {
			endGate.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		long end = System.nanoTime() ;
		return end-start ;
		
	}
}
