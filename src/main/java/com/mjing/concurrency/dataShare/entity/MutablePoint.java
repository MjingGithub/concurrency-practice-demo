package com.mjing.concurrency.dataShare.entity;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MutablePoint {

	public int x,y ;
	
	public MutablePoint(){
		x = 0 ;
		y = 0 ;
	}
	public MutablePoint(MutablePoint p){
		this.x = p.x ;
		this.y = p.y ;
	}
	

}
