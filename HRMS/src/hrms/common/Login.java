package hrms.common;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import hrms.admin.AdminDashBoard;
import hrms.hr.HRDashBoard;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import java.awt.Frame;

public class Login extends JFrame implements ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtid;
	private JPasswordField txtpass;
	private JRadioButton rdhr, rbadmin;
	private final ButtonGroup groupRoles = new ButtonGroup();
	private final JLabel lblNewLabel_3 = new JLabel("New label");
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		// JDK 1.5 -> swing adds EventDispatcher Thread that governs GUI programming
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		Login frame = new Login();
		frame.setVisible(true);
		
		System.out.println("Hello Sonu");
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/hrms/images/login.png")));
		setTitle("Login Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 526);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 253, 224));
		contentPane.setBorder(new LineBorder(new Color(0, 128, 255), 3));

		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel.setBounds(162, 119, 74, 54);
		contentPane.add(lblNewLabel);
		
		txtid = new JTextField();
		txtid.setToolTipText("Enter User ID");
		txtid.setFont(new Font("Calibri", Font.PLAIN, 25));
		txtid.setForeground(new Color(0, 128, 255));
		txtid.setBounds(328, 123, 194, 47);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_1.setForeground(new Color(0, 128, 255));
		lblNewLabel_1.setBounds(151, 204, 113, 47);
		contentPane.add(lblNewLabel_1);
		
		txtpass = new JPasswordField();
		txtpass.setToolTipText("Enter Password");
		txtpass.setForeground(new Color(0, 128, 255));
		txtpass.setBounds(328, 204, 194, 47);
		contentPane.add(txtpass);
		
		rdhr = new JRadioButton("HR");
		rdhr.setBackground(new Color(221, 253, 224));
		groupRoles.add(rdhr);
		rdhr.setFont(new Font("Calibri", Font.PLAIN, 25));
		rdhr.setForeground(new Color(0, 128, 255));
		rdhr.setBounds(151, 293, 88, 34);
		contentPane.add(rdhr);
		
		rbadmin = new JRadioButton("Admin");
		rbadmin.setBackground(new Color(221, 253, 224));
		groupRoles.add(rbadmin);
		rbadmin.setForeground(new Color(0, 128, 255));
		rbadmin.setFont(new Font("Calibri", Font.PLAIN, 25));
		rbadmin.setBounds(328, 293, 102, 34);
		contentPane.add(rbadmin);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);
		btnsubmit.setForeground(new Color(0, 128, 255));
		btnsubmit.setFont(new Font("Calibri", Font.PLAIN, 25));
		btnsubmit.setBounds(255, 381, 113, 34);
		contentPane.add(btnsubmit);
		
		JLabel lblNewLabel_2 = new JLabel("Login Form");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/hrms/images/key.png")));
		lblNewLabel_2.setForeground(new Color(0, 128, 255));
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNewLabel_2.setBackground(new Color(221, 253, 224));
		lblNewLabel_2.setBounds(255, 56, 155, 34);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/hrms/images/employee.jpg")));
		lblNewLabel_3.setBounds(0, 0, 714, 526);
		contentPane.add(lblNewLabel_3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	//	System.out.println("Button is being clicked");
		login();
	}
	
	public void login()
	{
		
		String id = txtid.getText().trim();  // trim() is a function to remove leading and trailing space
		char[] pass = txtpass.getPassword();
		String password = String.valueOf(pass);  // valueOf() function convert any type of array into String
		String userRole = "";
		
		if(rdhr.isSelected()) {   // to check which radio button is selected
		   userRole =  rdhr.getText();   // fetch the text
		}
		
		if(rbadmin.isSelected())
			userRole = rbadmin.getText();
		
		// Validation code for Mandatory fields
		if(id.length()==0 || password.isEmpty() || userRole.length()==0)
			JOptionPane.showMessageDialog(this, "All Fields are Mandatory");
		else {
			
			if(id.equalsIgnoreCase("precursor") &&  password.equals("admin") && userRole.equals("Admin")) {
				AdminDashBoard admin = new AdminDashBoard();
				admin.setVisible(true);
				this.dispose();
			}
			
			else if(id.equalsIgnoreCase("hr") &&  password.equals("hr")&& userRole.equals("HR")) {
				HRDashBoard hr = new HRDashBoard();
				hr.setVisible(true);
				this.dispose();
			}
			
			else {
				JOptionPane.showMessageDialog(this,"Invalid Credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();  // get the specific ASCII value for that key
	//	System.out.println(keyCode);
		if(keyCode==10)     // when we press Enter then login form submitted
			login();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
