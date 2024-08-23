package hrms.hr;

import java.awt.event.*;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import hrms.dbinfo.DBConnection;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class UpdateEmpoyee extends JFrame implements ActionListener , KeyListener, ItemListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtphone;
	private JTextField txtdept;
	private JTextField txtdesig;
	private JTextArea txtadd;
	private JComboBox<String> cmbemp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEmpoyee frame = new UpdateEmpoyee();
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectQuery = "select ID from employee";
		
		try
		{
		   
			ps = con.prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next())
			{
				
				count = 1;
				String name = rs.getString("ID");
				
				cmbemp.addItem(name);
				
				
			}
			
			if(count == 0)
				JOptionPane.showMessageDialog(this, "No employee exsists");
			
			
		}
		catch(SQLException se)
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
	
	
	public UpdateEmpoyee() {
		setTitle("Update Employee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 821, 623);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 128));
		contentPane.setBorder(new LineBorder(new Color(0, 128, 64), 2));

		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbemp = new JComboBox();
		cmbemp.addItemListener(this);
		cmbemp.setModel(new DefaultComboBoxModel(new String[] {"Select Employee ID"}));
		cmbemp.setFont(new Font("Calibri", Font.ITALIC, 25));
		cmbemp.setBounds(245, 32, 265, 42);
		fillCombo();
		contentPane.add(cmbemp);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Calibri", Font.ITALIC, 24));
		lblNewLabel.setBounds(95, 116, 190, 42);
		contentPane.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtname.setBounds(369, 117, 258, 42);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_1.setBounds(95, 192, 190, 42);
		contentPane.add(lblNewLabel_1);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtemail.setBounds(369, 194, 258, 42);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_2.setBounds(95, 258, 190, 42);
		contentPane.add(lblNewLabel_2);
		
		txtphone = new JTextField();
		txtphone.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtphone.setBounds(369, 262, 258, 37);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Department");
		lblNewLabel_3.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_3.setBounds(95, 322, 190, 37);
		contentPane.add(lblNewLabel_3);
		
		txtdept = new JTextField();
		txtdept.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtdept.setBounds(369, 323, 258, 37);
		contentPane.add(txtdept);
		txtdept.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Designation");
		lblNewLabel_4.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_4.setBounds(95, 387, 190, 37);
		contentPane.add(lblNewLabel_4);
		
		txtdesig = new JTextField();
		txtdesig.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtdesig.setBounds(369, 388, 258, 37);
		contentPane.add(txtdesig);
		txtdesig.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_5.setBounds(95, 448, 190, 39);
		contentPane.add(lblNewLabel_5);
		
		txtadd = new JTextArea();
		txtadd.setBounds(369, 448, 258, 39);
		
		contentPane.add(txtadd);
		
		JButton btnedit = new JButton("Edit");
		btnedit.addActionListener(this);
		btnedit.setIcon(new ImageIcon(UpdateEmpoyee.class.getResource("/hrms/images/editpencil.png")));
		btnedit.setFont(new Font("Calibri", Font.ITALIC, 25));
		btnedit.setBounds(245, 539, 272, 37);
		contentPane.add(btnedit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		editemp();
	}
	
	
	public void editemp() {
		
		
		String name = txtname.getText();
		String email = txtemail.getText();
		String phone = txtphone.getText();
		String dept = txtdept.getText();
		String desig = txtdesig.getText();
		String add = txtadd.getText();
		
		String empid = (String)cmbemp.getSelectedItem();
		
		
		if(empid.equalsIgnoreCase("Select Employee ID"))
		{
			JOptionPane.showMessageDialog(this,"Please select Employee ID");
		}
		
		else
		{
			
			Connection con = DBConnection.openConnection();
			PreparedStatement ps = null;
			
			String updateQuery = "update employee set Name=?,Email=?, Phone=?, Department=?, Designation=?, Address=? where ID=?";
			
			try
			{
				
				ps = con.prepareStatement(updateQuery);
				
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, phone);
				ps.setString(4, dept);
				ps.setString(5, desig);
				ps.setString(6, add);
				ps.setString(7, empid);
				
				int status = ps.executeUpdate();
				
				if(status > 0)
					JOptionPane.showMessageDialog(this, empid+" Employee details updated successfully");
				
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
			
		}
		
		
		
		
		
		
		if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || dept.isEmpty() || desig.isEmpty() || add.isEmpty()) 
		{
			JOptionPane.showMessageDialog(this, "All fields are mandatory");
		}
	
		else {
	            if(phone.length()>10 || phone.length()<10)
					
					JOptionPane.showMessageDialog(this, "PhoneNumber must have 10 digits");
				
				else if(email.indexOf('@')==-1 || email.indexOf(".")==-1)
				{
					JOptionPane.showMessageDialog(this, "Invalid email format");
				}
			}
		}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c=e.getKeyChar();
		if(e.getSource()==txtname || e.getSource()==txtdept || e.getSource()==txtdesig ||e.getSource()==txtadd )
		{
		if(!(Character.isAlphabetic(c) || c==KeyEvent.VK_BACK_SPACE ||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_SPACE))
		{
			JOptionPane.showMessageDialog(this, "Only alphabets allowed");
			   e.consume();
		}
		
		}
		
		if(e.getSource()==txtphone)
		{
			if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE ||c==KeyEvent.VK_DELETE ))
			{
				JOptionPane.showMessageDialog(this, "Only numbers allowed");
				   e.consume();
			}	
		}
		
		if(e.getSource()==txtphone)
		{
			if(!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE ||c==KeyEvent.VK_DELETE ))
			{
				JOptionPane.showMessageDialog(this, "Only numbers allowed");
				   e.consume();
			}	
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		String name = null;
		
		if(e.getStateChange() == 2)
		{
			
			name = (String)cmbemp.getSelectedItem();
			
			Connection con = DBConnection.openConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String selectQuery = "select * from employee where ID = ?";
			
			try
			{
				
				ps = con.prepareStatement(selectQuery);
				ps.setString(1, name);
				
				rs = ps.executeQuery();
				
				rs.next();
				
				txtname.setText(rs.getString("Name"));
			    txtphone.setText(rs.getString("Phone"));
			    txtemail.setText(rs.getString("Email"));
			    txtdept.setText(rs.getString("Department"));
			    txtdesig.setText(rs.getString("Designation"));
			    txtadd.setText(rs.getString("Address"));
				
				
				
			}
			catch(SQLException se)
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
