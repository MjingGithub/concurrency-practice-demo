package com.mjing.concurrency.syncTool;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界
 * @ClassName:BoundedHashSet
 * @Description:TODO
 * @author jing.ming
 * @date 2017年4月5日 下午9:25:52
 */
public class BoundedHashSet<T> {

	private final Set<T> set ;
	private final Semaphore sem ;
	
	public BoundedHashSet(int bound){
		this.set = Collections.synchronizedSet(new HashSet<T>()) ;
		this.sem = new Semaphore(bound) ;
	}
	
	public boolean add(T obj) throws InterruptedException{
		sem.acquire();
		boolean isAdd = false ;
		isAdd = set.add(obj) ;
		if(!isAdd){
			sem.release();
		}
		return isAdd ;
	}
	
	public boolean remove(Object o){
		boolean isRemoved = set.remove(o) ;
		if(isRemoved){
			sem.release();
		}
		return isRemoved ;
	}
}
	
