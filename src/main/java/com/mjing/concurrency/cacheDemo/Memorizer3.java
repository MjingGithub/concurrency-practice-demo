package com.mjing.concurrency.cacheDemo;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/**
 * 第三个设计几乎是完美的,它表现出了很好的并发性,若结果已经计算出来,它会立马返回,如果其他线程正在计算该结果,
 * 那么新的线程将会一直等待这个结果被计算出来,
 * 它只有一个缺陷,仍然存在2个线程计算出相同值的漏洞:由于compute方法中的if代码块仍然是非原子的"先检查再执行的操作",
 * 因此两个线程仍有可能在同一时间内调用compute来计算相同的值.
 * @ClassName:Memorizer3
 * @Description:TODO
 * @author jing.ming
 * @date 2017年4月6日 下午10:42:32
 */
public class Memorizer3<A,V> implements Computable<A,V> {
	private final Map<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>() ;
	
	private final Computable<A,V> c ;
	
	public Memorizer3(Computable<A,V> c){
		this.c = c ;
	}


	@Override
	public V compute(final A arg) throws InterruptedException {
		Future<V> future = cache.get(arg) ;
		if(future == null){
			Callable<V> eval = new Callable<V>(){

				@Override
				public V call() throws Exception {
					return c.compute(arg) ;
				}
				
			};
			FutureTask<V> ft = new FutureTask<V>(eval) ;
			future = ft ;
			cache.put(arg, ft) ;
			ft.run();
		}
		try{
			return future.get() ;
		}catch(ExecutionException e){
			throw new RuntimeException() ;
		}
	}
}
