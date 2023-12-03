import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Kelas AdminDriver merupakan kelas yang mengontrol tampilan dan logika aplikasi
 * untuk admin. Kelas ini mengelola tampilan menu admin, transaksi, dan operasi
 * pada barang, serta berinteraksi dengan berkas untuk menyimpan dan membaca data.
 *
 * @author Rafli Afriza Nugraha
 * @version 1.0
 */
public class AdminDriver extends Driver{
    private Admin akun;
    private ListBarang listBarang;
    private ArrayList<Invoice> listTransaksi;
    private ArrayList<Invoice> listInvoiceUser;
    private ArrayList<Barang> listKeranjangUser;
    private Scanner scan;

    /**
     * Konstruktor untuk kelas AdminDriver yang menerima objek Admin sebagai parameter.
     * Inisialisasi objek AdminDriver dengan akun admin, listBarang, listTransaksi,
     * listInvoiceUser, listKeranjangUser, dan Scanner.
     *
     * @param admin Objek Admin yang akan dihubungkan dengan AdminDriver.
     */
    public AdminDriver(Admin admin){
        this.akun = admin;
        this.listBarang = new ListBarang();
        this.listTransaksi = new ArrayList<Invoice>();
        this.listInvoiceUser = new ArrayList<Invoice>();
        this.listKeranjangUser = new ArrayList<Barang>();
        this.scan = new Scanner(System.in);
    }

    /**
     * Menampilkan menu utama admin dan meng-handle input pengguna.
     */
    public void menu(){
        int pilihMenu;
        ClearConsole clear = new ClearConsole();
        
        System.out.printf("\nSelamat Datang Admin %s ^.^",this.akun.getId());
        do{
            System.out.println("\n\n---------------------------");
            System.out.println("        Menu Admin");
            System.out.println("---------------------------");
            System.out.println("   [1] Tampilkan Barang");
            System.out.println("   [2] Tambah Barang");
            System.out.println("   [3] Edit Barang");
            System.out.println("   [4] Hapus Barang");
            System.out.println("   [5] Lihat Transaksi");
            System.out.println("   [6] Log Out");
            System.out.println("---------------------------");
            System.out.print("Pilih => ");
            pilihMenu = this.scan.nextInt();

            switch (pilihMenu) {
                case 1:
                    clear.clear();
                    listBarang.tampilkanBarang();
                    break;
                case 2:
                    listBarang.tambahBarang();
                    break;
                case 3:
                    listBarang.editBarang();
                    break;
                case 4:
                    listBarang.hapusBarang();
                    break;
                case 5:
                    this.lihatTransaksi();
                    break;
                case 6:
                    System.exit(1);
                    break;
                
                default:
                    break;
            }
        }while(pilihMenu > 0 && pilihMenu <6);
    }

    /**
     * Menampilkan transaksi yang dilakukan oleh pengguna dan memberikan opsi untuk
     * menerima atau menolak transaksi.
     */
    public void lihatTransaksi(){
        ClearConsole clear = new ClearConsole();
        clear.clear();
        System.out.println("--------List Transaksi--------\n");
        this.bacaDatabaseTransaksi();
        this.listTransaksi.forEach((i) -> {
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
            System.out.println("Status\t\t\t: " + i.getStatusCheckOut());
            System.out.println("\n");
        });
        this.actionTransaksi(clear);
        this.listTransaksi.clear();
        this.listKeranjangUser.clear();
        this.listInvoiceUser.clear();
    }

    /**
     * Menangani aksi yang dapat diambil pada transaksi, seperti menerima atau
     * menolak transaksi.
     *
     * @param clear Objek ClearConsole untuk membersihkan tampilan konsol.
     */
    private void actionTransaksi(ClearConsole clear){
        System.out.println("[1] Terima Transaksi");
        System.out.println("[2] Tolak Transaksi");
        System.out.println("[3] keluar");
        System.out.print("Pilih => ");
        int pilih = this.scan.nextInt();

        switch (pilih) {
            case 1:
                this.terimaTransaksi();
                break;
            case 2:
                this.tolakTransaksi();
                break;
            case 3:
                clear.clear();
                break;
            default:
                break;
        }
    }

    /**
     * Memproses transaksi dengan menerima barang dan meng-update stok barang.
     */
    private void terimaTransaksi(){
        ClearConsole clear = new ClearConsole();
        System.out.print("Masukkan ID Transaksi : ");
        this.scan.nextLine();
        String idTransaksi = this.scan.nextLine();
        int index = 0;
        for(int i = 0 ; i < this.listTransaksi.size();i++){
            if(idTransaksi.equals(this.listTransaksi.get(i).getIdTransaksi())){
                if(this.listTransaksi.get(i).getStatusCheckOut().equals("Ditolak") || this.listTransaksi.get(i).getStatusCheckOut().equals("Diterima")){
                    clear.clear();
                    System.out.println("Transaksi ini sudah pernah di proses, tidak bisa bengubah status transaksi");
                    continue;
                }
                this.listTransaksi.get(i).setStatusCheckOut("Diterima");
                this.bacaDatabaseInvoiceUser(this.listTransaksi.get(i).getUser());
                for(int j = 0 ; j < this.listInvoiceUser.size() ; j++){
                    if(idTransaksi.equals(this.listInvoiceUser.get(j).getIdTransaksi())){
                        this.listInvoiceUser.get(j).setStatusCheckOut("Diterima");
                    }
                }
                clear.clear();
                System.out.println("Transaksi Berhasil Diterima");
                index = i;
            }
        }
        this.listBarang.loadBarangFromFile();
        this.bacaKeranjangUser(this.listTransaksi.get(index).getUser());

        this.listTransaksi.get(index).getBarangInvoice().forEach((k) -> {
            for(int l = 0 ; l < this.listBarang.getBarang().size() ; l++){
                if(k.contains(String.valueOf(this.listBarang.getBarang().get(l).getIdBarang()))){
                    for(int m = 0 ; m < this.listKeranjangUser.size() ; m++){
                        if(this.listBarang.getBarang().get(l).getIdBarang() == this.listKeranjangUser.get(m).getIdBarang()){
                            this.listBarang.getBarang().get(l).setStok(this.listBarang.getBarang().get(l).getStok() - this.listKeranjangUser.get(m).getStok());
                            this.listKeranjangUser.remove(m);
                        }
                    }
                }
            }
        });
        this.listBarang.writeToFile();
        this.tulisKeDatabaseKeranjangUser(this.listTransaksi.get(index).getUser());
        this.listBarang.getBarang().clear();
        this.tulisDatabaseTransaksi();
        this.tulisDatabaseInvoiceUser(this.listTransaksi.get(index).getUser());
    }

    /**
     * Menolak transaksi dan meng-update status transaksi.
     */
    private void tolakTransaksi(){
        ClearConsole clear = new ClearConsole();
        System.out.print("Masukkan ID Transaksi : ");
        this.scan.nextLine();
        String idTransaksi = this.scan.nextLine();
        int index = 0;
        for(int i = 0 ; i < this.listTransaksi.size();i++){
            if(idTransaksi.equals(this.listTransaksi.get(i).getIdTransaksi())){
                if(this.listTransaksi.get(i).getStatusCheckOut().equals("Ditolak") || this.listTransaksi.get(i).getStatusCheckOut().equals("Diterima")){
                    clear.clear();
                    System.out.println("Transaksi ini sudah pernah di proses, tidak bisa bengubah status transaksi");
                    continue;
                }
                this.listTransaksi.get(i).setStatusCheckOut("Ditolak");
                this.bacaDatabaseInvoiceUser(this.listTransaksi.get(i).getUser());
                for(int j = 0 ; j < this.listInvoiceUser.size() ; j++){
                    if(idTransaksi.equals(this.listInvoiceUser.get(j).getIdTransaksi())){
                        this.listInvoiceUser.get(j).setStatusCheckOut("Ditolak");
                    }
                }
                clear.clear();
                System.out.println("Transaksi Berhasil Ditolak");
                index = i;
            }
        }
        this.tulisDatabaseTransaksi();
        this.tulisDatabaseInvoiceUser(this.listTransaksi.get(index).getUser());
    }

    /**
     * Menulis data transaksi ke berkas database transaksi.
     */
    private void tulisDatabaseTransaksi(){
        String pathDatabaseTransaksi = "assets/data-transaksi/dataTransaksi.txt";
        BufferedWriter databaseTransaksi = null;

        try{
            databaseTransaksi = new BufferedWriter(new FileWriter(pathDatabaseTransaksi));
            for(Invoice i : this.listTransaksi){
                databaseTransaksi.write(i.getIdTransaksi()+" ");
                databaseTransaksi.write(i.getUser()+" ");
               for(String j : i.getBarangInvoice()){
                    databaseTransaksi.write(j+" ");
               }
               databaseTransaksi.write(String.valueOf(i.getTotalHarga()).concat(" "));
               databaseTransaksi.write(i.getStatusCheckOut());
               databaseTransaksi.newLine();
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try{
                if(databaseTransaksi!=null){
                    databaseTransaksi.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Membaca data transaksi dari berkas database transaksi.
     */
    private void bacaDatabaseTransaksi(){
        String pathDatabaseTransaksi = "assets/data-transaksi/dataTransaksi.txt";
        BufferedReader databaseTransaksi = null;
        String bacaLine;

        try{
            databaseTransaksi = new BufferedReader( new FileReader(pathDatabaseTransaksi));
            while ((bacaLine = databaseTransaksi.readLine()) != null) {
                Invoice transaksi = new Invoice();
                String[] token = bacaLine.split(" ");
                int indexbarangBelian = token.length - 4;
                transaksi.setIdTransaksi(token[0]);
                transaksi.setUser(token[1]);
                int index = 2;
                for(int i = 0 ; i < indexbarangBelian;i++){
                    transaksi.getBarangInvoice().add(token[index]);
                    index++;
                }
                transaksi.setTotalHarga(Integer.parseInt(token[index]));
                transaksi.setStatusCheckOut(token[index+1]);
                this.listTransaksi.add(transaksi);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            try {
                if(databaseTransaksi!=null){
                    databaseTransaksi.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Membaca data invoice pengguna dari berkas database invoice.
     * Data invoice yang dibaca akan dimasukkan ke dalam ArrayList listInvoiceUser.
     *
     * @param idUser ID pengguna yang bersangkutan.
     */
    public void bacaDatabaseInvoiceUser(String idUser){
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
                this.listInvoiceUser.add(data);
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
     * Menulis data invoice pengguna ke berkas database invoice.
     * Data invoice yang akan ditulis berasal dari ArrayList listInvoiceUser.
     *
     * @param idUser ID pengguna yang bersangkutan.
     */
    private void tulisDatabaseInvoiceUser(String idUser){
        String pathInvoice = "assets/database-customer/" + idUser +"/" + idUser+"-invoice.txt";
        BufferedWriter databaseTransaksi = null;

        try{
            databaseTransaksi = new BufferedWriter(new FileWriter(pathInvoice));
            for(Invoice i : this.listInvoiceUser){
                databaseTransaksi.write(i.getIdTransaksi()+" ");
                databaseTransaksi.write(i.getUser()+" ");
               for(String j : i.getBarangInvoice()){
                    databaseTransaksi.write(j+" ");
               }
               databaseTransaksi.write(String.valueOf(i.getTotalHarga()).concat(" "));
               databaseTransaksi.write(i.getStatusCheckOut());
               databaseTransaksi.newLine();
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try{
                if(databaseTransaksi!=null){
                    databaseTransaksi.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Membaca data keranjang pengguna dari berkas database keranjang.
     * Data yang dibaca kemudian dimasukkan ke dalam ArrayList listKeranjangUser.
     *
     * @param idUser ID pengguna yang bersangkutan.
     */
    private void bacaKeranjangUser(String idUser){
        String pathkeranjang= "assets/database-customer/" + idUser +"/" + idUser+"-keranjang.txt";
        BufferedReader databaseKeranjang = null;

        try {
            databaseKeranjang = new BufferedReader( new FileReader(pathkeranjang));
            String bacaLine;
            while((bacaLine= databaseKeranjang.readLine())!=null){
                String[] token = bacaLine.split(" ");
                Barang barangKeranjang = new Barang();
                barangKeranjang.setIdBarang(Integer.parseInt(token[0]));
                barangKeranjang.setNamaBarang(token[1]);
                barangKeranjang.setHargaBarang(Integer.parseInt(token[2]));
                barangKeranjang.setStok(Integer.parseInt(token[3]));

                this.listKeranjangUser.add(barangKeranjang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(databaseKeranjang!=null){
                    databaseKeranjang.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Menuliskan data keranjang pengguna ke berkas database keranjang.
     *
     * @param idUser ID pengguna yang bersangkutan.
     */
    private void tulisKeDatabaseKeranjangUser(String idUser){
        String pathDatabaseKeranjang = "assets/database-customer/"+idUser+"/"+idUser+"-keranjang.txt";
        BufferedWriter keranjangUser = null;

        try {
            keranjangUser = new BufferedWriter( new FileWriter(pathDatabaseKeranjang));

            for(Barang i : this.listKeranjangUser){
                keranjangUser.write(i.getIdBarang() + " ");
                keranjangUser.write(i.getNamaBarang() + " ");
                keranjangUser.write(i.getHargaBarang() + " ");
                keranjangUser.write(String.valueOf(i.getStok()));
                keranjangUser.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(keranjangUser!=null){
                    keranjangUser.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}