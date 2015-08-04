import java.util.*;

import javax.swing.*;

import java.text.DateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

import org.jdesktop.swingx.JXDatePicker;

import rentalcar.data.FormObject;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import rentalcar.web.Request;
import rentalcar.system.Database;
import rentalcar.system.User;

public class ReservationUpdateModule extends JFrame implements ActionListener {

	private User user;
	private Database dbClient = new Database();

	// flags for checking if the following items are selected
	private Boolean locationSelected = false;
	private Boolean startDateSelected = false;
	private Boolean endDateSelected = false;
	private Boolean boxoneSelected = false;
	private Boolean boxtwoSelected = false;
	private Boolean boxthreeSelected = false;

	private JPanel contentPane;
	private JLabel labelLocation;
	private JLabel labelDate;
	private JLabel labelStart;
	private JLabel labelEnd;
	private JLabel labelDaymonthyear;
	private JLabel labelVehicle;
	private JLabel labelOptionalServicesequipments;
	private JLabel labelServices;
	private JLabel labelEquipments;
	private JLabel labelChildSeat;
	private JLabel labelTotalPrice;
	private JTextField labelTotalPriceValue;

	private HashMap<String, String> rMap = new HashMap<String, String>();
	private HashMap<String, Double> maps = new HashMap<String, Double>();
	private HashMap<String, Integer> equipmentCounts = new HashMap<String, Integer>();
	private JComboBox<String> locationsComboBox;
	private JComboBox comboBox_7;
	private JXDatePicker startPicker;
	private JXDatePicker endPicker;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JButton btnNewButton;
	private int Locationid = 0;
	private String sDate = null;
	private String eDate = null;
	private String price = null;
	private Double dailyrate = 0.0;
	private Double weeklyrate = 0.0;
	private Double totalcosts = 0.0;
	private boolean priceSet = false;

	// http://en.wikipedia.org/wiki/Julian_day
	public static int julianDay(int year, int month, int day) {
		int a = (14 - month) / 12;
		int y = year + 4800 - a;
		int m = month + 12 * a - 3;
		int jdn = day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400
				- 32045;
		return jdn;
	}

	public static int diff(int y1, int m1, int d1, int y2, int m2, int d2) {
		return julianDay(y1, m1, d1) - julianDay(y2, m2, d2);
	}

	/**
	 * This class will calculate the duration in DAYS between the pickup date
	 * and return date
	 */
	public int Duration(String startdate, String enddate) {
		// variable declaration
		int syear = Integer.parseInt(startdate.substring(0, 4));
		int smonth = Integer.parseInt(startdate.substring(5, 7));
		int sday = Integer.parseInt(startdate.substring(8, 10));
		int eyear = Integer.parseInt(enddate.substring(0, 4));
		int emonth = Integer.parseInt(enddate.substring(5, 7));
		int eday = Integer.parseInt(enddate.substring(8, 10));
		int total = diff(eyear, emonth, eday, syear, smonth, sday);
		return total;
	}

	/** This class will calculate the total cost of the vehicle reservation */
	public Double getvehicleCost(Double dailyrate, Double weeklyrate, int days) {
		// check if the costumer rent more than a week, if so use weeklyrate to
		// calculate the price
		Double vehicleCosts = 0.0;
		Double mydouble = days / 7.0;
		int numberofweeks = mydouble.intValue();
		Double decimal = (10 * mydouble - 10 * numberofweeks) / 10;
		vehicleCosts = weeklyrate * numberofweeks + decimal * 7 * dailyrate;
		return vehicleCosts;
		// System.out.println("dailyrate : " + dailyrate);
		// System.out.println("weeklyrate : " + weeklyrate);
		// System.out.println("days : " + days);
		// System.out.println("vehicleCosts : " + vehicleCosts);
	}

	/** this Class will calculate the total service costs */
	public Double getserviceCosts(Boolean box1, Boolean box2, Boolean box3) {
		Double costs = 0.0;
		Double cost1 = box1 ? 1.0 : 0.0;
		Double cost2 = box2 ? 1.0 : 0.0;
		Double cost3 = box3 ? 1.0 : 0.0;
		costs = 7.0 * cost1 + 25.0 * cost2 + 5.0 * cost3;
		return costs;
	}

	public Vector<String> getAvailableClasses(
			HashMap<String, Integer> totalClasses,
			HashMap<String, Integer> reservedClasses) {
		Vector<String> availableClasses = new Vector<String>();
		Iterator<String> classIterator = totalClasses.keySet().iterator();
		String _class;
		while (classIterator.hasNext()) {
			_class = classIterator.next();
			if (reservedClasses.get(_class) == null
					|| reservedClasses.get(_class) < totalClasses.get(_class)) {
				availableClasses.add(_class);
			}
		}
		return availableClasses;
	}

	/**
	 * Create the frame.
	 */
	public ReservationUpdateModule(User _user) {

		super("RentalCar/Reservation");

		rMap.put("gps", "0");
		rMap.put("childSeat", "0");
		rMap.put("ktag", "0");
		rMap.put("roadside", "0");
		rMap.put("waiver", "0");
		rMap.put("accident", "0");

		this.user = _user;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 601);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/** Select a location */

		labelLocation = new JLabel("Locations:");
		labelLocation.setForeground(Color.BLACK);
		labelLocation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelLocation.setBounds(77, 44, 95, 20);
		contentPane.add(labelLocation);

		String[] locations = dbClient.GetLocations();
		locationsComboBox = new JComboBox<String>(locations);
		locationsComboBox.setMaximumRowCount(12);
		locationsComboBox.setBounds(235, 44, 172, 20);
		contentPane.add(locationsComboBox);
		locationsComboBox.addActionListener(this);

		comboBox_7 = new JComboBox<String>();
		comboBox_7.setBounds(235, 200, 190, 20);
		contentPane.add(comboBox_7);
		comboBox_7.addActionListener(this);

		/** Select the Date */

		labelStart = new JLabel();
		labelStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelStart.setText("Start : ");
		labelStart.setBounds(77, 114, 67, 20);
		contentPane.add(labelStart);

		labelEnd = new JLabel();
		labelEnd.setText("End :");
		labelEnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEnd.setBounds(77, 156, 58, 20);
		contentPane.add(labelEnd);

		startPicker = new JXDatePicker();
		startPicker.setDate(Calendar.getInstance().getTime());
		startPicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		startPicker.setBounds(256, 114, 118, 20);
		// set a lower bound so dates before today cannot be selected
		startPicker.getMonthView().setLowerBound(
				Calendar.getInstance().getTime());
		startPicker.addActionListener(this);
		contentPane.add(startPicker);

		endPicker = new JXDatePicker();
		endPicker.setDate(Calendar.getInstance().getTime());
		endPicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		endPicker.setBounds(256, 156, 118, 20);
		endPicker.addActionListener(this);
		contentPane.add(endPicker);

		labelVehicle = new JLabel();
		labelVehicle.setText("Vehicle Class");
		labelVehicle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelVehicle.setBounds(77, 198, 118, 20);
		contentPane.add(labelVehicle);

		labelOptionalServicesequipments = new JLabel();
		labelOptionalServicesequipments.setText("Optional Services/Equipments");
		labelOptionalServicesequipments.setFont(new Font("Tahoma", Font.PLAIN,
				15));
		labelOptionalServicesequipments.setBounds(77, 247, 226, 20);
		contentPane.add(labelOptionalServicesequipments);

		labelServices = new JLabel();
		labelServices.setText("Services");
		labelServices.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelServices.setBounds(156, 289, 86, 20);
		contentPane.add(labelServices);

		labelEquipments = new JLabel();
		labelEquipments.setText("Equipments");
		labelEquipments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEquipments.setBounds(156, 333, 102, 20);
		contentPane.add(labelEquipments);

		chckbxNewCheckBox = new JCheckBox("Roadside Assistance");
		chckbxNewCheckBox.setToolTipText("");
		chckbxNewCheckBox.setBounds(270, 290, 161, 23);
		contentPane.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(this);

		chckbxNewCheckBox_1 = new JCheckBox("Loss Damage Waiver Insurance");
		chckbxNewCheckBox_1.setBounds(443, 288, 226, 23);
		contentPane.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.addActionListener(this);

		chckbxNewCheckBox_2 = new JCheckBox("Personal Accident Insurance");
		chckbxNewCheckBox_2.setBounds(670, 288, 209, 23);
		contentPane.add(chckbxNewCheckBox_2);
		chckbxNewCheckBox_2.addActionListener(this);

		labelChildSeat = new JLabel();
		labelChildSeat.setText("Child Seat");
		labelChildSeat.setBounds(530, 334, 80, 20);
		contentPane.add(labelChildSeat);

		JSpinner spinner_1 = new JSpinner();
		// int seatCounts = equipmentCounts.get("Child Seat");
		spinner_1.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));
		spinner_1.setBounds(611, 333, 58, 20);
		contentPane.add(spinner_1);

		labelTotalPrice = new JLabel();
		labelTotalPrice.setText("Total Price : ");
		labelTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelTotalPrice.setBounds(156, 395, 102, 20);
		contentPane.add(labelTotalPrice);

		/** Add an actionListener to print out price dynamically */
		labelTotalPriceValue = new JTextField();
		labelTotalPriceValue.setEditable(false);
		labelTotalPriceValue.setBounds(270, 395, 400, 20);
		contentPane.add(labelTotalPriceValue);
		labelTotalPriceValue.addActionListener(this);

		btnNewButton = new JButton("Confirm Reservation");
		btnNewButton.setBounds(327, 498, 161, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);

		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("GPS");
		chckbxNewCheckBox_3.setBounds(270, 332, 58, 23);
		contentPane.add(chckbxNewCheckBox_3);

		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("K-Tag");
		chckbxNewCheckBox_4.setBounds(392, 332, 82, 23);
		contentPane.add(chckbxNewCheckBox_4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}