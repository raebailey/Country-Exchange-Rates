package pages;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import components.CountryCard;
import components.ScrollBarCustom;
import components.notification.Notification;
import components.search.SearchTextField;
import layouts.WrapLayout;
import models.Country;
import models.Page;
import sample.DatabaseModel;
import tasks.ImageLoader_Task;
import tasks.SearchTask;
import ui.Home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.ArrayList;

public class HomePage extends CustomPage implements InternetConnectAction {
//	public HomePage() {
//		setLayout(new BorderLayout(0, 0));
//	}

	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel panel_2;
	private ImageLoader_Task load;
	private SearchTask search;
	private JPanel panel_1;
	private JPanel panel_4;
	private SearchTextField textField;
	private ArrayList<CountryCard> ccArr;

	public HomePage() {
		initialize();
		load.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setName("Home_Page");
		DatabaseModel model = new DatabaseModel();
		Country[] countries = model.getCountries();
		ArrayList<JLabel> cardArr = new ArrayList<JLabel>();
		ArrayList<String> urlArr = new ArrayList<String>();
		ccArr = new ArrayList<CountryCard>();

		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollBarCustom scrollBarCustom = new ScrollBarCustom();
		scrollBarCustom.setUnitIncrement(5);
		scrollPane.setVerticalScrollBar(scrollBarCustom);
		panel.add(scrollPane);

		panel_2 = new JPanel();

		WrapLayout wraplayout = new WrapLayout(WrapLayout.LEFT, 20, 20);
		panel_2.setLayout(wraplayout);
		scrollPane.setViewportView(panel_2);
		for (Country country : countries) {
			CountryCard card = new CountryCard(country, Page.getInstance().getPagefactory().getWindow());
			cardArr.add(card.getImagelbl());
			urlArr.add(country.getImageUrl());
			ccArr.add(card);
			panel_2.add(card);
		}
		JLabel[] cArr = new JLabel[cardArr.size()];
		String[] uArr = new String[urlArr.size()];
		load = new ImageLoader_Task(cardArr.toArray(cArr), urlArr.toArray(uArr));
		search = new SearchTask(ccArr.toArray(new CountryCard[ccArr.size()]), panel_2,
				Page.getInstance().getPagefactory().getWindow());

		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 40));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(300, 10));
		panel_1.add(panel_4, BorderLayout.EAST);
		panel_4.setBackground(new Color(53, 57, 53));
		panel_4.setLayout(null);

		textField = new SearchTextField(search);
		textField.setHintText("Search Country " + "(" + countries.length + ")");
		textField.setSuggestionItems(countries);
		textField.setBounds(0, 0, 300, 40);
		textField.setColumns(10);
		panel_4.add(textField);
	}

	public ImageLoader_Task getLoad() {
		return load;
	}

	private ArrayList<CountryCard> getCcArr() {
		return ccArr;
	}

	@Override
	public void refresh(boolean ref) {
		if (!ref) {
			Home home = Page.getInstance().getPagefactory().getWindow();
			new Notification(home, Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, home.getInternetMsg(),
					null).showNotification();
		} else {
			ArrayList<JLabel> cardArr = new ArrayList<JLabel>();
			ArrayList<String> urlArr = new ArrayList<String>();
			for (CountryCard card : getCcArr()) {
				cardArr.add(card.getImagelbl());
				urlArr.add(card.getCountry().getImageUrl());
			}
			JLabel[] cArr = new JLabel[cardArr.size()];
			String[] uArr = new String[urlArr.size()];
			new ImageLoader_Task(cardArr.toArray(cArr), urlArr.toArray(uArr)).start();
		}
		System.out.println(this.getName());

	}

}
