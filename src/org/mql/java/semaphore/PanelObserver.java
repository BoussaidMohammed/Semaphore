package org.mql.java.semaphore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelObserver extends JPanel implements Observer{
	private Buffer buffer;
	private Dimension dimension;
	private int cellSize;
	private int margin = 50;
	
	
	public PanelObserver(Buffer buffer, int cellSize) {
		this.buffer = buffer;
		this.cellSize = cellSize;
		dimension = new Dimension(2*margin + buffer.size()*cellSize,margin + cellSize*2);
		setBorder(new TitledBorder(new EtchedBorder(), " Buffer (Shared Resource) : "));
	}

	@Override
	public void print() {
		repaint();
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g);
		paintBuffer(g);
		writeData(g);
	}
	
	private void writeData(Graphics g) {
		Object data[] = buffer.getData();
		for(int i = 0; i < data.length;i++) {
			if(data[i] != null) {
				g.setColor(Color.red);
				g.fillRect(margin + cellSize*i + 1, margin + 1, cellSize - 1, cellSize -1);
				g.setColor(Color.black);
				g.drawString(data[i]+ "", margin + i*cellSize + 3, margin + cellSize/2);
				g.drawString(Thread.currentThread().getName(),margin + cellSize + 3,
						margin + cellSize + 2);
			}
		}
	}

	private void paintBuffer(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(margin, margin, cellSize*buffer.size(), cellSize);
		g.setColor(Color.black);
		g.drawRect(margin, margin, cellSize*buffer.size(), cellSize);
		for (int i = 0; i <= buffer.size(); i++) {
			g.drawLine(margin + i*cellSize, margin, margin + i *cellSize, margin + cellSize);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return dimension;
	}
}
