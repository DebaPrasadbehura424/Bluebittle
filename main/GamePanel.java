package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Object.SuperObject;
import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	// Base settings for the tile size and screen dimensions
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int ScreenWidth = tileSize * maxScreenCol; // 786
	public final int ScreenHeight = tileSize * maxScreenRow; // 576

	// World settings (map dimensions)
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int WorldWidth = maxWorldCol * tileSize;
	public final int WorldHeight = maxWorldRow * tileSize;
	public int maxlife = 6;
	public int life;

	// Create necessary objects and handlers for the game
	public KeyHandler keyH = new KeyHandler(this);
	public Player playHand = new Player(this, keyH);
	TileManager tileM = new TileManager(this);
	public CollsionChecker colc = new CollsionChecker(this);
	public AssertSetter aSetter = new AssertSetter(this);
	public UI ui = new UI(this);

	public SuperObject obj[] = new SuperObject[10]; // World array for objects
	public Entity npc[] = new Entity[10]; // World array for NPCs
	public Entity monster[] = new Entity[10];
	public EventHandler evenH = new EventHandler(this);

	Thread gameThread;

	// Constructor to set up the game panel and prepare for game rendering
	public GamePanel() {
		this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
		this.setFocusable(true);
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
	}

	// Initialize objects and NPCs in the game world
	public void setGame() {
		aSetter.setObject();
		aSetter.setObjectNpc();
		aSetter.setObjectMonster();
	}

	// Start the game thread
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / 60;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		// Main game loop
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	// Update game state
	public void update() {

		if (keyH.backOpenPress == true) {
			ui.showBack(true);
		} else {
			ui.showBack(false);
		}

		if (keyH.pausePress == false) {
			ui.showMessage("tap again for play(p)");
		}
		if (keyH.pausePress) {
			playHand.update();

			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}
			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					monster[i].update();
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Draw all tiles in the world
		tileM.drawTile(g2);

		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this); // Draw each object
			}
		}

		for (int i = 0; i < npc.length; i++) {
			if (npc[i] != null) {
				npc[i].drawNpc(g2); // Draw each NPC
			}
		}
		for (int i = 0; i < monster.length; i++) {
			if (monster[i] != null) {
				monster[i].drawNpc(g2); // Draw each NPC
			}
		}

		// Draw the player
		playHand.draw(g2);

		// Draw the UI
		ui.draw(g2);
		ui.drawBack(g2);
	}
}
