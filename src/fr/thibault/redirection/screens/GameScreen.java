package fr.thibault.redirection.screens;

import static org.lwjgl.opengl.GL11.GL_NEAREST;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import fr.thibault.redirection.Jeu;
import fr.thibault.redirection.gui.Gui;
import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Inputs;
import fr.thibault.redirection.utils.Texture;

public class GameScreen extends Screen{
	
	private Niveau niv;
	private int numNiveau;
	private int nbBlocsSup;
	private boolean enPause;
	private boolean estFini;
	
	private Texture titre;
	
	public GameScreen(int numNiveau, int[] nbBlocs, int nbBlocsSup) {
		this.title = "Game";
		this.numNiveau = Jeu.i.numNiveau;
		this.nbBlocsSup = Jeu.i.nbBlocsSup;
		this.enPause = false;
		this.niv = new Niveau(numNiveau, nbBlocs[numNiveau] + nbBlocsSup);
		this.estFini = false;
		
		this.titre = new Texture("title.png", GL_NEAREST);
	}
	
	@Override
	public void update() {
		super.update();
		this.estFini = Jeu.i.nivTermine;
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_ESCAPE)){
			if(estFini){
				Jeu.i.setCurrentScreen(new MainMenuScreen());
				if(numNiveau < Jeu.i.niveauMax)
					numNiveau++;
				Jeu.i.numNiveau = numNiveau;
			}else{
				this.enPause = true;
			}
		}
		if(this.enPause) return;
		niv.update();
	}
	
	@Override
	public void render() {
		super.render();
		if(this.enPause){
			this.titre.bind();
			Formes.carre(Display.getWidth() / 2 - titre.getWidth() / 2, 50, titre.getWidth(), titre.getHeight());
			Texture.unbind();
			if(Gui.button("Reprendre", Display.getWidth() / 2 - 158, 200)) enPause = false;
			if(Gui.button("Recommencer", Display.getWidth() / 2 - 182, 250)) Jeu.i.setCurrentScreen(new GameScreen(numNiveau, Jeu.i.nbBlocs, nbBlocsSup));
			if(Gui.button("Menu", Display.getWidth() / 2 - 98, 300)) Jeu.i.setCurrentScreen(new MainMenuScreen());
			
			return;
		}
		niv.render();
	}
}
