package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class DriveTest extends OpMode {

    double speed = 0.5;
    //Declare Objects
    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    Servo bin;
//    DcMotor viperLeft;
//    DcMotor viperRight;
    @Override
    public void init() {
        //Set up objects(DC Motors)
        backLeft = hardwareMap.get(DcMotor.class, "back left");
        frontLeft= hardwareMap.get(DcMotor.class, "front left");
        frontRight= hardwareMap.get(DcMotor.class, "front right");
        backRight= hardwareMap.get(DcMotor.class, "back right");
//        bin = hardwareMap.get(Servo.class,"bin");
//        viperLeft=hardwareMap.get(DcMotor.class,"viper1");
//        viperRight=hardwareMap.get(DcMotor.class,"viper2");
//        viperRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        // Use gamepad input to control motor
        double speedx=gamepad1.left_stick_x;
        backRight.setPower(-speedx);
        backLeft.setPower(-speedx);
        frontLeft.setPower(-speedx);
        frontRight.setPower(speedx);

        double speedy=gamepad1.left_stick_y;
        backRight.setPower(speedy);
        backLeft.setPower(-speedy);
        frontLeft.setPower(speedy);
        frontRight.setPower(speedy);

        double turn=gamepad1.right_stick_x;
        backRight.setPower(-turn);
        backLeft.setPower(turn);
        frontLeft.setPower(turn);
        frontRight.setPower(-turn);

//        if (gamepad1.a){
//            bin.setPosition(0);
//        } else if (gamepad1.x) {
//            bin.setPosition(90);
//        }


//        double vipers=gamepad1.left_trigger - gamepad1.right_trigger;
//        viperLeft.setPower(vipers);
//        viperRight.setPower(vipers);
//
//        double armMovement =


//        else if(gamepad1.a){
//            backLeft.setPower(speed);
//            frontLeft.setPower(speed);
//            frontRight.setPower(-speed);
//            backRight.setPower(-speed);
//        }
//
//        else if(gamepad1.x){
//            backLeft.setPower(-speed);
//            frontLeft.setPower(-speed);
//            frontRight.setPower(speed);
//            backRight.setPower(speed);
//        }
//        else if(gamepad1.b){
//            backLeft.setPower(speed);
//            frontLeft.setPower(-speed);
//            frontRight.setPower(speed);
//            backRight.setPower(-speed);
//
//        }
//        else if(gamepad1.dpad_right){
//            backLeft.setPower(-speed);
//            frontLeft.setPower(-speed);
//            frontRight.setPower(-speed);
//            backRight.setPower(-speed);
//        }
//        else if(gamepad1.dpad_left){
//            backLeft.setPower(speed);
//            frontLeft.setPower(speed);
//            frontRight.setPower(speed);
//            backRight.setPower(speed);
//        }


    }
}
