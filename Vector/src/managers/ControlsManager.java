package managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ControlsManager implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener
{
	public boolean UP, DOWN, LEFT, RIGHT;
	public boolean SPACE, CTRL, SHIFT, ALT;
	public boolean W,A,S,D,Q,E,R;
	
	public boolean mLEFT, mRIGHT, mCENTRE;
	public float mouseWeel;
	public int mX, mY; //ostatnia pozycja myszy
	public int DX, DY; //przesuniêcie myszy
	public ControlsManager() 
	{
		
	}
	
	public void reset()
	{
		DX=0;
		DY=0;
		mouseWeel = 0;
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int in = e.getKeyCode();
		if(in==KeyEvent.VK_UP)
			UP=true;
		else if(in==KeyEvent.VK_DOWN)
			DOWN=true;
		else if(in==KeyEvent.VK_LEFT)
			LEFT=true;
		else if(in==KeyEvent.VK_RIGHT)
			RIGHT=true;
		else if(in==KeyEvent.VK_SPACE)
			SPACE=true;
		else if(in==KeyEvent.VK_CONTROL)
			CTRL=true;
		else if(in==KeyEvent.VK_SHIFT)
			SHIFT=true;
		else if(in==KeyEvent.VK_ALT)
			ALT=true;
		else if(in==KeyEvent.VK_W)
			W=true;
		else if(in==KeyEvent.VK_A)
			A=true;
		else if(in==KeyEvent.VK_S)
			S=true;
		else if(in==KeyEvent.VK_D)
			D=true;
		else if(in==KeyEvent.VK_Q)
			Q=true;
		else if(in==KeyEvent.VK_R)
			R=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int in = e.getKeyCode();
		if(in==KeyEvent.VK_UP)
			UP=false;
		else if(in==KeyEvent.VK_DOWN)
			DOWN=false;
		else if(in==KeyEvent.VK_LEFT)
			LEFT=false;
		else if(in==KeyEvent.VK_RIGHT)
			RIGHT=false;
		else if(in==KeyEvent.VK_SPACE)
			SPACE=false;
		else if(in==KeyEvent.VK_CONTROL)
			CTRL=false;
		else if(in==KeyEvent.VK_SHIFT)
			SHIFT=false;
		else if(in==KeyEvent.VK_ALT)
			ALT=false;
		else if(in==KeyEvent.VK_W)
			W=false;
		else if(in==KeyEvent.VK_A)
			A=false;
		else if(in==KeyEvent.VK_S)
			S=false;
		else if(in==KeyEvent.VK_D)
			D=false;
		else if(in==KeyEvent.VK_Q)
			Q=false;
		else if(in==KeyEvent.VK_R)
			R=false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseWeel = e.getWheelRotation();
//		System.out.println(mouseWeel);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		DX=mX-e.getX();
		DY=mY-e.getY();
		
		mX = e.getX();
		mY = e.getY();
		//System.out.println("mysz "+mX+" "+mY+" "+DX+" "+DY);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		DX=e.getX()-mX;
		DY=e.getY()-mY;
		
		mX = e.getX();
		mY = e.getY();
		//System.out.println("mysz poruszono "+mX+" "+mY+" "+DX+" "+DY);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int in = e.getButton();
		if(in == MouseEvent.BUTTON1)
		mLEFT = true;
		else if(in == MouseEvent.BUTTON1)
		mCENTRE = true;
		else if(in == MouseEvent.BUTTON1)	
		mRIGHT = true;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int in = e.getButton();
		if(in == MouseEvent.BUTTON1)
		mLEFT = true;
		else if(in == MouseEvent.BUTTON1)
		mCENTRE = true;
		else if(in == MouseEvent.BUTTON1)	
		mRIGHT = true;
				
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int in = e.getButton();
		if(in == MouseEvent.BUTTON1)
		mLEFT = false;
		else if(in == MouseEvent.BUTTON1)
		mCENTRE = false;
		else if(in == MouseEvent.BUTTON1)	
		mRIGHT = false;
		
	}

}
