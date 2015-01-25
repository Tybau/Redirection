package fr.thibault.redirection.niveau;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.entite.Joueur;
import fr.thibault.redirection.terrain.Terrain;

public class Niveau {
	
	int nbNiv = 5;
	
	Terrain niveauCharge;
	Joueur joueur;
	
	public static int nbCase;
	public static int taille;
	public static int vitesse = 2;
	
	public Niveau(int niv, int nbBlocs, int nbCase) throws SlickException{
		Niveau.nbCase = nbCase;
		Niveau.taille = 500 / nbCase;
		
		niveauCharge = new Terrain("/assets/textures/niveaux/NIV_" + niv + ".png", nbBlocs);
		joueur = new Joueur("/assets/textures/joueur.png");
	}
	
	public void update(GameContainer container){
		niveauCharge.update(container, joueur.x, joueur.y);
		joueur.update(container);
	}
	
	public void render(Graphics g){
		niveauCharge.render(g);
		joueur.render(g);
	}
	
	public int recupNbCase(){
		return nbCase;
	}
	
	public int recupTaille(){
		return taille;
	}
}