package javaapplication1;

import java.util.ArrayList;
import java.util.Random;

public class Oyuncu {

    private int oyuncuID;
    private String oyuncuAdi;
    private int skor;
    private ArrayList<SavasAraclari> kartListesi;

    // Parametresiz Constructor
    public Oyuncu() {
        this.oyuncuID = 0;
        this.oyuncuAdi = "Yok";
        this.skor = 0;
        this.kartListesi = new ArrayList<>();
    }

    // Parametreli Constructor
    public Oyuncu(int oyuncuID, String oyuncuAdi, int skor) {
        this.oyuncuID = oyuncuID;
        this.oyuncuAdi = oyuncuAdi;
        this.skor = skor;
        this.kartListesi = new ArrayList<>();
    }

    // Skoru Gösterme Metodu
    public int SkorGoster() {
        System.out.println(oyuncuAdi + " Skoru: " + skor);
        return skor;
    }

    // Skoru Ekleme Metodu
    public void SkorEkle(int puan) {
        this.skor += puan;
    }

    // Kart Ekleme Metodu
    public void KartEkle(SavasAraclari kart) {
        kartListesi.add(kart);
    }

    // Kart Silme Metodu
    public boolean KartSil(SavasAraclari kart) {
        return kartListesi.remove(kart);
    }

    // Kartları Gösterme Metodu
    public ArrayList<SavasAraclari> KartlariGoster() {
        System.out.println(oyuncuAdi + " elindeki kartlar:");
        for (int i = 0; i < kartListesi.size(); i++) {
            System.out.println((i + 1) + ". Kart: ");
            kartListesi.get(i).KartPuaniGoster();
        }
        return kartListesi;
    }

    public void KartKontrolGuncelle() {
        int secilmeyenKartSayisi = 0;
        for (SavasAraclari kart : kartListesi) {
            if (!kart.isSecildiMi()) {
                secilmeyenKartSayisi += 1;
            }
        }
        if (secilmeyenKartSayisi < 3) {
            for (SavasAraclari kart : kartListesi) {
                kart.setSecildiMi(false);
            }
            System.out.println("Secilebilecek 3 Kart Yok, Tum Kartlar Secilebilir!");
        }
    }

    public boolean KartKontrol(int[] secimler) {
        for (int index : secimler) {
            if (index >= 0 && index < kartListesi.size() && kartListesi.get(index).isSecildiMi()) {
                return false;
            }
        }
        return true;
    }

    // Kart Seçme Metodu (Kullanıcı)
    public ArrayList<SavasAraclari> KartSec(int[] secimler) {
        ArrayList<SavasAraclari> secilenKartlar = new ArrayList<>();
        for (int index : secimler) {
            if (index >= 0 && index < kartListesi.size()) {
                secilenKartlar.add(kartListesi.get(index));
                kartListesi.get(index).setSecildiMi(true);
            }
        }
        return secilenKartlar;
    }

    // Kart Seçme Metodu (Bilgisayar)
    public ArrayList<SavasAraclari> KartSecRandom() {
        ArrayList<SavasAraclari> secilenKartlar = new ArrayList<>();
        Random random = new Random();
        while (secilenKartlar.size() < 3) {
            int index = random.nextInt(kartListesi.size());
            if (!secilenKartlar.contains(kartListesi.get(index))) {
                secilenKartlar.add(kartListesi.get(index));
            }
        }
        return secilenKartlar;
    }

    //Getter ve Setter Metodları
    public int getOyuncuID() {
        return oyuncuID;
    }

    public void setOyuncuID(int oyuncuID) {
        this.oyuncuID = oyuncuID;
    }

    public String getOyuncuAdi() {
        return oyuncuAdi;
    }

    public void setOyuncuAdi(String oyuncuAdi) {
        this.oyuncuAdi = oyuncuAdi;
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public ArrayList<SavasAraclari> getKartListesi() {
        return kartListesi;
    }

    public void setKartListesi(ArrayList<SavasAraclari> kartListesi) {
        this.kartListesi = kartListesi;
    }
}
