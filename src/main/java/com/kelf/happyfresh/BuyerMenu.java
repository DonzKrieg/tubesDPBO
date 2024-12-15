package com.kelf.happyfresh;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyerMenu {
    private Buyer buyer;
    private List<User> users;

    public BuyerMenu(Buyer buyer, List<User> users) {
        this.buyer = buyer;
        this.users = users;
    }

    public void tampilkanMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isBuyerActive = true;

        while (isBuyerActive) {
            System.out.println("=== Menu Buyer ===");
            System.out.println("=== Produk Tersedia ===");
            for (User user : users) {
                if (user instanceof Seller seller) {
                    seller.getInfoProduk(); 
                }
            }
            System.out.println("=======================");
            System.out.println("1. Beli Produk");
            System.out.println("2. Tambahkan ke Keranjang");
            System.out.println("3. Tampilkan Keranjang");
            System.out.println("4. Tambahkan ke Favorit");
            System.out.println("5. Tampilkan Favorit");
            System.out.println("6. Tambah Saldo");
            System.out.println("7. Logout");
            System.out.print("Pilih opsi: ");
            int buyerChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (buyerChoice) {
                case 1 -> beliProduk(scanner);
                case 2 -> {
                    System.out.print("Masukkan nama produk yang ingin ditambahkan ke keranjang: ");
                    String namaProdukKeranjang = scanner.nextLine();
                    Product produkKeranjang = null;
                        for (User user : users) {
                            if (user instanceof Seller seller) {
                                produkKeranjang = seller.cariProduk(namaProdukKeranjang);
                                if (produkKeranjang != null) break;
                            }
                    }

                    if (produkKeranjang != null) {
                        buyer.tambahkanKeKeranjang(produkKeranjang);
                        System.out.println("Produk berhasil ditambahkan ke keranjang.");
                    } else {
                        System.out.println("Produk tidak ditemukan.");
                    }
                }

                case 3 -> buyer.tampilkanKeranjang();
                case 4 -> tambahKeFavorit(scanner);
                case 5 -> buyer.tampilkanFavorit();
                case 6 -> tambahSaldo(scanner);
                case 7 -> {
                    System.out.println("Logout berhasil.");
                    isBuyerActive = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void beliProduk(Scanner scanner) {
        System.out.println("=== Beli Produk ===");
        System.out.print("Masukkan nama produk yang ingin dibeli: ");
        String namaProdukBeli = scanner.nextLine();
        System.out.print("Masukkan jumlah yang ingin dibeli: ");
        int jumlahBeli = scanner.nextInt();
        System.out.print("Masukkan jarak pengiriman (km): ");
        double jarak = scanner.nextDouble();
        scanner.nextLine(); 

        Product produkBeli = null;
        Seller sellerBeli = null;

        for (User user : users) {
            if (user instanceof Seller seller) {
                produkBeli = seller.cariProduk(namaProdukBeli);
                if (produkBeli != null) {
                    sellerBeli = seller; 
                    break;
                }
            }
        }

        if (produkBeli != null) {
            if (produkBeli.getStok() < jumlahBeli) {
                System.out.println("Stok produk tidak mencukupi.");
                return;
            }

            int totalHarga = produkBeli.getHarga() * jumlahBeli;
            int ongkir;

            if (jarak < 5) {
                ongkir = 10000;
            } else if (jarak < 10) {
                ongkir = 20000;
            } else {
                System.out.println("Maaf, pesanan tidak dapat diantar untuk jarak di atas 10 km.");
                return;
            }

            int totalBayar = totalHarga + ongkir;

            if (buyer.getSaldo() >= totalBayar) {
                pilihDriver(scanner, ongkir, totalBayar);
                produkBeli.setStok(produkBeli.getStok() - jumlahBeli);
                System.out.println("Stok produk berhasil diperbarui.");
                if (sellerBeli != null) {
                    sellerBeli.setSaldo(sellerBeli.getSaldo() + totalHarga);
                    System.out.println("Saldo penjual " + sellerBeli.getNamaToko() + " berhasil diperbarui: Rp " + totalHarga);
                }
            } else {
                System.out.println("Saldo Anda tidak cukup untuk membeli produk ini.");
            }
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }



    private void pilihDriver(Scanner scanner, int ongkir, int totalBayar) {
        System.out.println("=== Pilih Driver ===");
        List<Driver> availableDrivers = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Driver driver) {
                availableDrivers.add(driver);
            }
        }

        if (availableDrivers.isEmpty()) {
            System.out.println("Tidak ada driver tersedia saat ini.");
        } else {
            for (int i = 0; i < availableDrivers.size(); i++) {
                Driver driver = availableDrivers.get(i);
                System.out.println((i + 1) + ". " + driver.getNama() + " - Saldo: Rp" + driver.getSaldo());
            }

            System.out.print("Pilih Driver (1-" + availableDrivers.size() + "): ");
            int driverIndex = scanner.nextInt() - 1;
            scanner.nextLine(); 

            if (driverIndex >= 0 && driverIndex < availableDrivers.size()) {
                Driver selectedDriver = availableDrivers.get(driverIndex);

                buyer.setSaldo(buyer.getSaldo() - totalBayar);
                selectedDriver.setSaldo(selectedDriver.getSaldo() + ongkir);

                System.out.println("Pembelian berhasil!");
                System.out.println("Driver " + selectedDriver.getNama() + " akan mengantarkan pesanan Anda.");
            } else {
                System.out.println("Pilihan driver tidak valid.");
            }
        }
    }

    private void tambahKeFavorit(Scanner scanner) {
        System.out.print("Masukkan nama produk yang ingin ditambahkan ke favorit: ");
        String namaProdukFavorit = scanner.nextLine();
        Product produkFavorit = null;
        for (User user : users) {
            if (user instanceof Seller seller) {
                produkFavorit = seller.cariProduk(namaProdukFavorit);
                if (produkFavorit != null) break;
            }
        }

        if (produkFavorit != null) {
            buyer.tambahkanKeFavorit(produkFavorit);
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    private void tambahSaldo(Scanner scanner) {
        System.out.print("Masukkan jumlah saldo yang ingin ditambahkan: ");
        int saldoTambah = scanner.nextInt();
        buyer.tambahSaldo(saldoTambah);
    }

}
 
