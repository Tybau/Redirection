package fr.thibault.redirection.ui;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import fr.thibault.redirection.Jeu;

public class Menu {
	
	Input input;
	
	int choix = 0;
	
	public Menu(){
		
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
		
		if(input.isKeyPressed(input.KEY_DOWN) && choix < 1)
			choix++;
		
		if(input.isKeyPressed(input.KEY_UP) && choix > 0)
			choix--;
	}
	
	public void render(Graphics g){
		g.drawString("Redirection", 50, 50);
		
		if(choix == 0)
			g.setColor(Color.yellow);
		if(choix == 1)
			g.setColor(Color.white);
		g.drawString("Jouer", 75, 150);
		
		if(choix == 0)
			g.setColor(Color.white);
		if(choix == 1)
			g.setColor(Color.yellow);
		g.drawString("Option", 75, 200);
		
		g.setColor(Color.white);
		g.drawString("© Thibault(s) 2015", 800, 550);
	}
}
