package fr.thibault.redirection.screens;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Color;

import fr.thibault.redirection.Jeu;
import fr.thibault.redirection.utils.Inputs;
import fr.thibault.redirection.utils.Text;

public class MainMenuScreen extends Screen{
	
	private int choix;
	private int choixMax;
	
	public MainMenuScreen(){
		this.title = "Redirection";
		this.choix = 0;
		this.choixMax = 3;
	}
	
	@Override
	public void update() {
		super.update();
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_RETURN)){
			if(choix == 0){
				Jeu.i.setCurrentScreen(new GameScreen(Jeu.i.numNiveau, Jeu.i.nbBlocs, Jeu.i.nbBlocsSup));
			}
			if(choix == 1)
				Jeu.i.setCurrentScreen(new NiveauxScreen());
			
			if(choix == 2)
				Jeu.i.setCurrentScreen(new OptionScreen());
			
			if(choix == 3){
				System.err.println("[ Redirection ] Fin du jeu!");
				System.exit(0);
			}
		}
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_DOWN) && choix < choixMax)
			choix++;
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_UP) && choix > 0)
			choix--;
	}
	
	@Override
	public void render() {
		super.render();		
		//Jouer
		
		if(choix == 0)
			Text.text(75, 150, 255, "Jouer", 16, new Color(Color.RED), false);
		else
			Text.text(75, 150, 255, "Jouer", 16, new Color(Color.BLACK), false);
		
		//Niveaux
		
		if(choix == 1)
			Text.text(75, 200, 255, "Niveaux", 16, new Color(Color.RED), false);
		else
			Text.text(75, 200, 255, "Niveaux", 16, new Color(Color.BLACK), false);
		
		//Option
		
		if(choix == 2)
			Text.text(75, 250, 255, "Options", 16, new Color(Color.RED), false);
		else
			Text.text(75, 250, 255, "Options", 16, new Color(Color.BLACK), false);
		
		//Fermer
		
		if(choix == 3)
			Text.text(75, 500, 255, "Fermer", 16, new Color(Color.RED), false);
		else
			Text.text(75, 500, 255, "Fermer", 16, new Color(Color.BLACK), false);
	}
}
