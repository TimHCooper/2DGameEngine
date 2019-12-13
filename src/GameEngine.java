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
	int tileSize, barSizeW, barSizeH;
	int screenWidth, screenHeight;
	
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
		glcanvas.setSize(500, 500);
		
		final JFrame frame = new JFrame("2D Game Engine");
		
		frame.getContentPane().add(glcanvas);
		frame.getContentPane().setPreferredSize(glcanvas.getPreferredSize());
		frame.pack();
		frame.addKeyListener(this);
		
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//final FPSAnimator animator = new FPSAnimator(glcanvas, 60, true);
		//animator.start();
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
		System.out.println("Width: " + width + "\nHeight: " + height + "\n");
		if(width > height)
		{
			tileSize = height / 16;
			barSizeW = (width - (tileSize * 16)) / 2;
			barSizeH = 0;
		}
		else if(height > width)
		{
			tileSize = width / 16;
			barSizeH = (height - (tileSize * 16)) / 2;
			barSizeW = 0;
		}
		else
		{
			tileSize = width / 16;
			barSizeH = 0;
			barSizeW = 0;
		}
		screenWidth = width;
		screenHeight = height;
	}
	
	public void draw(GL2 gl)
	{
		int tileX = 1;
		int tileY = 1;
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, sprites[0]);
		gl.glBegin(GL2.GL_QUADS);
		
		double[][] vertices = getRelativeSize(tileX, tileY);
		
		gl.glTexCoord2d(1.0, 1.0);
		gl.glVertex2d(vertices[0][0], vertices[0][1]);
		
		gl.glTexCoord2d(1.0, 0.75);
		gl.glVertex2d(vertices[1][0], vertices[1][1]);
		
		gl.glTexCoord2d(0.75, 0.75);
		gl.glVertex2d(vertices[2][0], vertices[2][1]);
		
		gl.glTexCoord2d(0.75, 1.0);
		gl.glVertex2d(vertices[3][0], vertices[3][1]);
		
		gl.glEnd();
		gl.glFlush();
	}
	
	public double[][] getRelativeSize(int tileX, int tileY)
	{
		double[][] toReturn = new double[4][2];
		//System.out.println(tileSize);
		
		System.out.println((barSizeW + (tileX-1) * tileSize) / screenWidth);
		toReturn[0][0] = (barSizeW + (tileX-1) * tileSize) / screenWidth;
		toReturn[0][1] = (barSizeH + (tileY-1) * tileSize) / screenHeight;
		
		toReturn[1][0] = toReturn[0][0];
		toReturn[1][1] = (barSizeH + (tileY) * tileSize) / screenHeight;
		
		toReturn[2][0] = (barSizeW + (tileX) * tileSize) / screenWidth;
		toReturn[2][1] = toReturn[1][1];
		
		toReturn[3][0] = toReturn[2][0];
		toReturn[3][1] = toReturn[1][1];
		
		for(int i = 0; i < 4; i++)
		{
			System.out.println(toReturn[i][0] + ", " + toReturn[i][1]);
		}
		
		return toReturn;
	}
}
