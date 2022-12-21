package models;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pages.DetailPage;
import ui.CustomWindow;
import ui.Detail;
import ui.Home;

public class PageFactory {

	private Home homepage;
	private Detail detailpage;
	private JPanel previouspage;
	private JPanel currentpage;

	public PageFactory() {
	}

	public JFrame getHome() {
		if (homepage == null) {
			homepage = new Home();
		}
		currentpage = (JPanel)homepage.getMainPanel();
		homepage.setVisible(true);
		return homepage;
	}

	public void getDetail(Country country) {
//		homepage.getContentPane().removeAll();
//		previouspage = currentpage;
//		currentpage = new DetailPage();
//		homepage.getContentPane().add(currentpage);
//		homepage.repaint();
//		homepage.revalidate();
		
		if (detailpage == null) {
			detailpage = new Detail();
		}
		
		homepage.getContentPane().removeAll();
		previouspage = currentpage;
		detailpage.setCountry(country);
		currentpage = detailpage.getMainPanel();
		homepage.getContentPane().add(currentpage);
		homepage.repaint();
		homepage.revalidate();
		
//		detailpage.setVisible(true);
//			
//		return detailpage;
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
