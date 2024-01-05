package graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

public class Assets {
	
	public static boolean loaded = false;
	public static float count = 0;
	public static float MAX_COUNT = 82;
	
	
	public static BufferedImage player;
	public static BufferedImage doubleGunPlayer;
	
	// effects
	
	public static BufferedImage speed;
	
	public static BufferedImage[] shieldEffect = new BufferedImage[3];
	
	// explosion
	
	public static BufferedImage[] exp = new BufferedImage[9];
	
	// lasers
	
	public static BufferedImage blueLaser, greenLaser, redLaser;
	
	// Meteors
	
	public static BufferedImage[] bigs = new BufferedImage[4];
	public static BufferedImage[] meds = new BufferedImage[2];
	public static BufferedImage[] smalls = new BufferedImage[2];
	public static BufferedImage[] tinies = new BufferedImage[2];
	
	// ufo
	
	public static BufferedImage ufo;
	
	// numbers
	
	public static BufferedImage[] numbers = new BufferedImage[11];
	
	public static BufferedImage life;
	
	// fonts
	
	public static Font fontBig;
	public static Font fontMed;
	public static Font fontSmall;
	public static Font fontTiny;
	
	//sounds
	
	public static Clip backgroundMusicGame, backgroundMusicMenu, explosion, playerLoose, playerShoot, ufoShoot, powerUp;
	
	// ui
	
	public static BufferedImage blueBtn;
	public static BufferedImage greyBtn;
	public static BufferedImage exit;
	
	// power ups
	
	public static BufferedImage orb, doubleScore, doubleGun, fastFire, shield, star;
		
	// background
	
	   public static BufferedImage[] background = new BufferedImage[6];
	   public static BufferedImage[] gameBackground = new BufferedImage[3];
	   
	   //keyboard
	   
	   public static BufferedImage[] KeyA = new BufferedImage[2];
	   public static BufferedImage[] KeyW = new BufferedImage[2];
	   public static BufferedImage[] KeyD = new BufferedImage[2];
	   public static BufferedImage[] KeyP = new BufferedImage[2];
	
	public static void init()
	{
		player = loadImage("/ships/playerShip2_orange.png");
		doubleGunPlayer = loadImage("/ships/doubleGunPlayer.png");

		speed = loadImage("/effects/fire10.png");
		
		blueLaser = loadImage("/lasers/laserBlue01.png");
		
		greenLaser = loadImage("/lasers/laserGreen11.png");
		
		redLaser = loadImage("/lasers/laserRed01.png");
		
		ufo = loadImage("/ships/ufo.png");
		
		life = loadImage("/others/playerLife2_orange.png");
		
		fontBig = loadFont("/fonts/kenvector_future.ttf", 42);
		fontMed = loadFont("/fonts/kenvector_future.ttf", 20);
		fontSmall = loadFont("/fonts/kenvector_future.ttf", 15);
		fontTiny = loadFont("/fonts/kenvector_future.ttf", 11);
		
		for(int i = 0; i < 3; i++)
			shieldEffect[i] = loadImage("/effects/shield" + (i + 1) +".png"); 
		
		for(int i = 0; i < bigs.length; i++)
			bigs[i] = loadImage("/meteors/big"+ ( i + 1 ) +".png");
		
		for(int i = 0; i < meds.length; i++)
			meds[i] = loadImage("/meteors/med"+ ( i + 1 ) +".png");
		
		for(int i = 0; i < smalls.length; i++)
			smalls[i] = loadImage("/meteors/small"+ ( i + 1 ) +".png");
		
		for(int i = 0; i < tinies.length; i++)
			tinies[i] = loadImage("/meteors/tiny"+ ( i + 1 ) +".png");
		
		for(int i = 0; i < exp.length; i++)
			exp[i] = loadImage("/explosion/" + i + ".png");
		
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = loadImage("/numbers/" + i + ".png");
		
		for (int i = 0; i < background.length; i++)
		    background[i] = loadImage("/background/space" + i + ".png");
		
		for (int i = 0; i < gameBackground.length; i++)
	        gameBackground[i] = loadImage("/background/game" + i + ".png");
		
		for (int i = 0; i < KeyA.length; i++)
		    KeyA[i] = loadImage("/Keyboard/a" + i + ".png");

		for (int i = 0; i < KeyW.length; i++)
		    KeyW[i] = loadImage("/Keyboard/w" + i + ".png");

		for (int i = 0; i < KeyD.length; i++)
		    KeyD[i] = loadImage("/Keyboard/d" + i + ".png");

		for (int i = 0; i < KeyP.length; i++)
		    KeyP[i] = loadImage("/Keyboard/p" + i + ".png");
			
		backgroundMusicGame = loadSound("/sounds/backgroundMusic.wav");
		backgroundMusicMenu = loadSound("/sounds/backgroundMusicMenu.wav");
		explosion = loadSound("/sounds/explosion.wav");
		playerLoose = loadSound("/sounds/playerLoose.wav");
		playerShoot = loadSound("/sounds/playerShoot.wav");
		ufoShoot = loadSound("/sounds/ufoShoot.wav");
		powerUp = loadSound("/sounds/powerUp.wav");
		
		greyBtn = loadImage("/ui/grey_button15.png");
		blueBtn = loadImage("/ui/blue_button01.png");
		exit= loadImage("/ui/return.png");
		
		orb = loadImage("/powers/orb.png");
		doubleScore = loadImage("/powers/doubleScore.png");
		doubleGun = loadImage("/powers/doubleGun.png");
		fastFire = loadImage("/powers/fastFire.png");
		star = loadImage("/powers/star.png");
		shield = loadImage("/powers/shield.png");
		
		// ===========================================================
		
		loaded = true;
		
	}

	public static BufferedImage loadImage(String path) {
		count ++;
		return Loader.ImageLoader(path);
	}
	public static Font loadFont(String path, int size) {
		count ++;
		return Loader.loadFont(path, size);
	}
	public static Clip loadSound(String path) {
		count ++;
		return Loader.loadSound(path);
	}
	
}