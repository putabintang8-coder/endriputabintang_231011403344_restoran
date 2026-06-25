-- =====================================================
-- Database: db_restoran_231011403344
-- Tugas Akhir Pemrograman 2
-- Nama   : Endri Puta Bintang
-- NIM    : 231011403344
-- Objek  : Restoran (Menu & Pesanan)
-- =====================================================

DROP DATABASE IF EXISTS db_restoran_231011403344;
CREATE DATABASE db_restoran_231011403344;
USE db_restoran_231011403344;

-- =====================================================
-- Tabel user (untuk login)
-- =====================================================
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

INSERT INTO user (username, password) VALUES
('admin', 'admin123');

-- =====================================================
-- Tabel menu_pesanan
-- Entitas Utama: Menu & Pesanan (kode, nama, harga, jumlah)
-- =====================================================
CREATE TABLE menu_pesanan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kode VARCHAR(20) NOT NULL UNIQUE,
    nama VARCHAR(100) NOT NULL,
    harga DOUBLE NOT NULL,
    jumlah INT NOT NULL,
    keterangan VARCHAR(150) NULL
);

-- =====================================================
-- 10 data minimal (buatan sendiri)
-- Data pertama memuat nama & NIM mahasiswa sebagai bukti orisinalitas
-- =====================================================
INSERT INTO menu_pesanan (kode, nama, harga, jumlah, keterangan) VALUES
('MN001', 'Nasi Goreng Spesial (by Endri Puta Bintang - 231011403344)', 25000, 12, 'Pesanan favorit pelanggan'),
('MN002', 'Mie Ayam Bakso', 20000, 8, 'Topping bakso sapi'),
('MN003', 'Ayam Bakar Madu', 32000, 5, 'Disajikan dengan lalapan'),
('MN004', 'Sate Kambing 10 Tusuk', 35000, 4, 'Bumbu kacang khas'),
('MN005', 'Es Teh Manis', 6000, 25, 'Minuman pelengkap'),
('MN006', 'Jus Alpukat', 15000, 10, 'Tanpa gula tambahan'),
('MN007', 'Soto Ayam Lamongan', 22000, 7, 'Kuah bening segar'),
('MN008', 'Gado-Gado Spesial', 18000, 9, 'Sayur rebus + bumbu kacang'),
('MN009', 'Sop Buntut', 45000, 3, 'Daging empuk kuah gurih'),
('MN010', 'Nasi Uduk Komplit', 23000, 11, 'Dengan ayam goreng dan sambal'),
('MN011', 'Kopi Susu Gula Aren', 18000, 15, 'Best seller'),
('MN012', 'Pisang Goreng Keju', 12000, 6, 'Camilan manis renyah');
