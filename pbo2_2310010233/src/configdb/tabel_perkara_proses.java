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
public class tabel_perkara_proses {
    
    private Connection Koneksidb;
    private String username="root";
    private String password="";
    private String dbname="pbo2_2310010233";
    private String urlKoneksi="jdbc:mysql://localhost/"+dbname;
    public String CEK_TAHAPAN, CEK_PROSES, CEK_TANGGAL=null;
    public boolean duplikasi=false;
    
    public tabel_perkara_proses(){
        try {
            Driver dbdriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(dbdriver);
            Koneksidb=DriverManager.getConnection(urlKoneksi,username,password);
            System.out.print("Database Terkoneksi");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    
    public void simpanperkara01(String id, String tahapan, String proses, String tanggal){
        try {
            String sqlsimpan="insert into perkara_proses(id_perkara, tahapan, proses, tanggal) value"
                    + " ('"+id+"', '"+tahapan+"', '"+proses+"', '"+tanggal+"')";
            String sqlcari="select*from perkara_proses where id_perkara='"+id+"'";
            Statement cari=Koneksidb.createStatement();
            ResultSet data=cari.executeQuery(sqlcari);
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID perkara sudah terdaftar :D");
            } else {
                Statement perintah=Koneksidb.createStatement();
                perintah.execute(sqlsimpan);
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
     public void simpanperkara02(String id, String tahapan, String proses, String tanggal){
        try {
            String sqlsimpan="insert into perkara_proses(id_perkara, tahapan, proses, tanggal)"
                    + " value (?, ?, ?, ?)";
            String sqlcari= "select*from perkara_proses where id_perkara=?";
            PreparedStatement cari=Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, id);
            ResultSet data = cari.executeQuery();
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID perkara sudah terdaftar");
                this.duplikasi=true;
                this.CEK_TAHAPAN=data.getString("tahapan");
                this.CEK_PROSES=data.getString("proses");
                this.CEK_TANGGAL=data.getString("tanggal");
            } else {
                this.duplikasi=false;
                this.CEK_TAHAPAN=null;
                this.CEK_PROSES=null;
                this.CEK_TANGGAL=null;
                PreparedStatement perintah=Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, id );
                perintah.setString(2, tahapan);
                perintah.setString(3, proses);
                perintah.setString(4, tanggal);
                perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
     }
        
        public void ubahperkara(String id, String tahapan, String proses, String tanggal){
        try {
            String sqlubah="update perkara_proses set tahapan=?, proses=?, tanggal=? where id_perkara=?";        
                PreparedStatement perintah=Koneksidb.prepareStatement(sqlubah);
                perintah.setString(1, tahapan );
                perintah.setString(2, proses);
                perintah.setString(3, tanggal);
                perintah.setString(4, id);
                perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
         public void hapusperkara(String id){
        try {
            String sqlhapus="delete from perkara_proses where id_perkara=?";        
                PreparedStatement perintah=Koneksidb.prepareStatement(sqlhapus);
                perintah.setString(1, id );
                perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
         
    public void tampilDataperkara(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            modeltabel.addColumn("id_perkara");
            modeltabel.addColumn("tahapan");
            modeltabel.addColumn("proses");
            modeltabel.addColumn("tanggal");
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
