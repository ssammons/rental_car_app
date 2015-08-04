
package rentalcar.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import rentalcar.web.Request;
import rentalcar.system.Database;

import rentalcar.web.JSONRequest;
import rentalcar.data.FormObject;
import org.json.*;
import java.util.*;

public class servicemngrWindow extends JPanel {
	private boolean DEBUG = false;

	public JFrame frmServiceManager;
	private final JCheckBox checkBox = new JCheckBox("Changed Oil");
	private JTable table;
	private JScrollBar scrollBar;
	private JTable table_1;
	private JTextField txtEnterMileage;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					servicemngrWindow window = new servicemngrWindow();
					window.frmServiceManager.setVisible(true);
					//createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public servicemngrWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServiceManager = new JFrame();
		frmServiceManager.setTitle("Service Manager");
		frmServiceManager.setBounds(400, 150, 1250, 950);
		frmServiceManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServiceManager.getContentPane().setLayout(null);
		checkBox.setBounds(1056, 281, 122, 31);
		frmServiceManager.getContentPane().add(checkBox);
		
		final JCheckBox chckbxChangedAirFilter = new JCheckBox("Changed Air Filter");
		chckbxChangedAirFilter.setBounds(1056, 209, 152, 23);
		frmServiceManager.getContentPane().add(chckbxChangedAirFilter);
		
		final JCheckBox chckbxRotatedTires = new JCheckBox("Rotated Tires");
		chckbxRotatedTires.setBounds(1056, 249, 129, 23);
		frmServiceManager.getContentPane().add(chckbxRotatedTires);
		
		String[] columnNames = {"License#",
                //"make",
                //"model",
                //"year",
                "Mileage",
                "Location",
               // "Class",
                "DateLastOilCh.",
                "Mi.LastOilCh.",
                "DateLastAirFil.Ch.",
                "Mi.LastAirFil.Ch.",
                "DateLastTireRot",
                "Mi.LastTireRot"
                };
		
		/*Object[][] data = {
			    {"1231465", "John Smith",
			     "Minivan", "Toyota Sienna", "03-12-2015",  "03-15-2015", new Double(255.00), 20, 10},
			    {"4561465", "Will Turner",
			     "Std SUV", "Ford Explorer", "04-12-2015",  "05-15-2015", new Double(2225.00), 20, 10},
			    {"8678865", "Ned Stark",
			     "Std SUV", "Honda Pilot", "03-22-2015",  "03-25-2015", new Double(225.00), 20, 10},
			    {"9793465", "Jon Snow",
			     "Sm SUV", "Toyota RAV4", "01-12-2015",  "03-15-2015", new Double(4010.00), 20, 10},
			    {"3331465", "Khal Drogo",
			     "Standard", "Honda Accord", "02-12-2015",  "03-15-2015", new Double(1900.00), 20, 10},
			    {"5555465", "Tyrion Lannister",
			     "Compact", "Honda Civic", "03-01-2015",  "03-15-2015", new Double(700.00), 20, 10}
			    };*/

		//try{
		Database dbClient = new Database();
		List<HashMap<String, String>> vehicles = dbClient.GetAllVehicles();
		


		Object[][] data = new Object[vehicles.size()][vehicles.get(0).size()];
		for(int i=0; i < vehicles.size(); i++)
		{
			String licenseNum, licenseSt, license;
			licenseNum = vehicles.get(i).get("platenum");
			licenseSt = vehicles.get(i).get("platestate");
			license = licenseNum + " " + licenseSt;
			data[i][0] = license;
			data[i][1] = vehicles.get(i).get("mileage");
			data[i][2] = vehicles.get(i).get("location");
			data[i][3] = vehicles.get(i).get("oilchangedate");
			data[i][4] = vehicles.get(i).get("oilchangemile");
			data[i][5] = vehicles.get(i).get("filterdate");
			data[i][6] = vehicles.get(i).get("filtermile");
			data[i][7] = vehicles.get(i).get("tirerotationdate");
			data[i][8] = vehicles.get(i).get("tirerotationmile");
		}
		
		
		table_1 = new JTable(data, columnNames);
		table_1.setBounds(1, 20, 741, 553);
		table_1.setPreferredScrollableViewportSize(new Dimension(800, 600));
		table_1.setFillsViewportHeight(true);
		table_1.setAutoCreateRowSorter(true);
		//}catch(JSONException a){}
		//JScrollPane scrollPane = new JScrollPane(table_1);

        //Add the scroll pane to this panel.
        //add(scrollPane);
		frmServiceManager.getContentPane().add(table_1);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Enter Mileage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(1056, 40, 124, 41);
		frmServiceManager.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtEnterMileage = new JTextField();
		txtEnterMileage.setBounds(5, 17, 114, 19);
		panel.add(txtEnterMileage);
		txtEnterMileage.setText("Enter Mileage");
		txtEnterMileage.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxChangedAirFilter.setSelected(false);
				chckbxRotatedTires.setSelected(false);
				checkBox.setSelected(false);
				txtEnterMileage.setText("Enter Mileage");
				
			}
		});
		btnSubmit.setBounds(1056, 475, 117, 25);
		frmServiceManager.getContentPane().add(btnSubmit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Vehicle Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(1056, 131, 180, 60);
		frmServiceManager.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		String[] comboStr =
			{
				"Available",
				"In Service"
			};
		
		JComboBox comboBox = new JComboBox(comboStr);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		comboBox.setBounds(5, 17, 131, 23);
		panel_1.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(12, 50, 1000, 820);
		scrollPane.setColumnHeaderView(table_1.getTableHeader());
		frmServiceManager.getContentPane().add(scrollPane);
		
		
	
				
		
	}
	class MyTableModel extends AbstractTableModel {
	    private String[] columnNames = {"Reservation ID",
	                                    "Customer Name",
	                                    "Class",
	                                    "Vehicle",
	                                    "Start Date",
	                                    "End Date",
	                                    "Total Amount"};
	    private Object[][] data = {
	    {"1231465", "John Smith",
	     "Minivan", "Toyota Sienna", "03-12-2015",  "03-15-2015", new Double(255.00)},
	    {"4561465", "Will Turner",
	     "Std SUV", "Ford Explorer", "04-12-2015",  "05-15-2015", new Double(2225.00)},
	    {"8678865", "Ned Stark",
	     "Std SUV", "Honda Pilot", "03-22-2015",  "03-25-2015", new Double(225.00)},
	    {"9793465", "Jon Snow",
	     "Sm SUV", "Toyota RAV4", "01-12-2015",  "03-15-2015", new Double(4010.00)},
	    {"3331465", "Khal Drogo",
	     "Standard", "Honda Accord", "02-12-2015",  "03-15-2015", new Double(1900.00)},
	    {"5555465", "Tyrion Lannister",
	     "Compact", "Honda Civic", "03-01-2015",  "03-15-2015", new Double(700.00)}
	    };

	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    public int getRowCount() {
	        return data.length;
	    }

	  /*  public String getColumnName(int col) {
	        return columnNames[col];
	    }*/

	    public Object getValueAt(int row, int col) {
	        return data[row][col];
	    }

	    /*
	     * JTable uses this method to determine the default renderer/
	     * editor for each cell.  If we didn't implement this method,
	     * then the last column would contain text ("true"/"false"),
	     * rather than a check box.
	     */
	    public Class getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }

	    /*
	     * Don't need to implement this method unless your table's
	     * editable.
	     */
	    public boolean isCellEditable(int row, int col) {
	        //Note that the data/cell address is constant,
	        //no matter where the cell appears onscreen.
	        if (col < 2) {
	            return false;
	        } else {
	            return true;
	        }
	    }

	    /*
	     * Don't need to implement this method unless your table's
	     * data can change.
	     */
	    public void setValueAt(Object value, int row, int col) {
	        if (DEBUG) {
	            System.out.println("Setting value at " + row + "," + col
	                               + " to " + value
	                               + " (an instance of "
	                               + value.getClass() + ")");
	        }

	        data[row][col] = value;
	        //fireTableCellUpdated(row, col);

	        if (DEBUG) {
	            System.out.println("New value of data:");
	            printDebugData();
	        }
	    }

	    private void printDebugData() {
	        int numRows = getRowCount();
	        int numCols = getColumnCount();

	        for (int i=0; i < numRows; i++) {
	            System.out.print("    row " + i + ":");
	            for (int j=0; j < numCols; j++) {
	                System.out.print("  " + data[i][j]);
	            }
	            System.out.println();
	        }
	        System.out.println("--------------------------");
	    }
	}

	
	
	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	private static void createAndShowGUI() {
	    //Create and set up the window.

	    JFrame frame = new JFrame("History");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //frame.setSize(800,600);
	    frame.setBounds(100, 100, 800, 600);
	    
	    JPanel contentPane = new JPanel();
	    //Create and set up the content pane.
	    servicemngrWindow newContentPane = new servicemngrWindow();
	    
	    newContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    newContentPane.setOpaque(true); //content panes must be opaque
	    contentPane.add(newContentPane);
	    frame.setContentPane(contentPane);
	    //java.awt.Dimension.preferredSize(800,600);

	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	}
	}
