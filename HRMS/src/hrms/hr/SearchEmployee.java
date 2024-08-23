package hrms.hr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.LineBorder;

import hrms.dbinfo.DBConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SearchEmployee extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtphone;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtdesig;
	private JTextField txtdept;
	private JTextField txtadd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchEmployee frame = new SearchEmployee();
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
	public SearchEmployee() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchEmployee.class.getResource("/hrms/images/search.png")));
		setTitle("Search Employee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 727);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 128));
		contentPane.setBorder(new LineBorder(new Color(0, 128, 0), 2));

		
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel.setBounds(133, 28, 164, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblOr.setBounds(288, 75, 164, 37);
		contentPane.add(lblOr);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblPhone.setBounds(133, 136, 164, 37);
		contentPane.add(lblPhone);
		
		txtid = new JTextField();
		txtid.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtid.setBounds(419, 28, 201, 37);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtphone.setColumns(10);
		txtphone.setBounds(419, 136, 201, 37);
		contentPane.add(txtphone);
		
		JButton btngo = new JButton("GO");
		btngo.addActionListener(this);
		btngo.setFont(new Font("Calibri", Font.ITALIC, 25));
		btngo.setBounds(686, 75, 90, 37);
		contentPane.add(btngo);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_1.setBounds(133, 248, 229, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_1_1.setBounds(133, 333, 229, 37);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Designation");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_1_2.setBounds(133, 414, 229, 37);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Department");
		lblNewLabel_1_2_1.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_1_2_1.setBounds(133, 496, 229, 37);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Address");
		lblNewLabel_1_2_1_1.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel_1_2_1_1.setBounds(133, 574, 229, 37);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Cambria", Font.PLAIN, 18));
		txtname.setEditable(false);
		txtname.setBounds(440, 248, 275, 37);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Cambria", Font.PLAIN, 18));
		txtemail.setEditable(false);
		txtemail.setColumns(10);
		txtemail.setBounds(440, 330, 275, 37);
		contentPane.add(txtemail);
		
		txtdesig = new JTextField();
		txtdesig.setFont(new Font("Cambria", Font.PLAIN, 18));
		txtdesig.setEditable(false);
		txtdesig.setColumns(10);
		txtdesig.setBounds(440, 414, 275, 37);
		contentPane.add(txtdesig);
		
		txtdept = new JTextField();
		txtdept.setFont(new Font("Cambria", Font.PLAIN, 18));
		txtdept.setEditable(false);
		txtdept.setColumns(10);
		txtdept.setBounds(440, 496, 275, 37);
		contentPane.add(txtdept);
		
		txtadd = new JTextField();
		txtadd.setFont(new Font("Cambria", Font.PLAIN, 18));
		txtadd.setEditable(false);
		txtadd.setColumns(10);
		txtadd.setBounds(440, 583, 275, 37);
		contentPane.add(txtadd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		searchEmployee();
		
	}
	
	public void searchEmployee()
	{
		
		Connection con = DBConnection.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String id = txtid.getText();
		String phone = txtphone.getText();
		String searchQuery = "select * from employee where ID = ?";
		String searchQuery1 = "select * from employee where Phone = ?";
		
		
			
		if(!(id.isEmpty()))
		{
			
		   try
		   {
			   txtphone.setEditable(false);
			   ps = con.prepareStatement(searchQuery);
			   ps.setString(1,id);
			   rs = ps.executeQuery();
			   
			   if(rs.next())
			   {
				   
				   txtadd.setText(rs.getString("Address"));
				   txtdept.setText(rs.getString("Department"));
				   txtdesig.setText(rs.getString("Designation"));
				   txtemail.setText(rs.getString("Email"));
				   txtname.setText(rs.getString("Name"));
				   txtphone.setText(rs.getString("Phone"));
				   
			   }
			   
			   else {
				   
				   JOptionPane.showMessageDialog(this, "There is no such employee exists whose ID is "+id);
				   txtid.setText("");
				   txtphone.setText("");
				   
			   }	
			
			   
			   
			   
		   }
		   catch(SQLException se)
		   {
			   se.printStackTrace();
		   }
			
		}
		
		
		if(!(phone.isEmpty()))
		{
			
		   try
		   {
			   txtid.setEditable(false);
			   ps = con.prepareStatement(searchQuery1);
			   ps.setString(1,phone);
			   rs = ps.executeQuery();
			   
			   if(rs.next())
			   {
				   
				   txtadd.setText(rs.getString("Address"));
				   txtdept.setText(rs.getString("Department"));
				   txtdesig.setText(rs.getString("Designation"));
				   txtemail.setText(rs.getString("Email"));
				   txtname.setText(rs.getString("Name"));
				   txtid.setText(rs.getString("ID"));
				   
			   }
			   
			   else {
				   
				   JOptionPane.showMessageDialog(this, "There is no such employee exists whose mobile number is "+phone);
				   txtid.setText("");
				   txtphone.setText("");
				   
			   }
			   
			   
			   
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
