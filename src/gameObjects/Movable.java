package gameObjects;

import main.Vector2d;

public interface Movable 
{
	public boolean moving = false;
	public void move(Vector2d movement);
	public void moveTick();
}
