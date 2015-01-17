package fr.thibault.redirection.terrain;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.entite.Joueur;
import fr.thibault.redirection.terrain.blocs.Blocs;

public class Terrain {
	
	public static int nbCase = 10;
	public static int taille = 50;
	public static int[][] blocs = new int[nbCase][nbCase];
	
	Input input;
	
	Image niv;
	Blocs mur;
	Joueur joueur;
	
	GameContainer container;
	
	public Terrain(GameContainer container, String niveau) throws SlickException{
		this.container = container;
		niv = new Image(niveau);
		mur = new Blocs("/assets/textures/mur.png", true);
		joueur = new Joueur("/assets/textures/joueur.png");
		
		for (int x = 0; x < nbCase; x ++){
			for (int y = 0; y < nbCase; y ++){
				if (niv.getColor(x, y).getRed() == 0 &&
					niv.getColor(x, y).getBlue() == 0 &&
					niv.getColor(x, y).getGreen()== 0)
					blocs[x][y] = 1;
				else
					blocs[x][y] = 0;
			}
		}
	}
	
	public void update(GameContainer container){
		input = container.getInput();
		
		if(input.isMousePressed(0) && input.getMouseX() <= taille * nbCase && input.getMouseY() <= taille * nbCase){
			if(input.getMouseX() / taille != joueur.x || input.getMouseY() / taille != joueur.y)
				blocs[input.getMouseX() / taille][input.getMouseY() / taille] = 1;
		}
		joueur.update(container);
	}
	
	public void render(Graphics g){
		for (int x = 0; x < nbCase; x ++){
			for (int y = 0; y < nbCase; y ++){
				g.drawRect(x * taille, y * taille, taille, taille);
				if (blocs[x][y] == 1)
					mur.render(g, x, y);
			}
		}
		joueur.render(g);
	}
}
