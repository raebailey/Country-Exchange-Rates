package ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatDarkLaf;

public class CustomWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9153467656675809168L;

	public CustomWindow() {
		initialize();
	}
	
	private void initialize() {
		FlatDarkLaf.setup();
		setTitle("Exchange Rate Tracker");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomWindow.class.getResource("/images/main_icon.png"))); 
		setBounds(100, 100, 1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
	}

}
