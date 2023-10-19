package bazad;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ZmienRozmiar extends Thread {
	File input;
	File output;
	Ev_Thread ev;
	public ZmienRozmiar(File input, File output) {
		this.input = input;
		this.output = output;
	}

	public void run() {
		try {
			ev.onLoad(true);
			BufferedImage img = ImageIO.read(input);
			img = resizeImage(img, 200, 200);
			ImageIO.write(img, "jpg", output);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			ev.onLoad(false);
		}
	}
    public void setListener(Ev_Thread ev) {
    	this.ev = ev;
    }
	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}
}
