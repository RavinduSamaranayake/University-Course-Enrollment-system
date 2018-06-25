
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

 
public class fillCombo {
   Connection con = null;
   PreparedStatement ps = null;
   ResultSet rs = null;
   public fillCombo(){
   con = MyConnection.getconnection();
   }
   public void fillcombo1(){
       try {
           ps = con.prepareStatement("SELECT * FROM `lecture`");
           rs = ps.executeQuery();
           while(rs.next()){
           String id = rs.getString(1);
           AddCourse.combLecId.addItem(id);
           
           }
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
   }
   public void fillStrItems(JComboBox combo,String q,int Index){
       try {
           ps = con.prepareStatement(q);
           rs = ps.executeQuery();
           while(rs.next()){
           String id = rs.getString(Index);
           combo.addItem(id);
           
           }
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
   }
   public void fillIntItems(JComboBox combo,String q,int Index){
       try {
           ps = con.prepareStatement(q);
           rs = ps.executeQuery();
           while(rs.next()){
           int id = rs.getInt(Index);
           combo.addItem(id);
           
           }
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
   }
   public void fillStrItems(JComboBox combo,String q,int Index,int num,String val ){
       try {
           ps = con.prepareStatement(q);
           ps.setString(num, val);
           rs = ps.executeQuery();
           while(rs.next()){
           String id = rs.getString(Index);
           combo.addItem(id);
           
           }
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
   }
}
