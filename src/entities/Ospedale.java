package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ospedale {

    private ArrayList<Persona> persone;

    public Ospedale(String percorso, String p2, String p3) {
        persone = new ArrayList<>();
        ArrayList<String[]> righeSplittate = leggiESplitta(percorso);
        if (percorso.equalsIgnoreCase("res/pazienti.txt")) {
            for (String[] righe : righeSplittate)
                persone.add(new Paziente(righe[0], righe[1], righe[2], righe[3],
                        righe[4], Integer.parseInt(righe[5]),
                        Integer.parseInt(righe[6])));
        }

        righeSplittate = leggiESplitta(p2);
        if (p2.equalsIgnoreCase("res/dottori.txt")) {
            for (String[] righe : righeSplittate) {
                persone.add(new Dottore(righe[0], righe[1], righe[2], righe[3], righe[4],
                        Integer.parseInt(righe[5]), (righe[6]).equalsIgnoreCase("si")));
            }
        }
        righeSplittate = leggiESplitta(p3);
        if (p3.equalsIgnoreCase("res/chirurghi.txt")) {
            for (String[] righe : righeSplittate) {
                persone.add(new Chirurgo(righe[0], righe[1], righe[2], righe[3], righe[4],
                        Integer.parseInt(righe[5]), (righe[6]).equalsIgnoreCase("si"),
                        Integer.parseInt(righe[7]), Integer.parseInt(righe[8])));
            }
        }
    }

    public ArrayList<Persona> getPersone() {
        return persone;
    }

    public ArrayList<Persona> getPazienti() {
        ArrayList<Persona> ris = new ArrayList<>();
        for (Persona persona : persone) {
            if (persona instanceof Paziente) {
                ris.add(persona);
            }
        }
        return ris;
    }

    public ArrayList<Persona> getDottori() {
        ArrayList<Persona> ris = new ArrayList<>();
        for (Persona persona : persone) {
            if (persona instanceof Dottore d) {
                ris.add(d);
            }
        }
        return ris;
    }

    public ArrayList<Persona> getChirurghi() {
        ArrayList<Persona> ris = new ArrayList<>();
        for (Persona persona : getDottori()) {
            if (persona instanceof Chirurgo c) {
                ris.add(c);
            }
        }
        return ris;
    }

    public ArrayList<Persona> dottAnniLavoro() {
        ArrayList<Persona> ris = new ArrayList<>();
        for (Persona dottore : getDottori()) {
            if (((Dottore)dottore).getAnniLavoro() >= 10) {
                ris.add((Dottore)dottore);
            }
        }
        return ris;
    }

    public ArrayList<Persona> dottPerCitta(String citta) {
        ArrayList<Persona> ris = new ArrayList<>();
        for (Persona dottore : getDottori()) {
            if(dottore.getResidenza().equalsIgnoreCase(citta)){
                ris.add(dottore);
            }
        }
        return ris;
    }

    public double mediaStipSpecializzazione(String specializzazione){
        double somma = 0;
        for (Persona d : getDottori()) {
            Dottore dott = (Dottore)d;
            somma+=dott.stipendio(dott.buonMedico());
        }
        return (getDottori().size()>0)?somma/getDottori().size():0;
    }

    public ArrayList<Persona> listaBraviDottori(){
        ArrayList<Persona> ris = new ArrayList<>();
        for (Persona dottore : getDottori()) {
            if (((Dottore)dottore).buonMedico()) {
                ris.add(dottore);
            }
        }
        return ris;
    }

    public double spesaTotale(){
        double spesa = 0;
        for(Persona p : getPazienti()){
            spesa += ((Paziente)p).getCostoGiornaliero() * ((Paziente)p).getGiorniRicovero();
        }
        for (Persona d : getDottori()){
            spesa += ((Dottore)d).stipendio(((Dottore)d).buonMedico());
        }
        for (Persona d : getChirurghi()){
            spesa += ((Chirurgo)d).stipendio(((Chirurgo)d).buonMedico());
        }
        return spesa;
    }

    public ArrayList<String> repartoMaxPazienti() {
        ArrayList<String> reparti = new ArrayList<>();
        ArrayList<Integer> contatori = new ArrayList<>();

        for (Persona paziente : getPazienti()) {
            String reparto = ((Paziente) paziente).getReparto();
            int index = reparti.indexOf(reparto);
            if (index != -1) {
            contatori.set(index, contatori.get(index) + 1);
            } else {
            reparti.add(reparto);
            contatori.add(1);
            }
        }

        int massimo = 0;
        for (int count : contatori) {
            if (count > massimo) {
                massimo = count;
            }
        }
        
        ArrayList<String> repartiPiuPopolati = new ArrayList<>();
        for (int i = 0; i < contatori.size(); i++) {
            if (contatori.get(i) == massimo) {
                repartiPiuPopolati.add(reparti.get(i));
            }
        }
        
        return repartiPiuPopolati;
    }
    
    public String stampaLista(ArrayList<Persona> lista){
        String ris = "";
        for (Persona persona : lista) {
            ris += persona + "\n";
        }
        return ris;
    }
    private ArrayList<String> leggi(String percorso) {
        ArrayList<String> ris = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(percorso));
            while (scanner.hasNextLine()) {
                ris.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                scanner.close();
            } catch (Exception e) {
                System.out.println("scanner non chiuso");
            }
        }
        return ris;
    }

    private ArrayList<String[]> splitta(ArrayList<String> righe) {
        ArrayList<String[]> ris = new ArrayList<>();
        for (String string : righe) {
            ris.add(string.split(","));
        }
        return ris;
    }

    public ArrayList<String[]> leggiESplitta(String percorso){
        ArrayList<String> righe  = leggi(percorso);
        ArrayList<String[]> ris = splitta(righe);
        return ris;
    }
}