package hrms.hr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hrms.dbinfo.DBConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.sql.*;
import java.util.Date;

public class Employee extends JFrame implements ActionListener , KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtphone;
	private JTextField txtdept;
	private JTextField txtdesig;
	private JTextArea txtadd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
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
	public Employee() {
		setTitle("Employee Form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 819, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 254, 203));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel.setBounds(89, 23, 250, 31);
		contentPane.add(lblNewLabel);
		
		txtid = new JTextField();
		txtid.addKeyListener(this);
		txtid.setToolTipText("Enter ID");
		txtid.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtid.setBounds(416, 24, 264, 31);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(89, 72, 250, 31);
		contentPane.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.addKeyListener(this);
		txtname.setToolTipText("Enter Name");
		txtname.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtname.setBounds(416, 73, 264, 31);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(89, 122, 250, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Phone");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_1_2.setBounds(89, 172, 250, 31);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Department");
		lblNewLabel_1_3.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_1_3.setBounds(89, 223, 250, 31);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Designation");
		lblNewLabel_1_4.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_1_4.setBounds(89, 277, 250, 31);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Address");
		lblNewLabel_1_5.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_1_5.setBounds(89, 327, 250, 31);
		contentPane.add(lblNewLabel_1_5);
		
		txtemail = new JTextField();
		txtemail.addKeyListener(this);
		txtemail.setToolTipText("Enter Email");
		txtemail.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtemail.setColumns(10);
		txtemail.setBounds(416, 123, 264, 31);
		contentPane.add(txtemail);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setToolTipText("Enter Phone Number");
		txtphone.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtphone.setColumns(10);
		txtphone.setBounds(416, 173, 264, 31);
		contentPane.add(txtphone);
		
		txtdept = new JTextField();
		txtdept.addKeyListener(this);
		txtdept.setToolTipText("Enter Department");
		txtdept.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtdept.setColumns(10);
		txtdept.setBounds(416, 229, 264, 31);
		contentPane.add(txtdept);
		
		txtdesig = new JTextField();
		txtdesig.addKeyListener(this);
		txtdesig.setToolTipText("Enter Designation");
		txtdesig.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtdesig.setColumns(10);
		txtdesig.setBounds(416, 278, 264, 31);
		contentPane.add(txtdesig);
		
		txtadd = new JTextArea();
		txtadd.addKeyListener(this);
		txtadd.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtadd.setToolTipText("Enter Address");
		txtadd.setText("");
		txtadd.setBounds(416, 336, 264, 37);
		contentPane.add(txtadd);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.setFont(new Font("Calibri", Font.PLAIN, 25));
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);
		btnsubmit.setBounds(602, 383, 172, 40);
		contentPane.add(btnsubmit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	// 	System.out.println("Button is click");
		addEmployee();
		
	}
	
	public void addEmployee()
	{
		String name = txtname.getText();
		String email = txtemail.getText();
		String phone = txtphone.getText();
		String dept = txtdept.getText();
		String id = txtid.getText();
		String desi = txtdesig.getText();
		String add = txtadd.getText();
		
		if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || dept.isEmpty() || id.isEmpty() || desi.isEmpty() || add.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "All Fields are mandatory");
		}
		else 
		{
			
			if(phone.length()>10 || phone.length()<10)
			{
				JOptionPane.showMessageDialog(this, "Phone Number must contain 10 digits only");
			}
			
			else if(email.indexOf('@')== -1 || email.indexOf('.')== -1)
			{
				JOptionPane.showMessageDialog(this, "Invalid email");
			}
			
			else 
			{
				
				Connection con = DBConnection.openConnection();
				String insertQuery = "insert into employee(ID, Name, Email, Phone, Department, Designation, Address,Date) values(?,?,?,?,?,?,?,?)";
				
				PreparedStatement ps = null;
				
				try
				{
					ps = con.prepareStatement(insertQuery);
					java.util.Date d = new java.util.Date();
					long dt = d.getTime();
					
					java.sql.Date sd = new java.sql.Date(dt);
					
					ps.setString(1,id);
					ps.setString(2, name);
					ps.setString(3, email);
					ps.setString(4, phone);
					ps.setString(5, dept);
					ps.setString(6, desi);
					ps.setString(7, add);
					ps.setDate(8, sd);
					
					System.out.println(ps);
					
					int status = ps.executeUpdate();
					System.out.println(status);
					
					if(status > 0)
					{
						JOptionPane.showMessageDialog(this, "Employee data added successfully");
						
						txtid.setText("");
						txtname.setText("");
						txtemail.setText("");
						txtdept.setText("");
						txtdesig.setText("");
						txtadd.setText("");
						txtphone.setText("");
					}
					
				}	
				catch (SQLException se) 
				{
						se.printStackTrace();
				}
				
				finally 
				{
					
					try {
						
						if(ps != null)
							ps.close();
						
						if(con != null)
							con.close();
						
						
					} catch (SQLException se) {
						// TODO: handle exception
						se.printStackTrace();
					}
				}
				
			}
			
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		
		if(e.getSource()== txtid)
		{
			if(!(Character.isAlphabetic(c) || Character.isDigit(c)|| c == KeyEvent.VK_BACK_SPACE))
			{
				JOptionPane.showMessageDialog(this, "Enter valid ID");
				e.consume();
			}
		}
		
		if(e.getSource()==txtname || e.getSource()==txtdept || e.getSource() == txtdesig)
		{
			if(!(Character.isAlphabetic(c)|| c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_SPACE))
			{
				JOptionPane.showMessageDialog(this, "Only alphabets are allowed");
				e.consume();
			}
		}
		
		if(e.getSource()==txtphone)
		{
			if(!(Character.isDigit(c)||c == KeyEvent.VK_BACK_SPACE))
			{
				JOptionPane.showMessageDialog(this, "Only digits are allowed");
				e.consume();
			}
		}
		
		if(e.getSource()==txtadd )
		{
			if(!(Character.isAlphabetic(c) || Character.isDigit(c)|| c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_BACK_SLASH))
			{
				JOptionPane.showMessageDialog(this, "Only Characters are allowed");
				e.consume();
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode(); 
		if(keyCode==10)   
			addEmployee();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
