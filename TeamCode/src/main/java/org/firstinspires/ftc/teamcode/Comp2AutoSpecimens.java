package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public abstract class Comp2AutoSpecimens extends Comp2Specimens{
    
    public void loopOpMode() throws InterruptedException {
         //Build Trajectories:
        Pose2d startPose = getPose2d(15, -62, Math.toRadians(90));
        driveTrain.setPoseEstimate(startPose);

        TrajectorySequence driveToBarStart = driveTrain.trajectorySequenceBuilder(startPose)
                .splineToConstantHeading(getVector2d(0, -36), getHeading(Math.toRadians(90)))
                .splineToConstantHeading(getVector2d(-56, 13), getHeading(Math.toRadians(90)))
                .splineToConstantHeading(getVector2d(-50, 13), getHeading(Math.toRadians(90)))
                .build();

        TrajectorySequence pushSamples = driveTrain.trajectorySequenceBuilder(
                // TODO change this later if we do not drive to bar
                driveToBarStart.end())
                .splineTo(new Vector2d(0, -36), Math.toRadians(90))
                .splineTo(new Vector2d(-56, 13), Math.toRadians(90))
                .forward(6)
                .forward(-10)
                .splineTo(new Vector2d(37, -31), Math.toRadians(90))
                .splineTo(new Vector2d(36,-10), Math.toRadians(90))
                .strafeTo(new Vector2d(48,-10))
                .strafeTo(new Vector2d(48,-54))
                .strafeTo(new Vector2d(48,-10))
                .strafeTo(new Vector2d(57,-10))
                .strafeTo(new Vector2d(57,-54))
                .strafeTo(new Vector2d(59,-10))
                .strafeTo(new Vector2d(62,-10))
                .forward(45)
                .strafeTo(new Vector2d(47,-55))
                .forward(5)
                .forward(-20)
                .splineTo(new Vector2d(0, -56), Math.toRadians(270))
                .forward(26)
                .strafeTo(new Vector2d(47,-55))
                .forward(-6)
                .forward(6)
                .strafeTo(new Vector2d(0, -30))
                .strafeTo(new Vector2d(47,-55))
                .forward(-6)
                .forward(6)
                .forward(-6)
                .forward(6)
                .strafeTo(new Vector2d(0, -30))
                .strafeTo(new Vector2d(47,-55))
                .forward(-6).build()
    );
        waitForStart();
        driveTrain.followTrajectorySequence(driveToBarStart);
        // TODO hang specimen
        driveTrain.followTrajectorySequence(pushSamples);
        sleep(3000);
        driveTrain.followTrajectory(part2);

    }
}
