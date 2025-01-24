package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous
public abstract class Comp2AutoSpecimens extends Comp2Specimens{
    @Override
    protected void initOpMode(boolean redAlliance){
        this.redAlliance = redAlliance;

        // Init drive train
        driveTrain = new SampleMecanumDrive(hardwareMap);
        driveTrain.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Init horizontal slide
        motorHorizontalSlide = hardwareMap.get(DcMotor.class, "horizontalExtender");
        intakeClaw = hardwareMap.get(Servo.class, "intakeClaw");
        intakeArm = hardwareMap.get(Servo.class, "intakeArm");
        intakeWrist = hardwareMap.get(Servo.class,"intakeWrist");
        motorHorizontalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorHorizontalSlide.setTargetPosition(HORIZONTAL_SLIDE_IN_LIMIT);
        motorHorizontalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorHorizontalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorHorizontalSlide.setPower(0.5);
        intakeArm.setDirection(Servo.Direction.FORWARD);
        intakeArm.setPosition(INSIDE_ROBOT_CLAW_HORIZONTAL);
        intakeWrist.setPosition(WRIST_START_POSITION);
        // Init vertical slide
        motorVerticalSlide = hardwareMap.get(DcMotor.class, "verticalViper");
        topClaw  = hardwareMap.get(Servo.class, "topClaw");
        topArm   = hardwareMap.get(Servo.class, "topArm");
        topWrist = hardwareMap.get(Servo.class, "topWrist");
        motorVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorVerticalSlide.setTargetPosition(BOTTOM_VERTICAL_POSITION);
        motorVerticalSlide.setPower(0.6);
        motorVerticalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
        topClaw.setPosition(TOP_CLAW_CLOSE);
        topWrist.setPosition(WRIST_HANG_POSITION);
        // Init lights
        leftLight = hardwareMap.get(Servo.class, "lights left");
        rightLight = hardwareMap.get(Servo.class, "lights right");
        // Init color sensor
        colorSensor = hardwareMap.get(ColorSensor.class, "color sensor");
        // webcam
        if (COLLECT_DATA)
        {
            portal = new VisionPortal.Builder()
                    .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                    .setCameraResolution(new Size(RESOLUTION_WIDTH, RESOLUTION_HEIGHT))
                    .build();
        }

//        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }
    
    public void loopOpMode() throws InterruptedException {
         //Build Trajectories:
        Pose2d startPose = getPose2d(7.5, -65, Math.toRadians(90));
        driveTrain.setPoseEstimate(startPose);

        TrajectorySequence driveToBarStart = driveTrain.trajectorySequenceBuilder(startPose)
                .splineTo(getVector2d(0, -36), getHeading(Math.toRadians(90)))
//                .forward(10)
//                .forward(-10)
                .build();

        TrajectorySequence waitForViperRaise = driveTrain.trajectorySequenceBuilder(startPose)
                .forward(16)
                .build();
        TrajectorySequence pushSamples = driveTrain.trajectorySequenceBuilder(
                // TODO change this later if we do not drive to bar
                driveToBarStart.end())

                .forward(-16)
                .strafeTo(getVector2d(33, -36))
                .strafeTo(getVector2d(33,0))
                .strafeTo(getVector2d(36,0))
                .forward(-55)
                .strafeLeft(10)
//                .forward(55)
//                .strafeRight(5)
//                .forward(15)
//                .strafeLeft(10)
//                .forward(55)
//                .strafeRight(5)
//                .strafeTo(getVector2d(43,0))
//                .strafeTo(getVector2d(43,-54))
//                .strafeTo(getVector2d(43,0))
//                .strafeTo(getVector2d(45,0))
//                .forward(45)
                .build();

        TrajectorySequence grabingOfWall = driveTrain.trajectorySequenceBuilder(pushSamples.end())
//                .strafeTo(getVector2d(47,-55))
//                .forward(5)
//                .forward(-23)
//                .splineTo(getVector2d(0, -56), getHeading(Math.toRadians(270)))
//                .forward(26)
//                .strafeTo(getVector2d(47,-55))
//                .forward(-6)
                .build();
        TrajectorySequence driveHome = driveTrain.trajectorySequenceBuilder(pushSamples.end())
                .strafeTo(getVector2d(36,-55))
                .build();

        waitForStart();
        hangSpecimen();
        driveTrain.followTrajectorySequence(driveToBarStart);
        motorVerticalSlide.setTargetPosition(MIDDLE_VERTICAL_POSITION+1000);
        sleep(1000);
        driveTrain.followTrajectorySequence(waitForViperRaise);
        motorVerticalSlide.setTargetPosition(MIDDLE_VERTICAL_POSITION-300);
        sleep(1000);
        resetToInitial();
        motorVerticalSlide.setTargetPosition(0);
        sleep(100);
        driveTrain.followTrajectorySequence(pushSamples);
//        specimenDrop();
//        sleep(1000);
//        driveTrain.followTrajectorySequence(grabingOfWall);
//        hangSpecimen();
//        driveTrain.followTrajectorySequence(driveToBarStart);
//        motorVerticalSlide.setTargetPosition(MIDDLE_VERTICAL_POSITION+1000);
//        driveTrain.followTrajectorySequence(waitForViperRaise);
//        resetToInitial();
//        motorVerticalSlide.setTargetPosition(0);


        // TODO hang specimen
//        driveTrain.followTrajectorySequence(pushSamples);

    }
}
