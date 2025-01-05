package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public abstract class Comp2TeleOpSpecimens extends Comp2TeleOp{

    protected enum State {
        INITIAL,
        CLAW_READY_TO_GRAB,
        GRAB_AND_RETRACT,
        TRANSFER_TO_TOP_CLAW,
        DROP_SAMPLE,
        OBTAIN_SPECIMENS,
        VIPER_READY_TO_HANG,
    }

    protected Comp2TeleOpSpecimens.State currentState = Comp2TeleOpSpecimens.State.INITIAL;


    protected void loopOpMode() {
        waitForStart();
        while (opModeIsActive()) {
           driveControlls();
            // moving horizontal slide using the left joystick
            horizontalControls();
            //moving the viper slide up to different positions
            motorVerticalController();

            // state transitions
            telemetry.addData("Current State ", getStateName() );
            if (currentState == Comp2TeleOpSpecimens.State.INITIAL) {
                if(gamepad2.left_trigger>0){ // no transfer, reset positions
                    //        readyForTransfer = false;
                    intakeArm.setPosition(ARM_READY_TO_GRAB);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    currentState = Comp2TeleOpSpecimens.State.CLAW_READY_TO_GRAB;
                }
//                if(gamepad2.left_bumper){
//                    topArm.setPosition(SPECIMEN_GRAB);
//                    topClaw.setPosition(TOP_CLAW_OPEN);
//                    currentState = Comp2TeleOpSpecimens.State.DROP_SAMPLE;
//                }
            }
            if (currentState == Comp2TeleOpSpecimens.State.CLAW_READY_TO_GRAB) {
                if(gamepad2.right_trigger>0){
                    // both samples and specimens
                    // positions intake arm ready to grab
                    intakeArm.setPosition(ARM_GRAB);
                    sleep(300);
                    // closes the intake claw
                    String whatColor = detectColor();
                    if ( (whatColor == BLUE_COLOR && !redAlliance)
                            || (whatColor == RED_COLOR && redAlliance)
                    ) {
                        closeIntakeClaw();
                    } else {
                        openIntakeClaw();
                    }
                    intakeWrist.setPosition(WRIST_START_POSITION);
                    // position intake claw inside robot
                    intakeArm.setPosition(INSIDE_ROBOT_CLAW_HORIZONTAL);
                    sleep(700);
                    // takes the whole arm in
                    horizontalSlideLocation = HORIZONTAL_SLIDE_IN_LIMIT;
                    readyForTransfer = true;
                    currentState = Comp2TeleOpSpecimens.State.GRAB_AND_RETRACT;
                }
                if(gamepad2.left_trigger>0){ // no transfer, reset positions
                    //        readyForTransfer = false;
                    intakeArm.setPosition(ARM_READY_TO_GRAB);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    currentState = Comp2TeleOpSpecimens.State.CLAW_READY_TO_GRAB;
                }
                if(gamepad2.b){
                    intakeWrist.setPosition(WRIST_START_POSITION - 0.3);
                }
                if(gamepad2.a){
                    intakeWrist.setPosition(WRIST_START_POSITION);
                }
            }
            if (currentState == Comp2TeleOpSpecimens.State.GRAB_AND_RETRACT){
                if(!motorHorizontalSlide.isBusy() && gamepad2.y) {
                    topClaw.setPosition(TOP_CLAW_CLOSE);
                    sleep(700);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    sleep(500);
                    topArm.setPosition(DROPPING_POSITION);
                    currentState = Comp2TeleOpSpecimens.State.TRANSFER_TO_TOP_CLAW;
                }
                if(gamepad2.left_trigger>0){ // no transfer, reset positions
                    //        readyForTransfer = false;
                    intakeArm.setPosition(ARM_READY_TO_GRAB);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    currentState = Comp2TeleOpSpecimens.State.CLAW_READY_TO_GRAB;
                    topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
                    topClaw.setPosition(TOP_CLAW_OPEN);
                }
            }
            if (currentState == Comp2TeleOpSpecimens.State.TRANSFER_TO_TOP_CLAW){
                if(gamepad2.left_bumper){
                    topArm.setPosition(SPECIMEN_GRAB);
                    sleep(700);
                    topClaw.setPosition(TOP_CLAW_OPEN);
                    currentState = Comp2TeleOpSpecimens.State.DROP_SAMPLE;
                }
                if(gamepad2.left_trigger>0){ // no transfer, reset positions
                    //        readyForTransfer = false;
                    intakeArm.setPosition(ARM_READY_TO_GRAB);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    currentState = Comp2TeleOpSpecimens.State.CLAW_READY_TO_GRAB;

                }
            }
            if (currentState == Comp2TeleOpSpecimens.State.DROP_SAMPLE){
                if(gamepad2.right_bumper){
                    topClaw.setPosition(TOP_CLAW_CLOSE);
                    sleep(700);
                    topArm.setPosition(FRONT_POSITION);
                    sleep(300);
                    topWrist.setPosition(WRIST_HANG_POSITION);
                    sleep(300);
                    currentState = State.VIPER_READY_TO_HANG;
                }
                if(gamepad2.left_bumper){
                    topArm.setPosition(SPECIMEN_GRAB);
                    topClaw.setPosition(TOP_CLAW_OPEN);
                }
            }
            if (currentState == State.VIPER_READY_TO_HANG){
                if(gamepad2.a){
                    topClaw.setPosition(TOP_CLAW_OPEN);
                    sleep(300);
                    topWrist.setPosition(WRIST_START_POSITION_TOP);
                    sleep(300);
                    topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
                    sleep(500);

                    currentState = State.INITIAL;

                }
            }
            telemetry.addData("Next state", getStateName());
            dumpState();
            telemetry.update();
        }
    }



    protected String getStateName() {
        if (currentState == State.INITIAL){
            return "Initial State";
        }
        if (currentState == State.OBTAIN_SPECIMENS){
            return  "Obtain specimens";
        }
        if (currentState == State.DROP_SAMPLE){
            return  "Drop sample";
        }
        if (currentState == State.VIPER_READY_TO_HANG){
            return  "Ready to hang";
        }
        if (currentState == State.CLAW_READY_TO_GRAB){
            return  "Claw ready to grab";
        }
        if (currentState == State.GRAB_AND_RETRACT){
            return  "Grab and retract";
        }
        if (currentState == State.TRANSFER_TO_TOP_CLAW){
            return  "Transfer to claw";
        }
        return "Invalid state";
    }

}
