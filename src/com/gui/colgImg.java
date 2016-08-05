package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class colgImg extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	public JLabel imglbl;
	public String colgName;
	int sessionSel;
	public byte[] imageData=null;
	public String roundSel,heading;
	ImageIcon format=null;

	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					colgImg frame = new colgImg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	**/
	
	/**
	 * Create the frame.
	 */
	public colgImg() {
		frame = new JFrame();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.LIGHT_GRAY);
		
		//format = new ImageIcon(imageData);
		//int ht=format.getIconHeight(),wt=format.getIconWidth();
		//frame.setBounds(1,1,wt+20, ht+20);
		//imglbl = new JLabel();
		//contentPane.add(imglbl);
		//imglbl.setBounds(10, 10, 400, 400);
		//imglbl.setIcon(format);
	
	}

	public colgImg(String headingg,String colgNamee, int sessionSell, String roundSell, byte[] imageDataa) {
		heading = headingg;
		colgName = colgNamee;
		sessionSel = sessionSell;
		roundSel = roundSell;
		imageData = imageDataa;
		format = new ImageIcon(imageDataa);
		int ht=format.getIconHeight(),wt=format.getIconWidth();
		frame = new JFrame();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, wt+10, ht+10);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.LIGHT_GRAY);
		//frame.setBounds(1,1,wt+20, ht+20);
		imglbl = new JLabel();
		contentPane.add(imglbl);
		imglbl.setBounds(1, 1, wt, ht);
		imglbl.setIcon(format);
			
		//colgImg window = new colgImg();
		//window.setVisible(true);
			
	}

}
//------------------------------------------------------- YO VIMAL --------------------------------------------------------------