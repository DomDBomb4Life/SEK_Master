package org.firstinspires.ftc.teamcode.util.pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class PixelLocator extends OpenCvPipeline {
    //put your variables here to use later

    @Override// this is the method that tells the pipeline what to do with an image
    public Mat processFrame(Mat input) {
        //apply a filter to the image to make it so all of the white image pixels are represented as 1s in a binary image.
        // Convert the image to grayscale
        Mat gray = new Mat();
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_BGR2GRAY);

        // Apply a threshold to create a binary image
        Mat binary = new Mat();
        Imgproc.threshold(gray, binary, 230, 255, Imgproc.THRESH_BINARY);

        // Invert the binary image
        Core.bitwise_not(binary, binary);

        // Set the result as the processed frame
        input = binary;
        return input;
    }
}
