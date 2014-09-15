package oyakov.dgame.graphics;

public class Render {
	public final int width;
	public final int height;
	public final int[] pixels;

	public Render(int width, int heght) {
		this.width = width;
		this.height = heght;
		pixels = new int[width * heght];
	}

	public void draw(Render render, int xOffset, int yOffset) {
		for (int y = 0; y < render.height; y++) {
			int yPix = y + yOffset;
			if(yPix < 0 || yPix >= height){
				continue;
			}
			for (int x = 0; x < render.width; x++) {
				int xPix = x + xOffset;
				if(xPix < 0 || xPix >= width){
					continue;
				}
				int alpha = render.pixels[x + y * render.width];
				if(alpha > 0){
					pixels[xPix + yPix * width] = alpha;
				}
			}
		}
	}
}
