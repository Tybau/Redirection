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
	
	public int x = 1, y = 1;
	String move = "R";
	
	long dernTicks = GameMain.ticks;
	
	public Joueur(String texture) throws SlickException{
		this.texture = texture;
		
		img = new Image(texture);
	}
	
	public void update(GameContainer container){
		if (GameMain.ticks >= dernTicks + 30){
			dernTicks += 30;
			
			if(move == "R" && Terrain.blocs[x + 1][y] == 0)
				x ++;
			
			if(move == "L" && Terrain.blocs[x - 1][y] == 0)
				x --;
			
			if(move == "D" && Terrain.blocs[x][y + 1] == 0)
				y ++;
			
			if(move == "U" && Terrain.blocs[x][y - 1] == 0)
				y --;
			
			if(move == "R" && Terrain.blocs[x + 1][y] == 1)
				move = "D";
			
			if(move == "L" && Terrain.blocs[x - 1][y] == 1)
				move = "U";
			
			if(move == "D" && Terrain.blocs[x][y + 1] == 1)
				move = "L";
			
			if(move == "U" && Terrain.blocs[x][y - 1] == 1)
				move = "R";
			
		}
	}
	
	public void render(Graphics g){
		img.draw(x * Terrain.taille, y * Terrain.taille, Terrain.taille, Terrain.taille);
	}
}
