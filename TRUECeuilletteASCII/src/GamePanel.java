import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.swing.*;

// Also used by the refresh Thread
/**
 * @author Jean-Baptiste
 *
 */
public class GamePanel extends JFrame implements Runnable{
	
	private List drawables = new LinkedList();
	private JPanel content;
	private int nbPatch;

	/**
	 * Builder
	 */
	public GamePanel(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.content = new JPanel();
		this.setTitle("Game");
		this.setSize(500,500);
		this.setVisible(false);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
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
	
	/**
	 * Set the current value of patches remaining
	 * @param nbPatch
	 */
	public void setNbPatch(int nbPatch){
		this.nbPatch = nbPatch;
	}
	
	/**
	 * Verify if the rect is on an empty are (with no patches or agents)
	 * @param rect FormDrawables used for the test
	 * @return True if the position is empty, False otherwise
	 */
	public boolean isFree(Rectangle rect) {
		for (Iterator iter = drawables.iterator(); iter.hasNext();) {
			IDrawable element = (IDrawable) iter.next();
			if(element.getRectangle().intersects(rect)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Adds a FormDrawable to the list
	 * @param d
	 */
	public void addDrawable(FormDrawable d) {
		drawables.add(d);
	}
	
	/**
	 * @param pos Position to check out
	 * @return Every Objects presents on the "pos" position
	 */
	public List findDrawables(Position pos) {
		Point p = new Point(pos.getX(), pos.getY());
		List l = new ArrayList();
		for (Iterator iter = drawables.iterator(); iter.hasNext();) {
			IDrawable element = (IDrawable) iter.next();
			if(element.getRectangle().contains(p)){
				l.add(element);
			}
		}
		return l;
	}
	
	public void removeDrawable(FormDrawable d) {
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
		while(this.nbPatch != 0 && this.isShowing()){
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
