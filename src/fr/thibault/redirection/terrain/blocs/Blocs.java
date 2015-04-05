package fr.thibault.redirection.terrain.blocs;

import fr.thibault.redirection.niveau.Niveau;
import fr.thibault.redirection.utils.Formes;
import fr.thibault.redirection.utils.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Blocs {
	private boolean estSolide;
	private String type;
	
	private Texture texture;
	
	public Blocs(String path, String type, boolean estSolide){
		this.type = type;
		this.estSolide = estSolide;
		
		this.texture = new Texture(path, GL_NEAREST);
	}
	
	public void render(int x, int y){
		this.texture.bind();
		Formes.carre(x * Niveau.level.getTaille(), y * Niveau.level.getTaille(), Niveau.level.getTaille(), Niveau.level.getTaille());
		Texture.unbind();
	}
	
	public String getType(){
		return type;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public boolean getEstSolide(){
		return estSolide;
	}
	
	public void setTexture(Texture tex){
		this.texture = tex;
	}
	
	public void setEstSolide(boolean v){
		estSolide = v;
	}
}
