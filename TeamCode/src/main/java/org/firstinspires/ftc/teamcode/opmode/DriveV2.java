package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.drive.DriveTrain;
import org.firstinspires.ftc.teamcode.robot.*;

@TeleOp(name = "Drive V2")
public class DriveV2 extends LinearOpMode {
    //Initiating the classes
    private PlaneLauncher launcher;
    private Claw claw;
    private ArmPipeline arm;

    private DriveTrain mecanumWheels;



    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */


    @Override
    public void runOpMode() {
        //Create the robot classes
        mecanumWheels = new DriveTrain(this);
        launcher = new PlaneLauncher(this);
        arm = new ArmPipeline(this);
        claw = new Claw(this);


        waitForStart();


        while (opModeIsActive()) {
            // Put run blocks here that get looped

            mecanumWheels.drive(this);


            //everything that goes after this is for the gamepad 2

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
}