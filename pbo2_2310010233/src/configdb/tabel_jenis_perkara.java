/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configdb;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.sql.ResultSetMetaData;

/**
 *
 * @author ilhm
 */
public class tabel_jenis_perkara {
    
    private Connection Koneksidb;
    private String username="root";
    private String password="";
    private String dbname="pbo2_2310010233";
    private String urlKoneksi="jdbc:mysql://localhost/"+dbname;
    public String CEK_PARENT, CEK_PERKARA, CEK_NAMA=null;
    public boolean duplikasi=false;
    
    public tabel_jenis_perkara(){
        try {
            Driver dbdriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(dbdriver);
            Koneksidb=DriverManager.getConnection(urlKoneksi,username,password);
            System.out.print("Database Terkoneksi");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    
    public void simpanjenis01(String id, String parent, String perkara, String nama){
        try {
            String sqlsimpan="insert into jenis_perkara(id, parent, jenis_perkara_id, jenis_perkara_nama) value"
                    + " ('"+id+"', '"+parent+"', '"+perkara+"', '"+nama+"')";
            String sqlcari="select*from jenis_perkara where id='"+id+"'";
            Statement cari=Koneksidb.createStatement();
            ResultSet data=cari.executeQuery(sqlcari);
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID jenis sudah terdaftar :D");
            } else {
                Statement perintah=Koneksidb.createStatement();
                perintah.execute(sqlsimpan);
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
     public void simpanjenis02(String id, String parent, String perkara, String nama){
        try {
            String sqlsimpan="insert into jenis_perkara(id, parent, jenis_perkara_id, jenis_perkara_nama)"
                    + " value (?, ?, ?, ?)";
            String sqlcari= "select*from jenis_perkara where id=?";
            PreparedStatement cari=Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, id);
            ResultSet data = cari.executeQuery();
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID jenis sudah terdaftar");
                this.duplikasi=true;
                this.CEK_PARENT=data.getString("parent");
                this.CEK_PERKARA=data.getString("perkara");
                this.CEK_NAMA=data.getString("nama");
            } else {
                this.duplikasi=false;
                this.CEK_PARENT=null;
                this.CEK_PERKARA=null;
                this.CEK_NAMA=null;
                PreparedStatement perintah=Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, id );
                perintah.setString(2, parent);
                perintah.setString(3, perkara);
                perintah.setString(4, nama);
                perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
     }
        
        public void ubahjenis(String id, String parent, String perkara, String nama){
        try {
            String sqlubah="update jenis_perkara set parent=?, jenis_perkara_id=?, jenis_perkara_nama=? where id=?";        
                PreparedStatement perintah=Koneksidb.prepareStatement(sqlubah);
                perintah.setString(1, parent );
                perintah.setString(2, perkara);
                perintah.setString(3, nama);
                perintah.setString(4, id);
                perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
         public void hapusjenis(String id){
        try {
            String sqlhapus="delete from jenis_perkara where id=?";        
                PreparedStatement perintah=Koneksidb.prepareStatement(sqlhapus);
                perintah.setString(1, id );
                perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
         
    public void tampilDatajenis  (JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            modeltabel.addColumn("id");
            modeltabel.addColumn("parent");
            modeltabel.addColumn("perkara");
            modeltabel.addColumn("nama");
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }
     
     
    
}
