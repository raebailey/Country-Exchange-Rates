package enums;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import components.image.ImageHelper;

public enum MessageTypes {
		NOUPDATE("No Update","/images/update.png"),
		NEWCOUNTRY("New Country"),
		REJECT("Reject","/images/warning.png"),
		NEWRATE("New Rate","/images/money.png"),
		ERROR("Error","/images/reject.png",new Color(201, 32, 32));

		private MessageTypes(String title,String path) {
			this.title = title;
			Image img;
			img = ImageHelper.readImage(path,0,0);
			img = img.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
			this.imageObj = img;
		}
		
		private MessageTypes(String title,String path,Color color) {
			this.title = title;
			Image img;
			img = ImageHelper.readImage(path,0,0);
			img = img.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
			this.imageObj = img;
			this.background = color;
		}
		
		private MessageTypes(String title) {
			this.title = title;
		}

		
		private String title;
		private Image imageObj;
		private Color background;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Image getImageObj() {
			return imageObj;
		}

		public void setImageObj(Image imageObj) {
			this.imageObj = imageObj;
		}

		public Color getBackground() {
			return background;
		}

		public void setBackground(Color background) {
			this.background = background;
		}
		
		
		
		
		

}
