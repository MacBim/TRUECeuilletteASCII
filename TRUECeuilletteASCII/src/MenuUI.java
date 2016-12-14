import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class MenuUI extends JFrame {

	private Thread refreshThread;
	private Thread gameThread;
	
	private GameEngine ge;
	private JPanel contentPane;
	private JButton generateMapBtn;
	private JButton startBtn;
	private JButton openMapBtn;
	private JSpinner agentSelector;
	private JSpinner alphaSelector;
	private JLabel starProbabilityLabel;
	private JComboBox functionUsedCB;
	private String selectedMap;
	private JLabel mapNameLabel;
	private JSpinner mapSizeSelector;
	private JSpinner startProbabilitySelector;
	private JCheckBox chkboxCalibration;
	private JSpinner nbIterationsSelector;
	private JLabel nbIterationsLabel;

	/**
	 * Create the frame.
	 */
	public MenuUI() {
		setTitle("Projet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 408);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		agentSelector = new JSpinner();
		agentSelector.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		agentSelector.setBounds(57, 210, 87, 20);
		contentPane.add(agentSelector);
		
		JLabel AgentSelectorLabel = new JLabel("Agents");
		AgentSelectorLabel.setBounds(57, 193, 87, 14);
		contentPane.add(AgentSelectorLabel);
		
		startBtn = new JButton("Start");
		startBtn.setBounds(235, 141, 133, 23);
		startBtn.addActionListener(e -> start());
		contentPane.add(startBtn);
		
		openMapBtn = new JButton("Open a map");
		openMapBtn.setBounds(235, 309, 133, 23);
		openMapBtn.addActionListener(e -> chooseMap());
		contentPane.add(openMapBtn);
		
		alphaSelector = new JSpinner();
		alphaSelector.setToolTipText("");
		alphaSelector.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(2), new Float(0.01)));
		alphaSelector.setBounds(57, 257, 87, 20);
		contentPane.add(alphaSelector);
		
		JLabel alphaSelectorLabel = new JLabel("Alpha");
		alphaSelectorLabel.setBounds(55, 236, 78, 14);
		contentPane.add(alphaSelectorLabel);
		
		JLabel logoLabel = new JLabel("Smart Picking");
		logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 41));
		logoLabel.setBounds(218, 33, 254, 84);
		contentPane.add(logoLabel);
		
		generateMapBtn = new JButton("Generate a map");
		generateMapBtn.setBounds(235, 213, 133, 23);
		generateMapBtn.addActionListener(e -> generateMap());
		contentPane.add(generateMapBtn);
		
		mapNameLabel = new JLabel("No map selected");
		mapNameLabel.setEnabled(false);
		mapNameLabel.setBounds(394, 313, 95, 14);
		contentPane.add(mapNameLabel);
		
		startProbabilitySelector = new JSpinner();
		startProbabilitySelector.setModel(new SpinnerNumberModel(new Double(0.5), new Double(0), null, new Double(0.01)));
		startProbabilitySelector.setBounds(394, 216, 78, 20);
		contentPane.add(startProbabilitySelector);
		
		starProbabilityLabel = new JLabel("Patch probability");
		starProbabilityLabel.setBounds(394, 193, 95, 14);
		contentPane.add(starProbabilityLabel);
		
		functionUsedCB = new JComboBox();
		functionUsedCB.setModel(new DefaultComboBoxModel(new String[] {"L\u00E9vy", "Random"}));
		functionUsedCB.setSelectedIndex(0);
		functionUsedCB.setBounds(394, 144, 95, 20);
		contentPane.add(functionUsedCB);
		
		JLabel functionUsedLabel = new JLabel("Function to use :");
		functionUsedLabel.setBounds(394, 126, 95, 14);
		contentPane.add(functionUsedLabel);
		
		mapSizeSelector = new JSpinner();
		mapSizeSelector.setModel(new SpinnerNumberModel(new Integer(10), new Integer(10), null, new Integer(1)));
		mapSizeSelector.setBounds(394, 262, 78, 20);
		contentPane.add(mapSizeSelector);
		
		JLabel mapSizeLabel = new JLabel("Map Size");
		mapSizeLabel.setBounds(394, 243, 46, 14);
		contentPane.add(mapSizeLabel);
		
		chkboxCalibration = new JCheckBox("Calibration Mode");
		chkboxCalibration.setBackground(new Color(0, 191, 255));
		chkboxCalibration.setBounds(514, 141, 133, 23);
		chkboxCalibration.addActionListener(e -> displayCalibrationInformation());
		contentPane.add(chkboxCalibration);
		
		nbIterationsSelector = new JSpinner();
		nbIterationsSelector.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		nbIterationsSelector.setBounds(514, 190, 95, 20);
		nbIterationsSelector.setVisible(false);
		contentPane.add(nbIterationsSelector);
		
		nbIterationsLabel = new JLabel("Number of iterations :");
		nbIterationsLabel.setBounds(514, 171, 111, 14);
		nbIterationsLabel.setVisible(false);
		contentPane.add(nbIterationsLabel);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void displayCalibrationInformation(){
		if(isCalibrationMode()){
			this.nbIterationsLabel.setVisible(true);
			this.nbIterationsSelector.setVisible(true);
		} else {
			this.nbIterationsLabel.setVisible(false);
			this.nbIterationsSelector.setVisible(false);
		}
	}
	
	
	public void chooseMap(){
		String path = null;
		File f;
		JFileChooser mapChooser = new JFileChooser();
		if(mapChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			f = mapChooser.getSelectedFile();
			path = f.getPath();
			this.mapNameLabel.setText(f.getName());
			this.selectedMap = path;
		} else {
			this.mapNameLabel.setText("Invalid File");
		}
		
	}
	
	public String getSelectedMap(){
		return this.selectedMap;
	}
	
	public void generateMap(){
		MapGenerator mg = new MapGenerator();
		try {
			this.selectedMap = mg.generateMap((int) mapSizeSelector.getValue(), (double) startProbabilitySelector.getValue());
			String tmpPath = this.selectedMap;
			Path p1 = Paths.get(tmpPath); 
			this.mapNameLabel.setText(p1.getFileName().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void start(){
		if(selectedMap != null){
			List list = null;
			Parser parser = new Parser();
			try {
				list = (List) parser.parse(selectedMap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			GamePanel gp = new GamePanel();
			GameEngine ge = new GameEngine(list, gp, this);
			if(!isCalibrationMode())
				ge.getGamePanel().setVisible(true);
			ge.setAlpha((float) alphaSelector.getValue());
			ge.setNbAgents((int) agentSelector.getValue());
			ge.setFunctionUsed(getFunctionUsed());
			
			gameThread = new Thread(ge);
			gameThread.start();
			
			if(!isCalibrationMode()) {
				refreshThread = new Thread(ge.getGamePanel());
				refreshThread.start();
			}
		}
	}
	
	public boolean getFunctionUsed(){
		if(functionUsedCB.getSelectedIndex() == 0){ // AKA Lévy
			return true;
		}
		return false; // AKA Random
	}
	
	public boolean isCalibrationMode(){
		return this.chkboxCalibration.isSelected();
	}
	
	public Thread getRefreshThread() {
		return refreshThread;
	}
	
	public Thread getGameThread() {
		return gameThread;
	}
	
	public int getNbIterations(){
		return (int) this.nbIterationsSelector.getValue();
	}
}
