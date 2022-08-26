package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;

import cache.CacheManager;
import components.CustomButton;
import components.CustomButton.ButtonStyle;
import components.LineChart;
import components.ModelChart;
import components.RoundPanel;
import components.TableDark;
import models.Country;
import models.Rate;
import net.miginfocom.swing.MigLayout;
import sample.DatabaseModel;

public class Detail {

	private JFrame frame;
	private JTextField textField;


	/**
	 * Create the application.
	 */
	public Detail(Country country) {
		initialize(country);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Country country) {
		FlatDarkLaf.setup();
		frame = new JFrame();
		frame.getContentPane().setSize(new Dimension(1280, 0));
		frame.getContentPane().setPreferredSize(new Dimension(1280, 0));
		frame.getContentPane().setMinimumSize(new Dimension(1280, 0));
		frame.setPreferredSize(new Dimension(1280, 0));
		frame.setMinimumSize(new Dimension(1280, 0));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel body = new JPanel();
		frame.getContentPane().add(body);
		body.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		body.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 10));

		JLabel imageLbl = new JLabel("");

		DatabaseModel model = new DatabaseModel();
//		Country country = model.fetchCountry("JMD");
		String imageUrl = country.getImageUrl();
		String default_url = "https://www.freeiconspng.com/uploads/no-image-icon-6.png";
		URL url;
		Image image;
		try {
			Image newImage = (Image)CacheManager.getCacheItem(default_url);
			if(newImage==null) {
				url = new URL(default_url);
				image = ImageIO.read(url);
			}else {
				image = newImage;
			}
			
			
			
			
			if (imageUrl != null) {
				Image newImage1 = (Image)CacheManager.getCacheItem(imageUrl);
				if(newImage1==null) {
					url = new URL(imageUrl);
					image = ImageIO.read(url);
				}else {
					image = newImage1;
				}
				
			}
			imageLbl.setIcon(new ImageIcon(image.getScaledInstance(320, 160, java.awt.Image.SCALE_SMOOTH)));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		imageLbl.setPreferredSize(new Dimension(320, 180));
		imageLbl.setSize(new Dimension(320, 0));
		panel_8.add(imageLbl);
		imageLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		imageLbl.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_2 = new JPanel();
		panel_8.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[200px][46px,grow]", "[22px][grow][][grow]"));

		JLabel nameLbl = new JLabel(country.getName());
		nameLbl.setFont(new Font("SansSerif", Font.BOLD, 24));
		nameLbl.setPreferredSize(new Dimension(200, 20));
		panel_2.add(nameLbl, "cell 0 0,alignx left,aligny top");

		JLabel lblNewLabel_1 = new JLabel("Geo Coordinates");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_1, "cell 0 1,alignx left,aligny bottom");

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, "cell 1 1,alignx left,aligny bottom");
		FlowLayout fl_panel_4 = new FlowLayout(FlowLayout.LEFT, 5, 0);
		fl_panel_4.setAlignOnBaseline(true);
		panel_4.setLayout(fl_panel_4);

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		JLabel lblNewLabel_5 = new JLabel("Latitude:");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_5);

		JLabel latLbl = new JLabel(String.valueOf(country.getLatitude()));
		latLbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_5.add(latLbl);

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		FlowLayout fl_panel_6 = new FlowLayout(FlowLayout.LEFT, 5, 0);
		fl_panel_6.setAlignOnBaseline(true);
		panel_6.setLayout(fl_panel_6);

		JLabel lblNewLabel_7 = new JLabel("Longitude:");
		lblNewLabel_7.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_6.add(lblNewLabel_7);

		JLabel longLbl = new JLabel(String.valueOf(country.getLongitude()));
		longLbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_6.add(longLbl);

		JLabel lblNewLabel_2 = new JLabel("Currency Code");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_2, "cell 0 2");

		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, "cell 1 2,grow");
		panel_7.setLayout(new MigLayout("", "[46px,grow]", "[]"));

		JLabel currCodeLbl = new JLabel(country.getCurrencyCode().toUpperCase());
		panel_7.add(currCodeLbl, "cell 0 0");

		JLabel lblNewLabel_3 = new JLabel("Rate Earnings");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_3, "cell 0 3");

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, "cell 1 3,alignx left,growy");
		FlowLayout fl_panel_3 = new FlowLayout(FlowLayout.LEADING, 5, 5);
		fl_panel_3.setAlignOnBaseline(true);
		panel_3.setLayout(fl_panel_3);

		JLabel lblNewLabel_4 = new JLabel("- 0.24%");
		panel_3.add(lblNewLabel_4);

		JLabel rateEstLbl = new JLabel("");

		// if (imageUrl != null) {
		// url = new URL(imageUrl);
		// image = ImageIO.read(url);
		// }

		try {
			url = new URL("https://img.icons8.com/emoji/48/000000/red-triangle-pointed-down-emoji.png");
			image = ImageIO.read(url);
			rateEstLbl.setIcon(new ImageIcon(image.getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH)));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel_3.add(rateEstLbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		body.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(700, 10));

		RoundPanel roundPanel1 = new RoundPanel();
		roundPanel1.setPreferredSize(new Dimension(1240, 500));
		TableDark table = new TableDark();
		table.setSize(new Dimension(1240, 600));
		table.setPreferredSize(new Dimension(1240, 600));
		table.setAutoCreateRowSorter(true);
		table.setRowSelectionAllowed(false);
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "#", "Rates", "Date" }) {
			boolean[] canEdit = new boolean[] { false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setViewportBorder(null);
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setPreferredSize(new Dimension(1225, 480));
		JPanel pane = new JPanel();
		pane.setLayout(new MigLayout("", "[225px]", "[1px]"));
		pane.add(table, "cell 0 0,grow");
		scrollpane.setViewportView(pane);
		table.fixTable(scrollpane);
		DefaultTableModel mode = (DefaultTableModel) table.getModel();
		int count = 1;
		for (Rate r : model.fetchRates(country.getCurrencyCode())) {
			mode.addRow(new Object[] { count, r.getRateVal(), r.getDate().split(" ")[0] });
			count += 1;
		}
		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setPreferredSize(new Dimension(1240, 370));
		roundPanel.setLayout(new MigLayout("", "[1000px]", "[360px]"));

		LineChart linechart = new LineChart();
		linechart.setPreferredSize(new Dimension(1000, 360));
		roundPanel.add(linechart, "cell 0 0,alignx left,aligny top");
		Rate[] rate = model.fetchRates(country.getCurrencyCode());

		linechart.addLegend(country.getName(), new Color(12, 84, 175), new Color(0, 108, 247));
		for (Rate r : rate) {
			linechart.addData(new ModelChart(r.getDate().split(" ")[0], new double[] { r.getRateVal() }));
		}

		linechart.start();

		panel_1.setPreferredSize(new Dimension(10, 1000));
		panel_1.setSize(new Dimension(0, 600));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new MigLayout("", "[100%]", "[500px][370px]"));
		panel_1.add(roundPanel1, "cell 0 0,grow");
		roundPanel1.setLayout(new MigLayout("", "[1225px]", "[480px]"));
		roundPanel1.add(scrollpane, "cell 0 0,grow");
		panel_1.add(roundPanel, "cell 0 1,alignx left,aligny top");

		JPanel heading = new JPanel();
		heading.setBackground(new Color(14, 14, 14));
		heading.setPreferredSize(new Dimension(10, 40));
		frame.getContentPane().add(heading, BorderLayout.NORTH);
		heading.setLayout(new BorderLayout(0, 0));

		CustomButton btnNewButton = new CustomButton();
		btnNewButton.setStyle(ButtonStyle.SECONDARY);
		btnNewButton.setRound(100);
		btnNewButton.setPreferredSize(new Dimension(40, 30));
		btnNewButton.setFocusable(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		try {
			URL rightUrl = new URL("https://img.icons8.com/material-sharp/24/000000/double-left.png");
			Image rightImage = ImageIO.read(rightUrl);
			btnNewButton.setIcon(new ImageIcon(rightImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		heading.add(btnNewButton, BorderLayout.WEST);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(53, 57, 53));
		panel.setBorder(null);
		panel.setPreferredSize(new Dimension(300, 10));
		heading.add(panel, BorderLayout.EAST);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(80, 3, 210, 35);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(24, 2, 46, 35);
		panel.add(lblNewLabel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	
	

}
