package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.drive.DriveTrain;
import org.firstinspires.ftc.teamcode.robot.*;

@TeleOp(name = "Drive V2")
public class DriveV2 extends LinearOpMode {
    //Initiating the classes
    private Claw claw = new Claw(this);
    private ArmPipeline arm;

    private PlaneLauncher launcher = new PlaneLauncher(this);


    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */


    @Override
    public void runOpMode() {
        //Create the robot classes
        DriveTrain mecanumWheels = new DriveTrain(this);

        //this is all hardwaremaps
        arm = new ArmPipeline(this);


        waitForStart();


        while (opModeIsActive()) {
            // Put run blocks here that get looped
            mecanumWheels.drive(this);

            //open and close the claw
            claw.OpenClose();
            //run the airplane launcher
            launcher.Launch(this);

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