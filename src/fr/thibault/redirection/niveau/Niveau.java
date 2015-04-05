package fr.thibault.redirection.niveau;

import org.lwjgl.util.vector.Vector2f;

import fr.thibault.redirection.entite.Joueur;
import fr.thibault.redirection.terrain.Terrain;

public class Niveau {
	
	public static Niveau level;
	
	private Terrain niveauCharge;
	private Joueur joueur;
	
	public static int nbCase;
	public static int taille;
	private int xTpA, yTpA;
	
	public Niveau(int niv, int nbBlocs){
		level = this;
		
		nbCase = 10;
		taille = 500 / nbCase;
		this.xTpA = 1;
		this.yTpA = 1;
		
		this.niveauCharge = new Terrain(niv, nbBlocs);
		this.joueur = new Joueur();
		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(Terrain.blocs[i][j].getType().equalsIgnoreCase("TPA")){
					this.xTpA = i;
					this.yTpA = j;
				}
			}
		}
	}
	
	public void update(){
		this.joueur.update();
		this.niveauCharge.update(joueur);
	}
	
	public void render(){
		this.niveauCharge.render();
		this.joueur.render();
	}
	
	public Vector2f getTpAPos(){
		return new Vector2f(this.xTpA, this.yTpA);
	}
	
	public Joueur getJoueur(){
		return joueur;
	}
}