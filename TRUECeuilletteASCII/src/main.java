import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class main {
	
	private static boolean action = false;
	private static boolean genNewMap = false;
	
	public static void main(String[] args) throws IOException {
		
		MenuUI menu = new MenuUI();
		String path = menu.getSelectedMap();
		if(path != null){
			Parser parser = new Parser();
			List<Position> list = (List) parser.parse(path);
			GamePanel gp = new GamePanel();
		}		
	}

	

}
