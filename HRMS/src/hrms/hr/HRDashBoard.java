package hrms.hr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hrms.admin.DeleteDepartment;
import hrms.admin.UpdateDepartment;
import hrms.common.Login;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;

public class HRDashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HRDashBoard frame = new HRDashBoard();
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
	public HRDashBoard() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("HR DashBoard");
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
				// TODO Auto-generated method stub
				
				JOptionPane.showMessageDialog(HRDashBoard.this, "Thanks for using me!");
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
		
		setBounds(100, 100, 810, 524);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(149, 244, 177));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 255, 255));
		panel.setBounds(0, 0, 233, 800);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnadd = new JButton("Add Employee");
		
		btnadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Employee emp = new Employee();
				emp.setVisible(true);
				
			}
		});
		
		btnadd.setFont(new Font("Calibri", Font.ITALIC, 25));
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnadd.setBounds(10, 79, 208, 49);
		panel.add(btnadd);
		
		JButton btndelete = new JButton("Delete Employee");
		
		
		btndelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DeleteEmployee de = new DeleteEmployee();
				de.setVisible(true);
				
				
			}
		});
		
		
		
		btndelete.setFont(new Font("Calibri", Font.ITALIC, 25));
		btndelete.setBounds(10, 272, 208, 49);
		panel.add(btndelete);
		
		JButton btnedit = new JButton("Edit Employee");
		
		
		btnedit.addActionListener(
				new ActionListener(){
			 
					public void actionPerformed(ActionEvent e) {
						
						UpdateEmpoyee ue  = new UpdateEmpoyee();
						ue.setVisible(true);
					}
			
				}
				
				);
		
		
		btnedit.setFont(new Font("Calibri", Font.ITALIC, 25));
		btnedit.setBounds(10, 437, 208, 49);
		panel.add(btnedit);
		
	}
}
