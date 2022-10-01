package Borne;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Borne {
    private static final String regIdStationnement = "[A-D]{1}\\d{3}";
    private static final double tempsMax = 2;
    private static final double montantMAx = 6;

    private static final GregorianCalendar dateActuelle = new GregorianCalendar();
    private static final int mois = 1 + dateActuelle.get(Calendar.MONTH);
    private static final int annee = dateActuelle.get(Calendar.YEAR);
    private static final int jour = dateActuelle.get(Calendar.DAY_OF_WEEK);
    private final static int heure = dateActuelle.get(Calendar.HOUR_OF_DAY);
    private final static int minutes = dateActuelle.get(Calendar.MINUTE);

    private boolean active;
    private boolean idValid;
    private boolean paiementCash;
    private boolean paiementCredit;
    private boolean carteValide;
    private String idStationnement;
    private String modePaiement;
    private int dureeSationnement;
    private double montantTransaction;
    private double sommeTotale;
    private Transaction transaction;
    private Piece piece = new Piece();

    public Borne(){
        this.paiementCredit = this.paiementCash = true;
        this.idValid = false;
        this.montantTransaction = 0;
        this.dureeSationnement = 0;
        this.modePaiement = "";
    }
    public boolean getIdValid(){
        return this.idValid;
    }
    public boolean getPaiementCash(){
        return this.paiementCash;
    }
    public boolean getPaiementCredit(){
        return this.paiementCredit;
    }
    public String borneActive(){
        String message;
        boolean active = false;
        //Lundi a vendredi entre 9 et 21h : borne active
        if (this.jour >= 2 && this.jour <= 6 && this.heure >= 9 && this.heure < 21){
            active = true;
            this.active = true;
        }
        //Lundi a vendredi en dehors des heures payantes :
        else if (this.jour >= 2 && this.jour <= 6 && this.heure < 9 && this.heure >= 21){
            active = false;
            this.active = false;
        }
        //Samedi dans les heures payantes
        else if (this.jour == 7  && this.heure >= 9 && this.heure < 18){
            active = true;
            this.active = true;
        }
        // Samedi dans les heurs gratuites
        else if (this.jour == 7 && this.heure < 9 && this.heure >=18){
            active = false;
            this.active = false;
        }
        //Dimanche dans les heures payantes
        else if (this.jour == 1 && this.heure >= 13 && this.heure < 18){
            active = true;
            this.active = true;
        }
        //dimanche dans les heures gratuite
        else {
            active = false;
            this.active = false;
        }

        //choix du message pour utilisateur
        if (active == true){
            message = "Veuillez saisir votre identifiant de stationnement";
        } else {
            message = "Stationnement gratuit";
        }
        return message;
    }
    public void setSommeTotale(double somme) {
        this.sommeTotale += somme;
    }
    public boolean verifIdStationement(String idStationnement){
        boolean valid = false;
        if (idStationnement.matches(regIdStationnement)){
            this.idStationnement = idStationnement;
            this.idValid = valid = true;
            this.transaction = new Transaction(this.idStationnement);
        }
        return valid;
    }
    public boolean paiementCredit(String numeroCarte, String dateExp) {
        boolean valid = false;

        int mois = Integer.parseInt(String.valueOf(dateExp).substring(0,2));
        int annee = Integer.parseInt(String.valueOf(dateExp).substring(3,5));
        CarteCredit credit = new CarteCredit(numeroCarte, mois, annee);

        if (credit.verifCarteCredit()){
            this.paiementCredit = true;
            this.paiementCash = false;
            this.carteValide = valid = true;
        }
        return valid;
    }
    public void add25(){
        this.montantTransaction += piece.getQuart();
        this.dureeSationnement += 5;
    }
    public void add100(){
        this.montantTransaction += piece.getUn();
        this.dureeSationnement +=20;
        this.paiementCash = true;
        this.paiementCredit = false;

    }
    public void add200(){
        this.montantTransaction += piece.getDeux();
        this.dureeSationnement += 40;
    }
    public void add25Credit(){
        this.montantTransaction += 0.25;
        this.dureeSationnement += 5;
    }
    public void addMax(){
        this.montantTransaction = 6;
        this.dureeSationnement = 120;
    }
    public void actionBouton(int bouton){
        if (bouton >= 1 && bouton <= 3){
            if (bouton == 1){
                this.add25();
            } else if (bouton == 2){
                this.add100();
            } else if (bouton == 3) {
                this.add200();
            }
            this.paiementCash = true;
            this.paiementCredit = false;
            this.modePaiement = "Cash";
        } else {
            if (bouton == 4) {
                this.add25Credit();
            } else if (bouton == 5) {
                this.addMax();
            }
            this.paiementCash = false;
            this.paiementCredit = true;
            this.modePaiement = "Credit";
        }
    }
    public String printTempMontant(){
        return "Duree : " + this.dureeSationnement + " minutes        Prix : " + this.montantTransaction + "$";
    }
    public String setTransaction(){
        transaction.setDureeStationnement(this.dureeSationnement);
        transaction.setMontant(this.montantTransaction);
        transaction.setTypePaiement(this.modePaiement);
        this.sommeTotale += this.montantTransaction;
        return transaction.printTransaction();
    }
    public void resetBorne(){
        this.paiementCredit = this.paiementCash = true;
        this.idValid = false;
        this.montantTransaction = 0;
        this.dureeSationnement = 0;
        this.modePaiement = "";
    }
    public String printRapport(){
        return "Montant total de la borne : " + this.sommeTotale + "$";
    }
}
