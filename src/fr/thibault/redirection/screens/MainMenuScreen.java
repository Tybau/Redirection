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
			Text.drawText(75, 150, "Jouer", 20, new Color(Color.RED));
		else
			Text.drawText(75, 150, "Jouer", 20, new Color(Color.WHITE));
		
		//Niveaux
		
		if(choix == 1)
			Text.drawText(75, 200, "Niveaux", 20, new Color(Color.RED));
		else
			Text.drawText(75, 200, "Niveaux", 20, new Color(Color.WHITE));
		
		//Option
		
		if(choix == 2)
			Text.drawText(75, 250, "Options", 20, new Color(Color.RED));
		else
			Text.drawText(75, 250, "Options", 20, new Color(Color.WHITE));
		
		//Fermer
		
		if(choix == 3)
			Text.drawText(75, 500, "Fermer", 20, new Color(Color.RED));
		else
			Text.drawText(75, 500, "Fermer", 20, new Color(Color.WHITE));
	}
}
