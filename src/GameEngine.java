import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.TextureIO;

public class GameEngine implements GLEventListener, KeyListener
{
	int BSSwidth, BSSheight;
	int[] sprites;
	int tileSize;
	
	public static void main(String[] args)
	{
		new GameEngine();
	}
	
	public GameEngine()
	{
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		GLCanvas glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(this);
		glcanvas.setSize(480, 480);
		
		final JFrame frame = new JFrame("2D Game Engine");
		
		frame.getContentPane().add(glcanvas);
		frame.setSize(glcanvas.getPreferredSize());
		
		frame.addKeyListener(this);
		
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 60, true);
		animator.start();
	}

	public void keyPressed(KeyEvent k) 
	{
		
	}

	public void keyReleased(KeyEvent k) 
	{
		
	}

	public void keyTyped(KeyEvent k) 
	{
		
	}

	public void display(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		draw(gl);
		
	}

	public void dispose(GLAutoDrawable drawable) 
	{
		
	}

	public void init(GLAutoDrawable drawable) 
	{
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		
		try 
		{
			File BSS = new File(System.getProperty("user.dir") + "/Assets/Sprites/basicSpriteSheet.png");
			File BSS2 = new File(System.getProperty("user.dir") + "/Assets/Sprites/basicSpriteSheet2.png");
			BSSwidth = ImageIO.read(BSS).getWidth();
			BSSheight = ImageIO.read(BSS).getHeight();
			
			sprites = new int[2];
			sprites[0] = TextureIO.newTexture(BSS, true).getTextureObject(gl);
			sprites[1] = TextureIO.newTexture(BSS2, true).getTextureObject(gl);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
	{
		
	}
	
	public void draw(GL2 gl)
	{
		gl.glBindTexture(GL2.GL_TEXTURE_2D, sprites[0]);
		gl.glBegin(GL2.GL_QUADS);
		
		gl.glTexCoord2d(1.0, 0.75);
		gl.glVertex2d(-0.2, -0.2);
		
		gl.glTexCoord2d(1.0, 1.0);
		gl.glVertex2d(-0.2, 0.2);
		
		gl.glTexCoord2d(0.75, 1.0);
		gl.glVertex2d(0.2, 0.2);
		
		gl.glTexCoord2d(0.75, 0.75);
		gl.glVertex2d(0.2, -0.2);
		
		gl.glEnd();
		gl.glFlush();
	}
}
