package hrms.admin;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import hrms.dbinfo.DBConnection;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class DeleteDepartment extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteDepartment frame = new DeleteDepartment();
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
	public DeleteDepartment() {
		setTitle("Delete Department");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 791, 457);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 128));
		contentPane.setBorder(new LineBorder(new Color(128, 0, 64), 2));

		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtname.setBounds(221, 37, 295, 46);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(this);
		btndelete.setIcon(new ImageIcon(DeleteDepartment.class.getResource("/hrms/images/delete.png")));
		btndelete.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		btndelete.setBounds(221, 184, 295, 46);
		contentPane.add(btndelete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	//	JOptionPane.showMessageDialog(this, "Data is Empty");
		// System.out.println("Data is Empty");
		deleteData();
		
	}
	
	public void deleteData() {
		
		String deptName = txtname.getText();
		if(deptName.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Name Required");
		}
		else
		{
			
			int choice=JOptionPane.showConfirmDialog(this, "Do you really wish to delete "+ deptName+" department");
			//  System.out.println(choice);
			
			if(choice == 0)
			{
				
				Connection con = DBConnection.openConnection();
				PreparedStatement ps = null;
				// it is used to prepare the query 
				String deleteQuery = "delete from department where dept_name = ? ";
				
				try {
					
					ps = con.prepareStatement(deleteQuery);
					ps.setString(1, deptName);
					
					System.out.println(ps);
					
					int status = ps.executeUpdate();
					
					if(status>0)
					{
						JOptionPane.showMessageDialog(this, "Data deleted successfully");
					} else {
						JOptionPane.showMessageDialog(this, deptName+" does not exists");
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
