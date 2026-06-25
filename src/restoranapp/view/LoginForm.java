package restoranapp.view;

import restoranapp.dao.UserDAO;

import java.awt.*;
import javax.swing.*;

/**
 * Form Login sebelum masuk ke menu utama
 * Tugas Akhir - Endri Puta Bintang - 231011403344
 */
public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnKeluar;
    private final UserDAO userDAO = new UserDAO();

    public LoginForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Login - Sistem Informasi Restoran");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 320);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelUtama = new JPanel();
        panelUtama.setLayout(null);
        panelUtama.setBackground(new Color(245, 245, 245));

        JLabel lblJudul = new JLabel("SISTEM INFORMASI RESTORAN");
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblJudul.setBounds(40, 25, 340, 25);
        panelUtama.add(lblJudul);

        JLabel lblSub = new JLabel("Menu & Pesanan");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(Color.GRAY);
        lblSub.setBounds(40, 50, 340, 20);
        panelUtama.add(lblSub);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 100, 100, 25);
        panelUtama.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(160, 100, 200, 28);
        panelUtama.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 140, 100, 25);
        panelUtama.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(160, 140, 200, 28);
        panelUtama.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(160, 190, 90, 32);
        btnLogin.setBackground(new Color(46, 125, 50));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        panelUtama.add(btnLogin);

        btnKeluar = new JButton("Keluar");
        btnKeluar.setBounds(260, 190, 90, 32);
        panelUtama.add(btnKeluar);

        JLabel lblFooter = new JLabel("Default: admin / admin123");
        lblFooter.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblFooter.setForeground(Color.GRAY);
        lblFooter.setBounds(50, 240, 300, 20);
        panelUtama.add(lblFooter);

        getContentPane().add(panelUtama);

        btnLogin.addActionListener(e -> prosesLogin());
        btnKeluar.addActionListener(e -> System.exit(0));

        txtPassword.addActionListener(e -> prosesLogin());
    }

    private void prosesLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan password wajib diisi!",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean berhasil = userDAO.login(username, password);
        if (berhasil) {
            JOptionPane.showMessageDialog(this, "Login berhasil! Selamat datang, " + username,
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
            MainForm mainForm = new MainForm();
            mainForm.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau password salah!",
                    "Login Gagal", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
        }
    }
}
