package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.VisionPipeline;
import org.firstinspires.ftc.teamcode.util.pipelines.PixelLocator;

@Autonomous
public class TestAuton extends LinearOpMode {

    @Override
    public void runOpMode() {
        PixelLocator pipe = new PixelLocator();
        VisionPipeline eyes = new VisionPipeline(this, pipe);
        //waiting for the start to be pressed
        waitForStart();
        //when running
        while(opModeIsActive()){

        }
    }
}