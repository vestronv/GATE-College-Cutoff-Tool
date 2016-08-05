package com.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import com.gui.globalVars;

public class showColg extends JFrame {

	public JPanel contentPane;
	private JFrame frame;
	private JComboBox roundCB,sessionCB;
	public ImageIcon format=null;
	public JLabel imglbl;
	static String userName="",colgName="";
	static int maxPkg,avgPkg,session=globalVars.session;
	static Connection conn=null;
	int sessionSel=2014;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showColg frame = new showColg();
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
	public void setVars(){
		try{
			String query = "SELECT * FROM `collegeInfo` WHERE `COLLEGE_NAME`=\""+colgName+"\"  ;";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			ResultSetMetaData rsmd = rst.getMetaData();
			
			int columnNumber = rsmd.getColumnCount();
			while(rst.next()){
				for(int i=1; i<=columnNumber; i++){
					if(i==1)colgName = ((String)rst.getObject(i));
					else if(i==2)maxPkg = ((int)rst.getObject(i));
					else if(i==3)avgPkg = ((int)rst.getObject(i));
				}
			}
			
		}catch(Exception ex1){
			JOptionPane.showMessageDialog(null, ex1);
		}
	}
	public void setHeading(){
		JLabel lblCollegeName = new JLabel("Welcome to "+colgName.split("for ")[1]);
		lblCollegeName.setBackground(Color.LIGHT_GRAY);
		lblCollegeName.setFont(new Font("Courier 10 Pitch", Font.BOLD, 22));
		lblCollegeName.setForeground(Color.DARK_GRAY);
		lblCollegeName.setBounds(40, 20, 650, 30);
		contentPane.add(lblCollegeName);
		
		JLabel lblMaxPkg = new JLabel("Maximum Package : "+String.valueOf(maxPkg));
		lblMaxPkg.setBackground(Color.LIGHT_GRAY);
		lblMaxPkg.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblMaxPkg.setForeground(Color.DARK_GRAY);
		lblMaxPkg.setBounds(70, 80, 200, 20);
		contentPane.add(lblMaxPkg);
		
		
		JLabel lblAvgPkg = new JLabel("Average Package : "+String.valueOf(avgPkg));
		lblAvgPkg.setBackground(Color.LIGHT_GRAY);
		lblAvgPkg.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblAvgPkg.setForeground(Color.DARK_GRAY);
		lblAvgPkg.setBounds(70, 110, 200, 20);
		contentPane.add(lblAvgPkg);

	}
	public void roundCCMT(){
		JLabel lblInfo = new JLabel("Select Year and Round for which you want to see cutoff : ");
		lblInfo.setBackground(Color.LIGHT_GRAY);
		lblInfo.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblInfo.setForeground(Color.DARK_GRAY);
		lblInfo.setBounds(30, 220, 580, 20);
		contentPane.add(lblInfo);
		
		JLabel lblSession = new JLabel("Select Year  :");
		lblSession.setBackground(Color.LIGHT_GRAY);
		lblSession.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblSession.setForeground(Color.DARK_GRAY);
		lblSession.setBounds(100, 240, 140, 25);
		contentPane.add(lblSession);
		
		ArrayList<String> sessions = new ArrayList<String>();
		for(int i=2014;i<session;i++)sessions.add(String.valueOf(i));
		sessionCB = new JComboBox(sessions.toArray());
		sessionCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sessionSel=2014;
				Object temp = sessionCB.getSelectedItem();//Integer.parseInt(sessionCB.getName());
				sessionSel = Integer.parseInt(temp.toString());
			}
		});
		sessionCB.setBounds(240, 240, 150, 25);
		contentPane.add(sessionCB);
		
		JLabel lblRounds = new JLabel("Select Round :");
		lblRounds.setBackground(Color.LIGHT_GRAY);
		lblRounds.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblRounds.setForeground(Color.DARK_GRAY);
		lblRounds.setBounds(100, 280, 140, 25);
		contentPane.add(lblRounds);
		
		String[] rounds = new String[]{"1","2","3"};
		roundCB = new JComboBox(rounds);
		roundCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roundSel="1";
				roundSel = roundCB.getSelectedItem().toString();
				try{
					String query = "SELECT `CUTOFF_PIC` from `cutoffPics` WHERE `ROUND`=\""+roundSel+"\" AND `COLLEGE_NAME`=\""
							+colgName+"\" AND `SESSION` = \""+sessionSel+"\" ;";
					//JOptionPane.showMessageDialog(null, query);
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rst = pst.executeQuery();
					if(rst.next()){
						byte[] imagedata = rst.getBytes("CUTOFF_PIC");
						format = new ImageIcon(imagedata);
						String heading=colgName.split("for ")[1];
						colgImg obj = new colgImg(heading,colgName,sessionSel,roundSel,imagedata);
						obj.setTitle(heading+" "+sessionSel+" round "+roundSel);
						obj.setVisible(true);
						//int ht=format.getIconHeight(),wt=format.getIconWidth();
						//imglbl = new JLabel();
						//contentPane.add(imglbl);
						//imglbl.setBounds(10, 10, wt, ht);
						//imglbl.setIcon(format);
					}else{
						System.out.println("rst not equal next.");
					}
				}catch(Exception ex1){
					JOptionPane.showMessageDialog(null, "[+] Image not retrieved\n"+ex1);
				}
			}
		});
		roundCB.setBounds(240, 280, 150, 25);
		contentPane.add(roundCB);
		
		
	}
	public showColg() {
		frame = new JFrame();
		frame.setTitle(colgName.split("for ")[1]);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.LIGHT_GRAY);
		setVars();
		setHeading();
		roundCCMT();
	}
	public showColg(String setUserName, Connection setConn, String setColgName){
		userName = setUserName;
		conn = setConn;
		colgName = setColgName;
		showColg window = new showColg();
		window.setVisible(true);
		
	}
}
//------------------------------------------------------- YO VIMAL --------------------------------------------------------------