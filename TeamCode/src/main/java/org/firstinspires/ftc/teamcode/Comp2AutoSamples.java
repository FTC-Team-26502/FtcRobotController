package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

@Autonomous(name = "tester")
public class Comp2Auto  extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
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

//        Trajectory turnToSample1 = drive.trajectoryBuilder(new Pose2d())
//                .splineTo(new Vector2d(52, 56), Math.toRadians(0))
//                .build();
//        Trajectory turnToBacket = drive.trajectoryBuilder(new Pose2d())
//                .splineTo(new Vector2d(52, 56), Math.toRadians(90))
//                .build();
//        Trajectory turnToSample2 = drive.trajectoryBuilder(new Pose2d())
//                .splineTo(new Vector2d(52, 56), Math.toRadians(105))
//                .build();
//        Trajectory turnToSample3 = drive.trajectoryBuilder(new Pose2d())
//                .splineTo(new Vector2d(52, 56), Math.toRadians(75))
//                .build();
//        Trajectory park = drive.trajectoryBuilder(new Pose2d())
//                .splineTo(new Vector2d(17, 10), Math.toRadians(75))
//                .build();
        waitForStart();

        if(isStopRequested()) return;
    }
}
