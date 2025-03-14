package entities;

import java.time.LocalDate;
import java.time.Period;

public abstract class Persona {

    private String nome;
    private String cognome;
    private int eta;
    private String residenza;

    public Persona(){}

    public Persona(String nome, String cognome, String eta, String residenza) {
        setNome(nome);
        setCognome(cognome);
        setEta(eta);
        setResidenza(residenza);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta(eta);
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public int eta(String dataNascita){
        if(!dataNascita.isBlank() && dataNascita != null){
            LocalDate dob = LocalDate.parse(dataNascita);
            Period p = Period.between(dob, LocalDate.now());
            return p.getYears();
        }    
        return 0;
    }

    @Override
    public String toString() {
        return "nome=" + nome + ", cognome=" + cognome + ", età=" + eta + ", residenza=" + residenza;
    }
}
