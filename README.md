# Sistem Informasi Restoran (Menu & Pesanan)
**Tugas Akhir Pemrograman 2**
Nama : Endri Puta Bintang
NIM  : 231011403344
Objek: Restoran — Menu & Pesanan

---

## 1. Struktur Project
```
RestoranApp/
├── build.xml                          -> script build Ant
├── manifest.mf                        -> manifest untuk JAR
├── db_restoran_231011403344.sql       -> script database (JALANKAN INI DULU)
├── lib/                               -> taruh mysql-connector-j-x.x.x.jar di sini
├── nbproject/
│   ├── project.xml
│   └── project.properties
└── src/restoranapp/
    ├── Main.java                      -> entry point aplikasi
    ├── model/
    │   ├── MenuPesanan.java
    │   └── User.java
    ├── dao/
    │   ├── MenuPesananDAO.java        -> CRUD + PreparedStatement
    │   └── UserDAO.java               -> proses login
    ├── util/
    │   └── KoneksiDB.java             -> koneksi MySQL (sesuaikan password!)
    └── view/
        ├── LoginForm.java             -> form login
        └── MainForm.java              -> form utama (CRUD, cari, urut)
```

## 2. Langkah Setup (PENTING, urutkan sesuai langkah)

### A. Download driver MySQL Connector/J
1. Buka: https://dev.mysql.com/downloads/connector/j/
2. Pilih "Platform Independent", download file `.zip` atau `.tar.gz`
3. Ekstrak, cari file bernama mirip `mysql-connector-j-8.4.0.jar`
4. Copy file `.jar` itu ke folder `RestoranApp/lib/`

> Alternatif lebih cepat: cari di Google "mysql connector j 8.4.0 jar download" lalu
> ambil dari situs resmi MySQL (dev.mysql.com), JANGAN dari situs pihak ketiga.

### B. Jalankan database
1. Nyalakan **XAMPP/Laragon**, start service **MySQL**
2. Buka **phpMyAdmin** (atau MySQL Workbench / HeidiSQL)
3. Klik tab **Import** atau **SQL**, lalu jalankan file `db_restoran_231011403344.sql`
4. Pastikan database `db_restoran_231011403344` muncul dengan tabel `user` dan `menu_pesanan`

### C. Buka project di NetBeans
1. Buka NetBeans → **File > Open Project** → pilih folder `RestoranApp`
2. Klik kanan project → **Properties > Libraries** → **Add JAR/Folder**
   → pilih file `mysql-connector-j-8.4.0.jar` di folder `lib/`
   (Ini WAJIB supaya NetBeans bisa compile, karena import driver MySQL ada di kode.)
3. Klik kanan project → **Clean and Build**
4. Jika sukses tanpa error merah, klik kanan project → **Run**

### D. Sesuaikan koneksi database (jika perlu)
Buka file `src/restoranapp/util/KoneksiDB.java`, cek baris ini:
```java
private static final String USER = "root";
private static final String PASSWORD = ""; // ganti kalau MySQL lo pakai password
```
- **XAMPP default**: user `root`, password kosong `""` → biasanya tidak perlu diubah
- **Laragon default**: sama, user `root`, password kosong `""`
- Kalau lo set password MySQL sendiri, ganti `PASSWORD` sesuai itu

### E. Login & mulai pakai
- Username: `admin`
- Password: `admin123`

## 3. Fitur yang sudah diimplementasikan (sesuai ketentuan dosen)
| Ketentuan | Status | Lokasi |
|---|---|---|
| Java + Swing (NetBeans), bukan console | ✅ | `view/LoginForm.java`, `view/MainForm.java` |
| MySQL via JDBC + PreparedStatement | ✅ | semua method di `dao/MenuPesananDAO.java`, `dao/UserDAO.java` |
| Pola MVC + DAO | ✅ | `model/` `view/` `dao/` terpisah jelas |
| CRUD: Tambah, Lihat, Ubah, Hapus | ✅ | tombol di `MainForm.java` |
| Form login sebelum menu utama | ✅ | `LoginForm.java` → `MainForm.java` |
| Minimal 1 fitur cari/urut | ✅ | fitur Cari (nama/kode) DAN Urutkan (4 kolom, naik/turun) |
| Validasi input wajib | ✅ | method `validasiInput()` di `MainForm.java` |
| Nama database `db_[objek]_[NIM]` | ✅ | `db_restoran_231011403344` |
| Minimal 1 data memuat nama & NIM | ✅ | baris pertama tabel `menu_pesanan` |
| Minimal 10 data buatan sendiri | ✅ | 12 data di file SQL |

## 4. Troubleshooting Umum
- **Error "Access denied for user 'root'"** → password di `KoneksiDB.java` salah, sesuaikan
- **Error "Unknown database"** → file `.sql` belum dijalankan di phpMyAdmin
- **Error "ClassNotFoundException: com.mysql.cj.jdbc.Driver"** → jar MySQL Connector belum ditambahkan ke Libraries project NetBeans
- **Error "Communications link failure"** → service MySQL di XAMPP/Laragon belum di-start

## 5. Catatan
- Project ini ditulis manual (bukan drag-drop GUI builder NetBeans), jadi semua kode bisa
  lo baca dan pahami isinya — penting kalau nanti diuji/disidang.
- Kalau dosen lo punya rumus penentuan objek berbeda dari NIM (bukan modulo 16),
  cek ulang pengumuman pembagian objek dari dosen di e-learning untuk memastikan
  objek "Restoran" ini memang benar sesuai NIM `231011403344`.
