package gameObjects;

import java.io.File;
import main.Vector2d;

public class GameObject 
{
	public File textureFile;
	public Vector2d[] occupiedTiles;
	public Vector2d[][] textureCoords;
	
	public GameObject(File t, Vector2d[][] tex, Vector2d[] o)
	{
		textureFile = t;
		occupiedTiles = o;
		textureCoords = tex;
	}
	public GameObject(File t, Vector2d[] tex, Vector2d o)
	{
		textureFile = t;
		occupiedTiles = new Vector2d[1];
		occupiedTiles[0] = o;
		textureCoords = new Vector2d[1][4];
		textureCoords[0] = tex;
	}
}
