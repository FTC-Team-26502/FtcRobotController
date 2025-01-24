package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public abstract class Comp2AutoSpecimens extends Comp2Specimens{
    
    public void loopOpMode() throws InterruptedException {
         //Build Trajectories:
        Pose2d startPose = new Pose2d(15, -62, Math.toRadians(90));
        driveTrain.setPoseEstimate(startPose);
        Trajectory driveToBarStart = driveTrain.trajectoryBuilder(new Pose2d(), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(0, -36), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-56, 13), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-50, 13), Math.toRadians(90))
                .build();
        Trajectory pushSamplesPart1 = driveTrain.trajectoryBuilder(new Pose2d(), Math.toRadians(90))
                .splineTo(new Vector2d(20, 50), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(37, 50), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(37,-10), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(37, 50), Math.toRadians(90))
//                push sample 1
//                .splineToConstantHeading(new Vector2d(57,-10), Math.toRadians(90))
//                .splineToConstantHeading(new Vector2d(57,-54), Math.toRadians(90))
//                .splineToConstantHeading(new Vector2d(59,-10), Math.toRadians(90))
//                .splineToConstantHeading(new Vector2d(62,-10), Math.toRadians(90))
////                .forward(45)
//                .splineToConstantHeading(new Vector2d(47,-55), Math.toRadians(90))
                .build();
        Trajectory part2 = driveTrain.trajectoryBuilder(new Pose2d(), Math.toRadians(90))
                .strafeTo(new Vector2d(48,-54))
                .strafeTo(new Vector2d(48,-10))
//                .splineToConstantHeading(new Vector2d(54,-10), Math.toRadians(90))
                .build();
        waitForStart();
        driveTrain.followTrajectory(pushSamplesPart1);
        sleep(3000);
        driveTrain.followTrajectory(part2);

    }
}
