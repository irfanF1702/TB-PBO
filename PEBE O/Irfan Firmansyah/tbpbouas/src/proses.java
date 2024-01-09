import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//constructor
//inherit dari kelas crud
public class proses extends menu {

    Scanner input = new Scanner(System.in);
    // variable koneksi
    static Connection conn;

    // methode menampilkan menu pilihan paket internet
    public void ListPaket() {
        System.out.println("==============================================");
        System.out.println("=============LIST Paket Internet==============");
        System.out.println("==============================================");
        System.out.println("1.Paket Internet 2GB (1 minggu)  ~  Rp.10.000");
        System.out.println("2.Paket Internet 5GB (1 minggu)  ~  Rp.30.000");
        System.out.println("3.Paket Internet 8GB (2 minggu)  ~  Rp.40.000");
        System.out.println("4.Paket Internet 11GB (1 bulan)  ~  Rp.50.000");
        System.out.println("5.Paket Internet 18GB (1 bulan)  ~  Rp.80.000");
        System.out.println("6.Paket Internet 25GB (1 bulan)  ~  Rp.110.000");
        System.out.println("==============================================\n");
    }

    // method menampilkan menu transaksi 
    public void array() {
        List<String> lis = new ArrayList<String>();
        lis.add("1. Lihat Data Transaksi");
        lis.add("2. Tambah Data Transaksi");
        lis.add("3. Ubah Data Transaksi");
        lis.add("4. Hapus Data Transaksi");
        lis.add("5. Cari Data Transaksi");
        // perulangan
        for (Iterator<String> iterator = lis.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            System.out.println(string);
        }
    }

    // method date
    public void waktu() {
        Date waktu = new Date();
        SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.print(tf.format(waktu));
    }

    @Override
    public void insert() throws SQLException {
        System.out.println("Masukkan Data : ");
        // link database
        String url = "jdbc:mysql://localhost:3306/tbpbo";
        // exception
        try {
            ListPaket();

            // Input NamaPembeli
            System.out.println("Masukkan Nama Pembeli : ");
            NamaPembeli = input.next();

            // Input JenisPaket
            System.out.print("Pilih Jenis Paket(1/2/3/4/5/6) : ");
            JenisPaket = input.nextInt();

        // NamaPaket
        if (JenisPaket == 1) {
            NamaPaket = "Paket Internet 2GB (1 minggu)";
            System.out.println("Paket Internet 2GB (1 minggu)");
        } else if (JenisPaket == 2) {
            NamaPaket = "Paket Internet 5GB (1 minggu)";
            System.out.println("Paket Internet 5GB (1 minggu)");
        } else if (JenisPaket == 3) {
            NamaPaket = "Paket Internet 8GB (2 minggu)";
            System.out.println("Paket Internet 8GB (2 minggu)");
        } else if (JenisPaket == 4) {
            NamaPaket = "Paket Internet 11GB (1 bulan)";
            System.out.println("Paket Internet 11GB (1 bulan)");
        } else if (JenisPaket == 5) {
            NamaPaket = "Paket Internet 18GB (1 bulan)";
            System.out.println("Paket Internet 18GB (1 bulan)");
        } else if (JenisPaket == 6) {
            NamaPaket = "Paket Internet 25GB (1 bulan)";
            System.out.println("Paket Internet 25GB (1 bulan)");
        } else {
            System.out.println("ERROR");
        }

        // Input Jumlah Paket yang akan dibeli
        System.out.print("Masukkan Jumlah Paket yang akan dibeli: ");
        int JumlahPaket = input.nextInt();

        for (int i = 0; i < JumlahPaket; i++) {
            // Tagihan
            if (JenisPaket == 1) {
                Tagihan = 10000;
            } else if (JenisPaket == 2) {
                Tagihan = 30000;
            } else if (JenisPaket == 3) {
                Tagihan = 40000;
            } else if (JenisPaket == 4) {
                Tagihan = 50000;
            } else if (JenisPaket == 5) {
                Tagihan = 80000;
            } else if (JenisPaket == 6) {
                Tagihan = 110000;
            } else {
                System.out.println("Pilihan Paket Tidak tersedia");
            }
        }

            System.out.println("Tagihan: " + Tagihan);

            // Total Tagihan
            // Proses Matematika
            TotalTagihan = JumlahPaket * Tagihan;
            System.out.println("Total Tagihan: " + TotalTagihan);

            // Bayar
            // Bayar
            int Bayar;
            do {
                System.out.print("Masukkan Uang Bayar Pembeli : ");
                Bayar = input.nextInt();
                if (Bayar < TotalTagihan) {
                    System.out.println("Uang Anda Tidak Cukup. Silakan Masukkan Jumlah Uang yang Mencukupi.");
                }
            } while (Bayar < TotalTagihan);

            // Kembalian
            // Proses Matematika
            Kembalian = Bayar - TotalTagihan;
            System.out.println("Kembalian: " + Kembalian);


            // SQL Query masukkan data ke database
            String sql = "INSERT INTO transaksi (NamaPembeli, NamaPaket, JenisPaket, JumlahPaket, Tagihan, TotalTagihan, Bayar, Kembalian) VALUES ('"
                     + NamaPembeli + "','" + NamaPaket + "','" + JenisPaket
                    + "','" + JumlahPaket + "','" + Tagihan + "','" + TotalTagihan + "','" + Bayar + "','" + Kembalian + "')";
            // connect ke database
            conn = DriverManager.getConnection(url, "root", "");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil Menambahkan Data!");
            }
        
        // exception database
        catch (SQLException e) {
             System.err.println("Terjadi Kesalahan Input Data! SQL: " + e.getMessage());
        }
        // exception tipe data inputan
        catch (InputMismatchException e) {
             System.err.println("Inputan Harus Berupa Angka: " + e.getMessage());
        }

    }

    @Override
    public void display() throws SQLException {
        // link database
        String url = "jdbc:mysql://localhost:3306/tbpbo";

        try {
            // SQL Query
            String sql = "SELECT * FROM transaksi";
            // connect ke database
            conn = DriverManager.getConnection(url, "root", "");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                // Menampilkan data di database
                System.out.print("\nIDTransaksi\t: ");
                System.out.print(result.getInt("IDTransaksi"));
                System.out.print("\nNama Pembeli\t: ");
                System.out.print(result.getString("NamaPembeli"));
                System.out.print("\nJenis Paket\t: ");
                System.out.print(result.getInt("JenisPaket"));
                System.out.print("\nNama Paket\t: ");
                System.out.print(result.getString("NamaPaket"));
                System.out.print("\nJumlah Paket\t: ");
                System.out.print(result.getString("JumlahPaket"));
                System.out.print("\nTagihan\t\t: ");
                System.out.print(result.getInt("Tagihan"));
                System.out.print("\nTotal Tagihan\t: ");
                System.out.print(result.getInt("TotalTagihan"));
                System.out.print("\nBayar\t\t: ");
                System.out.print(result.getInt("Bayar"));
                System.out.print("\nKembalian\t: ");
                System.out.print(result.getInt("Kembalian"));
                System.out.print("\n");
            }
        }
        // Exception eror Database
        catch (SQLException e) {
            System.err.println("Terjadi Kesalahan");
        }
        // Exception Program
        catch (InputMismatchException e) {
            System.err.println("Program Error");
        }
    }

    @Override
    public void update() {
    System.out.print("Ubah Data Transaksi");

    try {
        display();
        // link database
        String url = "jdbc:mysql://localhost:3306/tbpbo";
        System.out.print("\nMasukkan IDTransaksi yang akan di ubah : ");
        // Menerima inputan IDTransaksi Baru
        Integer IDTransaksi = Integer.parseInt(input.next());
        // SQL Query
        String sql = "SELECT * FROM transaksi WHERE IDTransaksi = " + IDTransaksi;
        // Connect ke Database
        conn = DriverManager.getConnection(url, "root", "");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        if (result.next()) {

            System.out.print("Nama Pembeli setelah diganti [" + result.getString("NamaPembeli") + "]\t: ");
            String NamaPembeli = input.next();

            // Perbarui data menggunakan pernyataan UPDATE yang benar
            sql = "UPDATE transaksi SET NamaPembeli='" + NamaPembeli + "' WHERE IDTransaksi=" + IDTransaksi;

            if (statement.executeUpdate(sql) > 0) {
                System.out.println("Berhasil memperbaharui data  (IDTransaksi: " + IDTransaksi + ")");
            }
        }
    }
    // SQL Exception
    catch (SQLException e) {
        System.err.println("Terjadi kesalahan dalam mengedit data");
        System.err.println(e.getMessage());
    }
}

    @Override
    public void search() throws SQLException {
    System.out.print("===Cari Data Transaksi====");
    System.out.println("");
    // Link Database
    String url = "jdbc:mysql://localhost:3306/tbpbo";
    try {
        System.out.print("Masukkan IDTransaksi yang ingin dicari : ");
        // Membaca input IDTransaksi dan membersihkan buffer
        Integer keyword = Integer.parseInt(input.next());
        input.nextLine();  // Membersihkan buffer

        // Connect ke Database
        conn = DriverManager.getConnection(url, "root", "");
        Statement statement = conn.createStatement();
        // SQL Query Search(Select)
        String sql = "SELECT * FROM transaksi WHERE IDTransaksi LIKE '%" + keyword + "%'";
        ResultSet result = statement.executeQuery(sql);
        // Cek apakah data ditemukan atau tidak
        boolean dataFound = false;

        // Menampilkan Data di Database yang di cari
        while (result.next()) {
            dataFound = true;
            System.out.print("Data Ditemukan!");
            System.out.print(" ");
            System.out.print("\nIDTransaksi\t: ");
            System.out.print(result.getInt("IDTransaksi"));
            System.out.print("\nNama Pembeli\t: ");
            System.out.print(result.getString("NamaPembeli"));
            System.out.print("\nJenis Paket\t: ");
            System.out.print(result.getInt("JenisPaket"));
            System.out.print("\nNama Paket\t: ");
            System.out.print(result.getString("NamaPaket"));
            System.out.print("\nJumlah Paket\t: ");
            System.out.print(result.getInt("JumlahPaket"));
            System.out.print("\nTagihan\t\t: ");
            System.out.print(result.getInt("Tagihan"));
            System.out.print("\nTotal Tagihan\t: ");
            System.out.print(result.getInt("TotalTagihan"));
            System.out.print("\nBayar\t\t: ");
            System.out.print(result.getInt("Bayar"));
            System.out.print("\nKembalian\t: ");
            System.out.print(result.getInt("Kembalian"));
            System.out.print("\n");
        }

        // Menampilkan pesan jika data tidak ditemukan
        if (!dataFound) {
            System.out.println("Data tidak ditemukan untuk IDTransaksi: " + keyword);
        }
    }
    // Exception SQL
    catch (SQLException e) {
        System.err.println("Terjadi kesalahan");
    }
    // Exception Program
    catch (InputMismatchException e) {
        System.err.println("Program Eror");
    }
}


    @Override
    public void delete() throws SQLException {
        System.out.print("Hapus Data");
        // Link Database
        String url = "jdbc:mysql://localhost:3306/tbpbo";
        try {
            // Memanggil methode display
            display();
            System.out.print("\nMasukan ID Transaksi yang akan Anda Hapus : ");
            Integer IDTransaksi = Integer.parseInt(input.nextLine());
            // SQL Query
            String sql = "DELETE FROM transaksi WHERE IDTransaksi = " + IDTransaksi;
            // Connect ke Database
            conn = DriverManager.getConnection(url, "root", "");
            Statement statement = conn.createStatement();
            // ResultSet result = statement.executeQuery(sql);

            if (statement.executeUpdate(sql) > 0) {
                System.out.println("Berhasil menghapus data ID Transaksi (Nomor " + IDTransaksi + ")");
            }
        }
        // SQL Exception
        catch (SQLException e) {
            System.out.println("Terjadi Kesalahan Dalam Menghapus Data!");
        }
        // Program Exception
        catch (Exception e) {
            System.out.println("Masukkan Data yang Benar!");
        }
    }
}
