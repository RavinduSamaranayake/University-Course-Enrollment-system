
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
public class PracticalTb {
    String crsId,insId,labId;
     
    Connection con = null;
    PreparedStatement ps = null;
    public PracticalTb(){
    con = MyConnection.getconnection();
    }
    public void fillPracTable(JTable table,String searchval){
         try {
            ps = con.prepareStatement("SELECT `labid`, `subid`, `instrucid` FROM `labmanage` WHERE CONCAT(  `subid` ,  `labid` ) LIKE ?");
            ps.setString(1, "%"+searchval+"%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){
            row = new Object[3];
            row[0] = rs.getString(2);
            row[1] = rs.getString(1);
            row[2] = rs.getString(3);
            model.addRow(row);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(StudentTb.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void AddDelUpdate(char ch,String insId,String labId,String crsId){
    this.insId=insId;
    this.labId = labId;
    this.crsId = crsId;
     
    if(ch == 'i'){
        try {
            ps = con.prepareStatement("INSERT INTO `labmanage`(`labid`, `subid`, `instrucid`) VALUES(?,?,?)");
            ps.setString(2, this.crsId);
            ps.setString(1, this.labId);
            ps.setString(3, this.insId);
             
            if(ps.executeUpdate()>0){
            JOptionPane.showMessageDialog(null,"New Lab session is Added");
            //System.out.println("executeUpdate is "+ps.executeUpdate());
            }
           else{
           JOptionPane.showMessageDialog(null, "Not Complete or Insert wrong details");
         }
            
           } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    else if(ch == 'u'){
        try {
            ps = con.prepareStatement(" UPDATE `labmanage` SET `labid`=?,`instrucid`=? WHERE  `subid`=?");
           
            ps.setString(3, this.crsId);
            ps.setString(2, this.insId);
            ps.setString(1, this.labId);
             
            if(ps.executeUpdate()>0){
            JOptionPane.showMessageDialog(null,"Practical Session is Updated");
            }
           else{
           JOptionPane.showMessageDialog(null, "Not Complete or Insert wrong details");
         }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    else if(ch=='d'){
        try {
            ps = con.prepareStatement("DELETE FROM `labmanage` WHERE `labid` = ?");
            ps.setString(1, this.labId);
            if(ps.executeUpdate()>0){
            JOptionPane.showMessageDialog(null,"Lab session is Deleted");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    
    
    }
    
}
