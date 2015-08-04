package rentalcar.ui;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

public class ContractModule extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JList<String> list;


	/**
	 * Create the frame.
	 */
	public ContractModule(HashMap<String, String> hm) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Contract Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(59, 10, 677, 496);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		list = new JList<String>();
		list.setBounds(5, 17, 667, 474);
		panel.add(list);
		
		
		DefaultListModel<String> newModel = new DefaultListModel<String>();
		Iterator<String> it = hm.keySet().iterator();
		
		while(it.hasNext()) {
			String nextKey = it.next();
			newModel.addElement(nextKey + ":          " + hm.get(nextKey));
		}
		
		list.setModel(newModel);
		
	}
	
}
