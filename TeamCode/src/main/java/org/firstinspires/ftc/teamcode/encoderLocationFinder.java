package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class encoderLocationFinder extends OpMode {
    protected DcMotor sideways;
    protected DcMotor up;
    protected DcMotor leftEncoder;
    protected DcMotor rightEncoder;
    protected DcMotor sidewaysEncoder;

    int end = -300;
    int start = 300;
    int top = 4000;
    int bottom = 0;
    int middle = 1200;
    int low = 700;



    @Override
    public void init() {
        sideways = hardwareMap.get(DcMotor.class, "horizontalExtender");
        leftEncoder = hardwareMap.get(DcMotor.class, "leftFront");
        rightEncoder = hardwareMap.get(DcMotor.class, "rightFront");
        sidewaysEncoder = hardwareMap.get(DcMotor.class, "leftBack");
        up = hardwareMap.get(DcMotor.class, "verticalViper");
//        sideways.setTargetPosition(0);
//        sideways.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        sideways.setPower(0.9);
//        sideways.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        up = hardwareMap.get(DcMotor.class, "vertical");
//        up.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        up.setTargetPosition(0);
//        up.setPower(0.9);
//        up.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        up.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void loop() {
        telemetry.addData("Status", "Running");
        telemetry.addData("Horizontal Position", sideways.getCurrentPosition());
        telemetry.addData("Encoder Right", rightEncoder.getCurrentPosition());
        telemetry.addData("Encoder Left", leftEncoder.getCurrentPosition());
        telemetry.addData("Encoder Sideways", sidewaysEncoder.getCurrentPosition());
        telemetry.addData("Vertical Position", up.getCurrentPosition());
        telemetry.update();
    }
}

