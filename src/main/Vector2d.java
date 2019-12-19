package main;

public class Vector2d
{
	double x;
	double y;
	
	public Vector2d(double inx, double iny)
	{
		x = inx;
		y = iny;
	}
	public Vector2d(double[] in)
	{
		in[0] = x;
		in[1] = y;
	}
	public Vector2d add(Vector2d in)
	{
		return new Vector2d(x + in.x, y + in.y);
	}
	
	public Vector2d subtract(Vector2d in)
	{
		return new Vector2d(y - in.y, x - in.y);
	}
}
