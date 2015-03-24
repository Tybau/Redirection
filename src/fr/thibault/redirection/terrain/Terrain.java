package fr.thibault.redirection.terrain;

import static org.lwjgl.opengl.GL11.GL_NEAREST;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Color;

import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.terrain.blocs.Blocs;
import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Inputs;
import fr.thibault.redirection.utils.Text;
import fr.thibault.redirection.utils.Texture;

public class Terrain {
	
	public static Blocs[][] blocs = new Blocs[Niveau.nbCase][Niveau.nbCase];
	private int nbBlocs;
	
	private Blocs mur, murPose, sol, fin;
	private Texture niveau, bloc;
	
	public Terrain(int niv, int nbBlocs){
		this.nbBlocs = nbBlocs;
		
		this.mur = new Blocs("blocs/mur.png", "BASE", true);
		this.murPose = new Blocs("blocs/mur_pose.png", "BASE", true);
		this.sol = new Blocs("blocs/sol.png", "BASE", false);
		this.fin = new Blocs("blocs/fin.png", "WIN", false);
		
		this.niveau = new Texture("niveaux/NIV_" + niv + ".png", GL_NEAREST);
		this.bloc = new Texture("blocs.png", GL_NEAREST);
		
		for (int x = 0; x < Niveau.nbCase; x ++){
			for (int y = 0; y < Niveau.nbCase; y ++){
				if (niveau.getImage(niveau).getRGB(x, y) == 0xff000000)
					blocs[x][y] = mur;
				else if (niveau.getImage(niveau).getRGB(x, y) == 0xffff0000)
						blocs[x][y] = fin;
				else
					blocs[x][y] = sol;
			}
		}
	}
	
	public void update(int joueurX, int joueurY){		
		if(Inputs.isMouseButtonPressed(0) && Mouse.getX() <= Niveau.taille * Niveau.nbCase && Display.getHeight() - Mouse.getY() <= Niveau.taille * Niveau.nbCase && nbBlocs != 0){
			if(Mouse.getX() / Niveau.taille != joueurX || Display.getHeight() - Mouse.getY() / Niveau.taille != joueurY){
				if(!blocs[Mouse.getX() / Niveau.taille][(Display.getHeight() - Mouse.getY()) / Niveau.taille].estSolide &&
				   blocs[Mouse.getX() / Niveau.taille][(Display.getHeight() - Mouse.getY()) / Niveau.taille].type == "BASE"){
					blocs[Mouse.getX() / Niveau.taille][(Display.getHeight() - Mouse.getY()) / Niveau.taille] = murPose;
					nbBlocs--;
				}
			}
		}
	}
	
	public void render(){
		for (int x = 0; x < Niveau.nbCase; x ++){
			for (int y = 0; y < Niveau.nbCase; y ++){
				blocs[x][y].render(x, y);
			}
		}
		Text.text(65, 550, 4, nbBlocs + "x", 16, new Color(Color.RED), false);
		
		bloc.bind();
		Formes.carre(100, 550, 20, 20);
		Texture.unbind();
	}
}
