package perpus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Buku{
    //atribut object buku
    public String isbn, judulBuku, pengarang, penerbit, jumlahHalaman;
    int  jumlahBuku;

    //field table buku
    protected String table = "buku";
    protected String primaryKey = "isbn";
    protected String[] fields = {"judul","penulis","penerbit","stok"};
    
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
                    String isbn = data.getString("isbn");
                    String jdl = data.getString("judul");
                    String penulis = data.getString("penulis");
                    String penerbit = data.getString("penerbit");
                    int stok = data.getInt("stok");

                    System.out.println("ISBN        : " + isbn);
                    System.out.println("Judul       : " + data.getString("judul"));
                    System.out.println("Penulis     : " + penulis);
                    System.out.println("Penerbit    : " + penerbit);
                    System.out.println("Jumlah      : " + stok+"\n");
                }
        } catch (SQLException e) {
            System.out.println("data buku gagal ditampilkan !");
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
                    this.isbn = data.getString("isbn");
                    this.judulBuku = data.getString("judul");
                    this.pengarang = data.getString("penulis");
                    this.pengarang = data.getString("penerbit");
                    this.jumlahBuku = data.getInt("stok");
                }
          
        } catch (SQLException e) {
            System.out.println("buku tidak ada !");
            System.out.println(e.getMessage());
        }
    }

    public void insertData(String isbn, String judul, String pengapenulisrang,String penulis, int stok){
        String sql = "INSERT INTO"+this.table+" values('"+isbn+"','"+judul+"','"+penulis+"','"+penerbit+"',"+stok+");";
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
    //belum diseting
    public void update(String pk,String isbn, String judul, String penulis, String penerbit, int jumlah ){
        String sql = "UPDATE "+this.table+" SET "+fields[0]+"='"+judul+"',"+fields[1]+"='"+penulis+"',"+fields[2]+"='"+penerbit+"',"+fields[3]+"="+stok+" WHERE "+this.primaryKey+"='"+pk+"';";
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
        String sql = "DELETE FROM "+this.table+" WHERE "+this.primaryKey+"' = "+pk+";";
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
        Buku buku = new Buku();
        buku.getAll();
    }
    
}