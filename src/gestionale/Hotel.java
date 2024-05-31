package gestionale;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hotel {
    private ArrayList<Stanza> stanze;
    private ArrayList<Utente> utenti;

    public Hotel() {
        stanze = new ArrayList<>();
        // Inizializzazione delle stanze dell'hotel (es. 10 stanze)
        for (int i = 0; i < 10; i++) {
            int qualita = (i % 5) + 1; // Per avere un ciclo di qualità da 1 a 5
            stanze.add(new Stanza(i + 1, qualita));
        }

        utenti = new ArrayList<>();
        // Aggiunta dell'utente ti appatta picciotto?
        utenti.add(new Utente("Daniele Di Bona", "pass"));
    }

    public boolean prenotaStanza(int numeroStanza, LocalDate dataFineOccupazione) {
        Stanza stanza = stanze.get(numeroStanza - 1); // Perché gli indici partono da 0
        if (!stanza.isOccupata() || stanza.getDataFineOccupazione().isBefore(LocalDate.now())) {
            stanza.occupaStanza(dataFineOccupazione);
            return true;
        } else {
            return false;
        }
    }

    public void visualizzaStanzeDisponibili(LocalDate dataInizio) {
        System.out.println("Stanze disponibili:");
        for (Stanza stanza : stanze) {
            if (!stanza.isOccupata() || stanza.getDataFineOccupazione().isBefore(dataInizio)) {
                System.out.println(stanza);
            }
        }
    }

    public double calcolaPrezzoSoggiorno(int numeroStanza, LocalDate dataInizio, LocalDate dataFine) {
        Stanza stanza = stanze.get(numeroStanza - 1);
        int giorni = (int) (dataFine.toEpochDay() - dataInizio.toEpochDay());
        return giorni * stanza.getPrezzo();
    }

    public void visualizzaTutteLeStanze() {
        System.out.println("Tutte le stanze dell'hotel:");
        for (Stanza stanza : stanze) {
            System.out.println(stanza);
        }
    }

    public void visualizzaTutteLePrenotazioni() {
        System.out.println("Tutte le prenotazioni:");
        for (Stanza stanza : stanze) {
            if (stanza.isOccupata()) {
                System.out.println("Stanza " + stanza.getNumeroStanza() + " è occupata fino al " + stanza.getDataFineOccupazione());
            } else {
                System.out.println("Stanza " + stanza.getNumeroStanza() + " è libera.");
            }
        }
    }

    public void aggiungiStanza(Stanza stanza) {
        stanze.add(stanza);
    }

    public void aggiungiUtente(String username, String password) {
        utenti.add(new Utente(username, password));
    }

    public boolean autenticaUtente(String username, String password) {
        for (Utente utente : utenti) {
            if (utente.getUsername().equals(username) && utente.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
