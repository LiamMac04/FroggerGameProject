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

public class FroggerGame extends JFrame implements KeyListener, ActionListener {
	
		//character variables
	    private PlayerFrog playerFrog;
	    private FroggerCars froggerCars;
	    private FroggerLogs froggerLogs;
	    
	    private Sprite fBackground;
	    
		
		//GUI variables
		private Container content;
		private JLabel playerFrogLabel, froggerCarsLabel, froggerLogsLabel;
		private ImageIcon playerFrogImage, froggerCarsImage, froggerLogsImage;
		
		private JLabel fBLabel;
		private ImageIcon fBImage;
		
		//start button
		private JButton startButton;
		
		private Boolean gameStarted;
		
		
		//SET UP GUI
		public FroggerGame() {
			
			super("Frogger");
			
			playerFrog = new PlayerFrog();
			froggerCars = new FroggerCars();
			froggerLogs = new FroggerLogs();
			
			
			fBackground = new Sprite(0, 0, 224, 288, "FroggerBackgroundFullNew.png");
			
			//the default window does not support custom colors
			//use content pane to fill the content window
			content = getContentPane();
			
			setSize(FGProperties.SCREEN_WIDTH, FGProperties.SCREEN_HEIGHT);
			content.setBackground(Color.GRAY);
			setLayout(null);
			
			this.gameStarted = false;
			
			//playerFrog GUI setup => STATE
			playerFrog.setSpriteX(112);
			playerFrog.setSpriteY(256);
			playerFrog.setSpriteWidth(16);
			playerFrog.setSpriteHeight(16);
			playerFrog.setSpriteImage("FroggerUp.png");
			
			//playerFrog DISPLAY
			playerFrogLabel = new JLabel();
			playerFrogImage = new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + playerFrog.getSpriteImage()));
			playerFrogLabel.setIcon(playerFrogImage);
			playerFrogLabel.setSize(playerFrog.getSpriteWidth(), playerFrog.getSpriteHeight());
			playerFrogLabel.setLocation(playerFrog.getSpriteX(), playerFrog.getSpriteY());
			
			//froggerCars GUI setup => STATE
			froggerCars.setSpriteX(208);
			froggerCars.setSpriteY(224);
			froggerCars.setSpriteWidth(16);
			froggerCars.setSpriteHeight(16);
			froggerCars.setSpriteImage("FroggerCar1.png");
			froggerCars.setLeft(true);
			
			//froggerCars DISPLAY
			froggerCarsLabel = new JLabel();
			froggerCarsImage = new ImageIcon(getClass().getResource("Sprites/Cars/" + froggerCars.getSpriteImage()));
			froggerCarsLabel.setIcon(froggerCarsImage);
			froggerCarsLabel.setSize(froggerCars.getSpriteWidth(), froggerCars.getSpriteHeight());
			froggerCarsLabel.setLocation(froggerCars.getSpriteX(), froggerCars.getSpriteY());
			
			//froggerLogs GUI setup => STATE
			froggerLogs.setSpriteX(176);
			froggerLogs.setSpriteY(112);
			froggerLogs.setSpriteWidth(48);
			froggerLogs.setSpriteHeight(16);
			froggerLogs.setSpriteImage("FroggerLog3.png");
			
			//froggerLogs DISPLAY
			froggerLogsLabel = new JLabel();
			froggerLogsImage = new ImageIcon(getClass().getResource("Sprites/Logs/" + froggerLogs.getSpriteImage()));
			froggerLogsLabel.setIcon(froggerLogsImage);
			froggerLogsLabel.setSize(froggerLogs.getSpriteWidth(), froggerLogs.getSpriteHeight());
			froggerLogsLabel.setLocation(froggerLogs.getSpriteX(), froggerLogs.getSpriteY());
			
			
			//fBackground DISPLAY
			fBLabel = new JLabel();
			fBImage = new ImageIcon(getClass().getResource("Sprites/Environment/" + fBackground.getSpriteImage()));
			fBLabel.setIcon(fBImage);
			fBLabel.setSize(fBackground.getSpriteWidth(), fBackground.getSpriteHeight());
			fBLabel.setLocation(fBackground.getSpriteX(), fBackground.getSpriteY());
			
			//DO NOT SEND froggerCarsLabel INTO froggerCars WHEN ITS VALUE IS STILL NULL
			froggerCars.setFroggerCarsLabel(froggerCarsLabel);
			//DO NOT SEND playerFrog INTO froggerCars WHEN ITS VALUE IS STILL NULL
			froggerCars.setPlayerFrog(playerFrog);
			//DO NOT SEND playerFroglabel INTO froggerCars WHEN ITS VALUE IS STILL NULL
			froggerCars.setPlayerFrogLabel(playerFrogLabel);
			froggerCars.setFroggerLogs(froggerLogs);
			froggerCars.setFroggerLogsLabel(froggerLogsLabel);
			
			//DO NOT SEND froggerLogsLabel INTO froggerLogs WHEN ITS VALUE IS STILL NULL
			froggerLogs.setFroggerLogsLabel(froggerLogsLabel);
			//DO NOT SEND playerFrog INTO froggerLogs WHEN ITS VALUE IS STILL NULL
			froggerLogs.setPlayerFrog(playerFrog);
			//DO NOT SEND character1label INTO character2 WHEN ITS VALUE IS STILL NULL
			froggerLogs.setPlayerFrogLabel(playerFrogLabel);
			froggerLogs.setFroggerCars(froggerCars);
			froggerLogs.setFroggerCarsLabel(froggerCarsLabel);
			
			//DO NOT SEND playerFrogLabel INTO playerFrog WHEN ITS VALUE IS STILL NULL
			playerFrog.setPlayerFrogLabel(playerFrogLabel);
			//DO NOT SEND froggerCars INTO froggerLogs WHEN ITS VALUE IS STILL NULL
			playerFrog.setFroggerCars(froggerCars);
			//DO NOT SEND FroggerCarsLabel INTO playerFrog WHEN ITS VALUE IS STILL NULL
			playerFrog.setFroggerCarsLabel(froggerCarsLabel);
			playerFrog.setFroggerLogs(froggerLogs);
			playerFrog.setFroggerLogsLabel(froggerLogsLabel);
			
			startButton = new JButton("START");
			startButton.setSize(64, 16);
			startButton.setLocation(0, 272);
			startButton.setFocusable(false);
			
			//add actionListeners to the button
			startButton.addActionListener(this);
			
			//send button into froggerCars
			froggerCars.setStartButton(startButton);
			
			//send button into froggerLogs
			froggerLogs.setStartButton(startButton);
			
			//populate screen
			content.add(playerFrogLabel);
			content.add(froggerCarsLabel);
			content.add(froggerLogsLabel);
			content.add(startButton);
			content.add(fBLabel);
			
			
			content.addKeyListener(this);
			content.setFocusable(true);
			
			//character2.startThread();
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		
		public static void main(String[] args) {
			FroggerGame myFGame = new FroggerGame();
			myFGame.setVisible(true);
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}
		
		@Override
		public void keyPressed(KeyEvent e) {
			//System.out.println("Key Pressed");
			
			
			//get the current position
			int x = playerFrog.getSpriteX();
			int y = playerFrog.getSpriteY();
			
			//determine the direction based on 4 arrow keys
			// e.getKeyCode() <= get compile-based code for physical key
			// VK_DOWN, VK_UP, VK_RIGHT, VK_LEFT
			//update the x or y values based on direction
			
			/*
			 * playerFrog.setSpriteImage("FroggerUpJump.png");
				playerFrog.playerFrogImage.setIcon(
						new ImageIcon(getClass().getResource("images/" + playerFrog.getSpriteImage()))
				);
				y -= FGProperties.CHARACTER_STEP;
				playerFrog.setSpriteImage("FroggerUp.png"); 
			 */
			
			switch( e.getKeyCode() ) {
			case KeyEvent.VK_UP:
				this.playerFrog.setSpriteImage("FroggerUpJump.png");
				this.playerFrogLabel.setIcon(
						new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + playerFrog.getSpriteImage()))
				);
				if (y + playerFrog.getSpriteHeight() <= 32) break;
				y -= FGProperties.CHARACTER_STEP;
				break;
			case KeyEvent.VK_DOWN: 
				this.playerFrog.setSpriteImage("FroggerDownJump.png");
				this.playerFrogLabel.setIcon(
						new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + playerFrog.getSpriteImage()))
				);
				if (y > FGProperties.SCREEN_HEIGHT - 16) break;
				y += FGProperties.CHARACTER_STEP;
				break;
			case KeyEvent.VK_LEFT: 
				this.playerFrog.setSpriteImage("FroggerLeftJump.png");
				this.playerFrogLabel.setIcon(
						new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + playerFrog.getSpriteImage()))
				);
				if (x + playerFrog.getSpriteWidth() <= 0 + 16) x = FGProperties.SCREEN_WIDTH;
				x -= FGProperties.CHARACTER_STEP;
				break;
			case KeyEvent.VK_RIGHT: 
				this.playerFrog.setSpriteImage("FroggerRightJump.png");
				this.playerFrogLabel.setIcon(
						new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + playerFrog.getSpriteImage()))
				);
				if (x >= FGProperties.SCREEN_WIDTH - 16) x = -1 * playerFrog.getSpriteWidth();
				x += FGProperties.CHARACTER_STEP;
				break;
				default:
					System.out.println("Unknown key pressed");
			}
			
			//update character state
			playerFrog.setSpriteX(x);
			playerFrog.setSpriteY(y);
			
			//move character graphic
			playerFrogLabel.setLocation(playerFrog.getSpriteX(), playerFrog.getSpriteY());
			
			if (!playerFrog.getOverWater() ) {
				if (y <= 112) {
					playerFrog.startThread();
				}
			} else if ( playerFrog.getOverWater() ) {
				if (y > 112) {
					playerFrog.stopThread();
				} else if (y < 48) {
					playerFrog.stopThread();
				}
			}
			
			
			
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == startButton) {
				
				if ( !froggerCars.getMoving() & !froggerLogs.getMoving() ) {
					froggerCars.startThread();
					froggerLogs.startThread();
					playerFrog.setSpriteX(112);
					playerFrog.setSpriteY(256);
					playerFrogLabel.setLocation(playerFrog.getSpriteX(), playerFrog.getSpriteY());
					this.gameStarted = true;
					startButton.setText("STOP");
				} else {
					froggerCars.stopThread();
					froggerLogs.stopThread();
					this.gameStarted = false;
					startButton.setText("RESTART");
				}
				
			} else {
				System.out.println("Unknown command");
			}
			
		}
		
}