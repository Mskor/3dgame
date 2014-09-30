package oyakov.dgame.graphics;

import oyakov.dgame.GameConveyor;

public class Screen extends Render {
	private Render3D render;
	private RenderLine renderL;

	public Screen(int width, int height) {
		super(width, height);
		render = new Render3D(width, height);
		renderL = new RenderLine(width, height);
	}

	public void render(GameConveyor game) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = 0;
		}		
		//render.renderBounds(game);
		//render.renderDistanceLimiter();
		renderL.renderBounds(game);
		draw(renderL, 0, 0);
	}
}
