package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class firstCompTele extends OpMode {
    protected DcMotor backLeft;
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backRight;
    protected DcMotor viper;
    protected DcMotor arm;
    protected Servo rotate;
    protected CRServo intake;


    @Override
    public void init() {
        backLeft = hardwareMap.get(DcMotor.class, "back left");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        frontRight = hardwareMap.get(DcMotor.class, "back right");
        backRight = hardwareMap.get(DcMotor.class, "front right");
        viper = hardwareMap.get(DcMotor.class, "arm");
        arm = hardwareMap.get(DcMotor.class, "viper");
        rotate = hardwareMap.get(Servo.class, "twist");
        intake = hardwareMap.get(CRServo.class, "intake");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        viper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        viper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }  public void loop() {
        double frontLeftPower = (gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x)/2;
        double backLeftPower = (gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x)/2;
        double frontRightPower = (gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x)/2;
        double backRightPower = (gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x)/2   ;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);


        double armmovment =gamepad1.right_stick_y/15;
        arm.setPower(armmovment);

        double vipermovements = (gamepad1.left_trigger - gamepad1.right_trigger)/10;
        viper.setPower(vipermovements);


        // servo move
        if(gamepad1.y) {
            // move to 0 degrees. nnn
            rotate.setPosition(0);
        } else if (gamepad1.x) {
            // move to 90 degrees.
            rotate.setPosition(0.8);
        } else if (gamepad1.a) {
            // move to 180 degrees.
            rotate.setPosition(1);
        }

        if(gamepad1.dpad_left) {
            // move to 0 degrees.
            intake.setPower(0.5);
        }
        else if(gamepad1.dpad_right) {
            // move to 0 degrees.
            intake.setPower(-0.5);
        }
        telemetry.addData("Servo Position", rotate.getPosition());
        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}

