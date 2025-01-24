package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
public abstract class Comp2TeleOpSamples extends Comp2Samples {

    protected enum State {
        INITIAL,
        CLAW_READY_TO_GRAB,
        GRAB_AND_RETRACT,
        TRANSFER_TO_TOP,
        READY_TO_DROP
    }

    protected State currentState = State.INITIAL;

    protected void loopOpMode() {
        waitForStart();
        while (opModeIsActive()) {
            detectColor();
            driveControls(currentState == State.READY_TO_DROP,true);
            horizontalControls();
            motorVerticalController();
            telemetry.addData("Current State ", getStateName() );
            // state transitions
            if (currentState == State.INITIAL) {
                if(gamepad2.left_trigger>0){
                    getReadyToGrab();
                    currentState = State.CLAW_READY_TO_GRAB;
                }
            } else if (currentState == State.CLAW_READY_TO_GRAB) {
                if(gamepad2.right_trigger>0){
                    grab();
                    currentState = State.GRAB_AND_RETRACT;
                }
                if (gamepad2.b) {
                    intakeWrist.setPosition(WRIST_START_POSITION - 0.3);
                }
                if (gamepad2.a) {
                    intakeWrist.setPosition(WRIST_START_POSITION);
                }
                if (gamepad2.x) {
                    intakeWrist.setPosition(gamepad2.left_stick_y);
                }
            } else if (currentState == State.GRAB_AND_RETRACT){
                if(!motorHorizontalSlide.isBusy() && gamepad2.y) {
                    transfer();
                    currentState = State.TRANSFER_TO_TOP;
                }
                if(gamepad2.left_trigger>0) {
                    resetTransfer();
                    currentState = State.CLAW_READY_TO_GRAB;
                }
            } else if (currentState == State.TRANSFER_TO_TOP){
                if(gamepad2.left_bumper){
                    readyToDropSamples();
                    currentState = State.READY_TO_DROP;
                }
                if(gamepad2.left_trigger>0){
                    intakeArm.setPosition(ARM_READY_TO_GRAB);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    currentState = State.CLAW_READY_TO_GRAB;
                }
            } else if (currentState == State.READY_TO_DROP){
                if(gamepad2.right_bumper){
                    dropAndReturn();
                    currentState = State.INITIAL;
                }
            }
            telemetry.addData("Next state", getStateName());
            dumpState();
            telemetry.update();
        }
        closeDataCollection();
    }


    protected String getStateName() {
        if (currentState == State.INITIAL){
            return "Initial State";
        }
        if (currentState == State.READY_TO_DROP){
            return  "Ready to drop";
        }
        if (currentState == State.CLAW_READY_TO_GRAB){
            return  "Claw ready to grab";
        }
        if (currentState == State.GRAB_AND_RETRACT){
            return  "Grab and retract";
        }
        if (currentState == State.TRANSFER_TO_TOP){
            return  "Transfer to claw";
        }
        return "Invalid state";
    }
}
