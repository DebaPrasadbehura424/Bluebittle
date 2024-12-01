package tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import java.io.BufferedReader;
import java.io.IOException;

import entity.Player;
import main.GamePanel;

public class TileManager {
	public BufferedImage brick, water, grass, sand, earth, tree, box, door, key;
	GamePanel gp;
	Player playerH;
	public Tile[] tile;

	public int mapNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapNum = new int[gp.maxWorldCol][gp.maxWorldRow]; 
		

		getTileImages();
		loadMap("res/map01.txt",0);

	}

	public void getTileImages() {
		String[] pathes = new String[] { "Utilitys/grass3.png", "Utilitys/brick.png", "Utilitys/water.png",
				"Utilitys/earth.png", "Utilitys/tree.png", "Utilitys/sand.png","Utilitys/whole.png" };
		boolean[] Isit = new boolean[] { false, true, true, false, true, false,false };
		for (int i = 0; i <= 6; i++) {
			setImageAll(i, pathes[i], Isit[i]);
		}
	}
	public void setImageAll(int index, String path, boolean check) {
		tile[index] = new Tile();
		try {
			tile[index].Tileimage = ImageIO.read(getClass().getClassLoader().getResource(path));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		tile[index].collision = check;
	}

	public void loadMap(String path,int maplabel) {
		try {
			InputStream io = getClass().getClassLoader().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(io));
			int col = 0;
			int row = 0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void drawTile(Graphics g2) {
		int Worldcol = 0;
		int Worldrow = 0;

		while (Worldrow < gp.maxWorldRow && Worldcol < gp.maxWorldCol) {
			int tilenum = mapNum[Worldcol][Worldrow];

			int worldX = Worldcol * gp.tileSize;
			int worldY = Worldrow * gp.tileSize;

			int screenX = worldX - gp.playHand.worldx + gp.playHand.screenX;
			int screenY = worldY - gp.playHand.worldy + gp.playHand.screenY;

			if (worldX + gp.tileSize > gp.playHand.worldx - gp.playHand.screenX
					&& worldX - gp.tileSize < gp.playHand.worldx + gp.playHand.screenX
					&& worldY + gp.tileSize > gp.playHand.worldy - gp.playHand.screenY
					&& worldY - gp.tileSize < gp.playHand.worldy + gp.playHand.screenY) {

				g2.drawImage(tile[tilenum].Tileimage, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			Worldcol++;
			if (Worldcol == gp.maxWorldCol) {
				Worldcol = 0;
				Worldrow++;
			}
		}

	}
}
