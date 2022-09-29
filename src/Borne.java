import java.util.Calendar;
import java.util.GregorianCalendar;

public class Borne {
    public static final int prixHeure = 3;
    private static final String regIdStationnement = "[A-D]{1}\\d{3}";
    private static final double tempsMax = 2;
    private static final double montantMAx = 6;

    private static final GregorianCalendar dateActuelle = new GregorianCalendar();
    private static final int mois = 1 + dateActuelle.get(Calendar.MONTH);
    private static final int annee = dateActuelle.get(Calendar.YEAR);
    private static final int jour = dateActuelle.get(Calendar.DAY_OF_WEEK);

    private String idStationnement;
    private String modePaiement;
    private double montantTransaction;
    private double sommeTotale;
    private Transaction transaction;


    public void setIdStationnement(String idStationnement) {
        this.idStationnement = idStationnement;
    }

    public void setSommeTotale(double sommeTotale) {
        this.sommeTotale = sommeTotale;
    }

    public boolean verifIdStationement(String idStationnement){
        boolean valid = false;
        if (idStationnement.matches(regIdStationnement)){
            this.idStationnement = idStationnement;
            valid = true;
        }
        return valid;
    }

    public boolean paiementCredit(String numeroCarte, String dateExp) {
        boolean valid = false;

        int mois = Integer.parseInt(String.valueOf(dateExp).substring(0,1));
        int annee = Integer.parseInt(String.valueOf(dateExp).substring(3,4));
        CarteCredit credit = new CarteCredit(numeroCarte, mois, annee);

        if (credit.verifCarteCredit()){
            if (credit.getSolde() >= 6){
                this.modePaiement = "Credit";
            }
        }
        return valid;
    }
}
