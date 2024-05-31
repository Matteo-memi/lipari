package gestionale;

import java.time.LocalDate;

public class Stanza {
    private int numeroStanza;
    private int qualita; // Da 1 a 5
    private boolean occupata;
    private LocalDate dataFineOccupazione;
    private double prezzo;

    public Stanza(int numeroStanza, int qualita) {
        this.numeroStanza = numeroStanza;
        this.qualita = qualita;
        this.occupata = false;
        this.prezzo = qualita * 50; // Prezzo base per qualità
    }

    public int getNumeroStanza() {
        return numeroStanza;
    }

    public boolean isOccupata() {
        return occupata;
    }

    public LocalDate getDataFineOccupazione() {
        return dataFineOccupazione;
    }

    public void occupaStanza(LocalDate dataFineOccupazione) {
        this.occupata = true;
        this.dataFineOccupazione = dataFineOccupazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    @Override
    public String toString() {
        return "Stanza " + numeroStanza + " [Qualità: " + qualita + " stelle, Prezzo: " + prezzo + ", Occupata: " + occupata + "]";
    }
}
