package javaapplication1;

import java.util.ArrayList;
import java.util.Random;

import java.util.HashMap;
import java.util.Map;

public class Oyun {

    private Oyuncu oyuncu;
    private Oyuncu bilgisayar;
    private int toplamHamleSayisi;

    // Constructor
    public Oyun(Oyuncu oyuncu, Oyuncu bilgisayar, int toplamHamleSayisi) {
        this.oyuncu = oyuncu;
        this.bilgisayar = bilgisayar;
        this.toplamHamleSayisi = toplamHamleSayisi;
    }

    // Saldırı Hesaplama Metodu
    private Map<String, Object> SaldiriHesapla(SavasAraclari saldiran, SavasAraclari savunan) {
        Map<String, Object> results = new HashMap<>();

        String hasarDetay = "Ozel Durum Yok.";
        int hasar = saldiran.vurus;
        if (saldiran instanceof Hava && savunan instanceof Kara) {
            System.out.println("Havadan Karaya Vurus Avantaji!");
            hasarDetay = "Havadan Karaya Vurus Avantaji!";
            hasar += ((Hava) saldiran).karaVurusAvantaji;
        } else if (saldiran instanceof Siha && savunan instanceof Deniz) {
            System.out.println("OZEL AVANTAJ! Havadan Denize Vurus Avantaji!");
            hasarDetay = "OZEL AVANTAJ! Havadan Denize Vurus Avantaji!";
            hasar += ((Siha) saldiran).denizVurusAvantaji;
        } else if (saldiran instanceof Kara && savunan instanceof Deniz) {
            System.out.println("Karadan Denize Vurus Avantaji!");
            hasarDetay = "Karadan Denize Vurus Avantaji!";
            hasar += ((Kara) saldiran).denizVurusAvantaji;
        } else if (saldiran instanceof Kfs && savunan instanceof Hava) {
            System.out.println("OZEL AVANTAJ! Karadan Havaya Vurus Avantaji!");
            hasarDetay = "OZEL AVANTAJ! Karadan Havaya Vurus Avantaji!";
            hasar += ((Kfs) saldiran).havaVurusAvantaji;
        } else if (saldiran instanceof Deniz && savunan instanceof Hava) {
            System.out.println("Denizden Havaya Vurus Avantaji!");
            hasarDetay = "Denizden Havaya Vurus Avantaji!";
            hasar += ((Deniz) saldiran).havaVurusAvantaji;
        } else if (saldiran instanceof Sida && savunan instanceof Kara) {
            System.out.println("OZEL AVANTAJ! Denizden Karaya Vurus Avantaji!");
            hasarDetay = "OZEL AVANTAJ! Denizden Karaya Vurus Avantaji!";
            hasar += ((Sida) saldiran).karaVurusAvantaji;
        }

        results.put("int", hasar);
        results.put("string", hasarDetay);
        return results;
    }

    private Map<String, Object> KartKarsilastir(SavasAraclari oyuncuKart, SavasAraclari bilgisayarKart) {
        Map<String, Object> results = new HashMap<>();

        // Oyuncu Hasar, Bilgisayar Hasar
        String[] karsilasmalar = new String[2];

        boolean oyuncuKartElendiMi;
        boolean bilgisayarKartElendiMi;

        Map<String, Object> results2 = SaldiriHesapla(oyuncuKart, bilgisayarKart);
        int oyuncuHasar = (int) results2.get("int");
        String oyuncuHasarDetay = (String) results2.get("string");

        Map<String, Object> results3 = SaldiriHesapla(bilgisayarKart, oyuncuKart);
        int bilgisayarHasar = (int) results3.get("int");
        String bilgisayarHasarDetay = (String) results3.get("string");

        karsilasmalar[0] = String.valueOf(oyuncuHasar);
        karsilasmalar[1] = String.valueOf(bilgisayarHasar);
        System.out.println("Oyuncudan Bilgisayara Hasar: " + oyuncuHasar);
        System.out.println("Bilgisayardan Oyuncuya Hasar: " + bilgisayarHasar);

        // Dayaniklilik Guncelle
        oyuncuKartElendiMi = oyuncuKart.DurumGuncelle(bilgisayarHasar);
        bilgisayarKartElendiMi = bilgisayarKart.DurumGuncelle(oyuncuHasar);

        // Skor Guncelle
        int oyuncuKartSeviyePuani = oyuncuKart.getSeviyePuani();
        int bilgisayarKartSeviyePuani = bilgisayarKart.getSeviyePuani();
        if (bilgisayarKartElendiMi) {
            if (bilgisayarKartSeviyePuani <= 10) {
                oyuncuKart.SeviyePuaniEkle(10);
                oyuncu.SkorEkle(10);
            } else {
                oyuncuKart.SeviyePuaniEkle(bilgisayarKartSeviyePuani);
                oyuncu.SkorEkle(bilgisayarKartSeviyePuani);
            }
        }
        if (oyuncuKartElendiMi) {
            if (oyuncuKartSeviyePuani <= 10) {
                bilgisayarKart.SeviyePuaniEkle(10);
                bilgisayar.SkorEkle(10);
            } else {
                bilgisayarKart.SeviyePuaniEkle(oyuncuKartSeviyePuani);
                bilgisayar.SkorEkle(oyuncuKartSeviyePuani);
            }
        }

        // Elenen Kartlari Döndür
        boolean[] elendiMi = {oyuncuKartElendiMi, bilgisayarKartElendiMi};

        results.put("booleans", elendiMi);
        results.put("strings", karsilasmalar);
        results.put("oyuncuHasarDetay", oyuncuHasarDetay);
        results.put("bilgisayarHasarDetay", bilgisayarHasarDetay);

        return results;
    }

    public String[][] HamleYap(int hamleSayisi, ArrayList<SavasAraclari> oyuncuSecilenKartlar, ArrayList<SavasAraclari> bilgisayarSecilenKartlar) {
        String[] oyuncuKarsilasanKartlar = new String[3];
        String[] bilgisayarKarsilasanKartlar = new String[3];
        String[] oyuncuHamleSonuKarsilasanKartlar = new String[3];
        String[] bilgisayarHamleSonuKarsilasanKartlar = new String[3];
        String[] skorlar = new String[2];
        String[] oyuncuElenenKartlar = {"", "", ""};
        String[] bilgisayarElenenKartlar = {"", "", ""};
        String[] tempHasarlar;
        String[] hasarlar = new String[6];
        String[] hasarDetaylar = new String[6];

        System.out.println("------------------" + (hamleSayisi + 1) + ". Hamle------------------");

        boolean[] elenenlerOyuncu = new boolean[3];
        boolean[] elenenlerBilgisayar = new boolean[3];
        boolean[] elenenlerTemp;
        // Kartlar Karsilastiriliyor
        for (int i = 0; i < oyuncuSecilenKartlar.size(); i++) {
            System.out.println("------" + (i + 1) + ". Karsilasma------");
            System.out.println("Oyuncunun " + (i + 1) + ". Karti: ");
            oyuncuKarsilasanKartlar[i] = oyuncuSecilenKartlar.get(i).KartPuaniGoster();
            System.out.println("-");
            System.out.println("Bilgisayarin " + (i + 1) + ". Karti: ");
            bilgisayarKarsilasanKartlar[i] = bilgisayarSecilenKartlar.get(i).KartPuaniGoster();

            System.out.println("------------------");
            System.out.println("Kartlar Savasiyor!");

            Map<String, Object> results = KartKarsilastir(oyuncuSecilenKartlar.get(i), bilgisayarSecilenKartlar.get(i));

            elenenlerTemp = (boolean[]) results.get("booleans");
            elenenlerOyuncu[i] = elenenlerTemp[0];
            elenenlerBilgisayar[i] = elenenlerTemp[1];

            tempHasarlar = (String[]) results.get("strings");
            hasarlar[2 * i] = tempHasarlar[0];
            hasarlar[2 * i + 1] = tempHasarlar[1];

            hasarDetaylar[2 * i] = (String) results.get("oyuncuHasarDetay");
            hasarDetaylar[2 * i + 1] = (String) results.get("bilgisayarHasarDetay");

            System.out.println("------------------");
            System.out.println("Hamle Sonrasi Oyuncu Karti: ");
            oyuncuHamleSonuKarsilasanKartlar[i] = oyuncuSecilenKartlar.get(i).KartPuaniGoster();
            System.out.println("-");
            System.out.println("Hamle Sonrasi Bilgisayar Karti: ");
            bilgisayarHamleSonuKarsilasanKartlar[i] = bilgisayarSecilenKartlar.get(i).KartPuaniGoster();

            System.out.println("-----------------------------------");
        }

        for (int i = 0; i < 3; i++) {
            if (elenenlerOyuncu[i]) {
                if (oyuncu.KartSil(oyuncuSecilenKartlar.get(i))) {
                    System.out.println("Oyuncu Karti Silindi!");
                    oyuncuElenenKartlar[i] = "  ---  ELENDI!";
                }
            }
            if (elenenlerBilgisayar[i]) {
                if (bilgisayar.KartSil(bilgisayarSecilenKartlar.get(i))) {
                    System.out.println("Bilgisayar Karti Silindi!");
                    bilgisayarElenenKartlar[i] = "   ---  ELENDI!";
                }
            }
        }

        // Skorlari Goster
        System.out.println("----------------");
        System.out.println("Skorlar:");
        skorlar[0] = String.valueOf(oyuncu.SkorGoster());
        skorlar[1] = String.valueOf(bilgisayar.SkorGoster());
        System.out.println("----------------");

        return new String[][]{oyuncuKarsilasanKartlar, bilgisayarKarsilasanKartlar, oyuncuHamleSonuKarsilasanKartlar, bilgisayarHamleSonuKarsilasanKartlar, skorlar, oyuncuElenenKartlar, bilgisayarElenenKartlar, hasarlar, hasarDetaylar};
    }

    public SavasAraclari DestedenKartVer(int baslangicSeviyePuani, int i) {
        Random random = new Random();

        switch (i) {
            case 1 -> {
                switch (random.nextInt(3)) {
                    case 0 -> {
                        return new Ucak(baslangicSeviyePuani);
                    }
                    case 1 -> {
                        return new Obus(baslangicSeviyePuani);
                    }
                    case 2 -> {
                        return new Firkateyn(baslangicSeviyePuani);
                    }
                    default ->
                        throw new AssertionError();
                }
            }
            case 2 -> {
                switch (random.nextInt(6)) {
                    case 0 -> {
                        return new Ucak(baslangicSeviyePuani);
                    }
                    case 1 -> {
                        return new Obus(baslangicSeviyePuani);
                    }
                    case 2 -> {
                        return new Firkateyn(baslangicSeviyePuani);
                    }
                    case 3 -> {
                        return new Siha(baslangicSeviyePuani);
                    }
                    case 4 -> {
                        return new Kfs(baslangicSeviyePuani);
                    }
                    case 5 -> {
                        return new Sida(baslangicSeviyePuani);
                    }
                    default ->
                        throw new AssertionError();
                }
            }
            default ->
                throw new AssertionError();
        }
    }

    public void YeniKartVer(int baslangicSeviyePuani) {
        if (oyuncu.getSkor() >= 20) {
            oyuncu.KartEkle(DestedenKartVer(baslangicSeviyePuani, 2));
        } else {
            oyuncu.KartEkle(DestedenKartVer(baslangicSeviyePuani, 1));
        }
        if (bilgisayar.getSkor() >= 20) {
            bilgisayar.KartEkle(DestedenKartVer(baslangicSeviyePuani, 2));
        } else {
            bilgisayar.KartEkle(DestedenKartVer(baslangicSeviyePuani, 1));
        }

        // 1 Kart Kaldiysa 2 Kart Verilmesi Durumu
        if (oyuncu.getKartListesi().size() == 2 && oyuncu.getSkor() >= 20) {
            oyuncu.KartEkle(DestedenKartVer(baslangicSeviyePuani, 2));
        } else if (oyuncu.getKartListesi().size() == 2) {
            oyuncu.KartEkle(DestedenKartVer(baslangicSeviyePuani, 1));
        }
        if (bilgisayar.getKartListesi().size() == 2 && bilgisayar.getSkor() >= 20) {
            bilgisayar.KartEkle(DestedenKartVer(baslangicSeviyePuani, 2));
        } else if (bilgisayar.getKartListesi().size() == 2) {
            bilgisayar.KartEkle(DestedenKartVer(baslangicSeviyePuani, 1));
        }
    }

    public void KartlariDagit(int baslangicSeviyePuani) {
        for (int i = 0; i < 6; i++) {
            oyuncu.KartEkle(DestedenKartVer(baslangicSeviyePuani, 1));
            bilgisayar.KartEkle(DestedenKartVer(baslangicSeviyePuani, 1));
        }
    }

    public ArrayList<SavasAraclari> KartSecimi(Oyuncu oyuncu, int[] secimler) {
        System.out.println(oyuncu.getOyuncuAdi() + " Kart Seciyor...");
        oyuncu.KartlariGoster();
        System.out.println("---");

        ArrayList<SavasAraclari> secilenKartlar = new ArrayList<>();

        // Secim Kontrolu
        if (oyuncu.KartKontrol(secimler)) {
            secilenKartlar = oyuncu.KartSec(secimler);
            System.out.println("Oyuncu Kartlarini Secti!");
            return secilenKartlar;
        } else {
            System.out.println("Tekrar Secim Yapin!");
            return secilenKartlar;
        }
    }

    public ArrayList<SavasAraclari> Baslat(int baslangicSeviyePuani) {
        // Kartlari Dagit
        KartlariDagit(baslangicSeviyePuani);
        System.out.println("Kartlar Dagitildi!");

        return oyuncu.getKartListesi();
    }

    public String OyunuSonlandir(int kazanan) {
        System.out.println("--------------Oyun Sona Erdi--------------");
        switch (kazanan) {
            case 1 -> {
                System.out.println("Tum Kartlar Yok Edildi! Kazanan: " + oyuncu.getOyuncuAdi());
                return "Tum Kartlar Yok Edildi! Kazanan: " + oyuncu.getOyuncuAdi();
            }
            case 2 -> {
                System.out.println("Tum Kartlar Yok Edildi! Kazanan: " + bilgisayar.getOyuncuAdi());
                return "Tum Kartlar Yok Edildi! Kazanan: " + bilgisayar.getOyuncuAdi();
            }
            default -> {
                if (oyuncu.getSkor() > bilgisayar.getSkor()) {
                    System.out.println("Kazanan: " + oyuncu.getOyuncuAdi());
                    return "Maksimum Hamle Yapildi!\nSkor Farkindan Kazanan: " + oyuncu.getOyuncuAdi();
                } else if (bilgisayar.getSkor() > oyuncu.getSkor()) {
                    System.out.println("Kazanan: " + bilgisayar.getOyuncuAdi());
                    return "Maksimum Hamle Yapildi!\nSkor Farkindan Kazanan: " + bilgisayar.getOyuncuAdi();
                } else {
                    String result = "Maksimum Hamle Yapildi!\nSkorlar esit! Dayanikliliklar kontrol ediliyor...\n";
                    System.out.println("Skorlar esit! Dayanikliliklar kontrol ediliyor...");
                    int oyuncuDayaniklilik = 0;
                    int bilgisayarDayaniklilik = 0;
                    for (SavasAraclari kart : oyuncu.getKartListesi()) {
                        oyuncuDayaniklilik += kart.getDayaniklilik();
                    }
                    for (SavasAraclari kart : bilgisayar.getKartListesi()) {
                        bilgisayarDayaniklilik += kart.getDayaniklilik();
                    }
                    if (oyuncuDayaniklilik > bilgisayarDayaniklilik) {
                        oyuncu.SkorEkle(oyuncuDayaniklilik - bilgisayarDayaniklilik);
                        System.out.println("Kazanan: " + oyuncu.getOyuncuAdi());
                        return result + "Dayaniklilik Fazlaligindan Kazanan: " + oyuncu.getOyuncuAdi();
                    } else if (oyuncuDayaniklilik < bilgisayarDayaniklilik) {
                        bilgisayar.SkorEkle(bilgisayarDayaniklilik - oyuncuDayaniklilik);
                        System.out.println("Kazanan: " + bilgisayar.getOyuncuAdi());
                        return result + " Dayaniklilik Fazlaligindan Kazanan: " + bilgisayar.getOyuncuAdi();
                    } else {
                        System.out.println("Skorlar hala esit! Berabere...");
                        return result + "Dayanikliliklar esit ve skorlar hala esit! Berabere...";
                    }
                }
            }
        }
    }
}
