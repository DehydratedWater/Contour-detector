package objects;

import java.awt.Graphics2D;

import managers.Texture;
import managers.textureManager;

public class StaticObject 
{
	public float x, y, width, height;
	public float scaleX = 1, scaleY = 1;
	public float angle = 0;
	private int textureIndex;
	public float mass;
	public boolean isPhisical = false;
	public boolean colliding = false;
	public boolean isAnimated = false;
	public int[] Animation;
	public int animationTime;
	public int frameIndex;
	public long lastFrameTime;
	public StaticObject(float posX, float posY, String name, String path, textureManager texManager)
	{
		this.x = posX;
		this.y = posY;
		textureIndex = texManager.addNewTextureAndGetTextureID(path, name);
		this.width = texManager.getTexture(textureIndex).texture.getWidth();
		this.height = texManager.getTexture(textureIndex).texture.getHeight();
		isAnimated = false;
	}
	public StaticObject(float posX, float posY, int sizX, int sizY, String name, textureManager texManager)
	{
		this.x = posX;
		this.y = posY;
		textureIndex = texManager.generateNewTextureAndGetTextureID(name, (int)sizX, (int)sizY);
		this.width = texManager.getTexture(textureIndex).texture.getWidth();
		this.height = texManager.getTexture(textureIndex).texture.getHeight();
		isAnimated = false;
	}

	public StaticObject(float posX, float posY, String[] name, String[] path, textureManager texManager, int animationTime)
	{
		this.x = posX;
		this.y = posY;
		Animation = new int[path.length];
		for(int i = 0; i < path.length; i++)
		{
			Animation[i] = texManager.addNewTextureAndGetTextureID(path[i], name[i]);
		}
		this.width = texManager.getTexture(textureIndex).texture.getWidth();
		this.height = texManager.getTexture(textureIndex).texture.getHeight();
		isAnimated = true;
		this.animationTime = animationTime;
		this.lastFrameTime = System.currentTimeMillis();
	}
	
	public void drawObject(Graphics2D g, textureManager texManager)
	{
		//System.out.println("Rysowanie tekstury");
		if(!isAnimated)
		drawNoAnimated(g, texManager);
		else
		drawAnimated(g, texManager);
	}

	private void drawAnimated(Graphics2D g, textureManager texManager) 
	{
		
		Texture tex = texManager.getTexture(Animation[frameIndex]);
		g.translate(x+(tex.texture.getWidth()*scaleX)/2, y+(tex.texture.getHeight()*scaleY)/2);
		
		g.rotate(Math.toRadians(angle));
		g.translate((-tex.texture.getWidth()*scaleX)/2, -(tex.texture.getHeight()*scaleY)/2);
		g.drawImage(tex.texture, 0, 0, (int)(tex.texture.getWidth()*scaleX), (int)(tex.texture.getHeight()*scaleY), null);

		g.translate((tex.texture.getWidth()*scaleX)/2, (tex.texture.getHeight()*scaleY)/2);
		g.rotate(Math.toRadians(-angle));
		g.translate(-x-(tex.texture.getWidth()*scaleX)/2, -y-(tex.texture.getHeight()*scaleY)/2);
		if(System.currentTimeMillis()-lastFrameTime>animationTime)
		{
			lastFrameTime = System.currentTimeMillis();
			frameIndex++;
			if(frameIndex>=Animation.length)
			{
				frameIndex = 0;
			}
		}
	}

	private void drawNoAnimated(Graphics2D g, textureManager texManager) {
		Texture tex = texManager.getTexture(textureIndex);
		g.translate(x+(tex.texture.getWidth()*scaleX)/2, y+(tex.texture.getHeight()*scaleY)/2);
		
		g.rotate(Math.toRadians(angle));
		g.translate((-tex.texture.getWidth()*scaleX)/2, -(tex.texture.getHeight()*scaleY)/2);
		g.drawImage(tex.texture, 0, 0, (int)(tex.texture.getWidth()*scaleX), (int)(tex.texture.getHeight()*scaleY), null);

		g.translate((tex.texture.getWidth()*scaleX)/2, (tex.texture.getHeight()*scaleY)/2);
		g.rotate(Math.toRadians(-angle));
		g.translate(-x-(tex.texture.getWidth()*scaleX)/2, -y-(tex.texture.getHeight()*scaleY)/2);
	}
	public int getTextureIndex() {
		return textureIndex;
	}

	public void setTextureIndex(int textureIndex) {
		this.textureIndex = textureIndex;
	}
	public void scale(float s)
	{
		this.scaleX = s;
		this.scaleY = s;
	}
}
