import java.awt.Rectangle;

//1. class skeleton
public class Sprite {
	
	//2. define any data members, attributes, member variable
	protected int spriteX, spriteY; //upper left hand coordinates
	protected int spriteWidth, spriteHeight; //size to display
	protected String spriteImage; //graphic to be displayed
	
	protected Rectangle r;
	
	
	//3. getters and setters
	public int getSpriteX() {
		return spriteX;
	}
	public void setSpriteX(int spriteX) {
		this.spriteX = spriteX;
		this.r.x = this.spriteX;
	}
	public int getSpriteY() {
		return spriteY;
	}
	public void setSpriteY(int spriteY) {
		this.spriteY = spriteY;
		this.r.y = this.spriteY;
	}
	public int getSpriteWidth() {
		return spriteWidth;
	}
	public void setSpriteWidth(int spriteWidth) {
		this.spriteWidth = spriteWidth;
		this.r.width = this.spriteWidth;
	}
	public int getSpriteHeight() {
		return spriteHeight;
	}
	public void setSpriteHeight(int spriteHeight) {
		this.spriteHeight = spriteHeight;
		this.r.height = this.spriteHeight;
	}
	public String getSpriteImage() {
		return spriteImage;
	}
	public void setSpriteImage(String spriteImage) {
		this.spriteImage = spriteImage;
	}
	
	public Rectangle getRectangle() {
		return r;
	}
	public void setRectangle(Rectangle r) {
		this.r = r;
	}
	
	//4. default constructor
	public Sprite() {
		super();
		r = new Rectangle(this.spriteX, this.spriteY, this.spriteWidth, this.spriteHeight);
	}
	
	//5. secondary constructor
	public Sprite(int spriteX, int spriteY, int spriteWidth, int spriteHeight, String spriteImage) {
		super();
		this.spriteX = spriteX;
		this.spriteY = spriteY;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.spriteImage = spriteImage;
		r = new Rectangle(this.spriteX, this.spriteY, this.spriteWidth, this.spriteHeight);
	}
	
	//6. other methods
	
	//7. display



public void display() {
		System.out.println("x, y: " + this.spriteX + ", " + this.spriteY);
		System.out.println("w, h " + this.spriteWidth + ", " + this.spriteHeight);
		System.out.println("image: " + this.spriteImage );

	}
	
	

}