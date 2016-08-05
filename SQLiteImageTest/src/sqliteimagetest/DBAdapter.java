/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqliteimagetest;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Kanishka
 * 
 * CREATE TABLE 'imagetable'(
  name TEXT,
  image BLOB
  );
 */
public class DBAdapter {
    DBConnectionMgr DBCM;
    
    public DBAdapter(){
        DBCM=new DBConnectionMgr();
    }
    
        /**private method to get byte array from bytestream
     * *
     * 
     */
    private byte[] getByteArrayFromFile(String filePath){
        byte[] result=null;
        FileInputStream fileInStr=null;
        try{
            File imgFile=new File(filePath);
            fileInStr=new FileInputStream(imgFile);
            long imageSize=imgFile.length();
            
            if(imageSize>Integer.MAX_VALUE){
                return null;    //image is too large
            }
            
            if(imageSize>0){
                result=new byte[(int)imageSize];
                fileInStr.read(result);
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                fileInStr.close();
            } catch (Exception e) {
            }
        }
        return result;
    }
    
    public void addImageToDB(String name,String imageName){
        Connection conn=DBCM.connect();
        String query="INSERT INTO imagetable(name,image) VALUES (?, ?)";
        PreparedStatement prepStmt=null;
        try{
            conn.setAutoCommit(false);
            prepStmt=conn.prepareStatement(query);
            prepStmt.setString(1, name);
            
            byte[] imageFileArr=getByteArrayFromFile(imageName);
            prepStmt.setBytes(2, imageFileArr);
            
            prepStmt.executeUpdate();
            conn.commit();
            JOptionPane.showMessageDialog(null, "Image saved successfully!","Successfull",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                conn.close();
                prepStmt.close();
            } catch (Exception e) {
            }
        }
    }
    
    public Image getImage(String name){
        Image img=null;
        String query="select image from imagetable where name='"+name+"'";
        Connection conn=DBCM.connect();
        Statement stmt=null;
        try{
            stmt=conn.createStatement();
            ResultSet rslt=stmt.executeQuery(query);
            if(rslt.next()){
                byte[] imgArr=rslt.getBytes("image");
                img=Toolkit.getDefaultToolkit().createImage(imgArr);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                conn.close();
                stmt.close();
            } catch (Exception e) {
            }
        }
        
        return img;
    }
    
}
