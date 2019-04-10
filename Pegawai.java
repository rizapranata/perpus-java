package perpus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Pegawai{
    //atribut object petugas
    public String nik, nama, alamat;

    //field table petugas
    protected String table = "pegawai";
    protected String primaryKey = "nik";
    //belum dipakai
    protected String[] fields = {"nama","alamat"};
    
    //function
    public Connection connect(){
        Connection koneksi = null;
        String db_path = "jdbc:sqlite:DB/db_perpus.db";
        try{
            koneksi = DriverManager.getConnection(db_path);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return koneksi;
    }

    public void getAll(){
        String sql = "SELECT * FROM "+this.table+" ORDER BY "+primaryKey+" ASC;";
        try(
            Connection konek = this.connect();
            Statement stmt = konek.createStatement();
            ResultSet data = stmt.executeQuery(sql);
            ) {
                //loop
                while(data.next()){
                    String nik = data.getString("nik");
                    String nama = data.getString("nama");
                    String alamat = data.getString("alamat");
                    
                    System.out.println("NIK         : " + nik);
                    System.out.println("Nama        : " + nama);
                    System.out.println("Alamat      : " + alamat+"\n");
                }
        } catch (SQLException e) {
            System.out.println("data pegawai gagal ditampilkan !");
            System.out.println(e.getMessage());
        }
    }

    public void getRow(String PK){
        String sql = "SELECT * FROM "+this.table+" WHERE "+this.primaryKey+" = '"+PK+"' LIMIT 1;";
        try(
            Connection konek = this.connect();
            Statement stmt = konek.createStatement();
            ResultSet data = stmt.executeQuery(sql);
            ) {
                while(data.next()){
                    this.nik = data.getString("nik");
                    this.nama = data.getString("nama");
                    this.alamat = data.getString("alamat");
                   
                }
          
        } catch (SQLException e) {
            System.out.println("pegawai tidak ada !");
            System.out.println(e.getMessage());
        }
    }

    public void insertData(String nik, String nama, String alamat){
        String sql = "INSERT INTO"+this.table+" values('"+nik+"','"+nama+"','"+alamat+"');";
        try(
            Connection konek = this.connect();
            Statement stmt = konek.createStatement()
            ) {
            stmt.execute(sql);
            System.out.println(sql);
            System.out.println("data berhasil disimpan !");
        } catch (SQLException e) {
            System.out.println("data gagal disimpan !");
            System.out.println(e.getMessage());
        }
    }

    public void update(String pk,String nik, String nama, String alamat){
        String sql = "UPDATE "+this.table+" SET "+fields[0]+"='"+nik+"',"+fields[1]+"='"+nama+"',"+fields[3]+"="+alamat+" WHERE "+this.primaryKey+"='"+pk+"';";
        try(
            Connection konek = this.connect();
            Statement stmt = konek.createStatement()
            ) {
            stmt.execute(sql);
            System.out.println(sql);
            System.out.println("data berhasil diubah !");
        } catch (SQLException e) {
            System.out.println("data gagal diubah !");
            System.out.println(e.getMessage());
        }
    }

    public void delete(String pk){
        String sql = "DELETE FROM "+this.table+" WHERE "+this.primaryKey+" = '"+pk+"';";
        try(
            Connection konek = this.connect();
            Statement stmt = konek.createStatement()
            ) {
            stmt.execute(sql);
            System.out.println(sql);
            System.out.println("data berhasil dihapus !");
        } catch (SQLException e) {
            System.out.println("data gagal dihapus !");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        Pegawai pegawai = new Pegawai();
        // pegawai.getRow("M003");
        // System.out.println(pegawai.nik);
        // System.out.println(pegawai.nama);
        // System.out.println(pegawai.alamat);
        pegawai.getAll();
    }
}