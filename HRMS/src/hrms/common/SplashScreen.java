package hrms.common;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;

public class SplashScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
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
	
	// to load login frame
	
	public void showLogin()
	{
		
		Thread t = new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						
						try
						{
							Thread.sleep(5000);  
							 // after 5 seconds login page will be open
							Login login = new Login();
							login.setVisible(true);
							SplashScreen.this.dispose();
						}
						catch(InterruptedException e)
						{
							e.printStackTrace();
						}
						
					}
				}
				);
		
		t.start();
		
	}
	
	
	public SplashScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HRMS Welcomes You");
		lblNewLabel.setForeground(new Color(255, 128, 128));
		lblNewLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
		lblNewLabel.setBounds(266, 36, 235, 84);
		contentPane.add(lblNewLabel);
		
		
		// to show custom size image on label
		
		// creating ImageIcon object with image
		ImageIcon ic = new ImageIcon(SplashScreen.class.getResource("/hrms/images/employee.jpg"));
		
		
		// for image scalling
		Image i2 = ic.getImage().getScaledInstance(576, 307, Image.SCALE_DEFAULT);
		
		// for convert image -> icon
		ImageIcon ic1 = new ImageIcon(i2);
		
		
		
		
		JLabel lblimage = new JLabel("New label");
		// pass the reference of the icon 
		lblimage.setIcon(ic1);
		lblimage.setBounds(80, 154, 576, 307);
		contentPane.add(lblimage);
		
		showLogin();
		
		
	}
}
