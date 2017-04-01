package com.mjing.concurrency.dataShare;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * 一个典型的监视器模式的线程安全计数器
 * @author jing.ming
 *
 */
@ThreadSafe
public class Counter {
	
	@GuardedBy("this") private long value = 0 ;
	
	public synchronized long getCount(){
		return value ;
	}
	
	public synchronized long increment(){
		if(value==Long.MAX_VALUE){
			throw new IllegalStateException("counter overflow.") ;
		}
		return ++value ;
	}
}
