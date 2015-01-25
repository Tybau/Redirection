package fr.thibault.redirection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.ui.Menu;
import fr.thibault.redirection.ui.MenuNiveaux;
import fr.thibault.redirection.ui.MenuOption;

public class Jeu {
	
	Niveau niv;
	
	Menu menu;
	MenuOption option;
	MenuNiveaux niveaux;
	
	public static boolean lance = false;
	
	public static String scene = "MENU";
	
	public void init(GameContainer container) throws SlickException{		
		menu = new Menu();
		option = new MenuOption();
		niveaux = new MenuNiveaux();
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
		if(scene == "MENU")
			menu.update(container);
		
		if(scene == "OPTION")
			option.update(container);
		
		if(scene == "NIVEAUX")
			niveaux.update(container);
		
		if(scene == "JEU"){
			if(lance){
				niv = new Niveau(1, 25, 10);
				lance = false;
			}
			niv.update(container);
		}
	}
	
	public void render(GameContainer container, Graphics g){
		if(scene == "MENU")
			menu.render(g);
		
		if(scene == "OPTION")
			option.render(g);
		
		if(scene == "NIVEAUX")
			niveaux.render(g);
		
		if(scene == "JEU")
			niv.render(g);
	}
}
