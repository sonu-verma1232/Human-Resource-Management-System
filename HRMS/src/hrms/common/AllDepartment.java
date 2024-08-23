package hrms.common;

import java.sql.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import hrms.dbinfo.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AllDepartment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllDepartment frame = new AllDepartment();
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
	public AllDepartment() {
		setTitle("All Department");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 836, 734);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 10, 791, 492);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		JTableHeader header = table.getTableHeader();  // for changing the color of header
		
		header.setBackground(Color.CYAN);
		header.setForeground(Color.RED);
		header.setFont(new Font("Calibri", Font.ITALIC,25));
		
		allDept();
		scrollPane.setViewportView(table);
	}
	
	public void allDept()
	{
		
		Connection con = DBConnection.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectQuery = "select * from department";
		
		try
		{
			
			ps = con.prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			TableModel tm = DbUtils.resultSetToTableModel(rs);
			
			table.setModel(tm);
			
			TableColumnModel tcm = table.getColumnModel();
			
			tcm.getColumn(0).setHeaderValue("Department Name");
			tcm.getColumn(1).setHeaderValue("Head Name");
			tcm.getColumn(2).setHeaderValue("Email ID");
			tcm.getColumn(3).setHeaderValue("Phone Number");
		
			
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
