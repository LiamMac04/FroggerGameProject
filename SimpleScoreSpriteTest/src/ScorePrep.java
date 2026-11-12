import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScorePrep extends JFrame implements KeyListener, ActionListener {
	
	//character variables
	private ScoreBoard scoreBoard;
	
	//GUI variables
	private Container content;
	private JLabel scoreBoardLabel;
	private ImageIcon scoreBoardImage;
	
	//buttons to control game
	private JButton addButton, subtractButton;
	
	private int score = 0;
	
	//SET UP GUI
	public ScorePrep() {
		super("Score Test");
		
		scoreBoard = new ScoreBoard();
		
		//the default window does not support custom colors
		//use content pane to fill the content window
		content = getContentPane();
		
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		content.setBackground(Color.GRAY);
		setLayout(null);
		
		//character1 GUI setup => STATE
		scoreBoard.setSpriteX(400);
		scoreBoard.setSpriteY(300);
		scoreBoard.setSpriteWidth(8);
		scoreBoard.setSpriteHeight(8);
		scoreBoard.setSpriteImage("FroggerRedText0.png");
		
		//scoreBoard DISPLAY
		scoreBoardLabel = new JLabel();
		scoreBoardImage = new ImageIcon(getClass().getResource("Sprites/UIElements/RedText/" + scoreBoard.getSpriteImage()));
		scoreBoardLabel.setIcon(scoreBoardImage);
		scoreBoardLabel.setSize(scoreBoard.getSpriteWidth(), scoreBoard.getSpriteHeight());
		scoreBoardLabel.setLocation(scoreBoard.getSpriteX(), scoreBoard.getSpriteY());
		
		scoreBoard.setScoreBoardLabel(scoreBoardLabel);
		
		//set up buttons
		addButton = new JButton("Add 1");
		addButton.setSize(100, 100);
		addButton.setLocation(200, 400);
		addButton.setFocusable(false);
		
		subtractButton = new JButton("Subtract 1");
		subtractButton.setSize(100, 100);
		subtractButton.setLocation(400, 400);
		subtractButton.setFocusable(false);
		
		//add actionListeners to the buttons
		addButton.addActionListener(this);
		subtractButton.addActionListener(this);
		
		//send button into character2
		scoreBoard.setAddButton(addButton);
		scoreBoard.setSubtractButton(subtractButton);
		
		//populate screen
		content.add(scoreBoardLabel);
		content.add(addButton);
		content.add(subtractButton);
		
		content.addKeyListener(this);
		content.setFocusable(true);
		
		//character2.startThread();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		ScorePrep myGame = new ScorePrep();
		myGame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == addButton) {
			scoreBoard.setDigitValue(scoreBoard.getDigitValue() + 1);
		} else if (e.getSource() == subtractButton) {
			scoreBoard.setDigitValue(scoreBoard.getDigitValue() - 1);
		} else {
			System.out.println("Unknown command");
		}
		
	}
	
}

