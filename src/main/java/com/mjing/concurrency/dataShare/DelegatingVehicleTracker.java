package com.mjing.concurrency.dataShare;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.concurrent.ThreadSafe;

import com.mjing.concurrency.dataShare.entity.Point;

/**
 * 将线程安全委托给ConcurrentHashMap
 * @author jing.ming
 *
 */
@ThreadSafe
public class DelegatingVehicleTracker {

	private final ConcurrentMap<String,Point> locations ;
	
	private final Map<String,Point> unmodifiableMap ;
	
	public DelegatingVehicleTracker(Map<String,Point> points){
		locations = new ConcurrentHashMap<String,Point>(points) ;
		unmodifiableMap = Collections.unmodifiableMap(points) ;
	}
	
	public Map<String,Point> getLocations(){
		return unmodifiableMap ;
	}
	
	public Point getLocation(String id){
		return locations.get(id) ;
	}
	
	public void setLocation(String id,int x,int y){
		if(locations.replace(id, new Point(x,y))==null){
			throw new IllegalArgumentException("invalid vehicle name:"+id) ;
		}
	}
	
}
