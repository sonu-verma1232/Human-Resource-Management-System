package hrms.admin;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.border.LineBorder;

import hrms.dbinfo.DBConnection;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class UpdateDepartment extends JFrame implements ActionListener, KeyListener, ItemListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtemail;
	private JTextField txtphone;
	private JTextField txthname;
	private JComboBox<String> cmbname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDepartment frame = new UpdateDepartment();
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
	
	
	public void fillCombo()
	{
		
		Connection con = DBConnection.openConnection();
		// create connection to establish connection
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectQuery="select dept_name from department";
		
		try {
			
			ps = con.prepareStatement(selectQuery);
			// to prepare the query with dbms
			rs = ps.executeQuery();
			// to execute the query with rdbms and hold the referece in rs
			
			int count = 0;
			while(rs.next())
				// to put pointer in the dataset and check for the availability of data
			{
				count = 1;
				String name = rs.getString("dept_name");
				// to fetch data from dept_name column
				
				cmbname.addItem(name);
				// to add items in comboBox
				
				
			} 
			if(count == 0) {
				JOptionPane.showMessageDialog(this, "there is no department exsists");
			}
			
		} 
		catch (SQLException se) 
		{
			// TODO: handle exception
			se.printStackTrace();
		}
		finally 
		{
			try
			{
				if(rs != null)
					rs.close();
				
				if(ps != null)
					ps.close();
				
				if(con != null)
					con.close();
				
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
	}
	
	
	
	public UpdateDepartment() {
		setTitle("Update Department");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 910, 506);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 128));
		contentPane.setBorder(new LineBorder(new Color(128, 0, 64), 2));

		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbname = new JComboBox();
		cmbname.addItemListener(this);
		cmbname.setModel(new DefaultComboBoxModel(new String[] {"Select Department"}));
		cmbname.setFont(new Font("Calibri", Font.ITALIC, 20));
		cmbname.setBounds(397, 30, 218, 34);
		fillCombo(); // populate comboBox with item from table
		contentPane.add(cmbname);
		
		JLabel newlabel = new JLabel("Email");
		
		newlabel.setFont(new Font("Calibri", Font.ITALIC, 25));
		newlabel.setBounds(169, 91, 187, 34);
		contentPane.add(newlabel);
		
		txtemail = new JTextField();
		txtemail.addKeyListener(this);
		txtemail.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtemail.setBounds(397, 91, 218, 34);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtphone.setBounds(397, 159, 218, 31);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Phone");
		lblNewLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel.setBounds(169, 159, 187, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Head Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_1.setBounds(169, 224, 187, 34);
		contentPane.add(lblNewLabel_1);
		
		txthname = new JTextField();
		txthname.addKeyListener(this);
		txthname.setFont(new Font("Calibri", Font.PLAIN, 20));
		txthname.setBounds(397, 224, 218, 34);
		contentPane.add(txthname);
		txthname.setColumns(10);
		
		JButton btnedit = new JButton("Edit");
		btnedit.addActionListener(this);
		btnedit.setIcon(new ImageIcon(UpdateDepartment.class.getResource("/hrms/images/editpencil.png")));
		btnedit.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		btnedit.setBounds(397, 338, 218, 47);
		contentPane.add(btnedit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("Department Edited");
		editDepartment();
	}
	
	public void editDepartment() {
		
		String email = txtemail.getText();
		String phone = txtphone.getText();
		String hname = txthname.getText();
		
		String deptName = (String)cmbname.getSelectedItem();
		// it is used to fetch any object of comboBox 
		
		if(deptName.equalsIgnoreCase("Select Department"))
		{
			JOptionPane.showMessageDialog(this,"Please select department name");
		}
		else 
		{
			Connection con = DBConnection.openConnection();
			// create connection to establish connection
			PreparedStatement ps = null;
			
			String updateQuery = "update department set email=?,phone=?,hod_name=? where dept_name=?";
			
			try
			{
				
				ps = con.prepareStatement(updateQuery);
				
				ps.setString(1, email);  // here number is written inorder in which it which placeholder in update query 
				ps.setString(2, phone);
				ps.setString(3, hname);
				ps.setString(4, deptName);
				
				int status = ps.executeUpdate();
				
				if(status > 0)
					JOptionPane.showMessageDialog(this, deptName+" department details updated successfully");
				
				
			}
			catch(SQLException se)
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
					
					
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				
			}
			
			
			
			
			if(phone.length()>10 || phone.length()<10)
			{
				JOptionPane.showMessageDialog(this, "Phone Number must contain 10 digits only");
			}
			
			else if (email.indexOf('@')== -1 || email.indexOf('.')== -1)
			{
			// indexOf()-> it returns the index of particular character otherwise it return -1	
				JOptionPane.showMessageDialog(this, "Invalid email ");
				
				
				
			}
			
		}
		
		if(email.isEmpty() || phone.isEmpty() || hname.length()==0) {
			JOptionPane.showMessageDialog(this, "All fields are mandatory to filled");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if( e.getSource()==txthname)
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
		    
			;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		// TODO Auto-generated method stub
		
//		System.out.println(e.getStateChange());
//		System.out.println("Hello Combo");
		
		String name = null;
		
		if(e.getStateChange() == 2)
		{
			name = (String)cmbname.getSelectedItem();
			System.out.println("Department name is "+name);
			
			
			Connection con = DBConnection.openConnection();
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String selectQuery = "select * from department where dept_name = ?";
			
			try
			{
				
				ps = con.prepareStatement(selectQuery);
				ps.setString(1, name);
				rs = ps.executeQuery();
				
				rs.next(); // to move the cursor in the data set
				
				String hodname = rs.getString("hod_name");
				String phone = rs.getString("phone");
				
				txthname.setText(hodname);
				txtphone.setText(phone);
				
				txtemail.setText(rs.getString("email"));
				
				
			}
			catch (SQLException se)
			{
				se.printStackTrace();
			}
			
			finally 
			{
				try
				{
					if(rs != null)
						rs.close();
					
					if(ps != null)
						ps.close();
					
					if(con != null)
						con.close();
					
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
			
		}
		
	}
}
