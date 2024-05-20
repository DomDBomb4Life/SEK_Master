package org.firstinspires.ftc.teamcode.util.pipelines;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.TermCriteria;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.opencv.core.Rect;
import org.opencv.core.Size;

public class PixelLocator extends OpenCvPipeline {
    //put your variables here to use later
    Mat gray = new Mat();
    Mat binary = new Mat();
    OpMode opmode;


    //constructor

    public PixelLocator(OpMode opmode){
        this.opmode = opmode;

    }

    @Override// this is the method that tells the pipeline what to do with an image
    public Mat processFrame(Mat input) {
        // Reduce the resolution of the image


        // Apply a filter to the resized image to make it so all of the white image pixels are represented as 1s in a binary image.
        // Convert the resized image to grayscale
        Mat gray = new Mat();
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_BGR2GRAY);

        // Apply a threshold to create a binary image
        Mat binary = new Mat();
        Imgproc.threshold(gray, binary, 225, 255, Imgproc.THRESH_BINARY);

        // Invert the binary image
        // Core.bitwise_not(binary, binary);
        
        // Set the result as the processed frame
        opmode.telemetry.addLine("new frame");

        return binary;
    }

    // public Mat processFrame(Mat input) {
    //     // Convert the input image to grayscale
    //     Mat gray = new Mat();
    //     Imgproc.cvtColor(input, gray, Imgproc.COLOR_BGR2GRAY);

    //     // Apply a Gaussian blur to reduce noise
    //     Mat blurred = new Mat();
    //     Imgproc.GaussianBlur(gray, blurred, new Size(5, 5), 0);

    //     // Apply a threshold to create a binary image
    //     Mat binary = new Mat();
    //     Imgproc.threshold(blurred, binary, 225, 255, Imgproc.THRESH_BINARY);

    //     // Apply dilation and erosion to close gaps in contours
    //     Mat dilated = new Mat();
    //     Imgproc.dilate(binary, dilated, new Mat(), new Point(-1, -1), 2); // 2 iterations

    //     Mat eroded = new Mat();
    //     Imgproc.erode(dilated, eroded, new Mat(), new Point(-1, -1), 1); // 1 iteration

    //     // Invert the binary image
    //     Core.bitwise_not(eroded, eroded);

    //     // Set the result as the processed frame
    //     opmode.telemetry.addLine("new frame");

    //     return eroded;
    // }
    
    //this method finds the gamepiece location and returns it based on an int
    public int getPixelLocation(){
        //splice the image into thirds
        Mat[] thirds = getThirds(binary);

        for (Mat third : thirds) {
            boolean found = determinePixelDensity(third);
            if (found) {
                // Return the index of the third where the gamepiece is found
                return Arrays.asList(thirds).indexOf(third);
            }
        }

        // Return -1 if the gamepiece is not found in any third
        return -1;
    }

    public boolean determinePixelDensity(Mat input) {
        int minDensity = 50;
        int maxDensity = 100;

        int minX = 50;
        int maxX = 100;

        int minY = 10;
        int maxY = 50;

        boolean found = false;
        // Find contours in the binary image
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(binary, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_NONE);

        opmode.telemetry.addData("contours", contours.size());
        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            Rect rect = Imgproc.boundingRect(contour);
            double density = area / (rect.width * rect.height);
            
            //telemetry stuff
            // opmode.telemetry.addLine("Density: " + density);
            // opmode.telemetry.addLine("width: " + rect.width);
            // opmode.telemetry.addLine("height: " + rect.height);
            // opmode.telemetry.update();

            //determine if the density is the correct amount
           if ((density >= minDensity && density <= maxDensity) && (rect.width <= maxX && rect.width >= minX) && (rect.height <= maxY && rect.height >= minY)) {
               found = true;
               break;
           }
        }
        return found;
    }

    public void addTelemetry(MatOfPoint contour){
        // Add telemetry for the contours
     
    }

    //*******************************************************************************************
    

    //*******************************************************************************************

//     public double determinePixelDensity_V2(Mat input){
//         // Convert the binary image to a set of points
//         List<Point> points = new ArrayList<>();
//             for (int y = 0; y < input.rows(); y++) {
//                 for (int x = 0; x < input.cols(); x++) {
//                     if (input.get(y, x)[0] > 0) {
//                         points.add(new Point(x, y));
//                     }
//                 }
//             }

//         // Convert the list of points to a MatOfPoint2f
//         MatOfPoint2f pointsMat = new MatOfPoint2f();
//         pointsMat.fromList(points);

//         // Apply the k-means algorithm to cluster the points
//         TermCriteria criteria = new TermCriteria(TermCriteria.EPS + TermCriteria.MAX_ITER, 100, 0.2);
//         Mat labels = new Mat();
//         Mat centers = new Mat();
//         Core.kmeans(pointsMat, 3, labels, criteria, 3, Core.KMEANS_PP_CENTERS, centers);

//         // Calculate the density of each cluster
//         int[] counts = new int[3];
//         for (int i = 0; i < labels.rows(); i++) {
//             int clusterIdx = (int) labels.get(i, 0)[0];
//             counts[clusterIdx]++;
//         }

//         // Calculate the density of each cluster
//         double[] densities = new double[3];
//         for (int i = 0; i < 3; i++) {
//             double area = Imgproc.contourArea(new MatOfPoint(centers.row(i)));
//             densities[i] = counts[i] / area;
//         }

//         // Return the maximum density
//         return Arrays.stream(densities).max().getAsDouble();
//     }


//     //this method splits the image into thirds and returns a matrix of the images
    public Mat[] getThirds(Mat input){
        int width = input.cols();
        int thirdWidth = width / 3;

        Mat firstThird = input.submat(new Rect(0, 0, thirdWidth, input.rows()));
        Mat secondThird = input.submat(new Rect(thirdWidth, 0, thirdWidth, input.rows()));
        Mat thirdThird = input.submat(new Rect(2 * thirdWidth, 0, thirdWidth, input.rows()));

        return new Mat[]{firstThird, secondThird, thirdThird};
    }
}
        