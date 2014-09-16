package oyakov.dgame.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements UserInputListener{

	// This object holds all raw user input.
	// It is updated asynchronously.
	// 
	private class ActionDescriptor{
		public boolean[] keyboard = new boolean[68836];
		public int MouseX;
		public int MouseY;
		public int prevMouseX;
		public int prevMouseY;
	}
	
	// This object represents meaning of user input.
	// e.g pressing W key means that character is going to go forward.
	public class CurrentTickAction{
		public boolean forward;
		public boolean back;
		public boolean left;
		public boolean right;
		public boolean jump;
		public boolean crouch;
		public boolean run;
		public boolean isTurningLeft;
		public boolean isTurningRight;
	}
	
	// fetch current inputs from ActionDescriptor and convert
	// them to semantical CurrentTickAction data structure
	public CurrentTickAction extractAction(){		 
		cta.forward = rawInput.keyboard[KeyEvent.VK_W];
		cta.back = rawInput.keyboard[KeyEvent.VK_S];
		cta.left = rawInput.keyboard[KeyEvent.VK_A];
		cta.right = rawInput.keyboard[KeyEvent.VK_D];
		cta.jump = rawInput.keyboard[KeyEvent.VK_SPACE];
		cta.crouch = rawInput.keyboard[KeyEvent.VK_CONTROL];
		cta.run = rawInput.keyboard[KeyEvent.VK_SHIFT];		
		int mouseDeltaX  = rawInput.MouseX - rawInput.prevMouseX;
		cta.isTurningRight = false;
		cta.isTurningLeft = false;
		if(mouseDeltaX > 0)
			cta.isTurningRight = true;			
		else if(mouseDeltaX < 0)
			cta.isTurningLeft = true;		
		
		return cta;
	}
	
	private ActionDescriptor rawInput = new ActionDescriptor();
	private CurrentTickAction cta = new CurrentTickAction();
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode > 0 && keyCode < rawInput.keyboard.length){
			rawInput.keyboard[keyCode] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode > 0 && keyCode < rawInput.keyboard.length){
			rawInput.keyboard[e.getKeyCode()] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		rawInput.MouseX = e.getX();
		rawInput.MouseY = e.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		for(int i = 0; i < rawInput.keyboard.length; i++){
			rawInput.keyboard[i] = false;
		}
	}

}
