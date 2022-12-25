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
		currentpage.setName("Detail_Page");
		homepage.setPage(currentpage);
		System.out.println("current page is: "+currentpage);
		
//		if (detailpage == null) {
//			detailpage = new Detail();
//		}
//		
//		homepage.getContentPane().removeAll();
//		previouspage = currentpage;
//		detailpage.setCountry(country);
//		currentpage = detailpage.getMainPanel();
//		homepage.getContentPane().add(currentpage);
//		homepage.repaint();
//		homepage.revalidate();
		
//		detailpage.setVisible(true);
//			
//		return detailpage;
	}
	
	public Home getWindow() {
		return homepage;
	}
	
	public void back() {
		if(previouspage!=null) {
			homepage.getContentPane().removeAll();
			currentpage = previouspage;
			previouspage = null;
			homepage.getContentPane().add(currentpage);
			homepage.repaint();
			homepage.revalidate();
		}
	}
	
	public void closePage(CustomWindow frame) {
		frame.dispose();
	}

//	frame.setContentPane(newContents());
//	frame.revalidate(); // frame.pack() if you want to resize.
}
