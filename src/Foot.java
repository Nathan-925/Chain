import java.awt.Color;
import java.awt.Graphics;

public class Foot extends ChainAttatchment {
	
	private double x, y, angle, length, allowableDistance;
	
	public Foot(Chain chain, double angle, double length, double allowableDistance) {
		super(chain);
		this.angle = angle;
		this.length = length;
		x = length*Math.cos(angle)+chain.getX();
		y = length*Math.sin(angle)+chain.getY();
		this.allowableDistance = allowableDistance;
	}

	@Override
	public void move() {
		if(Math.hypot(chain.getX()+length*Math.cos(angle+chain.getAngle())-x, chain.getY()+length*Math.sin(angle+chain.getAngle())-y) > allowableDistance) {
			x = chain.getX()+length*Math.cos(angle+chain.getAngle());
			y = chain.getY()+length*Math.sin(angle+chain.getAngle());
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawOval((int)(x-5), (int)(y-5), 10, 10);
		g.drawLine((int)x, (int)y, (int)chain.getX(), (int)chain.getY());
		//g.setColor(Color.BLUE);
		//g.drawOval((int)(chain.getX()+length*Math.cos(angle+chain.getAngle())-5), (int)(chain.getY()+length*Math.sin(angle+chain.getAngle())-5), 10, 10);
		//g.drawLine((int)(chain.getX()+length*Math.cos(angle+chain.getAngle())), (int)(chain.getY()+length*Math.sin(angle+chain.getAngle())), (int)chain.getX(), (int)chain.getY());
		//g.setColor(Color.RED);
		//g.drawLine((int)(chain.getX()+length*Math.cos(angle+chain.getAngle())), (int)(chain.getY()+length*Math.sin(angle+chain.getAngle())), (int)x, (int)y);
	}
	
}
