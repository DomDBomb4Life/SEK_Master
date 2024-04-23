package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class PlaneLauncher {
    Servo launcher;
    boolean planeLaunched = false;
    final double launched = .5;
    final double docked = 0;
    private boolean buttonReleased = true;
    //constructor for launcher
    public PlaneLauncher(OpMode opMode){
        launcher = opMode.hardwareMap.get(Servo.class,"LauncherServo");
    }
    public void Launch(OpMode opMode){
        if(!opMode.gamepad2.y){
            buttonReleased = true;
        }
        if(opMode.gamepad2.y && buttonReleased) {
            planeLaunched = !planeLaunched;
            buttonReleased = false;
        }

        if(planeLaunched) {
            launcher.setPosition(launched);
        }else{
            launcher.setPosition(docked);
        }
    }
}
