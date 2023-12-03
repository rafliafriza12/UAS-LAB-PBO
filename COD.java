/**
 * Kelas COD (Cash On Delivery) merupakan turunan dari kelas Pembayaran.
 * Representasi objek pembayaran dengan metode bayar ketika barang sampai.
 */
public class COD extends Pembayaran {

    /**
     * Variabel instance untuk menyimpan harga total pembayaran.
     */
    private long hargaTotal;

    /**
     * Konstruktor kelas COD.
     *
     * @param hargaTotal Harga total pembayaran yang harus dilakukan.
     */
    public COD(long hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    /**
     * Metode untuk melakukan proses pembayaran dengan metode COD.
     * Pengguna diminta untuk membayar ketika barang sampai rumah.
     */
    public void bayar() {
        ClearConsole clear = new ClearConsole();
        clear.clear();
        System.out.println("Menunggu transaksi diterima admin, Bayar ketika barang sampai rumah\n");
    }
}
