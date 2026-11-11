package com.mycompany.titikdaya;

/**
 * Superclass: menyimpan data umum sebuah lokasi.
 * Menerapkan Encapsulation (atribut private + getter/setter).
 */
public class Lokasi {
    private String nama;
    private String alamat;
    private String kecamatan;

    public Lokasi(String nama, String alamat, String kecamatan) {
        setNama(nama);
        setAlamat(alamat);
        setKecamatan(kecamatan);
    }

    // ===== Getter =====
    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    // ===== Setter (dengan validasi sederhana) =====
    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            this.nama = "Tanpa Nama";
        } else {
            this.nama = nama.trim();
        }
    }

    public void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty()) {
            this.alamat = "-";
        } else {
            this.alamat = alamat.trim();
        }
    }

    public void setKecamatan(String kecamatan) {
        if (kecamatan == null || kecamatan.trim().isEmpty()) {
            this.kecamatan = "-";
        } else {
            this.kecamatan = kecamatan.trim();
        }
    }

    // Method yang akan di-override oleh subclass
    public void tampilInfo() {
        System.out.println("Nama      : " + nama);
        System.out.println("Alamat    : " + alamat);
        System.out.println("Kecamatan : " + kecamatan);
    }
}
