package org.firstinspires.ftc.teamcode;

import android.icu.text.Transliterator;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;



@TeleOp
public class Teliop extends OpMode {
    //Declare Objects
    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    DcMotor arm;
    Servo servoTires;
    Servo servoTwister;
    double SERVO_OPEN_POSITION = 0;
    double SERVO_CLOSED_POSITION = 1;
    double TWISTER_POSITION_UP = 0;
    double TWISTER_POSITION_DOWN = 0.5;

    @Override
    public void init() {
        //Set up objects(DC Motors)
        backLeft = hardwareMap.get(DcMotor.class, "back left");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        frontRight = hardwareMap.get(DcMotor.class, "front right");
        backRight = hardwareMap.get(DcMotor.class, "back right");
        servoTires = hardwareMap.get(Servo.class, "claw right");
        servoTwister = hardwareMap.get(Servo.class, "claw left");
        arm = hardwareMap.get(DcMotor.class, "arm");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);



    }


    @Override
    public void loop() {
        // check to see if we need to move the servo.

//        double x = gamepad1.left_stick_x;  // Left/right movement
//        double y = -gamepad1.left_stick_y;
//
//        backLeft.setPower(y);
//        frontLeft.setPower(y);
//        frontRight.setPower(y);
//        backRight.setPower(y);
//        backLeft.setPower(x);
//        frontLeft.setPower(x);
//        frontRight.setPower(x);
//        backRight.setPower(x);
//       /* double turnx = (gamepad1.right_stick_x);
//        backLeft.setPower(-turnx);
//        frontLeft.setPower(-turnx);
//        frontRight.setPower(-turnx);
//        backRight.setPower(-turnx);*/

        //Get values from controller
        double forward = -gamepad1.left_stick_y;  // Forward/backward
        double sideways = gamepad1.left_stick_x;

        // Calculate motor powers
        double frontLeftPower = forward + sideways;
        double frontRightPower = forward - sideways;
        double backLeftPower = forward - sideways;
        double backRightPower = forward + sideways;

        //Move the motors with powers
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        double armMove = gamepad1.left_trigger - gamepad1.right_trigger;
        arm.setPower(armMove);


        if (gamepad1.a) {
            servoTires.setPosition(100);

        } else if (gamepad1.b) {
            servoTires.setPosition(SERVO_CLOSED_POSITION);
        }

        if (gamepad1.x) {
            servoTwister.setPosition(100);
        } else if (gamepad1.y) {
            servoTwister.setPosition(TWISTER_POSITION_DOWN);
        }


    }
}