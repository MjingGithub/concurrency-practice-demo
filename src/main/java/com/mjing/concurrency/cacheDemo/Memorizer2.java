package com.mjing.concurrency.cacheDemo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 改进版的缓存,多线程可以并发的使用它们,但是仍然存在一点不足:
 * 如果某个线程移动了一个花销很大的计算,而其他线程并不知道这个计算正在进行,那么很可能会重复这个计算
 * @ClassName:Memorizer2
 * @Description:TODO
 * @author jing.ming
 * @date 2017年4月5日 下午10:23:59
 */
public class Memorizer2<A,V> implements Computable<A,V> {
	
	private final Map<A,V> cache = new ConcurrentHashMap<A,V>() ;
	private final Computable<A,V> c ;
	
	public Memorizer2(Computable<A,V> c){
		this.c = c ;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		V result = cache.get(arg) ;
		if(result==null){
			result = c.compute(arg) ;
			cache.put(arg, result) ;
		}
		return result;
	}

}
