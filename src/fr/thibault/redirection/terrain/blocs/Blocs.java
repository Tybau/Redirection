package fr.thibault.redirection.terrain.blocs;

import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Blocs {
	public boolean estSolide;
	public String type;
	
	private Texture texture;
	
	public Blocs(String path, String type, boolean estSolide){
		this.type = type;
		this.estSolide = estSolide;
		
		this.texture = new Texture(path, GL_NEAREST);
	}
	
	public void render(int x, int y){
		this.texture.bind();
		Formes.carre(x * Niveau.taille, y * Niveau.taille, Niveau.taille, Niveau.taille);
		Texture.unbind();
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public void setEstSolide(boolean v){
		estSolide = v;
	}
}
