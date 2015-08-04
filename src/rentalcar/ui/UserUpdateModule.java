package rentalcar.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserUpdateModule extends JFrame implements ActionListener{
	private JFrame frame;
	private JLabel message;
	private JTextField userField;
	private JTextField passField;
	private JTextField driveField;
	private JTextField addressField;
	private JTextField emailField;
	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField phoneField;
	private JTextField birthField;

	private JButton btnSubmit;
	public final int MENU_GAP = 5;
	public final int AUTHENTICATION_MENU_OFFSET = 0;
	public final int AUTHENTICATION_MENU_HEIGHT = 25;
	public final int NAVIGATION_MENU_OFFSET = AUTHENTICATION_MENU_OFFSET + AUTHENTICATION_MENU_HEIGHT + MENU_GAP;
	public final int NAVIGATION_MENU_HEIGHT = 25;
	public final int MODULE_OFFSET = NAVIGATION_MENU_OFFSET + NAVIGATION_MENU_HEIGHT + MENU_GAP;
	public final int MODULE_HEIGHT = 600;
	public final int WINDOW_HEIGHT = AUTHENTICATION_MENU_HEIGHT + NAVIGATION_MENU_HEIGHT + MODULE_HEIGHT + MENU_GAP;
	public UserUpdateModule() {	
		initialize();
		frame.setVisible(true);
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//frame.setDefaultCloseOperation(operations());
		frame.getContentPane().setLayout(null);

		registerActions();
	}
	public void registerActions(){

		final int LEFT_SIDE = 40;
		final int RIGHT_SIDE = 440;

		//------------------------
		//Left side of screen
		//------------------------


		//Username
		message = new JLabel("Please Enter Your Preferred Username:");
		message.setBounds(LEFT_SIDE, 80, 300, 25);
		frame.getContentPane().add(message);

		userField = new JTextField();
		userField.setText("");
		userField.setBounds(LEFT_SIDE, 110, 300, 25);
		frame.getContentPane().add(userField);


		//Password
		message = new JLabel("Please Enter Your Preferred Password:");
		message.setBounds(LEFT_SIDE, 150, 300, 25);
		frame.getContentPane().add(message);

		passField = new JTextField();
		passField.setText("");
		passField.setBounds(LEFT_SIDE, 180, 300, 25);
		frame.getContentPane().add(passField);


		//Driver's license
		message = new JLabel("Please Enter Your Driver's License:");
		message.setBounds(LEFT_SIDE, 220, 300, 25);
		frame.getContentPane().add(message);

		driveField = new JTextField();
		driveField.setText("");
		driveField.setBounds(LEFT_SIDE, 250, 300, 25);
		frame.getContentPane().add(driveField);


		//Address
		message = new JLabel("Please Enter Your Address:");
		message.setBounds(LEFT_SIDE, 290, 300, 25);
		frame.getContentPane().add(message);

		addressField = new JTextField();
		addressField.setText("");
		addressField.setBounds(LEFT_SIDE, 320, 300, 25);
		frame.getContentPane().add(addressField);


		//Email
		message = new JLabel("Please Enter Your Email Address:");
		message.setBounds(LEFT_SIDE, 360, 300, 25);
		frame.getContentPane().add(message);

		emailField = new JTextField();
		emailField.setText("");
		emailField.setBounds(LEFT_SIDE, 390, 300, 25);
		frame.getContentPane().add(emailField);


		//------------------------
		//Right side of screen
		//------------------------



		//First Name
		message = new JLabel("Please Enter Your First Name:");
		message.setBounds(RIGHT_SIDE, 80, 300, 25);
		frame.getContentPane().add(message);

		fNameField = new JTextField();
		fNameField.setText("");
		fNameField.setBounds(RIGHT_SIDE, 110, 300, 25);
		frame.getContentPane().add(fNameField);


		//Last Name
		message = new JLabel("Please Enter Your Last Name:");
		message.setBounds(RIGHT_SIDE, 150, 300, 25);
		frame.getContentPane().add(message);

		lNameField = new JTextField();
		lNameField.setText("");
		lNameField.setBounds(RIGHT_SIDE, 180, 300, 25);
		frame.getContentPane().add(lNameField);


		//Phone Number
		message = new JLabel("Please Enter Your Phone Number:");
		message.setBounds(RIGHT_SIDE, 220, 300, 25);
		frame.getContentPane().add(message);

		phoneField = new JTextField();
		phoneField.setText("");
		phoneField.setBounds(RIGHT_SIDE, 250, 300, 25);
		frame.getContentPane().add(phoneField);


		//Date Of Birth
		message = new JLabel("Date Of Birth (1991-07-14):");
		message.setBounds(RIGHT_SIDE, 290, 300, 25);
		frame.getContentPane().add(message);

		birthField = new JTextField();
		birthField.setText("");
		birthField.setBounds(RIGHT_SIDE, 320, 300, 25);
		frame.getContentPane().add(birthField);


		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Submit's actions
			}
		});
		btnSubmit.setBounds(335, 470, 110, 23);
		frame.getContentPane().add(btnSubmit);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
