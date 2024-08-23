package hrms.hr;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import hrms.dbinfo.DBConnection;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class DeleteEmployee extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtemp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEmployee frame = new DeleteEmployee();
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
	public DeleteEmployee() {
		setTitle("Delete Employee");
		setBackground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 721, 545);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 128));
		contentPane.setBorder(new LineBorder(new Color(0, 128, 0), 2));

		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtemp = new JTextField();
		txtemp.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtemp.setBounds(216, 83, 279, 53);
		contentPane.add(txtemp);
		txtemp.setColumns(10);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(this);
		btndelete.setIcon(new ImageIcon(DeleteEmployee.class.getResource("/hrms/images/delete.png")));
		btndelete.setFont(new Font("Calibri", Font.ITALIC, 25));
		btndelete.setBounds(216, 216, 279, 53);
		contentPane.add(btndelete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		deleteemp();
		
		
	}
	
	public void deleteemp() {
		
		String empId = txtemp.getText();
		
		if(empId.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter employee ID ");
		}
		else
		{
			
			int choice=JOptionPane.showConfirmDialog(this, "Do you really wish to delete "+ empId+"  employee");
			//  System.out.println(choice);
			
			if(choice == 0)
			{
				
				Connection con = DBConnection.openConnection();
				PreparedStatement ps = null;
				// it is used to prepare the query 
				String deleteQuery = "delete from employee where ID = ? ";
				
				try {
					
					ps = con.prepareStatement(deleteQuery);
					ps.setString(1, empId);
					
					System.out.println(ps);
					
					int status = ps.executeUpdate();
					
					if(status>0)
					{
						JOptionPane.showMessageDialog(this, "Data deleted successfully");
					} else {
						JOptionPane.showMessageDialog(this, empId+" does not exists");
					}
					
				} 
				catch (SQLException se) 
				{
					// TODO: handle exception
					se.printStackTrace();
				}
				
				finally {
					
					try {
						
						if(ps!=null)
							ps.close();
						
						if(con!=null)
							con.close();
						
					} catch (SQLException se) {
						// TODO: handle exception
						
						se.printStackTrace();
					}
					
				}
				
			}
		}
	}
}
