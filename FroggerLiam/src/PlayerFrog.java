import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PlayerFrog extends Sprite {
	
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
	private int logNum;
	
	private int score = 0;

	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isFrogActive() {
		return frogActive;
	}

	public void setFrogActive(boolean frogActive) {
		this.frogActive = frogActive;
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

	public int getLogNum() {
		return logNum;
	}

	public void setLogNum(int logNum) {
		this.logNum = logNum;
	}

	public Boolean getOverWater() {
		return overWater;
	}

	public void setOverWater(Boolean overWater) {
		this.overWater = overWater;
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
		super.display();
	}
	
	public boolean detectLog() {
		if ( this.r.intersects( this.froggerLogs.getRectangle() ) ) {
			return true;
		} else {
			return false;
		}
		
	}
	
}