package tasks;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import cache.CacheManager;
import components.image.ImageHelper;

public class ImageLoader_Task extends Thread implements Runnable {

	private String[] imageUrls;
	private JLabel[] imageComponents;
	private int width = 330;
	private int height = 111;

	/**
	 * Creates thread to load image urls.
	 * 
	 * @param components An array of components which image will be set.
	 * @param urls       An array of urls of images to be loaded.
	 */
	public ImageLoader_Task(JLabel[] components, String[] urls) {
		imageUrls = urls;
		imageComponents = components;
	}

	public ImageLoader_Task(JLabel[] components, String[] urls, int width, int height) {
		imageUrls = urls;
		imageComponents = components;
		this.width = width;
		this.height = height;
	}

	@Override
	public void run() {
		for (int i = 0; i < imageComponents.length; i++) {
			JLabel label = imageComponents[i];
			String imageUrl = imageUrls[i];
			Image image;
			try {
				Image newImage = (Image) CacheManager.getCacheItem(imageUrl);
				if (newImage != null) {
					image = newImage;
				} else {
					URL url = new URL(imageUrl);
					image = ImageIO.read(url);
					CacheManager.addCache(imageUrl, image);
				}

				image = image.getScaledInstance(this.width, this.height, java.awt.Image.SCALE_SMOOTH);
				label.setIcon(new ImageIcon(ImageHelper.makeRoundedCorner(image, 8, this.width, this.height)));
			} catch (Exception ex) {
				label.setIcon(new ImageIcon(ImageHelper.readImage("/images/fail.png", 0, 0)));
			}
		}

	}

}
