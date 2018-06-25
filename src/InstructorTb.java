
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class InstructorTb {
    String insid,email,fname,lname;
    Connection con = null;
    PreparedStatement ps = null;
    public InstructorTb(){
        con = MyConnection.getconnection();
    }
    public void fillInsTable(JTable table,String searchVal){
        try {
            ps = con.prepareStatement("SELECT `insid`, `fname`, `lname`, `email` FROM `instructor` WHERE CONCAT(  `fname` ,  `lname` ,  `email` ) LIKE ?");
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
        insid = lid;
        
        if(c=='u'){
            try {
                ps = con.prepareStatement("UPDATE `instructor` SET  `fname`=?,`lname`=?,`email`=? WHERE `insid`=?");
                ps.setString(1,fname);
                ps.setString(2,lname);
                ps.setString(3,email);
                ps.setString(4,insid); 
                if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Updated Instructor");  
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        if(c=='d'){
            try {
                ps = con.prepareStatement("DELETE FROM `instructor` WHERE `insid`=?");
                ps.setString(1,insid);
                if(ps.executeUpdate()>0){
                  JOptionPane.showMessageDialog(null, "Deleted Instructor");
                }
            } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    public void fillLabAllocateTable(JTable table,String Val){
        try {
            ps = con.prepareStatement("SELECT subjects.`subid` , subjects.`subname` , labmanage.`labid` FROM  `subjects` ,  `labmanage` WHERE subjects.`subid`=labmanage.`subid` AND labmanage.`instrucid` = ?");
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
