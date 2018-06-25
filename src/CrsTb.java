
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

public class CrsTb {
    String facname,semid,crsId,crsName,lecId,location,type;
    int credits,numAssign,duration,cost;
    Connection con = null;
    PreparedStatement ps = null;
    public CrsTb(){
    con = MyConnection.getconnection();
    }
    public void fillCrsTable(JTable table,String searchval){
         try {
            ps = con.prepareStatement("SELECT `subid`, `subname`, `faculty`, `credits`, `numassign`, `cost`, `semid`, `lecid`, `duration`, `location` FROM subjects WHERE CONCAT(  `subid` ,  `subname` ,  `faculty` ) LIKE ?");
            ps.setString(1, "%"+searchval+"%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){
            row = new Object[10];
            row[0] = rs.getString(3);
            row[1] = rs.getString(7);
            row[2] = rs.getString(1);
            row[3] = rs.getString(2);
            row[4] = rs.getInt(4);
            row[5] = rs.getInt(5);
            row[6] = rs.getInt(6);
            row[7] = rs.getString(8);
            row[8] = rs.getInt(9);
            row[9] = rs.getString(10);
            model.addRow(row);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(StudentTb.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void AddDelUpdate(char ch,String facname,String semid,String crsId,String crsName,String lecId, String location,String type, int credits,int numAssign,int duration,int cost){
    this.facname=facname;
    this.semid = semid;
    this.crsId = crsId;
    this.crsName = crsName;
    this.type = type;
    this.lecId = lecId;
    this.location = location;
    this.cost = cost;
    this.numAssign = numAssign;
    this.duration = duration;
    this.credits = credits;
    if(ch == 'i'){
        try {
            ps = con.prepareStatement("INSERT INTO `subjects`(`subid`, `subname`, `faculty`, `type`, `credits`, `numassign`, `cost`, `semid`, `lecid`, `duration`, `location`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, this.crsId);
            ps.setString(2, this.crsName);
            ps.setString(3, this.facname);
            ps.setString(4, this.type);
            ps.setInt(5, this.credits);
            ps.setInt(6, this.numAssign);
            ps.setInt(7, this.cost);
            ps.setString(8, this.semid);
            ps.setString(9, this.lecId);
            ps.setInt(10, this.duration);
            ps.setString(11, this.location);
            if(ps.executeUpdate()>0){
            JOptionPane.showMessageDialog(null,"New Course is Added");
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
            ps = con.prepareStatement("UPDATE `subjects` SET `subname`=?,`faculty`=?,`credits`=?,`numassign`=?,`cost`=?,`semid`=?,`lecid`=?,`duration`=?,`location`=? WHERE `subid`=?");
           
            ps.setString(1, this.crsName);
            ps.setString(2, this.facname);
            ps.setInt(3, this.credits);
            ps.setInt(4, this.numAssign);
            ps.setInt(5, this.cost);
            ps.setString(6, this.semid);
            ps.setString(7, this.lecId);
            ps.setInt(8, this.duration);
            ps.setString(9, this.location);
            ps.setString(10, this.crsId);
            if(ps.executeUpdate()>0){
            JOptionPane.showMessageDialog(null,"Course is Updated");
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
            ps = con.prepareStatement("DELETE FROM `subjects` WHERE `subid` = ?");
            ps.setString(1, this.crsId);
            if(ps.executeUpdate()>0){
            JOptionPane.showMessageDialog(null,"Course is Deleted");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    
    
    }
    public void fillSlctCrsTable(JTable table,String facname,String semid){
         try {
            ps = con.prepareStatement("SELECT `subid`, `subname`, `credits`, `numassign`, `cost`, `duration` FROM subjects WHERE `faculty`=? AND `semid`= ? AND `type`= ? ");
            ps.setString(1,facname);
            ps.setString(2,semid);
            String tpe = "Optional";
            ps.setString(3, tpe);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){
            row = new Object[6];
            row[0] = rs.getString(1);
            row[1] = rs.getString(2);
            row[2] = rs.getString(3);
            row[3] = rs.getString(4);
            row[4] = rs.getString(5);
            row[5] = rs.getString(6);
             
            model.addRow(row);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(StudentTb.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void insertStCrsTable(String sid, String crsid,String semid){
        try {
           
            ps = con.prepareStatement("INSERT INTO `stsub`(`studentid`, `subjectid`, `semid`) VALUES (?,?,?)");
            ps.setString(1, sid);
            ps.setString(2, crsid);
            ps.setString(3, semid);
            if(ps.executeUpdate()>0){
            //JOptionPane.showMessageDialog(null,"Course is Added");
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null,ex.getMessage()); 
            System.out.println(ex.getMessage());
        }
    }
    public void fillCrsDetailTable(JTable table,String stid){
         try {
            ps = con.prepareStatement("SELECT stsub.`semid` , stsub.`subjectid` , subjects.`subname` ,subjects.`type` ,subjects.`credits` , subjects.`numassign` , subjects.`cost` ,  subjects.`duration` , subjects.`lecid` , subjects.`location` FROM  `stsub` ,  `subjects` WHERE stsub.`subjectid` = subjects.`subid` AND stsub.`studentid`=? ORDER BY  `stsub`.`semid` ASC");
            ps.setString(1, stid);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){
            row = new Object[10];
            row[0] = rs.getString(1);
            row[1] = rs.getString(2);
            row[2] = rs.getString(3);
            row[3] = rs.getString(4);
            row[4] = rs.getInt(5);
            row[5] = rs.getInt(6);
            row[6] = rs.getInt(7);
            row[7] = rs.getInt(8);
            row[8] = rs.getString(9);
            row[9] = rs.getString(10);
             
            model.addRow(row);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(StudentTb.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
}
