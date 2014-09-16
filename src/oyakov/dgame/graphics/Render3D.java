package oyakov.dgame.graphics;

import java.util.Random;

import oyakov.dgame.Game;
import oyakov.dgame.input.Controller;

public class Render3D extends Render {
	
	public double[] zBuffer;
	private double renderDistance = 5000.0;
	Random random = new Random();
	StaticLoader loader = new StaticLoader();
	Render floorTexCache = loader.getResource("floor.png");
	
	public Render3D(int width, int heght) {
		super(width, heght);
		zBuffer = new double[width * height];
	}

	public void renderBounds(Game game) {
		
		double floorPosition = 8.0;
		double ceilingPosition = 8.0;
		
		double forwardMovement = game.controls.z;
		double sideMovement = game.controls.x;
		
		double rotation = game.controls.rotation;
		double cosine = Math.cos(rotation);
		double sine = Math.sin(rotation);
		double up = Math.sin(game.time / 10.0) * 2.0;
		double walkingShake = Controller.walking ? Math.sin(game.time / 6.0) * 0.5 : 0;
		
		for (int y = 0; y < height; y++) {
			double ceiling = (y - height / 2.0) / height;
			
			double z = (floorPosition  + game.controls.y + walkingShake)/ ceiling;
			
			if (ceiling < 0) {
				z = (ceilingPosition - game.controls.y - walkingShake) / ceiling;
			}			
									
			for (int x = 0; x < width; x++) {				
				double depth = (x - width / 2.0) / height;				
				depth *= z;				
				double xx = depth * cosine + z * sine;
				double yy = z * cosine - depth * sine;
				int xPix = (int) (xx + sideMovement);
				int yPix = (int) (yy + forwardMovement);
				zBuffer[x + y * width] = z;				
				pixels[x + y * width] = floorTexCache.pixels[(xPix & 7) + (yPix & 7) * 8];				
			}
		}
		
		double xx = random.nextDouble();
		double yy = random.nextDouble();
		double zz = 1;
		
		int xPixel = (int) (xx / zz  * height / 2 + width);
		
		
		pixels[10 + 10 * width] = 10;
	}
	
	public void renderDistanceLimiter(){
		for(int i = 0; i < width * height; i++){
			int colour = pixels[i];
			int brightness = (int) (renderDistance / zBuffer[i]);
			
			if(brightness < 0){
				brightness = 0;
			}
			
			if(brightness > 255){
				brightness = 255;
			}
			
			int r = (colour >> 16) & 0xff;
			int g = (colour >> 8) & 0xff;
			int b = (colour) & 0xff;
			
			r = r * brightness / 255;
			g = g * brightness / 255;
			b = b * brightness / 255;
			
			pixels[i] = r << 16 | g << 8 | b;
	}
	}
		
}
