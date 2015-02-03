package fr.thibault.redirection.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.Jeu;

public class MenuNiveaux {
	
	Input input;
	
	Image title;
	
	int choix = 0;
	int choixMax = 5;
	
	public MenuNiveaux() throws SlickException{
		title = new Image("/assets/textures/title.png");
	}
	
	@SuppressWarnings("static-access")
	public void update(GameContainer container){
		input = container.getInput();
		
		if(input.isKeyPressed(input.KEY_ENTER)){
			if(choix == 5)
				Jeu.scene = "MENU";
			else{
				Jeu.numNiveau = choix;
				Jeu.scene = "MENU";
			}
		}
		
		if(input.isKeyPressed(input.KEY_DOWN) && choix < choixMax)
			choix++;
		
		if(input.isKeyPressed(input.KEY_UP) && choix > 0)
			choix--;
	}
	
	public void render(Graphics g){
		title.draw(50, 50);
		
		//0
		
		if(choix == 0)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Niveau 0", 75, 150);
		
		//1
		
		if(choix == 1)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Niveau 1", 75, 200);
		
		//2
		
		if(choix == 2)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Niveau 2", 75, 250);
		
		//3
		
		if(choix == 3)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Niveau 3", 75, 300);
		
		//4
		
		if(choix == 4)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Niveau 4", 75, 350);
		
		//Retour MENU
		
		if(choix == 5)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.white);
		g.drawString("Menu", 75, 500);
		
		g.setColor(Color.white);
		g.drawString("© Thibault(s) 2015", 800, 550);
	}
}
