package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class PlaneLauncher {
    Servo launcher;
    double open = 0.5;
    double closed = 0;
    double position = closed;
    boolean planeLaunched = false;
    final double launched = .5;
    final double docked = 0;
    OpMode opMode;
    //constructor for launcher
    public PlaneLauncher(LinearOpMode opMode){
        launcher = opMode.hardwareMap.get(Servo.class,"LauncherServo");
        this.opMode = opMode;
    }
    public void Launch(){
        boolean buttonReleased = true;
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
