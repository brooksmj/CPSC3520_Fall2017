package entity;

/**
 * @author Michael
 */
import java.io.IOException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MouseSprite extends Entity {
	
	private static enum State { FALLING, JUMPING, LEVEL };
	
	private Rectangle box;
	private Texture sprite;
	private State state;
	private int jumpTime;
	private boolean collision = false;

	public MouseSprite(float width) {
		try {
			sprite = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Locke.png"));

			box = new Rectangle(0, 0, (int) width, (int) width * (sprite.getImageWidth() / sprite.getImageHeight()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			System.err.println("Could not open file.");
			throw new RuntimeException(e);
		}
		state = State.FALLING;

		System.out.println("Loaded Texture: " + sprite);
		System.out.println(">> Image width: "+sprite.getImageWidth());
		System.out.println(">> Image height: "+sprite.getImageHeight());
		System.out.println(">> Texture width: "+sprite.getTextureWidth());
		System.out.println(">> Texture height: "+sprite.getTextureHeight());
		System.out.println(">> Texture ID: "+sprite.getTextureID());
	}

	public void update(float delta) {
		if (state == State.FALLING) {
			box.translate(0, (int) (.5 * delta));

			if (box.getY() + box.getHeight() > Display.getHeight()) {
				box.setY(Display.getHeight() - box.getHeight() + 60);
				state = State.LEVEL;
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
								
				if(box.getX() < 0)
				{
					box.translate((int) (.50 * delta), 0);
				}
				else
				{
					box.translate((int) (-.50 * delta), 0);
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				
				if(box.getX() > 800)
				{
					box.translate((int) (-.50 * delta), 0);
				}
				else
				{
					box.translate((int) (.50 * delta), 0);
				}
			}
		}

		if (state == State.LEVEL) {

			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				
				if(box.getX() < 0)
				{
					box.translate((int) (.50 * delta), 0);
				}
				else
				{
					box.translate((int) (-.50 * delta), 0);
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				
				if(box.getX() > 800 - box.getWidth())
				{
					box.translate((int) (-.50 * delta), 0);
				}
				else
				{
					box.translate((int) (.50 * delta), 0);
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				jumpTime = 15;
				state = State.JUMPING;
				
				if(box.getY() < 0)
				{
					state = State.FALLING;
				}
			}
			
		}

		if (state == State.JUMPING) {
			if (jumpTime <= 0) {
				state = State.FALLING;
			}

			jumpTime--;
			box.translate(0, (int) (-.5 * delta));
			
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				box.translate((int) (-.50 * delta), 0);
				
				if(box.getX() < 0)
				{
					box.translate((int) (.50 * delta), 0);
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				box.translate((int) (.50 * delta), 0);
				
				if(box.getX() < 0)
				{
					box.translate((int) (-.50 * delta), 780);
				}
			}
		}		
	}
	
	public int getX()
	{
		return box.getX();
	}
	
	public int getY()
	{
		return box.getY();
	}
	
	public void draw() {
		float x = (float) box.getX();
		float y = (float) box.getY();
		float w = (float) box.getWidth();
		float h = (float) box.getHeight();

		// draw this rectangle using the loaded sprite
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, sprite.getTextureID());
		
		if(collision)
		{
			GL11.glColor3f(1, 0, 0);
		}
		else
		{
			GL11.glColor3f(1, 1, 1);
		}

		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x, y);

		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(x + w, y);

		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(x + w, y + h);

		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(x, y + h);

		GL11.glEnd();

		// unbind the sprite so that other objects can be drawn
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
//	public boolean collisionDetected()
//	{
//		System.out.println("Collision!");
//		return collision = true;
//	}
//	
//	public boolean collisionNotDetected()
//	{
//		return collision = false;
//	}
	
	public boolean collisionDetection(int x1, int x2, int y1, int y2)
	{
		// Ghetto collision detection
		double distance = Math.sqrt(
				Math.pow(x2 - x1, 2) + 
				Math.pow(y2 - y1, 2));
		
		
		if(distance >= 90 && distance <= 115)
		{
			System.out.println("Collision!");
			System.out.println(distance);
			collision = true;
		}
		else
		{
			collision = false;
		}
		
		return collision;
	}
}
