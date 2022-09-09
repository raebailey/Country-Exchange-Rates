package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import components.CustomButton.ButtonStyle;
import interfaces.CustomAnimation;
import models.ApiNotification;
import ui.ApiUI;

import java.awt.FlowLayout;

public class Cards extends RoundPanel {
	private ApiUI ui;
	private ArrayList<ApiNotification> notifs;
	private JLabel datelabl;
	private JLabel messagelbl;
	private JLabel titlelbl;
	private CustomLabel counterlbl;
	private JPanel panel_2;
	private CustomAnimation animation;
	private JPanel panel_4;

	/**
	 * Create the panel.
	 */
	public Cards(ApiNotification notif) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setMaximumSize(new Dimension(328, 100));
		notifs = new ArrayList<>();
		notifs.add(notif);
		init();
//		animation = new CustomAnimation(this);
		
		
		
	}

	public JLabel getDatelabl() {
		return datelabl;
	}

	public void setDatelabl(JLabel datelabl) {
		this.datelabl = datelabl;
	}

	public JLabel getMessagelbl() {
		return messagelbl;
	}

	public void setMessagelbl(JLabel messagelbl) {
		this.messagelbl = messagelbl;
	}

	public JLabel getTitlelbl() {
		return titlelbl;
	}

	public void setTitlelbl(JLabel titlelbl) {
		this.titlelbl = titlelbl;
	}

	public CustomLabel getCounterlbl() {
		return counterlbl;
	}

	public void setCounterlbl(CustomLabel counterlbl) {
		this.counterlbl = counterlbl;
	}

	public ApiNotification getNotif() {
		return notifs.get(notifs.size() - 1);
	}

	public ArrayList<ApiNotification> getNotifs() {
		return notifs;
	}

	public void setNotifs(ArrayList<ApiNotification> notifs) {
		this.notifs = notifs;
	}

	public CustomAnimation getAnimation() {
		return animation;
	}

	public void setAnimation(CustomAnimation animation) {
		this.animation = animation;
	}

	private Cards self() {
		return this;
	}

	public ApiUI getUi() {
		return ui;
	}

	public void setUi(ApiUI ui) {
		this.ui = ui;
	}

	/**
	 * Initializes components.
	 */
	private void init() {
		ApiNotification notif = getNotif();
		setPreferredSize(new Dimension(328, 100));
		setLayout(new BorderLayout(0, 0));
		setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 150, 255)));
		setBackground(new Color(15, 15, 15));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.CENTER);

		messagelbl = new JLabel(notif.getMessage());
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addContainerGap().addComponent(messagelbl).addContainerGap(231, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addContainerGap().addComponent(messagelbl).addContainerGap(45, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 25));
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.NORTH);

		JPanel mainHeaderlbl = new JPanel();
		mainHeaderlbl.setLocation(0, 0);
		mainHeaderlbl.setSize(194, 25);
		mainHeaderlbl.setOpaque(false);
		FlowLayout fl_mainHeaderlbl = new FlowLayout(FlowLayout.LEFT, 10, 5);
		fl_mainHeaderlbl.setAlignOnBaseline(true);
		mainHeaderlbl.setLayout(fl_mainHeaderlbl);
		panel_1.add(mainHeaderlbl);

		titlelbl = new JLabel(notif.getType().getTitle());
		titlelbl.setForeground(new Color(255, 255, 255));
		titlelbl.setFont(new Font("SansSerif", Font.BOLD, 14));
		Image image = notif.getType().getPath();
		if (image != null) {
			titlelbl.setIcon(new ImageIcon(image));
		}
//		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		panel_1.setLayout(null);
		mainHeaderlbl.add(titlelbl);

		datelabl = new JLabel(notif.getLastexec());
		datelabl.setFont(new Font("SansSerif", Font.PLAIN, 11));
		datelabl.setForeground(new Color(255, 255, 255));
		mainHeaderlbl.add(datelabl);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBounds(308, 6, 12, 12);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		CustomButton closeButton = new CustomButton();
		closeButton.setIcon(new ImageIcon(Cards.class.getResource("/images/close_1.png")));
		closeButton.setBounds(310, 0, 20, 20);

		closeButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		closeButton.setStyle(ButtonStyle.CLOSE);
		closeButton.setFocusable(false);
		closeButton.setRound(20);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ui.getPanel_3().remove(self());
				ui.getItems().remove(self());
			}

		});
		panel_3.add(closeButton);

		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setPreferredSize(new Dimension(35, 10));
		add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(null);

		counterlbl = new CustomLabel();
		counterlbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
		counterlbl.setHorizontalTextPosition(SwingConstants.CENTER);
		counterlbl.setHorizontalAlignment(SwingConstants.CENTER);
		counterlbl.setBounds(5, 25, 28, 28);
		counterlbl.setPreferredSize(new Dimension(32, 32));
		counterlbl.setRound(100);
		counterlbl.setText(String.valueOf(notifs.size()));
		counterlbl.setVisible(false);

		if (notifs.size() > 1) {
			counterlbl.setVisible(true);
		}
		panel_2.add(counterlbl);
		
		panel_4 = new JPanel();
		panel_4.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignOnBaseline(true);
		add(panel_4, BorderLayout.SOUTH);
		RoundPanel subbar = new RoundPanel();
		subbar.setBackground(new Color(245, 245, 245,30));
		subbar.setPreferredSize(new Dimension(100, 2));
		subbar.setSize(new Dimension(200, 0));
		// TODO modularize code for animation
		Animator ani = new Animator(3000);
		ani.setResolution(1);
		ani.addTarget(new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				
				subbar.repaint();
			}
		});
		subbar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				if (ani.isRunning()) {
					ani.stop();
				}
				subbar.setBackground(new Color(245, 245, 245));
				ani.start();
			}

			@Override
			public void mouseExited(MouseEvent me) {
				if (ani.isRunning()) {
					ani.stop();
				}
				subbar.setBackground(new Color(245, 245, 245,30));
				ani.start();
			}

			@Override
			public void mousePressed(MouseEvent me) {
//				animationPress.start(currentStyle.background, getStyle().backgroundPress);
			}

			@Override
			public void mouseReleased(MouseEvent me) {
//				animationPress.start(currentStyle.background, getStyle().background);
			}
		});
		panel_4.add(subbar);

	}

	/**
	 * Refreshes current instance of the card element to show latest changes.
	 */
	public void refresh() {
		ApiNotification notif = getNotif();
		Image image = notif.getType().getPath();
		if (image != null) {
			titlelbl.setIcon(new ImageIcon(image));
		}
		getTitlelbl().setText(notif.getType().getTitle());
		getDatelabl().setText(notif.getLastexec());
		getMessagelbl().setText(notif.getMessage());
		if (notifs.size() > 1) {
			getCounterlbl().setVisible(true);
			if (notifs.size() > 99) {
				getCounterlbl().setText("99+");
			} else {
				getCounterlbl().setText(String.valueOf(notifs.size()));
			}
		}

	}
}
