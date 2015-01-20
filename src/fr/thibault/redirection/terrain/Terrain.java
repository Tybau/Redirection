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
	public static Blocs[][] blocs = new Blocs[nbCase][nbCase];
	
	Input input;
	int nbBlocs;
	
	Image niv, blocLogo;
	Blocs mur, sol, fin;
	Joueur joueur;
	
	public Terrain(String niveau, int nbBlocs) throws SlickException{
		this.nbBlocs = nbBlocs;
		
		niv = new Image(niveau);
		blocLogo = new Image("/assets/textures/blocs.png");
		mur = new Blocs("/assets/textures/blocs/mur.png", "BASE", true);
		sol = new Blocs("/assets/textures/blocs/sol.png", "BASE", false);
		fin = new Blocs("/assets/textures/blocs/fin.png", "WIN", false);
		joueur = new Joueur("/assets/textures/joueur.png");
		
		for (int x = 0; x < nbCase; x ++){
			for (int y = 0; y < nbCase; y ++){
				if (niv.getColor(x, y).getRed() == 0 &&
					niv.getColor(x, y).getBlue() == 0 &&
					niv.getColor(x, y).getGreen()== 0)
					blocs[x][y] = mur;
				else if (niv.getColor(x, y).getRed() == 255 &&
						niv.getColor(x, y).getBlue() == 0 &&
						niv.getColor(x, y).getGreen()== 0)
						blocs[x][y] = fin;
				else
					blocs[x][y] = sol;
			}
		}
	}
	
	public void update(GameContainer container){
		input = container.getInput();
		
		if(input.isMousePressed(0) && input.getMouseX() <= taille * nbCase && input.getMouseY() <= taille * nbCase && nbBlocs != 0){
			if(input.getMouseX() / taille != joueur.x || input.getMouseY() / taille != joueur.y){
				if(!blocs[input.getMouseX() / taille][input.getMouseY() / taille].estSolide &&
				   blocs[input.getMouseX() / taille][input.getMouseY() / taille].type == "BASE"){
					blocs[input.getMouseX() / taille][input.getMouseY() / taille] = mur;
					nbBlocs--;
				}
			}
		}
		joueur.update(container);
	}
	
	public void render(Graphics g){
		for (int x = 0; x < nbCase; x ++){
			for (int y = 0; y < nbCase; y ++){
				blocs[x][y].render(g, x, y);
			}
		}
		joueur.render(g);
		g.drawString(nbBlocs + " x ", 50, 550);
		blocLogo.draw(90, 550, 20, 20);
	}
}
