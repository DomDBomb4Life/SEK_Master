package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.DriveTrain;
import org.firstinspires.ftc.teamcode.robot.*;

@TeleOp(name = "Drive V2")
public class DriveV2 extends LinearOpMode {
    //toggle and state variables
    boolean planeLaunched = false;

    //these are all our motors

    //servos
    private Servo claw;
    private Servo launcher;

    private ArmPipeline arm;


    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */


    @Override
    public void runOpMode() {
        //Create the robot classes
        DriveTrain mecanumWheels = new DriveTrain(this);


        //open and closed position of the gate, launcher, etc
        final double clawOpen = .32;
        final double clawClosed = .38;
        final double launched = .5;
        final double docked = 0;

        //this is all hardwaremaps

        claw = hardwareMap.get(Servo.class, "GateServo");
        launcher = hardwareMap.get(Servo.class, "LauncherServo");
        arm = new ArmPipeline(this);


        waitForStart();


        while (opModeIsActive()) {
            // Put run blocks here that get looped

            mecanumWheels.drive(this);


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

            //run the arm code
            arm.moveLift();

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