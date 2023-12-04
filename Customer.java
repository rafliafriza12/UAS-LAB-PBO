import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
public class Customer extends Akun{
    private String passCutomer;
    private Keranjang keranjang;
    private ArrayList<Invoice> invoiceSelesai;
    // private ArrayList<Barang> barangBelian; 



    public Customer(){
        super("Customer");
        this.passCutomer = "Customer";
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<Invoice>();
    }

    public Customer(String id, String passCustomer){
        super(id);
        this.passCutomer = passCustomer;
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<Invoice>();
    }

    public void setId(String id){
        super.id = id;
    }

    public String getId(){
        return super.id;
    }

    public void setPass(String pass){
        this.passCutomer = pass;
    }

    public String getPass(){
        return this.passCutomer;
    }

    public ArrayList<Invoice> getInvoiceSelesai() {
        return this.invoiceSelesai;
    }

    public Keranjang getKeranjang(){
        return this.keranjang;
    } 

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

    public void tampilInvoice(String idUser){
        System.out.println("------------------List Transaksi------------------\n");
        this.bacaDatabaseInvoice(idUser);
        // System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        this.getInvoiceSelesai().forEach((i) -> {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("ID Transaksi\t\t: "+i.getIdTransaksi());
            System.out.println("User\t\t\t: " +i.getUser());
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
