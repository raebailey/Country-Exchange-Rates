package tasks;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import cache.CacheManager;

public class ImageLoader_Task extends Thread implements Runnable {

	private String[] imageUrls;
	private Object[] imageComponents;

	/**
	 * Creates thread to load image urls.
	 * 
	 * @param components An array of components which image will be set.
	 * @param urls       An array of urls of images to be loaded.
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
				if (imageUrl == null) {
					Image newImage = (Image) CacheManager.getCacheItem(default_url);
					if (newImage != null) {
						image = newImage;
					} else {
						url = new URL(default_url);
						image = ImageIO.read(url);
						CacheManager.addCache(default_url, image);
					}
				} else {
					Image newImage = (Image) CacheManager.getCacheItem(imageUrl);
					if (newImage != null) {
						image = newImage;
					} else {
						url = new URL(imageUrl);
						image = ImageIO.read(url);
						CacheManager.addCache(imageUrl, image);
					}
				}

				label.setIcon(new ImageIcon(image.getScaledInstance(330, 111, java.awt.Image.SCALE_SMOOTH)));
			} catch (Exception ex) {
				label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("/images/fail image.png")));
			}
		}

	}

}
