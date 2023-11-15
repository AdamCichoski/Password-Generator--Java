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
public class Frame extends JFrame implements ActionListener, ChangeListener{
	private JSlider lengthSlider;
	private JFrame frame;
	private JTextField tf;
	private JTextArea savedPasswords;
	private JLabel label, length, saved;
	private JButton checkPassword, generatePassword, reset;
	private JCheckBox includesCapitals, includesSpecialChar;
	private boolean needsCapitals = false, needsSpecialCharacters = false;
	private int neededLength;
	// private ImageIcon img = new ImageIcon(IMG_0370.jpg);

	private Font myFont = new Font("Times new Roman", Font.BOLD, 30);
	private Font myFont2 = new Font("Times new Roman", Font.BOLD, 20);

	/**
	 * Constructor to create the Frame 
	 */
	public Frame() {
		frame.setIconImage(getIconImage());
		// Initializing all objects and components that are in the frame
		frame = new JFrame("Frame");
		checkPassword = new JButton("Check");
		generatePassword = new JButton("Generate Password");
		reset = new JButton("Reset");
		tf = new JTextField();
		savedPasswords = new JTextArea();
		label = new JLabel("Enter a password to test:");
		length = new JLabel("0");
		saved = new JLabel("Saved Passwords:");
		includesCapitals = new JCheckBox("Includes Capitals", false);
		includesSpecialChar = new JCheckBox("Includes Special Characters");
		lengthSlider = new JSlider(0, 20);


		setFrameItems();

		// Adding objects to my action listener
		this.add(includesCapitals);
		this.add(checkPassword);
		this.add(generatePassword);
		this.add(reset);
		this.add(includesSpecialChar);

		// Setting up all needed speficications for the frame
		frame.setSize(1200, 650);
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);

		// Adding all items to the frame
		frame.add(tf);
		frame.add(label);
		frame.add(includesCapitals);
		frame.add(checkPassword);
		frame.add(lengthSlider);
		frame.add(length);
		frame.add(generatePassword);
		frame.add(reset);
		frame.add(includesSpecialChar);
		frame.add(savedPasswords);
		frame.add(saved);
	}

	/**
	 * This method reads the action events of buttons
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
					savedPasswords.setText(savedPasswords.getText()+"\n"+tf.getText());
					tf.setText("Your password is strong!");
				} else {
					tf.setEditable(false);
					tf.setText("Password is weak.");
				}
		}
		if(e.getSource() == generatePassword){
			tf.setText(pc.generatePassword());
		}
		if(e.getSource() == reset){
			tf.setText("");
			this.neededLength=0;
			this.needsCapitals=false;
			this.needsSpecialCharacters=false;
			this.lengthSlider.setValue(0);
			includesSpecialChar.setSelected(false);
			includesCapitals.setSelected(false);
			tf.setEditable(true);
		}

	}

	/**
	 * This method is overriden from the ChangeListener interface
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == lengthSlider){
			length.setText(lengthSlider.getValue()+"");
		}
	}

	/**
	 * A method to more easily return the inputted password
	 * 
	 * @return the String in the text field
	 */
	public String getPassword() {
		return this.tf.getText();
	}

	/**
	 * This method sets up all required fields for the items that will be added into
	 * the frame
	 */
	private void setFrameItems() {
		// Setting up the check button
		checkPassword.addActionListener(this);
		checkPassword.setBounds(10, 500, 150, 60);
		checkPassword.setFont(myFont2);
		checkPassword.setFocusable(false);

		// Setting up the includes capitals checkbox
		includesCapitals.setBounds(10, 120, 250, 50);
		includesCapitals.setFont(myFont2);
		includesCapitals.setFocusable(false);

		//Setting up the includes special characters check box
		includesSpecialChar.setBounds(10,160,350,50);
		includesSpecialChar.setFont(myFont2);
		includesSpecialChar.setFocusable(false);

		// Creating the visuals for the text frame
		tf.setBounds(10, 60, 1170, 50);
		tf.setFont(myFont);

		savedPasswords.setBounds(600,200,550,400);
		savedPasswords.setEditable(false);
		savedPasswords.setFont(myFont);

		// Creating a space for the label to represent the needed length
		length.setBounds(215, 200, 200, 60);
		length.setFont(myFont2);

		// Creating the basis for the label above the text frame
		label.setBounds(10, 10, 1170, 50);
		label.setFont(myFont);

		saved.setBounds(600,160,250,50);
		saved.setFont(myFont);

		// Setting up the length slider
		lengthSlider.addChangeListener(this);
		lengthSlider.setBounds(10, 215, 200, 30);
		lengthSlider.setMajorTickSpacing(0);
		lengthSlider.setFont(myFont2);
		lengthSlider.setFocusable(false);
		lengthSlider.setValue(0);

		// Setting up the space and visuals for the generate password button
		generatePassword.addActionListener(this);
		generatePassword.setBounds(170, 500, 200, 60);
		generatePassword.setFocusable(false);
		generatePassword.setFont(myFont2);

		//Setting up the rest button
		reset.addActionListener(this);
		reset.setBounds(380, 500, 150,60);
		reset.setFocusable(false);
		reset.setFont(myFont2);
	}
}
