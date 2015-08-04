package rentalcar.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import rentalcar.ui.CustomerModule;
import rentalcar.ui.servicemngrWindow;
import rentalcar.system.User;

public class LoginModule extends JFrame {
		private User user = new User();
		private JPanel contentPane = new JPanel();
		private JLabel usernameLabel = new JLabel("Username");
		private JLabel passwordLabel = new JLabel("Password");
		private JLabel status = new JLabel();
		private JButton loginButton = new JButton("login");
		private JTextField username = new JTextField(20);
		private JTextField password = new JTextField(20);
    private JButton browseButton = new JButton("Browse Cars");
    private JButton registerButton = new JButton("Sign Up");

    
    void redirectByUserType(String type) {
      if (type.equals("customer")) {
        CustomerModule customerMod = new CustomerModule(user);
        customerMod.setVisible(true);
      } else if (type.equals("service_manager")) {
        servicemngrWindow servMod = new servicemngrWindow();
        servMod.frmServiceManager.setVisible(true);
      } else if (type.equals("sales_manager")) {
        SalesModule salesMod = new SalesModule(user);
        salesMod.setVisible(true);
      } else if (type.equals("admin")) {
        AdminModule adminMod = new AdminModule(user);
        adminMod.setVisible(true);
      }
      this.dispose(); // close this window
    }

    public LoginModule() {
    	loginButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent evt) {

    			user.LogIn(username.getText(), password.getText());
    			if (user.IsLoggedIn()) {
    				redirectByUserType(user.Type());
            System.out.println(user.Type());
    			} else {
    				status.setText("Error!");
    			}
    		}
    	});

      browseButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          Object s = evt.getSource();
            if(s == browseButton){
            BrowseModule br = new BrowseModule();
                br.setVisible(true);
                }
        }
      });

      registerButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          Object s = evt.getSource();
            if(s == registerButton){
            RegisterModule br = new RegisterModule();
                //br.setVisible(true);
                }
        }
      });


    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setBounds(200, 200, 300, 301);
			contentPane.add(usernameLabel);
			contentPane.add(username);
			contentPane.add(passwordLabel);
			contentPane.add(password);
			contentPane.add(loginButton);
			contentPane.add(status);
      contentPane.add(browseButton);
      contentPane.add(registerButton);
			setContentPane(contentPane);
		}
}