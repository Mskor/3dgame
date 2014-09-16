package oyakov.dgame.graphics;

import java.awt.image.DataBuffer;

import oyakov.dgame.CurrentModel;

public interface Engine {
	public void render (CurrentModel model, DataBuffer renderTarget);
}
