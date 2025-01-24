package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name="Comp2", group="Comp2TeleOp")
public abstract class Comp2Specimens extends BRBLinearOpMode {
    protected void grab() {
        String whatColor = detectColor();
        // both samples and specimens
        // positions intake arm ready to grab
        intakeArm.setPosition(ARM_GRAB);
        // closes the intake claw
        if ( (whatColor == BLUE_COLOR && !redAlliance)
                || (whatColor == RED_COLOR && redAlliance)
        ) {
            closeIntakeClaw();
        } else {
            openIntakeClaw();
        }
        sleep(300);
        leftLight.setPosition(1);
        leftLight.setPosition(0);
        intakeWrist.setPosition(WRIST_START_POSITION);
        // position intake claw inside robot
        intakeArm.setPosition(INSIDE_ROBOT_CLAW_HORIZONTAL);
        topArm.setPosition(FRONT_POSITION);
        // takes the whole arm in
        horizontalSlideLocation = HORIZONTAL_SLIDE_IN_LIMIT;
    }

    protected void specimenDrop() {
        topArm.setPosition(SPECIMEN_GRAB);
        sleep(500);
        topClaw.setPosition(TOP_CLAW_OPEN);
    }

    protected void hangSpecimen() {
        topClaw.setPosition(TOP_CLAW_CLOSE);
        sleep(1000);
        topArm.setPosition(FRONT_POSITION);
        sleep(300);
        topWrist.setPosition(WRIST_HANG_POSITION);
        sleep(800);
        topClaw.setPosition(TOP_CLAW_ALMOST_CLOSE);
        sleep(300);
        topClaw.setPosition(TOP_CLAW_CLOSE);
        verticalBottom = MIDDLE_VERTICAL_POSITION;
    }

    protected void resetToInitial() {
        topClaw.setPosition(TOP_CLAW_OPEN);
        sleep(700);
        topWrist.setPosition(WRIST_START_POSITION_TOP);
        sleep(300);
        topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
        sleep(500);
        verticalBottom = BOTTOM_VERTICAL_POSITION;
    }

}

 