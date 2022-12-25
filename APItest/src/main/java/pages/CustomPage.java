package pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.UUID;

import javax.swing.JPanel;

public class CustomPage extends JPanel{
	final static int width =1280;
	final static  int height = 720;
	private String identifier;
	private String name;
	private boolean isactive;
	
	public CustomPage() {
		this.identifier = UUID.randomUUID().toString();
		this.isactive = true;
		setSize(new Dimension(width, height));
		setLayout(new BorderLayout(0, 0));
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "CustomPage [identifier=" + identifier + ", name=" + name + ", isactive=" + isactive + "]";
	}
	
	

}
