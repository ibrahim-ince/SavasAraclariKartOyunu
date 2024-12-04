package javaapplication1;

public class Kfs extends Kara {

    protected int havaVurusAvantaji;

    public Kfs(int seviyePuani) {
        super(seviyePuani, 10, 10, "Kara", "KFS", 10); // Seviye Puanı, Dayanıklılık, Vuruş, Sınıf, Alt Sınıf, Deniz Vuruş Avantajı
        this.havaVurusAvantaji = 20;
    }

    @Override
    public boolean DurumGuncelle(int hasar) {
        dayaniklilik -= hasar;
        if (dayaniklilik <= 0) {
            System.out.println("KFS elendi!");
            return true;
        }
        return false;
    }

    //Getter ve Setter Metodları
    public int getHavaVurusAvantaji() {
        return havaVurusAvantaji;
    }

    public void setHavaVurusAvantaji(int havaVurusAvantaji) {
        this.havaVurusAvantaji = havaVurusAvantaji;
    }

    public String getAltSinif() {
        return altSinif;
    }

    public void setAltSinif(String altSinif) {
        this.altSinif = altSinif;
    }

    public int getDenizVurusAvantaji() {
        return denizVurusAvantaji;
    }

    public void setDenizVurusAvantaji(int denizVurusAvantaji) {
        this.denizVurusAvantaji = denizVurusAvantaji;
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
