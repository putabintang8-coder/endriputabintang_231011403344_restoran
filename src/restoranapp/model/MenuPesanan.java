package restoranapp.model;

/**
 * Model untuk entitas Menu & Pesanan
 * Tugas Akhir - Endri Puta Bintang - 231011403344
 */
public class MenuPesanan {
    private int id;
    private String kode;
    private String nama;
    private double harga;
    private int jumlah;
    private String keterangan;

    public MenuPesanan() {
    }

    public MenuPesanan(String kode, String nama, double harga, int jumlah, String keterangan) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    public MenuPesanan(int id, String kode, String nama, double harga, int jumlah, String keterangan) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public double getTotal() {
        return harga * jumlah;
    }
}
