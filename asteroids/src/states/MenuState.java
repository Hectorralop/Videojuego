package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameObjects.Constants;
import graphics.Animation;
import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import ui.Action;
import ui.Button;

public class MenuState extends State{
	
	private ArrayList<Button> buttons;
    private Sound backgroundMusic;
	private Animation backgroundAnimation;
	
	public MenuState() {
		buttons = new ArrayList<Button>();
		
		int buttonSpacing = 30; 

		buttons.add(new Button(
		        Assets.greyBtn,
		        Assets.blueBtn,
		        Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
		        Constants.HEIGHT / 2 - Assets.greyBtn.getHeight() * 2 - buttonSpacing * 2,
		        Constants.PLAY,
		        new Action() {
		            @Override
		            public void doAction() {
		            	backgroundMusic.stop();
		                State.changeState(new GameState());
		            }
		        }
		));

		buttons.add(new Button(
		        Assets.greyBtn,
		        Assets.blueBtn,
		        Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
		        Constants.HEIGHT / 2 - Assets.greyBtn.getHeight() - buttonSpacing,
		        Constants.CONTROL_STATE,
		        new Action() {
		            @Override
		            public void doAction() {
		                State.changeState(new ControlState());
		            }
		        }
		));

		buttons.add(new Button(
		        Assets.greyBtn,
		        Assets.blueBtn,
		        Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
		        Constants.HEIGHT / 2,
		        Constants.HIGH_SCORES,
		        new Action() {
		            @Override
		            public void doAction() {
		                State.changeState(new ScoreState());
		            }
		        }
		));

		buttons.add(new Button(
		        Assets.greyBtn,
		        Assets.blueBtn,
		        Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
		        Constants.HEIGHT / 2 + Assets.greyBtn.getHeight() + buttonSpacing,
		        Constants.EXIT,
		        new Action() {
		            @Override
		            public void doAction() {
		                System.exit(0);
		            }
		        }
		));
		
		backgroundMusic = new Sound(Assets.backgroundMusicMenu);
        backgroundMusic.loop();
		
		BufferedImage[] backgroundFrames = Assets.background;
        Vector2D backgroundPosition = new Vector2D(0, 0);
        backgroundAnimation = new Animation(backgroundFrames, 200, backgroundPosition);
    }
	
	@Override
	public void update(float dt) {
		for(Button b: buttons) {
			b.update();
		}
		backgroundAnimation.update(dt);
	}
	
	@Override
	public void draw(Graphics g) {
		BufferedImage backgroundFrame = backgroundAnimation.getCurrentFrame();
        g.drawImage(backgroundFrame, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
		for(Button b: buttons) {
			b.draw(g);
		}
		Graphics2D g2d = (Graphics2D) g;
        Font font = Assets.fontBig;
        g2d.setFont(font);

        String title = "Asteroids";
        int titleX = Constants.WIDTH / 2 - g2d.getFontMetrics().stringWidth(title) / 2;
        int titleY = 200;

        g2d.setColor(Color.WHITE);
        g2d.drawString(title, titleX, titleY);

	}
}