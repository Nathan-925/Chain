import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Rope extends Chain {

	public Rope(double x, double y, int length, double spacing, double size) {
		super(x, y, length, spacing, size);
		if(length > 1)
			nextLink = new Rope(this, x-spacing, y, length-1, spacing, size);
	}
	
	private Rope(Chain pastLink, double x, double y, int length, double spacing, double size) {
		this(x, y, length, spacing, size);
		this.pastLink = pastLink;
	}
	
	@Override
	public void draw(Graphics g) {
		Path2D.Double path = new Path2D.Double();
		path.moveTo(x, y);
		pathTo(path);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke((float)size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2.draw(path);
	}
	
	private void pathTo(Path2D.Double path) {
		if(nextLink != null) {
			path.lineTo(nextLink.x, nextLink.y);
			((Rope)nextLink).pathTo(path);
		}
	}
	
}
