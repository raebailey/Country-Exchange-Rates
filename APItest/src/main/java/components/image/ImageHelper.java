package components.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageHelper {
	
	/**
	 * Creates and returns an image with round corners
	 * @param image The Image object that is to have round corners.
	 * @param radius The amount the corners should be rounded by.
	 * @param width The proposed width of new image;
	 * @param height The proposed height of new image;
	 * @return Image
	 */
	public static Image makeRoundedCorner(Image image, int radius,int width,int height) {
	    int w = width;
	    int h = height;
	    Image output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2 = (Graphics2D)output.getGraphics();
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, radius, radius));
	    g2.setComposite(AlphaComposite.SrcIn);
	    g2.drawImage(image, 0, 0, null);
	    
	    g2.dispose();
	    
	    return output;
	}
	
	/**
	 * Reads path of images found in application resource folder.
	 * @param path The file path of image needed.
	 * @return Image
	 */
	public static Image readImage(String path,int width,int height) {
		Image img = null;
		try {
			if(width==0||height==0) {
				width = height = 24;
			}
			img = ImageIO.read(ImageHelper.class.getResource(path));
			img = img.getScaledInstance(width,height, Image.SCALE_SMOOTH);
			img = ImageHelper.makeRoundedCorner(img,8,width,height);
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

}
