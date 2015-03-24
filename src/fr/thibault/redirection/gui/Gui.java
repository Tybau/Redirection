package fr.thibault.redirection.gui;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Inputs;
import fr.thibault.redirection.utils.Texture;

public class Gui {
	
	private static Texture font = new Texture("fonts/font.png", GL_NEAREST);

	private static String chars = "" + //
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + //
			"0123456789.,!?'\"-+=/\\%()<>:;     " + //
			"";

	public static void drawText(String text, float x, float y, int size) {
		String msg = text.toUpperCase();
		glColor3f(1, 1, 1);
		font.bind();
		glBegin(GL_QUADS);
		for (int i = 0; i < msg.length(); i++) {
			int ix = chars.indexOf(msg.charAt(i));
			int iy = 0;
			if (ix >= 32)
				iy = 1;
			if (iy >= 0) {
				if (ix >= 0) {
					float xx = x + i * size;
					float yy = y;

					float yo = iy;
					float xo = ix % 32;
					float texSize = 32.0f;

					glTexCoord2f((1 + xo) / texSize, (0 + yo) / 2.0f);
					glVertex2f(xx + size, yy);
					glTexCoord2f((0 + xo) / texSize, (0 + yo) / 2.0f);
					glVertex2f(xx, yy);
					glTexCoord2f((0 + xo) / texSize, (1 + yo) / 2.0f);
					glVertex2f(xx, yy + size);
					glTexCoord2f((1 + xo) / texSize, (1 + yo) / 2.0f);
					glVertex2f(xx + size, yy + size);
				}
			}
		}
		Texture.unbind();
		glEnd();
	}

	public static void drawText(String text, float x, float y, int size, Vector3f color) {
		String msg = text.toUpperCase();
		glColor3f(color.x, color.y, color.z);
		font.bind();
		glBegin(GL_QUADS);
		for (int i = 0; i < msg.length(); i++) {
			int ix = chars.indexOf(msg.charAt(i));
			int iy = 0;
			if (ix >= 32)
				iy = 1;
			if (iy >= 0) {
				if (ix >= 0) {
					float xx = x + i * size;
					float yy = y;

					float yo = iy;
					float xo = ix % 32;
					float texSize = 32.0f;

					glTexCoord2f((1 + xo) / texSize, (0 + yo) / 2.0f);
					glVertex2f(xx + size, yy);
					glTexCoord2f((0 + xo) / texSize, (0 + yo) / 2.0f);
					glVertex2f(xx, yy);
					glTexCoord2f((0 + xo) / texSize, (1 + yo) / 2.0f);
					glVertex2f(xx, yy + size);
					glTexCoord2f((1 + xo) / texSize, (1 + yo) / 2.0f);
					glVertex2f(xx + size, yy + size);
				}
			}
		}
		Texture.unbind();
		glEnd();
	}

	public static boolean button(String text, int x, int y) {
		boolean pressed = false;

		int mx = Mouse.getX();
		int my = Display.getHeight() - Mouse.getY();

		int h = 32;
		int w = text.length() * 24 + 100;
		
		Texture.unbind();
		
		glColor4f(0, 0, 0, 1);
		Formes.carre(x-3, y-3, w+6, h+6);
		
		glColor4f(0.5f, 0.5f, 0.5f, 1);
		if (mx > x && my > y && mx <= x + w && my <= y + h) {
			glColor4f(0.8f, 0.5f, 0.5f, 1);
			if (Inputs.isMouseButtonRelased(0)) {
				glColor4f(1, 1, 1, 1);
				pressed = true;
			}
		}
		Formes.carre(x, y, w, h);

		drawText(text, x + w / 2 - (text.length() * 24) / 2, y + h / 2 - 12, 24);
		Texture.unbind();

		glColor4f(1, 1, 1, 1);

		return pressed;
	}
}
