import java.awt.Graphics;
import java.util.ArrayList;

public class Chain {

	protected Chain nextLink, pastLink;
	protected double x, y, spacing, size, angle;
	protected ArrayList<ChainAttatchment> attatchments;
	
	public Chain(double x, double y, int length, double spacing, double size) {
		this.x = x;
		this.y = y;
		this.spacing = spacing;
		this.size = size;
		angle = 0;
		attatchments = new ArrayList<>();
		if(length > 1)
			nextLink = new Chain(this, x-spacing, y, length-1, spacing, size);
	}
	
	private Chain(Chain pastLink, double x, double y, int length, double spacing, double size) {
		this(x, y, length, spacing, size);
		this.pastLink = pastLink;
	}
	
	public boolean addAttatchment(ChainAttatchment a) {
		return attatchments.add(a);
	}
	
	public void moveTo(double x, double y) {
		this.x = x;
		this.y = y;
		if(nextLink != null) {
			if(pastLink != null)
				angle = Math.atan2(pastLink.y-y, pastLink.x-x);
			else
				angle = 0;
				//angle = Math.atan2(y-nextLink.y, x-nextLink.x)+Math.PI;
			if(Math.hypot(this.x-nextLink.x, this.y-nextLink.y) > spacing) {
				double angle = Math.atan2(nextLink.y-this.y, nextLink.x-this.x);
				nextLink.moveTo(x+spacing*Math.cos(angle), y+spacing*Math.sin(angle));
			}
		}
		
		for(ChainAttatchment a: attatchments)
			a.move();
	}
	
	public void move(double x, double y) {
		moveTo(x-this.x, y-this.y);
	}
	
	public void draw(Graphics g) {
		g.drawOval((int)(x-size/2), (int)(y-size/2), (int)size, (int)size);
		if(nextLink != null) {
			g.drawLine((int)x, (int)y, (int)nextLink.x, (int)nextLink.y);
			nextLink.draw(g);
		}
		
		for(ChainAttatchment a: attatchments)
			a.draw(g);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public Chain get(int i) {
		Chain c = this;
		for(int j = 0; j < i; j++)
			c = c.nextLink;
		return c;
	}
	
}
