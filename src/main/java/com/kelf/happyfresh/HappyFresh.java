package com.kelf.happyfresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HappyFresh {

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        HashMap<String, String> credentials = new HashMap<>(); 
        Scanner scanner = new Scanner(System.in);
        RegisterMenu registerMenu = new RegisterMenu(users, credentials);

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("=== Selamat Datang di HappyFresh ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1 -> registerMenu.tampilkanMenuRegister(scanner); 
                case 2 -> {
                            LoginMenu loginMenu = new LoginMenu(credentials, users, scanner); 
                            User currentUser = loginMenu.login(); 

                            if (currentUser != null) {
                                boolean isLoggedIn = true;

                                while (isLoggedIn) {
                                    System.out.println("=== Menu ===");
                                    System.out.println("1. Lihat Profil");
                                    System.out.println("2. Logout");
                                    System.out.print("Pilih opsi: ");
                                    int menuPilihan = scanner.nextInt();
                                    scanner.nextLine(); 

                                    switch (menuPilihan) {
                                        case 1 -> {
                                            if (currentUser instanceof Seller) {
                                                System.out.println("Anda login sebagai Seller.");
                                                SellerMenu sellerMenu = new SellerMenu((Seller) currentUser, scanner);
                                                sellerMenu.tampilkanMenuSeller();
                                            } else if (currentUser instanceof Buyer) {
                                                System.out.println("Anda login sebagai Buyer.");
                                                Buyer buyer = (Buyer) currentUser;
                                                BuyerMenu buyerMenu = new BuyerMenu(buyer, users);
                                                buyerMenu.tampilkanMenu();
                                            } else if (currentUser instanceof Driver) {
                                                System.out.println("Anda login sebagai Driver.");
                                                Driver driver = (Driver) currentUser;
                                                DriverMenu driverMenu = new DriverMenu(driver, scanner);
                                                driverMenu.tampilkanMenuDriver();
                                            }
                                        }
                                        case 2 -> {
                                            System.out.println("Anda telah logout.");
                                            isLoggedIn = false;
                                        }
                                        default -> System.out.println("Pilihan tidak valid.");
                                    }
                                }
                            }
                        }

                case 3 -> {
                    System.out.println("Terima kasih telah menggunakan HappyFresh!");
                    isRunning = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }
}
