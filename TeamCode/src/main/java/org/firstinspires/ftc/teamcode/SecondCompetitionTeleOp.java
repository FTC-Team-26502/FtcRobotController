package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;


@TeleOp(name="Dorina and Ananya", group="Second Competition")
//  @Disabled
public class SecondCompetitionTeleOp extends LinearOpMode {
 
    protected boolean redAlliance = false;

    //////////////////////////////////////////////
    /// Drive Train motors.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

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
    private Servo clawHorizontalSlide = null;
    private Servo intakeVerticalRotationHorizontalSlide = null;
    
     
    /////////////////////////////////////////////
    /// Horizontal slide
    private DcMotor motorVerticalSlide = null;
    private Servo clawVerticalSlide = null;
    private Servo intakeVerticalRotationVerticalSlide = null;
    //making the position for linear and vertical extension
    private final int TOP_VERTICAL_SLIDE_POSITION = 4000;
    private final int BOTTOM_VERTICAL_SLIDE_POSITION = 0;
    private final int MIDDLE_VERTICAL_SLIDE_POSITION = 1200;
    private final int LOW_VERTICAL_SLIDE_POSITION = 700;


    /////////////////////////////////////////////
    // AprilTag variables
    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
     /**
     * The variable to store our instance of the AprilTag processor.
     */
    private AprilTagProcessor aprilTag;

    /**
     * Variables to store the position and orientation of the camera on the robot. Setting these
     * values requires a definition of the axes of the camera and robot:
     *
     * Camera axes:
     * Origin location: Center of the lens
     * Axes orientation: +x right, +y down, +z forward (from camera's perspective)
     *
     * Robot axes (this is typical, but you can define this however you want):
     * Origin location: Center of the robot at field height
     * Axes orientation: +x right, +y forward, +z upward
     *
     * Position:
     * If all values are zero (no translation), that implies the camera is at the center of the
     * robot. Suppose your camera is positioned 5 inches to the left, 7 inches forward, and 12
     * inches above the ground - you would need to set the position to (-5, 7, 12).
     *
     * Orientation:
     * If all values are zero (no rotation), that implies the camera is pointing straight up. In
     * most cases, you'll need to set the pitch to -90 degrees (rotation about the x-axis), meaning
     * the camera is horizontal. Use a yaw of 0 if the camera is pointing forwards, +90 degrees if
     * it's pointing straight left, -90 degrees for straight right, etc. You can also set the roll
     * to +/-90 degrees if it's vertical, or 180 degrees if it's upside-down.
     */
    private Position cameraPosition = new Position(DistanceUnit.INCH,
            0, 0, 0, 0);
    private YawPitchRollAngles cameraOrientation = new YawPitchRollAngles(AngleUnit.DEGREES,
            0, -90, 0, 0);


    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;

    private void initLights() {
        leftLight = hardwareMap.get(Servo.class, "lights left");
        rightLight = hardwareMap.get(Servo.class, "lights right");
    }


    private void initDriveTrain() {
        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        frontLeft  = hardwareMap.get(DcMotor.class, "left front");
        backLeft  = hardwareMap.get(DcMotor.class, "left back");
        frontRight = hardwareMap.get(DcMotor.class, "right front");
        backRight = hardwareMap.get(DcMotor.class, "right back");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void initHorizontalSlide() {
        motorHorizontalSlide = hardwareMap.get(DcMotor.class, "horizontal");
        clawHorizontalSlide = hardwareMap.get(Servo.class, "claw horizontal");
        intakeVerticalRotationHorizontalSlide = hardwareMap.get(Servo.class, "intake horizontal");
        motorHorizontalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorHorizontalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorHorizontalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorHorizontalSlide.setPower(0.2);
        intakeVerticalRotationHorizontalSlide.setDirection(Servo.Direction.FORWARD);
    }

    private void runHorizontalSlide() {
        String whatColor = null;
        int red = colorSensor.red();
        int green = colorSensor.green();
        int blue = colorSensor.blue();
        int alpha = colorSensor.alpha();

        telemetry.addData("Green", green);
        if(red>blue && green>red && alpha>blue && red<200){
            whatColor= "yellow";
            leftLight.setPosition(YELLOW);
            rightLight.setPosition(YELLOW);
            clawHorizontalSlide.setPosition(0);
        }else if(red<blue && green<blue && alpha<blue){
            whatColor = "blue";
            leftLight.setPosition(BLUE);
            rightLight.setPosition(BLUE);
            if ( redAlliance ) {
                clawHorizontalSlide.setPosition(1);
            } else {
                clawHorizontalSlide.setPosition(0);
            }
        }else if(red>blue && green<red && alpha<red){
            whatColor = "red";
            leftLight.setPosition(RED);
            rightLight.setPosition(RED);
            if ( redAlliance ) {
                clawHorizontalSlide.setPosition(0);
            } else {
                clawHorizontalSlide.setPosition(1);
            }
        }else{
            whatColor = "NONE";
        }

        if(gamepad1.a){
            clawHorizontalSlide.setPosition(1);
        }
        if (gamepad1.y) {
            intakeVerticalRotationHorizontalSlide.setPosition(0.05);  // Move to position 0.05 when the 'Y' button is pressed
        } else if (gamepad1.x) {
            intakeVerticalRotationHorizontalSlide.setPosition(0.5);  // Move to position 0.5 when the 'X' button is pressed
        }else if (gamepad1.a){
            intakeVerticalRotationHorizontalSlide.setPosition(0.6);
        }

        //Moving the linear extender with the joystick
        if (gamepad2.right_bumper){
            motorHorizontalSlide.setTargetPosition(0);
        }else if(gamepad2.left_bumper){
            motorHorizontalSlide.setTargetPosition(-200);
        }

        // Display the color values on the telemetry
        telemetry.addData("Red", red);
        telemetry.addData("Blue", blue);
        telemetry.addData("Alpha", alpha);
        telemetry.addData("What Color:", whatColor);
        telemetry.addData("Servo position", intakeVerticalRotationHorizontalSlide.getPosition());
        telemetry.addData("Servo claw",  clawHorizontalSlide.getPosition());
        telemetry.addData("Joy Stick:" , gamepad2.left_stick_x);
        telemetry.update();
    }

    private void initVerticalSlide() {
        motorVerticalSlide = hardwareMap.get(DcMotor.class, "vertical");
        clawVerticalSlide = hardwareMap.get(Servo.class, "claw vertical");
        intakeVerticalRotationVerticalSlide = hardwareMap.get(Servo.class, "intake vertical");
        motorVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorVerticalSlide.setTargetPosition(0);
        motorVerticalSlide.setPower(0.3);
        motorVerticalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorVerticalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void runVerticalSlide() {
        //moving the viper slide up to different positions
        if (gamepad1.a){
            motorVerticalSlide.setTargetPosition(BOTTOM_VERTICAL_SLIDE_POSITION);
        } else if (gamepad1.b) {
            motorVerticalSlide.setTargetPosition(TOP_VERTICAL_SLIDE_POSITION);
        }else if (gamepad1.x) {
            motorVerticalSlide.setTargetPosition(MIDDLE_VERTICAL_SLIDE_POSITION);
        }else if (gamepad1.y) {
            motorVerticalSlide.setTargetPosition(LOW_VERTICAL_SLIDE_POSITION);
        }

    }

    private void initColorSensor() {
        colorSensor = hardwareMap.get(ColorSensor.class, "color sensor");
    }

    /**
     * Initialize the AprilTag processor.
     */
    private void initAprilTag() {

        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()

                // The following default settings are available to un-comment and edit as needed.
                //.setDrawAxes(false)
                //.setDrawCubeProjection(false)
                //.setDrawTagOutline(true)
                //.setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                //.setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                //.setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .setCameraPose(cameraPosition, cameraOrientation)

                // == CAMERA CALIBRATION ==
                // If you do not manually specify calibration parameters, the SDK will attempt
                // to load a predefined calibration for your camera.
                //.setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                // ... these parameters are fx, fy, cx, cy.

                .build();

        // Adjust Image Decimation to trade-off detection-range for detection-rate.
        // eg: Some typical detection data using a Logitech C920 WebCam
        // Decimation = 1 ..  Detect 2" Tag from 10 feet away at 10 Frames per second
        // Decimation = 2 ..  Detect 2" Tag from 6  feet away at 22 Frames per second
        // Decimation = 3 ..  Detect 2" Tag from 4  feet away at 30 Frames Per Second (default)
        // Decimation = 3 ..  Detect 5" Tag from 10 feet away at 30 Frames Per Second (default)
        // Note: Decimation can be changed on-the-fly to adapt during a match.
        //aprilTag.setDecimation(3);

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        //builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        //builder.setAutoStopLiveView(false);

        // Set and enable the processor.
        builder.addProcessor(aprilTag);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Disable or re-enable the aprilTag processor at any time.
        //visionPortal.setProcessorEnabled(aprilTag, true);

    }   // end method initAprilTag()

    /**
     * Add telemetry about AprilTag detections.
     */
    private void telemetryAprilTag() {

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)",
                        detection.robotPose.getPosition().x,
                        detection.robotPose.getPosition().y,
                        detection.robotPose.getPosition().z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)",
                        detection.robotPose.getOrientation().getPitch(AngleUnit.DEGREES),
                        detection.robotPose.getOrientation().getRoll(AngleUnit.DEGREES),
                        detection.robotPose.getOrientation().getYaw(AngleUnit.DEGREES)));
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop

        // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");

    }   // end method telemetryAprilTag()



    private void runDriveTrain() {
        // calculating the power for each wheel with controller input
        double frontLeftPower = (gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x)/1.5;
        double backLeftPower = (gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x)/1.5;
        double frontRightPower = (gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x)/1.5;
        double backRightPower = (gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x)/1.5   ;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

    }

    private void runAprilTag() {
        telemetryAprilTag();
        // Push telemetry to the Driver Station.
        telemetry.update();

        // Save CPU resources; can resume streaming when needed.
        if (gamepad1.dpad_down) {
            visionPortal.stopStreaming();
        } else if (gamepad1.dpad_up) {
            visionPortal.resumeStreaming();
        }
    }

 
    @Override
    public void runOpMode() {
 
        initDriveTrain();
        initColorSensor();
        initAprilTag();
        initHorizontalSlide();
        initVerticalSlide();
        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();
 
        waitForStart();
        runtime.reset();
 
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) { 
            runDriveTrain();
            runAprilTag();
            runHorizontalSlide();
            runVerticalSlide();
        }
     }

}
 