package fr.thibault.redirection.entite;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.GameMain;
import fr.thibault.redirection.terrain.Terrain;

public class Joueur {
	
	String texture;
	Image img;
	
	int xJ = 1, yJ = 1;
	String move = "R";
	
	int taille = 50;
	
	long dernTicks = GameMain.ticks;
	
	public Joueur(String texture) throws SlickException{
		this.texture = texture;
		
		img = new Image(texture);
	}
	
	public void update(GameContainer container){
		if (GameMain.ticks >= dernTicks + 30){
			dernTicks += 30;
			
			if(move == "R" && Terrain.blocs[xJ + 1][yJ] == 0)
				xJ ++;
			
			if(move == "L" && Terrain.blocs[xJ - 1][yJ] == 0)
				xJ --;
			
			if(move == "D" && Terrain.blocs[xJ][yJ + 1] == 0)
				yJ ++;
			
			if(move == "U" && Terrain.blocs[xJ][yJ - 1] == 0)
				yJ --;
			
			if(move == "R" && Terrain.blocs[xJ + 1][yJ] == 1)
				move = "D";
			
			if(move == "L" && Terrain.blocs[xJ - 1][yJ] == 1)
				move = "U";
			
			if(move == "D" && Terrain.blocs[xJ][yJ + 1] == 1)
				move = "L";
			
			if(move == "U" && Terrain.blocs[xJ][yJ - 1] == 1)
				move = "R";
			
		}
	}
	
	public void render(Graphics g){
		img.draw(xJ * taille, yJ * taille, taille, taille);
	}
}
