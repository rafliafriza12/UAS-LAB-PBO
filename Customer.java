import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Kelas Customer merupakan turunan dari kelas Akun.
 * Representasi objek customer yang dapat melakukan pembelian barang.
 */
public class Customer extends Akun{
    private String passCutomer;
    private Keranjang keranjang;
    private ArrayList<Invoice> invoiceSelesai;
    // private ArrayList<Barang> barangBelian; 

 /**
     * Konstruktor tanpa parameter untuk membuat objek Customer dengan nilai default.
     */
    public Customer(){
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
    public Customer(String id, String passCustomer){
        super(id);
        this.passCutomer = passCustomer;
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<Invoice>();
    }

    /**
     * Metode untuk mengatur ID customer.
     * @param id ID customer yang baru.
     */
    public void setId(String id){
        super.id = id;
    }

     /**
     * Metode untuk mendapatkan ID customer.
     * @return ID customer saat ini.
     */
    public String getId(){
        return super.id;
    }

    /**
     * Metode untuk mengatur password customer.
     * @param pass Password customer yang baru.
     */
    public void setPass(String pass){
        this.passCutomer = pass;
    }

     /**
     * Metode untuk mendapatkan password customer.
     * @return Password customer saat ini.
     */
    public String getPass(){
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
    public Keranjang getKeranjang(){
        return this.keranjang;
    } 

    /**
     * Metode untuk membaca database invoice dari file teks.
     * @param idUser ID customer yang digunakan untuk membaca database invoice.
     */
    public void bacaDatabaseInvoice(String idUser){
        String pathInvoice = "assets/database-customer/" + idUser +"/" + idUser+"-invoice.txt";
        BufferedReader databaseInvoice = null;
        String bacaLine;
        
        try {
            databaseInvoice = new BufferedReader(new FileReader(pathInvoice));
            while ((bacaLine = databaseInvoice.readLine()) != null) {
                Invoice data = new Invoice();
                String[] token = bacaLine.split(" ");
                int indexbarangBelian = token.length - 4;

                data.setIdTransaksi(token[0]);
                data.setUser(token[1]);
                int index = 2;
                for(int i = 0 ; i < indexbarangBelian;i++){
                    data.getBarangInvoice().add(token[index]);
                    index++;
                }
                data.setTotalHarga(Integer.parseInt(token[index]));
                data.setStatusCheckOut(token[index+1]);
                this.getInvoiceSelesai().add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(databaseInvoice!=null){
                    databaseInvoice.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

     /**
     * Metode untuk menampilkan daftar invoice yang sudah selesai.
     * @param idUser ID customer yang digunakan untuk menampilkan invoice.
     */
    public void tampilInvoice(String idUser){
        System.out.println("------------------List Transaksi------------------\n");
        this.bacaDatabaseInvoice(idUser);
        // System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        this.getInvoiceSelesai().forEach((i) -> {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("ID Transaksi\t\t: "+i.getIdTransaksi());
            System.out.println("User\t\t\t: " +i.getUser());
            System.out.println("Barang Yang dibeli");
            System.out.print("Barang Yang dibeli\t: ");
            for(int j = 0 ; j < i.getBarangInvoice().size();j++){
                if(j==0){
                    System.out.println("- "+i.getBarangInvoice().get(j));
                    continue;
                }
                System.out.println("\t\t\t  - "+i.getBarangInvoice().get(j));
            }
            System.out.println("Total harga\t\t: "+ i.getTotalHarga());
            System.out.println("Status\t\t\t: "+i.getStatusCheckOut());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");   
            System.out.println("\n");
        });

        this.invoiceSelesai.clear();
    }
    
}
