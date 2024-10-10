package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class Drivetrain extends OpMode {

    double speed = 0.25;
    //Declare objects


    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;

    @Override
    public void init() {
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");


        // set up objects
    }

    @Override
    public void loop() {
        // use Gamepad input to control motor
        speed = gamepad1.left_stick_x;
        if (gamepad1.y) {
            backLeft.setPower(-speed);
            frontLeft.setPower(-speed);
            frontRight.setPower(speed);
            backRight.setPower(speed);
        } else if (gamepad1.a) {
            backLeft.setPower(speed);
            frontLeft.setPower(speed);
            frontRight.setPower(-speed);
            backRight.setPower(-speed);


        }   if (gamepad1.x) {
            backLeft.setPower(speed);
            frontLeft.setPower(-speed);
            frontRight.setPower(speed);
            backRight.setPower(-speed);
        } else if (gamepad1.b) {
            backLeft.setPower(-speed);
            frontLeft.setPower(speed);
            frontRight.setPower(speed);
            backRight.setPower(-speed);
        }
        else {
            //set power back to 0
            backLeft.setPower(0);
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
        }
    }

}
