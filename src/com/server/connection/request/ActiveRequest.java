package com.server.connection.request;


public class ActiveRequest extends Thread{
	private ActiveRequestQueue _queue;
	public ActiveRequest() {
        _queue = new ActiveRequestQueue();
        start();
    }
	public void enqueue(MethodRequest mr) {
        _queue.enqueue(mr);
    }
	public void run() {
        while(true) {
            MethodRequest mr = _queue.dequeue();
            mr.call();
        }
    } 
}
