package fr.thibault.redirection.gui;

import static org.lwjgl.opengl.GL11.glColor4f;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Color;

import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Inputs;
import fr.thibault.redirection.utils.Text;
import fr.thibault.redirection.utils.Texture;

public class GuiButton {
	
	public static boolean create(String text, int x, int y){
		boolean pressed = false;

		int mx = Mouse.getX();
		int my = Display.getHeight() - Mouse.getY();

		int height = 32;
		int width = text.length() * 24 + 100;
		
		Texture.unbind();
		
		glColor4f(0, 0, 0, 1);
		Formes.carre(x - 3, y - 3, width + 6, height + 6);
		
		glColor4f(0.5f, 0.5f, 0.5f, 1);
		if (mx > x && my > y && mx <= x + width && my <= y + height) {
			glColor4f(0.8f, 0.5f, 0.5f, 1);
			if (Inputs.isMouseButtonRelased(0)) {
				glColor4f(1, 1, 1, 1);
				pressed = true;
			}
		}
		Formes.carre(x, y, width, height);

		Text.drawText(x + width / 2 - (text.length() * 24) / 2, y + height / 2 - 12, text,  24, new Color(Color.WHITE));
		Texture.unbind();

		glColor4f(1, 1, 1, 1);

		return pressed;
	}
}
