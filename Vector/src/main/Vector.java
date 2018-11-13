package main;

import windows.Window;

public class Vector {

	public static void main(String args[])
	{
		Window bs = new Window(1280, 720, 100, 100, "Vector");
		bs.initializeData();
		while(bs.isVisible())
		{
			bs.refreshFrame();
		}
		bs.clearUp();
	}
}
