package main;

import gameObjects.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

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
	int[] sprites;
	int[] tileMaps;
	int tileSize, barSizeW, barSizeH;
	int screenWidth, screenHeight;
	int tileX = 0;
	int tileY = 0;
	int activeSprite = 0;
	byte animStage = 0, animDir = 0, activeRoom = 0, activeTileMap = 0;
	Room[] rooms;
	public final static byte MOVE_FRAMES = 10;
	Player player;
	
	public static void main(String[] args)
	{
		new GameEngine();
	}
	
	public GameEngine()
	{
		rooms = new Room[1];
		rooms[0] = new Room(16, 16, Room.TYPE_OVERWORLD, new Vector2d(0, 0));
		
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		GLCanvas glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(this);
		glcanvas.setSize(720, 480);
		
		final JFrame frame = new JFrame("2D Game Engine");
		
		frame.getContentPane().add(glcanvas);
		frame.getContentPane().setPreferredSize(glcanvas.getPreferredSize());
		frame.pack();
		frame.addKeyListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(glcanvas, 60, true);
		animator.start();
	}

	public void keyPressed(KeyEvent k) 
	{
		int key = k.getKeyCode();
		switch(key)
		{
		case KeyEvent.VK_W:
			tileY--;
			animDir = 3;
			break;
		case KeyEvent.VK_S:
			tileY++;
			animDir = 0;
			break;
		case KeyEvent.VK_D:
			tileX++;
			animDir = 2;
			break;
		case KeyEvent.VK_A:
			tileX--;
			animDir = 1;
			break;
		}
		if(tileY < 0)
			tileY= 0;
		if(tileY > 15)
			tileY = 15;
		if(tileX < 0)
			tileX=0;
		if(tileX > 15)
			tileX=15;
		
		animStage = (byte) (++animStage % 4);
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
		gl.glClearColor(0.0f,  0.0f,  0.0f,  1.0f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		drawRoom(gl, rooms[0]);
		drawChar(gl);
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
			
			sprites = new int[1];
			sprites[0] = TextureIO.newTexture(BSS, true).getTextureObject(gl);
			
			Vector2d[] playerTexCoords = {new Vector2d(0, 0), new Vector2d(0, .25), new Vector2d(.25,.25), new Vector2d(.25,0)};
			
			rooms[0].add(new Player(BSS, playerTexCoords, rooms[0].startPos));
			
			File over = new File(System.getProperty("user.dir") + "/Assets/TileSets/terrain.png");
			
			tileMaps = new int[1];
			tileMaps[0] = TextureIO.newTexture(over, true).getTextureObject(gl);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
	{
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
	
	public void drawChar(GL2 gl)
	{	
		gl.glBindTexture(GL2.GL_TEXTURE_2D, sprites[activeSprite]);
		gl.glBegin(GL2.GL_QUADS);
		
		Vector2d[] vertices = getRelativeSize(tileX, tileY);
		
		gl.glTexCoord2d((animStage * 0.25), 1.0 - (animDir * 0.25));
		gl.glVertex2d(vertices[0].x, vertices[0].y);
		
		gl.glTexCoord2d((animStage * 0.25), 0.75 - (animDir * 0.25));
		gl.glVertex2d(vertices[1].x, vertices[1].y);
		
		gl.glTexCoord2d((animStage * 0.25) + 0.25, 0.75 - (animDir * 0.25));
		gl.glVertex2d(vertices[2].x, vertices[2].y);
		
		gl.glTexCoord2d((animStage * 0.25) + 0.25, 1.0 - (animDir * 0.25));
		gl.glVertex2d(vertices[3].x, vertices[3].y);
		
		gl.glEnd();
		gl.glFlush();
	}
	
	public void drawRoom(GL2 gl, Room room)
	{
		gl.glBindTexture(GL2.GL_TEXTURE_2D, tileMaps[activeTileMap]);
		
		Vector2d[][][] roomTexArray = room.roomTexArray;
		Vector2d[] vertices;
		for(int i = 0; i < room.sizeX; i++)
		{
			for(int ii = 0; ii < room.sizeY; ii++)
			{
				gl.glBegin(GL2.GL_QUADS);
				vertices = getRelativeSize(i, ii);
				
				gl.glTexCoord2d(roomTexArray[i][ii][0].x, roomTexArray[i][ii][0].y);
				gl.glVertex2d(vertices[0].x, vertices[0].y);
				
				gl.glTexCoord2d(roomTexArray[i][ii][1].x, roomTexArray[i][ii][1].y);
				gl.glVertex2d(vertices[1].x, vertices[1].y);
				
				gl.glTexCoord2d(roomTexArray[i][ii][2].x, roomTexArray[i][ii][2].y);
				gl.glVertex2d(vertices[2].x, vertices[2].y);
				
				gl.glTexCoord2d(roomTexArray[i][ii][3].x, roomTexArray[i][ii][3].y);
				gl.glVertex2d(vertices[3].x, vertices[3].y);
				
				gl.glEnd();
				gl.glFlush();
			}
		}
	}
	
	public Vector2d[] getRelativeSize(int tileX, int tileY)
	{
		Vector2d[] toReturn = new Vector2d[4];
		
		toReturn[0] = new Vector2d((double) ((barSizeW + (tileX * tileSize)) * 2.0 / screenWidth) - 1.0,
				(double) -((barSizeH + (tileY * tileSize)) * 2.0 / screenHeight) + 1.0);
		
		toReturn[1] = new Vector2d(toReturn[0].x,
				(double) -((barSizeH + 1 + ((tileY + 1) * tileSize) - 1) * 2.0 / screenHeight) + 1.0);
		
		toReturn[2] = new Vector2d((double) ((barSizeW + 1 + ((tileX + 1) * tileSize) - 1) * 2.0 / screenWidth) - 1.0,
				toReturn[1].y);
		
		toReturn[3] = new Vector2d(toReturn[2].x, toReturn[0].y);
		
		return toReturn;
	}
}