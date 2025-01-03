package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public abstract class BRBLinearOpMode extends LinearOpMode {
    protected boolean redAlliance = false;
    protected boolean samples = true;

    //////////////////////////////////////////////
    /// Drive Train motors.
//    private ElapsedTime runtime = new ElapsedTime();
    private final double DRIVE_MOTOR_SPEED = 0.8;
    private SampleMecanumDrive driveTrain = null;

    //////////////////////////////////////////////
    /// Color sensor
    protected ColorSensor colorSensor = null;

    //////////////////////////////////////////////
    /// Lights
    private Servo leftLight = null;
    private Servo rightLight = null;
    private final double BLUE_LIGHT = 0.65;
    private final double RED_LIGHT = 0.35;
    private final double YELLOW_LIGHT = 0.45;
    private final double LIGHT_OFF = 0.0;


    /////////////////////////////////////////////
    /// Horizontal slide
    private final double INTAKE_CLAW_OPEN = 1;
    private final double INTAKE_CLAW_CLOSED = 0.8;
    private final double INSIDE_ROBOT_CLAW_HORIZONTAL = 0.05;
    private final double WRIST_START_POSITION = 0.8;
    private final double ARM_READY_TO_GRAB = 0.55;
    private final double ARM_GRAB = 0.7;
    private final int HORIZONTAL_SLIDE_OUT_LIMIT = -850;
    private final int HORIZONTAL_SLIDE_IN_LIMIT = 0;
    private final int HORIZONTAL_JOYSTICK_MULTIPLIER = 20;

    private int horizontalSlideLocation = 0;
    private DcMotor motorHorizontalSlide = null;
    private Servo intakClaw = null;
    private Servo intakeArm = null;
    private Servo intakeWrist = null;



    /////////////////////////////////////////////
    /// Vertical slide
    private final int TOP_VERTICAL_POSITION = 3600;
    private final int BOTTOM_VERTICAL_POSITION = 0;
    private final int MIDDLE_VERTICAL_POSITION = 1600;
    private final int VERTICAL_JOYSTICK_MULTIPLIER = 30;
    private final double TOP_CLAW_OPEN = 0.5;
    private final double TOP_CLAW_CLOSE = 0;
    private final double DROPPING_POSITION = 0.95;
    private final double INSIDE_ROBOT_CLAW_VERTICAL = 0.07;
    private Servo topWrist;
    private DcMotor motorVerticalSlide = null;
    private Servo topClaw = null;
    private Servo topArm = null;
    private int verticalCurrentPosition = 0;

    /////////////////////////////////////////////
    /// Transfer
    private boolean readyForTransfer = false;

    protected BRBLinearOpMode(boolean redAlliance, boolean samples){
        super();
        this.redAlliance = redAlliance;
        this.samples = samples;
        // Init drive train
        driveTrain = new SampleMecanumDrive(hardwareMap);
        driveTrain.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Init horizontal slide
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
        intakeArm.setPosition(INSIDE_ROBOT_CLAW_HORIZONTAL);
        intakeWrist.setPosition(WRIST_START_POSITION);
        // Init vertical slide
        motorVerticalSlide = hardwareMap.get(DcMotor.class, "verticalViper");
        topClaw = hardwareMap.get(Servo.class, "topClaw");
        topArm = hardwareMap.get(Servo.class, "topArm");
        topWrist = hardwareMap.get(Servo.class, "topWrist");
        motorVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorVerticalSlide.setTargetPosition(0);
        motorVerticalSlide.setPower(0.7);
        motorVerticalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        topArm.setPosition(INSIDE_ROBOT_CLAW_VERTICAL);
        topClaw.setPosition(TOP_CLAW_OPEN);
        // Init lights
        leftLight = hardwareMap.get(Servo.class, "lights left");
        rightLight = hardwareMap.get(Servo.class, "lights right");
        // Init color sensor
        colorSensor = hardwareMap.get(ColorSensor.class, "color sensor");
    }

    /**
     * Return the horizontal extender to inmost state and rotate the servos to the correct positions
      */
    public void transitionPart1(){
        intakeArm.setPosition(ARM_GRAB);
        sleep(300);
        intakClaw.setPosition(INTAKE_CLAW_CLOSED);
        intakeArm.setPosition(INSIDE_ROBOT_CLAW_HORIZONTAL);
        sleep(700);
        horizontalSlideLocation = HORIZONTAL_SLIDE_IN_LIMIT;
        readyForTransfer = true;
    }

    /**
     * Grab the block and turn the claw to be ready to deposit
     */
    protected void transitionPart2(){
        topClaw.setPosition(TOP_CLAW_CLOSE);
        sleep(700);
        intakClaw.setPosition(INTAKE_CLAW_OPEN);
        sleep(500);
        topArm.setPosition(DROPPING_POSITION);
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
        String whatColor = null;
        double red = colorSensor.red();
        double green = colorSensor.green();
        double blue = colorSensor.blue();
        double alpha = colorSensor.alpha();

        telemetry.addData("Green", green);
        if(red>blue && green>red && alpha>blue && red<200){
            return "yellow";
        }else if(red<blue && green<blue && alpha<blue){
            return  "blue";
        }else if(red>blue && green<red && alpha<red){
            return  "red";
        }else {
            return "NONE";
        }
    }
}
