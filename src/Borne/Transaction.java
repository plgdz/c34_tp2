package Borne;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Transaction {
    private static final GregorianCalendar dateActuelle = new GregorianCalendar();
    private static final int mois = 1 + dateActuelle.get(Calendar.MONTH);
    private static final int annee = dateActuelle.get(Calendar.YEAR);
    private static final int jour = dateActuelle.get(Calendar.DAY_OF_MONTH);
    private final static int heure = dateActuelle.get(Calendar.HOUR_OF_DAY);
    private final static int minutes = dateActuelle.get(Calendar.MINUTE);

    private String numTransaction;
    private int dureeStationnement;
    private double montant;
    private String typePaiement;
    private CarteCredit carte;

    public Transaction(String code, int duree, double prix, String modePaiement){
        this.numTransaction = code;
        this.dureeStationnement = duree;
        this.montant = prix;
        this.typePaiement = modePaiement;
    }

    public String printTransaction(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd ' - ' hh:mm a");
        format.setCalendar(dateActuelle);
        String dateFormateDebut = format.format(dateActuelle.getTime());
        dateActuelle.add((GregorianCalendar.MINUTE), this.dureeStationnement);
        String dateFormatFin = format.format(dateActuelle.getTime());
        String recu = "RECU DE STATIONNEMENT\n\n" +
                "Debut de la peridode de stationnement : " + dateFormateDebut + "\n" +
                "Fin de la priode de stationement : " + dateFormatFin;
        return recu;
    }
}
