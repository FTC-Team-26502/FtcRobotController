package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;


@TeleOp(name="Dorina and Ananya", group="Second Competition")
//  @Disabled
public class SecondCompetitionTeleOp extends LinearOpMode {

    protected boolean redAlliance = false;

    //////////////////////////////////////////////
    /// Drive Train motors.
    private ElapsedTime runtime = new ElapsedTime();
    private SampleMecanumDrive driveTrain = null;
//    private DcMotor frontLeft = null;
//    private DcMotor backLeft = null;
//    private DcMotor frontRight = null;
//    private DcMotor backRight = null;

    //////////////////////////////////////////////
    /// Color sensor
    protected ColorSensor colorSensor = null;

    //////////////////////////////////////////////
    /// Lights
    private Servo leftLight = null;
    private Servo rightLight = null;
    private final double BLUE = 0.65;
    private final double RED = 0.35;
    private final double YELLOW = 0.45;
    private final double OFF = 0.0;


    /////////////////////////////////////////////
    /// Horizontal slide
    private DcMotor motorHorizontalSlide = null;
    private Servo intakClaw = null;
    private Servo intakeArm = null;
    private Servo intakeWrist = null;


    /////////////////////////////////////////////
    /// Horizontal slide
    private Servo intakeWristVerticalSlide;
    private DcMotor motorVerticalSlide = null;
    private Servo clawVerticalSlide = null;
    private Servo topArm = null;
    //making the position for linear and vertical extension
    private final int TOP_VERTICAL_SLIDE_POSITION = 3600;
    private final int BOTTOM_VERTICAL_SLIDE_POSITION = 0;
    private final int MIDDLE_VERTICAL_SLIDE_POSITION = 1600;
    private final int VERTICAL_JOYSTICK_MULTIPLYER = 30;
    private int verticalCurrentPosition = 0;

    private final double MOTOR_SPEED = 0.8;

    private final int HORIZONTAL_SLIDE_OUT_LIMIT = -850;
    private final int HORIZONTAL_SLIDE_IN_LIMIT = 0;
    private int horizontalSlideLocation = 0;
    private int HORIZONTAL_JOYSTICK_MULTIPLYER = 20;

    private boolean readyForTransfer = false;

    private final double INTAKE_CLAW_OPEN = 1;
    private final double INTAKE_CLAW_CLOSED = 0.8;
    private final double INSIDED_ROBOT_CLAW_HORIZONTAL = 0.05;
        private final double WRIST_START_POSITION = 0.8;
    private final double ARM_READY_TO_GRAB = 0.55;
    private final double ARM_GRAB = 0.7;

    private final double TOP_CLAW_OPEN = 0.5;
    private final double TOP_CLAW_CLOSE = 0;
    private final double DROPPING_POSITION = 0.95;
    private final double INSIDED_ROBOT_CLAW_VERTICAL = 0.07;



    private AprilTagProcessor aprilTag;

    private Position cameraPosition = new Position(DistanceUnit.INCH,
            0, 0, 0, 0);
    private YawPitchRollAngles cameraOrientation = new YawPitchRollAngles(AngleUnit.DEGREES,
            0, -90, 0, 0);

    private void initLights() {
        leftLight = hardwareMap.get(Servo.class, "lights left");
        rightLight = hardwareMap.get(Servo.class, "lights right");
    }


    private void initDriveTrain() {
        driveTrain = new SampleMecanumDrive(hardwareMap);
        driveTrain.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    private void initHorizontalSlide() {
        motorHorizontalSlide = hardwareMap.get(DcMotor.class, "horizontalExtender");
        intakClaw = hardwareMap.get(Servo.class, "intakeClaw");
        intakeArm = hardwareMap.get(Servo.class, "intakeArm");
        intakeWrist = hardwareMap.get(Servo.class,"intakeWrist");
        motorHorizontalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorHorizontalSlide.setTargetPosition(0);
        motorHorizontalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorHorizontalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorHorizontalSlide.setPower(0.5);
        intakeArm.setDirection(Servo.Direction.FORWARD);
        intakeArm.setPosition(INSIDED_ROBOT_CLAW_HORIZONTAL);
        intakeWrist.setPosition(WRIST_START_POSITION);
    }

    private void runHorizontalSlide() {
        String whatColor = null;
        double red = colorSensor.red();
        double green = colorSensor.green();
        double blue = colorSensor.blue();
        double alpha = colorSensor.alpha();

        telemetry.addData("Green", green);
//        if(red>blue && green>red && alpha>blue && red<200){
//            whatColor= "yellow";
//            intakClaw.setPosition(0.0);
//        }else if(red<blue && green<blue && alpha<blue){
//            whatColor = "blue";
//            if ( redAlliance ) {
//                intakClaw.setPosition(0.0);
//            } else {
//                intakClaw.setPosition(1.0);
//            }
//        }else if(red>blue && green<red && alpha<red){
//            whatColor = "red";
//            if ( redAlliance ) {
//                intakClaw.setPosition(1.0);
//            } else {
//                intakClaw.setPosition(0.8);
//            }
//        }else{
//            whatColor = "NONE";
        if(gamepad2.a){
            intakClaw.setPosition(INTAKE_CLAW_OPEN);
        }if(gamepad2.b){
            intakClaw.setPosition(INTAKE_CLAW_CLOSED);
        }


        if(gamepad2.left_trigger>0){
            readyForTransfer = false;
            intakeArm.setPosition(ARM_READY_TO_GRAB);
            intakClaw.setPosition(INTAKE_CLAW_OPEN);
        }else if(gamepad2.right_trigger>0){
            intakeArm.setPosition(ARM_GRAB);
            sleep(300);
            intakClaw.setPosition(INTAKE_CLAW_CLOSED);
            intakeArm.setPosition(INSIDED_ROBOT_CLAW_HORIZONTAL);
            sleep(700);
            horizontalSlideLocation = HORIZONTAL_SLIDE_IN_LIMIT;
            readyForTransfer = true;
        }
//        if(horizontalSlideLocation == HORIZONTAL_SLIDE_IN_LIMIT){
//            readyForTransfer = true;
//        }
        if (gamepad2.left_stick_x > 0 && horizontalSlideLocation > HORIZONTAL_SLIDE_OUT_LIMIT) {
            horizontalSlideLocation -= gamepad2.left_stick_x * HORIZONTAL_JOYSTICK_MULTIPLYER;
            readyForTransfer = false;
        } else if (gamepad2.left_stick_x < 0 && horizontalSlideLocation < HORIZONTAL_SLIDE_IN_LIMIT) {
            horizontalSlideLocation -= gamepad2.left_stick_x * HORIZONTAL_JOYSTICK_MULTIPLYER;
            readyForTransfer = false;
        }
            motorHorizontalSlide.setTargetPosition(horizontalSlideLocation);

        // Display the color values on the telemetry
        telemetry.addData("Red", red);
        telemetry.addData("Blue", blue);
        telemetry.addData("Alpha", alpha);
        telemetry.addData("What Color:", whatColor);
        telemetry.addData("Motor position", motorHorizontalSlide.getCurrentPosition());
        telemetry.addData("Servo claw", intakClaw.getPosition());
        telemetry.addData("Joy Stick:", gamepad2.right_stick_x);
        telemetry.addData("Ready for transfer", readyForTransfer);
        telemetry.update();
    }

    private void initVerticalSlide() {
        motorVerticalSlide = hardwareMap.get(DcMotor.class, "verticalViper");
        clawVerticalSlide = hardwareMap.get(Servo.class, "topClaw");
        topArm = hardwareMap.get(Servo.class, "topArm");
        intakeWristVerticalSlide = hardwareMap.get(Servo.class, "topWrist");
        motorVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorVerticalSlide.setTargetPosition(0);
        motorVerticalSlide.setPower(0.7);
        motorVerticalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        topArm.setPosition(INSIDED_ROBOT_CLAW_VERTICAL);
        clawVerticalSlide.setPosition(TOP_CLAW_OPEN);
    }

    private void runVerticalSlide() {
        //moving the viper slide up to different positions
        if (gamepad2.a){
            verticalCurrentPosition = BOTTOM_VERTICAL_SLIDE_POSITION;
        } else if (gamepad2.b) {
            verticalCurrentPosition = TOP_VERTICAL_SLIDE_POSITION;
        }else if (gamepad2.x) {
            verticalCurrentPosition = MIDDLE_VERTICAL_SLIDE_POSITION;
        } else if (gamepad2.right_stick_y > 0 && verticalCurrentPosition < TOP_VERTICAL_SLIDE_POSITION) {
            verticalCurrentPosition -= gamepad2.right_stick_y * VERTICAL_JOYSTICK_MULTIPLYER;
        } else if (gamepad2.right_stick_y < 0 && verticalCurrentPosition > BOTTOM_VERTICAL_SLIDE_POSITION) {
            verticalCurrentPosition -= gamepad2.right_stick_y * VERTICAL_JOYSTICK_MULTIPLYER;
        }
        motorVerticalSlide.setTargetPosition(verticalCurrentPosition);
        if(readyForTransfer && !motorHorizontalSlide.isBusy() && gamepad2.y) {
            clawVerticalSlide.setPosition(TOP_CLAW_CLOSE);
            sleep(700);
            intakClaw.setPosition(INTAKE_CLAW_OPEN);
            sleep(500);
            topArm.setPosition(DROPPING_POSITION);
        }
        if (gamepad2.left_bumper){
            clawVerticalSlide.setPosition(TOP_CLAW_OPEN);
            sleep(300);
            telemetry.addData("hi i am being told to move", "by dorina");
            topArm.setPosition(INSIDED_ROBOT_CLAW_VERTICAL);
        }
        telemetry.addData("motor position", motorVerticalSlide.getCurrentPosition());


    }

    private void initColorSensor() {
        colorSensor = hardwareMap.get(ColorSensor.class, "color sensor");
    }



    /**
     * Add telemetry about AprilTag detections.
     */




    private void runDriveTrain() {
//        // calculating the power for each wheel with controller input
//        double frontLeftPower = (gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x)/1.5;
//        double backLeftPower = (gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x)/1.5;
//        double frontRightPower = (gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x)/1.5;
//        double backRightPower = (gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x)/1.5   ;
//
//        frontLeft.setPower(frontLeftPower);
//        backLeft.setPower(backLeftPower);
//        frontRight.setPower(frontRightPower);
//        backRight.setPower(backRightPower);
        driveTrain.setWeightedDrivePower(
                new Pose2d(
                        gamepad1.left_stick_y * MOTOR_SPEED,

                        gamepad1.left_stick_x * MOTOR_SPEED,

                        -gamepad1.right_stick_x * MOTOR_SPEED
                )
        );
    }




    @Override
    public void runOpMode() {

//        initDriveTrain();
        initColorSensor();
        initHorizontalSlide();
        initVerticalSlide();
//        initLights();

        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
//            runDriveTrain();
            runHorizontalSlide();
            runVerticalSlide();
        }
    }

}
 