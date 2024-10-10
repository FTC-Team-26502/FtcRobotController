package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TestDrive extends OpMode {

    double speed = 0.5;
    //Declare Objects
    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    @Override
    public void init() {
        //Set up objects(DC Motors)
        backLeft = hardwareMap.get(DcMotor.class, "back left");
        frontLeft= hardwareMap.get(DcMotor.class, "front left");
        frontRight= hardwareMap.get(DcMotor.class, "front right");
        backRight= hardwareMap.get(DcMotor.class, "back right");
    }

    @Override
    public void loop() {
        // Use gamepad input to control motor
        speed=gamepad1.left_stick_x;
        if(gamepad1.y){
            backLeft.setPower(-speed);
            frontLeft.setPower(-speed);
            frontRight.setPower(speed);
            backRight.setPower(speed);

        }
        else if(gamepad1.a){
            backLeft.setPower(speed);
            frontLeft.setPower(speed);
            frontRight.setPower(-speed);
            backRight.setPower(-speed);
        }

        else if(gamepad1.x){
            backLeft.setPower(-speed);
            frontLeft.setPower(speed);
            frontRight.setPower(speed);
            backRight.setPower(-speed);
        }
        else if(gamepad1.b){
            backLeft.setPower(speed);
            frontLeft.setPower(-speed);
            frontRight.setPower(-speed);
            backRight.setPower(speed);

        }
        else if(gamepad1.dpad_right){
            backLeft.setPower(-speed);
            frontLeft.setPower(-speed);
            frontRight.setPower(-speed);
            backRight.setPower(-speed);
        }
        else if(gamepad1.dpad_left){
            backLeft.setPower(speed);
            frontLeft.setPower(speed);
            frontRight.setPower(speed);
            backRight.setPower(speed);
        }

        else {
            //set power back to zero
            backLeft.setPower(0);
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
        }
        if(gamepad1.a){
            backLeft.setPower(0.22);
            frontLeft.setPower(0.22);
            backRight.setPower(-0.22);
            frontRight.setPower(-0.22);
        }
        if(gamepad1.x){
            backLeft.setPower(-0.22);
            frontLeft.setPower(0.22);
            backRight.setPower(-0.22);
            frontRight.setPower(0.22);
        }
        if(gamepad1.b){
            backLeft.setPower(0.22);
            frontLeft.setPower(-0.22);
            backRight.setPower(0.22);
            frontRight.setPower(-0.22);
        }
        else{
            backLeft.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);

        }
    }
}
