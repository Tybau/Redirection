package fr.thibault.redirection.entite;

import static org.lwjgl.opengl.GL11.GL_NEAREST;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.lwjgl.util.Color;

import fr.thibault.redirection.Jeu;
import fr.thibault.redirection.gui.GuiButton;
import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.screens.GameScreen;
import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Text;
import fr.thibault.redirection.utils.Texture;

public class Joueur {
	
	private int x, y;
	private int move;
	private boolean peutBouger;
	
	private int speed;
	
	private boolean aBombe;
	
	private Texture texture;
	private Texture bombe;
	
	private FileWriter fw;
	private BufferedWriter out;
	
	public Joueur(){
		this.x = 1;
		this.y = 1;
		this.move = Jeu.RIGHT;
		this.peutBouger = true;
		this.speed = 20;
		
		try {
			this.fw = new FileWriter("redirection.data");
			this.out = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
			if(move == Jeu.RIGHT && !Niveau.level.getTerrain().getBloc(x + 1, y).getEstSolide())
				x ++;
			
			if(move == Jeu.LEFT && !Niveau.level.getTerrain().getBloc(x - 1, y).getEstSolide())
				x --;
			
			if(move == Jeu.DOWN && !Niveau.level.getTerrain().getBloc(x, y + 1).getEstSolide())
				y ++;
			
			if(move == Jeu.UP && !Niveau.level.getTerrain().getBloc(x, y - 1).getEstSolide())
				y --;
    			
    		if (!Niveau.level.getTerrain().getBloc(x, y).getType().equalsIgnoreCase("REVE")){
    			if(move == Jeu.RIGHT && Niveau.level.getTerrain().getBloc(x + 1 ,y).getEstSolide())
    				move = Jeu.DOWN;
    			
    			if(move == Jeu.LEFT && Niveau.level.getTerrain().getBloc(x - 1, y).getEstSolide())
    				move = Jeu.UP;
    			
    			if(move == Jeu.DOWN && Niveau.level.getTerrain().getBloc(x, y + 1).getEstSolide())
    				move = Jeu.LEFT;
    			
    			if(move == Jeu.UP && Niveau.level.getTerrain().getBloc(x, y - 1).getEstSolide())
    				move = Jeu.RIGHT;
			}
    		
    		/* Verification du bloc de tp */
    		if (Niveau.level.getTerrain().getBloc(x, y).getType().equalsIgnoreCase("TPD"))
    			setXY((int)Niveau.level.getTpAPos().x, (int)Niveau.level.getTpAPos().y);
			
    		/* Verification des blocs reverse */
			if(move == Jeu.UP && Niveau.level.getTerrain().getBloc(x, y).getType().equalsIgnoreCase("REVE"))
				move = Jeu.DOWN;
			
			else if(move == Jeu.DOWN && Niveau.level.getTerrain().getBloc(x, y).getType().equalsIgnoreCase("REVE"))
				move = Jeu.UP;
			
			else if(move == Jeu.RIGHT && Niveau.level.getTerrain().getBloc(x, y).getType().equalsIgnoreCase("REVE"))
				move = Jeu.LEFT;
			
			else if(move == Jeu.LEFT && Niveau.level.getTerrain().getBloc(x, y).getType().equalsIgnoreCase("REVE"))
				move = Jeu.RIGHT;
			
			if(Niveau.level.getTerrain().getBloc(x, y).getType() == "WIN"){
				peutBouger = false;
			}
			t = 0;
		}
		t++;
	}
	
	public void render(){
		this.texture.bind();
		Formes.carre(x * Niveau.level.getTaille(), y *  Niveau.level.getTaille(),  Niveau.level.getTaille(),  Niveau.level.getTaille());
		Texture.unbind();
		
		if(!peutBouger){
			Text.drawText(600, 150, "Tu as gagne !", 16, new Color(Color.BLACK));
			Jeu.i.nivTermine = true;
			if(Jeu.i.numNiveau == Jeu.i.niveauAtteint && Jeu.i.niveauAtteint < Jeu.i.niveauMax){
				Jeu.i.niveauAtteint++;
				try {
					this.out.write("save:" + Jeu.i.niveauAtteint);
					this.out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(Jeu.i.numNiveau < Jeu.i.niveauMax){
				if(GuiButton.create("Suivant", 600, 500)){
					Jeu.i.numNiveau ++;
					Jeu.i.setCurrentScreen(new GameScreen(Jeu.i.numNiveau, Jeu.i.nbBlocs, Jeu.i.nbBlocsSup));
				}
			}
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
