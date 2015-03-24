package fr.thibault.redirection.entite;

import static org.lwjgl.opengl.GL11.GL_NEAREST;

import org.lwjgl.util.Color;

import fr.thibault.redirection.Jeu;
import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.terrain.Terrain;
import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Text;
import fr.thibault.redirection.utils.Texture;

public class Joueur {
	
	private int x, y;
	private String move;
	private boolean peutBouger;
	
	private int speed;
	
	private Texture texture;
	
	public Joueur(){
		this.x = 1;
		this.y = 1;
		this.move = "R";
		this.peutBouger = true;
		this.speed = 20;
		
		this.texture = new Texture("joueur.png", GL_NEAREST);
		
		Jeu.i.nivTermine = false;
		switch(Jeu.i.difficulte){
			case 1:
				speed = 15;
				break;
			case 2:
				speed = 12;
				break;
			default:
				speed = 20;
		}
	}
	
	private int t = 0;
	public void update(){
		if(t == speed && peutBouger){
			if(move == "R" && !Terrain.blocs[x + 1][y].estSolide)
				x ++;
			
			if(move == "L" && !Terrain.blocs[x - 1][y].estSolide)
				x --;
			
			if(move == "D" && !Terrain.blocs[x][y + 1].estSolide)
				y ++;
			
			if(move == "U" && !Terrain.blocs[x][y - 1].estSolide)
				y --;
			
			if(move == "R" && Terrain.blocs[x + 1][y].estSolide)
				move = "D";
			
			if(move == "L" && Terrain.blocs[x - 1][y].estSolide)
				move = "U";
			
			if(move == "D" && Terrain.blocs[x][y + 1].estSolide)
				move = "L";
			
			if(move == "U" && Terrain.blocs[x][y - 1].estSolide)
				move = "R";
			
			if(Terrain.blocs[x][y].type == "WIN"){
				peutBouger = false;
			}
			t = 0;
		}
		t++;
	}
	
	public void render(){
		this.texture.bind();
		Formes.carre(x * Niveau.taille, y *  Niveau.taille,  Niveau.taille,  Niveau.taille);
		Texture.unbind();
		
		if(!peutBouger){
			Text.text(600, 150, 255, "Tu as gagné !", 16, new Color(Color.RED), false);
			Jeu.i.nivTermine = true;
			Jeu.i.niveauAtteint = Jeu.i.numNiveau + 1;
		}else{
			Text.text(600, 150, 255, "     Niveau:\nLe but de ce jeu est de\nparvenir à la croix rouge\nen posant des blocs pour\nfaire tourner le joueur.", 16, new Color(Color.RED), false);
		}
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
