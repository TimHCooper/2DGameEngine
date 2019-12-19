package main;

public class Vector2d
{
	public double x;
	public double y;
	
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
	public void add(Vector2d in)
	{
		x += in.x;
		y += in.y;
	}
	
	public void subtract(Vector2d in)
	{
		x -= in.x;
		y -= in.y;
	}
	
	public void multiply(Vector2d in)
	{
		x *= in.x;
		y *= in.y;
	}
	
	public void divide(Vector2d in)
	{
		x /= in.x;
		y /= in.y;
	}
	
	public static Vector2d add(Vector2d in1, Vector2d in2)
	{
		return new Vector2d(in1.x + in2.x, in1.y + in2.y);
	}
	
	public static Vector2d subtract(Vector2d in1, Vector2d in2)
	{
		return new Vector2d(in1.x - in2.x, in1.y - in2.y);
	}
	
	public static Vector2d multiply(Vector2d in1, Vector2d in2)
	{
		return new Vector2d(in1.x * in2.x, in1.y * in2.y);
	}
	
	public static Vector2d divide(Vector2d in1, Vector2d in2)
	{
		return new Vector2d(in1.x / in2.x, in1.y / in2.y);
	}
	
	public void set(double inx, double iny)
	{
		x = inx;
		y = iny;
	}
}
