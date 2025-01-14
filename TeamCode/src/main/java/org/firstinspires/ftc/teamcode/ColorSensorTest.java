package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
public class ColorSensorTest extends OpMode{
    protected Servo rightLight;
    protected Servo leftLight;
    protected ColorSensor whatColor;
    @Override
    public void init() {
        leftLight = hardwareMap.get(Servo.class, "lights left");
        rightLight = hardwareMap.get(Servo.class, "lights right");
        whatColor = hardwareMap.get(ColorSensor.class, "color sensor");
    }

    @Override
    public void loop() {

        final double YELLOW = 0.388;
        final double BLUE = 0.611;
        final double RED = 0.279;
        final double SAGE = 0.7;
        if (whatColor.red() > whatColor.blue() && whatColor.red() > whatColor.green()) {
            leftLight.setPosition(RED);
            rightLight.setPosition(RED);
        } else if (whatColor.blue() > whatColor.green() && whatColor.blue() > whatColor.red()) {
            leftLight.setPosition(BLUE);
            rightLight.setPosition(BLUE);
        } else if (whatColor.blue() < whatColor.green() && whatColor.blue() < whatColor.red() && whatColor.green()>whatColor.red()) {
            leftLight.setPosition(YELLOW);
            rightLight.setPosition(YELLOW);
            telemetry.addData("YELLOW", "IS HERE");
        } else{
            leftLight.setPosition(SAGE);
            rightLight.setPosition(SAGE);
        }
        telemetry.update();


    }
}
