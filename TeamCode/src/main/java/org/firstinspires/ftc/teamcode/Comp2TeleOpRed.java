package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp Red Specimens")
public class Comp2TeleOpRedSpecimens extends  Comp2TeleOpSpecimens{
    @Override
    public void runOpMode() {
        initOpMode(true);
        // Wait for the game to start (driver presses START).
        loopOpMode();
    }
}
