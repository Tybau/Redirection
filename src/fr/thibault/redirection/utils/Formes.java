package fr.thibault.redirection.utils;

import static org.lwjgl.opengl.GL11.*;

public class Formes {
	
	public static void carre(int x, int y, int width, int height){
		glPushMatrix();
		glTranslatef(x, y, 0);
		glScalef(width, height, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex2f(0, 0);
		glTexCoord2f(1, 0); glVertex2f(1, 0);
		glTexCoord2f(1, 1); glVertex2f(1, 1);
		glTexCoord2f(0, 1); glVertex2f(0, 1);
		glEnd();
		glPopMatrix();
	}
}
