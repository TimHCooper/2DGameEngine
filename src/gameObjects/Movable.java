package gameObjects;

import main.Vector2d;

public interface Movable 
{
	public void move(Vector2d movement);
	public void moveTick();
}
