package fr.thibault.redirection.terrain.blocs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Blocs {
	
	String texture;
	boolean estSolide;
	
	Image img;
	
	public Blocs(String texture, boolean estSolide) throws SlickException{
		this.texture = texture;
		this.estSolide = estSolide;
		
		img = new Image(texture);
	}
	
	public void render(Graphics g, int x, int y){
		img.draw(x, y, 50, 50);
	}
}
