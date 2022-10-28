package models;

import javax.swing.JFrame;

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
		return homepage;
	}

	public JFrame getDetail(Country country) {
//		if (detailpage == null) {
			detailpage = new Detail(country);
//		}
		return detailpage;
	}
	
	public void closePage(JFrame frame) {
		frame.dispose();
	}

//	frame.setContentPane(newContents());
//	frame.revalidate(); // frame.pack() if you want to resize.
}
