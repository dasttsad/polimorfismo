package entities;

public class Chirurgo extends Dottore{
    
    private int numeroInterventi;
    private int interventiRiusciti;

    public Chirurgo(){}

    public Chirurgo(String nome, String cognome, String eta, String residenza, String specializzazione, int anniLavoro,
            boolean tirocinante, int numeroInterventi, int interventiRiusciti) {
        super(nome, cognome, eta, residenza, specializzazione, anniLavoro, tirocinante);
        setNumeroInterventi(numeroInterventi);
        setInterventiRiusciti(interventiRiusciti);
    }

    public int getNumeroInterventi() {
        return numeroInterventi;
    }

    public void setNumeroInterventi(int numeroInterventi) {
        this.numeroInterventi = numeroInterventi;
    }

    public int getInterventiRiusciti() {
        return interventiRiusciti;
    }

    public void setInterventiRiusciti(int interventiRiusciti) {
        this.interventiRiusciti = interventiRiusciti;
    }

    @Override
    public double stipendio(){
        double base = super.stipendio() * 1.2;
        base += 20 *interventiRiusciti;
        base -=  (numeroInterventi- interventiRiusciti) * 10;
        base += (int)(interventiRiusciti/20) * 15;
        return base;
    }

    @Override
    public boolean buonMedico(){
        return super.buonMedico() && interventiRiusciti/numeroInterventi >= 2/3;
    }

    @Override
    public double stipendio(boolean buonMedico){
        return (buonMedico())? stipendio()* 1.08 : stipendio(); 
    }

    @Override
    public String toString() {
        return "Chirurgo: "+ super.toString() + ", numero di interventi=" + 
        numeroInterventi + ", numero di interventi riusciti=" + interventiRiusciti;
    }
}
