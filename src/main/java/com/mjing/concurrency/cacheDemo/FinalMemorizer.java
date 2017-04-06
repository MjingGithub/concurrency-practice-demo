package com.mjing.concurrency.cacheDemo;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/**
 * 使用了ConcurrentHashMap的putIfAbsent,避免了Memorizer3中的漏洞.
 * @ClassName:FinalMemorizer
 * @Description:TODO
 * @author jing.ming
 * @date 2017年4月6日 下午10:54:58
 */
public class FinalMemorizer<A,V> implements Computable<A,V> {
	
	private final ConcurrentHashMap<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>() ;
	
	private final Computable<A,V> c ;
	
	public FinalMemorizer(Computable<A,V> c){
		this.c = c ;
	}

	@Override
	public V compute(final A arg) throws InterruptedException {
		while(true){
			Future<V> f = cache.get(arg) ;
			if(f==null){
				Callable<V> eval = new Callable<V>(){
					@Override
					public V call() throws Exception {
						return c.compute(arg) ;
					}		
				};
				FutureTask<V> ft = new FutureTask<V>(eval) ;
				f = cache.putIfAbsent(arg, ft) ;
				if(f==null){
					f = ft ;
					ft.run();
				}
			}
			try{
				return f.get() ;
			}catch(CancellationException e){
				cache.remove(arg,f) ;
			}catch(ExecutionException e){
				throw new RuntimeException() ;
			}
		}
	}

}
