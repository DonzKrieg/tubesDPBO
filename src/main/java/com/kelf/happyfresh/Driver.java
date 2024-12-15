package com.kelf.happyfresh;

public class Driver extends User {
    private int saldo;
    private boolean hasOrder;
    private String currentOrder;

    public Driver(String id, String nama, String noHp, int saldo) {
        super(id, nama, noHp);
        this.saldo = saldo;
        this.hasOrder = false; 
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public boolean hasOrder() {
        return hasOrder;
    }

    public void setOrder(boolean order) {
        this.hasOrder = order;
    }

    public void menerimaOrderan(int ongkir) {
        if (hasOrder) {
            System.out.println("Anda menerima orderan dengan ongkir sebesar Rp" + ongkir + ". Apakah Anda ingin menerima orderan ini? (y/n)");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String pilihan = scanner.nextLine();

            if (pilihan.equalsIgnoreCase("y")) {
                terimaOrderan(ongkir, true);
            } else if (pilihan.equalsIgnoreCase("n")) {
                terimaOrderan(ongkir, false);
            } else {
                System.out.println("Pilihan tidak valid. Orderan dianggap ditolak.");
                terimaOrderan(ongkir, false);
            }
        } else {
            System.out.println("Tidak ada orderan untuk diterima saat ini.");
        }
    }

    private void terimaOrderan(int ongkir, boolean menerima) {
        if (menerima) {
            saldo += ongkir;
            System.out.println("Orderan diterima. Saldo Anda bertambah sebesar Rp" + ongkir + ". Saldo sekarang: Rp" + saldo);
        } else {
            System.out.println("Orderan ditolak. Tidak ada perubahan saldo.");
        }
    }

    public String getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(String currentOrder) {
        this.currentOrder = currentOrder;
        this.hasOrder = (currentOrder != null);
    }

    public void orderanSelesai() {
        if (hasOrder) {
            System.out.println("Order '" + currentOrder + "' telah selesai.");
            clearOrder(); 
        } else {
            System.out.println("Tidak ada orderan yang sedang dikerjakan.");
        }
    }

    public void clearOrder() {
        currentOrder = null;
        hasOrder = false;
        System.out.println("Orderan telah selesai. Anda siap menerima orderan baru.");
    }
}
