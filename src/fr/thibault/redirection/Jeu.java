package fr.thibault.redirection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	
	public int[] nbBlocs = {2, 4, 6, 8, 5, 8, 10, 7, 9};		//Tableau des nombres de blocs donn� en fonction des niveaux 
	
	private Screen screen;
	
	public int difficulte = 0;
	public int volume = 5;
	
	public int numNiveau;
	public int nbBlocsSup = 2;		//Nombres de blocs donn� en plus selon la difficult� (par d�faut facile avec 2 blocs supl�mentaires)
	
	public int niveauMax = 8;
	
	public int niveauAtteint = 0;
	
	public boolean nivTermine = false;
	
	
	/* Fichier de sauvegarde */
	private File file;
	
	private FileReader fr;
	private BufferedReader in;
	
	private FileWriter fw;
	private BufferedWriter out;
	
	public Jeu(){
		i = this;
		this.numNiveau = 0;
		try {
			this.file = new File("redirection.data");
			file.createNewFile();
			this.fr = new FileReader("redirection.data");
			this.in = new BufferedReader(fr);
			
			String data = in.readLine();		//R�cup�ration de la sauvegarde
			if(data != null)
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
	
	public void stop(){
		try {
			this.fw = new FileWriter("redirection.data");
			this.out = new BufferedWriter(fw);
			
			this.out.write("save:" + Jeu.i.niveauAtteint);		//Sauvegarde
			this.out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setCurrentScreen(Screen screen){
		this.screen = screen;
	}
	
	public Screen getCurrentScreen(){
		return this.screen;
	}
}
