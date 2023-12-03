import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * Kelas Keranjang merepresentasikan fungsionalitas keranjang belanja untuk seorang pelanggan.
 * Kelas ini mencakup metode-metode untuk mengelola barang di dalam keranjang, seperti menambah,
 * mengedit, dan menghapus barang, serta proses checkout dan menampilkan isi keranjang.
 * Kelas ini juga menangani pembacaan dan penulisan data keranjang ke file database.
 *
 * @author Rafli Afriza Nugraha
 * @version 1.0
 */
public class Keranjang {
    private ArrayList<Barang> keranjang;
    private ArrayList<Barang> listBarang;
    private ArrayList<Barang> barangBelian;
    private ArrayList<Integer> indexKeranjang;
    private ArrayList<Integer> indexBarang;

    /**
     * Konstruktor untuk membuat objek Keranjang kosong.
     * Menginisialisasi ArrayList untuk keranjang belanja, daftar barang yang tersedia,
     * barang yang akan dibeli, dan indeks terkait.
     */
    public Keranjang(){
        this.keranjang = new ArrayList<Barang>();
        this.listBarang = new ArrayList<Barang>();
        this.barangBelian = new ArrayList<Barang>();
        this.indexBarang = new ArrayList<Integer>();
        this.indexKeranjang = new ArrayList<Integer>();

    }

    /**
     * Mendapatkan daftar barang yang akan dibeli.
     *
     * @return ArrayList dari barang yang akan dibeli.
     */
    public ArrayList<Barang> getBarangBelian() {
        return this.barangBelian;
    }

    /**
     * Mendapatkan indeks barang di dalam keranjang.
     *
     * @return ArrayList dari indeks barang di dalam keranjang.
     */
    public ArrayList<Integer> getIndexBarang() {
        return this.indexBarang;
    }

    /**
     * Mendapatkan indeks barang di dalam keranjang belanja.
     *
     * @return ArrayList dari indeks barang di dalam keranjang belanja.
     */
    public ArrayList<Integer> getIndexKeranjang() {
        return this.indexKeranjang;
    }

    /**
     * Menampilkan isi keranjang belanja untuk seorang pelanggan.
     *
     * @param customer Pelanggan yang ingin menampilkan keranjangnya.
     */
    public void tampilkanIsiKeranjang(Customer customer){
        this.bacaDatabaseKeranjangUser(customer.getId());
        System.out.println("--------------List Keranjang--------------\n");
        if(this.keranjang.size()==0){
            System.out.println("Keranjang anda kosong\n");
            this.keranjang.clear();
        }else{
            this.keranjang.forEach((i) -> {
                System.out.println("Id Barang\t: "+i.getIdBarang());
                System.out.println("Nama Barang\t: "+i.getNamaBarang());
                System.out.println("Harga\t\t: "+i.getHargaBarang());
                System.out.println("jumlah\t\t: "+i.getStok());
                System.out.println("\n");
            });
    
            this.keranjang.clear();
        }

    }

    /**
     * Menambahkan barang yang dipilih ke dalam keranjang belanja untuk seorang pelanggan.
     *
     * @param customer Pelanggan yang menambahkan barang ke keranjang.
     */
    public void masukkanBarangKeKeranjang(Customer customer){ 
        Scanner scan = new Scanner(System.in);
        ClearConsole clear = new ClearConsole();
        this.bacaDatabaseKeranjangUser(customer.getId());
        this.loadBarangFromFile();
        boolean cekBarang = false;
        Barang barangKeranjang = new Barang();

        System.out.print("Masukkan id Barang\t: ");
        int idBarang = scan.nextInt();
        for(int i = 0; i < this.listBarang.size();i++){
            if(idBarang == this.listBarang.get(i).getIdBarang()){
                System.out.println("Nama barang\t\t: "+this.listBarang.get(i).getNamaBarang());
                System.out.println("Harga barang\t\t: "+this.listBarang.get(i).getHargaBarang());

                scan.nextLine();

                System.out.print("Masukkan jumlah barang\t: ");
                String jlhBarang = scan.nextLine();
            
                barangKeranjang.setIdBarang(this.listBarang.get(i).getIdBarang());
                barangKeranjang.setNamaBarang(this.listBarang.get(i).getNamaBarang());
                barangKeranjang.setHargaBarang(this.listBarang.get(i).getHargaBarang());
                barangKeranjang.setStok(Integer.parseInt(jlhBarang));

                this.keranjang.add(barangKeranjang);

                this.tulisKeDatabaseKeranjangUser(customer.getId());
                cekBarang = true;
                clear.clear();
                System.out.println(barangKeranjang.getNamaBarang()+" Ditambahkan ke Keranjang\n");
            }
            else if((i==this.listBarang.size()-1)&&cekBarang==false){
                clear.clear();
                System.out.println("Barang tidak ditemukan, Masukkan id barang dengan benar\n");
            }
        }

        this.keranjang.clear();
        this.listBarang.clear();
    }

    /**
     * Mengedit jumlah barang tertentu di dalam keranjang belanja.
     *
     * @param customer Pelanggan yang mengedit keranjang.
     */
    public void editKeranjang(Customer customer){
        ClearConsole clear = new ClearConsole();
        Scanner scan = new Scanner(System.in);
        boolean cekBarang = false;
        this.bacaDatabaseKeranjangUser(customer.getId());

        System.out.print("Masukkan id barang\t: ");
        int idBarang = scan.nextInt();

        for(int i = 0 ; i < this.keranjang.size() ; i++){
            if(idBarang == this.keranjang.get(i).getIdBarang()){
                Barang editBarang = new Barang();

                System.out.println("Nama Barang\t\t: "+this.keranjang.get(i).getNamaBarang());
                System.out.println("Harga Barang\t\t: "+this.keranjang.get(i).getHargaBarang());
                System.out.print("Masukkan jumlah baru\t: ");
                int jumlah = scan.nextInt();

                editBarang.setIdBarang(this.keranjang.get(i).getIdBarang());
                editBarang.setNamaBarang(this.keranjang.get(i).getNamaBarang());
                editBarang.setHargaBarang(this.keranjang.get(i).getHargaBarang());
                editBarang.setStok(jumlah);

                this.keranjang.set(i, editBarang);
                this.tulisKeDatabaseKeranjangUser(customer.getId());
                clear.clear();
                System.out.println("Barang dengan id "+ editBarang.getIdBarang() +" berhasil di edit\n");
                cekBarang = true;
            }
            else if((i==this.keranjang.size()-1)&&cekBarang==false){
                clear.clear();
                System.out.println("Barang tidak ditemukan di keranjang\n");
            }
        }
        
        this.keranjang.clear();

    }

    /**
     * Menghapus barang tertentu dari keranjang belanja.
     *
     * @param customer Pelanggan yang menghapus barang dari keranjang.
     */
    public void hapusBarang(Customer customer){
        ClearConsole clear = new ClearConsole();
        Scanner scan = new Scanner(System.in);
        this.bacaDatabaseKeranjangUser(customer.getId());
        System.out.print("Masukkan id barang\t: ");
        int idBarang = scan.nextInt();
        boolean cekBarang = false;

        for(int i = 0; i < this.keranjang.size();i++){
            if(idBarang==this.keranjang.get(i).getIdBarang()){
                this.keranjang.remove(i);
                clear.clear();
                System.out.println("Barang dengan id "+idBarang+" berhasil di hapus\n");
                this.tulisKeDatabaseKeranjangUser(customer.getId());
                cekBarang = true;
            }
            else if((i==this.keranjang.size()-1)&&cekBarang==false){
                System.out.println("Barang tidak ditemukan di keranjang\n");
            }
        }

        this.keranjang.clear();
    }

    /**
     * Memproses checkout untuk barang-barang di dalam keranjang belanja.
     *
     * @param customer Pelanggan yang melakukan checkout.
     * @return Harga total dari barang yang dibeli.
     */
    public long checkOut(Customer customer){
        Scanner scan = new Scanner(System.in);
        long totalHarga = 0;
        ClearConsole clear = new ClearConsole();
        this.loadBarangFromFile();
        this.bacaDatabaseKeranjangUser(customer.getId());
        System.out.print("Berapa barang yang ingin anda checkout : ");
        int jlh = scan.nextInt();

        if(jlh > 0 && jlh <= this.keranjang.size()){            
            int i = 0;
            while(i<jlh){
                boolean cekBarang = false;
                System.out.print("Masukkan id barang : ");
                int idBarang = scan.nextInt();
                
                for(int j = 0; j < this.keranjang.size();j++){
                    if(idBarang == this.keranjang.get(j).getIdBarang()){
                        for(int k = 0;k < this.listBarang.size();k++){
                            if(idBarang==this.listBarang.get(k).getIdBarang()){
                                if(this.keranjang.get(j).getStok()>this.listBarang.get(k).getStok()) {
                                    clear.clear();
                                    System.out.println("Barang ini tidak cukup stok, silahkan kurangi dahulu quantity\n");
                                    this.keranjang.clear();
                                    this.listBarang.clear();
                                    return 0;
                                }
                                else{
                                    System.out.println("ID Barang\t: " + this.keranjang.get(j).getIdBarang());
                                    System.out.println("Nama Barang\t: " + this.keranjang.get(j).getNamaBarang());
                                    System.out.println("Harga/pcs\t: "+this.keranjang.get(j).getHargaBarang());
                                    System.out.println("Quantity\t: "+this.keranjang.get(j).getStok());
                                    System.out.println("\n");
                                    totalHarga += (this.keranjang.get(j).getHargaBarang() * this.keranjang.get(j).getStok());

                                    cekBarang = true;
                                    this.barangBelian.add(this.keranjang.get(j));
                                    this.indexBarang.add(k);
                                    this.indexKeranjang.add(j);

                                    i++;
                                }
                            }
                        }
                        break;
                    }
                    else if((j==this.keranjang.size()-1)&&cekBarang==false){
                        clear.clear();
                        System.out.println("Barang dengan id "+idBarang+" tidak ditemukan\n");
                    }
                }
                
            }
            this.keranjang.clear();
            this.listBarang.clear();
            return totalHarga;
        }else{
            clear.clear();
            System.out.println("Barang di keranjang anda hanya "+this.keranjang.size()+"\nTidak bisa checkout kurang dari 1 ataupun melebihi jumlah barang dikeranjang");
            this.keranjang.clear();
            this.listBarang.clear();
            return totalHarga;
        }
    }

    /**
     * Membaca data keranjang belanja untuk seorang pelanggan dari file database.
     *
     * @param idUser ID pelanggan.
     */
    private void bacaDatabaseKeranjangUser(String idUser){
        String pathDatabaseKeranjang = "assets/database-customer/"+idUser+"/"+idUser+"-keranjang.txt";
        BufferedReader databaseKeranjangUser = null;

        try{
            databaseKeranjangUser = new BufferedReader(new FileReader(pathDatabaseKeranjang));
            String bacaDatabase;
            
            while((bacaDatabase = databaseKeranjangUser.readLine())!=null){
                String[] detailKeranjang = bacaDatabase.split(" ");
                Barang isiKeranjang = new Barang();

                isiKeranjang.setIdBarang(Integer.parseInt(detailKeranjang[0]));
                isiKeranjang.setNamaBarang(detailKeranjang[1]);
                isiKeranjang.setHargaBarang(Integer.parseInt(detailKeranjang[2]));
                isiKeranjang.setStok(Integer.parseInt(detailKeranjang[3]));

                this.keranjang.add(isiKeranjang);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(databaseKeranjangUser!=null){
                    databaseKeranjangUser.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * Menulis data keranjang belanja untuk seorang pelanggan ke dalam file database.
     *
     * @param idUser ID pelanggan.
     */
    private void tulisKeDatabaseKeranjangUser(String idUser){
        String pathDatabaseKeranjang = "assets/database-customer/"+idUser+"/"+idUser+"-keranjang.txt";
        BufferedWriter keranjangUser = null;

        try {
            keranjangUser = new BufferedWriter( new FileWriter(pathDatabaseKeranjang));

            for(Barang i : this.keranjang){
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

    /**
     * Memuat daftar barang yang tersedia dari file.
     */
    private void loadBarangFromFile() {
        String pathFile = "assets/barang/barang.txt";
        BufferedReader fileBarang = null;
        try {
            fileBarang = new BufferedReader(new FileReader(pathFile));
            String bacaBaris;
            // boolean loop = true;
                while ((bacaBaris = fileBarang.readLine()) != null) {
                    String[] detailBarang = bacaBaris.split(" ");
                    Barang objBarang = new Barang();

                    objBarang.setIdBarang(Integer.parseInt(detailBarang[0]));
                    objBarang.setNamaBarang(detailBarang[1]);
                    objBarang.setHargaBarang(Integer.parseInt(detailBarang[2]));
                    objBarang.setStok(Integer.parseInt(detailBarang[3]));

                    this.listBarang.add(objBarang);
                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileBarang != null) {
                    fileBarang.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
