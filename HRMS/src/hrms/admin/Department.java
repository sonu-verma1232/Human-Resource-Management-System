package hrms.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import hrms.dbinfo.DBConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.util.Date;

public class Department extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtdname;
	private JTextField txtHname;
	private JTextField txtphone;
	private JTextField txtemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Department frame = new Department();
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
	public Department() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Department Form");
		setBounds(100, 100, 813, 493);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 254, 203));
		contentPane.setBorder(new LineBorder(new Color(128, 0, 64), 2));

		setLocationRelativeTo(null); // it will place the component in center
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Department Details");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNewLabel.setBounds(281, 26, 241, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Department Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(113, 91, 227, 31);
		contentPane.add(lblNewLabel_1);
		
		txtdname = new JTextField();
		txtdname.addKeyListener(this);
		txtdname.setToolTipText("Enter Department Name");
		txtdname.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtdname.setBounds(397, 91, 227, 31);
		contentPane.add(txtdname);
		txtdname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Department Head");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(113, 148, 227, 31);
		contentPane.add(lblNewLabel_2);
		
		txtHname = new JTextField();
		txtHname.addKeyListener(this);
		txtHname.setToolTipText("Enter Department Head Name");
		txtHname.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtHname.setBounds(397, 148, 227, 31);
		contentPane.add(txtHname);
		
		txtHname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Department PhoneNo.");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(113, 201, 227, 42);
		contentPane.add(lblNewLabel_3);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setToolTipText("Enter Department Number");
		txtphone.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtphone.setBounds(397, 213, 227, 30);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Department Email");
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(113, 269, 227, 36);
		contentPane.add(lblNewLabel_4);
		
		txtemail = new JTextField();
		txtemail.setToolTipText("Enter Email");
		txtemail.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtemail.setBounds(397, 274, 227, 31);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);
		btnsubmit.setFont(new Font("Calibri", Font.PLAIN, 25));
		btnsubmit.setBounds(604, 350, 141, 31);
		contentPane.add(btnsubmit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	//	System.out.println("Submit successfully");
		addDepartment();
		
	}
	
	public void addDepartment()
	{
		String deptName = txtdname.getText();
	    String deptHead = txtHname.getText();
		String phone = txtphone.getText();
		String email = txtemail.getText();
		
		
		
		if(deptName.isEmpty() || deptHead.length()==0 || phone.isEmpty() || email.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"All fields are mandatory !");
		}
		else
		{
			if(phone.length()>10 || phone.length()<10)
			{
				JOptionPane.showMessageDialog(this, "Phone Number must contain 10 digits only");
			}
			
			else if (email.indexOf('@')== -1 || email.indexOf('.')== -1)
			{
			// indexOf()-> it returns the index of particular character otherwise it return -1	
				
				JOptionPane.showMessageDialog(this, "Invalid email ");
				
				
				
			}
			
			else if(email.indexOf('@')!= -1) {
				int j=0, k=0;
				for(int i=0; i<email.length(); i++) {
					char ch = email.charAt(i);
					
					if(ch == '@')
					{
						j++;
					}
					if(ch == '.')
						k++;
				}
				if(j==2) {
					JOptionPane.showMessageDialog(this, "  Two '@' are not allowed ");
				}
				if(k==2)
					JOptionPane.showMessageDialog(this, "Two '.' are not  allowed");
			}
			
			else
			{
			
				
				
				
			Connection con = DBConnection.openConnection(); // connection with hrms_db
			String insertQuery = "insert into department(dept_name, hod_name, email, phone, date) values(?,?,?,?,?)";
			// ? -> placeHolder to store value
			
			PreparedStatement ps = null;
			
			try {
				
				ps = con.prepareStatement(insertQuery);
				// it passes the query to RDBMS and RDBMS complies the query and store it into a buffer and assign 
				// address of that buffer to ps
				
				java.util.Date d = new java.util.Date();  // fully qualified class name -> class name with package name
				long dt = d.getTime();
				
				java.sql.Date sd = new java.sql.Date(dt);  // fully qualified class
				
				ps.setString(1, deptName);  // textBox fetched value
				ps.setString(2, deptHead);
				ps.setString(3,email);
				ps.setString(4, phone);
				ps.setDate(5,sd);
				
				System.out.println(ps);
				
				int status = ps.executeUpdate();  // to insert data in the table call this method
				System.out.println(status);
				
				if(status > 0) 
				{
					JOptionPane.showMessageDialog(this, "Department data added successfully");
					txtdname.setText("");  // it clear all field after adding of the data successfully
					txtemail.setText("");
					txtHname.setText("");
					txtphone.setText("");
				}
				
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
				
			//	System.out.println("Error code : "+ se.getErrorCode());
				
				if(se.getErrorCode()==1062) {  // to check Primary key error code
					
					JOptionPane.showMessageDialog(this, deptName+" Department Already Exists");
				}
				
			}
			
			finally {       // closing all the resources
				
				try {
					
					if(ps != null)
						ps.close();
					
					if(con != null)
						con.close();
					
				} catch (SQLException se) {
					
					se.printStackTrace();
					
				}
			}
			
		}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar(); // it will return the character
	//	System.out.println(c);
		
		if(e.getSource()==txtdname || e.getSource()==txtHname)
			// getSource() -> it returns the objects of current initiated event
			{
				if(!(Character.isAlphabetic(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE || c==KeyEvent.VK_SPACE))
				{
					
					JOptionPane.showMessageDialog(this, "Only alphabets are allowed");
					e.consume();
					
				}
			}
		
		if(e.getSource()==txtphone)
		{
			if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE)) {
				JOptionPane.showMessageDialog(this, "Only digits are allowed");
				e.consume();
			}
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode(); 
		if(keyCode==10)     
			addDepartment();
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
