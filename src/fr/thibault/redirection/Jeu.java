package fr.thibault.redirection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.thibault.redirection.terrain.Terrain;

public class Jeu {
	
	Input input;
	Graphics g;
	
	Terrain terrain;
	
	public void init(GameContainer container) throws SlickException{
		input = container.getInput();
		terrain = new Terrain(container, "/assets/textures/level.png");
	}
	
	public void update(GameContainer container, int delta){
		terrain.update(container);
	}
	
	public void render(GameContainer container, Graphics g){
		terrain.render(g);
	}
}
