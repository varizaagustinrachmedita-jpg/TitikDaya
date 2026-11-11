package com.mycompany.titikdaya;

/**
 * Class pengelola data SPKLU (penyimpanan, daftar, dan pencarian).
 * Menggunakan array biasa untuk menyimpan data.
 * Sumber nama & alamat: daftar SPKLU PLN UID Lampung (IDN Times, 17 Maret 2026).
 */
public class DaftarSPKLU {
    private SPKLU[] daftar = new SPKLU[30]; // kapasitas maksimal 30 lokasi
    private int jumlah = 0;                 // jumlah lokasi yang sudah terisi

    public DaftarSPKLU() {
        isiDataAwal();
    }

    private void tambah(SPKLU s) {
        if (jumlah < daftar.length) {
            daftar[jumlah] = s;
            jumlah++;
        }
    }

    // Data SPKLU di Kota Bandar Lampung.
    // Catatan: kecamatan ditentukan dari lokasi alamat; mohon dicek ulang di Google Maps.
    private void isiDataAwal() {
        tambah(new SPKLU("SPKLU El's Coffee Roastery", "Jl. Soekarno Hatta (Bypass)",
                "Rajabasa", "Kafe"));
        tambah(new SPKLU("SPKLU Kantor PLN UP3 Tanjung Karang", "Jl. Diponegoro",
                "Enggal", "Kantor layanan PLN"));
        tambah(new SPKLU("SPKLU Hotel Holiday Inn", "Jl. Kamboja No. 1",
                "Tanjung Karang Timur", "Hotel, restoran, area parkir"));
        tambah(new SPKLU("SPKLU Aston Lampung City Hotel", "Jl. Gatot Subroto No. 81",
                "Teluk Betung Utara", "Hotel"));
        tambah(new SPKLU("SPKLU Kantor PLN UID Lampung", "Jl. Z.A. Pagar Alam",
                "Kedaton", "Kantor PLN"));
        tambah(new SPKLU("SPKLU IIB Darmajaya", "Jl. Z.A. Pagar Alam No. 93",
                "Kedaton", "Kampus"));
        tambah(new SPKLU("SPKLU Transmart Lampung", "Jl. Sultan Agung",
                "Way Halim", "Pusat perbelanjaan"));
        tambah(new SPKLU("SPKLU Mall Boemi Kedaton", "Jl. Teuku Umar No. 1",
                "Kedaton", "Pusat perbelanjaan"));
        tambah(new SPKLU("SPKLU Taman Santap Rumah Kayu", "Jl. Arif Rahman Hakim No. 45",
                "Kedaton", "Restoran"));
    }

    public int getJumlah() {
        return jumlah;
    }

    // Mengambil data berdasarkan nomor urut (mulai dari 1); null jika tidak valid
    public SPKLU getByNomor(int nomor) {
        if (nomor < 1 || nomor > jumlah) {
            return null;
        }
        return daftar[nomor - 1];
    }

    public void tampilDaftar() {
        System.out.println("\n=== DAFTAR LOKASI SPKLU BANDAR LAMPUNG ===");
        for (int i = 0; i < jumlah; i++) {
            System.out.println((i + 1) + ". " + daftar[i].getNama()
                    + " (" + daftar[i].getKecamatan() + ")");
        }
    }

    // Mengecek apakah nama atau kecamatan memuat kata kunci (tidak sensitif huruf besar/kecil)
    private boolean cocok(SPKLU s, String kunci) {
        return s.getNama().toLowerCase().contains(kunci)
                || s.getKecamatan().toLowerCase().contains(kunci);
    }

    // Pencarian berdasarkan nama atau kecamatan
    public SPKLU[] cari(String kataKunci) {
        String kunci = kataKunci.toLowerCase().trim();

        // 1) hitung dulu berapa lokasi yang cocok
        int banyak = 0;
        for (int i = 0; i < jumlah; i++) {
            if (cocok(daftar[i], kunci)) {
                banyak++;
            }
        }

        // 2) isi array hasil sesuai jumlah yang cocok
        SPKLU[] hasil = new SPKLU[banyak];
        int urutan = 0;
        for (int i = 0; i < jumlah; i++) {
            if (cocok(daftar[i], kunci)) {
                hasil[urutan] = daftar[i];
                urutan++;
            }
        }
        return hasil;
    }
}