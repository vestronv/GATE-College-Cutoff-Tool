package com.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.sql.*;
import java.sql.*;
import java.util.Random;

import javax.swing.*;
import com.gui.globalVars;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login {

	private JFrame frame;
	private JLabel temp;
	private JPasswordField passwordTF;
	private JTextField userNameTF;
	private String userName="",password="";
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("AUTHENTICATE");
		frame.getContentPane().setLayout(null);
		
		final String [] anon = {"We are anonymous.","We don't forget.","We don't forgive.",
				"We are legion.","Expect Us.","*ANONYMOUS*"};
		final Random rand=new Random();
		ImageIcon bimage = new ImageIcon(globalVars.curDir+"/images/login.jpg");
		final JLabel background = new JLabel(bimage);
		
		background.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = rand.nextInt(6);
				frame.setTitle(anon[idx]);
				//temp = new JLabel(anon[idx]);
				//temp.setForeground(Color.RED);
				//temp.setFont(new Font("Courier 10 Pitch", Font.BOLD, 12));
				//temp.setBounds(rand.nextInt(600)+2,rand.nextInt(450)+2,150,10);
				//background.add(temp);
			}
		});
		frame.getContentPane().add(background);
		background.setSize(650, 500);
		
		JLabel lblUserName = new JLabel("User Name :");
		lblUserName.setForeground(Color.RED);
		lblUserName.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblUserName.setBounds(160, 200, 110, 15);
		//frame.getContentPane().add(lblUserName);
		background.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setForeground(Color.RED);
		lblPassword.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblPassword.setBounds(160, 230, 110, 15);
		//frame.getContentPane().add(lblPassword);
		background.add(lblPassword);
		
		userNameTF = new JTextField();
		userNameTF.setForeground(Color.YELLOW);
		userNameTF.setBackground(Color.DARK_GRAY);
		userNameTF.setBounds(300, 200, 120, 20);
		userNameTF.setFont(new Font("Courier 10 Pitch", Font.BOLD, 16));
		//frame.getContentPane().add(userNameTF);
		background.add(userNameTF);
		userNameTF.setColumns(10);
		
		passwordTF = new JPasswordField();
		passwordTF.setForeground(Color.YELLOW);
		passwordTF.setBackground(Color.DARK_GRAY);
		passwordTF.setBounds(300, 230, 120, 20);
		passwordTF.setFont(new Font("Courier 10 Pitch", Font.BOLD, 16));
		//frame.getContentPane().add(passwordTF);
		background.add(passwordTF);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Courier 10 Pitch", Font.BOLD, 20));
		btnLogin.setForeground(Color.RED);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					userName = userNameTF.getText();
					password = String.valueOf(passwordTF.getPassword());
					connection = SQLConnector.dbConnector();
					String query = "SELECT * FROM loginCred where USERNAME='"+userName+"' and PASSWORD='"+password+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					if(!rs.next()){
						JOptionPane.showMessageDialog(null, "Invalid Username and/or Password");
					}else{
						//if i show for limited time faltu ka code bada hoga...
						JOptionPane.showMessageDialog(null, "Logged In");
						frame.dispose();
						firstMenu tmpObj = new firstMenu(userName,connection);
						//firstMenu fmObj = new firstMenu();
						//fmObj.setVisible(true);
						//rs.close();
					}
					//fmObj.setVisible(true);
				}catch(Exception sqlError){
					sqlError.printStackTrace();
					frame.dispose();
				}finally{}
			}
		});
		btnLogin.setBounds(270, 300, 120, 25);
		background.add(btnLogin);
		//frame.getContentPane().add(btnLogin);
	}
}
//------------------------------------------------------- YO VIMAL --------------------------------------------------------------