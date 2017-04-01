package com.mjing.concurrency.dataShare.entity;

import javax.annotation.concurrent.Immutable;

@Immutable
public class Point {

	public final int x,y ;
	
	public Point(int x,int y){
		this.x = x ;
		this.y = y ;
	}
}
