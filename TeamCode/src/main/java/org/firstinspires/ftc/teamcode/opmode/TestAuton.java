package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.java.teamcode.util.VisionPipeline;

@Autonomous
public class TestAuton extends LinearOpMode{
    testPipeline pipe = new testPipeline();
    VisionPipeline eyes = new VisionPipeline(this, pipe);
    @Override
    public void runOpMode(){
        eyes.
    }
}

class testPipeline extends OpenCvPipeline{
    //put your variables here to use later

    @Override// this is the method that tells the pipeline what to do with an image
    public Mat processFrame(Mat input)
    {
        //apply a filter to the image to make it so all of the white image pixels are represented as 1s in a binary image.
        // Convert the image to grayscale
        Mat gray = new Mat();
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_BGR2GRAY);

        // Apply a threshold to create a binary image
        Mat binary = new Mat();
        Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY);

        // Invert the binary image
        Core.bitwise_not(binary, binary);

        // Set the result as the processed frame
        input = binary;
        return input;
    }
}