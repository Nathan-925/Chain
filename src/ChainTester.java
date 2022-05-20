import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChainTester extends JPanel implements Runnable {

	private Chain chain;
	
	public ChainTester() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(800, 800));
		
		chain = new Rope(100, 100, 20, 20, 10);
		//chain.get(2).addAttatchment(new Foot(chain.get(2), -Math.PI/6, 40, 80));
		//chain.get(2).addAttatchment(new Foot(chain.get(2), Math.PI/6, 40, 80));
		//chain.get(6).addAttatchment(new Foot(chain.get(6), -Math.PI/6, 40, 80));
		//chain.get(6).addAttatchment(new Foot(chain.get(6), Math.PI/6, 40, 80));
		
		new Thread(this).start();
	}
	
	public void tick() {
		Point p = getMousePosition();
		if(p != null) {
			p.setLocation(p.x-getLocation().x, p.y-getLocation().y);
			chain.moveTo(p.x, p.y);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		chain.draw(g);
	}

	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(5);
				tick();
				repaint();
			}
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(new ChainTester());
		frame.pack();
		frame.setVisible(true);
	}
	
}
