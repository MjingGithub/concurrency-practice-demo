package com.mjing.concurrency.dataShare;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.concurrent.ThreadSafe;

import com.mjing.concurrency.dataShare.entity.SafePoint;

/**
 * 
 * @author jing.ming
 *
 */
@ThreadSafe
public class PublishingVehicleTracker {

	private final Map<String,SafePoint> locations ;
	private final Map<String,SafePoint> unmodifiableMap ;
	
	public PublishingVehicleTracker(Map<String,SafePoint> locations){
		this.locations = new ConcurrentHashMap<String,SafePoint>(locations) ;
		this.unmodifiableMap = Collections.unmodifiableMap(locations) ;
	}
	
	public Map<String,SafePoint> getLocations(){
		return unmodifiableMap ;
	}
	
	public SafePoint getLocation(String id){
		return locations.get(id) ;
	}
	
	public void setLocation(String id,int x,int y){
		if(!locations.containsKey(id)){
			throw new IllegalArgumentException("invalid vehicle name:"+id) ;
		}
		locations.get(id).set(x, y);
	}
}
