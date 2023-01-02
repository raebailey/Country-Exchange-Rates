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
import components.ScrollBarCustom;
import components.TableDark;
import components.CustomButton.ButtonStyle;
import components.image.ImageHelper;
import components.notification.Notification;
import models.Country;
import models.DatabaseModel;
import models.Page;
import models.Rate;
import net.miginfocom.swing.MigLayout;
import tasks.ImageLoader_Task;
import ui.CustomWindow;
import ui.Home;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTable;

public class DetailPage extends CustomPage implements InternetConnectAction {
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
	private JLabel currCodeLbl;
	private JLabel rateEstLbl;
	private DatabaseModel model = new DatabaseModel();
	private JScrollPane scrollPane;
	private TableDark table;
	
	private Country country;

	/**
	 * Create the application.
	 */
	public DetailPage(Country country) {
		super();
		initialize();
		this.country = country;
		setCountry(country);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		this.setName("Detail_Page");
		setPreferredSize(new Dimension(1150, 720));

		body = new JPanel();
		body.setPreferredSize(new Dimension(1150, 720));
		body.setMinimumSize(new Dimension(1150, 720));
		add(body);
		body.setLayout(new BorderLayout(0, 0));

		panel_8 = new JPanel();
		panel_8.setBackground(new Color(14, 14, 14));
		panel_8.setMinimumSize(new Dimension(1280, 7200));
		panel_8.setSize(new Dimension(1280, 720));
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
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
		back.setPreferredSize(new Dimension(100, 30));
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

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollBarCustom scrollBarCustom = new ScrollBarCustom();
		scrollBarCustom.setUnitIncrement(5);
		scrollPane.setVerticalScrollBar(scrollBarCustom);
		body.add(scrollPane, BorderLayout.CENTER);

		table = new TableDark();
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
		scrollPane.setViewportView(table);
	}

	public DetailPage self() {
		return this;
	}

	private void setCountry(Country country) {

		String imageUrl = country.getImageUrl();
		imageLbl.setIcon(new ImageIcon(getClass().getResource("/images/loading3 new.gif")));
		JLabel[] lblArr = { imageLbl };
		String[] strArr = { imageUrl };
		Page.getExecutor().execute(new ImageLoader_Task(lblArr, strArr, 320, 160));

		nameLbl.setText(country.getName());
		latLbl.setText(String.valueOf(country.getLatitude()));
		longLbl.setText(String.valueOf(country.getLongitude()));
		currCodeLbl.setText(country.getCurrencyCode().toUpperCase());
//
//		try {
//			url = new URL("https://img.icons8.com/emoji/48/000000/red-triangle-pointed-down-emoji.png");
//			image = ImageIO.read(url);
//			rateEstLbl.setIcon(new ImageIcon(image.getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH)));
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
		DefaultTableModel mode = (DefaultTableModel) table.getModel();
		int count = 1;
		Rate[] rates = model.fetchRates(country.getCurrencyCode());
		if(mode.getRowCount()>0) {
			mode.setRowCount(0);
		}

		for (Rate r : rates) {
			String formatedRate = String.format("(%1$s)%2$s", country.getCurrency().getSymbol(), r.getRateVal());
			mode.addRow(new Object[] { count, formatedRate, r.getDate().split(" ")[0] });
			count += 1;
		}
//		Rate[] rate = model.fetchRates(country.getCurrencyCode());

//		linechart.addLegend(country.getName(), new Color(12, 84, 175), new Color(0, 108, 247));
//		for (Rate r : rates) {
//			linechart.addData(new ModelChart(r.getDate().split(" ")[0], new double[] { r.getRateVal() }));
//		}

//		linechart.start();
	}

	@Override
	public void refresh() {
		setCountry(country);
	}

	@Override
	public void sendNotification(String message) {
		Home home = Page.getInstance().getPagefactory().getWindow();
		new Notification(home, Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, home.getInternetMsg(),
				null).showNotification();
	}
}
