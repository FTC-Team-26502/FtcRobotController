package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp
public class FirstCompTele extends OpMode {
    protected DcMotor backLeft;
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backRight;

    @Override
    public void init() {
        backLeft = hardwareMap.get(DcMotor.class, "back left");
        frontLeft = hardwareMap.get(DcMotor.class, "front left");
        frontRight = hardwareMap.get(DcMotor.class, "front right");
        backRight = hardwareMap.get(DcMotor.class, "back right");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        double backLeftPower = gamepad1.left_stick_x + gamepad1.left_stick_y*15;
        double frontLeftPower = -gamepad1.left_stick_x - gamepad1.left_stick_y;
        double backRightPower = gamepad1.left_stick_x - gamepad1.left_stick_y*15;
        double frontRightPower = -gamepad1.left_stick_x +  gamepad1.left_stick_y;

        backLeft.setPower(backLeftPower);
        frontLeft.setPower(frontLeftPower);
        backRight.setPower(backRightPower);
        frontRight.setPower(frontRightPower);


    }
}
