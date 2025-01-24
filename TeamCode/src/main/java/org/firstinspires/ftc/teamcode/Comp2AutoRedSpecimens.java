package org.firstinspires.ftc.teamcode;

public class Comp2AutoRedSpecimens extends  Comp2AutoSpecimens {
    public void runOpMode() {
        initOpMode(true);
        // Wait for the game to start (driver presses START)
        try {
            loopOpMode();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
