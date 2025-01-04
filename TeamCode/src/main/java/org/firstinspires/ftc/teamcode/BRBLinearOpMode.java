package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public abstract class BRBLinearOpMode extends LinearOpMode {
    public static final String YELLOW_COLOR = "yellow";
    public static final String BLUE_COLOR = "blue";
    public static final String RED_COLOR = "red";
    public static final String NONE_COLOR = "NONE";
    protected boolean redAlliance = false;

    //////////////////////////////////////////////
    /// Drive Train motors.
    protected final double DRIVE_MOTOR_SPEED = 0.8;
    protected SampleMecanumDrive driveTrain = null;
    protected double driveSpeed = DRIVE_MOTOR_SPEED;

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
    protected final double INTAKE_CLAW_CLOSED = 0.8;
    protected final double INSIDE_ROBOT_CLAW_HORIZONTAL = 0.05;
    protected final double WRIST_START_POSITION = 0.8;
    protected final double ARM_READY_TO_GRAB = 0.5;
    protected final double ARM_GRAB = 0.7;
    protected final int HORIZONTAL_SLIDE_OUT_LIMIT = -850;
    protected final int HORIZONTAL_SLIDE_IN_LIMIT = 0;
    protected final int HORIZONTAL_JOYSTICK_MULTIPLIER = 20;
    protected final double SPECIMEN_GRAB = 1;

    protected int horizontalSlideLocation = HORIZONTAL_SLIDE_IN_LIMIT;
    protected DcMotor motorHorizontalSlide = null;
    protected Servo intakeClaw = null;
    protected Servo intakeArm = null;
    protected Servo intakeWrist = null;
    protected double intakeClawPosition = INTAKE_CLAW_OPEN;

    /////////////////////////////////////////////
    /// Vertical slide
    protected final int TOP_VERTICAL_POSITION = 3500;
    protected final int BOTTOM_VERTICAL_POSITION = 0;
    protected final int MIDDLE_VERTICAL_POSITION = 1400;
    protected final int VERTICAL_JOYSTICK_MULTIPLIER = 20;
    protected final double TOP_CLAW_OPEN = 0.5;
    protected final double TOP_CLAW_CLOSE = 0;
    protected final double DROPPING_POSITION = 0.3;
    protected final double UP_POSITION = 0.45;
    protected final double FRONT_POSITION = 0.17;
    protected final double INSIDE_ROBOT_CLAW_VERTICAL = 0;
    protected final double WRIST_START_POSITION_TOP = 0.85;
    protected final double WRIST_HANG_POSITION = 0.15;
    protected Servo topWrist;
    protected DcMotor motorVerticalSlide = null;
    protected Servo topClaw = null;
    protected Servo topArm = null;
    protected int verticalCurrentPosition = 0;

    /////////////////////////////////////////////
    /// Transfer
    protected boolean readyForTransfer = false;

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
        topClaw = hardwareMap.get(Servo.class, "topClaw");
        topArm = hardwareMap.get(Servo.class, "topArm");
        topWrist = hardwareMap.get(Servo.class, "topWrist");
        motorVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorVerticalSlide.setTargetPosition(BOTTOM_VERTICAL_POSITION);
        motorVerticalSlide.setPower(0.5);
        motorVerticalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
        topClaw.setPosition(TOP_CLAW_OPEN);
        topWrist.setPosition(WRIST_START_POSITION_TOP);
        // Init lights
//        leftLight = hardwareMap.get(Servo.class, "lights left");
//        rightLight = hardwareMap.get(Servo.class, "lights right");
        // Init color sensor
        colorSensor = hardwareMap.get(ColorSensor.class, "color sensor");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /**
     * Extend the viper slide up
     */
    protected void viperSlideUp(){
        motorVerticalSlide.setTargetPosition(TOP_VERTICAL_POSITION);
    }

    /**
     * Move the viper slide to the middle
     */
    protected void viperSlideMiddle(){
        motorVerticalSlide.setTargetPosition(MIDDLE_VERTICAL_POSITION);
    }

    /**
     * Retract the viper slide down
     */
    protected void viperSlideDown(){
        motorVerticalSlide.setTargetPosition(BOTTOM_VERTICAL_POSITION);
    }

    protected String detectColor(){
        double red = colorSensor.red();
        double green = colorSensor.green();
        double blue = colorSensor.blue();
        double alpha = colorSensor.alpha();

        telemetry.addData("Green", green);
        if(red>blue && green>red && alpha>blue && red<200){
            return YELLOW_COLOR;
        }else if(red<blue && green<blue && alpha<blue){
            return BLUE_COLOR;
        }else if(red>blue && green<red && alpha<red){
            return RED_COLOR;
        }else {
            return NONE_COLOR;
        }
    }
    protected void closeIntakeClaw() {
        intakeClaw.setPosition(INTAKE_CLAW_CLOSED);
        intakeClawPosition = INTAKE_CLAW_CLOSED;
    }
    protected void openIntakeClaw() {
        intakeClaw.setPosition(INTAKE_CLAW_OPEN);
        intakeClawPosition = INTAKE_CLAW_OPEN;
    }

    protected void dumpState() {
        telemetry.addData("Motor position", motorHorizontalSlide.getCurrentPosition());
        telemetry.addData("Servo claw", intakeClaw.getPosition());
        telemetry.addData("Joy Stick:", gamepad2.right_stick_x);
        telemetry.addData("Ready for transfer", readyForTransfer);
    }
}
