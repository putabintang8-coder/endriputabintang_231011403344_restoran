package restoranapp;

import restoranapp.view.LoginForm;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Entry point aplikasi
 * Sistem Informasi Restoran (Menu & Pesanan)
 * Tugas Akhir - Endri Puta Bintang - 231011403344
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // jika Nimbus tidak tersedia, lanjut dengan default look and feel
        }

        java.awt.EventQueue.invokeLater(() -> {
            LoginForm login = new LoginForm();
            login.setVisible(true);
        });
    }
}
