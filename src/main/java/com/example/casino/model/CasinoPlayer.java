package com.example.casino.model;

import com.example.casino.CasinoController;
import com.example.casino.Controller;
import com.example.casino.model.crashModel.SaveHandler;

import java.util.Locale;

public class CasinoPlayer {

    //Standardspecs
    private String name = "Martin";
    private float kontostand = 1000; //startkapital

    private String kontostand_path = "src/main/resources/saves/player_kontostand.txt";
    private KontostandSaveHandler kontostandSaveHandler = new KontostandSaveHandler();

    private int last_gluecksrad_time;

    //Anbindung Model->Controller
    public CasinoController controller;

    //Singleton
    private static CasinoPlayer player = null;

    private CasinoPlayer(Controller controller) {
        this.controller = (CasinoController) controller;
        float loadedKontostant = kontostandSaveHandler.load_num(kontostand_path);
        setKontostand(loadedKontostant);
    }

    private CasinoPlayer() {
        System.out.println("Spieler ohne Params erstellt");
    }

    public static CasinoPlayer getPlayer(Controller controller) {
        if(player == null) {
            player = new CasinoPlayer(controller);
        }
        return player;
    }

    public static CasinoPlayer getPlayer() {
        if(player == null) {
            player = new CasinoPlayer();
        }
        return player;
    }

    //Spiellogik
    public boolean checkBet(float angefragterEinsatz) throws InsufficientFundsException, NegativeBetException {
        if(angefragterEinsatz > kontostand) {
            throw new InsufficientFundsException(angefragterEinsatz-kontostand);
        } else if (angefragterEinsatz <=0) throw new NegativeBetException();
        return true;
    }

    //Getter u. Setter
    public float getKontostand() {
        return kontostand;
    }

    public void setKontostand(float newkontostand) {
        String newStringKontostand = String.format(Locale.US,"%.2f" , newkontostand) ;
        kontostandSaveHandler.save_num(kontostand_path, newkontostand);
        kontostand = Float.parseFloat(newStringKontostand);
        controller.updateKonto(newStringKontostand);
    }

    public void setKontostandThread(float newkontostand){
        String newStringKontostand = String.format(Locale.US,"%.2f" , newkontostand) ;
        kontostandSaveHandler.save_num(kontostand_path, newkontostand);
        System.out.println(kontostand);
        kontostand = Float.parseFloat(newStringKontostand);

        controller.updateKontoThread(newStringKontostand);
    }

    public int getLast_gluecksrad_time() {
        return last_gluecksrad_time;
    }

    public void setLast_gluecksrad_time(int last_gluecksrad_time) {
        this.last_gluecksrad_time = last_gluecksrad_time;
    }
}
