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
        final double SAGE = 0.48;
        double red = whatColor.red();
        double blue = whatColor.blue();
        double green = whatColor.green();
        if (blue > green && blue > 2*red) {
            leftLight.setPosition(BLUE);
            rightLight.setPosition(BLUE);
        } else if (red > 1.3*blue && red > green){
            leftLight.setPosition(RED);
            rightLight.setPosition(RED);
        } else if (red>1.3*blue && green>1.3*blue) {
            leftLight.setPosition(YELLOW);
            rightLight.setPosition(YELLOW);
            telemetry.addData("YELLOW", "IS HERE");
        } else{
            leftLight.setPosition(SAGE);
            rightLight.setPosition(SAGE);
        }
        telemetry.addData("bLue", whatColor.blue());
        telemetry.addData("green", whatColor.green());
        telemetry.addData("red", whatColor.red());
        telemetry.update();


    }
}
