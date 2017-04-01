package com.mjing.concurrency.dataShare.entity;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * 可变且线程安全的Point
 * @author jing.ming
 *
 */
@ThreadSafe
public class SafePoint {

	@GuardedBy("this")private int x , y ;
	private SafePoint(int[] a){
		this(a[0],a[1]) ;
	}
	public SafePoint(SafePoint p){
		this(p.get()) ;
	}
	
	public SafePoint(int x, int y){
		this.x = x ;
		this.y = y ;
	}
	
	public synchronized int[] get(){
		return new int[]{x,y} ;
	}
	
	public synchronized void set(int x,int y){
		this.x = x ;
		this.y = y ;
	}
}
