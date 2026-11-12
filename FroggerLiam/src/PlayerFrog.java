import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PlayerFrog extends Sprite implements Runnable {
	
	private Boolean overWater, gameActive;
	
	private Thread w, c;

	//Link to Jlabel from GamePrep
	private JLabel playerFrogLabel;
	private JButton startButton;
	
	//link to cars and logs
	private FroggerCars froggerCars;
	private FroggerLogs froggerLogs;
	
	//link to cars and logs Label
	private JLabel froggerCarsLabel;
	private JLabel froggerLogsLabel;
	
	private boolean frogActive;
	
	private boolean logLeft;
	private int logSpeed;
	
	public boolean isFrogActive() {
		return frogActive;
	}

	public void setFrogActive(boolean frogActive) {
		this.frogActive = frogActive;
	}

	public Boolean getOverWater() {
		return overWater;
	}

	public void setOverWater(Boolean overWater) {
		this.overWater = overWater;
	}

	public boolean isLogLeft() {
		return logLeft;
	}

	public void setLogLeft(boolean logLeft) {
		this.logLeft = logLeft;
	}

	public int getLogSpeed() {
		return logSpeed;
	}

	public void setLogSpeed(int logSpeed) {
		this.logSpeed = logSpeed;
	}

	public JLabel getPlayerFrogLabel() {
		return playerFrogLabel;
	}

	public void setPlayerFrogLabel(JLabel playerFrogLabel) {
		this.playerFrogLabel = playerFrogLabel;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public FroggerCars getFroggerCars() {
		return froggerCars;
	}

	public void setFroggerCars(FroggerCars froggerCars) {
		this.froggerCars = froggerCars;
	}

	public FroggerLogs getFroggerLogs() {
		return froggerLogs;
	}

	public void setFroggerLogs(FroggerLogs froggerLogs) {
		this.froggerLogs = froggerLogs;
	}

	public JLabel getFroggerCarsLabel() {
		return froggerCarsLabel;
	}

	public void setFroggerCarsLabel(JLabel froggerCarsLabel) {
		this.froggerCarsLabel = froggerCarsLabel;
	}

	public JLabel getFroggerLogsLabel() {
		return froggerLogsLabel;
	}

	public void setFroggerLogsLabel(JLabel froggerLogsLabel) {
		this.froggerLogsLabel = froggerLogsLabel;
	}

	public PlayerFrog() {
		super(112, 256, 16, 16, "FroggerUp.png");
		this.overWater = false;
		this.setPlayerFrogLabel(null);
		this.startButton = null;
	}
	
	public void display() {
		//System.out.println("x, y: " + this.spriteX + ", " + this.spriteY);
		//System.out.println("w, h " + this.spriteWidth + ", " + this.spriteHeight);
		//System.out.println("image: " + this.spriteImage );
		System.out.println("FROG: ");
		super.display();
	}
	
	public void startWater() {
		if (!this.overWater) {
			this.overWater = true;
			w = new Thread(this, "FroggerWaterCheck");
			w.start();
			
		}
		
	}
	
	public void stopWater() {
		this.overWater = false;
		System.out.println("Water Stop Test");
	}
	
	public void startCar() {
		if (!this.gameActive) {
			this.gameActive = true;
			c = new Thread(this, "FroggerCarCheck");
			c.start();
			
		}
		
	}
	
	public void stopCar() {
		this.gameActive = false;
		System.out.println("Car Stop Test");
	}
	
	

	@Override
	public void run() {
		this.overWater = true;
		System.out.println("Water Test");
		
		while (this.overWater) {
			if ( detectLog() ) {
				int x = this.getSpriteX();
				
				//if (this.logLeft) {
				//	if (!(x + this.getSpriteWidth() <= 16)) {
				//		x -= FGProperties.OBSTACLE_STEP;
				//	}
				//} else if (!this.logLeft) {
				//	if (!(x + this.getSpriteWidth() >= 16)) {
				//		x += FGProperties.OBSTACLE_STEP;
				//	}
					
				//}
					
				if ( !(x + this.getSpriteWidth() <= 16) ) {
					x -= FGProperties.OBSTACLE_STEP;
				}
				
				this.setSpriteX(x);
				this.setSpriteY(this.spriteY);
				
				//move character graphic
				playerFrogLabel.setLocation(this.spriteX, this.spriteY);
				
				try {
					Thread.sleep(this.logSpeed);
				} catch(InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
				}
				
			} else if ( !detectLog() ){
				System.out.println("Game Over by no log");
				this.display();
				
				this.overWater = false;
				this.frogActive = false;
				
				froggerLogs.display();
				
				this.overWater = false;
				
				//make graphic for frog dead
				this.setSpriteImage("FroggerLose.png");
				this.playerFrogLabel.setIcon(
						new ImageIcon(getClass().getResource("Sprites/FrogSprites/" + this.getSpriteImage()))
				);
			}
		}
		
	}
	
	private boolean detectLog() {
		if ( this.r.intersects( this.froggerLogs.getRectangle() ) ) {
			return true;
		} else {
			return false;
		}
	}
	
}