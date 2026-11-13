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
	    private FroggerCars froggerCars[][];
	    private FroggerLogs froggerLogs[][];
	    
	    
	    private Sprite fBackground;
	    
		
		//GUI variables
		private Container content;
		private JLabel playerFrogLabel, froggerCarsLabel[][], froggerLogsLabel[][];
		private ImageIcon playerFrogImage, froggerCarsImage[][], froggerLogsImage[][];
		
		private JLabel fBLabel;
		private ImageIcon fBImage;
		
		//start button
		private JButton startButton;
		
		private int carStartPosition[][];
		private int logStartPosition[][];
		
		private int carsInRow[];
		
		private Text score[];
		private JLabel scoreLabel[];
		private ImageIcon scoreImage[];
		
		private Text playerName[];
		private JLabel playerNameLabel[];
		private ImageIcon playerNameImage[];
		
		private String fullScore;
		
		private String fullName = "";
		
		private boolean nameEntered;
		
		private JButton nameButton;
		
		
		
		
		//SET UP GUI
		public FroggerGame() {
			
			super("Frogger");
			
			playerFrog = new PlayerFrog();
			
			froggerCars = new FroggerCars[5][5];
			froggerCarsLabel = new JLabel[5][5];
			froggerCarsImage = new ImageIcon[5][5];
					
			froggerLogs = new FroggerLogs[5][5];
			froggerLogsLabel = new JLabel[5][5];
			froggerLogsImage = new ImageIcon[5][5];
			
			carStartPosition = new int[5][5];
			logStartPosition = new int[5][5];
			carsInRow = new int[5];
			
			score = new Text[6];
			scoreLabel = new JLabel[6];
			scoreImage = new ImageIcon[6];
			
			for (int i = 0; i < 6; i++) {
				
				score[i] = new Text();
				
				score[i].setSpriteX(168 + (8 * i));
				score[i].setSpriteY(4);
				score[i].setSpriteWidth(8);
				score[i].setSpriteHeight(8);
				score[i].setSpriteImage("FroggerRedText0.png");
				
				scoreLabel[i] = new JLabel();
				scoreImage[i] = new ImageIcon(getClass().getResource("Sprites/UIElements/RedText/" + score[i].getSpriteImage()));
				scoreLabel[i].setIcon(scoreImage[i]);
				scoreLabel[i].setSize(score[i].getSpriteWidth(), score[i].getSpriteHeight());
				scoreLabel[i].setLocation(score[i].getSpriteX(), score[i].getSpriteY());
				
				score[i].setTextLabel(scoreLabel[i]);
				
			}
			
			playerName = new Text[11];
			playerNameLabel = new JLabel[11];
			playerNameImage = new ImageIcon[11];
			
			for (int i = 0; i < 11; i++) {
				playerName[i] = new Text();
				
				playerName[i].setSpriteX(36 + (8 * i));
				playerName[i].setSpriteY(4);
				playerName[i].setSpriteWidth(8);
				playerName[i].setSpriteHeight(8);
				playerName[i].setSpriteImage("FroggerRedText_Underscore.png");
				
				playerNameLabel[i] = new JLabel();
				playerNameImage[i] = new ImageIcon(getClass().getResource("Sprites/UIElements/RedText/" + playerName[i].getSpriteImage()));
				playerNameLabel[i].setIcon(playerNameImage[i]);
				playerNameLabel[i].setSize(playerName[i].getSpriteWidth(), playerName[i].getSpriteHeight());
				playerNameLabel[i].setLocation(playerName[i].getSpriteX(), playerName[i].getSpriteY());
				
				playerName[i].setTextLabel(playerNameLabel[i]);
				
			}
			
			
			for (int i = 0; i < 5; i++) {
				if (i == 0) {
					carsInRow[i] = 4;
				} else if (i == 1) {
					carsInRow[i] = 3;
				} else if (i == 2) {
					carsInRow[i] = 4;
				} else if (i == 3) {
					carsInRow[i] = 1;
				} else if (i == 4) {
					carsInRow[i] = 3;
				}
				
				for (int j = 0; j < carsInRow[i]; j++) {
					
					froggerCars[i][j] = new FroggerCars();
					
					if (i == 0 || i == 2 || i == 4) {
						froggerCars[i][j].setLeft(true);
					} else if (i == 1 || i == 3) {
						froggerCars[i][j].setLeft(false);
					}
					
					froggerCars[i][j].setSpriteY(224 - (16 * i));
					
					if (i < 4) {
						froggerCars[i][j].setSpriteWidth(16);
						froggerCars[i][j].setSpriteHeight(16);
						froggerCars[i][j].setTruck(false);
						
						if ( i == 0 ) {
							froggerCars[i][j].setSpacing((16 + 64));
							froggerCars[i][j].setSpriteX(208 - (j * froggerCars[i][j].getSpacing()));
							froggerCars[i][j].setSpriteImage("FroggerCar1.png");
							froggerCars[i][j].setSpeed(FGProperties.OBSTACLE_SPEED_NORMAL);
						} else if ( i == 1 ) {
							froggerCars[i][j].setSpacing((16 + 96));
							froggerCars[i][j].setSpriteX(j * froggerCars[i][j].getSpacing());
							froggerCars[i][j].setSpriteImage("FroggerCar3.png");
							froggerCars[i][j].setSpeed(FGProperties.OBSTACLE_SPEED_FAST);
						} else if ( i == 2 ) {
							froggerCars[i][j].setSpacing((16 + 64));
							froggerCars[i][j].setSpriteX(208 - (j * froggerCars[i][j].getSpacing()));
							froggerCars[i][j].setSpriteImage("FroggerCar4.png");
							froggerCars[i][j].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
						} else if ( i == 3 ) {
							froggerCars[i][j].setSpacing(0);
							froggerCars[i][j].setSpriteX(0);
							froggerCars[i][j].setSpriteImage("FroggerCar2.png");
							froggerCars[i][j].setSpeed(FGProperties.OBSTACLE_SPEED_FASTER);
						}
					} else if ( i == 4 ) {
						froggerCars[i][j].setSpacing((48 + 48));
						froggerCars[i][j].setSpriteX(224 - (j * froggerCars[i][j].getSpacing()));
						froggerCars[i][j].setSpriteWidth(32);
						froggerCars[i][j].setSpriteHeight(16);
						froggerCars[i][j].setSpriteImage("FroggerTruck.png");
						froggerCars[i][j].setTruck(true);
						froggerCars[i][j].setSpeed(FGProperties.OBSTACLE_SPEED_NORMAL);
					}
					
					froggerCarsLabel[i][j] = new JLabel();
					froggerCarsImage[i][j] = new ImageIcon(getClass().getResource("Sprites/Cars/" + froggerCars[i][j].getSpriteImage()));
					froggerCarsLabel[i][j].setIcon(froggerCarsImage[i][j]);
					froggerCarsLabel[i][j].setSize(froggerCars[i][j].getSpriteWidth(), froggerCars[i][j].getSpriteHeight());
					froggerCarsLabel[i][j].setLocation(froggerCars[i][j].getSpriteX(), froggerCars[i][j].getSpriteY());
					
					carStartPosition[i][j] = froggerCars[i][j].getSpriteX();
					
					
				}
				
				for (int j = 0; j < 3; j++) { //logs
					
					froggerLogs[i][j] = new FroggerLogs();
					
					froggerLogs[i][j].setSpacing((48 + 48));
					
					if (i == 0 || i == 2 || i == 4 ) {
						froggerLogs[i][j].setSpriteX(0 + (j * froggerLogs[i][j].getSpacing()));	
					} else if (i == 1 || i == 3) {
						froggerLogs[i][j].setSpriteX(176 - (j * froggerLogs[i][j].getSpacing()));
					}
					
					froggerLogs[i][j].setSpriteY(112 - (16 * i));
					
					froggerLogs[i][j].setSpriteWidth(48);
					froggerLogs[i][j].setSpriteHeight(16);
					
					froggerLogs[i][j].setSpriteImage("FroggerLog3.png");
					
					if ( i == 0 ) {
						froggerLogs[i][j].setLeft(false);
						froggerLogs[i][j].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
					} else if ( i == 1 ) {
						froggerLogs[i][j].setLeft(true);
						froggerLogs[i][j].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
					} else if ( i == 2 ) {
						froggerLogs[i][j].setLeft(false);
						froggerLogs[i][j].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
					} else if ( i == 3 ) {
						froggerLogs[i][j].setLeft(true);
						froggerLogs[i][j].setSpeed(FGProperties.OBSTACLE_SPEED_NORMAL);
					} else if ( i == 4 ) {
						froggerLogs[i][j].setLeft(false);
						froggerLogs[i][j].setSpeed(FGProperties.OBSTACLE_SPEED_SLOW);
					}
					
					froggerLogsLabel[i][j] = new JLabel();
					froggerLogsImage[i][j] = new ImageIcon(getClass().getResource("Sprites/Logs/" + froggerLogs[i][j].getSpriteImage()));
					froggerLogsLabel[i][j].setIcon(froggerLogsImage[i][j]);
					froggerLogsLabel[i][j].setSize(froggerLogs[i][j].getSpriteWidth(), froggerLogs[i][j].getSpriteHeight());
					froggerLogsLabel[i][j].setLocation(froggerLogs[i][j].getSpriteX(), froggerLogs[i][j].getSpriteY());
					
					logStartPosition[i][j] = froggerLogs[i][j].getSpriteX();
					
				}
				
				
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
				
				for (int j = 0; j < carsInRow[i]; j++ ) {
					froggerCars[i][j].setFroggerCarsLabel(froggerCarsLabel[i][j]);
					froggerCars[i][j].setPlayerFrog(playerFrog);
					froggerCars[i][j].setPlayerFrogLabel(playerFrogLabel);
					
					playerFrog.setFroggerCars(froggerCars[i][j]);
					playerFrog.setFroggerCarsLabel(froggerCarsLabel[i][j]);
				}
				
				for (int j = 0; j < 3; j++ ) {
					froggerLogs[i][j].setFroggerLogsLabel(froggerLogsLabel[i][j]);
					froggerLogs[i][j].setPlayerFrog(playerFrog);
					froggerLogs[i][j].setPlayerFrogLabel(playerFrogLabel);
					
					playerFrog.setFroggerLogs(froggerLogs[i][j]);
					playerFrog.setFroggerLogsLabel(froggerLogsLabel[i][j]);	
				}
	
			}
		
			playerFrog.setPlayerFrogLabel(playerFrogLabel);
			
			startButton = new JButton("START");
			startButton.setSize(64, 16);
			startButton.setLocation(0, 272);
			startButton.setFocusable(false);
			
			//add actionListeners to the button
			startButton.addActionListener(this);
			
			nameButton = new JButton("ENTER NAME");
			nameButton.setSize(96, 16);
			nameButton.setLocation(72, 272);
			nameButton.setFocusable(false);
			
			//add actionListeners to the button
			nameButton.addActionListener(this);
			
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < carsInRow[i]; j++ ) {
					froggerCars[i][j].setStartButton(startButton);
				}
				
				for (int j = 0; j < 3; j++ ) {
					froggerLogs[i][j].setStartButton(startButton);
				}
			}
			
			//populate screen
			content.add(playerFrogLabel);
			
			
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < carsInRow[i]; j++ ) {
					content.add(froggerCarsLabel[i][j]);
				}
				
				for (int j = 0; j < 3; j++ ) {
					content.add(froggerLogsLabel[i][j]);
				}
			}
			
			for (int i = 0; i < 6; i++) {
				content.add(scoreLabel[i]);
			}
			
			for (int i = 0; i < 11; i++) {
				content.add(playerNameLabel[i]);
			}
			
			
			
			
			content.add(startButton);
			content.add(nameButton);
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
		
		int nameCount = 0;
		
		@Override
		public void keyPressed(KeyEvent e) {
			//System.out.println("Key Pressed");
			if (!nameEntered) {
				
				boolean validText = false;
				
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					fullName = "";
					nameCount = 0;
					
					for (int i = 0; i < 11; i++) {
						playerName[i].setDigitValue("_UnderScore");
					}	
				} else if (nameCount == 11 && !(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
					
				} else if (e.getKeyCode() == KeyEvent.VK_SPACE && !(nameCount == 11)) {
					fullName = fullName + e.getKeyChar();
					playerName[nameCount].setDigitValue("_UnderScore");
					nameCount = nameCount + 1;
				} else {
					
					for (int i = 48; i < 91; i++) {
						if (e.getKeyCode() == i) {
							validText = true;
							System.out.println(e.getKeyCode());
							break;
						} else {
							validText = false;
						}
						
						if (i == 57) {
							i = i + 7;
						}
					}
					
					if (validText == true) {
						fullName = fullName + e.getKeyChar();
						playerName[nameCount].setDigitValue("" + e.getKeyChar());
						nameCount = nameCount + 1;
					}
					
				}
					
			}
			
			if (playerFrog.isFrogActive() && (nameEntered == true)) {
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
				playerFrog.setScore(playerFrog.getScore() + 1);
				this.scoreChange(playerFrog.getScore());
				
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
					if (x + playerFrog.getSpriteWidth() <= 16) x = FGProperties.SCREEN_WIDTH;
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
					if (y <= 112 && y > 42) {
						
						playerFrog.setOverWater(true);
						
						Boolean onLog = false;
						
						for (int i = 0; i < 5; i++) {
							
							for (int j = 0; j < 3; j++) {
								if (froggerLogs[i][j].getRectangle().intersects(this.playerFrog.getRectangle())) {
									onLog = true;
									froggerLogs[i][j].setHasFrog(true);
								} else {
									froggerLogs[i][j].setHasFrog(false);
								}
							}
						}
						
						if (onLog == false) {
							System.out.println("Game Over");
						
							playerFrog.setScore(playerFrog.getScore() - 50);
							if (playerFrog.getScore() < 0){
								playerFrog.setScore(0);	
							}
							
							System.out.println(playerFrog.getScore());
							
							this.scoreChange(playerFrog.getScore());
							
							playerFrog.setFrogActive(false);
							playerFrog.setOverWater(false);
							
							
							
							//make graphic for frog dead
							this.playerFrog.setSpriteImage("FroggerLose.png");
							this.playerFrogLabel.setIcon(
									new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + playerFrog.getSpriteImage()))
							);
						}
	
						
					}
				} else if ( playerFrog.getOverWater() ) {
					if (y > 112) {
						playerFrog.setOverWater(false);
						for (int i = 0; i < 5; i++) {
							for (int j = 0; j < 3; j++) {
								froggerLogs[i][j].setHasFrog(false);
							}
						}
					} else if (y < 48) {
						playerFrog.setOverWater(false);
						for (int i = 0; i < 5; i++) {
							for (int j = 0; j < 3; j++) {
								froggerLogs[i][j].setHasFrog(false);
							}
						}
					} else if (y <= 112) {
						Boolean onLog = false;
						
						for (int i = 0; i < 5; i++) {
							
							for (int j = 0; j < 3; j++) {
								if (froggerLogs[i][j].getRectangle().intersects(this.playerFrog.getRectangle())) {
									onLog = true;
									froggerLogs[i][j].setHasFrog(true);
								} else {
									froggerLogs[i][j].setHasFrog(false);
								}
							}
							
						}
						
						if (onLog == false) {
							System.out.println("Game Over");
							
							playerFrog.setScore(playerFrog.getScore() - 50);
							if (playerFrog.getScore() < 0){
								playerFrog.setScore(0);	
							}
							
							System.out.println(playerFrog.getScore());
							
							this.scoreChange(playerFrog.getScore());
						
						
							playerFrog.setFrogActive(false);
							playerFrog.setOverWater(false);
							
							//make graphic for frog dead
							this.playerFrog.setSpriteImage("FroggerLose.png");
							this.playerFrogLabel.setIcon(
									new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + playerFrog.getSpriteImage()))
							);
						}
					}
					
				}
				
				if (y <= 42) {
					System.out.println("You Win");
					
					playerFrog.setScore(playerFrog.getScore() + 50);
					
					this.scoreChange(playerFrog.getScore());
					
					playerFrog.setFrogActive(false);
					
					this.playerFrog.setSpriteImage("FroggerInverted.png");
					this.playerFrogLabel.setIcon(
							new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + playerFrog.getSpriteImage()))
					);
					
					System.out.println(playerFrog.getScore());
				
				}
				
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < carsInRow[i]; j++ ) {
						if (froggerCars[i][j].isGotHit()) {
							this.scoreChange(playerFrog.getScore());
						}
					}
					
				}
				
			}
				
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == startButton && nameEntered == true) {
				
				this.scoreChange(playerFrog.getScore());
				
				if ( !playerFrog.isFrogActive()) {
					
					playerFrog.setFrogActive(true);
					
					playerFrog.setSpriteX(112);
					playerFrog.setSpriteY(256);
					
					playerFrogLabel.setLocation(playerFrog.getSpriteX(), playerFrog.getSpriteY());
					
					for (int i = 0; i < 5; i++) {
						
						for (int j = 0; j < carsInRow[i]; j++ ) {
							froggerCars[i][j].setSpriteX(carStartPosition[i][j]);
							froggerCarsLabel[i][j].setLocation(froggerCars[i][j].getSpriteX(), froggerCars[i][j].getSpriteY());
							
							froggerCars[i][j].startThread();
						}
						
						for (int j = 0; j < 3; j++ ) {
							froggerLogs[i][j].setSpriteX(logStartPosition[i][j]);
							froggerLogsLabel[i][j].setLocation(froggerLogs[i][j].getSpriteX(), froggerLogs[i][j].getSpriteY());
							
							froggerLogs[i][j].startThread();
						}
					}
					
					playerFrog.setSpriteX(112);
					playerFrog.setSpriteY(256);
					
					
					
					playerFrogLabel.setLocation(playerFrog.getSpriteX(), playerFrog.getSpriteY());
					
					startButton.setText("STOP");
				} else {
					playerFrog.setFrogActive(false);
					
					
					startButton.setText("RESTART");
				}
				
			} else if (e.getSource() == nameButton) {
				nameEntered = true;
				
				System.out.println(fullName);
				
				content.remove(nameButton);
			} else {
				System.out.println("Unknown command");
			}
			
		}
		
		public void scoreChange(int scoreInt) {
			
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

				score[i].setDigitValue(thisDigit);
			}
			
		}
		
		
}