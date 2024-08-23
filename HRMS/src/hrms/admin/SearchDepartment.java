package hrms.admin;

import java.awt.event.*;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class SearchDepartment extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtdept;
	private JTextField txthname;
	private JTextField txtphone;
	private JTextField txtemail;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchDepartment frame = new SearchDepartment();
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
	public SearchDepartment() {
		setTitle("Search Department");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 829, 562);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 128));
		contentPane.setBorder(new LineBorder(new Color(0, 128, 128), 2));

		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Department");
		lblNewLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel.setBounds(55, 47, 246, 47);
		contentPane.add(lblNewLabel);
		
		txtdept = new JTextField();
		txtdept.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtdept.setBounds(357, 47, 206, 47);
		contentPane.add(txtdept);
		txtdept.setColumns(10);
		
		JButton btngo = new JButton("Go");
		btngo.addActionListener(this);
		btngo.setFont(new Font("Calibri", Font.ITALIC, 25));
		btngo.setBounds(629, 47, 98, 47);
		contentPane.add(btngo);
		
		JLabel lblNewLabel_1 = new JLabel("Head Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_1.setBounds(136, 181, 233, 47);
		contentPane.add(lblNewLabel_1);
		
		txthname = new JTextField();
		txthname.setEditable(false);
		txthname.setFont(new Font("Calibri", Font.PLAIN, 18));
		txthname.setBounds(412, 181, 246, 47);
		contentPane.add(txthname);
		txthname.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtphone.setEditable(false);
		txtphone.setBounds(412, 379, 246, 47);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_2.setBounds(136, 277, 166, 47);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_3.setBounds(136, 378, 165, 47);
		contentPane.add(lblNewLabel_3);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtemail.setEditable(false);
		txtemail.setColumns(10);
		txtemail.setBounds(412, 277, 246, 47);
		contentPane.add(txtemail);
	}
	
	
	
	public void searchDepartment()
	{
		
		
			
			Connection con = DBConnection.openConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			
            String deptname = txtdept.getText();
            String searchQuery = "select * from department where dept_name = ?";
            
            if(!deptname.isEmpty())
            {
			
			   try
			   { 
				 
				  
				  ps = con.prepareStatement(searchQuery);
				  ps.setString(1, deptname);
				  rs = ps.executeQuery();
				
				  if(rs.next())// to move the cursor in the data set
				  {
					 	  txthname.setText(rs.getString("hod_name"));
						  txtphone.setText(rs.getString("phone"));
						  txtemail.setText(rs.getString("email"));
					 
					  
				  }

				  else {
					  
					  
					  JOptionPane.showMessageDialog(this, "NO such department exists");
					  txtdept.setText("");
					  txtemail.setText("");
					  txthname.setText("");
					  txtphone.setText("");
					  
				  }
					 
					 
				  
				
				
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
		
	

	@Override
	   public void actionPerformed(ActionEvent e) {
		  // TODO Auto-generated method stub
		
		searchDepartment();
		     
	   }
}
