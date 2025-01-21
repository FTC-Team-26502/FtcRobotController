package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;


@Disabled
@Autonomous(name = "Auto Red Samples")
public abstract class Comp2AutoSamples extends Comp2Samples {

    public void loopOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        //Forming the Trajectorys(Paths the robot moves on)
//        drive.setPoseEstimate();
        Trajectory driveToBasket = drive.trajectoryBuilder(new Pose2d(), Math.toRadians(90))
                .splineTo(new Vector2d(-52, -55), Math.toRadians(45))
                .build();
        Trajectory driveToHang = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(-24, -12), Math.toRadians(0))
                .build();

        Trajectory driveToBlue = drive.trajectoryBuilder(new Pose2d(), Math.toRadians(90))
                .splineTo(new Vector2d(52, 55), Math.toRadians(225))
                .splineTo(new Vector2d(48, 12), Math.toRadians(0))
                .forward(-26)
                .build();

        waitForStart();

        if(isStopRequested()) return;
    }
}
