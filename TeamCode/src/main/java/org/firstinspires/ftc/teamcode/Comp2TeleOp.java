package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;


@TeleOp(name="Comp2", group="Comp2TeleOp")
public abstract class Comp2TeleOp extends BRBLinearOpMode {

    protected void loopOpMode() {
        waitForStart();
        // @TODO
        while ( !isStopRequested() ) {
            // move the robot
            driveTrain.setWeightedDrivePower(
                    new Pose2d(
                            gamepad1.left_stick_y * DRIVE_MOTOR_SPEED,

                            gamepad1.left_stick_x * DRIVE_MOTOR_SPEED,

                            -gamepad1.right_stick_x * DRIVE_MOTOR_SPEED
                    )
            );
            // check horizontal slide commands
            String whatColor = detectColor();
            telemetry.addData("Color Detected", whatColor);
            telemetry.update();
            if ( whatColor == "yellow" )
//        if(red>blue && green>red && alpha>blue && red<200){
//            whatColor= "yellow";
//            intakClaw.setPosition(0.0);
//        }else if(red<blue && green<blue && alpha<blue){
//            whatColor = "blue";
//            if ( redAlliance ) {
//                intakClaw.setPosition(0.0);
//            } else {
//                intakClaw.setPosition(1.0);
//            }
//        }else if(red>blue && green<red && alpha<red){
//            whatColor = "red";
//            if ( redAlliance ) {
//                intakClaw.setPosition(1.0);
//            } else {
//                intakClaw.setPosition(0.8);
//            }
//        }else{
//            whatColor = "NONE";
            if(gamepad2.a){
                intakClaw.setPosition(INTAKE_CLAW_OPEN);
            }if(gamepad2.b){
                intakClaw.setPosition(INTAKE_CLAW_CLOSED);
            }


            if(gamepad2.left_trigger>0){
                readyForTransfer = false;
                intakeArm.setPosition(ARM_READY_TO_GRAB);
                intakClaw.setPosition(INTAKE_CLAW_OPEN);
            }else if(gamepad2.right_trigger>0){
                intakeArm.setPosition(ARM_GRAB);
                sleep(300);
                intakClaw.setPosition(INTAKE_CLAW_CLOSED);
                intakeArm.setPosition(INSIDE_ROBOT_CLAW_HORIZONTAL);
                sleep(700);
                horizontalSlideLocation = HORIZONTAL_SLIDE_IN_LIMIT;
                readyForTransfer = true;
            }
//        if(horizontalSlideLocation == HORIZONTAL_SLIDE_IN_LIMIT){
//            readyForTransfer = true;
//        }
            if (gamepad2.left_stick_x > 0 && horizontalSlideLocation > HORIZONTAL_SLIDE_OUT_LIMIT) {
                horizontalSlideLocation -= gamepad2.left_stick_x * HORIZONTAL_JOYSTICK_MULTIPLIER;
                readyForTransfer = false;
            } else if (gamepad2.left_stick_x < 0 && horizontalSlideLocation < HORIZONTAL_SLIDE_IN_LIMIT) {
                horizontalSlideLocation -= gamepad2.left_stick_x * HORIZONTAL_JOYSTICK_MULTIPLIER;
                readyForTransfer = false;
            }
            motorHorizontalSlide.setTargetPosition(horizontalSlideLocation);

            // Display the color values on the telemetry
            telemetry.addData("Red", red);
            telemetry.addData("Blue", blue);
            telemetry.addData("Alpha", alpha);
            telemetry.addData("What Color:", whatColor);
            telemetry.addData("Motor position", motorHorizontalSlide.getCurrentPosition());
            telemetry.addData("Servo claw", intakClaw.getPosition());
            telemetry.addData("Joy Stick:", gamepad2.right_stick_x);
            telemetry.addData("Ready for transfer", readyForTransfer);

        }
    }

    private void runHorizontalSlide() {

        telemetry.update();
    }


    private void runVerticalSlide() {
        //moving the viper slide up to different positions
        if (gamepad2.a){
            verticalCurrentPosition = BOTTOM_VERTICAL_POSITION;
        } else if (gamepad2.b) {
            verticalCurrentPosition = TOP_VERTICAL_POSITION;
        }else if (gamepad2.x) {
            verticalCurrentPosition = MIDDLE_VERTICAL_POSITION;
        } else if (gamepad2.right_stick_y > 0 && verticalCurrentPosition < TOP_VERTICAL_POSITION) {
            verticalCurrentPosition -= gamepad2.right_stick_y * VERTICAL_JOYSTICK_MULTIPLIER;
        } else if (gamepad2.right_stick_y < 0 && verticalCurrentPosition > BOTTOM_VERTICAL_POSITION) {
            verticalCurrentPosition -= gamepad2.right_stick_y * VERTICAL_JOYSTICK_MULTIPLIER;
        }
        motorVerticalSlide.setTargetPosition(verticalCurrentPosition);
        if(readyForTransfer && !motorHorizontalSlide.isBusy() && gamepad2.y) {
            topClaw.setPosition(TOP_CLAW_CLOSE);
            sleep(700);
            intakClaw.setPosition(INTAKE_CLAW_OPEN);
            sleep(500);
            topArm.setPosition(DROPPING_POSITION);
        }
        if (gamepad2.left_bumper){
            topClaw.setPosition(TOP_CLAW_OPEN);
            sleep(300);
            telemetry.addData("hi i am being told to move", "by dorina");
            topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
        }
        telemetry.addData("motor position", motorVerticalSlide.getCurrentPosition());


    }







}
 