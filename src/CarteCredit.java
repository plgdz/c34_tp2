import java.util.Calendar;
import java.util.GregorianCalendar;

public class CarteCredit {
    private String numeroCarte;
    private int moisExp;
    private int anneeExp;
    private double solde;
    private static final String regNum = "\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}";

    public CarteCredit(String numeroCarte, int moisExp, int anneeExp){
        this.numeroCarte = numeroCarte;
        this.moisExp = moisExp;
        this.anneeExp = anneeExp;
        this.solde = 50;
    }

    public double getSolde(){
        return this.solde;
    }

    public void setSolde(double montant){
        this.solde -= montant;
    }

    public boolean verifCarteCredit(){
        GregorianCalendar dateActuelle = new GregorianCalendar();
        int mois = 1 + dateActuelle.get(Calendar.MONTH);
        int annee = dateActuelle.get(Calendar.YEAR);
        annee = Integer.parseInt(String.valueOf(annee).substring(2));
        boolean valid = false;

        if (this.numeroCarte.matches(regNum)){
            if (this.anneeExp < annee){
                valid = true;
            }
            else if (this.anneeExp == annee){
                if (this.moisExp <= mois){
                    valid = true;
                }
            }
        }
        return valid;
    }

    public static void main(String[] args) {
        CarteCredit test = new CarteCredit("1111 1111 1111 1111", 10, 22);
        boolean t = test.verifCarteCredit();
        System.out.println(t);
    }
}



