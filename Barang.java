/**
 * Kelas Barang merepresentasikan informasi tentang suatu barang dalam sistem inventaris.
 * Kelas ini memiliki atribut untuk mengatur ID barang, nama barang, harga barang, dan stok.
 */
public class Barang extends ListBarang {
    private int idBarang;       // Menyimpan ID barang
    private String namaBarang;  // Menyimpan nama barang
    private int hargaBarang;    // Menyimpan harga barang
    private int stok;           // Menyimpan jumlah stok barang

    /**
     * Konstruktor default untuk kelas Barang.
     * Tidak melakukan operasi inisialisasi.
     */
    public Barang() {
        
    }

    // Start method setter

    /**
     * Setter untuk mengatur ID barang.
     * @param idBarang ID yang akan diatur untuk barang.
     */
    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    /**
     * Setter untuk mengatur nama barang.
     * @param namaBarang Nama yang akan diatur untuk barang.
     */
    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    /**
     * Setter untuk mengatur harga barang.
     * @param hargaBarang Harga yang akan diatur untuk barang.
     */
    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    /**
     * Setter untuk mengatur stok barang.
     * @param stok Jumlah stok yang akan diatur untuk barang.
     */
    public void setStok(int stok) {
        this.stok = stok;
    }
    // End of method setter

    // Start method getter

    /**
     * Getter untuk mendapatkan ID barang.
     * @return ID barang.
     */
    public int getIdBarang() {
        return this.idBarang;
    }

    /**
     * Getter untuk mendapatkan nama barang.
     * @return Nama barang.
     */
    public String getNamaBarang() {
        return this.namaBarang;
    }

    /**
     * Getter untuk mendapatkan harga barang.
     * @return Harga barang.
     */
    public int getHargaBarang() {
        return this.hargaBarang;
    }

    /**
     * Getter untuk mendapatkan stok barang.
     * @return Jumlah stok barang.
     */
    public int getStok() {
        return this.stok;
    }
    // End of method getter
}
