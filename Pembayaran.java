/**
 * Kelas abstrak Pembayaran adalah kerangka dasar untuk proses pembayaran dalam sistem.
 * Kelas ini memiliki metode abstrak 'bayar()' yang perlu diimplementasikan oleh kelas turunannya.
 */
public abstract class Pembayaran {
    /**
     * Metode abstrak bayar() merupakan kerangka untuk proses pembayaran.
     * Setiap kelas turunan wajib mengimplementasikan metode ini sesuai dengan jenis pembayaran yang digunakan.
     */
    public abstract void bayar();
}
