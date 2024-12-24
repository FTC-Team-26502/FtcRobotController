package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
@Autonomous
public class Auto extends LinearOpMode {

    public static final double MOTOR_TICK_FOR_ONE_ROTATION=538;
    public static final double CIRCUMFERENCE = 104;
    public static final double ROTATIONS_NEEDED = 18/CIRCUMFERENCE;
    public static final int ENCODER_DRIVING_TARGET = (int)(ROTATIONS_NEEDED*MOTOR_TICK_FOR_ONE_ROTATION);

    protected DcMotor backLeft;
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backRight;

    public void forward(double amountInInch){
        frontLeft.setTargetPosition(ENCODER_DRIVING_TARGET);
        backRight.setTargetPosition(ENCODER_DRIVING_TARGET);
        backLeft.setTargetPosition(ENCODER_DRIVING_TARGET);
        frontRight.setTargetPosition(ENCODER_DRIVING_TARGET);
    }

    @Override
    public void runOpMode() {
        // Initialize motors
        backLeft = hardwareMap.get(DcMotor.class, "backleft");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        frontRight = hardwareMap.get(DcMotor.class, "back right");
        backRight = hardwareMap.get(DcMotor.class, "front right");
//        viper = hardwareMap.get(DcMotor.class, "viper");
//        arm = hardwareMap.get(DcMotor.class, "arm");
//        rotate = hardwareMap.get(Servo.class, "twist");
//        intake = hardwareMap.get(CRServo.class, "intake");
//        color = hardwareMap.get(ColorSensor.class, "color");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);


        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
//

//        forward(10);
//        frontLeft.setPower(0.3);
//        backLeft.setPower(0.3);
//        frontRight.setPower(0.3);
//        backRight.setPower(0.3);
        telemetry.addData("FL", frontLeft.getCurrentPosition());
        telemetry.addData("BL", backLeft.getCurrentPosition());
        telemetry.addData("FR", frontRight.getCurrentPosition());
        telemetry.addData("BR", backRight.getCurrentPosition());
    }
}
