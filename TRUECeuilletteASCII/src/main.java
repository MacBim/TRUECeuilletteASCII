import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

	public static void main(String[] args) throws IOException {
		GameWindow window = new GameWindow(500);
		UIv2 WindowUI = (UIv2) window.getUI();
		Parser parser = new Parser();
		List list = (List) parser.parse("../map.txt");
		GameIA gameIA = new GameIA(list,WindowUI);
		
		window.getCommandePanel().startButton.addActionListener(new ActionListener() { 
	        public void actionPerformed(ActionEvent e) { 
	            gameIA.lauchgame(window);
	        } 
	    });
	}

}
