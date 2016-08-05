package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class indexFrame extends JFrame {

	//################## THIS CLALSS USED TO LET USER SELECT JUST ONE ITEM OF JTABLE :D ##############################
		public class ForcedListSelectionModel extends DefaultListSelectionModel {
			
		    public ForcedListSelectionModel () {
		        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    }
		    
		    @Override
		    public void clearSelection() {
		    }
		    
		    @Override
		    public void removeSelectionInterval(int index0, int index1) {
		    }
		    
		}
	//################################################################################################################
	
	private JPanel contentPane;
	private JFrame frame;
	private JComboBox collegeCB;
	static String userName="";
	static Connection conn=null;
	String collegeT="";
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					indexFrame frame = new indexFrame();
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
	
	public void combobx(){
		String [] collegeType = new String[]{"IIT","NIT/IIIT"};
		//a thing learnt that if u create scroll pane, jtable, etc on every action performed of CB than it might
		//not update it as you wanted so pre declare it.. :)
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 265, 600, 308);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		collegeCB = new JComboBox(collegeType);
		collegeCB.setFont(new Font("Courier 10 Pitch", Font.BOLD, 16));
		collegeCB.setBackground(Color.DARK_GRAY);
		collegeCB.setForeground(Color.YELLOW);
		collegeCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//collegeT = collegeCB.getSelectedItem().toString();
				collegeT = collegeCB.getItemAt(collegeCB.getSelectedIndex()).toString();
				try{
					if(collegeT.length()>2 ){
						//String query1 = "SELECT * FROM `YOUR_GATE_INFO` WHERE `userName`=\""+userName+"\" ;";
						//PreparedStatement pst1 = conn.prepareStatement(query1);
						//ResultSet rst1 = pst1.executeQuery();
						//Array catt_ = rst1.getArray(0);
						//String cat_ = catt_.toString();
						String query = "SELECT `COLLEGE_NAME` "
								//+ ", `minScore`,`avgPackage` "
								+ "FROM `collegeDB` WHERE `COLLEGE_TYPE`=\""+
								collegeT+"\"  "+" ;";
						//JOptionPane.showMessageDialog(null, query+"-"+"-"+collegeT);
						PreparedStatement pst = conn.prepareStatement(query);
						ResultSet rst = pst.executeQuery();
						table.setSelectionModel(new ForcedListSelectionModel());
						table.setModel(DbUtils.resultSetToTableModel(rst));	
					}
					else{
						JOptionPane.showMessageDialog(null, "Select type of college.");
					}	
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
				//refreshTable();
			}
		});
		collegeCB.setBounds(195, 143, 630, 24);
		contentPane.add(collegeCB);
		
	}
	public indexFrame() {
		frame = new JFrame();
		frame.setTitle("IIT/NIT/IIIT College List.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 20, 970, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		frame.setResizable(false);
		contentPane.setLayout(null);
		
		JLabel lblHello = new JLabel("HELLO "+userName);
		lblHello.setFont(new Font("Courier 10 Pitch", Font.BOLD, 16));
		lblHello.setForeground(Color.GRAY);
		lblHello.setBounds(800, 45, 137, 25);
		contentPane.add(lblHello);
		
		combobx();
		
		JLabel lblCollegeType = new JLabel("College Type :");
		lblCollegeType.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		lblCollegeType.setForeground(Color.RED);
		lblCollegeType.setBounds(40, 148, 150, 25);
		contentPane.add(lblCollegeType);
		
		JButton btnView = new JButton("VIEW COLLEGE");
		btnView.setFont(new Font("Courier 10 Pitch", Font.BOLD, 17));
		btnView.setBackground(Color.DARK_GRAY);
		btnView.setForeground(Color.RED);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row_num = table.getSelectedRow();
				if(row_num<0)JOptionPane.showMessageDialog(null, "Select a College.");
				else{
					//show this college info in a new frame.. offcourse...
					String colg_name = table.getValueAt(row_num, 0).toString();
					showColg obj = new showColg(userName,conn,colg_name);
				}
			}
		});
		btnView.setBounds(800, 586, 160, 25);
		contentPane.add(btnView);
		
		ImageIcon bimage = new ImageIcon(globalVars.curDir+"/images/collegeAhead.jpeg");
		JLabel background = new JLabel(bimage);
		contentPane.add(background);
		background.setSize(1000, 700);
		
		
	}
	public indexFrame(String setUserName, Connection setConn) {
		userName = setUserName;
		conn = setConn;
		indexFrame window = new indexFrame();
		window.setVisible(true);
	}
}
//------------------------------------------------------- YO VIMAL --------------------------------------------------------------