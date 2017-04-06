package com.mjing.concurrency.cacheDemo;

public interface Computable<A,V> {
	V compute(A arg) throws InterruptedException ;

}
