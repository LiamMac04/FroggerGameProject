import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Text extends Sprite {

	private JLabel textLabel;
	
	private String digitValue;
	
	public Text() {
		super(224, 8, 8, 8, "FroggerRedText0.png");
	}
	
	public void display() {
		//System.out.println("x, y: " + this.spriteX + ", " + this.spriteY);
		//System.out.println("w, h " + this.spriteWidth + ", " + this.spriteHeight);
		//System.out.println("image: " + this.spriteImage );
		super.display();
	}

	public String getDigitValue() {
		return digitValue;
	}

	public void setDigitValue(String digitValue) {
		this.digitValue = digitValue;
		
		this.setSpriteImage("FroggerRedText" + digitValue + ".png");
		this.textLabel.setIcon(
				new ImageIcon(getClass().getResource("Sprites/UIElements/RedText/" + this.getSpriteImage()))
		);
		
	}

	public JLabel getTextLabel() {
		return textLabel;
	}

	public void setTextLabel(JLabel textLabel) {
		this.textLabel = textLabel;
	}

}