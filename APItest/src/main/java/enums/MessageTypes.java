package enums;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public enum MessageTypes {
		NOUPDATE("No Update","/images/update.png"),
		NEWCOUNTRY("New Country"),
		ERROR("Error","/images/warning.png"),
		NEWRATE("Rate"),
		REJECT("Reject","/images/reject.png");
//		SECONDARY(new Color(203, 209, 219), new Color(58, 70, 81), new Color(81, 92, 108), new Color(230, 239, 255)),
//		DESTRUCTIVE(new Color(255, 0, 0), new Color(255, 255, 255), new Color(198, 86, 0), new Color(255, 161, 90)),
//		CLOSE(new Color(211, 211, 211, 30), new Color(255, 255, 255), new Color(120, 120, 120), new Color(255, 0, 0),
//				new Color(255, 255, 255));

		private MessageTypes(String title,String path) {
			this.title = title;
			Image img;
			img = readImage(path);
			img = img.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
			this.path = img;
		}
		
		private Image readImage(String path) {
			Image img = null;
			try {
				img = ImageIO.read(getClass().getResource(path));
				return img;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return img;
		}
		
		private MessageTypes(String title) {
			this.title = title;
		}

		
		private String title;
		private Image path;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Image getPath() {
			return path;
		}

		public void setPath(Image path) {
			this.path = path;
		}
		
		
		

}
