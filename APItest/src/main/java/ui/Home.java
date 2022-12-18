package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.formdev.flatlaf.FlatDarkLaf;

import components.CountryCard;
import components.ScrollBarCustom;
import components.TableDark;
import components.search.EventCallBack;
import components.search.EventTextField;
import components.search.SearchTextField;
import layouts.WrapLayout;
import models.Country;
import sample.DatabaseModel;
import tasks.Country_Task;
import tasks.ImageLoader_Task;
import tasks.SearchTask;
import tasks.UpdateRate_Task;

import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Cursor;

public class Home extends CustomWindow{

	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel panel_2;
	private ImageLoader_Task load;
	private SearchTask search;
	private JPanel panel_1;
	private JPanel panel_4;
	private SearchTextField textField;

	
	public Home() {
		initialize();
		load.start();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DatabaseModel model = new DatabaseModel();
		Country[] countries = model.getCountries();
		ArrayList<JLabel> cardArr = new ArrayList<JLabel>();
		ArrayList<String> urlArr = new ArrayList<String>();
		ArrayList<CountryCard> ccArr = new ArrayList<CountryCard>();
		
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollBarCustom scrollBarCustom = new ScrollBarCustom();
		scrollBarCustom.setUnitIncrement(5);
		scrollPane.setVerticalScrollBar(scrollBarCustom);
		panel.add(scrollPane);

		panel_2 = new JPanel();
		
		WrapLayout wraplayout = new WrapLayout(WrapLayout.LEFT,20,20);
		panel_2.setLayout(wraplayout);
		scrollPane.setViewportView(panel_2);
		for (Country country : countries) {
			CountryCard card = new CountryCard(country,this);
			cardArr.add(card.getImagelbl());
			urlArr.add(country.getImageUrl());
			ccArr.add(card);
			panel_2.add(card);
		}
		JLabel[] cArr = new JLabel[cardArr.size()];
		String[] uArr = new String[urlArr.size()];
		load = new ImageLoader_Task(cardArr.toArray(cArr), urlArr.toArray(uArr));
		search = new SearchTask(ccArr.toArray(new CountryCard[ccArr.size()]),panel_2,this);
		
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
		textField.setHintText("Search Country "+"("+countries.length+")");
		textField.setSuggestionItems(countries);
		textField.setBounds(0, 0, 300, 40);
		textField.setColumns(10);
		panel_4.add(textField);
	}


	public ImageLoader_Task getLoad() {
		return load;
	}
	
}
