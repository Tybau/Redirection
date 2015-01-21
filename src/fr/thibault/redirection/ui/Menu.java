package fr.thibault.redirection.ui;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.Jeu;

public class Menu {
	
	Input input;
	
	Image title, fond;
	
	int choix = 0;
	int choixMax = 3;
	
	public Menu() throws SlickException{
		title = new Image("/assets/textures/title.png");
		fond = new Image("/assets/textures/bg.png");
	}
	
	@SuppressWarnings("static-access")
	public void update(GameContainer container){
		input = container.getInput();
		
		if(input.isKeyPressed(input.KEY_ENTER) && choix == 0){
			Jeu.scene = "JEU";
			Jeu.lance = true;
		}
		
		if(input.isKeyPressed(input.KEY_ENTER) && choix == 1)
			Jeu.scene = "OPTION";
		
		if(input.isKeyPressed(input.KEY_DOWN) && choix < choixMax)
			choix++;
		
		if(input.isKeyPressed(input.KEY_UP) && choix > 0)
			choix--;
	}
	
	public void render(Graphics g){
		title.draw(50, 50);
		fond.draw(350, 25, 600, 550);
		
		//Jouer
		
		if(choix == 0)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Jouer", 75, 150);
		
		//Niveaux
		
		if(choix == 1)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Niveaux", 75, 200);
		
		//Option
		
		if(choix == 2)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Option", 75, 250);
		
		//Fermer
		
		if(choix == 3)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Fermer", 75, 300);
		
		g.setColor(Color.white);
		g.drawString("© Thibault(s) 2015", 800, 550);
	}
}
