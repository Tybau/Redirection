package fr.thibault.redirection;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class GameMain{

	private Jeu j;
	
	public GameMain() {
		this.j = new Jeu();		//Appelle de la Class du Jeu
	}
	
	public static void main(String[] args){		//Fonction Principale éxécuté par Java
		try {
			/* Création de la fenètre */
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
	
	private long dernTick = System.nanoTime();
	private long dernFrame = System.nanoTime();
	private long dernSecond = System.nanoTime();
	private int tps, fps;
	
	public void boucle(){		//Fonction lançant la boucle du jeu.
		init();		//Initialisation du jeu
		while (!Display.isCloseRequested()) {
			if(System.nanoTime() >= dernTick + 1000000000 / 60){
				update();		//Calcules
				dernTick += 1000000000 / 60;
				tps++;
			}
			
			if(System.nanoTime() >= dernFrame + 1000000000 / 120){
				glClearColor(0, 0, 0, 1);
				render();		//Rendus
				Display.update();
				dernFrame += 1000000000 / 120;
				fps++;
			}
			
			if(System.nanoTime() >= dernSecond + 1000000000){
				dernSecond += 1000000000;
				Display.setTitle("Redirection     ||     TPS: " + tps + "  |  FPS: " + fps);		//Affichage du titre du programme
				tps = 0;
				fps = 0;
			}
		}
		Display.destroy();		//Destruction de la fenètre
		System.exit(0);		//Fermeture du programme
	}
	
	public void init() {
		System.err.println("[ Redirection ] La fenètre à été créé!");
		j.init();
	}

	public void update(){
		j.update();
	}

	public void render(){
		j.render();
	}

}
