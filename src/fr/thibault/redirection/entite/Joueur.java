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
	private int move;
	private boolean peutBouger;
	
	private int speed;
	
	private Texture texture;
	private Texture bombe;
	
	private boolean aBombe;
	
	public Joueur(){
		this.x = 1;
		this.y = 1;
		this.move = Jeu.RIGHT;
		this.peutBouger = true;
		this.speed = 20;
		
		this.texture = new Texture("joueur.png", GL_NEAREST);
		this.bombe = new Texture("bombe_icon.png", GL_NEAREST);
		
		this.aBombe = false;
		
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
			if(move == Jeu.RIGHT && !Terrain.blocs[x + 1][y].getEstSolide())
				x ++;
			
			if(move == Jeu.LEFT && !Terrain.blocs[x - 1][y].getEstSolide())
				x --;
			
			if(move == Jeu.DOWN && !Terrain.blocs[x][y + 1].getEstSolide())
				y ++;
			
			if(move == Jeu.UP && !Terrain.blocs[x][y - 1].getEstSolide())
				y --;
    			
    		if (!Terrain.blocs[x][y].getType().equalsIgnoreCase("REVE")){
    			if(move == Jeu.RIGHT && Terrain.blocs[x + 1][y].getEstSolide())
    				move = Jeu.DOWN;
    			
    			if(move == Jeu.LEFT && Terrain.blocs[x - 1][y].getEstSolide())
    				move = Jeu.UP;
    			
    			if(move == Jeu.DOWN && Terrain.blocs[x][y + 1].getEstSolide())
    				move = Jeu.LEFT;
    			
    			if(move == Jeu.UP && Terrain.blocs[x][y - 1].getEstSolide())
    				move = Jeu.RIGHT;
			}
    		
    		/* Verification du bloc de tp */
    		if (Terrain.blocs[x][y].getType().equalsIgnoreCase("TPD"))
    			setXY((int)Niveau.level.getTpAPos().x, (int)Niveau.level.getTpAPos().y);
			
    		/* Verification des blocs reverse */
			if(move == Jeu.UP && Terrain.blocs[x][y].getType().equalsIgnoreCase("REVE"))
				move = Jeu.DOWN;
			
			else if(move == Jeu.DOWN && Terrain.blocs[x][y].getType().equalsIgnoreCase("REVE"))
				move = Jeu.UP;
			
			else if(move == Jeu.RIGHT && Terrain.blocs[x][y].getType().equalsIgnoreCase("REVE"))
				move = Jeu.LEFT;
			
			else if(move == Jeu.LEFT && Terrain.blocs[x][y].getType().equalsIgnoreCase("REVE"))
				move = Jeu.RIGHT;
			
			if(Terrain.blocs[x][y].getType() == "WIN"){
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
			Text.drawText(600, 150, "Tu as gagne !", 16, new Color(Color.BLACK));
			Jeu.i.nivTermine = true;
			Jeu.i.niveauAtteint = Jeu.i.numNiveau + 1;
		}else{
			Text.drawText(600, 150, "     Niveau:\nLe but de ce jeu est de\nparvenir a la croix rouge\nen posant des blocs pour\nfaire tourner le joueur.", 12, new Color(Color.BLACK));
		}
		
		if(aBombe){
			bombe.bind();
			Formes.carre(200, 530, 50, 50);
			Texture.unbind();
		}
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getMove(){
		return move;
	}
	
	public void setMove(int v){
		this.move = v;
	}
	
	public boolean getBombe(){
		return aBombe;
	}
	
	public void setBombe(boolean v){
		this.aBombe = v;
	}
}
