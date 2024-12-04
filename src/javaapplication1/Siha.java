package javaapplication1;

public class Siha extends Hava {

    protected int denizVurusAvantaji;

    public Siha(int seviyePuani) {
        super(seviyePuani, 15, 10, "Hava", "Siha", 10); // Seviye Puanı, Dayanıklılık, Vuruş, Sınıf, Alt Sınıf, Kara Vuruş Avantajı
        this.denizVurusAvantaji = 10;
    }

    @Override
    public boolean DurumGuncelle(int hasar) {
        dayaniklilik -= hasar;
        if (dayaniklilik <= 0) {
            System.out.println("Siha elendi!");
            return true;
        }
        return false;
    }

    //Getter ve Setter Metodları
    public int getDenizVurusAvantaji() {
        return denizVurusAvantaji;
    }

    public void setDenizVurusAvantaji(int denizVurusAvantaji) {
        this.denizVurusAvantaji = denizVurusAvantaji;
    }

    public String getAltSinif() {
        return altSinif;
    }

    public void setAltSinif(String altSinif) {
        this.altSinif = altSinif;
    }

    public int getKaraVurusAvantaji() {
        return karaVurusAvantaji;
    }

    public void setKaraVurusAvantaji(int karaVurusAvantaji) {
        this.karaVurusAvantaji = karaVurusAvantaji;
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
