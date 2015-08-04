package rentalcar.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class BrowseModule extends JFrame implements ListSelectionListener {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblNewLabel;
	
	private JList<String> list;
	
	private String[] listStr = 
		{
			"Economy - $45/day or $300/week",
			"Compact - $50/day or $325/week",
			"Standard - $60/day or $400/week",
			"Premium - $65/day or $435/week",
			"Small SUV - $70/day or $475/week",
			"Standard SUV - $75/day or $500/week",
			"Minivan - $85/day or $575/week"
		};
	
	private ImageIcon[] pics = {new ImageIcon("pics/economy_-_ford_fiesta.jpg"), new ImageIcon("pics/compact_-_honda_civic.jpg"), 
			new ImageIcon("pics/standard_-_toyota_camry.jpg"), new ImageIcon("pics/premium_-_ford_mustang.jpg"), new ImageIcon("pics/small_suv_-_honda_cr-v.jpg"), 
			new ImageIcon("pics/standard_suv_-_toyota_highlander.jpg"),new ImageIcon("pics/minivan_-_honda_odyssey.jpg")};

	private JPanel contentPane;
	private JLabel label;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrowseModule frame = new BrowseModule();
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
	public BrowseModule() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Vehicle Selection", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(16, 10, 376, 291);
		contentPane.add(panel);
		panel.setLayout(null);
		
		list = new JList<String>(listStr);
		list.setBounds(6, 16, 364, 268);
		panel.add(list);
		list.setFont(new Font("Arial",Font.PLAIN,16));
		list.addListSelectionListener(this);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Vehicle Example", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(399, 10, 381, 291);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(6, 16, 369, 268);
		panel_1.add(lblNewLabel);
		lblNewLabel.setIcon(pics[0]);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Additional Equipment/Services", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(122, 316, 512, 198);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		label = new JLabel();
		label.setBounds(6, 16, 500, 175);
		panel_2.add(label);
		label.setText("<HTML>GPS Receiver - $15/day<BR>Child Seat - $10/day<BR>K-TAG Rental - $2/day (plus accumulated tolls)"
				+ "<BR>Roadside Assistance - $7/day<BR>Loss Damage Waiver Insurance - $25/day<BR>Personal Accident Insurance - $5/day</HTML>");
	}

	
	public void valueChanged(ListSelectionEvent e) {
		lblNewLabel.setIcon(pics[list.getSelectedIndex()]);
		
	}
}
