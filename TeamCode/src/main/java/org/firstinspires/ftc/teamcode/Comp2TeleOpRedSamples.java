package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp Red Samples")
public class Comp2TeleOpRedSamples extends Comp2TeleOpSamples {

    @Override
    public void runOpMode() {
        initOpMode(true);
        // Wait for the game to start (driver presses START)
        loopOpMode();
    }

}
