import java.util.*;
import java.util.concurrent.*;

public class  Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank=new Bank("MRXBANK");



        //ajouter interrer chaque 1 min
        ScheduledExecutorService scheduler = null;
        try {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    Bank.appliquerInteretSurTousLesSavings();
                   // System.out.println(" Intérêt ajouté (toutes les 1 minute)");
                } catch (Exception e) {
                    System.err.println("Erreur dans le scheduler: " + e.getMessage());
                }
            }, 1, 1, TimeUnit.MINUTES);
        } catch (Exception e) {
            System.err.println("Erreur d'initialisation du scheduler: " + e.getMessage());
        }






        // menu principale

        String choix;
        do{
            System.out.println("========================="+bank.getNom()+"===========================");
            System.out.println("*********************************************************************");
            System.out.println("========================ENTRER votre choix========================== ");
            System.out.println(" \n 1:ajouter un client  \n 2:afficher client \n 3:cree cempt \n 4:versser dans votre cempt \n 5:retrait \n 6:creat saving account  \n 7:transfert entre comptes  \n S:suprimer un cempt  \n E:exporter en csv  \n Q:quiter ");
            choix=scanner.nextLine();
            if(choix.equals("1")){
                Client Nouveauclient= Client.ajouterclient(scanner);
            }
            else if(choix.equals("2")){
                Client.afficherclient();
            }
            else if(choix.equalsIgnoreCase("Q")){
                System.out.println("au revoir");
                break;
            }
            else if (choix.equals("3")) {
                System.out.println("entrer le Cin de votre client :");
                String  recherchclient= scanner.nextLine();

                Client clientTrouve=null;
                for(Client client:Client.getListclients()){
                    if(client.getCIN().equalsIgnoreCase(recherchclient)){
                        clientTrouve=client;
                        break;
                    }
                }
                if(clientTrouve!=null){
                    Client.ajoutercempt(scanner,clientTrouve);

                }else {
                    System.out.println("Erreur : cette client ne corresponde pas !");
                }


            }


            else if(choix.equals("4")){
                System.out.println("entrer le Cin de votre client :");
                String  rechercherclient=scanner.nextLine();
                Client clientTrouve=null;

                for(Client client:Client.getListclients()){
                    if(client.getCIN().equalsIgnoreCase(rechercherclient)){
                        clientTrouve=client;

                        break;
                    }
                }
                if(clientTrouve!=null){
                    Client.versser(scanner,clientTrouve);
                }
                else {
                    System.out.println("Erreur : cette client ou cempt ne corresponde pas !");
                }

            }

            else if(choix.equals("5")){
                System.out.println("entrer le Cin de votre client :");
                String  rechercherclient=scanner.nextLine();
                Client clientTrouve=null;

                for(Client client:Client.getListclients()){
                    if(client.getCIN().equalsIgnoreCase(rechercherclient)){
                        clientTrouve=client;

                        break;
                    }
                }
                if(clientTrouve!=null){
                    Client.retrait(scanner,clientTrouve);
                }
                else {
                    System.out.println("Erreur : cette client ou cempt ne corresponde pas !");
                }

            }

            else if (choix.equals("6")) {
                System.out.println("entrer le Cin de votre client :");
                String  recherchclient= scanner.nextLine();

                Client clientTrouve=null;
                for(Client client:Client.getListclients()){
                    if(client.getCIN().equalsIgnoreCase(recherchclient)){
                        clientTrouve=client;
                        break;
                    }
                }
                if(clientTrouve!=null){
                    Client.ajouteSavingAccount(scanner,clientTrouve);

                }else {
                    System.out.println("Erreur : cette client ne corresponde pas !");
                }
            }

            else if(choix.equals("7")){
                Client.transfererArgent(scanner);
            }




            else if(choix.equalsIgnoreCase("S")){
                System.out.println("entrer le Cin de votre client :");
                String  rechercherclient=scanner.nextLine();
                Client clientTrouve=null;
                for(Client client:Client.getListclients()){
                    if(client.getCIN().equalsIgnoreCase(rechercherclient)){
                        clientTrouve=client;
                        break;
                    }
                }
                if(clientTrouve!=null){
                    Client.suprimer(scanner,clientTrouve);
                }

                else {
                    System.out.println("Erreur : cette client ne corresponde pas !");
                }
            }

            else if(choix.equalsIgnoreCase("E")){

                Bank.exporterComptesCSVDirect();
            }



            else{
                System.out.println("choix invalid ");
            }
        }while (!choix.equalsIgnoreCase("Q"));


        try {
            if (scheduler != null) {
                scheduler.shutdownNow();
                System.out.println("l'ajout d'interer stoper");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'arrêt du scheduler: " + e.getMessage());
        }


    }
}


