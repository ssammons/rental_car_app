package rentalcar.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import rentalcar.system.User;

public class CustomerModule extends JFrame implements ActionListener{
	private User user;
	private JPanel hub = new JPanel();
	private JButton browse = new JButton("Browse Cars");
	private JButton reserve = new JButton("Make a Reservation");
	private JButton history = new JButton("View my Rental History");
	private JButton logout = new JButton("Logout");
	
	public CustomerModule(User _user){
		this.user = _user;
		JLabel header = new JLabel("Welcome " + this.user.FirstName());	
		setTitle("Allen Eaton Customer");
		setBounds(800, 400, 250, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		logout.addActionListener(this);
		browse.addActionListener(this);
		reserve.addActionListener(this);
		history.addActionListener(this);
		hub.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = 0;
		hub.add(header, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
		hub.add(browse, gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
		hub.add(reserve, gbc);
        gbc.gridx = 3;
        gbc.gridy = 5;
		hub.add(history, gbc);
        gbc.gridx = 3;
        gbc.gridy = 7;
		hub.add(logout, gbc);
		add(hub);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if(s == browse){
			BrowseModule br = new BrowseModule();
			br.setVisible(true);
		}
		else if(s == reserve){
			MakeReservationModule res = new MakeReservationModule(user);
			res.setVisible(true);
		}
		else if(s == history){
						
			History h = new History(user);
			h.setVisible(true);
		}
		else if(s == logout){
			new LoginModule().setVisible(true);
			dispose();
		}
		
	}

	/*public static void main(String[] a) {
		new CustomerModule().setVisible(true);
	}*/
}

