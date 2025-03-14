package entities;

public abstract class Dipendente extends Persona{

    public Dipendente(){}

    public Dipendente(String nome, String cognome, String eta, String residenza){
        super(nome, cognome, eta, residenza);
    }  

    public abstract double stipendio();
    public abstract double stipendio(boolean buonMedico);

    @Override
    public String toString() {
        return "Dipendente: " + super.toString();
    }
}
