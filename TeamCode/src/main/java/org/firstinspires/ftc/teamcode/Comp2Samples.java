package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
public abstract class Comp2Samples extends BRBLinearOpMode{
    protected void grab() {
        String whatColor = detectColor();
        // both samples and specimens
        // positions intake arm ready to grab
        intakeArm.setPosition(ARM_GRAB);
        // closes the intake claw
        if ( (whatColor == BLUE_COLOR && !redAlliance)
                || (whatColor == RED_COLOR && redAlliance) || (whatColor == YELLOW_COLOR)
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

    protected void readyToDropSamples() {
        motorVerticalSlide.setTargetPosition(TOP_VERTICAL_POSITION);
        sleep(3000);
        topArm.setPosition(BASKET_DROP);
    }

    protected void dropAndReturn() {
        topClaw.setPosition(TOP_CLAW_OPEN);
        sleep(300);
        topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
        sleep(300);
        motorVerticalSlide.setTargetPosition(BOTTOM_VERTICAL_POSITION);
    }
}
