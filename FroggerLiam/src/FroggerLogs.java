import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
//not for self, can probably clean up the car stuff here, that is leftover from when i was doing colision

public class FroggerLogs extends Sprite implements Runnable {
	
	//2. new attributes
	private Boolean moving;
	
	private Thread t;

	//Link to Jlabel from GamePrep
	private JLabel froggerLogsLabel;
	private JButton startButton;
	
	//link to playerFrog and cars
	private PlayerFrog playerFrog;
	private FroggerCars froggerCars;
	
	//link to playerfrog and Cars Label
	private JLabel playerFrogLabel;
	private JLabel froggerCarsLabel;
	
	//getters/setters
	public Boolean getMoving() {
		return moving;
	}
	
	public void setMoving(Boolean move) {
		this.moving = move;
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


	public FroggerCars getFroggerCars() {
		return froggerCars;
	}

	public void setFroggerCars(FroggerCars froggerCars) {
		this.froggerCars = froggerCars;
	}

	public JLabel getFroggerCarsLabel() {
		return froggerCarsLabel;
	}

	public void setFroggerCarsLabel(JLabel froggerCarsLabel) {
		this.froggerCarsLabel = froggerCarsLabel;
	}

	//default constructor
	public FroggerLogs() {
		super(176, 112, 48, 16, "FroggerLog3.png");
		this.moving = false;
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
		while (this.moving) {
			//movement routine
			
			//get coordinates
			int x = this.spriteX;
			int y = this.spriteY;
			
			//step to left (boundary loop)
			x -= FGProperties.OBSTACLE_STEP;
			
			if (x + 48 < 0) {
				x = FGProperties.SCREEN_WIDTH;
			}
			
			//update Logs x and y
			this.setSpriteX(x);
			this.setSpriteY(y);
			
			//update the character2Label on the screen
			froggerLogsLabel.setLocation(this.spriteX, this.spriteY);
			
			this.display();
			
			//pause operations
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
				
		}
		
		startButton.setText("RESTART");
			
			
	}
	


		
}