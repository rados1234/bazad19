package bazad;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResizeWatek extends Thread {
	File source;
	File dest;
	Ev_Resize ev;
	public ResizeWatek(File source, File dest) {
		this.source = source;
		this.dest = dest;
	}
   public void run() {
	  
	    try {
	    	ev.OnLoad(true);
	    	BufferedImage img = ImageIO.read(source);
	 	    img = resizeImage(img, 200, 200);
			ImageIO.write(img, "jpg", dest);
			ev.OnLoad(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
	    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
	    graphics2D.dispose();
	    return resizedImage;
	}
   public void setListener(Ev_Resize ev) {
	   this.ev = ev;
   }
}
