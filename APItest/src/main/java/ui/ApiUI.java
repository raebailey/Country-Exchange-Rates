package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.vdurmont.emoji.EmojiParser;

import components.Cards;
import components.CustomButton;
import components.RoundPanel;
import components.ScrollBarCustom;
import components.CustomTitleBar.TitleBar;
import models.DatabaseModel;
import models.apinotifications.ApiNotification;
import tasks.Country_Task;
import tasks.UpdateRate_Task;

public class ApiUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_3;
	private ArrayList<Cards> items = new ArrayList<>();
	private UpdateRate_Task rateTask;
	private Country_Task countryTask;
	private Timer rateTimer;
	private Timer countryTimer;
	private TitleBar titleBar;
	private javax.swing.Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ApiUI window = new ApiUI();
			window.setVisible(true);
			String str = "An :grinning:awesome :white_check_mark:string &#128516;with a few :wink:emojis!";
			String result = EmojiParser.parseToUnicode(str);
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Cards> getItems() {
		return items;
	}

	public void setItems(ArrayList<Cards> items) {
		this.items = items;
	}

	public JPanel getPanel_3() {
		return panel_3;
	}

	public void setPanel_3(JPanel panel_3) {
		this.panel_3 = panel_3;
	}

	/**
	 * Create the application.
	 */
	public ApiUI() {
		getContentPane().setBounds(new Rectangle(0, 0, 339, 524));
		getContentPane().setSize(new Dimension(339, 524));
		getContentPane().setPreferredSize(new Dimension(339, 524));
		getContentPane().setMinimumSize(new Dimension(339, 524));
		getContentPane().setMaximumSize(new Dimension(339, 524));
		initialize();
		titleBar.init(this);
		timer = new javax.swing.Timer(60000,this);
		timer.start();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		FlatDarkLaf.setup();
		setAlwaysOnTop(true);
		setResizable(false);
		setUndecorated(true);
		Image image;
		try {
			image = ImageIO.read(getClass().getResource("/images/dataview icon.png"));
			setIconImage(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setBackground(new Color(0, 0, 0, 0));
		setLocationByPlatform(true);
		setTitle("Data View");
		setBounds(100, 100, 339, 525);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 339, 524);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		RoundPanel panel_1 = new RoundPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		FlowLayout fl_panel_1 = new FlowLayout(FlowLayout.RIGHT, 5, 5);
		fl_panel_1.setAlignOnBaseline(true);
		panel_1.setLayout(fl_panel_1);

		titleBar = new TitleBar();
		titleBar.setOpaque(false);
		titleBar.setPreferredSize(new Dimension(200, 29));
		panel_1.add(titleBar);

		final JButton btnNewButton = new JButton("Run");

		try {
			Image img;
			img = ImageIO.read(getClass().getResource("/images/run.png"));
			btnNewButton.setIcon(new ImageIcon(img.getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		CustomButton btnNewButton_1 = new CustomButton();
		btnNewButton_1.setPreferredSize(new Dimension(51, 23));
		btnNewButton_1.setMaximumSize(new Dimension(51, 23));
		btnNewButton_1.setMinimumSize(new Dimension(51, 23));
		btnNewButton_1.setText("Stop");
		btnNewButton_1.setStyle(CustomButton.ButtonStyle.DESTRUCTIVE);
		try {
			Image img;
			img = ImageIO.read(getClass().getResource("/images/stop.png"));
			btnNewButton_1.setIcon(new ImageIcon(img.getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setRound(8);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rateTask.cancel();
				countryTask.cancel();
				btnNewButton.setEnabled(true);
				btnNewButton_1.setEnabled(false);
			}

		});
		btnNewButton_1.setEnabled(false);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				run();
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(true);
			}

		});
		panel_1.add(btnNewButton_1);

		btnNewButton.setFocusable(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(null);
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(15, 15, 15, 80)));
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setBounds(0, 0, 339, 487);
		ScrollBarCustom scrollBarCustom = new ScrollBarCustom();
		scrollBarCustom.setOpaque(false);
		scrollBarCustom.setUnitIncrement(5);
		scrollPane.setVerticalScrollBar(scrollBarCustom);
		panel_2.add(scrollPane);

		panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_3.setSize(new Dimension(339, 487));
		panel_3.setBorder(null);
		panel_3.setMaximumSize(new Dimension(339, 487));
		panel_3.addContainerListener(new ContainerListener() {

			@Override
			public void componentAdded(ContainerEvent e) {
				panel_3.repaint();
				panel_3.revalidate();
			}

			@Override
			public void componentRemoved(ContainerEvent e) {
				panel_3.repaint();
				panel_3.revalidate();

			}

		});
		scrollPane.setViewportView(panel_3);

	}

	/**
	 * Begins timer scheduler to run update rate task and country task
	 */
	private void run() {
		rateTimer = new Timer();
		rateTask = new UpdateRate_Task();
		rateTask.setFrame(this);

		countryTimer = new Timer();
		countryTask = new Country_Task();
		countryTask.setFrame(this);

		Date nextTime = new DatabaseModel().getRunTime();
		try {
			new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("16/08/2022 21:05:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date now = new Date();
		long difference = nextTime.getTime() - now.getTime();
		long hours = (difference / (1000 * 60 * 60)) % 24;
		long minutes = (difference / (1000 * 60)) % 60;
		long seconds = (difference / 1000) % 60;
		// 24 hr : 86400000
		// 30 minutes: 1800000
		rateTimer.schedule(rateTask, now, 300000);
		System.out.println("Hours until execution time:" + difference + "\n" + hours + "  hours " + minutes
				+ " minutes " + seconds + " seconds ");

		countryTimer.schedule(countryTask, new Date());
	}

	/**
	 * Creates notification
	 * 
	 * @param notification  The notification object created when country event is fired.
	 */
	public void addMessage(ApiNotification notification) {
		//ApiNotification notification = new ApiNotification(message, dateTime, type, image);
		createCard(notification);
	}

	/**
	 * Creates card for new notification type and adds existing types to card that
	 * matches.
	 * 
	 * @param ApiNotification notification
	 */
	private void createCard(ApiNotification notification) {
		Cards card = new Cards(notification);

		if (items.size() > 0) {
			for (Cards c : items) {
				if (c.getNotif().getType().equals(notification.getType())) {
					c.addItem(notification);
					return;
				}
			}
		}

		items.add(card);
		card.setUi(this);
		panel_3.add(card);

	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		if(items.size()>0) {
			for(Cards card:items) {
				card.updateTime();
				
			}
		}
		
	}
}
