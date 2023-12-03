import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Kelas Invoice merepresentasikan invoice dari transaksi pelanggan.
 * Invoice mencakup informasi seperti ID transaksi, pengguna, total harga,
 * status checkout, barang belian, dan metode pembayaran.
 *
 * @author Rafli Afriza Nugraha
 * @version 1.0
 */
public class Invoice extends Customer {

    /**
     * ID transaksi untuk invoice.
     */
    private String idTransaksi;

    /**
     * Pengguna yang melakukan transaksi.
     */
    private String user;

    /**
     * Total harga dari transaksi.
     */
    private long totalHarga;

    /**
     * Status checkout dari transaksi.
     */
    private String statusCheckOut;

    /**
     * ArrayList yang menyimpan barang-barang yang dibeli.
     */
    private ArrayList<Barang> barangBelian;

    /**
     * ArrayList yang menyimpan deskripsi barang untuk invoice.
     */
    private ArrayList<String> barangInvoice;

    /**
     * Objek Transaksi yang digunakan untuk mengelola transaksi pelanggan.
     */
    private Transaksi transaksi;

    /**
     * Objek Pembayaran yang digunakan untuk melakukan pembayaran.
     */
    private Pembayaran pembayaran;

    /**
     * Konstruktor untuk kelas Invoice yang digunakan dalam konteks transaksi.
     *
     * @param id    ID pelanggan yang melakukan transaksi.
     * @param pass  Password pelanggan yang melakukan transaksi.
     * @param totalHarga Total harga dari transaksi.
     * @param barangBelian ArrayList berisi barang-barang yang dibeli.
     */
    public Invoice(String id, String pass, long totalHarga, ArrayList<Barang> barangBelian) {
        super(id, pass);
        this.totalHarga = totalHarga;
        this.barangBelian = barangBelian;
    }

    /**
     * Konstruktor tanpa parameter untuk kelas Invoice.
     * Inisialisasi ArrayList barangBelian dan barangInvoice.
     */
    public Invoice() {
        this.barangBelian = new ArrayList<Barang>();
        this.barangInvoice = new ArrayList<String>();
    }

    /**
     * Konstruktor untuk kelas Invoice yang digunakan dalam konteks pembayaran.
     *
     * @param id    ID pelanggan yang melakukan pembayaran.
     * @param pass  Password pelanggan yang melakukan pembayaran.
     */
    public Invoice(String id, String pass) {
        this.transaksi = new Transaksi(id, pass);
        this.barangBelian = new ArrayList<Barang>();
    }

    /**
     * Mendapatkan ArrayList yang berisi barang-barang yang dibeli.
     *
     * @return ArrayList yang berisi barang-barang yang dibeli.
     */
    public ArrayList<Barang> getBarangBelian() {
        return this.barangBelian;
    }

    /**
     * Mendapatkan ArrayList yang berisi deskripsi barang untuk invoice.
     *
     * @return ArrayList yang berisi deskripsi barang untuk invoice.
     */
    public ArrayList<String> getBarangInvoice() {
        return this.barangInvoice;
    }

    /**
     * Mengatur ID transaksi untuk invoice.
     *
     * @param idTransaksi ID transaksi yang akan diatur.
     */
    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    /**
     * Mendapatkan ID transaksi dari invoice.
     *
     * @return ID transaksi dari invoice.
     */
    public String getIdTransaksi() {
        return idTransaksi;
    }

    /**
     * Mengatur pengguna yang melakukan transaksi.
     *
     * @param user ID pengguna yang akan diatur.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Mendapatkan ID pengguna yang melakukan transaksi.
     *
     * @return ID pengguna yang melakukan transaksi.
     */
    public String getUser() {
        return user;
    }

    /**
     * Mengatur total harga dari transaksi.
     *
     * @param totalHarga Total harga yang akan diatur.
     */
    public void setTotalHarga(long totalHarga) {
        this.totalHarga = totalHarga;
    }

    /**
     * Mendapatkan total harga dari transaksi.
     *
     * @return Total harga dari transaksi.
     */
    public long getTotalHarga() {
        return this.totalHarga;
    }

    /**
     * Mengatur status checkout dari transaksi.
     *
     * @param statusCheckOut Status checkout yang akan diatur.
     */
    public void setStatusCheckOut(String statusCheckOut) {
        this.statusCheckOut = statusCheckOut;
    }

    /**
     * Mendapatkan status checkout dari transaksi.
     *
     * @return Status checkout dari transaksi.
     */
    public String getStatusCheckOut() {
        return this.statusCheckOut;
    }

    /**
     * Mendapatkan objek Transaksi yang digunakan dalam konteks invoice.
     *
     * @return Objek Transaksi yang digunakan dalam konteks invoice.
     */
    public Transaksi getTransaksi() {
        return this.transaksi;
    }

    /**
     * Mendapatkan objek Pembayaran yang digunakan untuk melakukan pembayaran.
     *
     * @return Objek Pembayaran yang digunakan untuk melakukan pembayaran.
     */
    public Pembayaran getPembayaran() {
        return this.pembayaran;
    }

    /**
     * Memeriksa apakah pelanggan ingin melanjutkan ke pembayaran atau membatalkan checkout.
     *
     * @param hargaTotal Harga total transaksi yang akan diperiksa.
     */
    public void cekPembayaran(long hargaTotal) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Lanjut ke Pembayaran ? ");
        System.out.println("[1] Lanjut");
        System.out.println("[2] Batal Check Out");
        System.out.print("Pilih => ");
        int pilih = scan.nextInt();

        if (pilih == 1) {
            this.pilihPembayaran(hargaTotal);
        } else {
            this.barangBelian.clear();
            System.out.println("Checkout Dibatalkan");
        }

    }

    /**
     * Meminta pelanggan memilih metode pembayaran dan melakukan pembayaran.
     *
     * @param hargaTotal Harga total transaksi yang akan dibayar.
     */
    private void pilihPembayaran(long hargaTotal) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("[1] Bank");
        System.out.println("[2] QRIS");
        System.out.println("[3] COD");
        System.out.print("Pilih => ");
        int pilih = scan.nextInt();
        if (pilih == 1) {
            this.pembayaran = new Bank(hargaTotal);
            pembayaran.bayar();
            this.tulisInvoiceKeFile(this.getId());
        } else if (pilih == 2) {
            this.pembayaran = new Qris(hargaTotal);
            this.pembayaran.bayar();
            this.tulisInvoiceKeFile(this.getId());
        } else if (pilih == 3) {
            this.pembayaran = new COD(hargaTotal);
            this.pembayaran.bayar();
            this.tulisInvoiceKeFile(this.getId());
        }
    }

    /**
     * Menulis informasi transaksi ke file invoice.
     *
     * @param idUser ID pengguna yang melakukan transaksi.
     */
    private void tulisInvoiceKeFile(String idUser) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String pathInvoice = "assets/database-customer/" + idUser + "/" + idUser + "-invoice.txt";
        BufferedWriter penulisInvoice = null;

        try {
            penulisInvoice = new BufferedWriter(new FileWriter(pathInvoice, true));
            penulisInvoice.write(timeStamp + " ");
            penulisInvoice.write(this.getId() + " ");
            for (Barang barang : this.barangBelian) {
                penulisInvoice.write((barang.getNamaBarang() + "(ID:" + barang.getIdBarang() + ")") + " ");
            }
            penulisInvoice.write(String.valueOf(this.getTotalHarga()).concat(" "));
            penulisInvoice.write("Proses");
            penulisInvoice.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (penulisInvoice != null) {
                    penulisInvoice.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // invoice admin
        String pathInvoinceAdmin = "assets/data-transaksi/dataTransaksi.txt";
        BufferedWriter databaseInvoice = null;

        try {
            databaseInvoice = new BufferedWriter(new FileWriter(pathInvoinceAdmin, true));
            databaseInvoice.write(timeStamp + " ");
            databaseInvoice.write(this.getId() + " ");
            for (Barang barang : this.barangBelian) {
                databaseInvoice.write((barang.getNamaBarang() + "(ID:" + barang.getIdBarang() + ")") + " ");
            }
            databaseInvoice.write(String.valueOf(this.getTotalHarga()).concat(" "));
            databaseInvoice.write("Proses");
            databaseInvoice.newLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (databaseInvoice != null) {
                    databaseInvoice.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
