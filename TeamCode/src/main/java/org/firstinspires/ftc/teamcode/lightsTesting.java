package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public abstract class lightsTesting extends BRBLinearOpMode{

    @Override
    public void runOpMode() {
        initOpMode(true);
        // Wait for the game to start (driver presses START).
        loopOpMode();
    }
    protected void loopOpMode() {
        final double YELLOW = 0.388;
        final double BLUE = 0.611;
        final double RED = 0.279;
        final double SAGE = 0.44;
        waitForStart();
        while (opModeIsActive()) if (gamepad1.a) {
            leftLight.setPosition(YELLOW);
            rightLight.setPosition(YELLOW);
        } else if (gamepad1.b) {
            leftLight.setPosition(BLUE);
            rightLight.setPosition(BLUE);
        } else if (gamepad1.x) {
            leftLight.setPosition(RED);
            rightLight.setPosition(RED);
        } else if (gamepad2.y) {
            leftLight.setPosition(SAGE);
            rightLight.setPosition(SAGE);
        }
    }

}
