package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Blue Samples")
public class Comp2TeleOpBlueSamples extends Comp2TeleOpSamples{
    @Override
    public void runOpMode() {
        initOpMode(false);
        // Wait for the game to start (driver presses START)
        loopOpMode();
    }
}
