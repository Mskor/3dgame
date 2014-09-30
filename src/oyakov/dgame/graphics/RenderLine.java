package oyakov.dgame.graphics;

import oyakov.dgame.GameConveyor;
import oyakov.dgame.base.Line;

public class RenderLine extends Render {

	public Line lineA = new Line(500, 500, 700, 300);

	public RenderLine(int width, int height) {
		super(width, height);
	}

	public void renderBounds(GameConveyor game) {
		lineA.draw(pixels, width, height);
		lineA.rotate(Math.PI / 32, 500, 500);
	}
}
