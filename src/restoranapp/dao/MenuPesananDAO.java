package restoranapp.dao;

import restoranapp.model.MenuPesanan;
import restoranapp.util.KoneksiDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * DAO (Data Access Object) untuk tabel menu_pesanan
 * Semua query menggunakan PreparedStatement (aman dari SQL Injection)
 * Tugas Akhir - Endri Puta Bintang - 231011403344
 */
public class MenuPesananDAO {

    // ================= CREATE (Tambah) =================
    public boolean tambah(MenuPesanan m) {
        String sql = "INSERT INTO menu_pesanan (kode, nama, harga, jumlah, keterangan) VALUES (?, ?, ?, ?, ?)";
        Connection conn = KoneksiDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getKode());
            ps.setString(2, m.getNama());
            ps.setDouble(3, m.getHarga());
            ps.setInt(4, m.getJumlah());
            ps.setString(5, m.getKeterangan());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menambah data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // ================= READ (Lihat semua data) =================
    public List<MenuPesanan> getAll() {
        List<MenuPesanan> list = new ArrayList<>();
        String sql = "SELECT * FROM menu_pesanan ORDER BY id ASC";
        Connection conn = KoneksiDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    // ================= READ (Cari berdasarkan nama / kode) =================
    public List<MenuPesanan> cari(String keyword) {
        List<MenuPesanan> list = new ArrayList<>();
        String sql = "SELECT * FROM menu_pesanan WHERE nama LIKE ? OR kode LIKE ? ORDER BY id ASC";
        Connection conn = KoneksiDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSet(rs));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mencari data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    // ================= READ (Urutkan data) =================
    public List<MenuPesanan> getAllSorted(String kolom, boolean ascending) {
        List<MenuPesanan> list = new ArrayList<>();
        // whitelist kolom agar aman (tidak menerima input bebas ke query)
        String kolomAman;
        switch (kolom) {
            case "harga": kolomAman = "harga"; break;
            case "jumlah": kolomAman = "jumlah"; break;
            case "nama": kolomAman = "nama"; break;
            default: kolomAman = "id";
        }
        String arah = ascending ? "ASC" : "DESC";
        String sql = "SELECT * FROM menu_pesanan ORDER BY " + kolomAman + " " + arah;
        Connection conn = KoneksiDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengurutkan data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    // ================= UPDATE (Ubah) =================
    public boolean update(MenuPesanan m) {
        String sql = "UPDATE menu_pesanan SET kode=?, nama=?, harga=?, jumlah=?, keterangan=? WHERE id=?";
        Connection conn = KoneksiDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getKode());
            ps.setString(2, m.getNama());
            ps.setDouble(3, m.getHarga());
            ps.setInt(4, m.getJumlah());
            ps.setString(5, m.getKeterangan());
            ps.setInt(6, m.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengubah data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // ================= DELETE (Hapus) =================
    public boolean hapus(int id) {
        String sql = "DELETE FROM menu_pesanan WHERE id=?";
        Connection conn = KoneksiDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // ================= Cek kode unik (validasi sebelum tambah) =================
    public boolean kodeSudahAda(String kode) {
        String sql = "SELECT id FROM menu_pesanan WHERE kode=?";
        Connection conn = KoneksiDB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kode);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    private MenuPesanan mapResultSet(ResultSet rs) throws SQLException {
        MenuPesanan m = new MenuPesanan();
        m.setId(rs.getInt("id"));
        m.setKode(rs.getString("kode"));
        m.setNama(rs.getString("nama"));
        m.setHarga(rs.getDouble("harga"));
        m.setJumlah(rs.getInt("jumlah"));
        m.setKeterangan(rs.getString("keterangan"));
        return m;
    }
}
