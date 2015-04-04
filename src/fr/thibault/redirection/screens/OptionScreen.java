package fr.thibault.redirection.screens;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Color;

import fr.thibault.redirection.Jeu;
import fr.thibault.redirection.utils.Inputs;
import fr.thibault.redirection.utils.Text;

public class OptionScreen extends Screen{
	
	private int choix;
	private int choixMax;
	private int difficulte;
	private int volume;
	
	public OptionScreen() {
		this.title = "Options";
		
		this.choix = 0;
		this.choixMax = 2;
		this.difficulte = Jeu.i.difficulte;
		this.volume = Jeu.i.volume;
	}
	
	@Override
	public void update() {
		super.update();
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_RETURN)){
			if(choix == 2)
				Jeu.i.setCurrentScreen(new MainMenuScreen());
		}
		
		//Choix du volume
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_LEFT)){
			if (choix == 0 && Jeu.i.volume > 0)
				volume--;
		
			if(choix == 1 && difficulte > 0)
				difficulte--;
		}
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_RIGHT)){
			if (choix == 0 && Jeu.i.volume < 10)
				volume++;
		
			if(choix == 1 && difficulte < 2)
				difficulte++;
		}
		
		Jeu.i.nbBlocsSup = 2 - difficulte;
		Jeu.i.difficulte = this.difficulte;
		Jeu.i.volume = this.volume;
		
		//Choix de l'option
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_DOWN) && choix < choixMax)
			choix++;
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_UP) && choix > 0)
			choix--;
	}
	
	@Override
	public void render() {
		super.render();
		//Son
		
		if(choix == 0)
			Text.drawText(75, 150, "Volume: " + Jeu.i.volume, 20, new Color(Color.RED));
		else
			Text.drawText(75, 150, "Volume: " + Jeu.i.volume, 20, new Color(Color.WHITE));
		
		//difficulte
		
		if(choix == 1)
			Text.drawText(75, 200, "Difficulte: " + difficulte, 20, new Color(Color.RED));
		else
			Text.drawText(75, 200, "Difficulte: " + difficulte, 20, new Color(Color.WHITE));
		
		//Retour MENU
		
		if(choix == 2)
			Text.drawText(75, 500, "Menu", 20, new Color(Color.RED));
		else
			Text.drawText(75, 500, "Menu", 20, new Color(Color.WHITE));
	}
}
