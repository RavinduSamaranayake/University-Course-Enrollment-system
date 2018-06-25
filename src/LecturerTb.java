
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class LecturerTb {
    String lecid,email,fname,lname;
    Connection con = null;
    PreparedStatement ps = null;
    public LecturerTb(){
        con = MyConnection.getconnection();
    }
    public void fillLecTable(JTable table,String searchVal){
        try {
            ps = con.prepareStatement("SELECT `empid`, `fname`, `lname`, `email` FROM `lecture` WHERE CONCAT(  `fname` ,  `lname` ,  `email` ) LIKE ?");
            ps.setString(1, "%"+searchVal+"%");
  
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){
            row = new Object[4];
            row[0] = rs.getString(1);
            row[1] = rs.getString(2);
            row[2] = rs.getString(3);
            row[3] = rs.getString(4);
            
            model.addRow(row);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(StudentTb.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
            
         
    }
    public void updateDelTable(char c,String fn,String ln,String em,String lid){
        fname = fn;
        lname = ln;
        email = em;
        lecid = lid;
        
        if(c=='u'){
            try {
                ps = con.prepareStatement("UPDATE `lecture` SET  `fname`=?,`lname`=?,`email`=? WHERE `empid`=?");
                ps.setString(1,fname);
                ps.setString(2,lname);
                ps.setString(3,email);
                ps.setString(4,lecid); 
                if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Updated Lecture");  
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if(c=='d'){
            try {
                ps = con.prepareStatement("DELETE FROM `lecture` WHERE `empid`=?");
                ps.setString(1,lecid);
                if(ps.executeUpdate()>0){
                  JOptionPane.showMessageDialog(null, "Deleted Lecturer");
                }
            } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
     public void fillLecAllocateTable(JTable table,String Val){
        try {
            ps = con.prepareStatement("SELECT `subid`, `subname`,`location` FROM `subjects` WHERE `lecid` = ?");
            ps.setString(1,Val);
  
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){
            row = new Object[3];
            row[0] = rs.getString(1);
            row[1] = rs.getString(2);
            row[2] = rs.getString(3);
             
            
            model.addRow(row);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(StudentTb.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
            
         
    }
}
