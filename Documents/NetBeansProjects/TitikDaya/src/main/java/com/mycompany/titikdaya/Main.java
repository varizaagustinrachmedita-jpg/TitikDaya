package com.mycompany.titikdaya;

import java.util.Scanner;

/**
 * Main class: menu utama aplikasi Sistem Informasi Lokasi SPKLU Bandar Lampung.
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DaftarSPKLU data = new DaftarSPKLU(); // instansiasi object
        boolean jalan = true;

        System.out.println("=======================================================");
        System.out.println(" SISTEM INFORMASI LOKASI PENGISIAN DAYA KENDARAAN LISTRIK");
        System.out.println("                    BANDAR LAMPUNG");
        System.out.println("=======================================================");

        while (jalan) {
            System.out.println("\n========== MENU UTAMA ==========");
            System.out.println("1. Lihat Daftar Lokasi SPKLU");
            System.out.println("2. Cari Lokasi (nama / kecamatan)");
            System.out.println("3. Lihat Detail Lokasi");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");

            String pilihan = input.nextLine().trim();

            switch (pilihan) {
                case "1":
                    data.tampilDaftar();
                    break;
                case "2":
                    menuCari(input, data);
                    break;
                case "3":
                    menuDetail(input, data);
                    break;
                case "4":
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    jalan = false;
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia. Masukkan angka 1-4.");
            }
        }
        input.close();
    }

    // Membaca input angka. Mengembalikan -1 jika input bukan angka.
    private static int bacaAngka(Scanner input) {
        String teks = input.nextLine().trim();
        if (teks.isEmpty() || teks.length() > 3) {
            return -1;
        }
        for (int i = 0; i < teks.length(); i++) {
            if (!Character.isDigit(teks.charAt(i))) {
                return -1;
            }
        }
        return Integer.parseInt(teks);
    }

    // Menu 2: pencarian berdasarkan nama atau kecamatan, lalu pilih lokasi untuk detail
    private static void menuCari(Scanner input, DaftarSPKLU data) {
        System.out.print("Masukkan nama lokasi atau kecamatan: ");
        String kunci = input.nextLine();

        if (kunci.trim().isEmpty()) {
            System.out.println("Kata kunci tidak boleh kosong.");
            return;
        }

        SPKLU[] hasil = data.cari(kunci);

        if (hasil.length == 0) {
            System.out.println("Data tidak tersedia untuk kata kunci \"" + kunci.trim() + "\".");
            return;
        }

        System.out.println("\nDitemukan " + hasil.length + " lokasi:");
        for (int i = 0; i < hasil.length; i++) {
            System.out.println((i + 1) + ". " + hasil[i].getNama()
                    + " (" + hasil[i].getKecamatan() + ")");
        }

        System.out.print("Pilih nomor lokasi untuk melihat detail (0 = kembali ke menu): ");
        int nomor = bacaAngka(input);

        if (nomor == -1) {
            System.out.println("Input harus berupa angka.");
        } else if (nomor == 0) {
            return;
        } else if (nomor >= 1 && nomor <= hasil.length) {
            tampilDetail(hasil[nomor - 1]);
        } else {
            System.out.println("Nomor lokasi tidak valid.");
        }
    }

    // Menu 3: pilih salah satu lokasi dari daftar lengkap untuk melihat detail
    private static void menuDetail(Scanner input, DaftarSPKLU data) {
        data.tampilDaftar();
        System.out.print("Pilih nomor lokasi (1-" + data.getJumlah() + "): ");
        int nomor = bacaAngka(input);

        if (nomor == -1) {
            System.out.println("Input harus berupa angka.");
        } else {
            SPKLU dipilih = data.getByNomor(nomor);
            if (dipilih == null) {
                System.out.println("Nomor lokasi tidak valid.");
            } else {
                tampilDetail(dipilih);
            }
        }
    }

    // Menampilkan detail lokasi beserta tautan Google Maps
    private static void tampilDetail(SPKLU s) {
        System.out.println("\n--- Detail Lokasi ---");
        s.tampilInfo();
        System.out.println("(Buka tautan Google Maps di atas melalui browser untuk melihat titik lokasi dan petunjuk arah)");
    }
}