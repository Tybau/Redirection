package fr.thibault.redirection.screens;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Color;

import fr.thibault.redirection.Jeu;
import fr.thibault.redirection.utils.Inputs;
import fr.thibault.redirection.utils.Text;

public class NiveauxScreen extends Screen{

	private int choix;
	private int choixMax;
	private int niveau;
	private int niveauMax;
	private int niveauAtteint;
	
	public NiveauxScreen() {
		this.title = "Niveaux";
		this.choix = 0;
		this.choixMax = 1;
		this.niveau = Jeu.i.numNiveau;
		this.niveauMax = Jeu.i.niveauMax;
		this.niveauAtteint = Jeu.i.niveauAtteint;
	}
	
	@Override
	public void update() {
		super.update();
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_RETURN)){
			if(choix == 5)
				Jeu.i.setCurrentScreen(new MainMenuScreen());
			else{
				Jeu.i.numNiveau = niveau;
				Jeu.i.setCurrentScreen(new MainMenuScreen());
			}
		}
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_LEFT)){
			if(choix == 0 && niveau > 0)
				niveau--;
		}
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_RIGHT)){
			if(choix == 0 && niveau < niveauAtteint && niveau < niveauMax)
				niveau++;
		}
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_DOWN) && choix < choixMax)
			choix++;
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_UP) && choix > 0)
			choix--;
	}
	
	@Override
	public void render() {
		super.render();
		
		if(choix == 0)
			Text.text(75, 150, 255, "Niveau: " + niveau, 16, new Color(Color.RED), false);
		else
			Text.text(75, 150, 255, "Niveau: " + niveau, 16, new Color(Color.BLACK), false);
		
		//Retour MENU
		
		if(choix == 1)
			Text.text(75, 500, 255, "Menu", 16, new Color(Color.RED), false);
		else
			Text.text(75, 500, 255, "Menu", 16, new Color(Color.BLACK), false);
	}
}
