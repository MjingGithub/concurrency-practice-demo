package com.mjing.concurrency.dataShare;

import java.util.EventListener;
/**
 *在内部类中隐式有一个this对象,可能导致this对象逸出,被别的线程修改,线程不安全
 * @author jing.ming
 *
 */
public class ThisEscape {

	/*public ThisEscape(EventSource event){
		event.registerListener(
				new EventListener(){
					@Override
					public void onEvent(Event e){
						//doSomething(e) ;
					}
				});
	}*/
}
