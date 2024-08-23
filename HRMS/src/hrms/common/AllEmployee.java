package hrms.common;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import hrms.dbinfo.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AllEmployee extends JFrame {

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
					AllEmployee frame = new AllEmployee();
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
	public AllEmployee() {
		setTitle("All Employee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 781, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 31, 703, 468);
		contentPane.add(scrollPane);
		
		table = new JTable();  
		
		JTableHeader header = table.getTableHeader(); // used to change the property of table header
		header.setBackground(Color.CYAN);
		header.setForeground(Color.RED);
		header.setFont(new Font("calibri", Font.ITALIC,25));
		
		allEmp();
		scrollPane.setViewportView(table);
	}
	
	
	public void allEmp() {
		
		Connection con = DBConnection.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectQuery = "select * from employee";
		
		try
		{
			
			ps = con.prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			TableModel tm = DbUtils.resultSetToTableModel(rs); // used to show table
			
		//	The code snippet you’ve provided is attempting to convert a ResultSet 
		//	(retrieved from a database query) into a TableModel for use with a
		//	JTable in a Java Swing application. The DbUtils.resultSetToTableModel(rs)
		//	method is used to achieve this conversion.
			
			table.setModel(tm);
			
		}
		catch(SQLException se)
		{
			
			se.printStackTrace();
			
		}
		
		
		
	}
	
}
