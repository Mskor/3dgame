package oyakov.dgame;

import oyakov.dgame.input.Controller;
import oyakov.dgame.input.InputHandler.CurrentTickAction;
import oyakov.dgame.input.UserInputListener;

public class GameConveyor{
	public long time;
	public Controller controls;
	public UserInputListener uiListener;
	
	public GameConveyor(UserInputListener uiListener, CurrentModel initialModel){
		controls = new Controller();
		this.uiListener = uiListener;
	}
	
	public void calcNextTick(){
		time++;
		CurrentTickAction cta = uiListener.extractAction();
		controls.calcNextTick(cta);
	}
}
