package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public abstract class Comp2TeleOpSamples extends Comp2TeleOp {

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
            driveControlls(currentState == State.READY_TO_DROP,true);
            horizontalControls();
            motorVerticalController();
            telemetry.addData("Current State ", getStateName() );
            // state transitions
            if (currentState == State.INITIAL) {
                if(gamepad2.left_trigger>0){
                    intakeArm.setPosition(ARM_READY_TO_GRAB);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    currentState = State.CLAW_READY_TO_GRAB;
                }
            }
            if (currentState == State.CLAW_READY_TO_GRAB) {
                if(gamepad2.right_trigger>0){
                    // both samples and specimens
                    // positions intake arm ready to grab
                    intakeArm.setPosition(ARM_GRAB);
                    sleep(300);
                    // closes the intake claw
                    String whatColor = detectColor();
                    if ( whatColor == YELLOW_COLOR
                            || (whatColor == BLUE_COLOR && !redAlliance)
                            || (whatColor == RED_COLOR && redAlliance)
                    ) {
                        closeIntakeClaw();
                    } else {
                        openIntakeClaw();
                    }
                    // position intake claw inside robot
                    intakeArm.setPosition(INSIDE_ROBOT_CLAW_HORIZONTAL);
                    sleep(700);
                    // takes the whole arm in
                    horizontalSlideLocation = HORIZONTAL_SLIDE_IN_LIMIT;
                    currentState = State.GRAB_AND_RETRACT;
                    saveFrame();
                }
            }
            if (currentState == State.GRAB_AND_RETRACT){
                if(!motorHorizontalSlide.isBusy() && gamepad2.y) {
                    // only samples
//                    readyForTransfer = false;
                    topClaw.setPosition(TOP_CLAW_CLOSE);
                    sleep(700);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    sleep(500);
                    topArm.setPosition(UP_POSITION);

                    currentState = State.TRANSFER_TO_TOP;
                }
                if(gamepad2.left_trigger>0){
                    intakeArm.setPosition(ARM_READY_TO_GRAB);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    currentState = State.CLAW_READY_TO_GRAB;
                }
            }
            if (currentState == State.TRANSFER_TO_TOP){
                if(gamepad2.left_bumper){
                    viperSlideUp();
                    sleep(3000);
                    topArm.setPosition(DROPPING_POSITION);
                    currentState = State.READY_TO_DROP;
                }
                if(gamepad2.left_trigger>0){
                    intakeArm.setPosition(ARM_READY_TO_GRAB);
                    intakeClaw.setPosition(INTAKE_CLAW_OPEN);
                    currentState = State.CLAW_READY_TO_GRAB;
                }
            }
            if (currentState == State.READY_TO_DROP){
                if(gamepad2.right_bumper){
                    topClaw.setPosition(TOP_CLAW_OPEN);
                    sleep(300);
                    topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
                    sleep(300);
                    viperSlideDown();
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
