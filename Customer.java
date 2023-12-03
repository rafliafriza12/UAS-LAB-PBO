import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Kelas Customer merupakan turunan dari kelas Akun.
 * Representasi objek customer yang dapat melakukan pembelian barang.
 */
public class Customer extends Akun {
    private String passCutomer;
    private Keranjang keranjang;
    private ArrayList<Invoice> invoiceSelesai;

    /**
     * Konstruktor tanpa parameter untuk membuat objek Customer dengan nilai default.
     */
    public Customer() {
        super("Customer");
        this.passCutomer = "Customer";
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<Invoice>();
    }

    /**
     * Konstruktor dengan dua parameter untuk membuat objek Customer dengan ID dan password tertentu.
     * @param id ID customer yang diinginkan.
     * @param passCustomer Password customer yang diinginkan.
     */
    public Customer(String id, String passCustomer) {
        super(id);
        this.passCutomer = passCustomer;
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<Invoice>();
    }

    /**
     * Metode untuk mengatur ID customer.
     * @param id ID customer yang baru.
     */
    public void setId(String id) {
        super.id = id;
    }

    /**
     * Metode untuk mendapatkan ID customer.
     * @return ID customer saat ini.
     */
    public String getId() {
        return super.id;
    }

    /**
     * Metode untuk mengatur password customer.
     * @param pass Password customer yang baru.
     */
    public void setPass(String pass) {
        this.passCutomer = pass;
    }

    /**
     * Metode untuk mendapatkan password customer.
     * @return Password customer saat ini.
     */
    public String getPass() {
        return this.passCutomer;
    }

    /**
     * Metode untuk mendapatkan daftar invoice yang sudah selesai.
     * @return Daftar invoice yang sudah selesai.
     */
    public ArrayList<Invoice> getInvoiceSelesai() {
        return this.invoiceSelesai;
    }

    /**
     * Metode untuk mendapatkan objek Keranjang yang digunakan oleh customer.
     * @return Objek Keranjang customer.
     */
    public Keranjang getKeranjang() {
        return this.keranjang;
    } 

    /**
     * Metode untuk membaca database invoice dari file teks.
     * @param idUser ID customer yang digunakan untuk membaca database invoice.
     */
    public void bacaDatabaseInvoice(String idUser) {
        // ... (implementasi metode bacaDatabaseInvoice)
    }

    /**
     * Metode untuk menampilkan daftar invoice yang sudah selesai.
     * @param idUser ID customer yang digunakan untuk menampilkan invoice.
     */
    public void tampilInvoice(String idUser) {
        // ... (implementasi metode tampilInvoice)
    }
}
