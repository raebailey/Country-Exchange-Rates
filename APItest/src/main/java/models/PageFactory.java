package models;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pages.CustomPage;
import pages.DetailPage;
import pages.HomePage;
import ui.CustomWindow;
import ui.Detail;
import ui.Home;

public class PageFactory {

	private Home homepage;
	private CustomPage previouspage;
	private CustomPage currentpage;

	public PageFactory() {
		
	}
	
	
	public void getHome() {
		if (homepage == null) {
			homepage = new Home();
		}
		currentpage = new HomePage();
		homepage.setPage(currentpage);
		homepage.setVisible(true);
	}

	public void getDetail(Country country) {
		previouspage = currentpage;
		previouspage.setIsactive(false);
		currentpage = new DetailPage(country);
		homepage.setPage(currentpage);
	}
	
	public Home getWindow() {
		return homepage;
	}
	
	public void back() {
		if(previouspage!=null) {
			homepage.getContentPane().removeAll();
			currentpage = previouspage;
			previouspage = null;
			homepage.setPage(currentpage);
		}
	}
	
	public void closePage(CustomWindow frame) {
		frame.dispose();
	}
}
