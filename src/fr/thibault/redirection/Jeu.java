package fr.thibault.redirection;

import fr.thibault.redirection.screens.MainMenuScreen;
import fr.thibault.redirection.screens.Screen;

public class Jeu {
	
	public static Jeu i;
	
	public int[] nbBlocs = {2, 4, 4, 8, 5, 8, 10};
	
	Screen screen;
	
	public int difficulte = 0;
	public int volume = 5;
	
	public int numNiveau;
	public int nbBlocsSup = 2;
	
	public int niveauMax = 6;
	
	public int niveauAtteint = 0;
	
	public boolean nivTermine = false;
	
	public Jeu(){
		i = this;
		this.numNiveau = 0;
	}
	
	public void init(){
		screen = new MainMenuScreen();
	}
	
	public void update(){
		screen.update();
	}
	
	public void render(){		
		screen.render();
	}
	
	public void setCurrentScreen(Screen screen){
		this.screen = screen;
	}
	
	public Screen getCurrentScreen(){
		return this.screen;
	}
}
