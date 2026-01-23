import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Bank {
    private  String nom;
    private static ArrayList<Client> clientsBank = new ArrayList<>();
    private static ArrayList<Cempt>  cemptsBank = new ArrayList<>();
    private static ArrayList<SavingsAccount>  SavingsAccountBank = new ArrayList<>();


    public  Bank(String nom) {
        this.nom = nom;


    }


    public String getNom() {
        return nom;
    }

    public static ArrayList<Client> getClientsBank() {
        return clientsBank;
    }

    public static ArrayList<Cempt> getCemptsBank() {
        return cemptsBank;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }

    public static void setClientsBank(ArrayList<Client> clientsBank) {
        Bank.clientsBank = clientsBank;
    }

    public static void setCemptsBank(ArrayList<Cempt> cemptsBank) {
        Bank.cemptsBank = cemptsBank;
    }

    public static void setSavingsAccountBank(ArrayList<SavingsAccount> SavingsAccountBank) {
        Bank.SavingsAccountBank = SavingsAccountBank;
    }

    public static ArrayList<SavingsAccount> getSavingsAccountBank() {
        return SavingsAccountBank;
    }





 // appliquer les interer

    public static void appliquerInteretSurTousLesSavings() {
        for (SavingsAccount sa : getSavingsAccountBank()) {
            sa.Addinterer();
        }
    }






    //EXPORTER csv

    public static void exporterComptesCSVDirect() {
        String date = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
        String nomFichier = "C:\\Users\\arkka\\Downloads\\comptes_" + date + ".csv";

        try (FileWriter writer = new FileWriter(nomFichier)) {
            // Définir le séparateur (point-virgule pour Excel français)
            final String SEPARATEUR = ";";

            // En-tête CSV avec séparateur personnalisé
            writer.append("Numéro de compte" + SEPARATEUR +
                    "Nom du client" + SEPARATEUR +
                    "Type de compte" + SEPARATEUR +
                    "Solde (DH)" + "\n");

            int totalComptes = 0;

            // Comptes normaux
            for (Client client : Client.getListclients()) {
                if (client.getCempt() != null) {
                    java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
                    df.setDecimalSeparatorAlwaysShown(true);

                    // Remplacer la virgule décimale par un point pour Excel
                    String soldeFormate = df.format(client.getCempt().getSolde())
                            .replace(',', '.');

                    writer.append(String.format("\"%s\"%s\"%s\"%s\"Compte Courant\"%s%s\n",
                            client.getCempt().getCemptN(),
                            SEPARATEUR,
                            client.getNom(),
                            SEPARATEUR,
                            SEPARATEUR,
                            soldeFormate));
                    totalComptes++;
                }
            }

            // Comptes d'épargne
            for (Client client : Client.getListclients()) {
                if (client.getSavingsAccount() != null) {
                    java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
                    df.setDecimalSeparatorAlwaysShown(true);

                    String soldeFormate = df.format(client.getSavingsAccount().getSolde())
                            .replace(',', '.');

                    writer.append(String.format("\"%s\"%s\"%s\"%s\"Compte Épargne (%d%%)\"%s%s\n",
                            client.getSavingsAccount().getCemptN(),
                            SEPARATEUR,
                            client.getNom(),
                            SEPARATEUR,
                            client.getSavingsAccount().getTauxInterer(),
                            SEPARATEUR,
                            soldeFormate));
                    totalComptes++;
                }
            }

            System.out.println("✓ Fichier CSV créé avec succès : " + nomFichier);
            System.out.println("✓ Séparateur utilisé : " + SEPARATEUR);
            System.out.println("✓ Nombre total de comptes exportés : " + totalComptes);
            System.out.println("✓ Astuce : Ouvrez avec Excel et choisir ';' comme séparateur");

        } catch (IOException e) {
            System.err.println("✗ Erreur lors de la création du fichier CSV : " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }
    }










}