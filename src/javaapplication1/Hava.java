package javaapplication1;

public abstract class Hava extends SavasAraclari {

    protected String altSinif;
    protected int karaVurusAvantaji;

    public Hava(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int karaVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif);
        this.altSinif = altSinif;
        this.karaVurusAvantaji = karaVurusAvantaji;
    }

    @Override
    public String KartPuaniGoster() {
        System.out.println("Sinif: " + sinif + ", Alt Sinif: " + altSinif);
        System.out.println("Seviye Puani: " + seviyePuani + ", Dayaniklilik: " + dayaniklilik + ", Vurus Gucu: " + vurus);
        return "Sinif: " + sinif + ", Alt Sinif: " + altSinif + ", Seviye Puani: " + seviyePuani + ", Dayaniklilik: " + dayaniklilik + ", Vurus Gucu: " + vurus + ", Secilebilir: " + !secildiMi;
    }
}
