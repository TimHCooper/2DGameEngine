
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
}
