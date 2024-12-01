package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
  
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Object.Obj_heart;
import Object.Obj_key;
import Object.SuperObject;

public class UI {
	GamePanel gp;
	Font arila_50l, arila_80l;

	public boolean messageOn = false;
	boolean backOpenOn = false;

	int messageCounter = 0;

	public String message;

	public BufferedImage KeyImage, full_heart, half_heart, no_heart, weapon;

	// New images for life and level

	Graphics2D g2;

	public UI(GamePanel gp) {
		this.gp = gp;
		arila_50l = new Font("Arial", Font.PLAIN, 30);
		arila_80l = new Font("Arial", Font.PLAIN, 20);
		Obj_key Objkey = new Obj_key();
		KeyImage = Objkey.ObjImage;

		SuperObject ht = new Obj_heart();
		full_heart = ht.ObjImage1;
		half_heart = ht.ObjImage2;
		no_heart = ht.ObjImage3;

		try {
			weapon = ImageIO.read(getClass().getClassLoader().getResource("Utilitys/skull.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void showMessage(String text) {
		messageOn = true;
		message = text;
	}

	public void showBack(boolean gama) {
		backOpenOn = gama;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		if (messageOn) {
			int boxWidth = 400;
			int boxHeight = 100;
			int boxX = (gp.ScreenWidth - boxWidth) / 2;
			int boxY = 80;

			GradientPaint gradient = new GradientPaint(boxX, boxY, new Color(50, 0, 0, 180), boxX, boxY + boxHeight,
					new Color(80, 0, 0, 220));
			g2.setPaint(gradient);
			g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 25, 25);

			g2.setColor(new Color(150, 0, 0));
			g2.setStroke(new java.awt.BasicStroke(5));
			g2.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 25, 25);

			g2.setFont(arila_80l);
			g2.setColor(Color.white);
			g2.drawString(message, boxX + 20, boxY + (boxHeight / 2) + 10);
		}

		g2.setFont(arila_50l);
		g2.setColor(Color.white);
		g2.drawImage(KeyImage, 24, 64, 40, 40, null);
		g2.drawString("X" + gp.playHand.hashKey, 66, 88);
		messageCounter++;

		if (messageCounter > 60) {
			messageOn = false;
			messageCounter = 0;
		}

		drawPlayerLife();
	}

	public void drawBack(Graphics2D g2) {
		if (backOpenOn) {

			int boxWidth = 300;
			int boxHeight = 400;
			int boxX = 50;
			int boxY = 80;

			// Create a transparent gray background
			GradientPaint gradient = new GradientPaint(boxX, boxY, new Color(128, 128, 128, 100), boxX,
					boxY + boxHeight, new Color(10, 10, 10, 150)); 
			g2.setPaint(gradient);
			g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 25, 25);

			g2.setColor(Color.white); 
			g2.setStroke(new java.awt.BasicStroke(5));
			g2.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 25, 25);

			g2.setFont(arila_80l);
			g2.setColor(Color.white);
			g2.drawString("life", boxX + 20, boxY + 30);
			g2.drawString("6/" + gp.maxlife, boxWidth - 100, boxY + 30); 

			g2.drawString("level", boxX + 20, boxY + 60);
			g2.drawString("1", boxWidth - 100, boxY + 60);

			g2.drawString("weapon", boxX + 20, boxY + 140);
			g2.drawImage(weapon, boxWidth - 100, boxY + 120, 32, 32, null); 
			
		}
	}

	public void drawPlayerLife() {
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;

		int fullHearts = gp.maxlife / 2;
		int halfHeart = (gp.maxlife % 2);
		int emptyHearts = (gp.maxlife / 2) - fullHearts - halfHeart;

		for (int i = 0; i < fullHearts; i++) {
			g2.drawImage(full_heart, x, y, gp.tileSize, gp.tileSize, null);
			x += gp.tileSize;
		}

		if (halfHeart > 0) {
			g2.drawImage(half_heart, x, y, gp.tileSize, gp.tileSize, null);
			x += gp.tileSize;
		}

		for (int i = 0; i < emptyHearts; i++) {
			g2.drawImage(no_heart, x, y, gp.tileSize, gp.tileSize, null);
			x += gp.tileSize;
		}
	}
}
