package fr.thibault.redirection.screens;

import static org.lwjgl.opengl.GL11.GL_NEAREST;

import org.lwjgl.opengl.Display;

import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Texture;

public class Screen {
	
	protected String title;
	
	private Texture fond, titre;
	
	public Screen(){
		this.fond = new Texture("fond.png", GL_NEAREST);
		this.titre = new Texture("title.png", GL_NEAREST);
	}
	
	public void update() {
		
	}
	
	public void render() {
		this.fond.bind();
		Formes.carre(0, 0, Display.getWidth(), Display.getHeight());
		if(!(this instanceof GameScreen)){
			titre.bind();
			Formes.carre(50, 50, this.titre.getWidth(), this.titre.getHeight());
		}
		Texture.unbind();
	}
}
