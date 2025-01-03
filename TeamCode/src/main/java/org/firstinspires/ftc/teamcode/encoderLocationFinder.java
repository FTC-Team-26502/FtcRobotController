package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class encoderLocationFinder extends OpMode {
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
        sideways = hardwareMap.get(DcMotor.class, "horizontalExtender");
        sideways.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sideways.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        sideways.setTargetPosition(0);
//        sideways.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        sideways.setPower(0.9);
//        sideways.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        up = hardwareMap.get(DcMotor.class, "vertical");
//        up.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        up.setTargetPosition(0);
//        up.setPower(0.9);
//        up.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        up.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void loop() {
//        if (gamepad2.right_bumper){
//            sideways.setTargetPosition(0);
//        }else if(gamepad2.left_bumper){
//            sideways.setTargetPosition(-850);
//        }
//        if (gamepad1.a){
//            up.setTargetPosition(bottom);
//        } else if (gamepad1.b) {
//            up.setTargetPosition(top);
//        }else if (gamepad1.x) {
//            up.setTargetPosition(middle);
//        }else if (gamepad1.y) {
//            up.setTargetPosition(low);
//        }


        telemetry.addData("Status", "Running");
        telemetry.addData("position", sideways.getCurrentPosition());
        telemetry.addData("gamepad", gamepad1.left_stick_x);
        telemetry.addData("Target Pose", sideways.getTargetPosition() );
        telemetry.update();
    }
}

