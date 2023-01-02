package ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.formdev.flatlaf.FlatDarkLaf;

import pages.CustomPage;
import java.awt.Dimension;
import java.awt.ComponentOrientation;

public class CustomWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9153467656675809168L;
	private CustomPage currentPage;
	private boolean internetStat=true;
	private String internetMsg;

	public CustomWindow() {
		initialize();
		javax.swing.Timer timer = new javax.swing.Timer(10000, this);
		timer.start();
//		try {
//		      Process process = Runtime.getRuntime().exec("netsh wlan show interfaces");
//		      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//		      String line;
//		      while ((line = reader.readLine()) != null) {
//		        if (line.contains("Signal")) {
//		          String[] parts = line.split(":");
//		          String strength = parts[1].trim();
//		          JOptionPane.showMessageDialog(this, "WiFi strength: " + strength, "WiFi Strength", JOptionPane.INFORMATION_MESSAGE);
//		        }
//		      }
//		      reader.close();
//		    } catch (Exception e) {
//		      e.printStackTrace();
//		    }
	}

	private void initialize() {
		FlatDarkLaf.setup();
		setTitle("Exchange Rate Tracker");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomWindow.class.getResource("/images/main_icon.png")));
		setBounds(100, 100, 1148, 720);
		setMinimumSize(new Dimension(1148, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
	}

	public void setPage(CustomPage page) {
		getContentPane().removeAll();
		getContentPane().add(page);
		setActivePage(page);
		revalidate();
		repaint();
	}

	public String getInternetMsg() {
		return internetMsg;
	}

	private void setInternetMsg(String internetMsg) {
		this.internetMsg = internetMsg;
	}

	/**
	 * Returns the current page the user is on
	 * 
	 * @return
	 */
	public CustomPage getActivePage() {
		return currentPage;
	}

	/**
	 * Sets the current page to the page the user is on.
	 * 
	 * @param page The page the user is on.
	 */
	private void setActivePage(CustomPage page) {
		this.currentPage = page;
	}

	private void noInternet() {
		setInternetMsg("No Internet Connection");
		if (internetStat) {
			internetStat = false;
//			currentPage.sendNotification(getInternetMsg());
		}
		currentPage.sendNotification(getInternetMsg());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			InetAddress address = InetAddress.getByName("www.google.com");
			if (address.isReachable(3000)) {
				if (!internetStat) {
					internetStat = true;
					currentPage.refresh();
				}
			} else {
				noInternet();
			}
		} catch (Exception ex) {
			noInternet();
//			ex.printStackTrace();
		}

	}

	

}
