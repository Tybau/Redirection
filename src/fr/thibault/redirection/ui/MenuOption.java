package fr.thibault.redirection.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.Jeu;

public class MenuOption {
	
	Input input;
	
	Image title;
	
	int choix = 0;
	int choixMax = 2;
	
	int difficulté = 0;
	
	public MenuOption() throws SlickException{
		title = new Image("/assets/textures/title.png");
	}
	
	@SuppressWarnings("static-access")
	public void update(GameContainer container){
		input = container.getInput();
		
		if(input.isKeyPressed(input.KEY_ENTER)){
			if(choix == 2)
				Jeu.scene = "MENU";
		}
		
		//Choix du volume
		
		if(input.isKeyPressed(input.KEY_LEFT)){
			if (choix == 0 && Jeu.volume > 0)
				Jeu.volume--;
		
			if(choix == 1 && difficulté > 0)
				difficulté--;
		}
		
		if(input.isKeyPressed(input.KEY_RIGHT)){
			if (choix == 0 && Jeu.volume < 10)
				Jeu.volume++;
		
			if(choix == 1 && difficulté < 2)
				difficulté++;
		}
		
		Jeu.nbBlocsSup = 2 - difficulté;
		
		//Choix de l'option
		
		if(input.isKeyPressed(input.KEY_DOWN) && choix < choixMax)
			choix++;
		
		if(input.isKeyPressed(input.KEY_UP) && choix > 0)
			choix--;
	}
	
	public void render(Graphics g){
		title.draw(50, 50);
		
		//Son
		
		if(choix == 0)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Volume sonore: " + Jeu.volume, 75, 150);
		
		//Difficulté
		
		if(choix == 1)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Difficulté: " + difficulté, 75, 200);
		
		//Retour MENU
		
		if(choix == 2)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Menu", 75, 500);
	}
}
