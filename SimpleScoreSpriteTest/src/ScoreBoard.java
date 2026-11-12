import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ScoreBoard extends Sprite {
	
	private JButton addButton, subtractButton;
	
	private JLabel scoreBoardLabel;
	
	private int digitValue;
	
	public ScoreBoard() {
		super(400, 300, 8, 8, "FroggerRedText0.png");
	}
	
	public void display() {
		//System.out.println("x, y: " + this.spriteX + ", " + this.spriteY);
		//System.out.println("w, h " + this.spriteWidth + ", " + this.spriteHeight);
		//System.out.println("image: " + this.spriteImage );
		super.display();
	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public JButton getSubtractButton() {
		return subtractButton;
	}

	public void setSubtractButton(JButton subtractButton) {
		this.subtractButton = subtractButton;
	}

	public int getDigitValue() {
		return digitValue;
	}

	public void setDigitValue(int digitValue) {
		this.digitValue = digitValue;
		
		//Oh shoot i have an idea. If this works this'll be sick
		this.setSpriteImage("FroggerRedText" + digitValue + ".png");
		this.scoreBoardLabel.setIcon(
				new ImageIcon(getClass().getResource("Sprites/UIElements/RedText/" + this.getSpriteImage()))
		);
		
	}

	public JLabel getScoreBoardLabel() {
		return scoreBoardLabel;
	}

	public void setScoreBoardLabel(JLabel scoreBoardLabel) {
		this.scoreBoardLabel = scoreBoardLabel;
	}

}