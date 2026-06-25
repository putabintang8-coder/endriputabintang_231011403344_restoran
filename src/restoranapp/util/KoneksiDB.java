package restoranapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Koneksi Database MySQL via JDBC
 * Database: db_restoran_231011403344
 * Tugas Akhir - Endri Puta Bintang - 231011403344
 *
 * CATATAN PENTING:
 * Sesuaikan USER dan PASSWORD MySQL lo di bawah ini.
 * Default XAMPP: user = "root", password = "" (kosong)
 * Default Laragon: user = "root", password = "" (kosong)
 */
public class KoneksiDB {

    private static final String URL = "jdbc:mysql://localhost:3306/db_restoran_231011403344";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // ganti sesuai konfigurasi MySQL lo

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Driver MySQL JDBC tidak ditemukan.\n"
                    + "Pastikan file mysql-connector-j-x.x.x.jar sudah ditambahkan ke library project.\n\n"
                    + "Detail: " + e.getMessage(),
                    "Error Driver", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal konek ke database.\n"
                    + "Pastikan:\n"
                    + "1. MySQL (XAMPP/Laragon) sudah running\n"
                    + "2. Database 'db_restoran_231011403344' sudah dibuat (jalankan file .sql)\n"
                    + "3. Username & password di KoneksiDB.java sudah benar\n\n"
                    + "Detail: " + e.getMessage(),
                    "Error Koneksi", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
