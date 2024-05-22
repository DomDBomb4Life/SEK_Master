package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.VisionPipeline;
import org.firstinspires.ftc.teamcode.util.pipelines.PixelLocator;
import org.openftc.easyopencv.OpenCvPipeline;

@TeleOp(name = "Pixel Locator Test", group = "Test")
public class PixelLocatorOpMode extends OpMode {
    private VisionPipeline visionPipeline;
    private PixelLocator pixelLocator;

    @Override
    public void init() {
        // Initialize the PixelLocator pipeline
        pixelLocator = new PixelLocator(this);

        // Initialize the VisionPipeline with the PixelLocator pipeline
        visionPipeline = new VisionPipeline(this, pixelLocator);
    }

    @Override
    public void loop() {
        // Retrieve the pixel location
        int pixelLocation = pixelLocator.getPixelLocation();

        // Display the pixel location on the telemetry
        telemetry.addData("Pixel Location", pixelLocation);
        telemetry.update();
    }

    @Override
    public void stop(){
        visionPipeline.webcam.stopStreaming();
    }
}
