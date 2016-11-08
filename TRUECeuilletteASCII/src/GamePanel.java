import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.swing.*;

public class GamePanel extends JPanel{
	
	private List drawables = new LinkedList();
	
	public void paint(Graphics g) {
		super.paint(g);
		for (Iterator iter = drawables.iterator(); iter.hasNext();) {
			try{
				IDrawable d = (IDrawable) iter.next();
				d.draw(g);
				validate();
			} catch(Exception e){
				//System.out.println("YOLO YA UNE ERREUR LOL");
			}
				
		}
	}
	
	public boolean isFree(Rectangle rect) {
		for (Iterator iter = drawables.iterator(); iter.hasNext();) {
			IDrawable element = (IDrawable) iter.next();
			if(element.getRectangle().intersects(rect)){
				return false;
			}
		}
		return true;
	}
	
	public void addDrawable(IDrawable d) {
		drawables.add(d);
//		try {
//			Thread.sleep(25);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void displayGeneratedMap(IDrawable d){
		drawables.add(d);
		repaint();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List findDrawables(Position pos) {
		Point p = new Point(pos.X, pos.Y);
		List l = new ArrayList();
		for (Iterator iter = drawables.iterator(); iter.hasNext();) {
			IDrawable element = (IDrawable) iter.next();
			if(element.getRectangle().contains(p)){
				l.add(element);
			}
		}
		return l;
	}
	
	public void removeDrawable(IDrawable d) {
		drawables.remove(d);
	}
	
	public void removeAllDrawables(){
		this.drawables.clear();
		this.removeAll();
		this.repaint();
	}

}
