import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class GameEngine implements GLEventListener, KeyListener
{

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
		glcanvas.setSize(720, 480);
		
		final JFrame frame = new JFrame("2D Game Engine");
		
		frame.getContentPane().add(glcanvas);
		frame.setSize(glcanvas.getPreferredSize());
		
		frame.addKeyListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 60, true);
		animator.start();
	}

	public void keyPressed(KeyEvent arg0) 
	{
		
	}

	public void keyReleased(KeyEvent arg0) 
	{
		
	}

	public void keyTyped(KeyEvent arg0) 
	{
		
	}

	public void display(GLAutoDrawable arg0) 
	{
		
	}

	public void dispose(GLAutoDrawable arg0) 
	{
		
	}

	public void init(GLAutoDrawable arg0) 
	{
		
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
	{
		
	}
}
