package org.firstinspires.ftc.teamcode.opmode;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;


public class TestAuton {
}

class testPipeline extends OpenCvPipeline{
    //put your variables here to use later

    @Override// this is the method that tells the pipeline what to do with an image
    public Mat processFrame(Mat input)
    {
        return input;
    }
}