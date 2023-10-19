package bazad;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PobierzZdj extends Thread{
	public PobierzZdj() {
		
	}
	
  public void run() {
	  try {
  	  //  BufferedImage img = ImageIO.read(selectedFile);
  	  //  img = resizeImage(img, 200, 200);
  	  //  ImageIO.write(img, "jpg", tmpZdj);
  	 //   selectedFile = tmpZdj;
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
  }
}
