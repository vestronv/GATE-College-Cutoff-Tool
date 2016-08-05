package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class queryDB extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnExecute;
	private Connection conn=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//queryDB frame = new queryDB();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public queryDB(Connection connn) {
		conn=connn;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter SQL query below :");
		lblNewLabel.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(116, 105, 287, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Courier 10 Pitch", Font.BOLD, 16));
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setForeground(Color.RED);
		textField.setBounds(70, 170, 800, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnExecute = new JButton("EXECUTE");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = textField.getText();
				try{
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rst = pst.executeQuery();
					if(rst.next()){
						JOptionPane.showMessageDialog(null, "Query was successfull.");
					}else{
						JOptionPane.showMessageDialog(null, "Some Error.");
					}
				}catch(Exception ex1){
					JOptionPane.showMessageDialog(null, "Error : "+ex1);
				}
			}
		});
		btnExecute.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		btnExecute.setBackground(Color.GRAY);
		btnExecute.setForeground(Color.RED);
		btnExecute.setBounds(370, 220, 117, 25);
		contentPane.add(btnExecute);
		
		
	}
}
