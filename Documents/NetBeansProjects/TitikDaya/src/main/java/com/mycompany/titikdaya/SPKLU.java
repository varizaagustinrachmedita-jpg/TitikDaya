package com.mycompany.titikdaya;

/**
 * Subclass dari Lokasi (Inheritance).
 * Menambahkan data khusus SPKLU: fasilitas dan tautan Google Maps.
 */
public class SPKLU extends Lokasi {
    private String fasilitas;
    private String linkMaps;

    public SPKLU(String nama, String alamat, String kecamatan, String fasilitas) {
        super(nama, alamat, kecamatan); // memanggil constructor superclass
        setFasilitas(fasilitas);
        // Tautan Google Maps dibuat otomatis dari nama tempat + kota
        String kataKunci = (nama + " Bandar Lampung").replace(" ", "+").replace("'", "%27");
        this.linkMaps = "https://www.google.com/maps/search/?api=1&query=" + kataKunci;
    }

    // ===== Getter =====
    public String getFasilitas() {
        return fasilitas;
    }

    public String getLinkMaps() {
        return linkMaps;
    }

    // ===== Setter =====
    public void setFasilitas(String fasilitas) {
        if (fasilitas == null || fasilitas.trim().isEmpty()) {
            this.fasilitas = "-";
        } else {
            this.fasilitas = fasilitas.trim();
        }
    }

    public void setLinkMaps(String linkMaps) {
        this.linkMaps = linkMaps;
    }

    // Override: menambah informasi khusus SPKLU
    @Override
    public void tampilInfo() {
        super.tampilInfo(); // memakai tampilan dari superclass
        System.out.println("Fasilitas : " + fasilitas);
        System.out.println("Google Maps:");
        System.out.println(linkMaps);
    }
}