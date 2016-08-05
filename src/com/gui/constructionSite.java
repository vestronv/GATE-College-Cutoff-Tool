package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class constructionSite extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JScrollPane SPProgress;
	static String userName="",curDir=globalVars.curDir;
	String url="";
	int session=globalVars.session,sessionUpd;
	static Connection conn=null;
	private JTextField txtEnterBaseUrl;
	private JButton btnUpdateDb;
	private JTextField TFSession;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					constructionSite frame = new constructionSite();
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
	public Boolean validYear(String year){
		if(year.length()!=4)return false;
		if(year.charAt(0)<'1' || year.charAt(0)>'3')return false;
		for(int i=1;i<4;i++)if(year.charAt(i)>'9' || year.charAt(i)<'0')return false;
		return true;
	}
	public constructionSite() {
		frame = new JFrame();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setTitle("Parse AND/OR Update");
		
		url="";
		txtEnterBaseUrl = new JTextField();
		txtEnterBaseUrl.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		txtEnterBaseUrl.setForeground(Color.GREEN);
		txtEnterBaseUrl.setBackground(Color.DARK_GRAY);
		txtEnterBaseUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				url=txtEnterBaseUrl.getText();
			}
		});
		txtEnterBaseUrl.setText("");
		txtEnterBaseUrl.setBounds(165, 140, 430, 25);
		contentPane.add(txtEnterBaseUrl);
		txtEnterBaseUrl.setColumns(10);
		
		JLabel lblURL = new JLabel("Enter URL :");
		lblURL.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblURL.setForeground(Color.RED);
		lblURL.setBounds(40, 140, 120, 25);
		contentPane.add(lblURL);
		
		
		JButton btnParse = new JButton("Parse");
		btnParse.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		btnParse.setForeground(Color.RED);
		btnParse.setBackground(Color.DARK_GRAY);
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//parse the side by calling python script and store result in DB
				if(url.length()<10){
					JOptionPane.showMessageDialog(null, "Wrong URL.(http://www.abcd.com)");
				}else{
					//python_script(url).. also tell which year was parsed...
					String command = "python "+globalVars.curDir+"/gateWaliScript.py "+url;
					String[] cmd = {
							command
							//"ls"
					};
					/*IMPORT OF TEXT FROM CMD TO DISPLAY AREA HERE NOT WORKING 
					 * IF FIX UN-INDENT 3 SLASHES(///) IN NEXT ABOUT ABOUT 20-30
					 * LINES
					 * */
					//JTextArea display = new JTextArea ( 16, 58 );
				    //display.setEditable ( false ); // set textArea non-editable
				    
				    ///SPProgress = new JScrollPane(display);
					///SPProgress.setBounds(20, 280, 500, 134);
					///contentPane.add(SPProgress);
					///display.append("Progres....");
					try {
						
						Process p = Runtime.getRuntime().exec(command);
						BufferedReader stdInput = new BufferedReader(new 
				                 InputStreamReader(p.getInputStream()));

						BufferedReader stdError = new BufferedReader(new 
						InputStreamReader(p.getErrorStream()));
						//http://cutoffs.aglasem.com/143327
						// read the output from the command
						String s=null;
						System.out.println("Here is the standard output of the command:\n");
						
						while ((s = stdInput.readLine()) != null) {
							///display.append("\n"+s);
							System.out.println(s);
						}
						// read any errors from the attempted command
						System.out.println("Here is the standard error of the command (if any):\n");
						while ((s = stdError.readLine()) != null) {
							//editorPane.setText("[+] Error : "+s);
							///display.append("\n[+] Error : "+s);
							System.out.println("[+] Error : "+s);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnParse.setToolTipText("Ensure that you entered valid URL.");
		btnParse.setBounds(620, 140, 117, 25);
		contentPane.add(btnParse);
		
		JLabel lblEnterYearTo = new JLabel("Enter year to update :");
		lblEnterYearTo.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblEnterYearTo.setForeground(Color.RED);
		lblEnterYearTo.setBounds(150, 250, 240, 25);
		contentPane.add(lblEnterYearTo);
		
		TFSession = new JTextField();
		TFSession.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		TFSession.setBackground(Color.DARK_GRAY);
		TFSession.setForeground(Color.BLUE);
		TFSession.setBounds(385, 250, 115, 25);
		contentPane.add(TFSession);
		TFSession.setColumns(10);
		
		btnUpdateDb = new JButton("Update DB");
		btnUpdateDb.setForeground(Color.RED);
		btnUpdateDb.setBackground(Color.DARK_GRAY);
		btnUpdateDb.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		btnUpdateDb.setToolTipText("Ensure that entered year DB is present. No Abort.");
		btnUpdateDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update DB
				/*
				for folder in folders :
					i=0
					for round-i-file in folder :
						i+=1
						read round-i-file
						insert into table values(folder[IT DENOTES COLG NAME],round-i-file,)
				public static void main(String... args) {
    			File[] files = new File("C:/").listFiles();
   				showFiles(files);
				}

				public static void showFiles(File[] files) {
    				for (File file : files) {
        				if (file.isDirectory()) {
            				System.out.println("Directory: " + file.getName());
            				showFiles(file.listFiles()); // Calls same method again.
        				} else {
            				System.out.println("File: " + file.getName());
        				}
    				}
				}
				*/
				
				if(validYear((TFSession.getText()))){
					//sessionUpd = Integer.parseInt(TFSession.getText());
					String sessionUpd = TFSession.getText();
					File[] folder = new File(curDir + "/gate_data/"+sessionUpd+"/").listFiles();
					for(File colgName : folder ){
						int round=0;
						System.out.println(colgName.getName());
						String query="INSERT INTO `collegeDB` VALUES(?, ?);";
						try{
							PreparedStatement pst1 = conn.prepareStatement(query);
							pst1.setString(1, colgName.getName());
							pst1.setString(2, colgName.getName().startsWith("CCMT")?"NIT/IIIT":"IIT");
							pst1.executeUpdate();
							
							query="INSERT INTO `collegeInfo` VALUES(?, ?)";
							PreparedStatement pst2 = conn.prepareStatement(query);
							pst2.setString(1, colgName.getName());
							pst2.setString(2, colgName.getName().startsWith("CCMT")?"NIT/IIIT":"IIT");
							pst2.executeUpdate();
							
						}catch(Exception ex1){
							//JOptionPane.showMessageDialog(null, ex1);
						}
						if(colgName.isDirectory()){
							for(File roundPic : new File(curDir + "/gate_data/"+sessionUpd+"/"+colgName.getName()).listFiles()){
								//make a query.. uff...
								round+=1;
								query = "INSERT INTO `cutoffPics` VALUES (?, ?, ?, ?) ;  ";
								try{
									PreparedStatement pst = conn.prepareStatement(query);
									pst.setString(1, colgName.getName());
									//in next 9 line i'll convert image to byte array and set prepared stattement for query..
									byte[] img = null;
									FileInputStream fis = new FileInputStream(roundPic);
									ByteArrayOutputStream bos = new ByteArrayOutputStream();
									byte[] buf = new byte[1024];
									for(int readnum; (readnum=fis.read(buf))!=-1;){
										bos.write(buf, 0, readnum);
									}
									img = bos.toByteArray();
									pst.setBytes(2, img);
									pst.setInt(3, round);
									pst.setInt(4, Integer.parseInt(sessionUpd));
									
									pst.executeUpdate();
									
								}catch(Exception ex1){
									JOptionPane.showMessageDialog(null, "[+] "+ex1+"\n[+] Duplicate data.");
								}
							}
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Not a valid year.");
				}
			}
		});
		btnUpdateDb.setBounds(270, 320, 150, 25);
		contentPane.add(btnUpdateDb);
		
		ImageIcon bimage = new ImageIcon(globalVars.curDir+"/images/code.jpg");
		JLabel background = new JLabel(bimage);
		contentPane.add(background);
		background.setSize(bimage.getIconWidth(), bimage.getIconHeight());
		
		
	}
	public constructionSite(String setUserName, Connection setConn) {
		userName=setUserName;
		conn=setConn;
		constructionSite window = new constructionSite();
		window.setVisible(true);
	}
}
//------------------------------------------------------- YO VIMAL --------------------------------------------------------------