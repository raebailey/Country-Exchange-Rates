package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import cache.CacheManager;
import components.CustomButton;
import components.LineChart;
import components.RoundPanel;
import components.TableDark;
import components.CustomButton.ButtonStyle;
import components.image.ImageHelper;
import models.Country;
import models.Page;
import models.Rate;
import net.miginfocom.swing.MigLayout;
import sample.DatabaseModel;
import ui.CustomWindow;

public class DetailPage extends JPanel {
	private JPanel body;
	private JPanel panel_8;
	private JLabel imageLbl;
	private JPanel panel_2;
	private JLabel nameLbl;
	private JLabel lblNewLabel_1;
	private JPanel panel_4;
	private JLabel lblNewLabel_5;
	private JPanel panel_5;
	private JLabel latLbl;
	private JLabel lblNewLabel_7;
	private JLabel longLbl;
	private LineChart linechart;
	private JLabel currCodeLbl;
	private JLabel rateEstLbl;
	private TableDark table;
	private DatabaseModel model = new DatabaseModel();

	/**
	 * Create the application.
	 */
	public DetailPage() {
		setSize(new Dimension(1280, 720));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(null);

		body = new JPanel();
		body.setBounds(0, 0, 1280, 720);
		add(body);
		body.setLayout(new BorderLayout(0, 0));

		panel_8 = new JPanel();
		body.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 10));

		imageLbl = new JLabel("");

		imageLbl.setPreferredSize(new Dimension(320, 180));
		imageLbl.setSize(new Dimension(320, 0));
		panel_8.add(imageLbl);
		imageLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		imageLbl.setHorizontalAlignment(SwingConstants.CENTER);

		panel_2 = new JPanel();
		panel_8.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[200px][46px,grow]", "[22px][grow][][grow]"));
		
		CustomButton back = new CustomButton();
		back.setForeground(Color.WHITE);
		back.setFont(new Font("SansSerif", Font.BOLD, 14));
		back.setRound(8);
		back.setText("Home");
		back.setSize(62, 25);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Page.getInstance().getPagefactory().back();
				
			}
		});
		panel_8.add(back);

		nameLbl = new JLabel();
		nameLbl.setFont(new Font("SansSerif", Font.BOLD, 24));
		nameLbl.setPreferredSize(new Dimension(200, 20));
		panel_2.add(nameLbl, "cell 0 0,alignx left,aligny top");

		lblNewLabel_1 = new JLabel("Geo Coordinates");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_1, "cell 0 1,alignx left,aligny bottom");

		panel_4 = new JPanel();
		panel_2.add(panel_4, "cell 1 1,alignx left,aligny bottom");
		FlowLayout fl_panel_4 = new FlowLayout(FlowLayout.LEFT, 5, 0);
		fl_panel_4.setAlignOnBaseline(true);
		panel_4.setLayout(fl_panel_4);

		panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		lblNewLabel_5 = new JLabel("Latitude:");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_5);

		latLbl = new JLabel();
		latLbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_5.add(latLbl);

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		FlowLayout fl_panel_6 = new FlowLayout(FlowLayout.LEFT, 5, 0);
		fl_panel_6.setAlignOnBaseline(true);
		panel_6.setLayout(fl_panel_6);

		lblNewLabel_7 = new JLabel("Longitude:");
		lblNewLabel_7.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_6.add(lblNewLabel_7);

		longLbl = new JLabel();
		longLbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_6.add(longLbl);

		JLabel lblNewLabel_2 = new JLabel("Currency Code");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_2, "cell 0 2");

		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, "cell 1 2,grow");
		panel_7.setLayout(new MigLayout("", "[46px,grow]", "[]"));

		currCodeLbl = new JLabel();
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

		rateEstLbl = new JLabel("");

		panel_3.add(rateEstLbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		body.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(700, 10));

		RoundPanel roundPanel1 = new RoundPanel();
		roundPanel1.setPreferredSize(new Dimension(1240, 500));
		table = new TableDark();
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

		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setPreferredSize(new Dimension(1240, 370));
		roundPanel.setLayout(new MigLayout("", "[1000px]", "[360px]"));

		linechart = new LineChart();
		linechart.setPreferredSize(new Dimension(1000, 360));
		roundPanel.add(linechart, "cell 0 0,alignx left,aligny top");

		panel_1.setPreferredSize(new Dimension(10, 1000));
		panel_1.setSize(new Dimension(0, 600));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new MigLayout("", "[100%]", "[500px][370px]"));
		panel_1.add(roundPanel1, "cell 0 0,grow");
		roundPanel1.setLayout(new MigLayout("", "[1225px]", "[480px]"));
		roundPanel1.add(scrollpane, "cell 0 0,grow");
		panel_1.add(roundPanel, "cell 0 1,alignx left,aligny top");

		try {
			URL rightUrl = new URL("https://img.icons8.com/material-sharp/24/000000/double-left.png");
			Image rightImage = ImageIO.read(rightUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DetailPage self() {
		return this;
	}


	public void setCountry(Country country) {

		String imageUrl = country.getImageUrl();
		String default_url = "https://www.freeiconspng.com/uploads/no-image-icon-6.png";
		URL url;
		Image image;
		try {
			Image newImage = (Image) CacheManager.getCacheItem(default_url);
			if (newImage == null) {
				url = new URL(default_url);
				image = ImageIO.read(url);
			} else {
				image = newImage;
			}
			if (imageUrl != null) {
				Image newImage1 = (Image) CacheManager.getCacheItem(imageUrl);
				if (newImage1 == null) {
					url = new URL(imageUrl);
					image = ImageIO.read(url);
				} else {
					image = newImage1;
				}

			}
			imageLbl.setIcon(new ImageIcon(ImageHelper.makeRoundedCorner(image, 8, 320, 160)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			imageLbl.setIcon(new ImageIcon(getClass().getResource("/images/fail.png")));
		}
		nameLbl.setText(country.getName());
		latLbl.setText(String.valueOf(country.getLatitude()));
		longLbl.setText(String.valueOf(country.getLongitude()));
		currCodeLbl.setText(country.getCurrencyCode().toUpperCase());

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

		DefaultTableModel mode = (DefaultTableModel) table.getModel();
		int count = 1;
		Rate[] rates = model.fetchRates(country.getCurrencyCode());
		for (Rate r : rates) {
			mode.addRow(new Object[] { count, r.getRateVal(), r.getDate().split(" ")[0] });
			count += 1;
		}
//		Rate[] rate = model.fetchRates(country.getCurrencyCode());

//		linechart.addLegend(country.getName(), new Color(12, 84, 175), new Color(0, 108, 247));
//		for (Rate r : rates) {
//			linechart.addData(new ModelChart(r.getDate().split(" ")[0], new double[] { r.getRateVal() }));
//		}

//		linechart.start();
		self().setVisible(true);
	}
}
