package gestionale;


import java.util.Scanner;

public class Admin {
    public static void gestisciHotel(Hotel hotel, Scanner scanner) {
        System.out.println("Inserisci le credenziali dell'amministratore:");
        System.out.println("Username: ");
        String adminUsername = scanner.nextLine();
        System.out.println("Password: ");
        String adminPassword = scanner.nextLine();

        // Controlla se le credenziali inserite corrispondono alle credenziali dell'amministratore
        if (adminUsername.equals("admin") && adminPassword.equals("pass")) {
            // Accesso come admin...
            System.out.println("Accesso come admin...");
            
            boolean esci = false;
            while (!esci) {
                System.out.println("Seleziona un'opzione:");
                System.out.println("1. Aggiungi una nuova stanza");
                System.out.println("2. Visualizza tutte le stanze");
                System.out.println("3. Visualizza tutte le prenotazioni");
                System.out.println("4. Esci");
                int scelta = Main.leggiIntero(scanner);

                switch (scelta) {
                    case 1:
                        System.out.println("Inserisci il numero della nuova stanza:");
                        int numeroStanza = Main.leggiIntero(scanner);
                        System.out.println("Inserisci la qualità della nuova stanza (1-5):");
                        int qualita = Main.leggiIntero(scanner);
                        hotel.aggiungiStanza(new Stanza(numeroStanza, qualita));
                        System.out.println("Nuova stanza aggiunta con successo!");
                        break;
                    case 2:
                        hotel.visualizzaTutteLeStanze();
                        break;
                    case 3:
                        hotel.visualizzaTutteLePrenotazioni();
                        break;
                    case 4:
                        esci = true;
                        System.out.println("Uscita dal menu admin.");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            }
        } else {
            System.out.println("Credenziali errate. Accesso negato.");
        }
    }
}
