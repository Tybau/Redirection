package fr.thibault.redirection.utils;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Inputs
{
	static boolean[] keyswp = new boolean[Keyboard.getKeyCount()+200];
	static boolean[] buttonswp = new boolean[Mouse.getButtonCount()];
	
	public static boolean isKeyboardKeyPressed(int key)
	{
		if (Keyboard.isKeyDown(key))
		{
			if (keyswp[key] == false)
			{
				keyswp[key] = true;
				return true;
			}
		}
		else if(keyswp[key] == true) keyswp[key] = false;

		return false;
	}
	
	public static boolean isMouseButtonPressed(int button)
	{
		if (Mouse.isButtonDown(button))
		{
			if (buttonswp[button] == false)
			{
				buttonswp[button] = true;
				return true;
			}
		}
		else if(buttonswp[button] == true) buttonswp[button] = false;
		return false;
	}
	
	public static boolean isMouseButtonRelased(int button)
	{
		if (Mouse.isButtonDown(button))
		{
			if (buttonswp[button] == false)
			{
				buttonswp[button] = true;
			}
			return false;
		}
		else
		{
			if(buttonswp[button] == true)
			{
				buttonswp[button] = false;
				return true;
			}
		}
		return false;
	}

	public static void resetMouseButton(int button)
	{
		if(buttonswp[button] == true)
		{
			buttonswp[button] = false;
		}
	}
}
