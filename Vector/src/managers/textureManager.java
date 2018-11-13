package managers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class textureManager 
{
	private ArrayList<Texture> textureList;
	private HashMap<String, Integer> serchMap;
	
	public textureManager()
	{
		textureList= new ArrayList<Texture>();
		serchMap = new HashMap<>();
	}
	
	public int addNewTextureAndGetTextureID(String path, String name)
	{
		if(serchMap.containsKey(name))
		{
			System.out.println("Tekstura jest ju¿ w pamiêci");
			return serchMap.get(name);
		}
		else
		{
			BufferedImage tex = null;
			
			try {
				tex = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("B³¹d ³adowania textury");
				return -10;
			}
			serchMap.put(name, textureList.size());
			textureList.add(new Texture(path, name, tex));
			System.out.println("Pomyœlnie za³adowano texturê "+name+" "+path+" "+(textureList.size()-1));
			return textureList.size()-1;
		}
	}
	public int generateNewTextureAndGetTextureID(String name, int x, int y)
	{

		if(serchMap.containsKey(name))
		{
			System.out.println("Tekstura jest ju¿ w pamiêci");
			return serchMap.get(name);
		}
		else
		{
			BufferedImage tex = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
//			Graphics2D g = (Graphics2D) tex.createGraphics();
//			g.setColor(Color.black);
//			g.fillRect(0, 0, tex.getWidth(), tex.getHeight());
			serchMap.put(name, textureList.size());
			textureList.add(new Texture(name, name, tex));
			System.out.println("Pomyœlnie wygenerowano texturê "+name+" "+(textureList.size()-1));
			return textureList.size()-1;
		}
	}
	public Texture getTexture(int i)
	{
		return textureList.get(i);
	}
}
