package entities;

public class Paziente extends Persona{

    private String reparto;
    private int giorniRicovero;
    private double costoGiornaliero;

    public Paziente(){}

    public Paziente(String nome, String cognome, String eta, String residenza, String reparto, int giorniRicovero,
            double costoGiornaliero) {
        super(nome, cognome, eta, residenza);
        this.reparto = reparto;
        this.giorniRicovero = giorniRicovero;
        this.costoGiornaliero = costoGiornaliero;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public int getGiorniRicovero() {
        return giorniRicovero;
    }

    public void setGiorniRicovero(int giorniRicovero) {
        this.giorniRicovero = giorniRicovero;
    }

    public double getCostoGiornaliero() {
        return costoGiornaliero;
    }

    public void setCostoGiornaliero(double costoGiornaliero) {
        this.costoGiornaliero = costoGiornaliero;
    }

    @Override
    public String toString() {
        return "Paziente " +  super.toString() + ", reparto=" + reparto + ", giorni di ricovero=" + giorniRicovero + ", costo giornaliero="
                + costoGiornaliero  + "\n";
    }
}
