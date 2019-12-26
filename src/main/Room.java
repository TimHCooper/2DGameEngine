package main;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.TextureIO;

import gameObjects.*;

public class Room 
{
	final static int TYPE_OVERWORLD = 0;
	final static int TYPE_DUNGEON = 1;
	final static int TYPE_HOUSE = 2;
	
	File tileSet;
	
	int sizeX, sizeY, type;
	Vector2d[][][] roomTexArray;
	ArrayList<GameObject> objects;
	ArrayList<GameObject> toTick;
	public Vector2d startPos;
	private int pIndex;
	
	public Room(int x, int y, int t, Vector2d pos)
	{
		objects = new ArrayList<GameObject>();
		toTick = new ArrayList<GameObject>();
		
		sizeX = x;
		sizeY = y;
		type = t;
		startPos = pos;
		
		roomTexArray = new Vector2d[sizeX][sizeY][4];
		
		Vector2d[] randSelect;
		
		Random rand = new Random();
		
		switch (type)
		{
		case TYPE_OVERWORLD:
			randSelect = new Vector2d[38];
			
			for(int i = 1; i < 19; i++)
			{
				randSelect[i - 1] = new Vector2d(i/39.0, 36.0/38.0);
			}
			for(int i = 0; i < 19; i++)
			{
				randSelect[i + 18] = new Vector2d((i + 1)/39.0, 35.0/38.0);
			}
			for(int i = 0; i < sizeX; i++)
			{
				for(int ii = 0; ii < sizeY; ii++)
				{
					int random = rand.nextInt(380);
					if(random < 343)
					{	
						roomTexArray[i][ii][0] = new Vector2d(1.0/39.0, 36.0/38.0);
						roomTexArray[i][ii][1] = new Vector2d(1.0/39.0, 35.0/38.0);
						roomTexArray[i][ii][2] = new Vector2d(2.0/39.0, 35.0/38.0);
						roomTexArray[i][ii][3] = new Vector2d(2.0/39.0, 36.0/38.0);
						
					}
					else
					{
						int index = random - 343;
						roomTexArray[i][ii][0] = randSelect[index];
						roomTexArray[i][ii][1] = new Vector2d(randSelect[index].x, randSelect[index].y - 1.0/39.0);
						roomTexArray[i][ii][2] = new Vector2d(randSelect[index].x + 1.0/39.0, randSelect[index].y - 1.0/38.0);
						roomTexArray[i][ii][3] = new Vector2d(randSelect[index].x + 1.0/39.0, randSelect[index].y);
					}
				}
			}
			break;
		case TYPE_DUNGEON:
			
			break;
		case TYPE_HOUSE:
			
			break;
		}
	}
	
	public Room(File room)
	{
		
	}
	
	public void add(GameObject go)
	{
		objects.add(go);
		if(go instanceof Player)
			pIndex = objects.indexOf(go);
		if(go instanceof Movable || go instanceof Animated)
			toTick.add(go);
	}
	
	public int[] initSprites(GL2 gl)
	{
		HashSet<File> files = new HashSet<File>();
		for(GameObject object : objects)
		{
			files.add(object.textureFile);
		}
		int[] toReturn = new int[files.size()];
		int i = 0;
		for(File file : files)
		{
			try {
			toReturn[i] = TextureIO.newTexture(file, true).getTextureObject(gl);
			i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return toReturn;
	}
	
	public Player getPlayer()
	{
		return (Player) objects.get(pIndex);
	}
}