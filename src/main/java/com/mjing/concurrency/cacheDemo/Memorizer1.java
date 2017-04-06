package com.mjing.concurrency.cacheDemo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.GuardedBy;
/**
 * 第一种方法,为了同步使用synchronized锁,可能导致计算时间等待很长,性能很低
 * @ClassName:Memorizer1
 * @Description:TODO
 * @author jing.ming
 * @date 2017年4月5日 下午10:18:04
 */
public class Memorizer1<A,V> implements Computable<A,V> {
	@GuardedBy("this")
	private final Map<A,V> cache = new HashMap<A,V>() ;
	
	private final Computable<A,V> c ;
	
	public Memorizer1(Computable<A,V> c){
		this.c = c ;
	}

	@Override
	public synchronized V compute(A arg) throws InterruptedException {
		V result = cache.get(arg) ;
		if(result==null){
			result = c.compute(arg) ;
			cache.put(arg, result) ;
		}
		return result;
	}

}
