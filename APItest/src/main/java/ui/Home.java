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
	private JTextField textField;
	private JLabel lblNewLabel_1;

	
	public Home() {
		initialize();
		load.start();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
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
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(new Color(0, 150, 255));
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("pressed");
				String value = textField.getText().trim();
				if(!value.isEmpty()) {
					search.setSearchTerm(value);
					search.run();
				}
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(209, 0, 91, 38);
		panel_4.add(lblNewLabel_1);

		DatabaseModel model = new DatabaseModel();
		Country[] countries = model.getCountries();
		ArrayList<JLabel> cardArr = new ArrayList<JLabel>();
		ArrayList<String> urlArr = new ArrayList<String>();
		ArrayList<CountryCard> ccArr = new ArrayList<CountryCard>();
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
		search = new SearchTask(ccArr.toArray(new CountryCard[ccArr.size()]),panel_2);
	}


	public ImageLoader_Task getLoad() {
		return load;
	}
	
}
