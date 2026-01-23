import java.sql.SQLOutput;
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

        System.out.println("==============la liste des client ==============");

        for(int i=0 ; i<listclients.size();i++){
            Client client=listclients.get(i);
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
        try{ String cemptN;
        do {
            System.out.println("entrer le numero de cempt");
            cemptN = scanner.nextLine().trim();
        }while (cemptN.isEmpty());

        int dejaexist=0;
        for(Client cl:Client.getListclients()){
            if(cl.getCempt()!=null && cl.getCempt().getCemptN().equalsIgnoreCase(cemptN)){
                dejaexist=1;
                break;
            }

            if(cl.getSavingsAccount()!=null && cl.getSavingsAccount().getCemptN().equalsIgnoreCase(cemptN)){
                dejaexist=1;
                break;
            }

        }

        if (dejaexist!=1){
        double   solde =0;
        Cempt ncempt=new Cempt(cemptN,solde,client.getCIN());
        Bank.getCemptsBank().add(ncempt);
        client.setCempt(ncempt);
        System.out.println("le  cempt  a ete cree avec succes ");
        System.out.println(client.cempt.getCemptN());
        return ncempt;
        }else {
            System.out.println("le num de cempt est deja existant ");
        }
        } catch (Exception e) {
            System.err.println("Erreur inattendue: " + e.getMessage());
            return null;
        }
       return null;
    }

    public static Cempt ajouteSavingAccount(Scanner scanner,Client client) {
        try {
            String cemptN;
            do {
                System.out.println("entrer le numero de cempt");
                 cemptN = scanner.nextLine().trim();
            }while (cemptN.isEmpty());

            int dejaexist=0;
            for(Client cl:Client.getListclients()){
                if(cl.getCempt()!=null && cl.getCempt().getCemptN().equalsIgnoreCase(cemptN)){
                     dejaexist=1;
                     break;
                }

                if(cl.getSavingsAccount()!=null && cl.getSavingsAccount().getCemptN().equalsIgnoreCase(cemptN)){
                    dejaexist=1;
                    break;
                }

            }
        if (dejaexist!=1){
            double solde;

            do {
                System.out.println("entrer le menton a saver  : ");
                solde = scanner.nextDouble();
            }while (solde==0 || solde<0);

            System.out.println("entrer le taux d'interer ");
            int Taux = scanner.nextInt();
            scanner.nextLine();
            SavingsAccount ncempt = new SavingsAccount(cemptN, solde, client.getCIN(), Taux);
            Bank.getSavingsAccountBank().add(ncempt);
            client.setSavingsAccount(ncempt);
            System.out.println("le savingsAccount a ete cree avec succes ");
            System.out.println(client.savingsAccount.getCemptN());
            return ncempt;
        }else {
            System.out.println("le num de cempt est deja existant ");
        }


        } catch (InputMismatchException e) {
            System.err.println("Erreur: Veuillez entrer des nombres valides !");
            scanner.nextLine();
            return null;
        } catch (Exception e) {
            System.err.println("Erreur inattendue: " + e.getMessage());
            return null;
        }
        return null;
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

            if ((soldeActuel > monton && monton>0 )|| soldeActuel==monton) {
                Double soldeN = soldeActuel - monton;
                client.getCempt().setSolde(soldeN);

                System.out.println("===========operation reussite===========");
                System.out.println("votre encien solde est : " + soldeActuel);
                System.out.println(" monton versé : " + monton);
                System.out.println("nouveau solde est : " + soldeN);
            } else {
                System.out.println(" votre solde est insufusant    ou monton infirieur ou egale a 0");
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
            if(confirmation.equalsIgnoreCase("O") && client.getCempt().getSolde()==0){

                client.setCempt(null);
                System.out.println("le cempt a ete suprimer avec succes ");
            }
            else {
                System.out.println("la supprission du cempt" + client.getCempt().getCemptN()+ "a ete annuler ou solde non nul");
            }

        }
    }




       //transfert d'argent entre 2 cempt

    public static void transfererArgent(Scanner scanner) {
        try {
            // Demander le compte source
            System.out.println("=== TRANSFERT D'ARGENT ===");

            System.out.println("Entrer le CIN du client source :");
            String cinSource = scanner.nextLine().trim();

            Client clientSource = null;
            for (Client client : Client.getListclients()) {
                if (client.getCIN().equalsIgnoreCase(cinSource)) {
                    clientSource = client;
                    break;
                }
            }

            if (clientSource == null) {
                System.out.println("❌ Client source non trouvé !");
                return;
            }

            if (clientSource.getCempt() == null) {
                System.out.println("❌ Le client source n'a pas de compte courant !");
                return;
            }

            System.out.println("Compte source trouvé :");
            System.out.println("- Client : " + clientSource.getNom());
            System.out.println("- N° Compte : " + clientSource.getCempt().getCemptN());
            System.out.println("- Solde : " + clientSource.getCempt().getSolde() + " DH");

            // cempte destination
            System.out.println("\nEntrer le numéro de compte destination :");
            String numCompteDest = scanner.nextLine().trim();

            Client clientDest = null;
            Cempt compteDest = null;

            // Rechercher  comptes courants
            for (Client client : Client.getListclients()) {
                if (client.getCempt() != null &&
                        client.getCempt().getCemptN().equalsIgnoreCase(numCompteDest)) {
                    clientDest = client;
                    compteDest = client.getCempt();
                    break;
                }

                // Rechercher comptes épargne
                if (client.getSavingsAccount() != null &&
                        client.getSavingsAccount().getCemptN().equalsIgnoreCase(numCompteDest)) {
                    clientDest = client;
                    compteDest = client.getSavingsAccount();
                    break;
                }
            }

            if (compteDest == null) {
                System.out.println("❌ Compte destination non trouvé !");
                return;
            }

            // pas transfert  vers le même compte
            if (clientSource.getCempt().getCemptN().equalsIgnoreCase(numCompteDest)) {
                System.out.println("❌ Impossible de transférer vers le même compte !");
                return;
            }

            System.out.println("Compte destination trouvé :");
            System.out.println("- Client : " + clientDest.getNom());
            System.out.println("- N° Compte : " + compteDest.getCemptN());
            System.out.println("- Type : " + (compteDest instanceof SavingsAccount ? "Compte Épargne" : "Compte Courant"));

            // Demander le montant
            System.out.println("\nEntrer le montant à transférer :");
            double montant = scanner.nextDouble();
            scanner.nextLine(); // Consommer la nouvelle ligne

            // Vérifications
            if (montant <= 0) {
                System.out.println("❌ Le montant doit être positif !");
                return;
            }

            double soldeSource = clientSource.getCempt().getSolde();
            if (soldeSource < montant) {
                System.out.println("❌ Solde insuffisant !");
                System.out.println("- Votre solde : " + soldeSource + " DH");
                System.out.println("- Montant demandé : " + montant + " DH");
                return;
            }


            System.out.println("\n=== CONFIRMATION DU TRANSFERT ===");
            System.out.println("De : " + clientSource.getNom() + " (N°" + clientSource.getCempt().getCemptN() + ")");
            System.out.println("Vers : " + clientDest.getNom() + " (N°" + compteDest.getCemptN() + ")");
            System.out.println("Montant : " + montant + " DH");
            System.out.println("\nConfirmer le transfert ? (O/N) :");
            String confirmation = scanner.nextLine().trim();

            if (!confirmation.equalsIgnoreCase("O")) {
                System.out.println("❌ Transfert annulé !");
                return;
            }

            // Effectuer le transfert
            double nouveauSoldeSource = soldeSource - montant;
            double nouveauSoldeDest = compteDest.getSolde() + montant;

            clientSource.getCempt().setSolde(nouveauSoldeSource);
            compteDest.setSolde(nouveauSoldeDest);

            System.out.println("\n✅ TRANSFERT RÉUSSI !");
            System.out.println("==================================");
            System.out.println("Compte source (" + clientSource.getCempt().getCemptN() + ") :");
            System.out.println("- Ancien solde : " + soldeSource + " DH");
            System.out.println("- Montant débité : " + montant + " DH");
            System.out.println("- Nouveau solde : " + nouveauSoldeSource + " DH");
            System.out.println("\nCompte destination (" + compteDest.getCemptN() + ") :");
            System.out.println("- Nouveau solde : " + nouveauSoldeDest + " DH");
            System.out.println("==================================");

        } catch (InputMismatchException e) {
            System.err.println("❌ Erreur : Le montant doit être un nombre !");
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println("❌ Erreur lors du transfert : " + e.getMessage());
            e.printStackTrace();
        }
    }





}