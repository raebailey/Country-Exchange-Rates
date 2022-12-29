package pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.UUID;

import javax.swing.JPanel;

import pagination.Pagination;

public class CustomPage extends JPanel implements InternetConnectAction {
	final static int width = 1150;
	final static int height = 720;
	private String identifier;
	private String name;
	private boolean isactive;
	private int pageNumber;
	private Pagination pagination;

	public CustomPage() {
		this.identifier = UUID.randomUUID().toString();
		this.isactive = true;
		pagination = new Pagination();
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

}
