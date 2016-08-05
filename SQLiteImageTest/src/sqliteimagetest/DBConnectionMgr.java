/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqliteimagetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Kanishka
 */
public class DBConnectionMgr {
    private Connection conn=null;
    private String connStrng=null;
    
    public DBConnectionMgr(){
        connStrng=new String("jdbc:sqlite:sampledb.s3db");
    }
    
    public Connection connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn=DriverManager.getConnection(connStrng);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Canno't find SQLite class library!\n"+ex.getMessage(),"Library Error",JOptionPane.ERROR_MESSAGE);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Library Error",JOptionPane.ERROR_MESSAGE);
        }
        return this.conn;
    }
    
    public void close(Connection c){
        if(c!=null){
            try {
                c.close();
            } catch (SQLException ex) {
                
            }
        }
    }
}
