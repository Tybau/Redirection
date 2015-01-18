package fr.thibault.redirection.niveau;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.terrain.Terrain;

public class Niveau {
	
	int nbNiv = 5;
	
	Terrain niveauCharge;
	
	public Niveau(int niv, int nbBlocs) throws SlickException{
		niveauCharge = new Terrain("/assets/textures/niveaux/NIV_" + niv + ".png", nbBlocs);
	}
	
	public void update(GameContainer container){
		niveauCharge.update(container);
	}
	
	public void render(Graphics g){
		niveauCharge.render(g);
	}
}