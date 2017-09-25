package entity;

import org.lwjgl.util.Rectangle;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class StupidBox extends Entity {
	private static enum State {
		START, LEFT, RIGHT, UP, DOWN
	};

	private float r = 0.75f;
	private float g = 0;
	private float b = 0;

	private Rectangle box;
	private State state;
	private float speed; // pixels / ms

	public StupidBox(float speed) {
		box = new Rectangle(0, Display.getHeight() - 72, 25, 40);
		state = State.START;
		this.speed = speed;
	}

	public double getWidth()
	{
		return box.getWidth();
	}
	
	public void draw() {
		float x = (float) box.getX();
		float y = (float) box.getY();
		float w = (float) box.getWidth();
		float h = (float) box.getHeight();

		// draw the square

		GL11.glColor3f(r, g, b);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x + w, y);
		GL11.glVertex2f(x + w, y + h);
		GL11.glVertex2f(x, y + h);

		GL11.glEnd();
	}
	
	public int getX()
	{
		return box.getX();
	}
	
	public int getY()
	{
		return box.getY();
	}

	public void update(float delta) {
		State previousState = null;
		switch (state) {
		case START:
			state = State.RIGHT;
//			System.out.println("State Changed!");

		case RIGHT:

			box.translate((int) (speed * delta), 0);

//			if (box.getX() >= 780 && box.getY() <= 10) {
//				state = State.DOWN;
//			}
			
			if (box.getX() >= Display.getWidth() - box.getWidth()) {
				state = State.LEFT;
			}
			
			previousState = State.RIGHT;

			break;

		case LEFT:

			box.translate((int) (-speed * delta), 0);

//			if (box.getX() <= 10 && box.getY() >= 580) {
//				state = State.UP;
//			}
			
			if (box.getX() <= 0) {
				state = State.RIGHT;
			}
			
			previousState = State.LEFT;

			break;

//		case UP:
//
//			box.translate(0, (int) (-speed * delta));
//
//			if (box.getY() <= 10 && box.getX() <= 10) {
//				state = State.RIGHT;
//			}
//			
//			previousState = State.UP;
//
//			break;
//
//		case DOWN:
//
//			box.translate(0, (int) (speed * delta));
//
//			if (box.getY() >= 580 && box.getX() >= 780) {
//				state = State.LEFT;
//			}
//			
//			previousState = State.DOWN;
//			break;
		}

//		if (!(state == previousState)) {
//			
//			GL11.glColor3f(r = new Random().nextFloat(), g = new Random().nextFloat(), b = new Random().nextFloat());
//		}
	}
}
