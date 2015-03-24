package fr.thibault.redirection.utils;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.Color;

public class Text
{
	private static Texture[] fonts = new Texture[] {new Texture("fonts/font2 - 1.png", GL_LINEAR), new Texture("fonts/font2 - 2.png", GL_LINEAR), new Texture("fonts/font2 - 3.png", GL_LINEAR), new Texture("fonts/font2 - 4.png", GL_LINEAR)};
	private static boolean toEnderligne = false;
	
	public static final String chars 	= "abcdefghijklm"	//
                            			+ "nopqrstuvwxyz"	//
                            			+ "ABCDEFGHIJKLM"	//
                            			+ "NOPQRSTUVWXYZ"	//
                            			+ "0123456789!?#"	//
                            			+ "(){}+-%/\\*\"'@"	//
                            			+ "[]ιΰθη_&^$€.," 	//
                            			+ ";:κλτφµω     ";	//

	public static float getStringSize(int textSize, int maxX)
	{
		if(textSize >= maxX)
			return maxX;
		else
			return (float)textSize / 2.0f;
	}

	public static int getPurcent(int purcent, int i)
	{
		return i - (i * purcent / 100);
	}

	public static String formatString(String lines, int max)
	{
		String maChaine = lines;
		int i = 0, index = -1;
		int searchMax = max;
		if (max > lines.length()) searchMax = lines.length();
		boolean canProcede = false;
		for(int j = 1; j < searchMax; j++)
		{
			if (lines.charAt(j) == ' ') canProcede = true;
		}
		while(i + max < lines.length() - 1)
		{
			if (canProcede) index = maChaine.substring(i, i + max).lastIndexOf(' ');
			else index = searchMax;
			if(index != -1)
				if (canProcede) maChaine = maChaine.substring(0, (index + i) + 1) + '\n' + maChaine.substring((index + i) + 1);
				else maChaine = maChaine.substring(0, (index + i)) + '\n' + maChaine.substring((index + i));
			else
				maChaine = maChaine.substring(0, i + 1) + '\n' + maChaine.substring(i + 1);
			i += index + 1;
		}
		return maChaine;
	}
	
	public static void text(int x, int y, int Xe, String text, int size, Color color, boolean centered)
	{
		float textSize = ((float)(text.length()) / 2.0f);
		if(text.length() > Xe) textSize = Xe;

		if(centered) Xe*=2;

		int sizeX = getPurcent(40, size);
		glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);

		fonts[getFontSize(size)].bind();
		
		text = formatString(text, Xe);
		int b = 0;
		
		glBegin(GL_QUADS);
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i) == '\n')
			{
				b = 0;
				y += size;
				if(text.charAt(i) == '\n')
					continue;
			}
			
			if(centered)
			{
				charData((float)(x - (textSize * sizeX)) + b * (sizeX), y, text.charAt(i), size, false, false);
			}
			else
			{
				charData(x + b * (sizeX), y, text.charAt(i), size, false, false);
			}
			b++;
		}
		glEnd();
		Texture.unbind();

		glColor4f(1, 1, 1, 1);
	}
	
	public static void text(int x, int y, int Xe, String text, int size, Color color, boolean centered, boolean underligned, boolean backLigned)
	{
		float textSize = ((float)(text.length()) / 2.0f);
		if(text.length() > Xe) textSize = Xe;

		if(centered) Xe*=2;

		int sizeX = getPurcent(40, size);
		glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);

		fonts[getFontSize(size)].bind();
		
		text = formatString(text, Xe);
		int b = 0;
		
		glBegin(GL_QUADS);
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i) == '\n')
			{
				b = 0;
				y += size;
				if(text.charAt(i) == '\n')
					continue;
			}

			boolean lowChar =  text.charAt(i) == 'g'
        					|| text.charAt(i) == 'j'
        					|| text.charAt(i) == 'p'
        					|| text.charAt(i) == 'q'
        					|| text.charAt(i) == 'y'
        					|| text.charAt(i) == '('
        					|| text.charAt(i) == ')'
        					|| text.charAt(i) == '}'
        					|| text.charAt(i) == '{'
        					|| text.charAt(i) == ']'
        					|| text.charAt(i) == '['
        					|| text.charAt(i) == 'µ'
        					|| text.charAt(i) == '/'
        					|| text.charAt(i) == '\\';
			if (underligned) toEnderligne = lowChar ? false : true;
			else toEnderligne = false;
			
			if(centered)
			{
				charData((float)(x - (textSize * sizeX)) + b * (sizeX), y, text.charAt(i), size, toEnderligne, backLigned);
			}
			else
			{
				charData(x + b * (sizeX), y, text.charAt(i), size, toEnderligne, backLigned);
			}
			b++;
		}
		glEnd();
		Texture.unbind();

		glColor4f(1, 1, 1, 1);
	}
	

	private static int getFontSize(int size)
	{
		int f = 0;
		if(size >= 48)
		{
			f = 0;
		}
		else if(size >= 24 || size < 48)
		{
			f = 1;
		}
		else if(size >= 12 || size < 24)
		{
			f = 2;
		}
		else
		{
			f = 3;
		}

		return f;
	}
	
	private static void charData(float x, float y, char character, int size, boolean underligned, boolean backLigned)
	{
		int sizeX = getPurcent(40, size);
		int tex = chars.indexOf(character);
		float w = 13.0f;
		float h = 13.0f;
		float xo = tex % (int)w;
		float yo = tex / (int)w;

		if(backLigned)
		{
			glColor4f(0.5f, 0.5f, 0.5f, 0.8f);
				glTexCoord2f((0 + 12) / w, (0 + 12) / h);
    			glVertex2f(x+2, y);
    			glVertex2f(x + sizeX+2, y);
    			glVertex2f(x + sizeX+2, y + size);
    			glVertex2f(x+2, y + size);
			glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		}
		
		if(underligned)
		{
			glTexCoord2f((0 + 12) / w, (0 + 12) / h);
			glVertex2f(x+2, y + size - size / 10);
			glVertex2f(x + sizeX + 2, y + size - size / 10);
			glVertex2f(x + sizeX + 2, y + size);
			glVertex2f(x+2, y + size);
		}
		
		glTexCoord2f((0 + xo) / w, (0 + yo) / h);
		glVertex2f(x, y);

		glTexCoord2f((1 + xo) / w, (0 + yo) / h);
		glVertex2f(x + size, y);

		glTexCoord2f((1 + xo) / w, (1 + yo) / h);
		glVertex2f(x + size, y + size);

		glTexCoord2f((0 + xo) / w, (1 + yo) / h);
		glVertex2f(x, y + size);
	}
}