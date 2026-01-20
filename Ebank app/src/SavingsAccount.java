import java.util.Scanner;

public class SavingsAccount  extends Cempt {
    private int TauxInterer;
    Scanner scanner = new Scanner(System.in);

    public SavingsAccount(String cemptN, double solde, String CIN) {
        super(cemptN,solde,CIN);

    }

    public SavingsAccount(String cemptN, double solde, String CIN, int TauxInterer) {
        super(cemptN,solde,CIN);
        this.TauxInterer = TauxInterer;
    }


    public int getTauxInterer() {
        return TauxInterer;
    }

    public void setTauxInterer(int TauxInterer) {
        this.TauxInterer =TauxInterer;
    }



    public  void Addinterer(){
        try {
            if (getSolde() > 0) {
                double soldeActuel = getSolde();
                double binifite = soldeActuel * (TauxInterer / 100.0);
                double Nsolde = soldeActuel + binifite;

                setSolde(Nsolde);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du calcul des intérêts: " + e.getMessage());
        }

    }









}