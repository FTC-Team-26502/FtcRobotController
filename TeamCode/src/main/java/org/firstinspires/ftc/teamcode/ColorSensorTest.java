package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ColorSensorTest extends OpMode{
    protected Servo claw;
    protected Servo spinner;
    protected ColorSensor color;
    protected String whatColor;
    @Override
    public void init() {
        claw = hardwareMap.get(Servo.class,"claw");
        spinner = hardwareMap.get(Servo.class, "spin");
        color = hardwareMap.get(ColorSensor.class, "color");
        spinner.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        int red = color.red();
        int green = color.green();
        int blue = color.blue();
        int alpha = color.alpha();

        telemetry.addData("Green", green);
        if(red>blue && green>red && alpha>blue && red<200){
            whatColor= "yellow";
            claw.setPosition(0);
        }else if(red<blue && green<blue && alpha<blue){
            whatColor = "blue";
            claw.setPosition(0);
        }else if(red>blue && green<red && alpha<red){
            whatColor = "red";
            claw.setPosition(1);
        }else{
            whatColor = "NONE";
        }

        if(gamepad1.a){
            claw.setPosition(1);
        }
        if (gamepad1.y) {
            spinner.setPosition(0.05);  // Move to position 0.05 when the 'Y' button is pressed
        } else if (gamepad1.x) {
            spinner.setPosition(0.5);  // Move to position 0.5 when the 'X' button is pressed
        }else if (gamepad1.a){
            spinner.setPosition(0.6);
        }

        // Display the color values on the telemetry
        telemetry.addData("Red", red);
        telemetry.addData("Blue", blue);
        telemetry.addData("Alpha", alpha);
        telemetry.addData("What Color:", whatColor);
        telemetry.addData("Servo position", spinner.getPosition());
        telemetry.addData("Servo claw", claw.getPosition());
        telemetry.addData("Joy Stick:" , gamepad2.left_stick_x);
        telemetry.update();


    }
}
