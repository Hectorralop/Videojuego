package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import gameObjects.Constants;
import graphics.Animation;
import graphics.Assets;
import graphics.Text;
import io.JSONParser;
import io.ScoreData;
import math.Vector2D;
import ui.Action;
import ui.Button;

public class ScoreState extends State{
	
	private Button returnButton;
	
	private PriorityQueue<ScoreData> highScores;
	
	private Comparator<ScoreData> scoreComparator;
	
	private ScoreData[] auxArray;
	
	private Animation backgroundAnimation;
	
	public ScoreState() {
		returnButton = new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Assets.greyBtn.getHeight(),
				Constants.HEIGHT - Assets.greyBtn.getHeight() * 2,
				Constants.RETURN,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new MenuState());
					}
				}
			);
		
		scoreComparator = new Comparator<ScoreData>() {
			@Override
			public int compare(ScoreData e1, ScoreData e2) {
				return e1.getScore() < e2.getScore() ? -1: e1.getScore() > e2.getScore() ? 1: 0;
			}
		};
		
		highScores = new PriorityQueue<ScoreData>(10, scoreComparator);
		
		try {
			ArrayList<ScoreData> dataList = JSONParser.readFile();
			
			for(ScoreData d: dataList) {
				highScores.add(d);
			}
			
			while(highScores.size() > 10) {
				highScores.poll();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedImage[] backgroundFrames = Assets.background;
        Vector2D backgroundPosition = new Vector2D(0, 0);
        backgroundAnimation = new Animation(backgroundFrames, 200, backgroundPosition);
	}
	
	@Override
	public void update(float dt) {
		returnButton.update();
		backgroundAnimation.update(dt);
	}

	@Override
	public void draw(Graphics g) {
		BufferedImage backgroundFrame = backgroundAnimation.getCurrentFrame();
        g.drawImage(backgroundFrame, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
		
		returnButton.draw(g);
		
		Graphics2D g2d = (Graphics2D) g;
        Font font = Assets.fontBig;
        g2d.setFont(font);

        String title = "PUNTAJES";
        int titleX = Constants.WIDTH / 2 - g2d.getFontMetrics().stringWidth(title) / 2;
        int titleY = 150;
        
        g2d.setColor(Color.WHITE);
        g2d.drawString(title, titleX, titleY);

		
		auxArray = highScores.toArray(new ScoreData[highScores.size()]);
		
		Arrays.sort(auxArray, scoreComparator);
		
		
		Vector2D scorePos = new Vector2D(
				Constants.WIDTH / 2 - 200,
				300
				);
		Vector2D datePos = new Vector2D(
				Constants.WIDTH / 2 + 200,
				300
				);
		
		Text.drawText(g, Constants.SCORE, scorePos, true, Color.LIGHT_GRAY, Assets.fontBig);
		Text.drawText(g, Constants.DATE, datePos, true, Color.LIGHT_GRAY, Assets.fontBig);
		
		scorePos.setY(scorePos.getY() + 60);
		datePos.setY(datePos.getY() + 60);
		
		for(int i = auxArray.length - 1; i > -1; i--) {
			
			ScoreData d = auxArray[i];
			
			Text.drawText(g, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, Assets.fontMed);
			Text.drawText(g, d.getDate(), datePos, true, Color.WHITE, Assets.fontMed);
			
			scorePos.setY(scorePos.getY() + 40);
			datePos.setY(datePos.getY() + 40);
			
		}
		
	}
	
}