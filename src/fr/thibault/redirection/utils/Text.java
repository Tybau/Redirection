package fr.thibault.redirection.utils;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.Color;

public class Text {
	
	private static Texture font = new Texture("fonts/ascii.png", GL_NEAREST);
	
	private static String chars = ""
			+ " ²²²²²²²²²²²²²²²"
			+ "²²²²²²²²²²²²²²²²"
			+ " !\"#$%&'()*+,-./" 
			+ "0123456789:;<=>?"
			+ "@ABCDEFGHIJKLMNO"
			+ "PQRSTUVWXYZ[\\]^_"		
			+ "'abcdefghijklmno"
			+ "pqrstuvwxyz{|}~²"
			+ "²üéâà²çê²èïîì²²";	//Emplacement des chatactères dans la texture

	public static void drawText(int x, int y, String text, int size, Color c) {		//Dessin d'un texte
		int charWidth = (int) (size * (3.0f / 4.0f));
		int xOffset = 0;
		int yOffset = 0;

		font.bind();
		glColor3f(c.getRed(), c.getGreen(), c.getBlue());		//Reglage de la couleur
		glBegin(GL_QUADS);
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '\n') {		//Pour le retour à la ligne
				yOffset += size;
				xOffset = 0;
			} else {
				charData(text.charAt(i), x + xOffset * charWidth, y + yOffset, size);		//Dessin du charactère en fonction de la chaine chars
				xOffset++;
			}
		}
		glEnd();
		glColor3f(1, 1, 1);		//Couleur remise à Blanc
		Texture.unbind();
	}

	public static void charData(char character, int x, int y, int size) {		//Dessin d'un charactère
		int index = chars.indexOf(character);
		int xo = index % 16;
		int yo = index / 16;

		glTexCoord2f((0 + xo) / 16.0f, (0 + yo) / 16.0f);		glVertex2f(x, y);
		glTexCoord2f((1 + xo) / 16.0f, (0 + yo) / 16.0f);		glVertex2f(x + size, y);
		glTexCoord2f((1 + xo) / 16.0f, (1 + yo) / 16.0f);		glVertex2f(x + size, y + size);
		glTexCoord2f((0 + xo) / 16.0f, (1 + yo) / 16.0f);		glVertex2f(x, y + size);
	}
}