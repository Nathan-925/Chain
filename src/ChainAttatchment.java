import java.awt.Graphics;

public abstract class ChainAttatchment {
	
	protected Chain chain;
	
	public ChainAttatchment(Chain chain) {
		this.chain = chain;
	}
	
	public abstract void move();
	
	public abstract void draw(Graphics g);
	
}
