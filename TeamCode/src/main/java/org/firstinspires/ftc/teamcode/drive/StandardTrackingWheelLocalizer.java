package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import java.util.List;

/**
 * StandardTrackingWheelLocalizer.java
 *
 * Lesson: Localization with Odometry Wheels
 *
 * Roadrunner provides localization, the ability to track the robot's position on the field.
 * This class extends `ThreeTrackingWheelLocalizer`, a Roadrunner class for robots using
 * three non-driven odometry wheels for localization. Even if we're not using three wheels,
 * this class provides a good starting point.
 *
 * Your Tasks:
 * 1. Identify the positions of your odometry wheels relative to the center of the robot.
 *    Consider x positive towards the front of the robot, y positive towards the left.
 * 2. Implement `getWheelPositions()`. Use encoder ticks to estimate how far each wheel has traveled.
 * 3. Implement `getWheelVelocities()`. This is similar to `getWheelPositions()` but reports speeds.
 * 4. (Optional) If using a fourth wheel or different setup, adjust the class accordingly.
 *
 * Why? Accurate localization allows your robot to perform precise autonomous movements and
 * adapt to in-game dynamics by knowing its exact position and orientation on the field.
 */
public class StandardTrackingWheelLocalizer extends ThreeTrackingWheelLocalizer {

    public StandardTrackingWheelLocalizer() {
        // TODO: Set the positions of your tracking wheels relative to the robot center.
        super(List.of(
                new Pose2d(0, 0, 0), // Example: Front wheel
                new Pose2d(0, 0, Math.toRadians(120)), // Example: Left wheel
                new Pose2d(0, 0, Math.toRadians(240))  // Example: Right wheel
        ));
    }

    @Override
    public List<Double> getWheelPositions() {
        // TODO: Return the positions of each tracking wheel. This will likely involve reading from encoders and converting ticks to distance.
        return List.of(0.0, 0.0, 0.0); // Example placeholder values
    }

    @Override
    public List<Double> getWheelVelocities() {
        // TODO: Similar to getWheelPositions but return the current velocity of each wheel.
        return List.of(0.0, 0.0, 0.0); // Example placeholder values
    }

    // Add any additional methods or overrides as necessary for your setup.
}
