package org.mql.java.semaphore;

import java.util.List;
import java.util.Vector;

public class Buffer {
	private Object[] data;
	private int readHead;
	private int writeHead;
	private List<Observer> observers;
	
	public Buffer(int size) {
		data = new Object[size];
		readHead = 0; writeHead = 0;
		observers = new Vector<Observer>();
	}
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void write(Object item) {
		data[writeHead] = item;
		writeHead++;
		if(writeHead >= data.length) writeHead = 0;
		signal();
		pause(1000);
	}

	private void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {}
	}

	private void signal() {
		for(Observer observer: observers) {
			observer.print();
		}
	}

	public Object[] getData() {
		return data;
	}

	public int size() {
		return data.length;
	}

	public Object read() {
		Object item = data[readHead];
		data[readHead] = null;
		readHead++;
		if(readHead >= data.length) readHead = 0;
		signal();
		pause(1000);
		return item;
	}
}
