package practice;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates a JFrame that takes in user input in a text field and is
 * able to determine the strength
 * of that string with respect to it being used as a password. It also holds the
 * capability to generate a random
 * password that will pass the strength test.
 * 
 * @Author Adam Cichoski
 */
public class Frame extends JFrame implements ActionListener, ChangeListener {
	private JSlider lengthSlider;
	private JFrame frame;
	private JTextField tf;
	private JLabel label, length, saved;
	private JButton checkPassword, generatePassword, reset;
	private JPanel passwordStorage;
	private JCheckBox includesCapitals, includesSpecialChar;
	private boolean needsCapitals = false, needsSpecialCharacters = false;
	private int neededLength;

	private int savedPasswordCount=0;
	private Font myFont = new Font("Times new Roman", Font.BOLD, 30);
	private Font myFont2 = new Font("Times new Roman", Font.BOLD, 20);

	/**
	 * Constructor to create the Password Generator Frame
	 */
	public Frame() {
		/****************************
		 * Initalizing all instance variables
		 */
		frame = new JFrame("Frame");
		checkPassword = new JButton("Check");
		generatePassword = new JButton("Generate Password");
		reset = new JButton("Reset");
		passwordStorage = new JPanel();
		tf = new JTextField();
		label = new JLabel("Enter a password to test:");
		length = new JLabel("0");
		saved = new JLabel("Saved Passwords:");
		includesCapitals = new JCheckBox("Includes Capitals", false);
		includesSpecialChar = new JCheckBox("Includes Special Characters");
		lengthSlider = new JSlider(0, 20);

		setFrameItems();
		/****************************
		 * Adding elements to the action listener
		 */
		this.add(includesCapitals);
		this.add(checkPassword);
		this.add(generatePassword);
		this.add(reset);
		this.add(includesSpecialChar);

		/****************************
		 * Setting up the frame
		 */
		frame.setSize(1200, 650);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);

		/****************************
		 * Adding all elements to the frame
		 */
		frame.add(tf);
		frame.add(label);
		frame.add(includesCapitals);
		frame.add(checkPassword);
		frame.add(lengthSlider);
		frame.add(length);
		frame.add(generatePassword);
		frame.add(reset);
		frame.add(includesSpecialChar);
		frame.add(passwordStorage);
		frame.add(saved);

	}

	/**
	 * This method reads the action events of buttons
	 * Buttons checked:
	 * Generate Password
	 * Check
	 * Reset
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		PasswordCharacters pc = new PasswordCharacters();
		if (e.getSource() == lengthSlider) {
			this.neededLength = lengthSlider.getValue();
		}
		if (e.getSource() == checkPassword) {
			this.neededLength = lengthSlider.getValue();
			this.needsCapitals = (includesCapitals.isSelected());
			this.needsSpecialCharacters = (includesSpecialChar.isSelected());
			if (pc.checkPassword(getPassword(), neededLength, needsCapitals, needsSpecialCharacters)) {
				tf.setEditable(false);
				checkPassword.setEnabled(false);
				addPassword(getPassword());
				pack();
				tf.setText("Your password is strong!");
			} else {
				tf.setEditable(false);
				checkPassword.setEnabled(false);
				tf.setText("Password is weak.");
			}
		}
		if (e.getSource() == generatePassword) {
			checkPassword.setEnabled(true);
			tf.setText(pc.generatePassword());
		}
		if (e.getSource() == reset) {
			tf.setText("");
			this.neededLength = 0;
			this.needsCapitals = false;
			this.needsSpecialCharacters = false;
			this.lengthSlider.setValue(0);
			includesSpecialChar.setSelected(false);
			includesCapitals.setSelected(false);
			tf.setEditable(true);
			checkPassword.setEnabled(true);
		}

	}

	/**
	 * This method sets up all required fields for the items that will be added into
	 * the frame
	 */
	private void setFrameItems() {
		/***********************
		 * Setting up the checkPassword button
		 **********************/
		checkPassword.addActionListener(this);
		checkPassword.setBounds(10, 500, 150, 60);
		checkPassword.setFont(myFont2);
		checkPassword.setFocusable(false);

		/***********************
		 * Setting up the includes capitals checkbox
		 **********************/
		includesCapitals.setBounds(10, 120, 250, 50);
		includesCapitals.setFont(myFont2);
		includesCapitals.setFocusable(false);

		/***********************
		 * Setting up the includes special characters checkbox
		 **********************/
		includesSpecialChar.setBounds(10, 160, 350, 50);
		includesSpecialChar.setFont(myFont2);
		includesSpecialChar.setFocusable(false);

		/***********************
		 * Setting up the textfield to hold the inputted passwords
		 **********************/
		tf.setBounds(10, 60, 1170, 50);
		tf.setFont(myFont);

		

		/***********************
		 * Setting up the label to show the needed length of a password
		 **********************/
		length.setBounds(215, 200, 200, 60);
		length.setFont(myFont2);

		/***********************
		 * Setting up the main label to go above the text field
		 **********************/
		label.setBounds(10, 10, 1170, 50);
		label.setFont(myFont);

		/***********************
		 * Setting up the label to go above the saved password text area
		 **********************/
		saved.setBounds(600, 140, 250, 50);
		saved.setFont(myFont);

		/***********************
		 * Setting up the slider used to determine needed password length
		 **********************/
		lengthSlider.addChangeListener(this);
		lengthSlider.setBounds(10, 215, 200, 30);
		lengthSlider.setMajorTickSpacing(0);
		lengthSlider.setFont(myFont2);
		lengthSlider.setFocusable(false);
		lengthSlider.setValue(0);

		/***********************
		 * Setting up the panel space to hold the saved passwords as buttons
		 **********************/
		passwordStorage.setBounds(600,200,400,400);
		passwordStorage.setLayout(new GridLayout(11,1,30,0));
		passwordStorage.setBackground(Color.GRAY);

		/***********************
		 * Setting up the generate password button
		 **********************/
		generatePassword.addActionListener(this);
		generatePassword.setBounds(170, 500, 200, 60);
		generatePassword.setFocusable(false);
		generatePassword.setFont(myFont2);

		/***********************
		 * Setting up the reset button
		 ***********************/
		reset.addActionListener(this);
		reset.setBounds(380, 500, 150, 60);
		reset.setFocusable(false);
		reset.setFont(myFont2);
	}

	/**
	 * This method is overriden from the ChangeListener interface
	 * Items checked
	 * Length slider (for password length)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == lengthSlider) {
			length.setText(lengthSlider.getValue() + "");
		}
	}

	private int addPassword(String newPassword){
		if(savedPasswordCount>=11){
			error("Password Storage Is Full!");
			return -1;
		}
		passwordStorage.add((new JButton(newPassword))).setFocusable(false);
		pack();
		return ++savedPasswordCount;
	}
	/**
	 * A method to more easily return the inputted password
	 * 
	 * @return the String in the text field
	 */
	public String getPassword() {
		return tf.getText();
	}
	private void error(String alert){
		tf.setText("ERROR: Password Storage Full");
	}
}
