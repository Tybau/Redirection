package fr.thibault.redirection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import fr.thibault.redirection.screens.MainMenuScreen;
import fr.thibault.redirection.screens.Screen;

public class Jeu {
	
	/* Instance du Jeu */
	public static Jeu i;
	
	/* Direction */
	public static int RIGHT = 1;
	public static int LEFT = 2;
	public static int UP = 3;
	public static int DOWN = 4;
	
	public int[] nbBlocs = {2, 4, 6, 8, 5, 8, 10, 7, 9};		//Tableau des nombres de blocs donné en fonction des niveaux 
	
	private Screen screen;
	
	public int difficulte = 0;
	public int volume = 5;
	
	public int numNiveau;
	public int nbBlocsSup = 2;		//Nombres de blocs donné en plus selon la difficulté (par défaut facile avec 2 blocs suplémentaires)
	
	public int niveauMax = 8;
	
	public int niveauAtteint = 0;
	
	public boolean nivTermine = false;
	
	private FileReader fr;
	private BufferedReader in;
	
	public Jeu(){
		i = this;
		this.numNiveau = 0;
		try {
			this.fr = new FileReader("redirection.data");
			this.in = new BufferedReader(fr);
			String data = in.readLine();
			this.niveauAtteint = Integer.parseInt(data.split(":")[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
