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
    }


    @Override
    public void loop() {
        // check to see if we need to move the servo.
        double speedx = (gamepad1.left_stick_x) * 3;
        double speedy = (gamepad1.left_stick_y) * 3;

        backLeft.setPower(speedy);
        frontLeft.setPower(speedy);
        frontRight.setPower(-speedy);
        backRight.setPower(-speedy);
        backLeft.setPower(-speedx);
        frontLeft.setPower(speedx);
        frontRight.setPower(speedx);
        backRight.setPower(-speedx);
        double turnx = (gamepad1.right_stick_x);
        backLeft.setPower(-turnx);
        frontLeft.setPower(-turnx);
        frontRight.setPower(-turnx);
        backRight.setPower(-turnx);
        double armMove = (gamepad1.left_trigger-gamepad1.right_trigger);
        arm.setPower(armMove);
        if (gamepad1.a){
            for( int i = 0; i < 300; i++) {
                servoTires.setPosition(i);
            }
        }
        if (gamepad1.b){
            for( int i = 300; i>0; i--) {
                servoTires.setPosition(i);
            }
        }
        if (gamepad1.x){
            servoTwister.setPosition(90);
        }
        if (gamepad1.y){
            servoTwister.setPosition(180);
        }

    }                       
}


