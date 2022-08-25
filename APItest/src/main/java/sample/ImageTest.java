package sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import components.CustomButton;
import models.Country;

public class ImageTest extends JFrame {

	private JPanel contentPane;
	private CustomButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageTest frame = new ImageTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImageTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(14, 14, 14));
		contentPane.setMinimumSize(new Dimension(320, 160));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setBorder(new LineBorder(new Color(14, 14, 14), 0, true));
		lblNewLabel.setMinimumSize(new Dimension(320, 160));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		try {
			DatabaseModel model = new DatabaseModel();
			Country country = model.fetchCountry("JMD");
			String imageUrl = country.getImageUrl();
			String default_url = "https://www.freeiconspng.com/uploads/no-image-icon-6.png";
			URL url = new URL(default_url);
			Image image = ImageIO.read(url);

			if (imageUrl != null) {
				url = new URL(imageUrl);
				image = ImageIO.read(url);
			}

			lblNewLabel.setIcon(new ImageIcon(image.getScaledInstance(320, 160, java.awt.Image.SCALE_SMOOTH)));
			String name = country.getName();
			if (name.trim().isEmpty()) {
				name = "<No country name.>";
			}
			JLabel lblNewLabel_1 = new JLabel(name);
			lblNewLabel_1.setForeground(SystemColor.text);
			lblNewLabel_1.setBorder(null);
			lblNewLabel_1.setPreferredSize(new Dimension(55, 25));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
			contentPane.add(lblNewLabel_1, BorderLayout.SOUTH);

			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setPreferredSize(new Dimension(10, 30));
			contentPane.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));

			JLabel lblNewLabel_2 = new JLabel("New label");
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
			panel.add(lblNewLabel_2);

			URL leftUrl = new URL("https://img.icons8.com/material-sharp/24/000000/double-left.png");
			Image leftImage = ImageIO.read(leftUrl);
			btnNewButton_1 = new CustomButton();
			btnNewButton_1.setFocusable(false);
			btnNewButton_1.setStyle(CustomButton.ButtonStyle.PRIMARY);
			btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnNewButton_1.setToolTipText("previous");
			panel.add(btnNewButton_1, BorderLayout.WEST);
			btnNewButton_1.setIcon(new ImageIcon(leftImage));

			URL rightUrl = new URL("https://img.icons8.com/material-sharp/24/000000/double-right.png");
			Image rightImage = ImageIO.read(rightUrl);
			CustomButton btnNewButton = new CustomButton();
			btnNewButton.setFocusable(false);
			btnNewButton.setStyle(CustomButton.ButtonStyle.PRIMARY);
			btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnNewButton.setToolTipText("next");
			panel.add(btnNewButton, BorderLayout.EAST);
			btnNewButton.setIcon(new ImageIcon(rightImage));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
