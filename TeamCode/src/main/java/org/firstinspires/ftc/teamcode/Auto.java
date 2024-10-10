package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
public class Auto {

    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    Servo clawleft;
    Servo clawright;
    double position = 0;
    Servo servoRight;
    Servo servoLeft;
    double closeright;
    double closeleft;

    @Override
    public void init() {
        //Set up objects(DC Motors)
        backLeft = hardwareMap.get(DcMotor.class, "back left");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        frontRight = hardwareMap.get(DcMotor.class, "front right");
        backRight = hardwareMap.get(DcMotor.class, "back right");
        servoRight = hardwareMap.get(Servo.class, "claw right");
        servoLeft = hardwareMap.get(Servo.class, "claw left");
        closeright = servoRight.getPosition();
        closeleft = servoLeft.getPosition();
    }
    public  void forward(double power, double distanceInCM){
        backLeft.setPower(power);
        backRight.setPower(power);
        frontLeft.setPower(power);
        frontRight.setPower(power);

    }
}
