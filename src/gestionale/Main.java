package gestionale;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        System.out.println("Benvenuto all'hotel Lipari!");

        boolean esci = false;

        while (!esci) {
            // Menu di login
            System.out.println("Seleziona un'opzione:");
            System.out.println("1. Login");
            System.out.println("2. Registrati");
            System.out.println("3. Esci");
            int sceltaLogin = leggiIntero(scanner);

            switch (sceltaLogin) {
                case 1:
                    // Effettua il login come admin o utente
                    System.out.println("Effettua il login come admin (a) o utente (u):");
                    String login = scanner.nextLine();

                    if (login.equalsIgnoreCase("a")) {
                        // Gestione dell'hotel per l'admin
                        Admin.gestisciHotel(hotel, scanner);
                    } else if (login.equalsIgnoreCase("u")) {
                        // Autenticazione dell'utente
                        System.out.println("Inserisci il tuo username:");
                        String username = scanner.nextLine();
                        System.out.println("Inserisci la tua password:");
                        String password = scanner.nextLine();

                        if (!hotel.autenticaUtente(username, password)) {
                            System.out.println("Utente non trovato o credenziali errate.");
                        } else {
                            // L'utente esiste, procedi con la prenotazione
                            gestisciPrenotazioni(scanner, hotel);
                        }
                    } else {
                        System.out.println("Input non valido. Riprova.");
                    }
                    break;

                case 2:
                    // Registrazione di un nuovo utente
                    System.out.println("Inserisci un nuovo username:");
                    String nuovoUsername = scanner.nextLine();
                    System.out.println("Inserisci una nuova password:");
                    String nuovaPassword = scanner.nextLine();
                    hotel.aggiungiUtente(nuovoUsername, nuovaPassword);
                    System.out.println("Utente creato con successo. Ora puoi effettuare il login.");

                    // Effettua login automatico con le nuove credenziali
                    System.out.println("Effettua il login con il nuovo utente.");
                    System.out.println("Inserisci il tuo username:");
                    String username = scanner.nextLine();
                    System.out.println("Inserisci la tua password:");
                    String password = scanner.nextLine();

                    if (!hotel.autenticaUtente(username, password)) {
                        System.out.println("Utente non trovato o credenziali errate.");
                    } else {
                        // L'utente esiste, procedi con la prenotazione
                        gestisciPrenotazioni(scanner, hotel);
                    }
                    break;

                case 3:
                    esci = true;
                    System.out.println("Uscita dal programma. Arrivederci!");
                    break;

                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }

        scanner.close();
    }

    private static void gestisciPrenotazioni(Scanner scanner, Hotel hotel) {
        System.out.println("Inserisci la data di inizio del soggiorno (formato yyyy-MM-dd):");
        LocalDate dataInizio = leggiData(scanner);
        hotel.visualizzaStanzeDisponibili(dataInizio);
        System.out.println("Inserisci il numero della stanza che desideri prenotare:");
        int numeroStanza = leggiIntero(scanner);
        System.out.println("Inserisci la data di fine del soggiorno (formato yyyy-MM-dd):");
        LocalDate dataFine = leggiData(scanner);
        double prezzoTotale = hotel.calcolaPrezzoSoggiorno(numeroStanza, dataInizio, dataFine);
        System.out.println("Il prezzo totale del soggiorno è: " + prezzoTotale);
        boolean prenotazioneEffettuata = hotel.prenotaStanza(numeroStanza, dataFine);
        if (prenotazioneEffettuata) {
            System.out.println("Prenotazione effettuata con successo!");
        } else {
            System.out.println("La stanza è già occupata per il periodo selezionato, prenotazione non riuscita.");
        }
    }

    static int leggiIntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Input non valido. Inserisci un numero intero:");
            scanner.next(); 
        }
        int numero = scanner.nextInt();
        scanner.nextLine(); 
        return numero;
    }

    private static LocalDate leggiData(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, DateTimeFormatter.ISO_DATE);
            } catch (Exception e) {
                System.out.println("Formato data non valido. Inserisci la data nel formato yyyy-MM-dd:");
            }
        }
    }
}
