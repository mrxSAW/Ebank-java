import java.util.*;

public class Client extends Person {

    Scanner scanner = new Scanner(System.in);
    private static ArrayList<Client> listclients= new ArrayList<>();
    private  Cempt cempt;
    SavingsAccount savingsAccount;


    public Client(String nom,int age,String CIN) {
        super(nom,age,CIN);
        listclients.add(this);

    }

    public Client(String nom,int age,String CIN,Cempt cempt) {
        super(nom,age,CIN);
        listclients.add(this);
        this.cempt = cempt;

    }

    public Client(String nom,int age,String CIN,SavingsAccount savingsAccount) {
        super(nom,age,CIN);
        listclients.add(this);
        this.savingsAccount=savingsAccount;

    }

    public static ArrayList<Client> getListclients() {
        return listclients;
    }

    public Cempt getCempt() {
        return cempt;
    }

    public void setCempt(Cempt cempt) {
        this.cempt = cempt;
    }
    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }
    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }




    public static Client ajouterclient(Scanner scanner) {
        try {
            System.out.println("entrer le nom du client: ");
            String nom = scanner.nextLine();

            System.out.println("entrer l'age du client : ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.println("entrer le CIN du client: ");
            String CIN = scanner.nextLine();

            Client client = new Client(nom, age, CIN);
            Bank.getClientsBank().add(client);
            System.out.println("client ajouter avec secces " + client.getNom());
            return client;

        } catch (InputMismatchException e) {
            System.err.println("Erreur: L'âge doit être un nombre entier !");
            scanner.nextLine();
            return null;
        } catch (Exception e) {
            System.err.println("Erreur inattendue dans ajouter client: " + e.getMessage());
            return null;
        }
    }


    public static void afficherclient(){
        if(listclients.isEmpty()){
            System.out.println("Liste de clients est vide");
            return;
        }

        for(int i=0 ; i<listclients.size();i++){
            Client client=listclients.get(i);
            System.out.println("==============la liste des client ==============");
            System.out.println("client : "+ (i+1));
            System.out.println("nom : "+client.getNom());
            System.out.println("age : "+client.getAge());
            System.out.println("CIN : "+client.getCIN());
            if(client.getCempt()!=null){
                System.out.println("============cempt============");
                System.out.println("nemero du cempt : "+ client.getCempt().getCemptN());
                System.out.println("solde : "+ client.getCempt().getSolde());

            }else {
                System.out.println("cempt=============null==========");
            }
            System.out.println("________________________________________________");
            if(client.getSavingsAccount()!=null){
                System.out.println("============ Saving cempt============");
                System.out.println("nemero du cempt : "+ client.getSavingsAccount().getCemptN());
                System.out.println("solde : "+ client.getSavingsAccount().getSolde());

            }else {
                System.out.println("Saving account =============null==========");
            }
            System.out.println("________________________________________________");

        }
    }


    public static Cempt ajoutercempt(Scanner scanner,Client client) {
        System.out.println("entrer le numero de cempt");
        String  cemptN = scanner.nextLine();
        double   solde =0;
        Cempt ncempt=new Cempt(cemptN,solde,client.getCIN());

        Bank.getCemptsBank().add(ncempt);

        client.setCempt(ncempt);
        System.out.println("le  cempt  a ete cree avec succes ");
        System.out.println(client.cempt.getCemptN());
        return ncempt;
    }

    public static Cempt ajouteSavingAccount(Scanner scanner,Client client) {
        try {
            System.out.println("entrer le numero de cempt");
            String cemptN = scanner.nextLine();

            System.out.println("entrer le menton a saver ");
            double solde = scanner.nextDouble();

            System.out.println("entrer le taux d'interer ");
            int Taux = scanner.nextInt();
            scanner.nextLine();

            SavingsAccount ncempt = new SavingsAccount(cemptN, solde, client.getCIN(), Taux);

            Bank.getSavingsAccountBank().add(ncempt);

            client.setSavingsAccount(ncempt);
            System.out.println("le savingsAccount a ete cree avec succes ");
            System.out.println(client.savingsAccount.getCemptN());
            return ncempt;

        } catch (InputMismatchException e) {
            System.err.println("Erreur: Veuillez entrer des nombres valides !");
            scanner.nextLine();
            return null;
        } catch (Exception e) {
            System.err.println("Erreur inattendue: " + e.getMessage());
            return null;
        }
    }




    public static void versser (Scanner scanner, Client client) {
        try {
            if (client.getCempt() == null) {
                System.out.println(" le client n'a pas de cempt ");
                return;
            }

            System.out.println("le numero de votre cempt est : " + client.getCempt().getCemptN());
            System.out.println("entrer le monton a versser : ");
            double monton = scanner.nextDouble();
            scanner.nextLine();

            if (monton > 0) {
                Double soldeActuel = client.getCempt().getSolde();
                Double soldeN = soldeActuel + monton;
                client.getCempt().setSolde(soldeN);

                System.out.println("===========operation reussite===========");
                System.out.println("votre encien solde est : " + soldeActuel);
                System.out.println(" monton versé : " + monton);
                System.out.println("nouveau solde est : " + soldeN);
            } else {
                System.out.println("entrer un monton superieur de 0");
            }

        } catch (InputMismatchException e) {
            System.err.println("Erreur: Le montant doit être un nombre !");
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println("Erreur inattendue fonction versser: " + e.getMessage());
        }
    }



    public static void retrait (Scanner scanner, Client client) {
        try {
            if (client.getCempt() == null) {
                System.out.println(" le client n'a pas de ceempt ");
                return;
            }

            System.out.println("le numero de votre cempt est : " + client.getCempt().getCemptN());
            System.out.println("entrer le monton a retiter : ");
            double monton = scanner.nextDouble();
            scanner.nextLine();

            Double soldeActuel = client.getCempt().getSolde();

            if (soldeActuel > monton) {
                Double soldeN = soldeActuel - monton;
                client.getCempt().setSolde(soldeN);

                System.out.println("===========operation reussite===========");
                System.out.println("votre encien solde est : " + soldeActuel);
                System.out.println(" monton versé : " + monton);
                System.out.println("nouveau solde est : " + soldeN);
            } else {
                System.out.println(" votre solde est insufusant");
            }

        } catch (InputMismatchException e) {
            System.err.println("Erreur: Le montant doit être un nombre !");
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }



    public static  void suprimer(Scanner scanner,Client client) {
        if(client.getCempt()==null){
            System.out.println(" le client n'a pas de cempt ");
        }
        else {

            System.out.println("voulez vous vraiment suprimer le cempt  : "  + client.getCempt().getCemptN());
            System.out.println("oui entrer O   NON entrer N ");
            String confirmation = scanner.nextLine();
            if(confirmation.equals("O")){
                String cemptNsuprimer=client.getCempt().getCemptN();
                client.setCempt(null);
                System.out.println("le cempt a ete suprimer avec succes ");
            }
            else {
                System.out.println("la supprission du cempt" + client.getCempt().getCemptN()+ "a ete annuler ");
            }

        }
    }

}