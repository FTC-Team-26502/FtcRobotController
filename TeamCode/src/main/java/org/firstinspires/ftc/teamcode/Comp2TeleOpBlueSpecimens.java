package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp Blue Specimens")
public class Comp2TeleOpBlueSpecimens extends Comp2TeleOpSpecimens{
    @Override
    public void runOpMode() {
        initOpMode(false);
        // Wait for the game to start (driver presses START)
        loopOpMode();
    }
}
