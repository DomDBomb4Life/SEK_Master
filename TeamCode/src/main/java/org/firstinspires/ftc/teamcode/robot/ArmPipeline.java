package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmPipeline {
    //my motors
    private DcMotor liftL;
    private DcMotor liftR;
    private DcMotor arm;

    private Servo wrist;

    private OpMode opmode;

    String[] Location = new String[4];

    String startingPoint = "home";

    //costructor
    public ArmPipeline(OpMode opmode){
        //init the opmode
        this.opmode = opmode;
        //init the motors
        liftL = opmode.hardwareMap.get(DcMotor.class, "LiftL");
        liftR = opmode.hardwareMap.get(DcMotor.class, "LiftR");
        arm = opmode.hardwareMap.get(DcMotor.class, "Arm");
        //init the wrist
        wrist = opmode.hardwareMap.get(Servo.class, "Wrist");
    }

    public void moveLift(){
        //test if the lift is moving
        if(!isMoving()){
            //test the starting point of the lift
            if(startingPoint == "home"){

            }
        }
    }

    //is busy method
    private boolean isMoving(){
        return liftL.isBusy() || liftR.isBusy() || arm.isBusy();
    }
}
