package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class DriveTesting extends BRBLinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        driveControls(false, true);
    }
}
