package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class firstCompTele extends OpMode {
    protected DcMotor backLeft;
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backRight;
    protected DcMotor side;
    protected DcMotor up;
    protected Servo claw;
    double upSpeed;
    int topPosition;
    int bottomPosition;

    double sideSpeed;
    int endPosition;
    int startPosition;
    double curentPose = 0.5;
//    protected DcMotor arm;
////    protected Servo rotate;
//    protected CRServo intake;
//    protected ColorSensor color;


    @Override
    public void init() {
//        backLeft = hardwareMap.get(DcMotor.class, "backleft");
//        frontLeft = hardwareMap.get(DcMotor.class, "front left");
//        frontRight = hardwareMap.get(DcMotor.class, "back right");
//        backRight = hardwareMap.get(DcMotor.class, "front right");
        side = hardwareMap.get(DcMotor.class, "horizontal");
        up = hardwareMap.get(DcMotor.class, "vertical");
        claw = hardwareMap.get(Servo.class, "claw");
////        viper = hardwareMap.get(DcMotor.class, "viper");
////        arm = hardwareMap.get(DcMotor.class, "arm");
////        rotate = hardwareMap.get(Servo.class, "twist");
////        intake = hardwareMap.get(CRServo.class, "intake");
////        color = hardwareMap.get(ColorSensor.class, "color");
//        frontLeft.setDirection(DcMotor.Direction.REVERSE);
//        backLeft.setDirection(DcMotor.Direction.REVERSE);
//        frontRight.setDirection(DcMotor.Direction.FORWARD);
//        backRight.setDirection(DcMotor.Direction.FORWARD);
//
//        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
////        viper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
////        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
////
////        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
////        viper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        side.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        side.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        side.setTargetPosition(0);
        side.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        up.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        up.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        up.setTargetPosition(0);
//        up.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        up.setPower(0.6);
//        side.setPower(0.6);
        endPosition = 1;
        startPosition = 0;
    }  public void loop() {
//        double frontLeftPower = (-gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x)/2;
//        double backLeftPower = (-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x)/2;
//        double frontRightPower = (-gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x)/2;
//        double backRightPower = (-gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x)/2   ;
//
//        frontLeft.setPower(frontLeftPower);
//        backLeft.setPower(backLeftPower);
//        frontRight.setPower(frontRightPower);
//        backRight.setPower(backRightPower);

        if (gamepad1.a){
            side.setTargetPosition(startPosition);
        }else if (gamepad1.b){
            side.setTargetPosition(endPosition);
        }
//        if (gamepad1.x){
//            up.setTargetPosition(0);
//        }else if (gamepad1.y){
//            up.setTargetPosition(4300);
//        }
//        if (gamepad1.left_bumper){
//            claw.setPosition(0.75);
//        }else if (gamepad1.right_bumper){
//            claw.setPosition(1);
//        }
//        if (gamepad1.x){
//            up.setTargetPosition(0);
//        }else if (gamepad1.y){
//            up.setTargetPosition(4300);
//        }
//        while (up.getCurrentPosition()>0 && up.getCurrentPosition()<200){
//            double upPower= gamepad1.right_stick_y;
//            up.setPower(upPower);
//        }
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
        telemetry.addData("Servo", claw.getPosition());
        telemetry.addData("side", side.getCurrentPosition());
        telemetry.update();
    }
}

