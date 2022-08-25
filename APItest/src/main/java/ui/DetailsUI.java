package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.formdev.flatlaf.FlatDarkLaf;

import components.CountryCard;
import models.Country;
import sample.DatabaseModel;
import tasks.ImageLoader_Task;

public class DetailsUI {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel panel_2;
	ImageLoader_Task load;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetailsUI window = new DetailsUI();
			window.frame.setVisible(true);
			window.load.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the application.
	 */
	public DetailsUI() {
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
		panel.add(scrollPane);

		panel_2 = new JPanel();
		panel_2.setMinimumSize(new Dimension(1280, 10));
		panel_2.setSize(new Dimension(1280, 0));
		panel_2.setMaximumSize(new Dimension(1280, 32767));
		panel_2.setAutoscrolls(true);
//		panel_2.setPreferredSize(new Dimension(10, 2500));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setAlignment(FlowLayout.LEFT);
		scrollPane.setViewportView(panel_2);

		DatabaseModel model = new DatabaseModel();
		Country[] countries = model.getCountries();
		ArrayList<JLabel> cardArr = new ArrayList<JLabel>();
		ArrayList<String> urlArr = new ArrayList<String>();
		for (Country country : countries) {
			CountryCard card = new CountryCard(country);
			cardArr.add(card.getImagelbl());
			urlArr.add(country.getImageUrl());
			panel_2.add(card);
		}
		JLabel[] cArr = new JLabel[cardArr.size()];
		String[] uArr = new String[urlArr.size()];
		load = new ImageLoader_Task(cardArr.toArray(cArr), urlArr.toArray(uArr));
	}
}
