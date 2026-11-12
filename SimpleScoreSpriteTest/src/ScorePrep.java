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
	private ScoreBoard scoreBoard[];
	
	//GUI variables
	private Container content;
	private JLabel scoreBoardLabel[];
	private ImageIcon scoreBoardImage[];
	
	//buttons to control game
	private JButton addButton, subtractButton;
	
	private int scoreInt = 0;
	
	private String fullScore;
	
	//SET UP GUI
	public ScorePrep() {
		super("Score Test");
		
		scoreBoard = new ScoreBoard[6];
		scoreBoardLabel = new JLabel[6];
		scoreBoardImage = new ImageIcon[6];
		
		//the default window does not support custom colors
		//use content pane to fill the content window
		content = getContentPane();
		
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		content.setBackground(Color.GRAY);
		setLayout(null);
		
		for (int i = 0; i < 6; i++) {
			
			scoreBoard[i] = new ScoreBoard();
			
			scoreBoard[i].setSpriteX(360 + (8 * i));
			scoreBoard[i].setSpriteY(300);
			scoreBoard[i].setSpriteWidth(8);
			scoreBoard[i].setSpriteHeight(8);
			scoreBoard[i].setSpriteImage("FroggerRedText0.png");
			
			scoreBoardLabel[i] = new JLabel();
			scoreBoardImage[i] = new ImageIcon(getClass().getResource("Sprites/UIElements/RedText/" + scoreBoard[i].getSpriteImage()));
			scoreBoardLabel[i].setIcon(scoreBoardImage[i]);
			scoreBoardLabel[i].setSize(scoreBoard[i].getSpriteWidth(), scoreBoard[i].getSpriteHeight());
			scoreBoardLabel[i].setLocation(scoreBoard[i].getSpriteX(), scoreBoard[i].getSpriteY());
			
			scoreBoard[i].setScoreBoardLabel(scoreBoardLabel[i]);
			
			content.add(scoreBoardLabel[i]);
		}
		
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
		
		
		//populate screen
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
		/*
		if (e.getSource() == addButton) {
			scoreBoard.setDigitValue(scoreBoard.getDigitValue() + 1);
		} else if (e.getSource() == subtractButton) {
			scoreBoard.setDigitValue(scoreBoard.getDigitValue() - 1);
		} else {
			System.out.println("Unknown command");
		}
		*/
		if (e.getSource() == addButton) {
			scoreInt = scoreInt + 1;
		} else if (e.getSource() == subtractButton) {
			scoreInt = scoreInt - 1;
		} else {
			System.out.println("Unknown command");
		}
		
		fullScore = "" + scoreInt;
		int usedDigits = fullScore.length();
		fullScore = "";
		
		for (int i = 0; i < (6 - usedDigits); i++) {
			fullScore = fullScore + "0";
		}
		fullScore = fullScore + scoreInt;
		
		System.out.println(fullScore);
		
		for (int i = 0; i < 6; i++) {
			fullScore.substring(i, i + 1);
			String thisDigit = (fullScore.substring(i, i + 1));
			
			Integer.parseInt(fullScore);
			scoreBoard[i].setDigitValue(Integer.parseInt(thisDigit));
		}
		
	}
	
}

