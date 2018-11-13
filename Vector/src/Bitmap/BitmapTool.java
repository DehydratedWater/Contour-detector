package Bitmap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BitmapTool 
{
	private static int[] rTab;
	private static int[] gTab;
	private static int[] bTab;
	
	
	public static float compareBitmaps(BufferedImage startImg, BufferedImage compImg)
	{
		int width = startImg.getWidth();
		int height = startImg.getHeight();
		
		if(compImg.getWidth()!=width||compImg.getHeight()!=height)
			return 0;
		float maxDiffence = (float) (width*height*Math.sqrt(255*255*3));
		float sum = 0;
		Color c1;
		Color c2;
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				c1 = new Color(startImg.getRGB(i, j));
				c2 = new Color(compImg.getRGB(i, j));
				sum += Math.sqrt(Math.pow((c1.getRed()-c2.getRed()), 2)+Math.pow((c1.getGreen()-c2.getGreen()), 2)+Math.pow((c1.getBlue()-c2.getBlue()), 2));
			}
		}
		return ((maxDiffence-sum))/maxDiffence;
	}

	public static final void changeBitmapColorsForSimilar(BufferedImage startImg, int similarity)
	{

		rTab = inicializeColorTab(similarity);
		gTab = inicializeColorTab(similarity);
		bTab = inicializeColorTab(similarity);
		
		int width = startImg.getWidth();
		int height = startImg.getHeight();
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				Color c = new Color(startImg.getRGB(i, j));
				Color c2 = new Color(rTab[c.getRed()], gTab[c.getGreen()], bTab[c.getBlue()]);
				startImg.setRGB(i, j, c2.getRGB());
			}
		}
	}

	private static int[] inicializeColorTab(int similarity) {
		int[] tab = new int[256];
		int step = 256/(similarity);

		for(int i = 0; i < 256; i++)
		{
			tab[i] = (i/step)*step; 
			//System.out.print(tab[i]+" ");
		}
		//System.out.println();
		return tab;
	}

	public static final void changeBitmapColorsForGray(BufferedImage startImg)
	{
		int width = startImg.getWidth();
		int height = startImg.getHeight();
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				Color c = new Color(startImg.getRGB(i, j));
				int med = (c.getRed()+c.getGreen()+c.getBlue())/3;
				Color c2 = new Color(med, med, med);
				startImg.setRGB(i, j, c2.getRGB());
			}
		}
	}
	public static final void changeBitmapColorsForBlackAndWhite(BufferedImage startImg, int border)
	{
		int width = startImg.getWidth();
		int height = startImg.getHeight();
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				Color c = new Color(startImg.getRGB(i, j));
				int med = (c.getRed()+c.getGreen()+c.getBlue())/3;
				if(med>=border)
					startImg.setRGB(i, j, Color.white.getRGB());
				else
					startImg.setRGB(i, j, Color.black.getRGB());
			}
		}
	}
	
	public static final void changeBitmapColorsForBlackAndGray(BufferedImage startImg, int border1, int border2)
	{
		int width = startImg.getWidth();
		int height = startImg.getHeight();
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				Color c = new Color(startImg.getRGB(i, j));
				int med = (c.getRed()+c.getGreen()+c.getBlue())/3;
				if(med>border2)
					startImg.setRGB(i, j, Color.white.getRGB());
				else if(med>border1)
					startImg.setRGB(i, j, Color.gray.getRGB());
				else
					startImg.setRGB(i, j, Color.black.getRGB());
			}
		}
	}
	public static void copyBitmap(BufferedImage copyImg, BufferedImage placeImg)
	{
		Graphics2D g = placeImg.createGraphics();
		g.fillRect(0, 0, copyImg.getWidth(), copyImg.getHeight());
		g.drawImage(copyImg, 0, 0, null);
	}
	public static void drawBitmapOnTop(BufferedImage copyImg, BufferedImage placeImg)
	{
		Graphics2D g = placeImg.createGraphics();
		g.drawImage(copyImg, 0, 0, null);
	}
	public static void drawNoWhiteOnTopOfBitmap(BufferedImage copyImg, BufferedImage placeImg)
	{
		int width = copyImg.getWidth();
		int height = copyImg.getHeight();
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				Color c = new Color(copyImg.getRGB(i, j));
				
				if(c.getRGB()!=Color.white.getRGB())
					placeImg.setRGB(i, j, Color.black.getRGB());
			}
		}
	}
	public static final void generateBorders(BufferedImage startImg, int borderSize)
	{
		int width = startImg.getWidth();
		int height = startImg.getHeight();
		BufferedImage result = new BufferedImage(startImg.getWidth(), startImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				if(startImg.getRGB(i, j) == Color.black.getRGB())
				{
				int sX = i-borderSize;
				int sY = j-borderSize;
				int eX = i+borderSize;
				int eY = j+borderSize;
				if(sX<0)
					sX = 0;
				if(sY < 0)
					sY = 0;
				if(eX>=startImg.getWidth())
					eX = startImg.getWidth()-1;
				if(eY>=startImg.getHeight())
					eY = startImg.getHeight()-1;
				//System.out.println(sX+" -> "+eX+" and "+sY+" -> "+eY);
				int value = 0;
				for(int x = sX; x <= eX; x++)
				{
					for(int y = sY; y <= eY; y++)
					{
						//System.out.println("Sprawdzanie koloru pixela "+x+" "+y+" "+new Color(startImg.getRGB(x, y)));
						if(x == i&&y == j)
						{
							//System.out.println("pomijanie œrodka");
							continue;
						}
						if(startImg.getRGB(x, y)!=Color.BLACK.getRGB())
							value++;
					}
				}
				//System.out.println("Value "+value);
				if(value>1)
					result.setRGB(i, j, Color.BLACK.getRGB());
				else
					result.setRGB(i, j, Color.WHITE.getRGB());
				}
				
			}
		}
		Graphics2D g = startImg.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.drawImage(result, 0, 0, null);
	}
	
	public static final void convertForPoints(BufferedImage startImg)
	{
		int width = startImg.getWidth();
		int height = startImg.getHeight();
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				if(startImg.getRGB(i, j) == Color.black.getRGB())
				{
				int sX = i-1;
				int sY = j-1;
				int eX = i+1;
				int eY = j+1;
				if(sX<0)
					sX = 0;
				if(sY < 0)
					sY = 0;
				if(eX>=startImg.getWidth())
					eX = startImg.getWidth();
				if(eY>=startImg.getHeight())
					eY = startImg.getHeight();
				//System.out.println(sX+" -> "+eX+" and "+sY+" -> "+eY);
				int value = 0;
				for(int x = sX; x < eX; x++)
				{
					for(int y = sY; y < eY; y++)
					{
						if(x == i&&y == j)
							continue;
						if(startImg.getRGB(x, y)!=Color.black.getRGB())
							value++;
					}
				}
				//System.out.println("Value "+value);
				if(value<3)
					startImg.setRGB(i, j, Color.white.getRGB());
				else
					startImg.setRGB(i, j, Color.black.getRGB());
				}
			}
		}
	}
}
