package tasks;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLoader_Task extends Thread implements Runnable {

	private String[] imageUrls;
	private Object[] imageComponents;

	/**
	 * Creates thread to load image urls.
	 * @param components An array of components which image will be set.
	 * @param urls An array of urls of images to be loaded.
	 */
	public ImageLoader_Task(Object[] components, String[] urls) {
		imageUrls = urls;
		imageComponents = components;
	}

	@Override
	public void run() {
		for (int i = 0; i < imageComponents.length; i++) {
			JLabel label = (JLabel) imageComponents[i];
			String imageUrl = imageUrls[i];
			String default_url = "https://www.freeiconspng.com/uploads/no-image-icon-6.png";
			URL url;
			Image image;
			try {
				url = new URL(default_url);
				image = ImageIO.read(url);

				if (imageUrl != null) {
					url = new URL(imageUrl);
					image = ImageIO.read(url);
				}
				label.setIcon(new ImageIcon(image.getScaledInstance(330, 111, java.awt.Image.SCALE_SMOOTH)));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

////	public String[] getImageUrls() {
////		return imageUrls;
////	}
////	public void setImageUrls(String[] imageUrls) {
////		this.imageUrls = imageUrls;
////	}
////	public Object[] getImageComponents() {
////		return imageComponents;
////	}
////	public void setImageCoponents(Object[] imageComponents) {
////		this.imageComponents = imageComponents;
////	}
//	
//	

}
