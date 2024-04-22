package org.firstinspires.ftc.teamcode.drive;

/**
 * DriveConstants.java
 *
 * Lesson: Defining Robot Constants
 *
 * This file should contain all the constants related to the drivetrain and robot physical configuration.
 * Keeping constants in one place makes it easier to update and maintain your code.
 *
 * Your Tasks:
 * 1. Define constants for your robot's wheel sizes, gear ratios, track width, etc.
 * 2. Include Roadrunner tuning parameters once determined (e.g., kV, kA, kStatic).
 * 3. Remember, constants should be static and final.
 *
 * Why? Accurate constants are crucial for Roadrunner's motion planning algorithms to work correctly.
 * They ensure that the path planning and following are as close to the real-world movements as possible.
 */
public class DriveConstants {

    // Example constants. Replace these with your actual robot's specifications.
    public static final double WHEEL_RADIUS = 2; // Wheel radius in inches
    public static final double GEAR_RATIO = 1;  // Gear ratio (output (wheel) speed / input (motor) speed)
    public static final double TRACK_WIDTH = 15; // Distance between left and right wheels in inches

    // Roadrunner PID coefficients and feedforward gains
    // These values will need to be tuned for your robot
    public static final double kV = 1.0;
    public static final double kA = 0.0;
    public static final double kStatic = 0.0;

    // Add more constants as necessary (e.g., encoder ticks per revolution, max velocity/acceleration)

    // Prevent instantiation of this utility class
    private DriveConstants() {}
}
