package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class firstCompTeleQueens extends OpMode {
    //Declaring all the motors
    protected DcMotor backLeft;
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backRight;
    protected DcMotor sideways;
    protected DcMotor up;
    //making the position for linear and vertical extention
    int end = -300;
    int start = 300;
    int top = 4000;
    int bottom = 0;
    int middle = 1200;
    int low = 700;

    @Override
    public void init() {
        backLeft = hardwareMap.get(DcMotor.class, "backleft");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        frontRight = hardwareMap.get(DcMotor.class, "back right");
        backRight = hardwareMap.get(DcMotor.class, "front right");

        //Setting drive motors settings
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Setting the extenders settings
        sideways = hardwareMap.get(DcMotor.class, "backleft");
        sideways.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sideways.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sideways.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sideways.setPower(0.2);
        up = hardwareMap.get(DcMotor.class, "vertical");
        up.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        up.setTargetPosition(0);
        up.setPower(0.3);
        up.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        up.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }  public void loop() {
        // calculating the power for each wheel with controler imput
        double frontLeftPower = (gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x)/1.5;
        double backLeftPower = (gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x)/1.5;
        double frontRightPower = (gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x)/1.5;
        double backRightPower = (gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x)/1.5   ;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

        //Moving the linear exteder with the joystick
        if (gamepad2.right_bumper){
            sideways.setTargetPosition(0);
        }else if(gamepad2.left_bumper){
            sideways.setTargetPosition(-200);
        }

        //moving the viper slide up to different positions
        if (gamepad1.a){
            up.setTargetPosition(bottom);
        } else if (gamepad1.b) {
            up.setTargetPosition(top);
        }else if (gamepad1.x) {
            up.setTargetPosition(middle);
        }else if (gamepad1.y) {
            up.setTargetPosition(low);
        }


        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}

