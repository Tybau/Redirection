package fr.thibault.redirection.terrain;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.terrain.blocs.Blocs;

public class Terrain {
	
	public static Blocs[][] blocs = new Blocs[Niveau.nbCase][Niveau.nbCase];
	
	Input input;
	int nbBlocs;
	
	Image niv, blocLogo;
	Blocs mur, sol, fin;
	
	public Terrain(String niveau, int nbBlocs) throws SlickException{
		this.nbBlocs = nbBlocs;
		
		niv = new Image(niveau);
		blocLogo = new Image("/assets/textures/blocs.png");
		mur = new Blocs("/assets/textures/blocs/mur.png", "BASE", true);
		sol = new Blocs("/assets/textures/blocs/sol.png", "BASE", false);
		fin = new Blocs("/assets/textures/blocs/fin.png", "WIN", false);
		
		for (int x = 0; x < Niveau.nbCase; x ++){
			for (int y = 0; y < Niveau.nbCase; y ++){
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
	
	public void update(GameContainer container, int joueurX, int joueurY){
		input = container.getInput();
		
		if(input.isMousePressed(0) && input.getMouseX() <= Niveau.taille * Niveau.nbCase && input.getMouseY() <= Niveau.taille * Niveau.nbCase && nbBlocs != 0){
			if(input.getMouseX() / Niveau.taille != joueurX || input.getMouseY() / Niveau.taille != joueurY){
				if(!blocs[input.getMouseX() / Niveau.taille][input.getMouseY() / Niveau.taille].estSolide &&
				   blocs[input.getMouseX() / Niveau.taille][input.getMouseY() / Niveau.taille].type == "BASE"){
					blocs[input.getMouseX() / Niveau.taille][input.getMouseY() / Niveau.taille] = mur;
					nbBlocs--;
				}
			}
		}
	}
	
	public void render(Graphics g){
		for (int x = 0; x < Niveau.nbCase; x ++){
			for (int y = 0; y < Niveau.nbCase; y ++){
				blocs[x][y].render(g, x, y);
			}
		}
		g.drawString(nbBlocs + " x ", 50, 550);
		blocLogo.draw(90, 550, 20, 20);
	}
}
