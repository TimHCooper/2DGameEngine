package gameObjects;

import java.io.File;

import main.GameEngine;
import main.Vector2d;

public class Player extends GameObject implements Collidable, Animated, Movable
{
	public boolean moving;
	public byte move_count;
	Vector2d moveOffset;
	Vector2d moveDir;
	
	public Player(File t, Vector2d[] tex, Vector2d o) 
	{
		super(t, tex, o);
		moving = false;
		moveOffset = new Vector2d(0, 0);
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
		moveDir = movement;
		
		super.occupiedTiles[0].add(movement);
	}

	public void moveTick() 
	{
		if(moving)
		{
			moveOffset.set(Math.abs(moveOffset.x) + 1/GameEngine.MOVE_FRAMES,
					Math.abs(moveOffset.y) + 1/GameEngine.MOVE_FRAMES);
			moveOffset.multiply(moveDir);
			
			move_count++;
			if(move_count == GameEngine.MOVE_FRAMES)
			{
				moveOffset.set(0.0, 0.0);
				moving = false;
			}
		}
	}
}