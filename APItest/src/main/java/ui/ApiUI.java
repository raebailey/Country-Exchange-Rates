package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Window.Type;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.formdev.flatlaf.FlatDarkLaf;

import components.Cards;
import components.CustomButton;
import components.ScrollBarCustom;
import enums.MessageTypes;
import models.ApiNotification;
import sample.DatabaseModel;
import tasks.Country_Task;
import tasks.UpdateRate_Task;

public class ApiUI {

	private JFrame frmDataView;
	private JPanel panel_3;
	private ArrayList<Cards> items = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
		try {
			ApiUI window = new ApiUI();
			window.frmDataView.setVisible(true);
			window.run();

		} catch (Exception e) {
			e.printStackTrace();
		}
//			}
//		});
	}

	public ArrayList<Cards> getItems() {
		return items;
	}

	public void setItems(ArrayList<Cards> items) {
		this.items = items;
	}

	/**
	 * Create the application.
	 */
	public ApiUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		FlatDarkLaf.setup();
		frmDataView = new JFrame();
		frmDataView.setType(Type.UTILITY);
		frmDataView.setAlwaysOnTop(true);
		frmDataView.setResizable(false);
		frmDataView.setTitle("Data View");
		frmDataView.setBounds(100, 100, 355, 563);
		frmDataView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDataView.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 339, 524);
		frmDataView.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		FlowLayout fl_panel_1 = new FlowLayout(FlowLayout.RIGHT, 5, 5);
		fl_panel_1.setAlignOnBaseline(true);
		panel_1.setLayout(fl_panel_1);

		JButton btnNewButton = new JButton("Run");

		try {
			Image img;
			img = ImageIO.read(getClass().getResource("/images/run.png"));
			btnNewButton.setIcon(new ImageIcon(img.getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setRound(8);
		panel_1.add(btnNewButton_1);

		btnNewButton.setFocusable(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setBounds(0, 0, 339, 487);
		ScrollBarCustom scrollBarCustom = new ScrollBarCustom();
		scrollBarCustom.setUnitIncrement(5);
		scrollPane.setVerticalScrollBar(scrollBarCustom);
		panel_2.add(scrollPane);

		panel_3 = new JPanel();
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
				// TODO Auto-generated method stub
				
			}
			
		});
		scrollPane.setViewportView(panel_3);
	}

	/**
	 * Begins timer scheduler to run update rate task and country task
	 */
	private void run() {
		Timer timer = new Timer();
		TimerTask task;

		UpdateRate_Task rateTask = new UpdateRate_Task();
		rateTask.setFrame(this);
		task = rateTask;
		Date nextTime = new DatabaseModel().getRunTime();
		try {
			new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("16/08/2022 21:05:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Date now = new Date();
		long difference = nextTime.getTime() - now.getTime();
		long hours = (difference / (1000 * 60 * 60)) % 24;
		long minutes = (difference / (1000 * 60)) % 60;
		long seconds = (difference / 1000) % 60;
		// 24 hr : 86400000
	   // 30 minutes: 1800000
//		timer.schedule(task, now, 300000);
		System.out.println("Hours until execution time:" + difference + "\n" + hours + "  hours " + minutes
				+ " minutes " + seconds + " seconds ");

		Country_Task country_task = new Country_Task();
		country_task.setFrame(this);
		
		new Timer().schedule(country_task, new Date());
	}

	public void addMessage(String message, String dateTime,MessageTypes type) {
		ApiNotification notification = new ApiNotification(message, dateTime,type);
		createCard(notification);
	}

	private void createCard(ApiNotification notification) {
		Cards card = new Cards(notification);
		if (items.size() > 0) {
			for (Cards c : items) {
				if (c.getNotif().getType().equals(notification.getType())) {
					c.getNotifs().add(notification);
					c.refresh();
					return;
				}
			}
		} 
			
			items.add(card);
			panel_3.add(card);

		
	}
}
