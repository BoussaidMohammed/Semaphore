package org.mql.java.semaphore;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Examples extends JFrame {
	
	static void multi(int a, int b) {
		System.out.println(a*b);
	}
	
	static void multi(double a, double b) {
		System.out.println(a*b);
	}
	
	

	public Examples() {
		exp01();
		init();
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 400);
		setVisible(true);
	}

	private void exp01() {
		Buffer buffer = new Buffer(7);
		Semaphore mutuel = new Semaphore(1);
		Semaphore empty = new Semaphore(buffer.size());
		Semaphore full = new Semaphore(0);
		buffer.addObserver(new ConsoleObserver(buffer));
		PanelObserver po = new PanelObserver(buffer, 50);
		buffer.addObserver(po);
		JPanel content = new JPanel();
		content.add(po);
		setContentPane(content);
		Producer p[] = new Producer[2];
		for (int i = 0; i < p.length; i++) {
			p[i] = new Producer("p" + i, buffer, mutuel, empty, full);
			p[i].start();
		}

		Consumer c[] = new Consumer[3];
		for (int i = 0; i < c.length; i++) {
			c[i] = new Consumer("c" + i, buffer, mutuel, empty, full);
			c[i].start();
		}
	}

	private void exp02() {
		Buffer buffer = new Buffer(7);
		Semaphore mutuel = new Semaphore(0);
		buffer.addObserver(new ConsoleObserver(buffer));
		
		Producer p[] = new Producer[3];
		for(int i = 0;i < p.length;i++) {
			p[i] = new Producer("p" + i, buffer, mutuel, null, null);
			p[i].start();
		}
		
		try {
			Thread.sleep(5000);
			mutuel.unlock();
		} catch (InterruptedException e) {}

	}

	public static void main(String[] args) {
		new Examples();
	}

}
