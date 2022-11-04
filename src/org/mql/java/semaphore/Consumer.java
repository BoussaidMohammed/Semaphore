package org.mql.java.semaphore;

public class Consumer extends Thread {
	private Semaphore mutuel, empty, full;
	private Buffer buffer;

	public Consumer(String name, Buffer buffer, Semaphore mutuel, Semaphore empty, Semaphore full) {
		super(name);
		this.mutuel = mutuel;
		this.buffer = buffer;
		this.empty  = empty;
		this.full = full;
	}

	@Override
	public void run() {
		do {
			full.lock();
			mutuel.lock();
			System.out.println(buffer.read());;
			mutuel.unlock();
			empty.unlock();
		} while (true);
	}
}
