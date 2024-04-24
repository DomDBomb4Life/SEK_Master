package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    //This is where important variables are
    Servo claw;
    double clawposition;
    LinearOpMode opMode;
    //constructor for claw
    public Claw(LinearOpMode opMode){
        this.opMode = opMode;
        claw = opMode.hardwareMap.get(Servo.class,"GateServo");
        clawposition = 0;
    }
    //opening and closing claw
    public void OpenClose(){
        if(opMode.gamepad2.b){
            clawposition = 0.32;
        }else{
            clawposition = 0.38;
        }
        claw.setPosition(clawposition);
    }

}
