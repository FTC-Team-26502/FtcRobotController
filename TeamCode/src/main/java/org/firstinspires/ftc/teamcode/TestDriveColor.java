package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


@TeleOp
public class TestDriveColor extends OpMode {
    protected DcMotor backLeft;
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backRight;
    protected DcMotor viper;
    protected DcMotor arm;
    protected Servo claw;
    protected Servo claw2;
    protected ColorSensor color;
    protected TouchSensor touch;
    boolean pickupcolor;

    @Override
    public void init() {

        claw = hardwareMap.get(Servo.class, "intake");
        claw2 = hardwareMap.get(Servo.class, "claw");
        color=hardwareMap.get(ColorSensor.class, "color");
        touch = hardwareMap.get(TouchSensor.class, "touch");
    }

    @Override
    public void loop() {
        if (color.blue() > 1000 && color.blue()> color.green() && color.red()<color.blue()){
            pickupcolor = true;
        }else if (color.red() > 1000 && color.red()> color.green() && color.red()>color.blue()) {
            pickupcolor = false;
        }else if (color.red() > 1500 && color.green()> 1500 && color.red()>color.blue()) {
            pickupcolor = true;
        }

        if(pickupcolor){

            if(gamepad1.dpad_left) {
                // move to 0 degrees.
                claw.setPosition(0.5);
                claw2.setPosition(-0.5);
            }
            else if(gamepad1.dpad_right) {
                // move to 0 degrees.
                claw.setPosition(-0.5);
                claw2.setPosition(0.5);
            }
        }else{
            telemetry.addData(")", "STOP WRONG COLOR");
        }

        telemetry.addData("red", color.red());
        telemetry.addData("green", color.green());
        telemetry.addData("blue", color.blue());
        telemetry.addData("Pickup", pickupcolor);
        telemetry.addData("touch", touch.isPressed());
    }
}