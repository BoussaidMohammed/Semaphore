package org.mql.java.semaphore;

public class Producer extends Thread {
	private Semaphore mutuel, empty, full;
	private Buffer buffer;

	public Producer(String name, Buffer buffer, Semaphore mutuel, Semaphore empty, Semaphore full) {
		super(name);
		this.mutuel = mutuel;
		this.buffer = buffer;
		this.empty  = empty;
		this.full = full;
	}

	@Override
	public void run() {
		do {
			int item = (int)(Math.random()*8000 + 999);
			empty.lock();
			mutuel.lock();
			buffer.write(item);
			mutuel.unlock();
			full.unlock();
		} while (true);
	}
}
