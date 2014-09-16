package oyakov.dgame.graphics;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class StaticLoader {
	
	public static HashMap<String, Render> resourceRegistry = new HashMap<String, Render>();
	
	private final String STATIC_RES_DIR = "/texture/"; 
	
	private void loadResource(String fileName){
		try{
			BufferedImage image  = ImageIO.read(StaticLoader.class.getResource(fileName));
			
			int width = image.getWidth();
			int height = image.getHeight();
			Render result = new Render(width, height);
			image.getRGB(0, 0, width, height, result.pixels, 0, width);
			String[] t;
			resourceRegistry.put((t = fileName.split("/"))[t.length - 1], result);
		}catch(Exception e){
			throw new RuntimeException("Error while loading textures");
		}
	}
		
	public synchronized Render getResource(String resName){
		if(!resourceRegistry.containsKey(resName)){
			loadResource(STATIC_RES_DIR + resName);
		}
		return resourceRegistry.get(resName);
	}

	
}
