package Object;

import javax.imageio.ImageIO;

public class Obj_boot extends SuperObject {
	public Obj_boot() {
		name = "boot";
		try {
			ObjImage = ImageIO.read(getClass().getClassLoader().getResource("Utilitys/Boot.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
