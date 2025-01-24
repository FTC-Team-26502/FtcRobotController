package org.firstinspires.ftc.teamcode;

public class Comp2AutoBlueSpecimens extends Comp2AutoSpecimens{

    public void runOpMode() {
        initOpMode(false);
        // Wait for the game to start (driver presses START)
        try {
            loopOpMode();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

