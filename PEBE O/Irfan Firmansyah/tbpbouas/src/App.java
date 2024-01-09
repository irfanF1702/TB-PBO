import java.sql.*;
import java.util.Scanner;

public class App {
    static Connection conn;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String pilihan;
        boolean lanjut = true;

        String url = "jdbc:mysql://localhost:33206/tbpbo";
        String user = "root";
        String password = ""; // Add your MySQL password here

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Class Driver ditemukan!");

            proses proses = new proses();

            while (lanjut) {
                System.out.println("----------------------");
                System.out.println("COUNTER PQR");
                proses.waktu();
                System.out.println("----------------------");
                proses.array();

                System.out.print("\nMasukkan Pilihan Anda (1/2/3/4/5): ");
                pilihan = input.next();

                switch (pilihan) {
                    case "1":
                        proses.display();
                        break;
                    case "2":
                        proses.insert();
                        break;
                    case "3":
                        proses.update();
                        break;
                    case "4":
                        proses.delete();
                        break;
                    case "5":
                        proses.search();
                        break;
                    default:
                        System.err.println("\nPilihan tidak ditemukan\nSilakan pilih [1-5]");
                }

                System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
                pilihan = input.next();
                lanjut = pilihan.equalsIgnoreCase("y");
            }

            System.out.println("Program Selesai");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Tidak Berhasil Koneksi: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            input.close();
        }
    }
}
