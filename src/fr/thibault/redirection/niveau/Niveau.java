package fr.thibault.redirection.niveau;

import fr.thibault.redirection.entite.Joueur;
import fr.thibault.redirection.terrain.Terrain;

public class Niveau {
		
	private Terrain niveauCharge;
	private Joueur joueur;
	
	public static int nbCase;
	public static int taille;
	
	public Niveau(int niv, int nbBlocs){
		nbCase = 10;
		taille = 500 / nbCase;
		this.niveauCharge = new Terrain(niv, nbBlocs);
		this.joueur = new Joueur();
	}
	
	public void update(){		
		this.joueur.update();
		this.niveauCharge.update(joueur.getX(), joueur.getY());
	}
	
	public void render(){
		this.niveauCharge.render();
		this.joueur.render();
	}
}