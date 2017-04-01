package com.mjing.concurrency.dataShare;

import java.awt.Event;
import java.util.EventListener;
/**
 * 使用私有的构造函数和公共的工厂方法,防止this逸出
 * @author jing.ming
 *
 */
public class SafeListener {
	
	/*private final EventListener listener ;
	
	private SafeListener(){
		listener = new EventListener(){
			@Override
			public void onEvent(Event e){
				//doSomething(e) ;
			}
		} ;
	}
	
	public static SafeListener newInstance(EventSource source){
		SafeListener safe = new SafeListener() ;
		source.registerListener(safe.listener) ;
		return safe ;
	}*/

}
