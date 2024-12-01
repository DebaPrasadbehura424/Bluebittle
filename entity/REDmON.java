package entity;

import java.util.Random;

import javax.swing.ImageIcon;

import main.GamePanel;

public class REDmON extends Entity {
	int skipCouneter = 0;


	public REDmON(GamePanel gp) {
		super(gp);
		type=2;
		direction = "s";
		speed = 3;
		getPlayerImages();
	}

	public void getPlayerImages() {
		body1 = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/red.png")).getImage();
		body2 = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/blue.png")).getImage();
		bodyback1 = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/red.png")).getImage();
		bodyback2 = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/blue.png")).getImage();
		left = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/red.png")).getImage();
		right = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/blue.png")).getImage();

	}

	public void setMovementMonster() {

		skipCouneter++;
		if (skipCouneter >= 120) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;
			if (i <= 25) {
				direction = "w";
			}
			if (i > 25 && i <= 50) {
				direction = "s";
			}
			if (i > 50 && i <= 75) {
				direction = "a";
			}
			if (i > 75 && i <= 100) {
				direction = "d";
			}
			skipCouneter = 0;

			
		}

	}
	
}
