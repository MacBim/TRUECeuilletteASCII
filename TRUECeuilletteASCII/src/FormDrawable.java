import java.awt.*;

public abstract class FormDrawable implements IDrawable {

	protected Position pos;
	protected Rectangle rect ;
	protected Color color;
	
	public FormDrawable(Color color, Position pos, Dimension dim){
		this.pos = pos;
		this.color = color;
//		this.rect = new Rectangle(pos,dim);
		this.rect = new Rectangle(this.pos.X, this.pos.Y, dim.height, dim.width);
	}
	
	public abstract void draw(Graphics g) ;
	
	public Rectangle getRectangle(){
		return (Rectangle) rect.clone();
	}
	
	public Position getPosition(){
		return this.pos;
	}
	
	public void setPosition(Position newPos){
		this.pos = newPos;
	}
}
