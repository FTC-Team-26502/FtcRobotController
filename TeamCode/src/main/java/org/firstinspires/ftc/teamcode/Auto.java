package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Robot: Auto Drive By Encoder", group="Robot")
public class Auto extends OpMode {

    protected DcMotor backLeft;
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backRight;
    protected DcMotor viper;
    protected DcMotor arm;
    protected Servo rotate;
    protected CRServo intake;
public void init() {
        //Set up objects(DC Motors)
    backLeft = hardwareMap.get(DcMotor.class, "back left");
    frontLeft = hardwareMap.get(DcMotor.class, "front left");
    frontRight = hardwareMap.get(DcMotor.class, "front right");
    backRight = hardwareMap.get(DcMotor.class, "back right");
    viper = hardwareMap.get(DcMotor.class, "viper");
    arm = hardwareMap.get(DcMotor.class, "arm");
    rotate = hardwareMap.get(Servo.class, "twist");
    intake = hardwareMap.get(CRServo.class, "intake");
    frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void move(long time, double forwardPower, double sidewaysPower, double turnPower) {
        double backLeftPower = (sidewaysPower - forwardPower + turnPower)/10 ;
        double frontLeftPower = (-sidewaysPower +forwardPower + turnPower)/10;
        double backRightPower = (sidewaysPower + forwardPower + turnPower)/10;
        double frontRightPower = (-sidewaysPower - frontLeftPower + turnPower)/10;

        backLeft.setPower(backLeftPower);
        frontLeft.setPower(frontLeftPower);
        backRight.setPower(backRightPower);
        frontRight.setPower(frontRightPower);

        try {
            Thread.sleep(time); // Sleep for 1 second (1000 milliseconds)
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        backLeft.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);

    }
    public void armfromstartgrab() {
        arm.setPower(1);
        viper.setPower(1);
        try {
            Thread.sleep(1000); // Sleep for 1 second (1000 milliseconds)
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        intake.setPower(-0.5);
    }
    public void armupordown(boolean up) {
    if (up){
        arm.setPower(1);
        viper.setPower(1);
        try {
            Thread.sleep(1000); // Sleep for 1 second (1000 milliseconds)
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        intake.setPower(-0.5);
    }else{
        arm.setPower(-1);
        viper.setPower(-1);
        try {
            Thread.sleep(1000); // Sleep for 1 second (1000 milliseconds)
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        arm.setPower(0);
        viper.setPower(1);
        try {
            Thread.sleep(1000); // Sleep for 1 second (1000 milliseconds)
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        viper.setPower(0);
        intake.setPower(0.5);

    }
    }
    public void loop(){
        move(3000, 0.2,0,0);
        move(3000, 0,0.2,0);
        move(3000, 0,0,0.2);
//        armfromstartgrab();
        move(3000,0,0,0.2);
//        armupordown(true);
    }
}