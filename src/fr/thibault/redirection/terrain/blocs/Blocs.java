package fr.thibault.redirection.terrain.blocs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.terrain.Terrain;

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
		img.draw(x * Terrain.taille, y * Terrain.taille, 50, 50);
	}
}
