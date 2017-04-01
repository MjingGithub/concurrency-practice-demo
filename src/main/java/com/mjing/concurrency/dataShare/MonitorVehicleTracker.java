package com.mjing.concurrency.dataShare;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.mjing.concurrency.dataShare.entity.MutablePoint;

/**
 * 基于监视器模式的车辆追踪
 * @author jing.ming
 *
 */
@ThreadSafe
public class MonitorVehicleTracker {

	@GuardedBy("this")
	private final Map<String,MutablePoint> locations = null ;
	
	public MonitorVehicleTracker(Map<String,MutablePoint> locations){
		deepCopy(locations) ;
	}
	
	public synchronized Map<String,MutablePoint> getLocations(){
		return deepCopy(locations) ;
	}
	
	public synchronized MutablePoint getLocation(String id){
		MutablePoint loc = locations.get(id) ;
		return loc==null?null:new MutablePoint(loc) ;
	}
	
	public synchronized void setLocation(String id,int x,int y){
		MutablePoint loc = locations.get(id) ;
		if(loc == null){
			throw new IllegalArgumentException("No Such ID:"+id) ;
		}
		loc.x = x;
		loc.y = y;
	}
	
	private static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> locations){
		Map<String,MutablePoint> result = new HashMap<String,MutablePoint>() ;
		for(String id:locations.keySet()){
			result.put(id, new MutablePoint(locations.get(id))) ;
		}
		return Collections.unmodifiableMap(result) ;
	}
}
