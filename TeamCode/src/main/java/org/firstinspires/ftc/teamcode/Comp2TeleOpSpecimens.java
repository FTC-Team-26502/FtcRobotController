package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
public abstract class Comp2TeleOpSpecimens extends Comp2Specimens {

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
           driveControls(motorVerticalSlide.getCurrentPosition()>BOTTOM_VERTICAL_POSITION+100,
                   currentState != State.VIPER_READY_TO_HANG);
            // moving horizontal slide using the left joystick
            horizontalControls();
            //moving the viper slide up to different positions
            motorVerticalController();
            telemetry.addData("What color", whatColor);

            // state transitions
            telemetry.addData("Current State ", getStateName() );
            if (currentState == Comp2TeleOpSpecimens.State.INITIAL) {
                if(gamepad2.left_trigger>0){ // no transfer, reset positions
                    //        readyForTransfer = false;
                    getReadyToGrab();
                    currentState = Comp2TeleOpSpecimens.State.CLAW_READY_TO_GRAB;
                }
            } else if (currentState == Comp2TeleOpSpecimens.State.CLAW_READY_TO_GRAB) {
                if(gamepad2.right_trigger>0){
                    grab();
                    currentState = Comp2TeleOpSpecimens.State.GRAB_AND_RETRACT;
                } else {
                    if (gamepad2.b) {
                        intakeWrist.setPosition(WRIST_START_POSITION - 0.3);
                    }
                    if (gamepad2.a) {
                        intakeWrist.setPosition(WRIST_START_POSITION);
                    }
                    if (gamepad2.x) {
                        intakeWrist.setPosition(gamepad2.left_stick_y);
                    }
                }
            } else if (currentState == Comp2TeleOpSpecimens.State.GRAB_AND_RETRACT){
                if(!motorHorizontalSlide.isBusy() && gamepad2.y) {
                    transfer();
                    currentState = Comp2TeleOpSpecimens.State.TRANSFER_TO_TOP_CLAW;
                } else if(gamepad2.left_trigger>0){ // no transfer, reset positions
                    resetTransfer();
                    currentState = Comp2TeleOpSpecimens.State.CLAW_READY_TO_GRAB;
                }
            } else if (currentState == Comp2TeleOpSpecimens.State.TRANSFER_TO_TOP_CLAW){
                if(gamepad2.left_bumper){
                    specimenDrop();
                    currentState = Comp2TeleOpSpecimens.State.DROP_SAMPLE;
                } else if(gamepad2.left_trigger>0){ // no transfer, reset positions
                    intakeArm.setPosition(ARM_READY_TO_GRAB);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
                    topClaw.setPosition(TOP_CLAW_OPEN);
                    currentState = Comp2TeleOpSpecimens.State.CLAW_READY_TO_GRAB;
                }
            } else if (currentState == Comp2TeleOpSpecimens.State.DROP_SAMPLE){
                if(gamepad2.right_bumper){
                    hangSpecimen();
                    currentState = State.VIPER_READY_TO_HANG;
                } else if(gamepad2.left_trigger>0){ // no transfer, reset positions
                    //        readyForTransfer = false;
                    intakeArm.setPosition(ARM_READY_TO_GRAB);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    currentState = Comp2TeleOpSpecimens.State.CLAW_READY_TO_GRAB;
                    topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
                    topClaw.setPosition(TOP_CLAW_OPEN);
                    verticalBottom = BOTTOM_VERTICAL_POSITION;
                }
            } else  if (currentState == State.VIPER_READY_TO_HANG){
                if(gamepad2.a){
                    //reset
                    resetToInitial();
                    currentState = State.INITIAL;
                }
                if(gamepad2.left_bumper){
                    // grab failed
                    specimenDrop();
                    currentState = Comp2TeleOpSpecimens.State.DROP_SAMPLE;
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
