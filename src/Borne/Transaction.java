package Borne;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Transaction {
    private GregorianCalendar dateActuelle;
    private int mois;
    private int annee;
    private int jour;
    private int heure;
    private int minutes;

    private String numTransaction;
    private int dureeStationnement;
    private double montant;
    private String typePaiement;
    private CarteCredit carte;

    public Transaction(String code){
        this.numTransaction = code;
        dateActuelle = new GregorianCalendar();
        mois = 1 + dateActuelle.get(Calendar.MONTH);
        annee = dateActuelle.get(Calendar.YEAR);
        jour = dateActuelle.get(Calendar.DAY_OF_MONTH);
        heure = dateActuelle.get(Calendar.HOUR_OF_DAY);
        minutes = dateActuelle.get(Calendar.MINUTE);
    }

    public void setDureeStationnement(int dureeStationnement) {
        this.dureeStationnement = dureeStationnement;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public String printTransaction(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd ' - ' hh:mm a");
        format.setCalendar(dateActuelle);
        String dateFormateDebut = format.format(dateActuelle.getTime());
        dateActuelle.add((GregorianCalendar.MINUTE), this.dureeStationnement);
        String dateFormatFin = format.format(dateActuelle.getTime());
        String recu = "RECU DE STATIONNEMENT\n\n" +
                "Espace de stationnement : " + this.numTransaction +"\n" +
                "Debut stationnement : " + dateFormateDebut + "\n" +
                "Fin stationement : " + dateFormatFin + "\n\n" +
                "Mode de paiement : " + this.typePaiement + "\n" +
                "Prix : " + this.montant + "$";
        return recu;
    }
}
