package windows;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import managers.textureManager;

public abstract class BasicWindow extends Canvas
{
	private static final long serialVersionUID = 1L;
	public static boolean showFPS = true;
	public static boolean clearLastFrame = true;
	public static boolean lossyScale = true;
	public static boolean antialiasing = true;
	public static int FPSrate = 60;
	public static float ScreenScale = 1;
	public static int delta;
	private JFrame window;
	private JPanel panel;
	
	private BufferStrategy bufor;
	private float ScreenSizeX, ScreenSizeY;
	private float Scale;

	/**
	 * Konstruktor bazowy
	 * rozdzielczoœæ okna [rozdzielczoœæ x, rozdzielczoœæ  y, 
	 * lokalizacja x, lokalizacja y, nazwa okna]
	 */
	public BasicWindow(int resx, int resy, int locx, int locy, String title)
	{
		window = new JFrame(title);
		window.setSize(resx, resy);
		window.setLocation(locx, locy);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createDrawingPanel(2);

		ScreenSizeX = resx;
		ScreenSizeY = resy;
	}
	/**
	 * Ustawia wyœwietlanie i jakoœæ odœwierzania
	 * [jakoœæ odœwierzania]
	 */
	private void createDrawingPanel(int bufferStrategy) {
		Container container = window.getContentPane();
		container.setLayout(new BorderLayout());
		panel = (JPanel) window.getContentPane();
		container.add(panel.add(this),BorderLayout.CENTER);
		createBufferStrategy(bufferStrategy);
		bufor = getBufferStrategy();
		requestFocus();
	}
	
	/**
	 * Wykonuje pojedyñcze odœwierzenie klatki
	 * Wszystko co ma zostaæ narysowane ma zostaæ
	 * umieszczone wewn¹trz toDraw(g)
	 */
	public void refresh(textureManager texManager)
	{
		
		long time = System.currentTimeMillis();
		Graphics2D g = (Graphics2D) bufor.getDrawGraphics();
		if(antialiasing)
		{
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		if(clearLastFrame)
		{
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
		}
		if(lossyScale)
		{
			refreshScale();
			g.scale(Scale, Scale);
		}
		
		useControls(g, delta);
		checkCollisions(g, delta);
		drawFrame(g, delta, texManager);
		time = System.currentTimeMillis()-time;
		float fps;
		int wait = (int) ((1000/FPSrate)-time);
		if(wait>=0) 
			fps = FPSrate;
		else
			fps = (1000/((1000/FPSrate)-wait));

		if(showFPS)
		{
			g.setColor(Color.BLACK);
			g.drawString("FPS: "+fps+" "+time+"ms", 10, 20);
		}
		bufor.show();
		delta = wait;
		if(wait > 0)
		{
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	
	public void refreshScale()
	{
		
		float ScaleX = window.getWidth()/ScreenSizeX;
		float ScaleY = window.getHeight()/ScreenSizeY;
		if(ScaleX>ScaleY)
		{
			Scale = ScaleY;
		}
		else
		{
			Scale = ScaleX;
		}
		Scale *= ScreenScale;
	}
	
	/**
	 * Wewn¹trz tej funkcji umieszcza sie wywo³ania
	 * innych funkcji które maj¹ coœ narysowaæ
	 * rysujPlansze(g);
	 * rysujPostaæ(g);
	 * ***
	 * @param g
	 */
	public void drawFrame(Graphics2D g, int delta, textureManager texManager)
	{
		
	}
	public void checkCollisions(Graphics2D g, int delta)
	{
		
	}
	public void useControls(Graphics2D g, int delta)
	{
		
	}
	public float getScale() {
		return Scale;
	}
	public void setScale(float scale) {
		Scale = scale;
	}
}
