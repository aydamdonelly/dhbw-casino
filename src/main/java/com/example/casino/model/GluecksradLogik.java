package com.example.casino.model;

import com.example.casino.Controller;
import com.example.casino.GluecksradController;
import javafx.animation.RotateTransition;
import javafx.util.Duration;

public class GluecksradLogik {

    private static GluecksradController controller;
    // private static CasinoPlayer player = CasinoPlayer.getPlayer();
    public GluecksradLogik(Controller controller) {

        GluecksradLogik.controller = (GluecksradController) controller;
    }

    public void dreheRad() {
        // RotateTransition rotation = new RotateTransition(Duration.seconds(3), controller.getRadGroup());
        // rotation.setByAngle(360); // Drehung um 360 Grad
        //rotation.play(); // Starte die Animation

    }
}
