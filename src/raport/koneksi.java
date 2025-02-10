package raport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksi {
    private final String url = "jdbc:mysql://localhost/eraport";
    private final String username = "root"; // Nama pengguna XAMPP
    private final String password = "";     // Kata sandi XAMPP
    private static Connection con = null;   // Gunakan `static` agar bisa digunakan di mana saja

    public void connect() {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Koneksi Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
        }
    }

    public static Connection getCon() {
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Koneksi belum dibuat. Panggil method connect() terlebih dahulu.");
        }
        return con;
    }
}
