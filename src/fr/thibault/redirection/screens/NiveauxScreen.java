package fr.thibault.redirection.screens;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Color;

import fr.thibault.redirection.Jeu;
import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Inputs;
import fr.thibault.redirection.utils.Text;
import fr.thibault.redirection.utils.Texture;
import static org.lwjgl.opengl.GL11.*;

public class NiveauxScreen extends Screen{

	private int choix;
	private int choixMax;
	private int niveau;
	private int niveauMax;
	private int niveauAtteint;
	
	private Texture nivCharge;
	
	public NiveauxScreen() {
		this.title = "Niveaux";
		this.choix = 0;
		this.choixMax = 1;
		this.niveau = Jeu.i.numNiveau;
		this.niveauMax = Jeu.i.niveauMax;
		this.niveauAtteint = Jeu.i.niveauAtteint;
		
		this.nivCharge = new Texture("niveaux/NIV_" + niveau + ".png" , GL_NEAREST);
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
			if(choix == 0 && niveau > 0){
				niveau--;
				this.nivCharge = new Texture("niveaux/NIV_" + niveau + ".png" , GL_NEAREST);
			}
		}
		
		if(Inputs.isKeyboardKeyPressed(Keyboard.KEY_RIGHT)){
			if(choix == 0 && niveau < niveauAtteint && niveau < niveauMax){
				niveau++;
				this.nivCharge = new Texture("niveaux/NIV_" + niveau + ".png" , GL_NEAREST);
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
		
		if(choix == 0)
			Text.drawText(75, 150, "Niveau: " + niveau, 20, new Color(Color.RED));
		else
			Text.drawText(75, 150, "Niveau: " + niveau, 20, new Color(Color.BLACK));
		
		//Retour MENU
		
		if(choix == 1)
			Text.drawText(75, 500, "Menu", 20, new Color(Color.RED));
		else
			Text.drawText(75, 500, "Menu", 20, new Color(Color.BLACK));
		
		nivCharge.bind();
		Formes.carre(550, 150, 300, 300);
	}
}
