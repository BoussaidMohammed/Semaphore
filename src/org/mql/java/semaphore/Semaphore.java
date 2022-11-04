package org.mql.java.semaphore;

import java.util.LinkedList;
import java.util.List;

public class Semaphore {
	private int value;
	private List<Thread> waitingThreads;
	
	public Semaphore() {
		init(1);
	}
	
	public Semaphore(int value) {
		init(value);
	}

	private void init(int value) {
		this.value = value;
		waitingThreads = new LinkedList<>();
	}
	
	synchronized public void lock() {
		value--;
		if(value < 0) {
			//il faut attendre une ressource pour qu'il soit disponible.
			sleep();
		}
		//pour 0 et plus veut dire que la ressource existe, donc proceder a executer la section critique
	}
	
	//fin d'execution de la section critique c'est le temp de liberer la ressource
	synchronized public void unlock() {
		value++;
		if(value <= 0) {// un des threads est on attent il faut le lancer
			wakeup();
		}
		//value = 1 veut dire qu'il y'a aucun thread qui a venu apres vous.
	}
	
	

	private void wakeup() {
		notify();
	}

	private void sleep() {
		waitingThreads.add(Thread.currentThread());
		try {
			wait();
		} catch (Exception e) {}
		waitingThreads.remove(Thread.currentThread());
	}
}
