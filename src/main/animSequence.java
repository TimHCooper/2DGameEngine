package main;

public class animSequence 
{
	private int tickCount;
	private int animStage;
	private Vector2d[] texCoords;
	private Vector2d currentStageTex;
	private int frameCount;
	
	public animSequence(Vector2d[] coords, int frames)
	{
		texCoords = coords;
		frameCount = frames;
		tickCount = 0;
		currentStageTex = texCoords[0];
		animStage = 0;
	}
	public Vector2d tick()
	{
		if(tickCount % frameCount == 0)
		{
			tickCount = 0;
			if(animStage == texCoords.length - 1)
				animStage = 0;
			else
				animStage++;
			currentStageTex = texCoords[animStage];
		}
		tickCount++;
		return currentStageTex;
	}
}
