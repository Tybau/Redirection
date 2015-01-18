package fr.thibault.redirection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.niveau.Niveau;

public class Jeu {
	
	Input input;
	Graphics g;
	
	Niveau niv;
	
	public void init(GameContainer container) throws SlickException{
		input = container.getInput();
		niv = new Niveau(1, 6);
	}
	
	public void update(GameContainer container, int delta){
		niv.update(container);
	}
	
	public void render(GameContainer container, Graphics g){
		niv.render(g);
	}
}
