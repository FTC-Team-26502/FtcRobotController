package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class encoder_test extends OpMode {
    protected DcMotor forward;


    @Override
    public void init() {
        forward = hardwareMap.get(DcMotor.class, "horizontal");
    }  public void loop() {
        telemetry.addData("Status", "Running");
        telemetry.addData("forward", forward.getCurrentPosition());
        telemetry.update();
    }
}

