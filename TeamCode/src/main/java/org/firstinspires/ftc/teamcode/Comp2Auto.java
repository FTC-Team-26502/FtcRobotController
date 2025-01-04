package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class Comp2Auto  extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        //Forming the Trajectorys(Paths the robot moves on)
        Trajectory driveToBasket = drive.trajectoryBuilder(new Pose2d(), Math.toRadians(90))
                .splineTo(new Vector2d(52, 56), Math.toRadians(45))
                .build();
        Trajectory turnToSample1 = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(52, 56), Math.toRadians(0))
                .build();
        Trajectory turnToBacket = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(52, 56), Math.toRadians(90))
                .build();
        Trajectory turnToSample2 = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(52, 56), Math.toRadians(105))
                .build();
        Trajectory turnToSample3 = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(52, 56), Math.toRadians(75))
                .build();
        Trajectory park = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(17, 10), Math.toRadians(75))
                .build();
        waitForStart();

        if(isStopRequested()) return;
        drive.followTrajectory(driveToBasket);
        //drop preloaded sample
        drive.followTrajectory(turnToSample1);
        //extend the slide and grab sample
        //sample transfer
        drive.followTrajectory(turnToBacket);
        //drop sample
        drive.followTrajectory(turnToSample2);
        //extend the slide and grab sample
        //sample transfer
        drive.followTrajectory(turnToBacket);
        //drop sample
        drive.followTrajectory(turnToSample3);
        //extend the slide and grab sample
        //sample transfer
        drive.followTrajectory(park);
        // raise arm and claw to touch bar
    }
}
