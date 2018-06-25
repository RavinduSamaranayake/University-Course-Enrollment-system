
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
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

public class ResultTb {
    String stid,subid,finalgrade;
    int presentage,finalmark,numofassign,assignavgmark,assignmark,exammark;
    Connection con = null;
    PreparedStatement ps = null;
    public ResultTb(){
        con = MyConnection.getconnection();
    }
    public void AddUpdateAssignTb(char ch,String stid,String subid,int numofassign,int assignavgmark){
    this.stid=stid;
    this.subid=subid;
    this.numofassign = numofassign;
    this.assignavgmark=assignavgmark;
    if(ch=='i'){
        try {
            
           ps = con.prepareStatement("INSERT INTO `subresults`(`studentid`, `subjectid`, `compassign`, `marks`) VALUES (?,?,?,?)");
           ps.setString(1, this.stid);
           ps.setString(2, this.subid);
           ps.setInt(3, this.numofassign);
           ps.setInt(4, this.assignavgmark);
           if(ps.executeUpdate()>0){
           JOptionPane.showMessageDialog(null,"Sucessfully Added");
           }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
           
        }
    }
    else if(ch=='u'){
        try {
            ps = con.prepareStatement("UPDATE `subresults` SET `compassign`=?,`marks`=? WHERE `studentid`=? AND `subjectid`= ?");
           ps.setString(3, this.stid);
           ps.setString(4, this.subid);
           ps.setInt(1, this.numofassign);
           ps.setInt(2, this.assignavgmark);
           if(ps.executeUpdate()>0){
           JOptionPane.showMessageDialog(null,"Sucessfully Updated");
           }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    }
    public void fillInsTable(JTable table,String searchVal){
        try {
            ps = con.prepareStatement("SELECT `studentid`, `subjectid`, `compassign`, `marks` FROM `subresults` WHERE CONCAT(`studentid`, `subjectid` ) LIKE ?");
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
    public void fillbl(String sid,String subid,JLabel lbl){
        try {
            ps = con.prepareStatement("SELECT `studentid`, `subjectid`, `compassign`, `marks` FROM `subresults` WHERE `studentid` = ? AND `subjectid`= ?");
            ps.setString(1, sid);
            ps.setString(2, subid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            String val = Integer.toString(rs.getInt(4));
            lbl.setText(val);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ResultTb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AddUpdateExamTb(char ch,String stid,String subid,int assignmark,int exammark, int presenatge,int finalmark,String finalgrade){
    this.stid=stid;
    this.subid=subid;
    this.assignmark=assignmark;
    this.exammark=exammark;
    this.presentage = presenatge;
    this.finalmark=finalmark;
    this.finalgrade=finalgrade;
    if(ch=='i'){
        try {
            
           ps = con.prepareStatement("INSERT INTO `examresults`(`stid`, `subjectid`, `assignmentmrk`, `exammark`, `exampresentage`, `finalmark`, `finalgrade`) VALUES (?,?,?,?,?,?,?)");
           ps.setString(1, this.stid);
           ps.setString(2, this.subid);
           ps.setInt(3, this.assignmark);
            ps.setInt(4, this.exammark);
             ps.setInt(5, this.presentage);
           ps.setInt(6, this.finalmark);
           ps.setString(7, this.finalgrade);
           
           if(ps.executeUpdate()>0){
           JOptionPane.showMessageDialog(null,"Sucessfully Added");
           }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
           
        }
    }
    else if(ch=='u'){
        try {
           ps = con.prepareStatement("UPDATE `examresults` SET `exammark`=?,`exampresentage`=?,`finalmark`=?,`finalgrade`=? WHERE `stid`=? AND `subjectid`=?");
           ps.setString(5, this.stid);
           ps.setString(6, this.subid);
           ps.setInt(1, this.exammark);
           ps.setInt(2, this.presentage);
           ps.setInt(3, this.finalmark);
           ps.setString(4, this.finalgrade);
           if(ps.executeUpdate()>0){
           JOptionPane.showMessageDialog(null,"Sucessfully Updated");
           }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    }
     public void fillxmTable(JTable table,String searchVal){
        try {
            ps = con.prepareStatement("SELECT `stid`, `subjectid`, `assignmentmrk`, `exammark`, `exampresentage`, `finalmark`, `finalgrade` FROM `examresults` WHERE CONCAT(`stid`, `subjectid` ) LIKE ?");
            ps.setString(1, "%"+searchVal+"%");
  
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){
            row = new Object[7];
            row[0] = rs.getString(1);
            row[1] = rs.getString(2);
            row[2] = rs.getInt(3);
            row[3] = rs.getInt(4);
            row[4] = rs.getInt(5);
            row[5] = rs.getInt(6);
            row[6] = rs.getString(7);
            
            
            model.addRow(row);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(StudentTb.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.getMessage());
             JOptionPane.showMessageDialog(null, "some problem");
        }
        
            
         
    }
      public void fillResultsheet(JTable table,String stid,int yr){
        try {
            ps = con.prepareStatement("SELECT subjects.`semid`,subjects.`subid`, subjects.`subname`,examresults.`finalgrade`   FROM `subjects`,`examresults`,`semesters` WHERE subjects.`subid`=examresults.`subjectid` AND  semesters.`semid`=subjects.`semid` AND examresults.`stid`=? AND semesters.`year`=?");
            ps.setString(1,stid);
            ps.setInt(2,yr);
  
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
            //JOptionPane.showMessageDialog(null, ex.getMessage());
             JOptionPane.showMessageDialog(null, "some problem");
        }
        
            
         
    }
    
    
}
