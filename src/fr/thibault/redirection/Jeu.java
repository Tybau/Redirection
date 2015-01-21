package fr.thibault.redirection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.ui.Menu;

public class Jeu {
	
	Input input;
	
	Niveau niv;
	Menu menu;
	
	public static boolean lance = false;
	
	public static String scene = "MENU";
	
	public void init(GameContainer container) throws SlickException{
		input = container.getInput();
		
		menu = new Menu();
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
		if(scene == "MENU")
			menu.update(container);
		if(scene == "JEU"){
			if(lance){
				niv = new Niveau(1, 2, 10);
				lance = false;
			}
			niv.update(container);
		}
		
	}
	
	public void render(GameContainer container, Graphics g){
		if(scene == "MENU")
			menu.render(g);
		if(scene == "JEU")
			niv.render(g);
	}
}
