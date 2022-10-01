package Borne;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;


public class GUITP2 {
    private JPanel panel1;
    private JLabel labelLogo;
    private JPanel panelNumeros;
    private JPanel panelDroite;
    private JPanel panelComptant;
    private JLabel champMessage;
    private JButton bouton25;
    private JButton bouton100;
    private JButton bouton200;
    private JPanel panelCredit;

    private JButton bouton25Credit;
    private JButton boutonMaxCredit;
    private JButton boutonOk;
    private JTextArea zoneRecu;
    private JButton boutonRapport;
    private JFormattedTextField champNumeroCarte;
    private JFormattedTextField champDateExp;
    private JButton boutonValiderDateExp;


    private EcouteurNumero ecouteurNumero;
    private EcouteurCarteCredit ecouteurCarteCredit;
    private EcouteurMonnaie ecouteurMonnaie;
    private EcouteurControles ecouteurControles;
    private EcouteurEntree ecouteurEntree;

    // variables utiles pour vous

    String place =""; //place de stationnement choisie
    Borne borne; // borne à créer dans le constructeur



   
    

    public GUITP2() throws ParseException {


        labelLogo.setIcon(new ImageIcon("Borne/logo.png"));
        boutonValiderDateExp.setMargin(new Insets(10,0, 10, 0));
        //Création des écouteurs
        ecouteurNumero = new EcouteurNumero();
        ecouteurCarteCredit = new EcouteurCarteCredit();
        ecouteurMonnaie = new EcouteurMonnaie();
        ecouteurControles = new EcouteurControles();
        ecouteurEntree = new EcouteurEntree();


        // panelNumeros avec la grille
        GridBagLayout gl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();


        panelNumeros.setLayout(gl);
        c.fill = GridBagConstraints.BOTH;
        c.weightx =1;
        c.weighty=1;
        for ( int i = 0; i <15 ; i++)
        {
            JButton temp = new JButton();
            temp.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
            temp.setForeground(new Color(0,174,239));
            temp.addActionListener(ecouteurNumero);
            if  ( i ==0 )
                temp.setText("A");

            else if ( i ==1 )
                temp.setText("B");
            else if ( i==2 )
               temp.setText("C");

            else if ( i == 3 ) {
                c.gridwidth = GridBagConstraints.REMAINDER; //end row
                temp.setText("D");
            }

            else if ( i <=6)
            {
                c.weightx=1;
                c.gridwidth = 1;
                temp.setText(String.valueOf(i-4));
            }
            else if ( i==7)
            {
                c.gridwidth = GridBagConstraints.REMAINDER; //end row
                temp.setText(String.valueOf(i-4));
            }
            else if ( i <=10)
            {
                c.weightx=1;
                c.gridwidth = 1;
                temp.setText(String.valueOf(i-4));
            }
            else if ( i==11)
            {
                c.gridwidth = GridBagConstraints.REMAINDER; //end row
                temp.setText(String.valueOf(i-4));
            }
            else if ( i <=13)
            {
                c.weightx=1;
                c.gridwidth = 1;
                temp.setText(String.valueOf(i-4));
            }
            else if ( i==14)
            {
                c.gridwidth = GridBagConstraints.REMAINDER; //end row
                temp.setText("entrée");
                temp.removeActionListener(ecouteurNumero);
                temp.addActionListener(ecouteurEntree);
            }
            gl.setConstraints(temp, c );
            panelNumeros.add( temp);









        }
        // inscrire les sources à l'écouteur
        bouton25.addActionListener(ecouteurMonnaie);
        bouton100.addActionListener(ecouteurMonnaie);
        bouton200.addActionListener(ecouteurMonnaie);

        bouton25Credit.addActionListener(ecouteurCarteCredit);
        boutonMaxCredit.addActionListener(ecouteurCarteCredit);
        boutonValiderDateExp.addActionListener(ecouteurCarteCredit);
        boutonOk.addActionListener(ecouteurControles);
        boutonRapport.addActionListener(ecouteurControles);

        //créer objet Borne.Borne
        borne = new Borne();
        

        // vérifier si la Borne.Borne est en fonction selon les heures
        champMessage.setText(borne.borneActive());
        


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            champNumeroCarte = new JFormattedTextField(new MaskFormatter("#### #### #### ####"));
            champDateExp = new JFormattedTextField(new MaskFormatter("##/##"));
        }
        catch ( ParseException pe)
        {
            pe.printStackTrace();
        }
    }
    private void $$$setupUI$$$() {
        createUIComponents();
    }
    private class EcouteurNumero implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            //lettre ou chiffre du bouton qu'on a cliqué dessus
           String lettreChiffre = ((JButton)e.getSource()).getText();
           boutonNumeroLettre_actionPerformed( lettreChiffre);
        



        }
    }

    private class EcouteurEntree implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boutonEntree_actionPerformed();
        }
    }

    private class EcouteurMonnaie implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ( e.getSource() == bouton25)
                bouton25_actionPerformed();
            else if ( e.getSource()==bouton100)
                bouton100_actionPerformed();
            else if ( e.getSource() == bouton200)
                bouton200_actionPerformed();
        }
    }

    private class EcouteurCarteCredit implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

             if ( e.getSource() == bouton25Credit)
                bouton25Credit_actionPerformed();
            else if (e.getSource() == boutonMaxCredit)
                boutonMaxCredit_actionPerformed();
            else if ( e.getSource() == boutonValiderDateExp)
                boutonValiderDateExp_actionPerformed();
        }
    }

    private class EcouteurControles implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if ( e.getSource() == boutonOk)
                boutonOK_actionPerformed();
            else if ( e.getSource() == boutonRapport)
                boutonRapport_actionPerformed();
        }
    }





    private void bouton25_actionPerformed() {
        //à coder
        zoneRecu.setText("");
        if (borne.getIdValid() && borne.getPaiementCash()) {
            borne.actionBouton(1);
            champMessage.setText(borne.printTempMontant());
        } else if (!borne.getIdValid()){
            champMessage.setText("Vous devez saisir un code de stationnement valide.");

        } else {
            champMessage.setText("Ce mode de paiement n'est plus disponnible pour cette transaction.");
        }
    }

    private void bouton100_actionPerformed() {
        //à coder
        zoneRecu.setText("");
        if (borne.getIdValid() && borne.getPaiementCash()) {
            borne.actionBouton(2);
            champMessage.setText(borne.printTempMontant());
        } else if (!borne.getIdValid()){
            champMessage.setText("Vous devez saisir un code de stationnement valide.");

        } else {
            champMessage.setText("Ce mode de paiement n'est plus disponnible pour cette transaction.");
        }
    }

    private void bouton200_actionPerformed() {
        //à coder
        zoneRecu.setText("");
        if (borne.getIdValid() && borne.getPaiementCash()){
            borne.actionBouton(3);
            champMessage.setText(borne.printTempMontant());
        } else if (!borne.getIdValid()){
            champMessage.setText("Vous devez saisir un code de stationnement valide.");

        } else {
            champMessage.setText("Ce mode de paiement n'est plus disponnible pour cette transaction.");
        }
    }

    private void boutonValiderDateExp_actionPerformed(){
            // à coder
        zoneRecu.setText("");
        if (borne.getIdValid() && borne.getPaiementCredit()) {
            boolean carteValide = borne.paiementCredit(champNumeroCarte.getText(), champDateExp.getText());
            if (carteValide == true) {
                champMessage.setText("Carte valide : veuillez choisir votre durée de stationnement");
            } else {
                champMessage.setText("Carte invalide");
            }
        } else if (!borne.getIdValid()){
            champMessage.setText("Vous devez saisir un code de stationnement valide.");

        } else {
            champMessage.setText("Ce mode de paiement n'est plus disponnible pour cette transaction.");
        }
    }

    private void bouton25Credit_actionPerformed() {
        //à coder
        zoneRecu.setText("");
        if (borne.getIdValid() && borne.getPaiementCredit()) {
            borne.actionBouton(4);
            champMessage.setText(borne.printTempMontant());
        } else if (!borne.getPaiementCredit()){
            champMessage.setText("Vous devez saisir une carte de credit valide.");
        } else {
            champMessage.setText("Ce mode de paiement n'est pas disponnible pour cette transaction.");
        }
    }

    private void boutonMaxCredit_actionPerformed() {
        //à coder
        zoneRecu.setText("");
        if (borne.getIdValid() && borne.getPaiementCredit()) {
            borne.actionBouton(5);
            champMessage.setText(borne.printTempMontant());
        } else if (!borne.getIdValid()){
            champMessage.setText("Vous devez saisir un code de stationnement valide.");

        } else {
            champMessage.setText("Ce mode de paiement n'est plus disponnible pour cette transaction.");
        }
    }

    public void boutonNumeroLettre_actionPerformed(String lettreChiffre) {
        // à compléter, afficher la place choisie dans le champMessage à partir de la lettre ou du chiffre cliqué en paramètre
       place += lettreChiffre;
       zoneRecu.setText("");
       champMessage.setText("Code de stationnement : " + place);
    }

    private void boutonEntree_actionPerformed() {
        //à coder
        boolean valid = borne.verifIdStationement(place);
        if (valid == true){
            champMessage.setText("Code VALIDE, choisissez un mode de paiement...");
            place = "";
        } else {
            place = "";
            champMessage.setText("Code NON VALIDE, veuillez saisir a nouveau votre code.");
        }
    }

    private void boutonOK_actionPerformed() {
        
        // à coder
        champMessage.setText("Transaction reussie.");
        zoneRecu.setText(borne.setTransaction());
        champNumeroCarte.setText("");
        champDateExp.setText("");
        borne.resetBorne();

    }

    private void boutonRapport_actionPerformed()
    {
        champMessage.setText("");
        zoneRecu.setText(borne.printRapport());
    }


    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("GUITP2");
            frame.setContentPane(new GUITP2().panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 875);
            //frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        catch ( Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
