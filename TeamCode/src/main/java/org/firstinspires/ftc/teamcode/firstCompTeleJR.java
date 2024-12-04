package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp
public class firstCompTeleJR extends OpMode {
    protected DcMotor backLeft;
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backRight;
//    protected DcMotor viper;
//    protected DcMotor arm;
////    protected Servo rotate;
//    protected CRServo intake;
//    protected ColorSensor color;


    @Override
    public void init() {
        backLeft = hardwareMap.get(DcMotor.class, "backleft");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        frontRight = hardwareMap.get(DcMotor.class, "back right");
        backRight = hardwareMap.get(DcMotor.class, "front right");
//        viper = hardwareMap.get(DcMotor.class, "viper");
//        arm = hardwareMap.get(DcMotor.class, "arm");
//        rotate = hardwareMap.get(Servo.class, "twist");
//        intake = hardwareMap.get(CRServo.class, "intake");
//        color = hardwareMap.get(ColorSensor.class, "color");
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        viper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        viper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }  public void loop() {
        double frontLeftPower = (gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x)/2;
        double backLeftPower = (gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x)/2;
        double frontRightPower = (gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x)/2;
        double backRightPower = (gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x)/2   ;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);


//        double armmovment =-gamepad1.right_stick_y/15;
//        arm.setPower(armmovment);
//
//        double vipermovements = (gamepad1.left_trigger - gamepad1.right_trigger)/10;
//        viper.setPower(vipermovements);


        // servo move



//        if (color.blue() > 1000 && color.blue()> color.green() && color.red()<color.blue()){
//            rotate.setPosition(0);
//        }else if (color.red() > 1000 && color.red()> color.green() && color.red()>color.blue()) {
//            rotate.setPosition(1);
//        }else if (color.red() > 1000 && color.green()> 1000 && color.red()>color.blue()) {
//            rotate.setPosition(0.5);
//        }
//        telemetry.addData("red", color.red());
//        telemetry.addData("green", color.green());
//        telemetry.addData("blue", color.blue());
//        telemetry.addData("Servo Position", rotate.getPosition());
        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}

