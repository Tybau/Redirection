package fr.thibault.redirection;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class GameMain{

	Jeu j;
	
	String title;
	
	public GameMain() {
		j = new Jeu();										//Appelle de la Class du Jeu
	}
	
	public static void main(String[] args){					//Fonction Principale
		try {
			Display.setDisplayMode(new DisplayMode(1000, 600));
			Display.create();
			
			glEnable(GL_TEXTURE_2D);

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            
            glOrtho(0, 1000, 600, 0, -1, 1);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		new GameMain().boucle();
	}
	
	public void boucle(){
		init();
		while (!Display.isCloseRequested()) {
			if(System.nanoTime() >= dernTick + 1000000000 / 60){
				update();
				dernTick += 1000000000 / 60;
				ticks++;
				tps++;
			}
			
			if(System.nanoTime() >= dernFrame + 1000000000 / 120){
				glClearColor(0, 0, 0, 1);
				render();
				Display.update();
				dernFrame += 1000000000 / 120;
				frames++;
				fps++;
			}
			
			if(System.nanoTime() >= dernSecond + 1000000000){
				dernSecond += 1000000000;
				Display.setTitle("Redirection     ||     TPS: " + tps + "  |  FPS: " + fps);
				tps = 0;
				fps = 0;
			}
		}
		Display.destroy();
		System.exit(0);
	}
	
	public void init() {		//Initialisation
		System.err.println("[ Redirection ] La fenètre à été créé!");
		j.init();
	}
	
	long dernTick = System.nanoTime();
	long dernFrame = System.nanoTime();
	long dernSecond = System.nanoTime();
	long ticks, frames;
	int tps, fps;

	public void update(){					//Mise à jour
		j.update();
	}

	public void render(){					//Rendu
		j.render();
	}

}
