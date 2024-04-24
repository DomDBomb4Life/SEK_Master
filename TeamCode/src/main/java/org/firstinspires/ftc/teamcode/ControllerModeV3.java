package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Drive")
public class ControllerModeV3<telemetry> extends LinearOpMode {
    //Motors
    DcMotor FrontL;
    DcMotor BackL;
    DcMotor FrontR;
    DcMotor BackR;
    DcMotor LiftL;
    DcMotor LiftR;
    DcMotor Arm;
            //servos
    Servo claw;
    Servo launcher;
    Servo wrist;
    //claw positions
    final double clawOpen = .32;
    final double clawClosed = .38;

    //plane launcher variables
    boolean launched = false;
    final double launchPos = .5;
    final double dockedpos = 0;
    //doubles for the drivetrain
    double leftStickY;
    double leftStickX;
    double pivot;
    double speed = .5;

    @Override
    public void runOpMode() {
        //hardwaremaps
        BackR = hardwareMap.get(DcMotor.class, "BackR");
        BackL = hardwareMap.get(DcMotor.class, "BackL");
        FrontR = hardwareMap.get(DcMotor.class, "FrontR");
        FrontL = hardwareMap.get(DcMotor.class, "FrontL");
        LiftL = hardwareMap.get(DcMotor.class,"LiftL");
        LiftR = hardwareMap.get(DcMotor.class,"LiftR");
        Arm = hardwareMap.get(DcMotor.class,"Arm");
        //servo hardwaremaps
        claw = hardwareMap.get(Servo.class, "GateServo");
        launcher = hardwareMap.get(Servo.class,"LauncherServo");
        wrist = hardwareMap.get(Servo.class,"wrist");
        while (opModeIsActive()) {
            leftStickY = gamepad1.left_stick_y;
            leftStickX = gamepad1.left_stick_x;
            pivot = gamepad1.right_stick_x;

            // Control the speed
            if(gamepad1.right_trigger == 1){
                speed = 1;
            }else if(gamepad1.left_trigger == 1){
                speed = .25;
            }else{
                speed = .5;
            }

            // Set motor powers
            FrontR.setPower((-pivot+(leftStickY-leftStickX))*speed);
            BackR.setPower((-pivot+leftStickY+leftStickX)*speed);
            FrontL.setPower((pivot+leftStickY+leftStickX)*speed);
            BackL.setPower((pivot+(leftStickY-leftStickX))*speed);

            // code for the claw
            if(gamepad2.b){
                claw.setPosition(clawOpen);
            }else{
                claw.setPosition(clawClosed);
            }

            // drone launcher
            if(gamepad2.y){
                if(launched){
                    launched = false;
                    launcher.setPosition(launchPos);
                }else{
                    launched = true;
                    launcher.setPosition(dockedpos);
                }
            }


        }
    }
}
