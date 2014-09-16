package oyakov.dgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import oyakov.dgame.graphics.Screen;
import oyakov.dgame.input.Controller;
import oyakov.dgame.input.InputHandler;

public class Display extends Canvas implements Runnable{
	private static final long serialVersionUID = -7241599448401532735L;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String title = "dgame 0.0.1";

	private Thread thread;
	private boolean isRunning = false;
	private Screen screen;
	private BufferedImage img;
	private int[] pixels;
	private GameConveyor game;
	private InputHandler input;
	private int fps = 0;

	public Display() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);		
		screen = new Screen(WIDTH, HEIGHT);
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		input = new InputHandler();
		addKeyListener(input);
		addFocusListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		game = new GameConveyor(input, new CurrentModel());
	}
	
	@Override
	public void run() {
		int frames = 0;
		double unprocessedSeconds = 0.0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		boolean ticked = false;
		requestFocus();
		while(isRunning = true){
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime/1000000000.0;
			while(unprocessedSeconds > secondsPerTick){
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				if(tickCount%60 == 0){
					fps = frames;
					previousTime += 1000;
					frames = 0;
				}
			}
			if(ticked){
				render();
				frames++;
			}
			render();
			frames++;
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.render(game);
		
		for(int i = 0; i < WIDTH * HEIGHT; i++){
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH + 10, HEIGHT + 10, null);
		g.setFont(new Font("Buxton Sketch", 0, 20));
		g.setColor(Color.YELLOW);
		g.drawString("FPS: " + fps, 50, 50);
		g.drawString(String.format("X: %1$5.2f", game.controls.x), 50, 80);
		g.drawString(String.format("Z: %1$5.2f", game.controls.z), 50, 110);
		g.drawString(String.format("Y: %1$5.2f", game.controls.y), 50, 140);
		g.dispose();
		bs.show();
	}

	private void tick() {
		game.calcNextTick();		
	}

	public synchronized void start() {
		if (isRunning) {
			return;
		}
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!isRunning) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String args[]) {
		BufferedImage cursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blank = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "blank");
		Display game = new Display();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.setTitle(title);
		frame.getContentPane().setCursor(blank);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		game.start();
	}
}
