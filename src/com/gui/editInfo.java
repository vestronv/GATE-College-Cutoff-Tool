package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.Color;
import java.awt.Font;

public class editInfo extends JFrame {

	private JPanel contentPane;
	static public String userName="";
	private JFrame frame;
	static Connection conn = null;
	private JTextField firstNameTF;
	private JTextField lastNameTF;
	private JTextField GScoreTF;
	private JTextField GRankTF;
	private JTextField GYearTF;
	private JComboBox GStreamCB;
	String fn="",ln="",gstream="",gcat="";
	Integer gs=null,
			gr=null,
			gy=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editInfo frame = new editInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}

	/**
	 * Create the frame.
	 */
	public editInfo() {
		frame = new JFrame();
		frame.setTitle("Fill your details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 10, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		frame.setResizable(false);
		contentPane.setLayout(null);
		
		JLabel lblHello = new JLabel("HELLO "+userName);
		lblHello.setBackground(Color.LIGHT_GRAY);
		lblHello.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblHello.setForeground(Color.DARK_GRAY);
		lblHello.setBounds(700, 100, 132, 25);
		contentPane.add(lblHello);
		
		JLabel lblFirstName = new JLabel("* First Name :");
		lblFirstName.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblFirstName.setForeground(Color.RED);
		lblFirstName.setBounds(55, 175, 160, 25);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("* Last Name  :");
		lblLastName.setForeground(Color.RED);
		lblLastName.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblLastName.setBounds(55, 230, 160, 25);
		contentPane.add(lblLastName);
		
		firstNameTF = new JTextField();
		firstNameTF.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		firstNameTF.setForeground(Color.BLUE);
		firstNameTF.setBackground(Color.LIGHT_GRAY);
		firstNameTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fn = firstNameTF.getText().toString();
			}
		});
		firstNameTF.setBounds(215, 175, 150, 25);
		contentPane.add(firstNameTF);
		firstNameTF.setColumns(10);
		
		lastNameTF = new JTextField();
		lastNameTF.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lastNameTF.setForeground(Color.BLUE);
		lastNameTF.setBackground(Color.LIGHT_GRAY);
		lastNameTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ln = lastNameTF.getText().toString();
			}
		});
		lastNameTF.setBounds(215, 230, 150, 25);
		contentPane.add(lastNameTF);
		lastNameTF.setColumns(10);
		
		JLabel lblGateScore = new JLabel("* GATE  Score:");
		lblGateScore.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblGateScore.setForeground(Color.RED);
		lblGateScore.setBounds(55, 350, 160, 15);
		contentPane.add(lblGateScore);
		
		GScoreTF = new JTextField();
		GScoreTF.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		GScoreTF.setForeground(Color.MAGENTA);
		GScoreTF.setBackground(Color.LIGHT_GRAY);
		GScoreTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gs = Integer.parseInt(GScoreTF.getText().toString());
			}
		});
		GScoreTF.setBounds(217, 340, 115, 25);
		contentPane.add(GScoreTF);
		GScoreTF.setColumns(10);
		
		JLabel lblGateRank = new JLabel("* GATE Rank  :");
		lblGateRank.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblGateRank.setForeground(Color.RED);
		lblGateRank.setBounds(55, 400, 160, 25);
		contentPane.add(lblGateRank);
		
		GRankTF = new JTextField();
		GRankTF.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		GRankTF.setForeground(Color.MAGENTA);
		GRankTF.setBackground(Color.LIGHT_GRAY);
		GRankTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gr = Integer.parseInt(GRankTF.getText().toString());
			}
		});
		GRankTF.setBounds(217, 395, 115, 25);
		contentPane.add(GRankTF);
		GRankTF.setColumns(10);
		
		JButton btnGateScoreCard = new JButton("<html>GATE SCORE<br>&nbsp;&nbsp;&nbsp;CARD</html>");
		btnGateScoreCard.setFont(new Font("Courier 10 Pitch", Font.BOLD, 16));
		btnGateScoreCard.setForeground(Color.YELLOW);
		btnGateScoreCard.setBackground(Color.DARK_GRAY);
		btnGateScoreCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//upload and save image in DB..
				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(null);
				File file=openFile.getSelectedFile();
				String fileName = file.getAbsolutePath();
				try{
					String query;
					byte[] img = null;
					FileInputStream fis = new FileInputStream(file);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					for(int readnum; (readnum=fis.read(buf))!=-1;){
						bos.write(buf, 0, readnum);
					}
					img = bos.toByteArray();
					query="UPDATE `yourInfo` SET `GATE_SCORE_CARD`=?"
							+" WHERE `USER_NAME`=\""+userName+"\" ;";
							
					JOptionPane.showMessageDialog(null, "[+] "+query);
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setBytes(1,img);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Gate Score Card Uploaded Successfully.");
					//query="SELECT `GATE_SCORE_CARD` FROM `yourInfo` WHERE `USER_NAME`=\""+userName+"\"";
					//PreparedStatement pst1 = conn.prepareStatement(query);
					//ResultSet rst = pst1.executeQuery();
					//ImageIcon format;
					//if(rst.next()){
					//	byte[] imagedata = rst.getBytes("gateScoreCard");
					//	format = new ImageIcon(imagedata);
					//	int ht=format.getIconHeight(),wt=format.getIconWidth();
					//	lblNewLabel.setBounds(10, 10, wt, ht);
					//	lblNewLabel.setIcon(format);
						
					//}else System.out.println("hi hi");
					
				}catch(Exception ex1){
					JOptionPane.showMessageDialog(null, "[+] Gate Score Card not uploaded.\n[+] "+ex1);
					JOptionPane.showMessageDialog(null, "First save your info then upload ScoreCard.");
				}
			}
		});
		btnGateScoreCard.setBounds(600, 190, 175, 45);
		contentPane.add(btnGateScoreCard);
		
		JLabel lblUploadGateScore = new JLabel("Upload GATE Score Card");
		lblUploadGateScore.setFont(new Font("Courier 10 Pitch", Font.BOLD, 13));
		lblUploadGateScore.setForeground(Color.RED);
		lblUploadGateScore.setBounds(600, 235, 190, 25);
		contentPane.add(lblUploadGateScore);
		
		JLabel lblYear = new JLabel("* Year       :");
		lblYear.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblYear.setForeground(Color.RED);
		lblYear.setBounds(55, 455,160, 25);
		contentPane.add(lblYear);
		
		GYearTF = new JTextField();
		GYearTF.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		GYearTF.setForeground(Color.MAGENTA);
		GYearTF.setBackground(Color.LIGHT_GRAY);
		GYearTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gy = Integer.parseInt(GYearTF.getText().toString());
			}
		});
		GYearTF.setBounds(217, 450, 80, 25);
		contentPane.add(GYearTF);
		GYearTF.setColumns(10);
		
		JLabel lblStream = new JLabel("* Stream :");
		lblStream.setForeground(Color.RED);
		lblStream.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblStream.setBounds(582, 400, 117, 25);
		contentPane.add(lblStream);
		
		String[] stream_ = new String[]{"CSE-IT","ECE","ME"};
		final JComboBox GStreamCB = new JComboBox(stream_);
		GStreamCB.setFont(new Font("Courier 10 Pitch", Font.BOLD, 16));
		GStreamCB.setBackground(Color.DARK_GRAY);
		GStreamCB.setForeground(Color.YELLOW);
		GStreamCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gstream = (String)GStreamCB.getSelectedItem();
			}
		});
		GStreamCB.setBounds(700, 395, 100, 24);
		contentPane.add(GStreamCB);
		
		JLabel lblCategory = new JLabel("Category :");
		lblCategory.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblCategory.setForeground(Color.RED);
		lblCategory.setBounds(582, 290, 110, 25);
		contentPane.add(lblCategory);
		
		String[] category_ = new String[] {"GEN","SC","ST","OBC","PWD"};
		final JComboBox GCatCB = new JComboBox(category_);
		GCatCB.setFont(new Font("Courier 10 Pitch", Font.BOLD, 16));
		GCatCB.setBackground(Color.DARK_GRAY);
		GCatCB.setForeground(Color.YELLOW);
		GCatCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gcat = (String)GCatCB.getSelectedItem();
			}
		});
		GCatCB.setBounds(700, 285, 115, 25);
		contentPane.add(GCatCB);
		
		JButton btnNext = new JButton("NEXT");
		btnNext.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		btnNext.setForeground(Color.RED);
		btnNext.setBackground(Color.DARK_GRAY);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check if all fields are filled or not except gate score card.
				
				if((firstNameTF.getText().toString().length() > 1 && lastNameTF.getText().toString().length() > 1
						&& GCatCB.getSelectedItem().toString().length() > 1
						&& isInteger(GScoreTF.getText().toString()) && isInteger(GRankTF.getText().toString())
						&& isInteger(GYearTF.getText().toString()) && GStreamCB.getSelectedItem().toString().length() > 1)){
					//all set let's go...
					//1. fill the DB
					//fn ln gs gr gcat gy gstream
					//actually this query is an update...
					String query;
					//query= "INSERT INTO `yourInfo` VALUES(\""+fn+"\", \""+ln+"\" ,"+
					//gs+", "+gr+", "+gcat+", "+gy+", "+"\""+gstream+"\" \""+userName+"\" );";
					query = "INSERT INTO `yourInfo` VALUES(?,?,?,?,?,?,?,?,?)";
					try{
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, fn);
						pst.setString(2, ln);
						pst.setString(3, userName);
						pst.setInt(4, gs);
						pst.setInt(5, gr);
						pst.setInt(6, gy);
						pst.setString(7, gstream);
						pst.setString(9, gcat);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Changes Saved. Press to proceed.");
						//proceed...
						//frame.dispose();
						frame.setVisible(false);
						indexFrame obj = new indexFrame(userName,conn);
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Fill 'ALL' fields dude.");
				}
			}
		});
		btnNext.setBounds(850, 600, 117, 40);
		contentPane.add(btnNext);
		
		JLabel lblAllFieldsAre = new JLabel("<html><u>All * marked fields are mandatory.</u></html>");
		lblAllFieldsAre.setFont(new Font("Courier 10 Pitch", Font.BOLD, 15));
		lblAllFieldsAre.setForeground(Color.RED);
		lblAllFieldsAre.setBounds(66, 567, 325, 20);
		contentPane.add(lblAllFieldsAre);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setBackground(Color.DARK_GRAY);
		btnSave.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		btnSave.setForeground(Color.RED);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((firstNameTF.getText().toString().length() > 1 && lastNameTF.getText().toString().length() > 1
						&& isInteger(GScoreTF.getText().toString()) && isInteger(GRankTF.getText().toString())
						&& isInteger(GYearTF.getText().toString()) && GStreamCB.getSelectedItem().toString().length() > 1)){
					//all set let's go...
					//1. fill the DB
					String fn=firstNameTF.getText(),ln=lastNameTF.getText(),gstream=GStreamCB.getSelectedItem().toString();
					Integer gs=Integer.parseInt(GScoreTF.getText().toString()),
							gr=Integer.parseInt(GRankTF.getText().toString()),
							gy=Integer.parseInt(GYearTF.getText().toString());
					//fn ln gs gr gy gstream
					String query;
					//query = "INSERT INTO `yourInfo` VALUES(\""+fn+"\", \""+ln+"\" ,"+
					//gs+", "+gr+", "+gy+", "+"\""+gstream+"\" "+");";
					query="INSERT INTO `yourInfo` VALUES(?,?,?,?,?,?,?,?,?)";
					try{
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, fn);
						pst.setString(2, ln);
						pst.setString(3, userName);
						pst.setInt(4, gs);
						pst.setInt(5, gr);
						pst.setInt(6, gy);
						pst.setString(7, gstream);
						pst.setString(9, gcat);
						
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Changes Saved.");
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1);
					}
				}else{
					JOptionPane.showMessageDialog(null, "[+]Fill 'ALL' fields \n[+]Duplicate info now allowed.");
				}
			}
		});
		btnSave.setBounds(622, 430, 117, 47);
		contentPane.add(btnSave);
		
		JLabel lblNewLabel = new JLabel("<html><u>Enter your details</u> :</html>");
		lblNewLabel.setFont(new Font("Courier 10 Pitch", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(60, 60, 335, 25);
		contentPane.add(lblNewLabel);
		
		ImageIcon bimage = new ImageIcon(globalVars.curDir+"/images/page.jpg");
		JLabel background = new JLabel(bimage);
		contentPane.add(background);
		background.setSize(1200, 700);
		
	}
	public editInfo(String setUserName,Connection setConn) {
		userName = setUserName;
		conn = setConn;
		editInfo window = new editInfo();
		window.setVisible(true);
		
	}
}
