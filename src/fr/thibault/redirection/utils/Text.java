package fr.thibault.redirection.utils;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.Color;

public class Text {
	
	private static Texture font = new Texture("fonts/font.png", GL_NEAREST);

	private static String chars = "" +
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ      " +
			"0123456789.,!?'\"-+=/\\%()<>:;     ";		//Chaine de charactère montrant l'emplacement des lettres dans le font.png

	public static void drawText(int x, int y, String text, int size, Color color) {
		int line = 0;
		int character = 0;

		String msg = text.toUpperCase();		//On met tout le texte en majuscule
		glColor3f(color.getRed(), color.getGreen(), color.getBlue());		//On règle la couleur sur celle choisis
		font.bind();		//On utilise la texture font.png
		glBegin(GL_QUADS);
		for (int i = 0; i < msg.length(); i++) {

			if (msg.charAt(i) == '\n') {		//On vérifie si le charactère n'est pas celui d'un retour à la ligne
				line++;
				character = 0;
			}

			//On recherche l'emplacement du charactère dans la texture
			int ix = chars.indexOf(msg.charAt(i));
			int iy = 0;
			if (ix >= 32)
				iy = 1;
			if (iy >= 0) {
				if (ix >= 0) {
					float xx = x + character * size;
					float yy = y + line * (size + 2);

					float yo = iy;
					float xo = ix % 32;
					float texSize = 32.0f;

					//On dessine le charactère dans un carré 
					glTexCoord2f((1 + xo) / texSize, (0 + yo) / 2.0f);		glVertex2f(xx + size, yy);
					glTexCoord2f((0 + xo) / texSize, (0 + yo) / 2.0f);		glVertex2f(xx, yy);
					glTexCoord2f((0 + xo) / texSize, (1 + yo) / 2.0f);		glVertex2f(xx, yy + size);
					glTexCoord2f((1 + xo) / texSize, (1 + yo) / 2.0f);		glVertex2f(xx + size, yy + size);
				}
			}
			character++;
		}
		glEnd();
		Texture.unbind();		//On décharge la texture font.png
		glColor3f(1, 1, 1);			//On remet la couleur à blanc
	}
}