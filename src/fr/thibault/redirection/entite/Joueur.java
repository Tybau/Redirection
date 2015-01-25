package fr.thibault.redirection.entite;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.GameMain;
import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.terrain.Terrain;

public class Joueur {
	
	String texture;
	Image img;
	
	public int x = 1, y = 1;
	String move = "R";
	boolean peutBouger = true;
	
	long dernTicks = GameMain.ticks;
	
	public Joueur(String texture) throws SlickException{
		this.texture = texture;
		
		img = new Image(texture);
	}
	
	public void update(GameContainer container){		
		if (GameMain.ticks >= dernTicks + 60 / Niveau.vitesse && peutBouger){
			dernTicks += 60 / Niveau.vitesse ;
			
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
		}
	}
	
	public void render(Graphics g){
		img.draw(x * Niveau.taille, y * Niveau.taille, Niveau.taille, Niveau.taille);
		if(!peutBouger){
			g.drawString("Tu as gagné!", 600, 150);
		}
	}
}
