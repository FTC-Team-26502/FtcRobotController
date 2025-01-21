package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name="Comp2", group="Comp2TeleOp")
public abstract class Comp2Specimens extends BRBLinearOpMode {

    protected void specimenDrop() {
        topArm.setPosition(SPECIMEN_GRAB);
        sleep(700);
        topClaw.setPosition(TOP_CLAW_OPEN);
    }

    protected void hangSpecimen() {
        topClaw.setPosition(TOP_CLAW_CLOSE);
        sleep(700);
        topArm.setPosition(FRONT_POSITION);
        sleep(300);
        topWrist.setPosition(WRIST_HANG_POSITION);
        sleep(300);
        verticalBottom = MIDDLE_VERTICAL_POSITION;
    }

    protected void resetToInitial() {
        topClaw.setPosition(TOP_CLAW_OPEN);
        sleep(300);
        topWrist.setPosition(WRIST_START_POSITION_TOP);
        sleep(300);
        topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
        sleep(500);
        verticalBottom = BOTTOM_VERTICAL_POSITION;
    }

}

 