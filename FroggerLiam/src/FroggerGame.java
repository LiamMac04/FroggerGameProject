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
	    private FroggerCars froggerCars[];
	    private FroggerLogs froggerLogs[];
	    
	    private Sprite fBackground;
	    
		
		//GUI variables
		private Container content;
		private JLabel playerFrogLabel, froggerCarsLabel[], froggerLogsLabel[];
		private ImageIcon playerFrogImage, froggerCarsImage[], froggerLogsImage[];
		
		private JLabel fBLabel;
		private ImageIcon fBImage;
		
		//start button
		private JButton startButton;
		
		private int carStartPosition[];
		private int logStartPosition[];
		
		
		
		
		//SET UP GUI
		public FroggerGame() {
			
			super("Frogger");
			
			playerFrog = new PlayerFrog();
			
			froggerCars = new FroggerCars[5];
			froggerCarsLabel = new JLabel[5];
			froggerCarsImage = new ImageIcon[5];
					
			froggerLogs = new FroggerLogs[5];
			froggerLogsLabel = new JLabel[5];
			froggerLogsImage = new ImageIcon[5];
			
			carStartPosition = new int[5];
			logStartPosition = new int[5];
			
			for (int i = 0; i < 5; i++) {
				froggerCars[i] = new FroggerCars();
				//idea for truck if i!= 4 do 16 by 16 for all. split left and right maybe?
				froggerLogs[i] = new FroggerLogs();
				
				if (i == 0 || i == 2 || i == 4 ) {
					froggerCars[i].setSpriteX(208);
					froggerLogs[i].setSpriteX(-48);
				} else if (i == 1 || i == 3) {
					froggerCars[i].setSpriteX(0);
					froggerLogs[i].setSpriteX(224);
				}
				
				froggerLogs[i].setSpriteWidth(48);
				froggerLogs[i].setSpriteHeight(16);
				
				if (i < 4) {
					froggerCars[i].setSpriteWidth(16);
					froggerCars[i].setSpriteHeight(16);
					froggerCars[i].setTruck(false);
					
					if ( i == 0 ) {
						froggerCars[i].setSpriteY(224);
						froggerCars[i].setSpriteImage("FroggerCar1.png");
						froggerCars[i].setLeft(true);
						froggerCars[i].setSpeed(FGProperties.OBSTACLE_SPEED_NORMAL);
						
						froggerLogs[i].setSpriteY(112);
						froggerLogs[i].setSpriteImage("FroggerLog3.png");
						froggerLogs[i].setLeft(false);
						froggerLogs[i].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
					} else if ( i == 1 ) {
						froggerCars[i].setSpriteY(208);
						froggerCars[i].setSpriteImage("FroggerCar3.png");
						froggerCars[i].setLeft(false);
						froggerCars[i].setSpeed(FGProperties.OBSTACLE_SPEED_FAST);
						
						froggerLogs[i].setSpriteY(96);
						froggerLogs[i].setSpriteImage("FroggerLog3.png");
						froggerLogs[i].setLeft(true);
						froggerLogs[i].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
					} else if ( i == 2 ) {
						froggerCars[i].setSpriteY(192);
						froggerCars[i].setSpriteImage("FroggerCar4.png");
						froggerCars[i].setLeft(true);
						froggerCars[i].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
						
						froggerLogs[i].setSpriteY(80);
						froggerLogs[i].setSpriteImage("FroggerLog3.png");
						froggerLogs[i].setLeft(false);
						froggerLogs[i].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
					} else if ( i == 3 ) {
						froggerCars[i].setSpriteY(176);
						froggerCars[i].setSpriteImage("FroggerCar2.png");
						froggerCars[i].setLeft(false);
						froggerCars[i].setSpeed(FGProperties.OBSTACLE_SPEED_FASTER);
						
						froggerLogs[i].setSpriteY(64);
						froggerLogs[i].setSpriteImage("FroggerLog3.png");
						froggerLogs[i].setLeft(true);
						froggerLogs[i].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
					}
				} else if ( i == 4 ) {
					froggerCars[i].setSpriteY(160);
					froggerCars[i].setSpriteWidth(32);
					froggerCars[i].setSpriteHeight(16);
					froggerCars[i].setSpriteImage("FroggerTruck.png");
					froggerCars[i].setLeft(true);
					froggerCars[i].setTruck(true);
					froggerCars[i].setSpeed(FGProperties.OBSTACLE_SPEED_NORMAL);
					
					froggerLogs[i].setSpriteY(48);
					froggerLogs[i].setSpriteImage("FroggerLog3.png");
					froggerLogs[i].setLeft(false);
					froggerLogs[i].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
				}
				
				froggerCarsLabel[i] = new JLabel();
				froggerCarsImage[i] = new ImageIcon(getClass().getResource("Sprites/Cars/" + froggerCars[i].getSpriteImage()));
				froggerCarsLabel[i].setIcon(froggerCarsImage[i]);
				froggerCarsLabel[i].setSize(froggerCars[i].getSpriteWidth(), froggerCars[i].getSpriteHeight());
				froggerCarsLabel[i].setLocation(froggerCars[i].getSpriteX(), froggerCars[i].getSpriteY());
				
				froggerLogsLabel[i] = new JLabel();
				froggerLogsImage[i] = new ImageIcon(getClass().getResource("Sprites/Logs/" + froggerLogs[i].getSpriteImage()));
				froggerLogsLabel[i].setIcon(froggerLogsImage[i]);
				froggerLogsLabel[i].setSize(froggerLogs[i].getSpriteWidth(), froggerLogs[i].getSpriteHeight());
				froggerLogsLabel[i].setLocation(froggerLogs[i].getSpriteX(), froggerLogs[i].getSpriteY());
				
				carStartPosition[i] = froggerCars[i].getSpriteX();
				logStartPosition[i] = froggerLogs[i].getSpriteX();
				
			}
			
			fBackground = new Sprite(0, 0, 224, 288, "FroggerBackgroundFullNew.png");
			
			//the default window does not support custom colors
			//use content pane to fill the content window
			content = getContentPane();
			
			setSize(FGProperties.SCREEN_WIDTH, FGProperties.SCREEN_HEIGHT);
			content.setBackground(Color.GRAY);
			setLayout(null);
			
			
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
			
			
			
			//fBackground DISPLAY
			fBLabel = new JLabel();
			fBImage = new ImageIcon(getClass().getResource("Sprites/Environment/" + fBackground.getSpriteImage()));
			fBLabel.setIcon(fBImage);
			fBLabel.setSize(fBackground.getSpriteWidth(), fBackground.getSpriteHeight());
			fBLabel.setLocation(fBackground.getSpriteX(), fBackground.getSpriteY());
			
			for (int i = 0; i < 5; i++) {
				//DO NOT SEND froggerCarsLabel INTO froggerCars WHEN ITS VALUE IS STILL NULL
				froggerCars[i].setFroggerCarsLabel(froggerCarsLabel[i]);
				froggerCars[i].setPlayerFrog(playerFrog);
				froggerCars[i].setPlayerFrogLabel(playerFrogLabel);
				playerFrog.setFroggerCars(froggerCars[i]);
				playerFrog.setFroggerCarsLabel(froggerCarsLabel[i]);
				
				froggerLogs[i].setFroggerLogsLabel(froggerLogsLabel[i]);
				froggerLogs[i].setPlayerFrog(playerFrog);
				froggerLogs[i].setPlayerFrogLabel(playerFrogLabel);
				playerFrog.setFroggerLogs(froggerLogs[i]);
				playerFrog.setFroggerLogsLabel(froggerLogsLabel[i]);
	
			}
		
			playerFrog.setPlayerFrogLabel(playerFrogLabel);
			
			startButton = new JButton("START");
			startButton.setSize(64, 16);
			startButton.setLocation(0, 272);
			startButton.setFocusable(false);
			
			//add actionListeners to the button
			startButton.addActionListener(this);
			
			for (int i = 0; i < 5; i++) {
				//send button into froggerCars
				froggerCars[i].setStartButton(startButton);
				froggerLogs[i].setStartButton(startButton);
			}
			
			//populate screen
			content.add(playerFrogLabel);
			
			
			for (int i = 0; i < 5; i++) {
				content.add(froggerCarsLabel[i]);
				content.add(froggerLogsLabel[i]);
			}
			
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
			
			if (playerFrog.isFrogActive()) {
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
				
				for (int i = 0; i <= 4; i++) {
					if (playerFrog.getSpriteY() == froggerLogs[i].getSpriteY() ) {
						playerFrog.setLogLeft(froggerLogs[i].getLeft());
						playerFrog.setLogSpeed(froggerLogs[i].getSpeed());
					}
				}
				
				if (!playerFrog.getOverWater() ) {
					if (y <= 112) {
						playerFrog.startWater();
					}
				} else if ( playerFrog.getOverWater() ) {
					if (y > 112) {
						playerFrog.stopWater();
					} else if (y < 48) {
						playerFrog.stopWater();
					}
				}
				
				
			}
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == startButton) {
				
				if ( !playerFrog.isFrogActive()) {
					
					playerFrog.setFrogActive(true);
					
					playerFrog.setSpriteX(112);
					playerFrog.setSpriteY(256);
					
					playerFrogLabel.setLocation(playerFrog.getSpriteX(), playerFrog.getSpriteY());
					
					for (int i = 0; i < 5; i++) {
						froggerCars[i].setSpriteX(carStartPosition[i]);
						froggerLogs[i].setSpriteX(logStartPosition[i]);
						froggerCarsLabel[i].setLocation(froggerCars[i].getSpriteX(), froggerCars[i].getSpriteY());
						froggerLogsLabel[i].setLocation(froggerLogs[i].getSpriteX(), froggerLogs[i].getSpriteY());
						
						froggerCars[i].startThread();
						froggerLogs[i].startThread();
					}
					
					startButton.setText("STOP");
				} else {
					playerFrog.setFrogActive(false);
					
					
					startButton.setText("RESTART");
				}
				
			} else {
				System.out.println("Unknown command");
			}
			
		}
		
		
}