
/**
 * Kelas Transaksi merepresentasikan proses transaksi yang dilakukan oleh seorang customer.
 * Transaksi ini mencakup pembelian barang dari keranjang belanja, penghitungan total harga,
 * pembuatan invoice, dan pengecekan pembayaran.
 *
 * <p>Kelas ini memiliki atribut Customer sebagai akun yang melakukan transaksi, harga total transaksi,
 * dan ID transaksi. Metode transaksiCustomer() memulai proses transaksi, dan jika berhasil,
 * membuat objek Invoice, menghitung total harga, dan mengecek pembayaran.
 * Juga terdapat metode untuk mengatur dan mendapatkan ID transaksi serta total harga.
 *
 * @author Rafli Afriza Nugraha
 * @version 1.0
 */
public class Transaksi {
    private Customer akun;
    private long hargaTotal;
    private String idTransaksi;

    /**
     * Konstruktor untuk membuat objek Transaksi dengan akun customer yang melakukan transaksi.
     *
     * @param id       ID customer yang melakukan transaksi.
     * @param password Password customer yang melakukan transaksi.
     */
    public Transaksi(String id, String password){
        this.akun = new Customer(id, password);
        // this.scan = new Scanner(System.in);
    }

    /**
     * Metode untuk mengatur ID transaksi.
     *
     * @param idTransaksi ID transaksi yang ingin diatur.
     */
    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    /**
     * Metode untuk mendapatkan ID transaksi.
     *
     * @return ID transaksi saat ini.
     */
    public String getIdTransaksi() {
        return idTransaksi;
    }

    /**
     * Metode untuk mengatur harga total transaksi.
     *
     * @param hargaTotal Harga total transaksi yang ingin diatur.
     */
    public void setHargaTotal(long hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    /**
     * Metode untuk mendapatkan harga total transaksi.
     *
     * @return Harga total transaksi saat ini.
     */
    public long getHargaTotal() {
        return this.hargaTotal;
    }

    /**
     * Metode untuk mendapatkan akun customer yang melakukan transaksi.
     *
     * @return Objek Customer yang menjadi akun transaksi.
     */
    public Customer getAkun(){
        return this.akun;
    }

    /**
     * Metode untuk menjalankan proses transaksi oleh customer.
     * Metode ini memanggil checkout dari keranjang belanja dan membuat objek Invoice.
     * Jika total harga transaksi tidak nol, maka cek pembayaran dilakukan.
     *
     * @return True jika transaksi berhasil, false jika tidak ada barang dalam keranjang.
     */
    public boolean transaksiCustomer(){
        this.setHargaTotal(this.akun.getKeranjang().checkOut(this.akun));
        if(this.getHargaTotal()==0){
            return false;
        }
        else{
            // Buat objek Invoice
            Invoice invoice = new Invoice(this.akun.getId(), this.akun.getPass(),this.getHargaTotal(),this.getAkun().getKeranjang().getBarangBelian()); //ubah ini
            // Cek pembayaran
            invoice.cekPembayaran(hargaTotal);
            // invoice.tulisInvoiceKeFile();
            return true;
        }

    }

}
