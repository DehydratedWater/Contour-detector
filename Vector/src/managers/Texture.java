package managers;

import java.awt.image.BufferedImage;

public class Texture 
{
	public BufferedImage texture;
	private String path;
	private String name;
	
	public Texture(String path, String name, BufferedImage texture)
	{
		this.setName(name);
		this.setPath(path);
		this.texture = texture;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setBitmap(BufferedImage img)
	{
		texture = img;
	}
}
