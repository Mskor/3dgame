package oyakov.dgame.input;

public class Controller {
	public double x, y, z, rotation, xa, za, rotationa;
	public static boolean turnLeft = false;
	public static boolean turnRight = false;
	public static boolean walking = false;
	
	public void tick(boolean forward, boolean back, boolean left, boolean right, boolean jump, boolean crouch, boolean run) {
		double rotationSpeed = 0.01;
		double walkSpeed = 0.5;
		double jumpHeight = 0.5;
		double xMove = 0;
		double zMove = 0;

		walking = false;
		if (forward) {
			zMove++;
			walking = true;
		}
		if (back) {
			zMove--;
			walking = true;
		}
		if (left) {
			xMove--;
			walking= true;
		}
		if (right) {
			xMove++;
			walking= true;
		}
		if (turnLeft) {
			rotationa -= rotationSpeed;
		}
		if (turnRight) {
			rotationa += rotationSpeed;
		}
		if(jump){
			y += jumpHeight;
			run = false;
		}
		if(crouch){
			y-=jumpHeight;
			run = false;
		}
		if(run){
			walkSpeed = 1;
		}
		
		xa += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
		za += (zMove * Math.cos(rotation) - xMove * Math.sin(rotation)) * walkSpeed;

		x += xa;
		y *= 0.90;
		z += za;
		xa *= 0.1;
		za *= 0.1;
		rotation += rotationa;
		rotationa *= 0.8;

	}
}
