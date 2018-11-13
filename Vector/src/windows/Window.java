package windows;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import Bitmap.BitmapTool;
import managers.ControlsManager;
import managers.textureManager;
import objects.StaticObject;

public class Window extends BasicWindow implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{


	private static final long serialVersionUID = 1L;
	private textureManager texManager;
	private ControlsManager cm;
	private StaticObject mainPhoto, grayPhoto, contrastPhoto, borderPhoto, backUpPhoto;
	private BufferedImage mainImage, grayImage, contrastImage, borderImage, backUpImage;
	private float contrast;
	private long lastTime;
	private int colors = 10;

	public static int weight, height;
	
	public Window(int resx, int resy, int locx, int locy, String title) {
		super(resx, resy, locx, locy, title);
		BasicWindow.antialiasing = true;
		BasicWindow.clearLastFrame = true;
		BasicWindow.lossyScale = false;
		BasicWindow.showFPS = true;
		BasicWindow.FPSrate = 30;
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void initializeData()
	{
		String actName = "images3.png";
		cm = new ControlsManager();
		texManager = new textureManager();
		mainPhoto = new StaticObject(0, 50, "mainPhoto", actName, texManager);
		grayPhoto = new StaticObject(mainPhoto.width+4, 50, "contrastPhoto", actName, texManager);
		contrastPhoto = new StaticObject(mainPhoto.width*2+8, 50, "grayPhoto", actName, texManager);
		borderPhoto = new StaticObject(mainPhoto.width*3+12, 50, "borderPhoto", actName, texManager);
		setBackUpPhoto(new StaticObject(mainPhoto.width*3+12, 50, "backUpPhoto", actName, texManager));
		mainImage = texManager.getTexture(0).texture;
		grayImage = texManager.getTexture(1).texture;
		contrastImage = texManager.getTexture(2).texture;
		borderImage = texManager.getTexture(3).texture;
		backUpImage = texManager.getTexture(4).texture;
		BitmapTool.changeBitmapColorsForGray(grayImage);
		BitmapTool.changeBitmapColorsForBlackAndWhite(grayImage, 55);
		BitmapTool.generateBorders(grayImage, 1);
		BitmapTool.changeBitmapColorsForSimilar(mainImage, colors );
	}
	
	public void renderFullSpectrum()
	{
		BufferedImage b = new BufferedImage(mainImage.getWidth(), mainImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < 256; i++)
		{
		BitmapTool.copyBitmap(mainImage, grayImage);
		BitmapTool.changeBitmapColorsForGray(grayImage);
		BitmapTool.copyBitmap(grayImage, contrastImage);
		BitmapTool.changeBitmapColorsForBlackAndWhite(contrastImage, i);
		//BitmapTool.changeBitmapColorsForBlackAndGray(contrastImage, (int) contrast, (int)contrast+10);
		BitmapTool.copyBitmap(contrastImage, borderImage);
		BitmapTool.generateBorders(borderImage, 1);		
		BitmapTool.drawNoWhiteOnTopOfBitmap(borderImage, b);
		//BitmapTool.changeBitmapColorsForBlackAndWhite(b, 255);
		}
		BitmapTool.copyBitmap(b, borderImage);
		
	}
	
	public void renderNewVersion()
	{
		BitmapTool.copyBitmap(backUpImage, mainImage);
		BitmapTool.changeBitmapColorsForSimilar(mainImage, colors );
		BitmapTool.copyBitmap(mainImage, grayImage);
		BitmapTool.changeBitmapColorsForGray(grayImage);
		BitmapTool.copyBitmap(grayImage, contrastImage);
		//BitmapTool.changeBitmapColorsForBlackAndWhite(contrastImage, (int)contrast);
		BitmapTool.changeBitmapColorsForBlackAndGray(contrastImage, (int) contrast, (int)contrast+10);
		BitmapTool.copyBitmap(contrastImage, borderImage);
		BitmapTool.generateBorders(borderImage, 1);		
	}
	
	
	public void refreshFrame()
	{
		super.refresh(texManager);
		
		
	}
	
	
	
	@Override
	public void useControls(Graphics2D g, int delta) 
	{
		super.useControls(g, delta);
		
		if(contrast>255)
			contrast = 0;
		if(contrast<0)
			contrast = 255;
		
		if(cm.SPACE)
		{
			colors -= cm.mouseWeel;
			if(colors<1)
				colors = 1;
			
		}
		else
		{
			contrast -= cm.mouseWeel*3;
		}
		
		if(System.currentTimeMillis()-lastTime>2000)
		renderNewVersion();
		
		if(cm.R)
		{
			renderFullSpectrum();
			lastTime = System.currentTimeMillis();
		}
		

			
		
		cm.reset();
	}
	@Override
	public void drawFrame(Graphics2D g, int delta, textureManager texManager) 
	{
		super.drawFrame(g,delta,texManager);
		mainPhoto.drawObject(g, texManager);
		contrastPhoto.drawObject(g, texManager);
		grayPhoto.drawObject(g, texManager);
		borderPhoto.drawObject(g, texManager);
		g.setColor(Color.BLACK);
		g.drawString("Contrast: "+contrast+" colors: "+colors, 150, 20);
	}
	
	@Override
	public void checkCollisions(Graphics2D g, int delta) 
	{
		super.checkCollisions(g, delta);
		

	}
	public void clearUp() 
	{
		
		
	}


	public void keyPressed(KeyEvent e) {
		cm.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		cm.keyReleased(e);
	}
	public void keyTyped(KeyEvent e) {
		cm.keyTyped(e);
	}
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		cm.mouseWheelMoved(arg0);
	}
	public void mouseDragged(MouseEvent arg0) {
		cm.mouseDragged(arg0);
	}
	public void mouseMoved(MouseEvent arg0) {
		cm.mouseMoved(arg0);
	}
	public void mouseClicked(MouseEvent arg0) {
		cm.mouseClicked(arg0);
	}
	public void mouseEntered(MouseEvent arg0) {
		cm.mouseEntered(arg0);
	}
	public void mouseExited(MouseEvent arg0) {
		cm.mouseExited(arg0);
	}
	public void mousePressed(MouseEvent arg0) {
		cm.mousePressed(arg0);
	}
	public void mouseReleased(MouseEvent arg0) {
		cm.mouseReleased(arg0);
	}

	public StaticObject getBackUpPhoto() {
		return backUpPhoto;
	}

	public void setBackUpPhoto(StaticObject backUpPhoto) {
		this.backUpPhoto = backUpPhoto;
	}
}
