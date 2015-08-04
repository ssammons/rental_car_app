package rentalcar.ui;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

import rentalcar.system.User;
import rentalcar.data.FormObject;
import rentalcar.system.Database;


public class RegisterModule {

	//------------------------------
	//Declare all components globally
	//------------------------------



	//User user;
	Database db = new Database();
	private JFrame frame;


	//Error banner
	private JLabel errMessage = new JLabel();

	//Register Button
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



	//Final values.
	public final int MENU_GAP = 5;
	public final int AUTHENTICATION_MENU_OFFSET = 0;
	public final int AUTHENTICATION_MENU_HEIGHT = 25;
	public final int NAVIGATION_MENU_OFFSET = AUTHENTICATION_MENU_OFFSET + AUTHENTICATION_MENU_HEIGHT + MENU_GAP;
	public final int NAVIGATION_MENU_HEIGHT = 25;
	public final int MODULE_OFFSET = NAVIGATION_MENU_OFFSET + NAVIGATION_MENU_HEIGHT + MENU_GAP;
	public final int MODULE_HEIGHT = 600;
	public final int WINDOW_HEIGHT = AUTHENTICATION_MENU_HEIGHT + NAVIGATION_MENU_HEIGHT + MODULE_HEIGHT + MENU_GAP;


	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//	public void run() {
	//			try {
	//				Register window = new Register();
	//			window.frame.setVisible(true);
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//			}
	//		}
	//	});
	//}

	/**
	 * Create the application.
	 */
	public RegisterModule() {	
		initialize();
		frame.setVisible(true);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		registerActions();
	}






	public void DisplayAuthenticationError(String message) {
		// display error next to logged out form fields
		errMessage = new JLabel(message);
		errMessage.setBounds(440, 80, 300, 25);
		frame.getContentPane().add(errMessage);
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
		message.setBounds(RIGHT_SIDE, 150, 300, 25);
		frame.getContentPane().add(message);

		fNameField = new JTextField();
		fNameField.setText("");
		fNameField.setBounds(RIGHT_SIDE, 180, 300, 25);
		frame.getContentPane().add(fNameField);


		//Last Name
		message = new JLabel("Please Enter Your Last Name:");
		message.setBounds(RIGHT_SIDE, 220, 300, 25);
		frame.getContentPane().add(message);

		lNameField = new JTextField();
		lNameField.setText("");
		lNameField.setBounds(RIGHT_SIDE, 250, 300, 25);
		frame.getContentPane().add(lNameField);


		//Phone Number
		message = new JLabel("Please Enter Your Phone Number:");
		message.setBounds(RIGHT_SIDE, 290, 300, 25);
		frame.getContentPane().add(message);

		phoneField = new JTextField();
		phoneField.setText("");
		phoneField.setBounds(RIGHT_SIDE, 320, 300, 25);
		frame.getContentPane().add(phoneField);


		//Date Of Birth
		message = new JLabel("Date Of Birth (1991-07-14):");
		message.setBounds(RIGHT_SIDE, 360, 300, 25);
		frame.getContentPane().add(message);

		birthField = new JTextField();
		birthField.setText("");
		birthField.setBounds(RIGHT_SIDE, 390, 300, 25);
		frame.getContentPane().add(birthField);


		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Submit's actions
				submitActions();
			}
		});
		btnSubmit.setBounds(335, 470, 110, 23);
		frame.getContentPane().add(btnSubmit);
	}





	public void submitActions(){
		if(userField.getText().equalsIgnoreCase("") || passField.getText().equalsIgnoreCase("") ||
				driveField.getText().equalsIgnoreCase("") || addressField.getText().equalsIgnoreCase("") ||
				emailField.getText().equalsIgnoreCase("") ||
				fNameField.getText().equalsIgnoreCase("") || lNameField.getText().equalsIgnoreCase("") ||
				phoneField.getText().equalsIgnoreCase("") || birthField.getText().equalsIgnoreCase(""))
		{
			frame.getContentPane().remove(errMessage);
			DisplayAuthenticationError("Please Fill All Fields.");
			frame.repaint();
		}else{
			//username - string
			//password - string
			//driver's license number - string
			//address - string
			//email - string

			//first name - string
			//last name - string
			//phone number - int - e.g. 9137750653
			//date of birth - date (string for now) - e.g. 1991-03-07 
			String userMess = userField.getText();
			String passMess = passField.getText();
			String driveMess = driveField.getText();
			String addressMess = addressField.getText();
			String emailMess = emailField.getText();

			String fNameMess = fNameField.getText();
			String lNameMess = lNameField.getText();
			String phoneMess = phoneField.getText();
			String birthMess = birthField.getText();

			int phoneMessInt;
			try{
				//Everything works
				phoneMessInt = Integer.parseInt(phoneMess);


				FormObject newUser = new FormObject();
			    newUser.Set("username",     userMess);
			    newUser.Set("password",     passMess);
			    newUser.Set("address",      addressMess);
			    newUser.Set("licenseNumber", driveMess);
			    newUser.Set("userType",         "customer");

			    newUser.Set("firstname",    fNameMess);
			    newUser.Set("lastname",     lNameMess);
			    newUser.Set("email",        emailMess);
			    newUser.Set("phoneNumber",  phoneMess); 
			    newUser.Set("birthdate",    birthMess);

			    //Really doesn't like these lines.
			    String affectedRowsUsers = db.CreateUser(newUser);
			    System.out.println("created "+ affectedRowsUsers + " user(s)");

				clear();
				frame.dispose();

    			
			}catch(NumberFormatException num){
				//Nothing works
				phoneMessInt = 0;
				frame.getContentPane().remove(errMessage);
				DisplayAuthenticationError("Use Only Numbers For Phone Number");
				frame.repaint();	
			}


		}
	}




//  public void AttemptLogin() {
	//}

	
	//public void AttemptLogout() {
	//	user.LogOut();
	//	if (!user.IsLoggedIn()) {
	//		SetLoggedInBanner();
	//		SetLoggedOutBanner();
	//	} else {
	//		DisplayAuthenticationError("Unable to log user out");
	//	}
	//}




	public void clear(){

		frame.getContentPane().removeAll();

		frame.repaint();
	}



}

