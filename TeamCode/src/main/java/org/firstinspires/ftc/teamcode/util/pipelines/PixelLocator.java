package org.firstinspires.ftc.teamcode.util.pipelines;

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

public class PixelLocator extends OpenCvPipeline {
    //put your variables here to use later
    Mat gray;
    Mat binary = new Mat();


    @Override// this is the method that tells the pipeline what to do with an image
    public Mat processFrame(Mat input) {
        //apply a filter to the image to make it so all of the white image pixels are represented as 1s in a binary image.
        // Convert the image to grayscale
        gray = new Mat();
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_BGR2GRAY);

        // Apply a threshold to create a binary image
        binary = new Mat();
        Imgproc.threshold(gray, binary, 230, 255, Imgproc.THRESH_BINARY);

        // Invert the binary image
        Core.bitwise_not(binary, binary);
        
        // Set the result as the processed frame
        return binary;
    }

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


        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            Rect rect = Imgproc.boundingRect(contour);
            double density = area / (rect.width * rect.height);
            
            //determine if the density is the correct amount
           if ((density >= minDensity && density <= maxDensity) && (rect.width <= maxX && rect.width >= minX) && (rect.height <= maxY && rect.height >= minY)) {
               found = true;
               break;
           }
        }
        return found;


        //this method will determine the pixel density of the image
        //this will be used to determine the location of the gamepiece
        //this will be done by counting the number of white pixels in the image and I want to apply some kind of kmeans clustering to the image to determine the probibility of a gamepaice in that image
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

        Mat firstThird = input.submat(new Rect(0, input.rows(), 0, thirdWidth));
        Mat secondThird = input.submat(new Rect(0, input.rows(), thirdWidth, 2 * thirdWidth));
        Mat thirdThird = input.submat(new Rect(0, input.rows(), 2 * thirdWidth, width));

        return new Mat[]{firstThird, secondThird, thirdThird};
    }
}
        