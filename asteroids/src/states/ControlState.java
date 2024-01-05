package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gameObjects.Constants;
import graphics.Animation;
import graphics.Assets;
import math.Vector2D;
import ui.Action;
import ui.Button;

public class ControlState extends State {

    private Button returnButton;

    private Animation keyAAnimation;
    private Animation keyWAnimation;
    private Animation keyDAnimation;
    private Animation keyPAnimation;

    private Animation backgroundAnimation;

    private BufferedImage doubleScore, doubleGun, fastFire, star, shield;

    public ControlState() {
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

        int controlX = 200;
        int controlY = 300;
        
        int keyX = controlX + 10;
        int keyY = controlY + 20;
        
        keyAAnimation = new Animation(Assets.KeyA, 200, new Vector2D(keyX, keyY));
        keyWAnimation = new Animation(Assets.KeyW, 200, new Vector2D(keyX, keyY));
        keyDAnimation = new Animation(Assets.KeyD, 200, new Vector2D(keyX, keyY));
        keyPAnimation = new Animation(Assets.KeyP, 200, new Vector2D(keyX, keyY));

        doubleScore = Assets.doubleScore;
        doubleGun = Assets.doubleGun;
        fastFire = Assets.fastFire;
        star = Assets.star;
        shield =  Assets.shield;

        BufferedImage[] backgroundFrames = Assets.background;
        Vector2D backgroundPosition = new Vector2D(0, 0);
        backgroundAnimation = new Animation(backgroundFrames, 200, backgroundPosition);
    }

    @Override
    public void update(float dt) {
        returnButton.update();
        keyAAnimation.update(dt);
        keyWAnimation.update(dt);
        keyDAnimation.update(dt);
        keyPAnimation.update(dt);
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

        String title = "JUGABILIDAD";
        int titleX = Constants.WIDTH / 2 - g2d.getFontMetrics().stringWidth(title) / 2;
        int titleY = 150;

        g2d.setColor(Color.WHITE);
        g2d.drawString(title, titleX, titleY);

        // Dibujar controles en el cuadro izquierdo
        int controlX = 200;
        int controlY = 300;
        int controlSpacing = 80;
        int controlWidth = 270;
        int controlHeight = 130 + 4 * controlSpacing;

        g.setColor(new Color(0, 0, 0, 150));
        g.fillRoundRect(controlX, controlY, controlWidth, controlHeight, 20, 20);

        g.setColor(Color.WHITE);
        g.setFont(Assets.fontBig);
        g.drawString("Controls", controlX - 5, controlY - 10);

        g.drawImage(keyWAnimation.getCurrentFrame(), controlX + 10, controlY + 80, null);
        drawName(g, "Tecla W", controlX + 10, controlY + 70 - 10, 15);
        textDescription(g, "Con esta tecla te mueves \n hacia en frente", controlX + 60, controlY + 95, 0, 0);

        g.drawImage(keyAAnimation.getCurrentFrame(), controlX + 10, controlY + 80 + controlSpacing, null);
        drawName(g, "Tecla A", controlX + 10, controlY + 70 + controlSpacing - 10, 15);
        textDescription(g, "Con esta tecla giras hacia \n la izquierda", controlX + 60, controlY + 95 + controlSpacing, 0, 0);

        g.drawImage(keyDAnimation.getCurrentFrame(), controlX + 10, controlY + 80 + 2 * controlSpacing, null);
        drawName(g, "Tecla D", controlX + 10, controlY + 70 + 2 * controlSpacing - 10, 15);
        textDescription(g, "Con esta tecla giras hacia \n la derecha", controlX + 60, controlY + 95 + 2 * controlSpacing, 0, 0);

        g.drawImage(keyPAnimation.getCurrentFrame(), controlX + 10, controlY + 80 + 3 * controlSpacing, null);
        drawName(g, "Tecla P", controlX + 10, controlY + 70 + 3 * controlSpacing - 10, 15);
        textDescription(g, "Con esta tecla disparas", controlX + 60, controlY + 95 + 3 * controlSpacing, 0, 0);

        // Dibujar power-ups en el cuadro derecho
        int powerUpX = Constants.WIDTH - 500;
        int powerUpY = 300;
        int powerUpSpacing = 95;
        int powerUpWidth = 350;
        int powerUpHeight = 70 + 4 * powerUpSpacing;

        g.setColor(new Color(0, 0, 0, 150));
        g.fillRoundRect(powerUpX, powerUpY, powerUpWidth, powerUpHeight, 20, 20);

        g.setColor(Color.WHITE);
        g.setFont(Assets.fontBig);
        g.drawString("Power-Ups", powerUpX + 10, powerUpY - 10);

        drawPowerUpImage(g, doubleGun, powerUpX + 10, powerUpY + 30);
        drawName(g, "Doble cañon", powerUpX + 10, powerUpY + 20 - 10, 15);
        textDescription(g, "Por un tiempo limitado agrega \n un caño mas para un doble \n disparo", powerUpX + 100, powerUpY + 55, 10, 0);

        drawPowerUpImage(g, shield, powerUpX + 10, powerUpY + 30 + 1 * powerUpSpacing);
        drawName(g, "Escudo", powerUpX + 10, powerUpY + 30 + powerUpSpacing - 10, 5);
        textDescription(g, "Activa un resistente campo de \n fuerza que te protege de los \n meteoritos", powerUpX + 100, powerUpY + 50 + 1 * powerUpSpacing, 10, 0);

        drawPowerUpImage(g, doubleScore, powerUpX + 10, powerUpY + 30 + 2 * powerUpSpacing);
        drawName(g, "Dobles puntos", powerUpX + 10, powerUpY + 20 + 2 * powerUpSpacing - 10, 15);
        textDescription(g, "Los objetos te otorgan el \n doble de puntos por un tiempo \n limitado", powerUpX + 100, powerUpY + 50 + 2 * powerUpSpacing, 10, 0);

        drawPowerUpImage(g, fastFire, powerUpX + 10, powerUpY + 30 + 3 * powerUpSpacing);
        drawName(g, "Disparo rapido", powerUpX + 10, powerUpY + 20 + 3 * powerUpSpacing - 10, 15);
        textDescription(g, "Por un tiempo limitado disparas \n mucho mas rapido", powerUpX + 100, powerUpY + 50 + 3 * powerUpSpacing, 10, 0);

        drawPowerUpImage(g, star, powerUpX + 10, powerUpY + 30 + 4 * powerUpSpacing);
        drawName(g, "Estrella", powerUpX + 10, powerUpY + 20 + 4 * powerUpSpacing - 10, 15);
        textDescription(g, "Te otorga 1000 puntos extras", powerUpX + 100, powerUpY + 50 + 4 * powerUpSpacing, 10, 0);
    }

    private void drawPowerUpImage(Graphics g, BufferedImage image, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    private void drawName(Graphics g, String powerUpName, int x, int y, int aboveOffsetY) {
        g.setColor(Color.WHITE);
        g.setFont(Assets.fontMed);
        g.drawString(powerUpName, x, y + aboveOffsetY);
    }

    private void textDescription(Graphics g, String customTextLeft, int x, int y, int leftOffsetX, int leftOffsetY) {
        g.setColor(Color.WHITE);
        g.setFont(Assets.fontTiny);
        int lineHeight = g.getFontMetrics().getHeight();

        String[] lines = customTextLeft.split("\n");

        for (int i = 0; i < lines.length; i++) {
            int lineY = y + i * lineHeight + leftOffsetY;
            g.drawString(lines[i], x + leftOffsetX, lineY);
        }
    }
}
