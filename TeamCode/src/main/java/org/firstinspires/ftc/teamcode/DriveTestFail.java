package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;



@TeleOp
public class DriveTestFail extends OpMode {
    //Declare Objects
    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;

    @Override
    public void init() {
        //Set up objects(DC Motors)
        backLeft = hardwareMap.get(DcMotor.class, "back left");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        frontRight = hardwareMap.get(DcMotor.class, "front right");
        backRight = hardwareMap.get(DcMotor.class, "back right");

    }


    @Override
    public void loop() {
        if (gamepad1.left_bumper || gamepad2.left_bumper) {

            backLeft.setPower(0);
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
        }
        else {
            double speedx = (gamepad1.left_stick_x) * 3;
            double speedy = (gamepad1.left_stick_y) * 3;
            backLeft.setPower(speedy);
            frontLeft.setPower(speedy);
            frontRight.setPower(-speedy);
            backRight.setPower(-speedy);
            backLeft.setPower(speedx);
            frontLeft.setPower(-speedx);
            frontRight.setPower(-speedx);
            backRight.setPower(speedx);
            double turnx = (gamepad1.right_stick_x);
            backLeft.setPower(-turnx);
            frontLeft.setPower(-turnx);
            frontRight.setPower(-turnx);
            backRight.setPower(-turnx);
            if (gamepad1.left_bumper) {

                backLeft.setPower(0);
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);
            }
            double speedx2 = (gamepad2.left_stick_x) * 2;
            double speedy2 = (gamepad2.left_stick_y) * 2;
            backLeft.setPower(speedy2);
            frontLeft.setPower(speedy2);
            frontRight.setPower(-speedy2);
            backRight.setPower(-speedy2);
            backLeft.setPower(speedx2);
            frontLeft.setPower(-speedx2);
            frontRight.setPower(-speedx2);
            backRight.setPower(speedx2);
            double turnx2 = (gamepad2.right_stick_x);
            backLeft.setPower(-turnx2);
            frontLeft.setPower(-turnx2);
            frontRight.setPower(-turnx2);
            backRight.setPower(-turnx2);

        }
    }


}



