package hrms.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import hrms.common.AllDepartment;
import hrms.common.AllEmployee;
import hrms.common.DepartmentWiseEmployee;
import hrms.common.Login;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class AdminDashBoard extends JFrame  {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashBoard frame = new AdminDashBoard();
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
	public AdminDashBoard() {
		getContentPane().setBackground(new Color(149, 255, 179));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Admin DashBoard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {

				 JOptionPane.showMessageDialog(AdminDashBoard.this, "Thank you for Using Me");
                  Login l = new Login();
                  l.setVisible(true);
                 
				
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setBounds(100, 100, 782, 579);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDepartment = new JMenu("Department");
		menuBar.add(mnDepartment);
		
		JMenuItem mi_add = new JMenuItem("New Department");
		
		mi_add.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/hrms/images/add.png")));
		mnDepartment.add(mi_add);
		
		
		mi_add.addActionListener( 
				
				new ActionListener() // anonymous inner class object -> that implements ActionListener
				{

					@Override
					public void actionPerformed(ActionEvent e) {
					
						Department d = new Department();
						d.setVisible(true);
						
					}
					
				}  // local anonymous inner class body
				
				);
		
		
		
		
		JMenuItem mi_edit = new JMenuItem("Edit Department");
		
		mi_edit.addActionListener(
				
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						UpdateDepartment ud = new UpdateDepartment();
						ud.setVisible(true);
						
					}	
					
				}
				
				);
		
		mi_edit.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/hrms/images/update.png")));
		mnDepartment.add(mi_edit);
		
		JMenuItem mi_delete = new JMenuItem("Delete Department");
		
		
		mi_delete.addActionListener(
				
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						DeleteDepartment dd = new DeleteDepartment();
						dd.setVisible(true);
						
					}
				
				}
				
				);
		
		mi_delete.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/hrms/images/delete.png")));
		mnDepartment.add(mi_delete);
		
		
		
		JMenuItem mi_search = new JMenuItem("Search Department");
		
        mi_search.addActionListener(
				
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						SearchDepartment sd = new SearchDepartment();
						sd.setVisible(true);
						
					}
				
				}
				
				);
		
		
		mi_search.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/hrms/images/search.png")));
		mnDepartment.add(mi_search);
		
		JMenu mnreport = new JMenu("Report\r\n");
		menuBar.add(mnreport);
		
		JMenuItem mi_dept = new JMenuItem("All Department");
		
		mi_dept.addActionListener(
				
				new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						
						AllDepartment ad = new AllDepartment();
						ad.setVisible(true);
						
					}
				}
				
				
				);
		
		mi_dept.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/hrms/images/departmentIcon.png")));
		mnreport.add(mi_dept);
		
		JMenuItem mi_emp = new JMenuItem("All Employee");
		
		mi_emp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AllEmployee ae = new AllEmployee();
				ae.setVisible(true);
				
			}
		});
		
		mi_emp.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/hrms/images/employeeIcon.png")));
		mnreport.add(mi_emp);
		
		JMenuItem mi_dept_emp = new JMenuItem("Department WIse Employee");
		
		mi_dept_emp.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						DepartmentWiseEmployee dwe = new DepartmentWiseEmployee();
						dwe.setVisible(true);
					}
				}
				);
		
		
		mi_dept_emp.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/hrms/images/depatmentwiseEmp.png")));
		mnreport.add(mi_dept_emp);
	}

	
}
