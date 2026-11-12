import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class FroggerCars extends Sprite implements Runnable {
	
	//2. new attributes
	private Boolean moving;
	
	// Make boolean to determine direction
	private Boolean left;
	private Boolean truck;
	private int speed;
	
	private Thread t;

	//Link to Jlabel from GamePrep
	private JLabel froggerCarsLabel;
	private JButton startButton;
	
	//link to playerFrog and logs
	private PlayerFrog playerFrog;
	
	//link to playerfrog and logs Label
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

	public Boolean getTruck() {
		return truck;
	}

	public void setTruck(Boolean truck) {
		this.truck = truck;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public JLabel getFroggerCarsLabel() {
		return froggerCarsLabel;
	}

	public void setFroggerCarsLabel(JLabel froggerCarsLabel) {
		this.froggerCarsLabel = froggerCarsLabel;
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
	public FroggerCars() {
		super(208, 224, 16, 16, "FroggerCar1.png");
		this.moving = false;
		this.left = true;
		this.truck = false;
		this.speed = FGProperties.OBSTACLE_SPEED_NORMAL;
		//this.character2Label = new JLabel();
		this.setFroggerCarsLabel(null);
		this.startButton = null;
	}
	
	public void display() {
		super.display();
		System.out.println("moving: " + moving);
	}
	
	public void startThread() {
		if (!this.moving) {
			this.moving = true;
			t = new Thread(this, "FroggerCarsMovement");
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
		
		//make graphic for character1 original (could have been swapped with red version)
		this.playerFrog.setSpriteImage("FroggerUp.png");
		this.playerFrogLabel.setIcon(
				new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + this.playerFrog.getSpriteImage()))
		);
		
		//start updating x, keep updating until something sets this.moving to false
		while (this.moving & !playerFrog.isFrogActive() ) { //added a ! to test logs easier DONT FORGET TO REMOVE
			//movement routine
			
			//get coordinates
			int x = this.spriteX;
			int y = this.spriteY;
			
			//Determine direction
			if ( left ) {
				x -= FGProperties.OBSTACLE_STEP;
			
				if (!truck) {
					if (x < 0 - 16) { x = FGProperties.SCREEN_WIDTH; }
				} else {
					if (x < 0 - 48) { x = FGProperties.SCREEN_WIDTH; }
					
				}
				
			} else if ( !left ) {
				x += FGProperties.OBSTACLE_STEP;
				
				if (x > 224) {
					x = 0;
				}
			}
			
			
			//update cars x and y
			this.setSpriteX(x);
			this.setSpriteY(y);
			
			//update the character2Label on the screen
			froggerCarsLabel.setLocation(this.spriteX, this.spriteY);
			
			//detect collision with character1 <= need to define attribute (no NEW)
			this.detectCollision();
			
			//pause operations
			try {
				Thread.sleep(speed);
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
	
	private void detectCollision() {
		if ( this.r.intersects( this.playerFrog.getRectangle() ) ) {
			System.out.println("Game Over");
			
			
			playerFrog.setFrogActive(false);
			
			
			//make graphic for frog dead
			this.playerFrog.setSpriteImage("FroggerLose.png");
			this.playerFrogLabel.setIcon(
					new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + playerFrog.getSpriteImage()))
			);
		}
	}

		
}


