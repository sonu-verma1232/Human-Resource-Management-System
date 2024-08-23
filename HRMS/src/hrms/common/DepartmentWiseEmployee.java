package hrms.common;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import hrms.dbinfo.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;

public class DepartmentWiseEmployee extends JFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane ;
	private JComboBox<String> cmbdept;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentWiseEmployee frame = new DepartmentWiseEmployee();
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
	public DepartmentWiseEmployee() {
		setTitle("Employee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 829, 590);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbdept = new JComboBox();
	    cmbdept.addItemListener(this);
		
		cmbdept.setModel(new DefaultComboBoxModel(new String[] {"Select Department"}));
		cmbdept.setFont(new Font("Calibri", Font.ITALIC, 25));
		cmbdept.setBounds(266, 10, 217, 43);
		fillCombo();
		contentPane.add(cmbdept);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 94, 705, 449);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		
		
	}
	
	
	public void fillCombo()
	{
		
		Connection con = DBConnection.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectQuery = "select dept_name from department ";
		
		try
		{
			
			ps = con.prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			int count = 0;
			
			while(rs.next())
			{
				 count = 1;
				 
				 String name = rs.getString("dept_name");
				 cmbdept.addItem(name);
			}				
			 
			 if(count == 0)
				 JOptionPane.showMessageDialog(this, "There is no department exsist");
			 
			
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
			catch (SQLException se)
			{
				se.printStackTrace();
			}
			
		}
		
	}

   
		
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		String name = null;
		if(e.getStateChange() == 2)
		{
			name = (String)cmbdept.getSelectedItem();
		//	System.out.println("Department is "+name);
			
			Connection con = DBConnection.openConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String selectQuery = "select * from employee where Department = ?";
			try
			{
				ps = con.prepareStatement(selectQuery);
				ps.setString(1, name);
				System.out.println(ps);
				rs = ps.executeQuery();
				
				TableModel tm = DbUtils.resultSetToTableModel(rs);
				
				DefaultTableModel dm = (DefaultTableModel) table.getModel();
				
				dm.setRowCount(0);  // to clear previous data from table
				
				int x = tm.getRowCount();
		        
		        
		    if (x==0)
		    {
		    	JOptionPane.showMessageDialog(this, "There is no employee exists in "+name+" department");
		    	
		    	 
		    }
				
		    else
		    {
		    	
                
				
				table.setModel(tm); 
				scrollPane.setViewportView(table);
			    
		    } 
			}
			catch (SQLException se)
			{
				se.printStackTrace();
			}
		}
	}

	
	
		
		
	
	
	

}
