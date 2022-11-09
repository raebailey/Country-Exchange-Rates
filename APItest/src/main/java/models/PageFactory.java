package models;

import javax.swing.JFrame;

import ui.CustomWindow;
import ui.Detail;
import ui.Home;

public class PageFactory {

	private Home homepage;
	private Detail detailpage;

	public PageFactory() {
	}

	public JFrame getHome() {
		if (homepage == null) {
			homepage = new Home();
		}
		homepage.setVisible(true);
		return homepage;
	}

	public JFrame getDetail(Country country) {
//		if (detailpage == null) {
			detailpage = new Detail(country);
//		}
			detailpage.setVisible(true);
		return detailpage;
	}
	
	public void closePage(CustomWindow frame) {
		frame.dispose();
	}

//	frame.setContentPane(newContents());
//	frame.revalidate(); // frame.pack() if you want to resize.
}
