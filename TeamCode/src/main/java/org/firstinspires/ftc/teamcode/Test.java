package org.firstinspires.ftc.teamcode;

import android.icu.text.Transliterator;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import android.icu.text.Transliterator;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;



@TeleOp
public class Test extends OpMode {



    //Declare Objects
    Servo servoRight;
    Servo servoLeft;
    double position = 0;
    double closeright;
    double closeleft;
    @Override
    public void init() {
        //Set up objects(DC Motors)

        servoRight = hardwareMap.get(Servo.class, "claw right");
        servoLeft = hardwareMap.get(Servo.class, "claw left");
        closeright = servoRight.getPosition();
        closeleft = servoLeft.getPosition();
    }

    public void loop() {
            // Control the servo with gamepad buttons
            if (gamepad1.a) {
               servoRight.setPosition(closeright);
               servoLeft.setPosition(closeleft);
            } else if (gamepad1.x) {
                // Move servo to position 1.0
               servoLeft.setPosition(1.0);
               servoRight.setPosition(1.0);
            }

        }
}
