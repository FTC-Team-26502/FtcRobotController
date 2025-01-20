package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;


@TeleOp(name="Comp2", group="Comp2TeleOp")
public abstract class Comp2TeleOp extends BRBLinearOpMode {

//    protected void loopOpMode() {
//        waitForStart();
//        // @TODO
//        while ( true ) {
//            // move the robot
//            driveTrain.setWeightedDrivePower(
//                    new Pose2d(
//                            gamepad1.left_stick_y * DRIVE_MOTOR_SPEED,
//
//                            gamepad1.left_stick_x * DRIVE_MOTOR_SPEED,
//
//                            -gamepad1.right_stick_x * DRIVE_MOTOR_SPEED
//                    )
//            );
//
//            // manual override
//            if(gamepad2.a){ // a opens the claw
//                openIntakeClaw();
//            }
//            if(gamepad2.b){ // b closes the claw
//                closeIntakeClaw();
//            }
//            //
//            if(gamepad2.left_trigger>0){ // no transfer, reset positions
//                transferPrep();
//            } else {
//                if(gamepad2.right_trigger>0){
//                    // both samples and specimens
//                    // positions intake arm ready to grab
//                    intakeArm.setPosition(ARM_GRAB);
//                    sleep(300);
//                    // closes the intake claw
//                    intakeClaw.setPosition(INTAKE_CLAW_CLOSED);
//                    // position intake claw inside robot
//                    intakeArm.setPosition(INSIDE_ROBOT_CLAW_HORIZONTAL);
//                    sleep(700);
//                    // takes the whole arm in
//                    horizontalSlideLocation = HORIZONTAL_SLIDE_IN_LIMIT;
//                    readyForTransfer = true;
//                }
//            }
//
//            // moving horizontal slide using the left joystick
//            if (gamepad2.left_stick_x > 0 && horizontalSlideLocation > HORIZONTAL_SLIDE_OUT_LIMIT) {
//                horizontalSlideLocation -= gamepad2.left_stick_x * HORIZONTAL_JOYSTICK_MULTIPLIER;
//                readyForTransfer = false;
//            } else if (gamepad2.left_stick_x < 0 && horizontalSlideLocation < HORIZONTAL_SLIDE_IN_LIMIT) {
//                horizontalSlideLocation -= gamepad2.left_stick_x * HORIZONTAL_JOYSTICK_MULTIPLIER;
//                readyForTransfer = false;
//            }
//
//            motorHorizontalSlide.setTargetPosition(horizontalSlideLocation);
//
//            if(horizontalSlideLocation == HORIZONTAL_SLIDE_IN_LIMIT && intakeClawPosition == INTAKE_CLAW_CLOSED){
//                readyForTransfer = true;
//            }
//
//
//
//            motorVerticalSlide.setTargetPosition(verticalCurrentPosition);
//
//            if(readyForTransfer && !motorHorizontalSlide.isBusy() && gamepad2.y) {
//                // only samples
//                readyForTransfer = false;
//                if (samples){
//                    topClaw.setPosition(TOP_CLAW_CLOSE);
//                    sleep(700);
//                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
//                    sleep(500);
//                    topArm.setPosition(UP_POSITION);
//                } else{
//                    telemetry.addData("Don't grab", "sample mode active");
//                }
//            }
//            if (gamepad2.left_bumper){
//                // release sample
//                topClaw.setPosition(TOP_CLAW_OPEN);
//                sleep(300);
//                // bring claw inside
//                topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
//                sleep(300);
//                viperSlideDown();
//                readyForTransfer = true;
//            }
//            telemetry.addData("motor position", motorVerticalSlide.getCurrentPosition());
//
//            // Display the color values on the telemetry
//            telemetry.addData("What Color:", whatColor);
//            telemetry.addData("Motor position", motorHorizontalSlide.getCurrentPosition());
//            telemetry.addData("Servo claw", intakeClaw.getPosition());
//            telemetry.addData("Joy Stick:", gamepad2.right_stick_x);
//            telemetry.addData("Ready for transfer", readyForTransfer);
//
//        }
    }

 