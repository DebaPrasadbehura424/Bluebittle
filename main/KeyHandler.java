package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
	GamePanel gp;
	public boolean upPress, downPress, leftPress, rightPress, pausePress = true, enterPressed, backOpenPress = false;
	int openChoose = 0;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			upPress = true;
		}
		if (code == KeyEvent.VK_S) {
			downPress = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPress = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPress = true;
		}
		if (code == KeyEvent.VK_P) {
			pausePress = (pausePress == true) ? false : true;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if (code == KeyEvent.VK_B) {
			backOpenPress = (backOpenPress == true) ? false : true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			upPress = false;
		}
		if (code == KeyEvent.VK_S) {
			downPress = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPress = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPress = false;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}

	}
}
