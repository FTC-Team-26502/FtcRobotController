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
    protected DcMotor up;
    protected Servo claw;
    protected Servo claw2;
    protected ColorSensor color;
    protected TouchSensor touch;
    boolean pickupcolor;

    @Override
    public void init() {

//        claw = hardwareMap.get(Servo.class, "light");
//        color = hardwareMap.get(ColorSensor.class,"color");
        up = hardwareMap.get(DcMotor.class, "vertical");
    }

    @Override
    public void loop() {
//        int red = color.red();
//        int green = color.green();
//        int blue = color.blue();
//        int alpha = color.alpha();
//
//        // Display the color values on the telemetry
//        telemetry.addData("Red", red);
//        telemetry.addData("Green", green);
//        telemetry.addData("Blue", blue);
//        telemetry.addData("Alpha", alpha);
//        telemetry.update();
//        if(red>green && red>blue && red>alpha){
//            claw.setPosition(0.28);
//        }else if(blue>green && red<blue && blue>alpha) {
//            claw.setPosition(0.6);
//        }else{
//            if(green>red && red>alpha && alpha>blue) {
//                claw.setPosition(0.335);
//            }else{
//                claw.setPosition(0.9);
//            }
//
//        }
//        telemetry.addData("Red", red);
//        telemetry.addData("Green", green);
//        telemetry.addData("Blue", blue);
//        telemetry.addData("Alpha", alpha);
        telemetry.addData("motor", up.getCurrentPosition());
//        telemetry.addData("Light", claw.getPosition());
        telemetry.update();
//        }else{
//            claw.setPosition(0.1);
//        }
//        if(blue<green && red<green && green>alpha){
//            claw.setPosition(0.335);
//        }else{
//            claw.setPosition(0.1);
//        }
//        double red = 0.28;
//        double yellow = 0.335;
//        double blue = 0.6;
//        claw.setPosition(0.71);
    }
}