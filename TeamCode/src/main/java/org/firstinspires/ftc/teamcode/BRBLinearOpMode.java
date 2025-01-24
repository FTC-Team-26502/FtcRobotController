package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ReadWriteFile;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.vision.VisionPortal;

import java.io.File;
import java.util.Locale;

@Disabled
public abstract class BRBLinearOpMode extends LinearOpMode {
    public static final String YELLOW_COLOR = "yellow";
    public static final String BLUE_COLOR = "blue";
    public static final String RED_COLOR = "red";
    public static final String NONE_COLOR = "NONE";
    protected boolean redAlliance = false;

    //////////////////////////////////////////////
    /// Drive Train motors.
    protected final double DRIVE_MOTOR_SPEED = 0.3;
    protected SampleMecanumDrive driveTrain = null;
    protected double driveSpeed = DRIVE_MOTOR_SPEED;
    protected double currentMotorSpeedY = 0;
    protected double currentMotorSpeedX = 0;
    protected double currentMotorSpeedHeading = 0;
    protected double amountOfChange = 0.005;

    //////////////////////////////////////////////
    /// Color sensor
    protected ColorSensor colorSensor = null;

    //////////////////////////////////////////////
    /// Lights
    protected Servo leftLight = null;
    protected Servo rightLight = null;
    protected final double BLUE_LIGHT = 0.65;
    protected final double RED_LIGHT = 0.35;
    protected final double YELLOW_LIGHT = 0.45;
    protected final double LIGHT_OFF = 0.0;


    /////////////////////////////////////////////
    /// Horizontal slide
    protected final double INTAKE_CLAW_OPEN = 1;
    protected final double INTAKE_CLAW_CLOSED = 0;
    protected final double INSIDE_ROBOT_CLAW_HORIZONTAL = 0.05;
    protected final double WRIST_START_POSITION = 0.5;
    protected final double ARM_READY_TO_GRAB = 0.55;
    protected final double ARM_GRAB = 0.65;
    protected final int HORIZONTAL_SLIDE_OUT_LIMIT = -900;
    protected final int HORIZONTAL_SLIDE_IN_LIMIT = 0;
    protected final int HORIZONTAL_JOYSTICK_MULTIPLIER = 20;
    protected final double SPECIMEN_GRAB = 1;

    protected double currentwristPosition = WRIST_START_POSITION;

    protected int horizontalSlideLocation = HORIZONTAL_SLIDE_IN_LIMIT;
    protected DcMotor motorHorizontalSlide = null;
    protected Servo intakeClaw = null;
    protected Servo intakeArm = null;
    protected Servo intakeWrist = null;
    //protected double intakeClawPosition = INTAKE_CLAW_OPEN;

    /////////////////////////////////////////////
    /// Vertical slide
    protected final int TOP_VERTICAL_POSITION = 3200;
    protected final int BOTTOM_VERTICAL_POSITION = 0;
    protected final int MIDDLE_VERTICAL_POSITION = 600;

    protected String whatColor;

    protected int verticalBottom = BOTTOM_VERTICAL_POSITION;
    protected final int VERTICAL_JOYSTICK_MULTIPLIER = 10;
    protected final double TOP_CLAW_OPEN = 1;
    protected final double TOP_CLAW_CLOSE = 0;
    protected final double TOP_CLAW_ALMOST_CLOSE = 0.4;
    protected final double DROPPING_POSITION = 0.25;
    protected final double BASKET_DROP = 0.7;
    protected final double UP_POSITION = 0.45;
    protected final double FRONT_POSITION = 0.18;
    protected final double INSIDE_ROBOT_CLAW_VERTICAL = 0;
    protected final double WRIST_START_POSITION_TOP = 0.325;
    protected final double WRIST_HANG_POSITION = 1;
    protected Servo topWrist;
    protected DcMotor motorVerticalSlide = null;
    protected Servo topClaw = null;
    protected Servo topArm = null;
    protected int verticalCurrentPosition = 0;

    /////////////////////////////////////////////
    /// Transfer
    //protected boolean readyForTransfer = false;

    ////////////////////////////////////////////
    /// Webcam
    protected final boolean COLLECT_DATA = false;
    protected final int RESOLUTION_WIDTH = 640;
    protected final int RESOLUTION_HEIGHT = 480;
    protected VisionPortal portal = null;
    protected long lastCaptureTime = System.currentTimeMillis();
    protected String fileContent = "";
    protected int countRows = 0;


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
        motorVerticalSlide.setPower(0.3);
        motorVerticalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
        topClaw.setPosition(TOP_CLAW_OPEN);
        topWrist.setPosition(WRIST_START_POSITION_TOP);
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


    protected String detectColor(){
        final double YELLOW = 0.388;
        final double BLUE = 0.611;
        final double RED = 0.279;
        final double SAGE = 0.48;
        double red = colorSensor.red();
        double blue = colorSensor.blue();
        double green = colorSensor.green();
        if (blue > green && blue > 2*red) {
            leftLight.setPosition(BLUE);
            rightLight.setPosition(BLUE);
            whatColor = BLUE_COLOR;
        } else if (red > 1.3*blue && red > green){
            leftLight.setPosition(RED);
            rightLight.setPosition(RED);
            whatColor = RED_COLOR;
        } else if (red>1.3*blue && green>1.3*blue) {
            leftLight.setPosition(YELLOW);
            rightLight.setPosition(YELLOW);
            whatColor = YELLOW_COLOR;
        } else{
            leftLight.setPosition(SAGE);
            rightLight.setPosition(SAGE);
            whatColor = NONE_COLOR;
        }
        return whatColor;
    }
    protected void closeIntakeClaw() {
        intakeClaw.setPosition(INTAKE_CLAW_CLOSED);
//        intakeClawPosition = INTAKE_CLAW_CLOSED;
    }
    protected void openIntakeClaw() {
        intakeClaw.setPosition(INTAKE_CLAW_OPEN);
//        intakeClawPosition = INTAKE_CLAW_OPEN;
    }
    protected void motorVerticalController() {
        verticalCurrentPosition -= gamepad2.right_stick_y * VERTICAL_JOYSTICK_MULTIPLIER;
        verticalCurrentPosition = Math.max(verticalCurrentPosition, verticalBottom);
        verticalCurrentPosition = Math.min(verticalCurrentPosition, TOP_VERTICAL_POSITION);
//        telemetry.addData("vericalCurrentPosition!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: ", verticalCurrentPosition);
        motorVerticalSlide.setTargetPosition(verticalCurrentPosition);
    }
    protected void driveControls(boolean viperUp, boolean backAllowed) {
        if (gamepad1.left_trigger>0 && gamepad1.right_trigger == 0 || viperUp) {
            amountOfChange = 0.003;
        } else if (gamepad1.right_trigger>0 && gamepad1.left_trigger == 0 ) {
            amountOfChange = 0.007;
        } else if (gamepad1.left_trigger > 0 && gamepad1.right_trigger > 0) {
            amountOfChange = 0.01;
        }else {
            amountOfChange = 0.005;
        }
        driveSpeed = DRIVE_MOTOR_SPEED + gamepad2.right_trigger - gamepad2.left_trigger;
        currentMotorSpeedX = speedIncrease(currentMotorSpeedX, gamepad1.left_stick_x, amountOfChange);
        if ( !viperUp ) {
            currentMotorSpeedY = speedIncrease(currentMotorSpeedY, gamepad1.left_stick_y, amountOfChange);
//            telemetry.addData("!!!!!!!!!!!!!!!!!!!!gamepad", gamepad1.left_stick_y);
        } else {
            if (backAllowed) {
//                telemetry.addData("!!!!!!!!!!!!!!!!!!!!gamepad", gamepad1.left_stick_y);
                currentMotorSpeedY = speedIncrease(currentMotorSpeedY, gamepad1.left_stick_y, amountOfChange);
//                telemetry.addData("back", "allowed");
            } else {
//                telemetry.addData("!!!!!!!!!!!!!!!!!!!!gamepad", gamepad1.left_stick_y);
                if (gamepad1.left_stick_y < 0) {
                    currentMotorSpeedY = speedIncrease(currentMotorSpeedY, gamepad1.left_stick_y, amountOfChange);
//                    telemetry.addData("Going", "forward");
                } else {
                    currentMotorSpeedY = 0;
//                    telemetry.addData("Not", "moving");
                }
            }
        }
        currentMotorSpeedHeading = speedIncrease(currentMotorSpeedHeading, gamepad1.right_stick_x, amountOfChange);
        // move the robot
        driveTrain.setWeightedDrivePower(
                new Pose2d(
                        -currentMotorSpeedY/2,

                        -currentMotorSpeedX/2,

                        -currentMotorSpeedHeading/5
                )
        );
        if(gamepad1.dpad_up){
            driveTrain.setWeightedDrivePower(
                    new Pose2d(
                            -driveSpeed/7,

                            0,

                            0
                    )
            );
            telemetry.addData("DPAD_UP", "11111111111122222222222222222223333333333333334444444444444");
        }
        if(gamepad1.dpad_down){
            driveTrain.setWeightedDrivePower(
                    new Pose2d(
                            driveSpeed/7,

                            0,

                            0
                    )
            );
            telemetry.addData("DPAD_DOWN", "11111111111122222222222222222223333333333333334444444444444");
        }
        if(gamepad1.dpad_right){
            driveTrain.setWeightedDrivePower(
                    new Pose2d(
                            0,

                            -driveSpeed/7,

                            0
                    )
            );
            telemetry.addData("DPAD_RIGHT", "11111111111122222222222222222223333333333333334444444444444");
        }
        if(gamepad1.dpad_left){
            driveTrain.setWeightedDrivePower(
                    new Pose2d(
                            0,

                            driveSpeed/7,

                            0
                    )
            );
            telemetry.addData("DPAD_LEFT", "11111111111122222222222222222223333333333333334444444444444");
        }
        telemetry.addData("Y speed", currentMotorSpeedY );
        telemetry.addData("X speed", currentMotorSpeedX );
        telemetry.addData("Turn speed", currentMotorSpeedHeading );
    }

    private double speedIncrease(double motorSpeed, double gamepad1, double amountOfChange) {
        double newSpeed = motorSpeed;
        if (Math.abs( gamepad1) < 0.01) {
            newSpeed = 0;
        } else {
            if (motorSpeed < gamepad1) {
                // backwards
                newSpeed = Math.max(motorSpeed - amountOfChange, gamepad1);
            } else {
                //forwards
                newSpeed = Math.min(motorSpeed + amountOfChange, gamepad1);
            }
        }
        return newSpeed;
    }

    protected void horizontalControls() {
        if (gamepad2.left_stick_x > 0 && horizontalSlideLocation > HORIZONTAL_SLIDE_OUT_LIMIT) {
            horizontalSlideLocation -= gamepad2.left_stick_x * HORIZONTAL_JOYSTICK_MULTIPLIER;
            telemetry.addData("Going ", "out");
//            readyForTransfer = false;
        } else if (gamepad2.left_stick_x < 0 && horizontalSlideLocation < HORIZONTAL_SLIDE_IN_LIMIT) {
            horizontalSlideLocation -= gamepad2.left_stick_x * HORIZONTAL_JOYSTICK_MULTIPLIER;
            telemetry.addData("Going ", "in");
//            readyForTransfer = false;
        }
        motorHorizontalSlide.setTargetPosition(horizontalSlideLocation);
    }

    protected void wristControl() {
        if (gamepad2.b) {
            currentwristPosition = WRIST_START_POSITION - 0.5;
        }else if (gamepad2.a) {
            currentwristPosition = WRIST_START_POSITION;
        } else if (gamepad2.left_stick_y != 0) {
            currentwristPosition += gamepad2.left_stick_y/100;
        }
        if (currentwristPosition>(WRIST_START_POSITION-0.5)) {
            currentwristPosition = Math.min(WRIST_START_POSITION,currentwristPosition);
        }else {
            currentwristPosition = Math.max(WRIST_START_POSITION-0.5, currentwristPosition);
        }
        intakeWrist.setPosition(currentwristPosition);
        telemetry.addData("Wrist Position", currentwristPosition);
    }

    protected void dumpState() {
//        telemetry.addData("Motor position", motorHorizontalSlide.getCurrentPosition());
//        telemetry.addData("Servo claw", intakeClaw.getPosition());
//        telemetry.addData("Joy Stick:", gamepad2.right_stick_y);
////        telemetry.addData("Ready for transfer", readyForTransfer);
//        telemetry.addData("Viper position", motorVerticalSlide.getCurrentPosition());
//        telemetry.addData("Viper target position", motorVerticalSlide.getTargetPosition());
//        telemetry.addData("Current Position Variable", verticalCurrentPosition);

    }

    protected void saveFrame() {
        if ( COLLECT_DATA && System.currentTimeMillis() - lastCaptureTime > 500 ) {
            String filename = String.format(Locale.US, "c%d", lastCaptureTime);
            portal.saveNextFrameRaw(filename);
            lastCaptureTime = System.currentTimeMillis();
            fileContent += String.format(Locale.US, "%s, %f, %f, %f\n",
                    filename,
                    topArm.getPosition(), topClaw.getPosition(), topWrist.getPosition());
            countRows ++;
            if (countRows > 1000) {
                ReadWriteFile.writeFile(new File("l" + System.currentTimeMillis() + ".log"), fileContent);
                countRows = 0;
            }
        }
    }

    protected void closeDataCollection() {
        if (COLLECT_DATA) {
            ReadWriteFile.writeFile(new File("l" + System.currentTimeMillis() + ".log"), fileContent);
        }
    }


    protected void getReadyToGrab() {
        intakeArm.setPosition(ARM_READY_TO_GRAB);
        intakeClaw.setPosition(INTAKE_CLAW_OPEN);
        topWrist.setPosition(WRIST_START_POSITION_TOP);
    }


    protected void transfer() {
        topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
        sleep(700);
        topClaw.setPosition(TOP_CLAW_CLOSE);
        sleep(700);
        intakeClaw.setPosition(INTAKE_CLAW_OPEN);
        sleep(500);
        topArm.setPosition(DROPPING_POSITION);
    }

    protected void resetTransfer() {
        intakeArm.setPosition(ARM_READY_TO_GRAB);
        intakeClaw.setPosition(INTAKE_CLAW_OPEN);
        topArm.setPosition(FRONT_POSITION);
        topClaw.setPosition(TOP_CLAW_OPEN);
    }
}
