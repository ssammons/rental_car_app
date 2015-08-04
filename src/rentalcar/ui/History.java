package rentalcar.ui;


import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import rentalcar.util.SpringUtilities;

import rentalcar.system.User;
import rentalcar.system.Database;

/** 
 * History is just like SimpleHistory, except that it
 * uses a custom TableModel.
 */
public class History extends JFrame 
{   

    private HashMap<Integer, String> nonConstLocations = new HashMap<Integer, String>();

    private void buildMap()
    {
        nonConstLocations.put(1, "Atchison");
        nonConstLocations.put(11, "Belton");
        nonConstLocations.put(21, "Emporia");
        nonConstLocations.put(31, "Hiawatha");
        nonConstLocations.put(41, "Kansas City");
        nonConstLocations.put(51, "Lawrence");
        nonConstLocations.put(61, "Leavenworth");
        nonConstLocations.put(71, "Manhattan");
        nonConstLocations.put(81, "Platte City");
        nonConstLocations.put(91, "St Joseph");
        nonConstLocations.put(101, "Topeka");
        nonConstLocations.put(111, "Warrensburg");
    }
    

    private boolean DEBUG = false;
    private JTable table;
    private JTextField filterText;
    private JTextField statusText;
    private TableRowSorter<MyTableModel> sorter;
    private JPanel contentPane;
        
    private static User user;

    private Object[][] data;
    private String[] columnNames;

    private Database db = new Database();
    private int userID;

    private void checkUserType(String type, int UserID) 
    {
      if (type.equals("customer")) 
      {
        setDataCustomer(UserID);
      } 
      else if (type.equals("service_manager")) 
      {
        setDataManager();
      }
      else if (type.equals("sales_manager")) 
      {
        setDataManager();
      } 
      else if (type.equals("admin")) 
      {
        setDataManager();
      }
    }

    private void setDataCustomer(int UserID)
    {
        //TODO: Set Data for Customer
        String[] tempColumnNames = 
        {
            "Vehicle Class",
            "Start Date",
            "End Date",
            "Location",
            "Reservation ID",
            "Total Cost($)"
        };
        
        columnNames = tempColumnNames;
        List<HashMap<String, String>> dataBond = db.GetReservations(UserID);
        data = new Object[dataBond.size()][columnNames.length];
        
        for (int i = 0; i < dataBond.size(); i++) 
        {
            int iter = 0;
            HashMap<String,String> dataMap = dataBond.get(i);

            for(Entry<String,String> entry : dataMap.entrySet())
            {
                
                if(entry.getKey().equals(new String("vehicleClass")))
                {
                    data[i][0] = entry.getValue();
                }
                if(entry.getKey().equals(new String("startDate")))
                {
                    data[i][1] = entry.getValue();
                }
                if(entry.getKey().equals(new String("endDate")))
                {
                    data[i][2] = entry.getValue();
                }
                if(entry.getKey().equals(new String("locationId")))
                {
                    data[i][3] = nonConstLocations.get(Integer.parseInt(entry.getValue()));
                }
                if(entry.getKey().equals(new String("reservationid")))
                {
                    data[i][4] = entry.getValue();
                }
                if(entry.getKey().equals(new String("totalCost")))
                {
                    data[i][5] = entry.getValue();
                }
                
            }
        }
    }

    private void setDataManager()
    {
        String[] tempColumnNames = 
        {
            "Vehicle Class",
            "Start Date",
            "End Date",
            "Location",
            "Reservation ID",
            "Total Cost($)"
        };
        
        columnNames = tempColumnNames;
        List<HashMap<String, String>> dataBond = db.GetAllReservations();
        data = new Object[dataBond.size()][columnNames.length];
        
        for (int i = 0; i < dataBond.size(); i++) 
        {
            int iter = 0;
            HashMap<String,String> dataMap = dataBond.get(i);

            for(Entry<String,String> entry : dataMap.entrySet())
            {
                
                if(entry.getKey().equals(new String("vehicleClass")))
                {
                    data[i][0] = entry.getValue();
                }
                if(entry.getKey().equals(new String("startDate")))
                {
                    data[i][1] = entry.getValue();
                }
                if(entry.getKey().equals(new String("endDate")))
                {
                    data[i][2] = entry.getValue();
                }
                if(entry.getKey().equals(new String("locationId")))
                {
                    data[i][3] = nonConstLocations.get(Integer.parseInt(entry.getValue()));
                }
                if(entry.getKey().equals(new String("reservationid")))
                {
                    data[i][4] = entry.getValue();
                }
                if(entry.getKey().equals(new String("totalCost")))
                {
                    data[i][5] = entry.getValue();
                }
                
            }
        }
        //TODO: Set Data for ServiceManager
    }
    
    /** 
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void newFilter() 
    {
        RowFilter<MyTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try 
        {
            rf = RowFilter.regexFilter(filterText.getText());
        } 
        catch (java.util.regex.PatternSyntaxException e) 
        {
            return;
        }
        sorter.setRowFilter(rf);
    }

    public History(User loggedInUser) 
    {
        this.user = loggedInUser;
        userID = user.getCustomerId();
        System.out.println("The user Id is : " + userID);
        contentPane = new JPanel();

        buildMap();

        checkUserType(user.Type(), userID);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setBounds(200, 200, 800, 600);
                
        MyTableModel model = new MyTableModel();
        sorter = new TableRowSorter<MyTableModel>(model);

        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        table.setFillsViewportHeight(true);
        table.setRowSorter(sorter);

        //We have a single selection for our model.
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 
        //When selection changes, provide user with row numbers for
        //both view and model.
        table.getSelectionModel().addListSelectionListener
        (
            new ListSelectionListener() 
            {
                public void valueChanged(ListSelectionEvent event) 
                {
                    int viewRow = table.getSelectedRow();

                    if (viewRow < 0) 
                    {
                        //Selection got filtered away.
                        statusText.setText("");
                    } 
                    else 
                    {
                        int modelRow = table.convertRowIndexToModel(viewRow);
                        statusText.setText(String.format("Selected Row in view: %d. " 
                                        + "Selected Row in model: %d.", viewRow, modelRow));
                    }
                }
            }
        );

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        contentPane.add(scrollPane);

        //Create a separate form for filterText and statusText
        JPanel form = new JPanel(new SpringLayout());
        JLabel l1 = new JLabel("Filter Text:", SwingConstants.TRAILING);
        form.add(l1);
        filterText = new JTextField();

        //Whenever filterText changes, invoke newFilter.
        filterText.getDocument().addDocumentListener
        (
            new DocumentListener() 
            {
                public void changedUpdate(DocumentEvent e) 
                {
                    newFilter();
                }
                public void insertUpdate(DocumentEvent e) 
                {
                    newFilter();
                }
                public void removeUpdate(DocumentEvent e) 
                {
                    newFilter();
                }
            }
        );

        l1.setLabelFor(filterText);
        form.add(filterText);
        JLabel l2 = new JLabel("Status:", SwingConstants.TRAILING);
        form.add(l2);
        statusText = new JTextField();
        l2.setLabelFor(statusText);
        form.add(statusText);
        SpringUtilities.makeCompactGrid(form, 2, 2, 6, 6, 6, 6);
        //add(form);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(contentPane);
        container.add(form);

        setContentPane(container);
        pack();
    }

    class MyTableModel extends AbstractTableModel 
    {
        
        public int getColumnCount() 
        {
            return columnNames.length;
        }

        public int getRowCount() 
        {
            return data.length;
            //return dataBond.size();
        }

        public String getColumnName(int col) 
        {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) 
        {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) 
        { 
            for(int rowIndex = 0; rowIndex < data.length; rowIndex++)
            { 
                Object[] row = data[rowIndex]; 
                if (row[c] != null) 
                { 
                    return getValueAt(rowIndex, c).getClass(); 
                } 
            } 
            return String.class; 
        } 

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) 
        {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) 
            {
                return false;
            } 
            else 
            {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) 
        {
            if (DEBUG) 
            {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            //fireTableCellUpdated(row, col);

            if (DEBUG) 
            {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() 
        {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) 
            {
                System.out.print("    row " + i + ":");

                for (int j=0; j < numCols; j++) 
                {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
}
