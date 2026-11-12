import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class FroggerLogs extends Sprite implements Runnable {
	
	//2. new attributes
	private Boolean moving;
	private Boolean hasFrog;
	
	// Make boolean to determine direction
	private Boolean left;
	private int speed;
	
	private Thread t;

	//Link to Jlabel from GamePrep
	private JLabel froggerLogsLabel;
	private JButton startButton;
	
	//link to playerFrog and cars
	private PlayerFrog playerFrog;
	
	//link to playerfrog and Cars Label
	private JLabel playerFrogLabel;
	
	//getters/setters
	public Boolean getMoving() {
		return moving;
	}
	
	public void setMoving(Boolean move) {
		this.moving = move;
	}
	
	public Boolean getLeft() {
		return left;
	}

	public void setLeft(Boolean left) {
		this.left = left;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Boolean getHasFrog() {
		return hasFrog;
	}

	public void setHasFrog(Boolean hasFrog) {
		this.hasFrog = hasFrog;
	}

	public JLabel getFroggerLogsLabel() {
		return froggerLogsLabel;
	}

	public void setFroggerLogsLabel(JLabel froggerLogsLabel) {
		this.froggerLogsLabel = froggerLogsLabel;
	}
	
	public JButton getStartButton() {
		return startButton;
	}


	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public PlayerFrog getPlayerFrog() {
		return playerFrog;
	}


	public void setPlayerFrog(PlayerFrog playerFrog) {
		this.playerFrog = playerFrog;
	}


	public JLabel getPlayerFrogLabel() {
		return playerFrogLabel;
	}


	public void setPlayerFrogLabel(JLabel playerFrogLabel) {
		this.playerFrogLabel = playerFrogLabel;
	}


	//default constructor
	public FroggerLogs() {
		super(176, 112, 48, 16, "FroggerLog3.png");
		this.moving = false;
		this.left = true;
		//this.character2Label = new JLabel();
		this.setFroggerLogsLabel(null);
		this.startButton = null;
	}
	
	public void display() {
		super.display();
		System.out.println("moving: " + moving);
	}
	
	public void startThread() {
		if (!this.moving) {
			this.moving = true;
			t = new Thread(this, "FroggerLogsMovement");
			t.start();
			
		}
		
	}
	
	public void stopThread() {
		this.moving = false;
	}


	@Override
	public void run() {
		//character 2 moving on screen
		this.moving = true;
		startButton.setText("STOP");
		this.hasFrog = false;
		
		//make graphic for character2 original (could have been swapped with red version)
		this.setSpriteImage("FroggerLog3.png");
		this.froggerLogsLabel.setIcon(
				new ImageIcon(getClass().getResource("Sprites/Logs/" + this.getSpriteImage()))
		);
		
		//make graphic for character1 original (could have been swapped with red version)
		this.playerFrog.setSpriteImage("FroggerUp.png");
		this.playerFrogLabel.setIcon(
				new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + this.playerFrog.getSpriteImage()))
		);
		
		//start updating x, keep updating until something sets this.moving to false
		while (this.moving && playerFrog.isFrogActive()) {
			//movement routine
			
			//get coordinates
			int x = this.spriteX;
			int y = this.spriteY;
			
			
			//Determine direction
			if ( left ) {
				x -= FGProperties.OBSTACLE_STEP;
			
				if (x + 48 < 0) {
					x = FGProperties.SCREEN_WIDTH;
				}
			} else if ( !left ) {
				x += FGProperties.OBSTACLE_STEP;
				
				if (x > 224) {
					x = 0 - 48;
				}
			}
			
			//update Logs x and y
			this.setSpriteX(x);
			this.setSpriteY(y);
			
			//update the character2Label on the screen
			froggerLogsLabel.setLocation(this.spriteX, this.spriteY);
			
			if ( this.hasFrog) {
				
				int k = this.playerFrog.getSpriteX();
				
				if ( this.left ) {
					k -= FGProperties.OBSTACLE_STEP;
					
					if (k + 48 < 0) {
						k = FGProperties.SCREEN_WIDTH;
					}
				} else if (!this.left ){
					k += FGProperties.OBSTACLE_STEP;
					
					if (k > 224) {
						k = 0 - 48;
					}
				}
				
				this.playerFrog.setSpriteX(k);
				this.playerFrog.setSpriteY(this.playerFrog.spriteY);
				
				//move character graphic
				this.playerFrogLabel.setLocation(this.playerFrog.spriteX, this.playerFrog.spriteY);
			}
			
			//pause operations
			try {
				Thread.sleep(this.speed);
			} catch(InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
				
		}
		
		this.setMoving(false);
		this.stopThread();
		
		startButton.setText("RESTART");
			
			
	}
	


		
}