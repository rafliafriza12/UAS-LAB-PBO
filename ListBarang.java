import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ListBarang {
    private ArrayList<Barang> barang;
    
    public ListBarang(){
        barang = new ArrayList<Barang>();
    }

    private void loadBarangFromFile() {
        String pathFile = "assets/barang/barang.txt";
        BufferedReader fileBarang = null;
        try {
            fileBarang = new BufferedReader(new FileReader(pathFile));
            String bacaBaris;
            boolean loop = true;
            while(loop){
                while ((bacaBaris = fileBarang.readLine()) != null) {
                    String[] detailBarang = bacaBaris.split(" ");
                    Barang objBarang = new Barang();

                    objBarang.setIdBarang(Integer.parseInt(detailBarang[0]));
                    objBarang.setNamaBarang(detailBarang[1]);
                    objBarang.setHargaBarang(detailBarang[2]);
                    objBarang.setStok(detailBarang[3]);

                    barang.add(objBarang);
                }
                loop=false;
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

    public void tampilkanBarang(){
        this.loadBarangFromFile();

        this.sorting();

        this.barang.forEach((i) -> {
            System.out.println("Id Barang\t: "+i.getIdBarang());
            System.out.println("Nama Barang\t: "+i.getNamaBarang());
            System.out.println("Harga\t\t: "+i.getHargaBarang());
            System.out.println("Stok\t\t: "+i.getStok());
            System.out.println("\n");
        });

        this.barang.clear();
    }

    protected void tambahBarang(){
        this.loadBarangFromFile();

        Barang tambahBarang = new Barang();
        Scanner scan = new Scanner(System.in);

        System.out.println("Tambah Barang");
        System.out.print("Id barang\t: ");
        int idBarang = scan.nextInt();
        scan.nextLine();
        System.out.print("Nama Barang\t: ");
        String namaBarang = scan.nextLine();
        System.out.print("Harga Barang\t: ");
        String hargaBarang = scan.nextLine();
        System.out.print("Stok Barang\t: ");
        String stokBarang = scan.nextLine();
        System.out.println("\n");

        tambahBarang.setIdBarang(idBarang);
        tambahBarang.setNamaBarang(namaBarang);
        tambahBarang.setHargaBarang(hargaBarang);
        tambahBarang.setStok(stokBarang);


        this.barang.add(tambahBarang);

        writeToFile();

        this.barang.clear();

    }

    protected void hapusBarang(){
        this.loadBarangFromFile();

        Scanner scan = new Scanner(System.in);
        int index = 0;

        System.out.print("Masukkan id barang yang ingin dihapus : ");
        int hapus = scan.nextInt();

        for(int i = 0; i < this.barang.size();i++){
            if(hapus == this.barang.get(i).getIdBarang()){
                index = i;
                this.barang.remove(index);
                break;
            }
            else if((i==this.barang.size()-1)&& index==0){
                System.out.println("Barang Tidak Ditemukan");
                break;
            }
        }

        writeToFile();

        this.barang.clear();
    }

    protected void editBarang(){
        this.loadBarangFromFile();

        Scanner scan = new Scanner(System.in);
        int index = 0;
        Barang editBarang = new Barang();

        System.out.print("Masukkan id barang yang ingin di edit : ");
        int edit = scan.nextInt();

        scan.nextLine();

        for(int i = 0 ; i < this.barang.size();i++){
            if(edit == this.barang.get(i).getIdBarang()){
                index = i;
                System.out.print("Masukkan nama barang baru : ");
                String namaBarangBaru = scan.nextLine();
        
                System.out.print("Masukkan harga baru : ");
                String hargaBaru = scan.nextLine();
        
                System.out.print("Masukkan stok baru : ");
                String stokBaru = scan.nextLine();
        
                editBarang.setIdBarang(edit);
                editBarang.setNamaBarang(namaBarangBaru);
                editBarang.setHargaBarang(hargaBaru);
                editBarang.setStok(stokBaru);
        
                this.barang.set(index, editBarang);
                break;
            }
            else if((i==this.barang.size()-1)&& index==0){
                System.out.println("Barang Tidak Ditemukan");
                break;
            }
        }   


        writeToFile();

        this.barang.clear();
    }

    private void writeToFile(){
        String pathFileBarang = "assets/barang/barang.txt";
        BufferedWriter fileBarang = null;

        try{
            fileBarang = new BufferedWriter(new FileWriter(pathFileBarang));
    
            for(Barang i : this.barang){
                fileBarang.write(i.getIdBarang()+" ");
                fileBarang.write(i.getNamaBarang()+" ");
                fileBarang.write(i.getHargaBarang()+" ");
                fileBarang.write(i.getStok());
                fileBarang.newLine();
            }
    
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(fileBarang!=null){
                    fileBarang.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }   
    }

    private void sorting(){
        for(int i = 0; i < this.barang.size(); i++){
            for(int j = 0; j < this.barang.size()-i-1;j++){
                if(this.barang.get(j).getIdBarang() > this.barang.get(j+1).getIdBarang()){
                    Barang temp = this.barang.get(j);
                    Barang temp1 = this.barang.get(j+1);
                    this.barang.set(j, temp1);
                    this.barang.set(j+1, temp);
                }
            }
        }
    }
}
