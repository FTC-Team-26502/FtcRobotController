package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class Comp2AutoSpecimens extends Comp2Specimens{
    @Override
    public void runOpMode() throws InterruptedException {
         //Build Trajectorys:
        Pose2d startPose = new Pose2d(15, -62, Math.toRadians(90));
        driveTrain.setPoseEstimate(startPose);
        Trajectory driveToBarStart = driveTrain.trajectoryBuilder(new Pose2d(), Math.toRadians(90))
                .splineTo(new Vector2d(0, -36), Math.toRadians(90))
                .splineTo(new Vector2d(-56, 13), Math.toRadians(90))
                .forward(6)
                .build();
        Trajectory pushSamples = driveTrain.trajectoryBuilder(new Pose2d(), Math.toRadians(90))
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
                .build();
        Trajectory driveToBar = driveTrain.trajectoryBuilder(new Pose2d(), Math.toRadians(90))
                .strafeTo(new Vector2d(47,-55))
                .forward(-6)
                .build();
        waitForStart();
        driveTrain.followTrajectory(pushSamples);

    }
}
