package oyakov.dgame.input;

import oyakov.dgame.CurrentModel;
import oyakov.dgame.input.InputHandler.CurrentTickAction;

public class Controller {
	public double x, y, z, rotation, xa, za, rotationa;
	public static boolean turnLeft = false;
	public static boolean turnRight = false;
	public static boolean walking = false;
	
	public void calcNextTick(CurrentTickAction cta) {
		double rotationSpeed = 0.01;
		double walkSpeed = 0.5;
		double jumpHeight = 0.5;
		double xMove = 0;
		double zMove = 0;

		walking = false;
		if (cta.forward) {
			zMove++;
			walking = true;
		}
		if (cta.back) {
			zMove--;
			walking = true;
		}
		if (cta.left) {
			xMove--;
			walking= true;
		}
		if (cta.right) {
			xMove++;
			walking= true;
		}
		if (cta.isTurningLeft) {
			rotationa -= rotationSpeed;
		}
		if (cta.isTurningRight) {
			rotationa += rotationSpeed;
		}
		if(cta.jump){
			y += jumpHeight;
			cta.run = false;
		}
		if(cta.crouch){
			y-=jumpHeight;
			cta.run = false;
		}
		if(cta.run){
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
