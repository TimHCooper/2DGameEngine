package gameObjects;

import java.io.File;

import main.Vector2d;

public class Player extends GameObject implements Collidable, Animated, Movable
{
	public boolean moving;
	public byte move_count;
	
	public Player(File t, Vector2d[] tex, Vector2d o) 
	{
		super(t, tex, o);
		moving = false;
	}
	
	public void collide(GameObject source) 
	{
		
	}
	public void Animate() 
	{
		
	}

	public void move(Vector2d movement) 
	{
		moving = true;
		move_count = 0;
	}

	public void moveTick() 
	{
		if(!moving)
		{
			
		}
	}
}