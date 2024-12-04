package javaapplication1;

public abstract class SavasAraclari {

    protected int seviyePuani;
    protected int dayaniklilik;
    protected int vurus;
    protected String sinif;

    // Aynı Kartların Seçilememesi İçin
    protected boolean secildiMi = false;

    public SavasAraclari(int seviyePuani, int dayaniklilik, int vurus, String sinif) {
        this.seviyePuani = seviyePuani;
        this.dayaniklilik = dayaniklilik;
        this.vurus = vurus;
        this.sinif = sinif;
    }

    public String KartPuaniGoster() {
        System.out.println("Sinif: " + sinif);
        System.out.println("Seviye Puani: " + seviyePuani + ", Dayaniklilik: " + dayaniklilik + ", Vurus Gucu: " + vurus);
        return "Sinif: " + sinif + ", Seviye Puani: " + seviyePuani + ", Dayaniklilik: " + dayaniklilik + ", Vurus Gucu: " + vurus + ", Secilebilir: " + !secildiMi;
    }

    public void SeviyePuaniEkle(int puan) {
        this.seviyePuani += puan;
    }

    public boolean isSecildiMi() {
        return secildiMi;
    }

    public void setSecildiMi(boolean secildiMi) {
        this.secildiMi = secildiMi;
    }

    public abstract boolean DurumGuncelle(int hasar);

    public abstract int getSeviyePuani();

    public abstract int getDayaniklilik();

}
