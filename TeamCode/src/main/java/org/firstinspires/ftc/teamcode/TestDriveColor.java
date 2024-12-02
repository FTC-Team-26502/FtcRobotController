package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class TestDrive extends OpMode {
    protected DcMotor backLeft;
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backRight;
    protected DcMotor viper;
    protected DcMotor arm;
    protected Servo rotate;
    protected Servo intake;


    @Override
    public void init() {
        intake = hardwareMap.get(Servo.class, "intake");
    }

    @Override
    public void loop() {

        if(gamepad1.dpad_left) {
            // move to 0 degrees.
            intake.setPosition(0.5);
        }
        else if(gamepad1.dpad_right) {
            // move to 0 degrees.
            intake.setPosition(-0.5);
        }
    }
}