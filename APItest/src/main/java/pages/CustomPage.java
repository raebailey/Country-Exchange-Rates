package pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.UUID;

import javax.swing.JPanel;

import pagination.Pagination;

public class CustomPage extends JPanel implements InternetConnectAction,MouseMotionListener{
	final static int width = 1150;
	final static int height = 720;
	private String identifier;
	private String name;
	private boolean isactive;
	private int pageNumber = 1;
	private Pagination pagination;

	public CustomPage() {
		this.identifier = UUID.randomUUID().toString();
		this.isactive = true;
		pagination = new Pagination();
		setSize(new Dimension(width, height));
		setLayout(new BorderLayout(0, 0));
		addMouseMotionListener(this);
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

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "CustomPage [identifier=" + identifier + ", name=" + name + ", isactive=" + isactive + "]";
	}

	@Override
	public void sendNotification(String message) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Dragged Page we are on is: "+getName()+" Mouse points x: "+e.getX()+" y: "+e.getY());
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("Page we are on page "+getPageNumber()+" of: "+getName()+" Mouse points x: "+e.getX()+" y: "+e.getY());
	}

}
