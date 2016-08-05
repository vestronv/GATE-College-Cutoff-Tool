package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class firstMenu extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	static public String userName="";
	static Connection conn=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					firstMenu frame = new firstMenu();
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
	public firstMenu() {
		frame = new JFrame();
		frame.setTitle("WELCOME GATE ASPIRANT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(Color.RED);
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblIfYou = new JLabel("<html>TIP : If you haven't filled basic information(GATE Score,etc.) "
				+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;choose 'EDIT' option.</html>");
		lblIfYou.setBounds(2, 360, 545, 30);
		lblIfYou.setFont(new Font("Courier 10 Pitch", Font.BOLD, 13));
		lblIfYou.setForeground(Color.BLUE);
		contentPane.add(lblIfYou);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.setBackground(Color.DARK_GRAY);
		btnEdit.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		btnEdit.setForeground(Color.RED);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//frame.dispose();
					editInfo eobj = new editInfo(userName,conn);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnEdit.setBounds(250, 200, 120, 25);
		contentPane.add(btnEdit);
		
		JButton btnNext = new JButton("NEXT");
		btnNext.setBackground(Color.DARK_GRAY);
		btnNext.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		btnNext.setForeground(Color.RED);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "SELECT * FROM `yourInfo` WHERE `USER_NAME`=\""+userName+"\" ;";//check if no name than off-course it's not filled...
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					if(!rs.next()){
						JOptionPane.showMessageDialog(null, "Can't proceed edit your GATE details.");
					}
					else{
						//frame.dispose();
						//the big stuff...
						indexFrame obj = new indexFrame(userName,conn);
					}
				}
				catch(Exception efm){
					JOptionPane.showMessageDialog(null, efm);
				}
			}
		});
		btnNext.setBounds(250, 250, 120, 25);
		contentPane.add(btnNext);
		
		JLabel lblEdit = new JLabel("Edit your GATE Details :");
		lblEdit.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		lblEdit.setForeground(Color.YELLOW);
		lblEdit.setBounds(20, 200, 200, 15);
		contentPane.add(lblEdit);
		
		JLabel lblHello = new JLabel("Hello "+userName);
		lblHello.setBounds(450, 35, 100, 15);
		contentPane.add(lblHello);
		
		JButton btnAdmin = new JButton("ADMIN");
		btnAdmin.setBackground(Color.DARK_GRAY);
		btnAdmin.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		btnAdmin.setForeground(Color.RED);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				constructionSite ibj = new constructionSite(userName,conn);
			}
		});
		btnAdmin.setBounds(250, 300, 120, 25);
		contentPane.add(btnAdmin);
		
		ImageIcon bimage = new ImageIcon(globalVars.curDir+"/images/Gate_exam.jpg");
		JLabel background = new JLabel(bimage);
		contentPane.add(background);
		background.setSize(600, 200);
		
		JLabel lblSqlQuery = new JLabel("SQL Query :");
		lblSqlQuery.setFont(new Font("Courier 10 Pitch", Font.BOLD, 14));
		lblSqlQuery.setForeground(Color.YELLOW);
		lblSqlQuery.setBounds(477, 234, 111, 15);
		contentPane.add(lblSqlQuery);
		
		JButton btnSql = new JButton("SQL");
		btnSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				queryDB obj = new queryDB(conn);
				obj.setTitle("QUERY DATABSE, ON YOUR OWN RISK!!");
				obj.setVisible(true);
			}
		});
		btnSql.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		btnSql.setBackground(Color.DARK_GRAY);
		btnSql.setForeground(Color.RED);
		btnSql.setToolTipText("CAUTION !!! Here you can enter any SQL query to DB and update DB the way you want. Purspose is to interact with DB in failure or similar conditions.");
		btnSql.setBounds(471, 249, 117, 25);
		contentPane.add(btnSql);
	}
	public firstMenu(String setUserName,Connection setConn) {
		userName = setUserName;
		conn = setConn;
		firstMenu window = new firstMenu();
		window.setVisible(true);
	}
}
//------------------------------------------------------- YO VIMAL --------------------------------------------------------------