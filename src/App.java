import java.util.Scanner;

import entities.Ospedale;

public class App {
    public static void main(String[] args) throws Exception {
        /*  proprietà dei pazienti: nome,cognome,eta,residenza,reparto,giorniRicovero,costoGiornaliero
            proprietà dei dottori: nome,cognome,eta,residenza, specializzazione, anniLavoro,tirocinante;
            proprietà dei chirurghi: nome,cognome,eta,residenza, specializzazione, anniLavoro,tirocinante,numeroInterventi,interventiRiusciti;
            chi gestirà i pazienti,i dottori e i chirurghi sarà un ospedale e dovrà poter permettere la visualizzazione di questi dati:
                    "1  - Lista pazienti\n" +
                    "2  - Lista Dottori\n" +
                    "3  - Lista Completa\n" +
                    "4  - Dottori con almeno 10 anni di lavoro\n" +
                    "5  - Dottori per città\n" +
                    "6  - Media stipendi per specializzazione\n" +
                    "7 - Lista buon dottore\n" +
                    "8 - Spesa totale\n" +
                    "9 - Reparto con più pazienti");

            Dottori
        -- stipendio dottori:
            ogni reparto ha uno stipendio diverso e un bonus per ogni anno di esperienza differente:
            Cardiologia: 1500 -> bonus 120
            Psichiatria: 1300 -> bonus 100
            Pediatria: 2000 -> bonus 200
            Altri: 1350 -> bonus 110
        -- buonMedico() => Se ha almeno 3 anni di esperienza, ma se è del reparto pediatria è un buon medico a prescindere
            dagli anni di esperienza
        -- stipendio dottori(boolean buonMedico)
            restituisce lo stipendio maggiorato del 5% se è un buon medico

            Chirurghi(il chirurgo è un dottore specializzato)
        -- stipendio: Allo stipendio calcolato dal medico aggiungiamo il 20%
            Per ogni intervento riuscito aggiungere 20
            Per ogni intervento fallito togliamo 10
            Ogni 20 interventi effettuati aggiungiamo 15
        -- buonMedico() oltre alle condizioni già imposte non può avere
            più di 1/3 di interventi falliti rispetto al totale
        -- stipendio chirurgo(boolean buonMedico)
            restituisce lo stipendio maggiorato dell'8% se è un buon chirurgo
         */

        Ospedale ospedale = new Ospedale("res/pazienti.txt", "res/dottori.txt", "res/chirurghi.txt");
         System.out.println("Benvenut* nell'ospedale SACRO QUORE!");
        Scanner tastiera = new Scanner(System.in);
        boolean esci = false;
        do {
            System.out.println("\nScegli una delle seguenti opzioni: \n" +
                    "1  - Lista pazienti\n" +
                    "2  - Lista Dottori\n" +
                    "3  - Lista Completa\n" +
                    "4  - Dottori con almeno 10 anni di lavoro\n" +
                    "5  - Dottori per città\n" +
                    "6  - Media stipendi per specializzazione\n" +
                    "7 - Lista buon dottore\n" +
                    "8 - Spesa totale\n" +
                    "9 - Reparto con più pazienti\n" +
                    "0  - ESCI\n");

            switch(Integer.parseInt(tastiera.nextLine())) {
                case 1:
                    System.out.println(ospedale.stampaLista(ospedale.getPazienti()));
                    break;
                case 2:
                    System.out.println(ospedale.stampaLista(ospedale.getDottori()));
                    break;
                case 3:
                    System.out.println(ospedale.stampaLista(ospedale.getPersone()));
                    break;
                case 4:
                    System.out.println(ospedale.stampaLista(ospedale.dottAnniLavoro()));
                    break;
                case 5:
                    System.out.println("Di che citta vuoi vedere i dottori?");
                    System.out.println(ospedale.stampaLista(ospedale.dottPerCitta(tastiera.nextLine())));
                    break;
                case 6:
                    System.out.println("inserisci il reparto di cui vuoi visualizzare la media: ");
                    String rep = tastiera.nextLine();
                    System.out.println("La media degli stipendi del reparto di" + rep+ " è: " +
                            ospedale.mediaStipSpecializzazione(rep) +" euro.");
                    break;
                case 7:
                    System.out.println(ospedale.stampaLista(ospedale.listaBraviDottori()));
                    break;
                case 8:
                    System.out.println("La spesa totale è: " + ospedale.spesaTotale() + " euro");
                    break;
                case 9:
                    System.out.println(ospedale.repartoMaxPazienti());
                    break;
                case 0:
                    esci=true;
                    break;
                default:
                    System.out.println("NON hai inserito un valore corretto!!");
                    break;
            } 

        }while(!esci);
        tastiera.close();
    }
}
