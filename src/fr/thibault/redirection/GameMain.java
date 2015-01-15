package fr.thibault.redirection;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GameMain extends BasicGame{

	Jeu j;
	
	long dernNano = System.nanoTime();
	long dernSecond = dernNano;
	public static long ticks;
	int tps;
	
	public GameMain(String title) {
		super(title);
		
		j = new Jeu();										//Appelle de la Class du Jeu
	}
	
	public static void main(String[] args){					//Fonction Principale
		try {
			AppGameContainer a = new AppGameContainer(new GameMain("Redirection"));
			a.setDisplayMode(1000, 600, false);				//Création d'une fenetre
			a.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {		//Initialisation
		System.out.println("[ Redirection ] La fenètre à été créé.");
		j.init(container);
	}

	@Override
	public void update(GameContainer container, int delta)					//Mise à jour
			throws SlickException {
		if(System.nanoTime() >= dernNano + 1000000000 / 60){
			j.update(container, delta);
			dernNano += 1000000000 / 60;
			ticks++;
			tps++;
		}
		
		if(System.nanoTime() >= dernSecond + 1000000000){
			dernSecond += 1000000000;
			System.out.println("[ Redirection ] TPS = " + tps);
			tps = 0;
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)					//Rendu
			throws SlickException {
		j.render(container, g);
	}

}
