package restoranapp.view;

import restoranapp.dao.MenuPesananDAO;
import restoranapp.model.MenuPesanan;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Form utama: CRUD Menu & Pesanan Restoran
 * Tugas Akhir - Endri Puta Bintang - 231011403344
 */
public class MainForm extends JFrame {

    private JTextField txtKode, txtNama, txtHarga, txtJumlah, txtKeterangan, txtCari;
    private JButton btnTambah, btnUbah, btnHapus, btnBersih, btnCari, btnTampilSemua, btnLogout;
    private JComboBox<String> cbUrutKolom;
    private JComboBox<String> cbUrutArah;
    private JTable table;
    private DefaultTableModel model;

    private final MenuPesananDAO dao = new MenuPesananDAO();
    private int idTerpilih = -1;

    public MainForm() {
        initComponents();
        tampilkanSemuaData();
    }

    private void initComponents() {
        setTitle("Sistem Informasi Restoran - Menu & Pesanan | Endri Puta Bintang - 231011403344");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);

        JPanel panelUtama = new JPanel(new BorderLayout(10, 10));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ===== PANEL ATAS: FORM INPUT =====
        JPanel panelForm = new JPanel(null);
        panelForm.setBorder(BorderFactory.createTitledBorder("Data Menu & Pesanan"));
        panelForm.setPreferredSize(new Dimension(950, 190));

        JLabel lblKode = new JLabel("Kode:");
        lblKode.setBounds(20, 30, 100, 25);
        panelForm.add(lblKode);
        txtKode = new JTextField();
        txtKode.setBounds(120, 30, 150, 28);
        panelForm.add(txtKode);

        JLabel lblNama = new JLabel("Nama Menu:");
        lblNama.setBounds(300, 30, 100, 25);
        panelForm.add(lblNama);
        txtNama = new JTextField();
        txtNama.setBounds(400, 30, 280, 28);
        panelForm.add(txtNama);

        JLabel lblHarga = new JLabel("Harga (Rp):");
        lblHarga.setBounds(20, 70, 100, 25);
        panelForm.add(lblHarga);
        txtHarga = new JTextField();
        txtHarga.setBounds(120, 70, 150, 28);
        panelForm.add(txtHarga);
        // validasi: hanya angka yang bisa diketik
        txtHarga.addKeyListener(hanyaAngka());

        JLabel lblJumlah = new JLabel("Jumlah:");
        lblJumlah.setBounds(300, 70, 100, 25);
        panelForm.add(lblJumlah);
        txtJumlah = new JTextField();
        txtJumlah.setBounds(400, 70, 100, 28);
        panelForm.add(txtJumlah);
        txtJumlah.addKeyListener(hanyaAngka());

        JLabel lblKeterangan = new JLabel("Keterangan:");
        lblKeterangan.setBounds(20, 110, 100, 25);
        panelForm.add(lblKeterangan);
        txtKeterangan = new JTextField();
        txtKeterangan.setBounds(120, 110, 560, 28);
        panelForm.add(txtKeterangan);

        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(700, 30, 110, 32);
        btnTambah.setBackground(new Color(46, 125, 50));
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFocusPainted(false);
        panelForm.add(btnTambah);

        btnUbah = new JButton("Ubah");
        btnUbah.setBounds(700, 68, 110, 32);
        btnUbah.setBackground(new Color(255, 152, 0));
        btnUbah.setForeground(Color.WHITE);
        btnUbah.setFocusPainted(false);
        panelForm.add(btnUbah);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(700, 106, 110, 32);
        btnHapus.setBackground(new Color(198, 40, 40));
        btnHapus.setForeground(Color.WHITE);
        btnHapus.setFocusPainted(false);
        panelForm.add(btnHapus);

        btnBersih = new JButton("Bersihkan Form");
        btnBersih.setBounds(820, 30, 110, 32);
        panelForm.add(btnBersih);

        panelUtama.add(panelForm, BorderLayout.NORTH);

        // ===== PANEL TENGAH: TABEL =====
        model = new DefaultTableModel(
                new Object[]{"ID", "Kode", "Nama Menu", "Harga", "Jumlah", "Total", "Keterangan"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setRowHeight(24);
        table.getSelectionModel().addListSelectionListener(e -> isiFormDariTabel());
        JScrollPane scrollPane = new JScrollPane(table);
        panelUtama.add(scrollPane, BorderLayout.CENTER);

        // ===== PANEL BAWAH: PENCARIAN, PENGURUTAN, LOGOUT =====
        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel lblCari = new JLabel("Cari (Nama/Kode):");
        panelBawah.add(lblCari);
        txtCari = new JTextField(15);
        panelBawah.add(txtCari);

        btnCari = new JButton("Cari");
        panelBawah.add(btnCari);

        btnTampilSemua = new JButton("Tampilkan Semua");
        panelBawah.add(btnTampilSemua);

        panelBawah.add(new JLabel("   Urutkan:"));
        cbUrutKolom = new JComboBox<>(new String[]{"ID", "Nama", "Harga", "Jumlah"});
        panelBawah.add(cbUrutKolom);

        cbUrutArah = new JComboBox<>(new String[]{"Naik (A-Z / Kecil-Besar)", "Turun (Z-A / Besar-Kecil)"});
        panelBawah.add(cbUrutArah);

        btnLogout = new JButton("Logout");
        btnLogout.setBackground(new Color(120, 120, 120));
        btnLogout.setForeground(Color.WHITE);
        panelBawah.add(Box.createHorizontalStrut(150));
        panelBawah.add(btnLogout);

        panelUtama.add(panelBawah, BorderLayout.SOUTH);

        getContentPane().add(panelUtama);

        // ===== ACTION LISTENERS =====
        btnTambah.addActionListener(e -> tambahData());
        btnUbah.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnBersih.addActionListener(e -> bersihkanForm());
        btnCari.addActionListener(e -> cariData());
        btnTampilSemua.addActionListener(e -> tampilkanSemuaData());
        btnLogout.addActionListener(e -> logout());
        cbUrutKolom.addActionListener(e -> urutkanData());
        cbUrutArah.addActionListener(e -> urutkanData());
        txtCari.addActionListener(e -> cariData());
    }

    private KeyAdapter hanyaAngka() {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != '.') {
                    e.consume();
                }
            }
        };
    }

    // ================= VALIDASI INPUT =================
    private boolean validasiInput() {
        if (txtKode.getText().trim().isEmpty()) {
            tampilkanPeringatan("Kode tidak boleh kosong!");
            txtKode.requestFocus();
            return false;
        }
        if (txtNama.getText().trim().isEmpty()) {
            tampilkanPeringatan("Nama menu tidak boleh kosong!");
            txtNama.requestFocus();
            return false;
        }
        if (txtHarga.getText().trim().isEmpty()) {
            tampilkanPeringatan("Harga tidak boleh kosong!");
            txtHarga.requestFocus();
            return false;
        }
        if (txtJumlah.getText().trim().isEmpty()) {
            tampilkanPeringatan("Jumlah tidak boleh kosong!");
            txtJumlah.requestFocus();
            return false;
        }
        try {
            double harga = Double.parseDouble(txtHarga.getText().trim());
            if (harga <= 0) {
                tampilkanPeringatan("Harga harus lebih besar dari 0!");
                return false;
            }
        } catch (NumberFormatException ex) {
            tampilkanPeringatan("Harga harus berupa angka!");
            txtHarga.requestFocus();
            return false;
        }
        try {
            int jumlah = Integer.parseInt(txtJumlah.getText().trim());
            if (jumlah < 0) {
                tampilkanPeringatan("Jumlah tidak boleh negatif!");
                return false;
            }
        } catch (NumberFormatException ex) {
            tampilkanPeringatan("Jumlah harus berupa angka bulat!");
            txtJumlah.requestFocus();
            return false;
        }
        return true;
    }

    private void tampilkanPeringatan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Validasi Gagal", JOptionPane.WARNING_MESSAGE);
    }

    // ================= CRUD ACTIONS =================
    private void tambahData() {
        if (!validasiInput()) {
            return;
        }
        if (dao.kodeSudahAda(txtKode.getText().trim())) {
            tampilkanPeringatan("Kode sudah digunakan, gunakan kode lain!");
            return;
        }
        MenuPesanan m = ambilDataDariForm();
        boolean berhasil = dao.tambah(m);
        if (berhasil) {
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            bersihkanForm();
            tampilkanSemuaData();
        }
    }

    private void ubahData() {
        if (idTerpilih == -1) {
            tampilkanPeringatan("Pilih data pada tabel terlebih dahulu untuk diubah!");
            return;
        }
        if (!validasiInput()) {
            return;
        }
        MenuPesanan m = ambilDataDariForm();
        m.setId(idTerpilih);
        boolean berhasil = dao.update(m);
        if (berhasil) {
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
            bersihkanForm();
            tampilkanSemuaData();
        }
    }

    private void hapusData() {
        if (idTerpilih == -1) {
            tampilkanPeringatan("Pilih data pada tabel terlebih dahulu untuk dihapus!");
            return;
        }
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus data ini?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            boolean berhasil = dao.hapus(idTerpilih);
            if (berhasil) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                bersihkanForm();
                tampilkanSemuaData();
            }
        }
    }

    private void cariData() {
        String keyword = txtCari.getText().trim();
        if (keyword.isEmpty()) {
            tampilkanSemuaData();
            return;
        }
        List<MenuPesanan> hasil = dao.cari(keyword);
        tampilkanKeTabel(hasil);
    }

    private void urutkanData() {
        String kolomTampil = (String) cbUrutKolom.getSelectedItem();
        boolean naik = cbUrutArah.getSelectedIndex() == 0;
        String kolomDb;
        switch (kolomTampil) {
            case "Nama": kolomDb = "nama"; break;
            case "Harga": kolomDb = "harga"; break;
            case "Jumlah": kolomDb = "jumlah"; break;
            default: kolomDb = "id";
        }
        List<MenuPesanan> hasil = dao.getAllSorted(kolomDb, naik);
        tampilkanKeTabel(hasil);
    }

    private void tampilkanSemuaData() {
        List<MenuPesanan> daftar = dao.getAll();
        tampilkanKeTabel(daftar);
    }

    private void tampilkanKeTabel(List<MenuPesanan> daftar) {
        model.setRowCount(0);
        Locale localeID = new Locale.Builder().setLanguage("id").setRegion("ID").build();
        NumberFormat formatRupiah = NumberFormat.getNumberInstance(localeID);
        for (MenuPesanan m : daftar) {
            model.addRow(new Object[]{
                    m.getId(),
                    m.getKode(),
                    m.getNama(),
                    "Rp " + formatRupiah.format(m.getHarga()),
                    m.getJumlah(),
                    "Rp " + formatRupiah.format(m.getTotal()),
                    m.getKeterangan()
            });
        }
    }

    private void isiFormDariTabel() {
        int row = table.getSelectedRow();
        if (row == -1) {
            return;
        }
        idTerpilih = (int) model.getValueAt(row, 0);
        txtKode.setText(String.valueOf(model.getValueAt(row, 1)));
        txtNama.setText(String.valueOf(model.getValueAt(row, 2)));
        String hargaStr = String.valueOf(model.getValueAt(row, 3)).replace("Rp ", "").replace(".", "");
        txtHarga.setText(hargaStr);
        txtJumlah.setText(String.valueOf(model.getValueAt(row, 4)));
        Object ket = model.getValueAt(row, 6);
        txtKeterangan.setText(ket == null ? "" : ket.toString());
    }

    private MenuPesanan ambilDataDariForm() {
        return new MenuPesanan(
                txtKode.getText().trim(),
                txtNama.getText().trim(),
                Double.parseDouble(txtHarga.getText().trim()),
                Integer.parseInt(txtJumlah.getText().trim()),
                txtKeterangan.getText().trim()
        );
    }

    private void bersihkanForm() {
        txtKode.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
        txtKeterangan.setText("");
        idTerpilih = -1;
        table.clearSelection();
    }

    private void logout() {
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin logout?",
                "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            LoginForm login = new LoginForm();
            login.setVisible(true);
            this.dispose();
        }
    }
}
