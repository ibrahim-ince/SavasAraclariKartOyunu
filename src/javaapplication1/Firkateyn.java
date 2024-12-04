package javaapplication1;

public class Firkateyn extends Deniz {

    public Firkateyn(int seviyePuani) {
        super(seviyePuani, 25, 10, "Deniz", "Firkateyn", 5); // Seviye Puanı, Dayanıklılık, Vuruş, Sınıf, Alt Sınıf, Hava Vuruş Avantajı
    }

    @Override
    public boolean DurumGuncelle(int hasar) {
        dayaniklilik -= hasar;
        if (dayaniklilik <= 0) {
            System.out.println("Firkateyn elendi!");
            return true;
        }
        return false;
    }

    //Getter ve Setter Metodları
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
