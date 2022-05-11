import java.awt.Graphics;

public class Chain {

	protected Chain nextLink;
	protected double x, y, spacing, size;
	
	public Chain(double x, double y, int length, double spacing, double size) {
		this.x = x;
		this.y = y;
		this.spacing = spacing;
		this.size = size;
		if(length > 1)
			nextLink = new Chain(x, y+spacing, length-1, spacing, size);
	}
	
	public void moveTo(double x, double y) {
		this.x = x;
		this.y = y;
		if(nextLink != null && Math.hypot(this.x-nextLink.x, this.y-nextLink.y) > spacing) {
			double angle = Math.atan2(nextLink.y-this.y, nextLink.x-this.x);
			nextLink.moveTo(x+spacing*Math.cos(angle), y+spacing*Math.sin(angle));
		}
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
	}
	
}
