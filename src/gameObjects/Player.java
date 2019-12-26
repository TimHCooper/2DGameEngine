package gameObjects;

import java.io.File;

import main.GameEngine;
import main.Vector2d;

public class Player extends GameObject implements Collidable, Animated, Movable
{
	public boolean moving;
	byte move_count;
	Vector2d moveOffset;
	Vector2d moveDir;
	public Vector2d position;
	
	public Player(File t, Vector2d[] tex, Vector2d o) 
	{
		super(t, tex, o);
		moving = false;
		moveOffset = new Vector2d(0, 0);
		position = o;
	}
	
	public void collide(GameObject source) 
	{
		
	}
	public void Animate() 
	{
		
	}

	public void move(Vector2d movement) 
	{
		if(!moving)
		{
			super.occupiedTiles[0].add(movement);
			
			moving = true;
			move_count = GameEngine.MOVE_FRAMES;
			moveOffset.set(1.0 / GameEngine.MOVE_FRAMES, 1.0 / GameEngine.MOVE_FRAMES);
			moveOffset.multiply(movement);
			position.subtract(movement);
		}
	}

	public void moveTick() 
	{
		if(moving)
		{	
			position.add(moveOffset);
			move_count--;
			if(move_count == 0)
			{
				moving = false;
				position.set(Math.round(position.x), Math.round(position.y));
			}
		}
	}
}