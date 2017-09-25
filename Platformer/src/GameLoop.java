import entity.MouseSprite;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.LWJGLException;
import entity.StupidBox;
import entity.Entity;
public class GameLoop {
	
	private static final int TARGET_FPS = 100;
	private static final int SCR_WIDTH = 800;
	private static final int SCR_HEIGHT = 600;
	private static final String TITLE = "2D Platformer";

	public static void main(String[] args) throws LWJGLException {
		
		initGL(SCR_WIDTH, SCR_HEIGHT);		
		
		List<Entity> entities = new LinkedList<>();

			StupidBox enemy1 = new StupidBox(.075f);
//			StupidBox enemy2 = new StupidBox(.2f);
			MouseSprite player = new MouseSprite(200);
			entities.add(enemy1);
//			entities.add(enemy2);
			entities.add(player);
//			entities.add(new Player());
			
		long oldTime = (Sys.getTime() * 1000) / Sys.getTimerResolution(); // ms
		
		while (!Display.isCloseRequested()) {
			
			long newTime = (Sys.getTime() * 1000) / Sys.getTimerResolution(); // ms
			float delta = (float) (newTime - oldTime);
//			System.out.println(delta);

//			drawBackground(); // Upside down, can't figure out why
			
			for (Entity e : entities) {
				e.update(delta);
			}
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			for (Entity e : entities) {
				e.draw();
			}
			
			// Method in MouseSprite to detect collision --- Needs Work
			player.collisionDetection(player.getX(), enemy1.getX(), player.getY(), enemy1.getY());
			
			// Ghetto collision detection
//			double distance = Math.sqrt(
//					Math.pow(player.getX() - enemy1.getX(), 2) + 
//					Math.pow(player.getY() - enemy1.getY(), 2));
//			System.out.println(distance);
//			
//			if((distance >= 90 && distance <= 115))
//			{
//				player.collisionDetected();
//			}
//			else
//			{
//				player.collisionNotDetected();
//			}
			
			// UPDATE DISPLAY
			Display.update();
			Display.sync(TARGET_FPS);
			oldTime = newTime;
			
//			System.out.println("Player: " + player.getX() + ", " +player.getY());
//			System.out.println("Enemy1: " + enemy1.getX() + ", " +enemy1.getY());
//			System.out.println("Enemy2: " + enemy2.getX() + ", " +enemy2.getY());
		}
		Display.destroy();
	}

	public static void initGL(int width, int height) throws LWJGLException {
		// open window of appropriate size
		Display.setTitle(TITLE);
		Display.setDisplayMode(new DisplayMode(width, height));
        Display.create();
        Display.setVSyncEnabled(true);
        
        // enable 2D textures
        GL11.glEnable(GL11.GL_TEXTURE_2D);              
     
        // set "clear" color to black
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);         

        // enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         
        // set viewport to entire window
        GL11.glViewport(0,0,width,height);
         
        // set up orthographic projection
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        // GLU.gluPerspective(90f, 1.333f, 2f, -2f);
        // GL11.glTranslated(0, 0, -500);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
//	public static void drawBackground() {
//	
//		// Sky
//		glBegin(GL_QUADS);
//		{
//			glColor3d(0.7, 0.8, 0.9);
//			glVertex2d(0, 0);
//			glVertex2d(SCR_WIDTH, 0);
//			
//			glColor3d(0.4, 0.5, 0.7);
//			glVertex2d(SCR_WIDTH, SCR_HEIGHT);
//			glVertex2d(0, SCR_HEIGHT);
//		}
//		glEnd();
//		
//		// Ground
//		glBegin(GL_QUADS);
//		{
//			glColor3d(0.6, 0.2, 0.1);
//			glVertex2d(0, 0);
//			glVertex2d(SCR_WIDTH, 0);
//			glVertex2d(SCR_WIDTH, 32);
//			glVertex2d(0, 32);
//		}
//		glEnd();
//		
//		// Grass
//		glBegin(GL_QUADS);
//		{
//			glColor3d(0.2, 0.8, 0.2);
//			glVertex2d(0, 25);
//			glVertex2d(SCR_WIDTH, 25);
//			glVertex2d(SCR_WIDTH, 32);
//			glVertex2d(0, 32);
//		}
//		glEnd();
//	}
}
