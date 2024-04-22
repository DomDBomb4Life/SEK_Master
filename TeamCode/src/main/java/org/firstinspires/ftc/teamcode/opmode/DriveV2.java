package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.robot.*;

@TeleOp(name = "Drive V2")
public class DriveV2<telemetry> extends LinearOpMode {
    //toggle and state variables
    boolean planeLaunched = false;

    //these are all our motors
    private DcMotor FrontL;
    private DcMotor BackL;
    private DcMotor FrontR;
    private DcMotor BackR;
    //servos
    private Servo claw;
    private Servo launcher;


    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */


    @Override
    public void runOpMode() {

        //these are for the movement values
        float leftStickY;
        float leftStickX;
        double driveSpeed;
        float pivot;

        //open and closed position of the gate, launcher, etc
        final double clawOpen = .32;
        final double clawClosed = .38;
        final double launched = .5;
        final double docked = 0;

        //this is all hardwaremaps
        FrontL = hardwareMap.get(DcMotor.class, "FrontL");
        BackL = hardwareMap.get(DcMotor.class, "BackL");
        FrontR = hardwareMap.get(DcMotor.class, "FrontR");
        BackR = hardwareMap.get(DcMotor.class, "BackR");
        claw = hardwareMap.get(Servo.class, "GateServo");
        launcher = hardwareMap.get(Servo.class, "LauncherServo");

        FrontR.setDirection(DcMotorSimple.Direction.REVERSE);
        BackR.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();


        while (opModeIsActive()) {
            // Put run blocks here

            //this is all the drive code
            if (gamepad1.right_trigger == 1) {
                driveSpeed = 1.0;
            }
            else if(gamepad1.left_trigger == 1){
                driveSpeed = 0.25;
            } else {
                driveSpeed = 0.5;
            }
            leftStickY = -gamepad1.left_stick_y;
            leftStickX = gamepad1.left_stick_x;
            pivot = gamepad1.right_stick_x;
            FrontR.setPower((-pivot + (leftStickY - leftStickX)) * driveSpeed);
            BackR.setPower((-pivot + leftStickY + leftStickX) * driveSpeed);
            FrontL.setPower((pivot + leftStickY + leftStickX) * driveSpeed);
            BackL.setPower((pivot + (leftStickY - leftStickX)) * driveSpeed);

            //everything that goes after this is for the gamepad 2

            //open and close the claw
            if(gamepad2.b){
                claw.setPosition(clawOpen);
            }else{
                claw.setPosition(clawClosed);
            }
            //run the airplane launcher
            if(gamepad2.y){
                planeLaunched = planeLaunched ? false : true;
            }

            if(planeLaunched) {
                launcher.setPosition(launched);
            }else{
                launcher.setPosition(docked);
            }

            //update all telemetry. Telemetry is added in its respective blocks
            telemetry.update();
        }
    }


        //constraining methods
        public double constrain(double Var, double Min, double Max){
            Var = Math.min(Math.max(Var, Min), Max);
            return Var;
        }
        public int constrain(int Var, int Min, int Max){
            Var = Math.min(Math.max(Var, Min), Max);
            return Var;
        }
}