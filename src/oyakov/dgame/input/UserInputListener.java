package oyakov.dgame.input;

import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import oyakov.dgame.input.InputHandler.CurrentTickAction;

public interface UserInputListener extends KeyListener, MouseMotionListener,
		MouseListener, FocusListener {
	public CurrentTickAction extractAction();
}
