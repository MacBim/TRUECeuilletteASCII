import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.swing.*;

// Also used by the refresh Thread
public class GamePanel extends JFrame implements Runnable{
	
	private List drawables = new LinkedList();
	private JPanel content;
	private int nbPatch;

	public GamePanel(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.content = new JPanel();
		this.setTitle("Game");
		this.setSize(500,500);
		this.setVisible(false);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for (Iterator iter = drawables.iterator(); iter.hasNext();) {
			try{
				IDrawable d = (IDrawable) iter.next();
				d.draw(g);
				this.content.validate();
			} catch(Exception e){
				//System.out.println("YOLO YA UNE ERREUR LOL");
			}
				
		}
	}
	
	public void setNbPatch(int nbPatch){
		this.nbPatch = nbPatch;
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
		this.content.removeAll();
		this.content.repaint();
	}
	
	public List getDrawables(){
		return this.drawables;
	}
	
	public void setDrawables(List drawables){
		this.drawables = drawables;
	}

	@Override
	public void run() {
		System.err.println("Refresh thread started");
		// TODO Auto-generated method stub
		while(this.nbPatch != 0){
			this.invalidate();
			this.validate();
			this.repaint();
			try {
				System.err.println("Refreshing...");
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("Thread déja arrété");
				return;
				//this.dispose();
			}
		}
	}
	
	

}
