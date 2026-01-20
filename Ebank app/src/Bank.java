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
            // En-tête CSV
            writer.append("Numéro de compte,Nom du client,Type de compte,Solde (DH)\n");

            int totalComptes = 0;

            // Comptes normaux
            for (Client client : Client.getListclients()) {
                if (client.getCempt() != null) {
                    // l'utilisation du point décimal avec DecimalFormat
                    java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
                    df.setDecimalSeparatorAlwaysShown(true);

                    writer.append(String.format("\"%s\",\"%s\",\"Compte Courant\",%s\n",
                            client.getCempt().getCemptN(),
                            client.getNom(),
                            df.format(client.getCempt().getSolde())));
                    totalComptes++;
                }
            }

            // Comptes d'épargne
            for (Client client : Client.getListclients()) {
                if (client.getSavingsAccount() != null) {
                    java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
                    df.setDecimalSeparatorAlwaysShown(true);

                    writer.append(String.format("\"%s\",\"%s\",\"Compte Épargne (%d%%)\",%s\n",
                            client.getSavingsAccount().getCemptN(),
                            client.getNom(),
                            client.getSavingsAccount().getTauxInterer(),
                            df.format(client.getSavingsAccount().getSolde())));
                    totalComptes++;
                }
            }

            System.out.println("✓ Fichier CSV créé avec succès : " + nomFichier);
            System.out.println("✓ Nombre total de comptes exportés : " + totalComptes);

        } catch (IOException e) {
            System.err.println("✗ Erreur lors de la création du fichier CSV : " + e.getMessage());

            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }
    }












}