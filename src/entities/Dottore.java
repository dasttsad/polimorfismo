package entities;

public class Dottore extends Dipendente {

    private String specializzazione;
    private int anniLavoro;
    private boolean tirocinante;

    public Dottore(){}

    public Dottore(String nome, String cognome, String eta, String residenza,String specializzazione, int anniLavoro, boolean tirocinante) {
        super(nome,cognome,eta,residenza);
        setSpecializzazione(specializzazione);
        setAnniLavoro(anniLavoro);
        setTirocinante(tirocinante);
    }


    public String getSpecializzazione() {
        return specializzazione;
    }
    public void setSpecializzazione(String specializzazione) {
        this.specializzazione = specializzazione;
    }

    public int getAnniLavoro() {
        return anniLavoro;
    }
    public void setAnniLavoro(int anniLavoro) {
        this.anniLavoro = anniLavoro;
    }

    public boolean isTirocinante() {
        return tirocinante;
    }
    public void setTirocinante(boolean tirocinante) {
        this.tirocinante = tirocinante;
    }


    public boolean buonMedico(){
        return (anniLavoro >= 3 || specializzazione.contains("pediatr"));
    }
    
    
    @Override
    public double stipendio() {
        double stipendio = 0;
        if(!specializzazione.isBlank() && specializzazione != null){
            switch (specializzazione.toLowerCase()) {
                case "cardiologo", "cardiologia":
                    stipendio = 1500 + (anniLavoro*120);
                    break;

                case "psichiatria":
                case "psichiatra":
                    stipendio = 1300 + (anniLavoro*100);
                    break;

                case "pediatra", "pediatria":
                    stipendio = 2000 + (anniLavoro*200);
                    break;

                default:
                    stipendio = 1350 + (anniLavoro*110);
                    break;
            }
        }
        return stipendio;
    }

    @Override
    public double stipendio(boolean buonMedico) {
        return (buonMedico())? stipendio()* 1.05 : stipendio();
    }

    @Override
    public String toString() {
        return "Dottore: "+ super.toString() + ", specializzazione=" + specializzazione + ", anni di lavoro=" + anniLavoro +
         ", tirocinante=" + (tirocinante?"sì":"no");
    }
}
