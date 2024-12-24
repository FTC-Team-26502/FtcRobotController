package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class LinearTester extends OpMode {
    protected DcMotor sideways;
    protected DcMotor up;

    int end = -300;
    int start = 300;
    int top = 4000;
    int bottom = 0;
    int middle = 1200;
    int low = 700;



    @Override
    public void init() {
        sideways = hardwareMap.get(DcMotor.class, "backleft");
        sideways.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sideways.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sideways.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        up = hardwareMap.get(DcMotor.class, "vertical");
        up.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        up.setTargetPosition(0);
        up.setPower(0.9);
        up.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        up.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void loop() {
        if (sideways.getCurrentPosition()<start && sideways.getCurrentPosition()>end){
            double power = gamepad1.left_stick_x/10;
            sideways.setPower(power);
            telemetry.addData("Motor moving", true);
        }else if(gamepad1.left_stick_x>0){
            sideways.setPower(gamepad1.left_stick_x/10);
        }else if(gamepad1.left_stick_x<0){
            sideways.setPower(gamepad1.left_stick_x/10);
        }else{
            sideways.setPower(0);
        }
        if (gamepad1.a){
            up.setTargetPosition(bottom);
        } else if (gamepad1.b) {
            up.setTargetPosition(top);
        }else if (gamepad1.x) {
            up.setTargetPosition(middle);
        }else if (gamepad1.y) {
            up.setTargetPosition(low);
        }


        telemetry.addData("Status", "Running");
        telemetry.addData("position", up.getCurrentPosition());
        telemetry.addData("gamepad", gamepad1.left_stick_x);
        telemetry.addData("Target Pose", up.getTargetPosition() );
        telemetry.update();
    }
}

