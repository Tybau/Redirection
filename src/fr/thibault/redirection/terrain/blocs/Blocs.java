package fr.thibault.redirection.terrain.blocs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.niveau.Niveau;

public class Blocs {
	
	String texture;
	public boolean estSolide;
	public String type;
	
	Image img;
	
	public Blocs(String texture, String type, boolean estSolide) throws SlickException{
		this.texture = texture;
		this.type = type;
		this.estSolide = estSolide;
		
		img = new Image("/assets/textures/blocs/" + texture + ".png");
	}
	
	public void render(Graphics g, int x, int y){
		img.draw(x * Niveau.taille, y * Niveau.taille, Niveau.taille, Niveau.taille);
	}
}
