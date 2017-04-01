package com.mjing.concurrency.dataShare;

import javax.annotation.concurrent.GuardedBy;

import com.mjing.concurrency.dataShare.entity.Widget;

/**
 * 使用私有锁访问状态
 * @author jing.ming
 *
 */
public class PrivateLock {
	
	private final Object lock = new Object() ;
	@GuardedBy("lock")
	Widget widget ;
	
	void someMethod(){
		synchronized(lock){
			//访问或者修改widget的状态
		}
	}

}
