package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class JoystickControl extends OpMode {

    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    @Override
    public void init() {
        backLeft = hardwareMap.get(DcMotor.class, "back left");
        frontLeft= hardwareMap.get(DcMotor.class, "front left");
        frontRight= hardwareMap.get(DcMotor.class, "front right");
        backRight= hardwareMap.get(DcMotor.class, "back right");
    }

    @Override
    public void loop() {

    }
}
