package perpus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Member{
    //atribut object petugas
    public String idMember, nama, alamat;

    //field table petugas
    protected String table = "member";
    protected String primaryKey = "idMember";
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
                    String idMember = data.getString("idMember");
                    String nama = data.getString("nama");
                    String alamat = data.getString("alamat");
                    
                    System.out.println("Id Member   : " + idMember);
                    System.out.println("Nama        : " + nama);
                    System.out.println("Alamat      : " + alamat+"\n");
                }
        } catch (SQLException e) {
            System.out.println("data member gagal ditampilkan !");
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
                    this.idMember = data.getString("idMember");
                    this.nama = data.getString("nama");
                    this.alamat = data.getString("alamat");
                   
                }
          
        } catch (SQLException e) {
            System.out.println("pegawai tidak ada !");
            System.out.println(e.getMessage());
        }
    }

    public void insertData(String idMember, String nama, String alamat){
        String sql = "INSERT INTO"+this.table+" values('"+idMember+"','"+nama+"','"+alamat+"');";
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
        String sql = "UPDATE "+this.table+" SET "+fields[0]+"='"+idMember+"',"+fields[1]+"='"+nama+"',"+fields[2]+"="+alamat+" WHERE "+this.primaryKey+"='"+pk+"';";
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
        Member member = new Member();
        // pegawai.getRow("M003");
        // System.out.println(pegawai.nik);
        // System.out.println(pegawai.nama);
        // System.out.println(pegawai.alamat);
        member.getRow("MEM001");
    }
}