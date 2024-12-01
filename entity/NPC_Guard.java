package entity;

import java.util.Random;

import javax.swing.ImageIcon;

import main.GamePanel;

public class NPC_Guard extends Entity {

	int skipCouneter = 0;

	public NPC_Guard(GamePanel gp) {
		super(gp);
		direction = "s";
		speed = 1;
		getPlayerImages();

	}

	public void getPlayerImages() {
		body1 = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/Guard1.png")).getImage();
		body2 = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/Guard2.png")).getImage();
		bodyback1 = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/Guard1.png")).getImage();
		bodyback2 = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/Guard2.png")).getImage();
		left = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/Guard1.png")).getImage();
		right = new ImageIcon(getClass().getClassLoader().getResource("Utilitys/Guard2.png")).getImage();

	}

	public void setMovementNpc() {

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
