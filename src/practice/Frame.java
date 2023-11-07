package practice;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
/**
 * This class 
 * @Author Adam Cichoski
 */
public class Frame extends JFrame implements ActionListener{
	private PasswordCharacters characterCheck = new PasswordCharacters();
	private JFrame frame, error;
	private JTextField tf, passwordLength;
	private JLabel label, checkBox1;
	private JButton checkPassword, submit; 
	private JCheckBox includesCapitals, includesSpecialChar;
	private boolean needsCapitals = false, needsSpecialCharacters = false;
	private int neededLength;
	
	private Font myFont = new Font("Times new Roman", Font.BOLD, 30);
	private Font myFont2 = new Font("Times new Roman", Font.BOLD, 20);
	public Frame() {
		frame = new JFrame("Frame");
		error = new JFrame("ERROR");
		submit = new JButton("Submit");
		checkPassword = new JButton("Check");
		tf = new JTextField();
		label = new JLabel("Enter a password to test:");
		includesCapitals = new JCheckBox("Includes Capitals", false);
		submit.addActionListener(this);
		checkPassword.addActionListener(this);
		
		checkPassword.setBounds(175,500,150,60);
		checkPassword.setFont(myFont2);
		checkPassword.setFocusable(false);
		
		submit.setBounds(10,500,150,60);
		submit.setFont(myFont2);
		submit.setFocusable(false);
		
		includesCapitals.setBounds(10,120,250,75);
		includesCapitals.setFont(myFont2);
		includesCapitals.setFocusable(false);
		
		
		tf.setBounds(10,60,1170,50);
		tf.setFont(myFont);
		
		label.setBounds(10,10,1170,50);
		label.setFont(myFont);
		
		frame.setSize(1200,650);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		this.add(submit);
		this.add(includesCapitals);
		
		frame.add(tf);
		frame.add(label);
		frame.add(includesCapitals);
		frame.add(submit);
		frame.add(checkPassword);
	}
	/**
	 * A method to more easily return the inputted password
	 * @return
	 */
	public String getPassword() {
		return this.tf.getText();
	}
	/**
	 * This method reads the action events of buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit) {
			if(includesCapitals.isSelected()) {
				this.needsCapitals = true;
			}
			else {
				this.needsCapitals = false;
			}

		}
		if(e.getSource() == checkPassword) {
			PasswordCharacters pc = new PasswordCharacters();
			if(pc.checkPassword(getPassword(), needsCapitals, needsSpecialCharacters)) {
				tf.setText("You have passed!");
			}
			else {
				tf.setText("hmmm, I am unsure");
			}
			
		}
		
	}
	
}
