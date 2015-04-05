package fr.thibault.redirection.terrain;

import static org.lwjgl.opengl.GL11.GL_NEAREST;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Color;

import fr.thibault.redirection.entite.Joueur;
import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.terrain.blocs.Blocs;
import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Inputs;
import fr.thibault.redirection.utils.Text;
import fr.thibault.redirection.utils.Texture;

public class Terrain {
	
	public static Blocs[][] blocs = new Blocs[Niveau.nbCase][Niveau.nbCase];
	private int nbBlocs;
	
	private Blocs mur, murPose, sol, fin, tuto, invisible, tpDepart, tpArrive, reverse, cassable, bombe;
	private Texture niveau, bloc;
	
	public Terrain(int niv, int nbBlocs){
		this.nbBlocs = nbBlocs;
		
		this.mur = new Blocs("blocs/mur.png", "BASE", true);
		this.murPose = new Blocs("blocs/mur_pose.png", "BASE", true);
		this.sol = new Blocs("blocs/sol.png", "BASE", false);
		this.fin = new Blocs("blocs/fin.png", "WIN", false);
		
		this.tuto = new Blocs("blocs/tuto.png", "BASE", false);
		this.invisible = new Blocs("blocs/sol.png", "INVI", true);
		this.tpDepart = new Blocs("blocs/tp_depart.png", "TPD", false);
		this.tpArrive = new Blocs("blocs/tp_arrive.png", "TPA", false);
		this.reverse = new Blocs("blocs/reverse.png", "REVE", false);
		this.cassable = new Blocs("blocs/cassable.png", "CASE", true);
		this.bombe = new Blocs("blocs/bombe.png", "BOMB", false);
		
		this.niveau = new Texture("niveaux/NIV_" + niv + ".png", GL_NEAREST);
		this.bloc = new Texture("blocs.png", GL_NEAREST);
		
		/* Attribution des blocs selon l'image de niveau */
		for (int x = 0; x < Niveau.nbCase; x ++){
			for (int y = 0; y < Niveau.nbCase; y ++){
				if (niveau.getImage(niveau).getRGB(x, y) == 0xff000000)
					blocs[x][y] = mur;
				else if (niveau.getImage(niveau).getRGB(x, y) == 0xffff0000)
						blocs[x][y] = fin;
				else if (niveau.getImage(niveau).getRGB(x, y) == 0xffff7e00)
					blocs[x][y] = tuto;
				else if (niveau.getImage(niveau).getRGB(x, y) == 0xff00ff00)
					blocs[x][y] = invisible;
				else if (niveau.getImage(niveau).getRGB(x, y) == 0xff0000ff)
					blocs[x][y] = tpDepart;
				else if (niveau.getImage(niveau).getRGB(x, y) == 0xffff00ff)
					blocs[x][y] = tpArrive;
				else if (niveau.getImage(niveau).getRGB(x, y) == 0xff560076)
					blocs[x][y] = reverse;
				else if (niveau.getImage(niveau).getRGB(x, y) == 0xff3d0f0f)
					blocs[x][y] = cassable;
				else if (niveau.getImage(niveau).getRGB(x, y) == 0xff00957b)
					blocs[x][y] = bombe;
				else
					blocs[x][y] = sol;
			}
		}
	}
	
	boolean aTourne = false;
	
	public void update(Joueur joueur){
		int joueurX = joueur.getX();
		int joueurY = joueur.getY();
		/* Verification de la pose d'un bloc */
		if(Inputs.isMouseButtonPressed(0) && Mouse.getX() <= Niveau.taille * Niveau.nbCase && Display.getHeight() - Mouse.getY() <= Niveau.taille * Niveau.nbCase && nbBlocs != 0){
			if(Mouse.getX() / Niveau.taille != joueurX || Display.getHeight() - Mouse.getY() / Niveau.taille != joueurY){
				if(!blocs[Mouse.getX() / Niveau.taille][(Display.getHeight() - Mouse.getY()) / Niveau.taille].getEstSolide() &&
				   blocs[Mouse.getX() / Niveau.taille][(Display.getHeight() - Mouse.getY()) / Niveau.taille].getType().equalsIgnoreCase("BASE")){
					blocs[Mouse.getX() / Niveau.taille][(Display.getHeight() - Mouse.getY()) / Niveau.taille] = murPose;
					nbBlocs--;
				}
			}
		}	
		
		/* Verification des blocs invisible autour du joueur */
		if(blocs[joueurX + 1][joueurY].getType().equalsIgnoreCase("INVI"))
			blocs[joueurX + 1][joueurY] = mur;
		
		if(blocs[joueurX - 1][joueurY].getType().equalsIgnoreCase("INVI"))
			blocs[joueurX - 1][joueurY] = mur;
		
		if(blocs[joueurX][joueurY + 1].getType().equalsIgnoreCase("INVI"))
			blocs[joueurX][joueurY + 1] = mur;
		
		if(blocs[joueurX][joueurY - 1].getType().equalsIgnoreCase("INVI"))
			blocs[joueurX][joueurY - 1] = mur;
		
		/* Verification des blocs cassable autour du joueur */
		if(blocs[joueurX + 1][joueurY].getType().equalsIgnoreCase("CASE") && joueur.getBombe()){
			blocs[joueurX + 1][joueurY] = sol;
			joueur.setBombe(false);
		}
		
		if(blocs[joueurX - 1][joueurY].getType().equalsIgnoreCase("CASE") && joueur.getBombe()){
			blocs[joueurX - 1][joueurY] = sol;
			joueur.setBombe(false);
		}
		
		if(blocs[joueurX][joueurY + 1].getType().equalsIgnoreCase("CASE") && joueur.getBombe()){
			blocs[joueurX][joueurY + 1] = sol;
			joueur.setBombe(false);
		}
		
		if(blocs[joueurX][joueurY - 1].getType().equalsIgnoreCase("CASE") && joueur.getBombe()){
			blocs[joueurX][joueurY - 1] = sol;
			joueur.setBombe(false);
		}
		
		/* Verification de la bombe */
		if(blocs[joueurX][joueurY].getType().equalsIgnoreCase("BOMB") && !joueur.getBombe()){
			joueur.setBombe(true);
			blocs[joueurX][joueurY] = sol;
		}
	}
	
	public void render(){
		for (int x = 0; x < Niveau.nbCase; x ++){
			for (int y = 0; y < Niveau.nbCase; y ++){
				blocs[x][y].render(x, y);
			}
		}
		Text.drawText(65, 550, nbBlocs + "x", 14, new Color(Color.BLACK));
		
		bloc.bind();
		Formes.carre(100, 550, 20, 20);
		Texture.unbind();
	}
}
