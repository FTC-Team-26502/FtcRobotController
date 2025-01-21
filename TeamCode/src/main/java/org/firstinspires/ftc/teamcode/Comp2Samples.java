package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
public abstract class Comp2Samples extends BRBLinearOpMode{
    protected void readyToDropSamples() {
        motorVerticalSlide.setTargetPosition(TOP_VERTICAL_POSITION);
        sleep(3000);
        topArm.setPosition(DROPPING_POSITION);
    }

    protected void dropAndReturn() {
        topClaw.setPosition(TOP_CLAW_OPEN);
        sleep(300);
        topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
        sleep(300);
        motorVerticalSlide.setTargetPosition(BOTTOM_VERTICAL_POSITION);
    }
}
