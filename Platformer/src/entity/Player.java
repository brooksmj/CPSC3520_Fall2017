package entity;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Player extends Entity {

	private Texture playerSprite;
	private static float x = 0;
	private static float y = Display.getHeight() - 73;
	
	public Player() {
		
		try {
				//Load texture from PNG file
				playerSprite = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Locke.png"));
				
//				System.out.println("Loaded Texture: " + playerSprite);
//				System.out.println(">> Image width: "+playerSprite.getImageWidth());
//				System.out.println(">> Image height: "+playerSprite.getImageHeight());
//				System.out.println(">> Texture width: "+playerSprite.getTextureWidth());
//				System.out.println(">> Texture height: "+playerSprite.getTextureHeight());
//				System.out.println(">> Texture ID: "+playerSprite.getTextureID());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(float delta) {
		draw();
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			
			if (this.x - playerSprite.getTextureWidth() < -60) {
				this.x += delta * 0.5f;
			}
			
			this.x -= delta * 0.5f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			
			if (this.x + playerSprite.getTextureWidth() > Display.getWidth() + 20 ) {
				this.x -= delta * 0.5f;
			}
			
			this.x += delta * 0.5f;
		}
		
		
		
	}

	public void draw() {
		
		// draw this rectangle using the loaded sprite
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, playerSprite.getTextureID());
		GL11.glColor3f(1, 1, 1);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x,y);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+playerSprite.getTextureWidth(),y);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+playerSprite.getTextureWidth(),y+playerSprite.getTextureHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x,y+playerSprite.getTextureHeight());
		GL11.glEnd();
		
		// unbind the sprite so that other objects can be drawn
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
}
