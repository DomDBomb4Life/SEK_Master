package org.firstinspires.ftc.teamcode.opmode;

import org.firstinspires.ftc.teamcode.opmode.TestAuton; // Add this import statement


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.VisionPipeline;
import org.firstinspires.ftc.teamcode.util.pipelines.PixelLocator;

@Autonomous
public class TestAuton extends LinearOpMode {

    @Override
    public void runOpMode() {
        PixelLocator pipe = new PixelLocator(this);
        VisionPipeline eyes = new VisionPipeline(this, pipe);



        //waiting for the start to be pressed
        do{
            //variables related to states and encoders
            int location = pipe.getPixelLocation();
            telemetry.addData("Location", location);
            telemetry.update();
        }while (!isStarted() && !isStopRequested());
        
        waitForStart();
        

        //when running
    }
}