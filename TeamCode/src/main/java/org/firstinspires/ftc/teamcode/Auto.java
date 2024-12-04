package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Robot: Auto Drive By Encoder", group = "Robot")
public class Auto extends OpMode {

//    protected DcMotor backLeft;
//    protected DcMotor frontLeft;
//    protected DcMotor frontRight;
//    protected DcMotor backRight;
//    protected DcMotor viper;
    protected DcMotor arm;
//    protected Servo rotate;
//    protected CRServo intake;

    public void init() {
        //Set up objects(DC Motors)
//        backLeft = hardwareMap.get(DcMotor.class, "back left");
//        frontLeft = hardwareMap.get(DcMotor.class, "front left");
//        frontRight = hardwareMap.get(DcMotor.class, "back right");
//        backRight = hardwareMap.get(DcMotor.class, "front right");
//        viper = hardwareMap.get(DcMotor.class, "arm");
        arm = hardwareMap.get(DcMotor.class, "viper");
//        rotate = hardwareMap.get(Servo.class, "twist");
//        intake = hardwareMap.get(CRServo.class, "intake");
//        frontLeft.setDirection(DcMotor.Direction.REVERSE);
//        backLeft.setDirection(DcMotor.Direction.REVERSE);
//        frontRight.setDirection(DcMotor.Direction.FORWARD);
//        backRight.setDirection(DcMotor.Direction.FORWARD);

//        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        viper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(0);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(0.6);
//        arm.setPower(0);

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        viper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

//    public void move(long time, double forwardPower, double sidewaysPower, double turnPower) {
//        double frontLeftPower = (forwardPower - sidewaysPower - turnPower) / 1.5;
//        double backLeftPower = (forwardPower - sidewaysPower + turnPower) / 1.5;
//        double frontRightPower = (forwardPower + sidewaysPower + turnPower) / 1.5;
//        double backRightPower = (forwardPower + sidewaysPower - turnPower) / 1.5;
//
//        frontLeft.setPower(frontLeftPower);
//        backLeft.setPower(backLeftPower);
//        frontRight.setPower(frontRightPower);
//        backRight.setPower(backRightPower);
//
//        try {
//            Thread.sleep(time); // Sleep for 1 second (1000 milliseconds)
//        } catch (InterruptedException e) {
//            System.err.println("Interrupted: " + e.getMessage());
//        }
//        backLeft.setPower(0);
//        frontLeft.setPower(0);
//        backRight.setPower(0);
//        frontRight.setPower(0);
//
//    }

    public void armfromstartgrab() {
        arm.setTargetPosition(-2350);
    }
//
//    public void armupordown(boolean up) {
//        if (up) {
//            arm.setPower(0);
//            viper.setPower(1);
//            try {
//                Thread.sleep(1000); // Sleep for 1 second (1000 milliseconds)
//            } catch (InterruptedException e) {
//                System.err.println("Interrupted: " + e.getMessage());
//            }
//            intake.setPower(-0.5);
//        } else {
//            arm.setPower(-1);
//            viper.setPower(-1);
//            try {
//                Thread.sleep(1000); // Sleep for 1 second (1000 milliseconds)
//            } catch (InterruptedException e) {
//                System.err.println("Interrupted: " + e.getMessage());
//            }
//            arm.setPower(0);
//            viper.setPower(1);
//            try {
//                Thread.sleep(1000); // Sleep for 1 second (1000 milliseconds)
//            } catch (InterruptedException e) {
//                System.err.println("Interrupted: " + e.getMessage());
//            }
//            viper.setPower(0);
//            intake.setPower(0.5);
//
//        }
//    }

    public void start() {
        armfromstartgrab();
    }

    @Override
    public void loop() {
        telemetry.addData("Arm Pos", arm.getCurrentPosition());
    }
}