package fr.thibault.redirection.niveau;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.Jeu;
import fr.thibault.redirection.entite.Joueur;
import fr.thibault.redirection.terrain.Terrain;

public class Niveau {
	
	Input input;
	
	Image niveau;
		
	Terrain niveauCharge;
	Joueur joueur;
	
	public static int nbCase;
	public static int taille;
	public static int vitesse = 3;
	
	public Niveau(int niv, int nbBlocs) throws SlickException{		
		niveau = new Image("/assets/textures/niveaux/NIV_" + niv + ".png");
		nbCase = niveau.getWidth();
		taille = 500 / nbCase;
		niveauCharge = new Terrain(niveau, nbBlocs);
		joueur = new Joueur("/assets/textures/joueur.png");
	}
	
	@SuppressWarnings("static-access")
	public void update(GameContainer container){
		input = container.getInput();
		
		niveauCharge.update(container, joueur.x, joueur.y);
		joueur.update(container);
		
		if(input.isKeyPressed(input.KEY_ESCAPE))
			Jeu.scene = "MENU";
	}
	
	public void render(Graphics g){
		niveauCharge.render(g);
		joueur.render(g);
	}
}