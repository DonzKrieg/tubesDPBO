package com.kelf.happyfresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RegisterMenu {
    private ArrayList<User> users;
    private HashMap<String, String> credentials;

    public RegisterMenu(ArrayList<User> users, HashMap<String, String> credentials) {
        this.users = users;
        this.credentials = credentials;
    }

    public void tampilkanMenuRegister(Scanner scanner) {
        System.out.println("=== Register ===");
        System.out.print("Masukkan ID: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan No HP: ");
        String noHp = scanner.nextLine();
        System.out.println("Pilih tipe pengguna: ");
        System.out.println("1. Seller");
        System.out.println("2. Buyer");
        System.out.println("3. Driver");
        System.out.print("Pilih opsi: ");
        int tipePengguna = scanner.nextInt();
        scanner.nextLine(); 

        switch (tipePengguna) {
            case 1 -> {
                System.out.print("Masukkan alamat: ");
                String alamatSeller = scanner.nextLine();
                System.out.print("Masukkan saldo awal: ");
                int saldoSeller = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Masukkan nama toko: ");
                String namaToko = scanner.nextLine();
                System.out.print("Masukkan deskripsi toko: ");
                String deskripsiToko = scanner.nextLine();
                Seller seller = new Seller(id, nama, noHp, saldoSeller, alamatSeller, namaToko, deskripsiToko);
                users.add(seller);
                credentials.put(id, password); 
                System.out.println("Registrasi Seller berhasil!");
            }
            case 2 -> {
                System.out.print("Masukkan alamat: ");
                String alamatBuyer = scanner.nextLine();
                System.out.print("Masukkan saldo awal: ");
                int saldoBuyer = scanner.nextInt();
                scanner.nextLine();
                Buyer buyer = new Buyer(id, nama, noHp, alamatBuyer, saldoBuyer);
                users.add(buyer);
                credentials.put(id, password); 
                System.out.println("Registrasi Buyer berhasil!");
            }
            case 3 -> {
                System.out.print("Masukkan saldo awal: ");
                int saldoDriver = scanner.nextInt();
                scanner.nextLine();
                Driver driver = new Driver(id, nama, noHp, saldoDriver);
                users.add(driver);
                credentials.put(id, password); 
                System.out.println("Registrasi Driver berhasil!");
            }
            default -> System.out.println("Pilihan tidak valid.");
        }
    }
}
