
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
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

public class StudentTb {
    Connection con = null;
    PreparedStatement ps;
     String stid;
     String fname;
     String lname;
     String semid;
     String facname;
    public StudentTb(){
        con = MyConnection.getconnection();
    }
    public void fillstjtable(JTable table,String searchval){
        
        
        try {
            ps = con.prepareStatement("SELECT  `stid` ,  `fname` ,  `lname` ,  `dob` ,  `email` ,  `facname` ,  `enroldate` ,  `semid` ,  `zscore` ,  `qtype` FROM  `student` WHERE CONCAT(  `fname` ,  `lname` ,  `email` ) LIKE ?");
            ps.setString(1, "%"+searchval+"%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){
            row = new Object[10];
            row[0] = rs.getString(1);
            row[1] = rs.getString(2);
            row[2] = rs.getString(3);
            row[3] = rs.getString(4);
            row[4] = rs.getString(5);
            row[5] = rs.getString(6);
            row[6] = rs.getString(7);
            row[7] = rs.getString(8);
            row[8] = rs.getString(9);
            row[9] = rs.getString(10);
            model.addRow(row);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(StudentTb.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    
    }
    public void setStVal(String id,String pass ){
        try {
                ps = con.prepareStatement("SELECT * FROM  `student` WHERE stid =? AND passwd =?");
                ps.setString(1,id);
                ps.setString(2,pass);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()){
                 
                this.stid = rs.getString(1);
                this.fname = rs.getString(2);
                this.lname = rs.getString(3);
                this.semid = rs.getString(11);
                this.facname = rs.getString(9);
                
                }
            } catch (SQLException ex) {
                //Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
                //System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
    }
}
