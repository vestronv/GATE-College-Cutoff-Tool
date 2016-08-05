package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;

public class testingImages extends JFrame {

	private JPanel contentPane;
	private ImageIcon format = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testingImages frame = new testingImages();
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
	Connection conn=SQLConnector.dbConnector();
	public testingImages() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JDesktopPane desktopPane = new JDesktopPane();
		//desktopPane.setBounds(51, 45, 327, 186);
		//contentPane.add(desktopPane);
		//final JFrame frame1 = new JFrame();
		//frame1.setBounds(51, 45, 327, 186);
		//contentPane.add(frame1);
		
		final JLabel lblNewLabel = new JLabel("New label");
		
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query;
					byte[] img = null;
					File f = new File("/home/vetron/desktop/ian-somerhalder.jpg");
					FileInputStream fis = new FileInputStream(f);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					for(int readnum; (readnum=fis.read(buf))!=-1;){
						bos.write(buf, 0, readnum);
					}
					img = bos.toByteArray();
					query="INSERT INTO `mytable` VALUES(?)";
					
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setBytes(1,img);
					pst.executeUpdate();
					query="SELECT `pic` FROM `mytable`; ";
					PreparedStatement pst1 = conn.prepareStatement(query);
					ResultSet rst = pst1.executeQuery();
					if(rst.next()){
						byte[] imagedata = rst.getBytes("pic");
						format = new ImageIcon(imagedata);
						int ht=format.getIconHeight(),wt=format.getIconWidth();
						lblNewLabel.setBounds(10, 10, wt, ht);
						lblNewLabel.setIcon(format);
						
					}else System.out.println("hi hi");
					
				}catch(Exception ex1){}
			}
		});
		btnNewButton.setBounds(151, 251, 117, 25);
		contentPane.add(btnNewButton);
		
	}
}
