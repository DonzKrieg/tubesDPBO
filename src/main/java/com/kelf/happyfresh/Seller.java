package com.kelf.happyfresh;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Seller extends User {
    private int saldo;
    private String alamat;
    private String namaToko;
    private String deskripsiToko;
    private ArrayList<Product> daftarProduk;

    public Seller(String id, String nama, String noHp, int saldo, String alamat, String namaToko, String deskripsiToko) {
        super(id, nama, noHp);
        this.saldo = saldo;
        this.alamat = alamat;
        this.namaToko = namaToko;
        this.deskripsiToko = deskripsiToko;
        this.daftarProduk = new ArrayList<>();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getDeskripsiToko() {
        return deskripsiToko;
    }

    public void setDeskripsiToko(String deskripsiToko) {
        this.deskripsiToko = deskripsiToko;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Product> getDaftarProduk() {
        return daftarProduk;
    }

    public void tambahkanProduk(Product produk) {
        daftarProduk.add(produk);
        System.out.println("Produk '" + produk.getNamaProduk() + "' berhasil ditambahkan!");
    }

    public void hapusProduk(String namaProduk) {
        Product produkUntukDihapus = cariProduk(namaProduk);
        if (produkUntukDihapus != null) {
            daftarProduk.remove(produkUntukDihapus);
            System.out.println("Produk '" + namaProduk + "' berhasil dihapus!");
        } else {
            System.out.println("Produk '" + namaProduk + "' tidak ditemukan!");
        }
    }

    public void editProduk(String namaProduk, String namaBaru, String jenisBaru, int hargaBaru, int stokBaru, String deskripsiBaru) {
        Product produkUntukDiedit = cariProduk(namaProduk);
        if (produkUntukDiedit != null) {
            if (namaBaru != null && !namaBaru.trim().isEmpty()) {
                produkUntukDiedit.setNamaProduk(namaBaru);
            }
            if (jenisBaru != null && !jenisBaru.trim().isEmpty()) {
                produkUntukDiedit.setJenis(jenisBaru);
            }
            if (hargaBaru > 0) {
                produkUntukDiedit.setHarga(hargaBaru);
            }
            if (stokBaru >= 0) {
                produkUntukDiedit.setStok(stokBaru);
            }
            if (deskripsiBaru != null && !deskripsiBaru.trim().isEmpty()) {
                produkUntukDiedit.setDeskripsiProduk(deskripsiBaru);
            }
            System.out.println("Produk '" + namaProduk + "' berhasil diedit!");
        } else {
            System.out.println("Produk '" + namaProduk + "' tidak ditemukan!");
        }
    }

    public void getInfoProduk() {
        System.out.println("Daftar Produk dari " + namaToko + ":");
        if (daftarProduk.isEmpty()) {
            System.out.println("- Tidak ada produk yang terdaftar.");
        } else {
            for (Product produk : daftarProduk) {
                System.out.println("- Nama Produk: " + produk.getNamaProduk());
                System.out.println("  Jenis: " + produk.getJenis());
                System.out.println("  Harga: Rp" + produk.getHarga());
                System.out.println("  Stok: " + produk.getStok());
                System.out.println("  Deskripsi: " + produk.getDeskripsiProduk());
                System.out.println();
            }
        }
    }

    public void tambahkanStok(String namaProduk, int jumlahStok) {
        if (jumlahStok <= 0) {
            System.out.println("Jumlah stok yang ditambahkan harus lebih dari 0.");
            return;
        }

        Product produkUntukDitambahStok = cariProduk(namaProduk);
        if (produkUntukDitambahStok != null) {
            produkUntukDitambahStok.setStok(produkUntukDitambahStok.getStok() + jumlahStok);
            System.out.println("Stok produk '" + namaProduk + "' berhasil ditambahkan sebanyak " + jumlahStok + ".");
        } else {
            System.out.println("Produk '" + namaProduk + "' tidak ditemukan!");
        }
    }

    public Product cariProduk(String namaProduk) {
        for (Product produk : daftarProduk) {
            if (produk.getNamaProduk().equalsIgnoreCase(namaProduk)) {
                return produk;
            }
        }
        return null;
    }

    public void getInfo() {
        int consoleWidth = 60;
        int padding = (consoleWidth - namaToko.length()) / 2;
        Locale myIndonesianLocale = new Locale("in", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(myIndonesianLocale);
        formatter.setMaximumFractionDigits(0);
        String formattedPrice = formatter.format(saldo);
        System.out.println("=".repeat(consoleWidth));
        System.out.printf("%" + (padding + namaToko.length()) + "s%n", namaToko);
        System.out.println("=".repeat(consoleWidth));
        System.out.println("Nama penjual: " + super.getNama());
        System.out.println("Id: " + super.getId());
        System.out.println("Saldo: " + formattedPrice);
        System.out.println(deskripsiToko);
        System.out.println("=".repeat(consoleWidth));
    }
    public ArrayList<Product> getProduk() {
        return daftarProduk;
    }
}