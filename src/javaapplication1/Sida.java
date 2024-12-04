package javaapplication1;

public class Sida extends Deniz {

    protected int karaVurusAvantaji;

    public Sida(int seviyePuani) {
        super(seviyePuani, 15, 10, "Deniz", "Sida", 10); // Seviye Puanı, Dayanıklılık, Vuruş, Sınıf, Alt Sınıf, Hava Vuruş Avantajı
        this.karaVurusAvantaji = 10;
    }

    @Override
    public boolean DurumGuncelle(int hasar) {
        dayaniklilik -= hasar;
        if (dayaniklilik <= 0) {
            System.out.println("Sida elendi!");
            return true;
        }
        return false;
    }

    //Getter ve Setter Metodları
    public int getKaraVurusAvantaji() {
        return karaVurusAvantaji;
    }

    public void setKaraVurusAvantaji(int karaVurusAvantaji) {
        this.karaVurusAvantaji = karaVurusAvantaji;
    }

    public String getAltSinif() {
        return altSinif;
    }

    public void setAltSinif(String altSinif) {
        this.altSinif = altSinif;
    }

    public int getHavaVurusAvantaji() {
        return havaVurusAvantaji;
    }

    public void setHavaVurusAvantaji(int havaVurusAvantaji) {
        this.havaVurusAvantaji = havaVurusAvantaji;
    }

    @Override
    public int getSeviyePuani() {
        return seviyePuani;
    }

    public void setSeviyePuani(int seviyePuani) {
        this.seviyePuani = seviyePuani;
    }

    @Override
    public int getDayaniklilik() {
        return dayaniklilik;
    }

    public void setDayaniklilik(int dayaniklilik) {
        this.dayaniklilik = dayaniklilik;
    }

    public int getVurus() {
        return vurus;
    }

    public void setVurus(int vurus) {
        this.vurus = vurus;
    }

    public String getSinif() {
        return sinif;
    }

    public void setSinif(String sinif) {
        this.sinif = sinif;
    }
}
