package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.formdev.flatlaf.FlatDarkLaf;

import components.CountryCard;
import components.ScrollBarCustom;
import components.TableDark;
import models.Country;
import sample.DatabaseModel;
import tasks.Country_Task;
import tasks.ImageLoader_Task;
import tasks.UpdateRate_Task;

import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Home {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel panel_2;
	ImageLoader_Task load;
	private JPanel panel_1;
	private JPanel panel_4;
	private JTextField textField;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Home window = new Home();
			window.frame.setVisible(true);
			window.load.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		FlatDarkLaf.setup();
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		ScrollBarCustom scrollBarCustom = new ScrollBarCustom();
		scrollBarCustom.setUnitIncrement(5);
		scrollPane.setVerticalScrollBar(scrollBarCustom);
		ScrollBarCustom scrollBarCustom_1 = new ScrollBarCustom();
		scrollBarCustom_1.setOrientation(JScrollBar.HORIZONTAL);
		scrollPane.setHorizontalScrollBar(scrollBarCustom_1);
		
		
		

		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(1200, 7000));
		
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
		scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel_2);
		panel.add(scrollPane);
		
		panel_2.setMinimumSize(new Dimension(1280, 10));
		panel_2.setSize(new Dimension(1280, 0));
		panel_2.setMaximumSize(new Dimension(1280, 32767));
		panel_2.setAutoscrolls(true);
//		panel_2.setPreferredSize(new Dimension(10, 2500));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		flowLayout.setVgap(20);
		flowLayout.setHgap(20);
		flowLayout.setAlignOnBaseline(true);
		scrollPane.setViewportView(panel_2);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 40));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(300, 10));
		panel_1.add(panel_4, BorderLayout.EAST);
		panel_4.setBackground(new Color(53, 57, 53));
		panel_4.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 0, 210, 40);
		panel_4.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(209, 0, 91, 38);
		panel_4.add(lblNewLabel_1);

		DatabaseModel model = new DatabaseModel();
		Country[] countries = model.getCountries();
		ArrayList<JLabel> cardArr = new ArrayList<JLabel>();
		ArrayList<String> urlArr = new ArrayList<String>();
		for (Country country : countries) {
			CountryCard card = new CountryCard(country,frame);
			cardArr.add(card.getImagelbl());
			urlArr.add(country.getImageUrl());
			panel_2.add(card);
		}
		JLabel[] cArr = new JLabel[cardArr.size()];
		String[] uArr = new String[urlArr.size()];
		load = new ImageLoader_Task(cardArr.toArray(cArr), urlArr.toArray(uArr));
	}
}
